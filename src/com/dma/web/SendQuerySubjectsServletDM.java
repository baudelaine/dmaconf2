package com.dma.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zeroturnaround.zip.ZipUtil;

import com.dma.datamodule.DM;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class AppendSelectionsServlet
 */
@WebServlet(name = "SendQuerySubjectsDM", urlPatterns = { "/SendQuerySubjectsDM" })
public class SendQuerySubjectsServletDM extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendQuerySubjectsServletDM() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map<String, Object> parms = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();

		try {
			
			result.put("CLIENT", request.getRemoteAddr() + ":" + request.getRemotePort());
			result.put("SERVER", request.getLocalAddr() + ":" + request.getLocalPort());
			
			result.put("FROM", this.getServletName());
			
			String user = request.getUserPrincipal().getName();
			result.put("USER", user);

			result.put("JSESSIONID", request.getSession().getId());
			
			Path wks = Paths.get(getServletContext().getRealPath("/datas") + "/" + user);			
			result.put("WKS", wks.toString());
			
			Path prj = Paths.get((String) request.getSession().getAttribute("projectPath"));
			result.put("PRJ", prj.toString());
			
			parms = Tools.fromJSON(request.getInputStream());
			result.put("PARMS", parms);

			if(parms != null) {

				String projectName = (String) parms.get("projectName");
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
//				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				mapper.setSerializationInclusion(Include.NON_NULL);

				List<QuerySubject> qsList = null;
				DM dm = null;
				
				
				try {
					String data = (String) parms.get("data");
					qsList = Arrays.asList(mapper.readValue(data, QuerySubject[].class));

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					result.put("STATUS", "KO");
					result.put("ERROR", "Error parsing DMA QuerySubject List." );
					result.put("TROUBLESHOOTING", "");
					e.printStackTrace();
					throw e;
				}				
					
				Path DMModel = Paths.get((String) request.getSession().getAttribute("projectPath") + "/dm.json");
				if(! Files.exists(DMModel)){
					result.put("STATUS", "KO");
					result.put("ERROR", "Error " + DMModel.getFileName() + " not found in project.");
					result.put("TROUBLESHOOTING", "");
					throw new FileNotFoundException(DMModel.toString());
				}

				try {
					dm = mapper.readValue(DMModel.toFile(), DM.class);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					result.put("STATUS", "KO");
					result.put("ERROR", "Error parsing DM from " + DMModel.getFileName() + ".");
					result.put("TROUBLESHOOTING", "");
					e.printStackTrace();
					throw e;
				}				
				
				if(dm != null) {
					
					dm.setRelationship(null);

					Path json = Paths.get("/tmp/" + user + "-" + projectName + ".json");
					Path zip = Paths.get("/tmp/" + user + "-" + projectName + ".zip");
					
					mapper.writeValue(json.toFile(), dm);

					ZipUtil.packEntry(json.toFile(), zip.toFile());
					
					if(Files.exists(zip)) {
						zip.toFile().setReadable(true, false);
						zip.toFile().setWritable(true, false);
						zip.toFile().setExecutable(true, false);
						result.put("FILENAME", zip.toString());
						request.getServletContext().setAttribute("publishedModelPath", zip);
					}
					else {
						result.put("STATUS", "KO");
						throw new Exception(zip.toString() + " not found.");
					}					
					
					
					result.put("STATUS", "OK");
					result.put("MESSAGE", projectName + " published sucessfully.<br>" + zip.toString() + " will be downloaded.");
				}
					
				
			}
			else {
				result.put("STATUS", "KO");
				result.put("ERROR", "Input parameters are not valid.");
				result.put("TROUBLESHOOTING", "Blablabla...");
				throw new Exception();
			}			
			result.put("STATUS", "OK");
			
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("STATUS", "KO");
			result.put("EXCEPTION", e.getClass().getName());
			result.put("MESSAGE", e.getMessage());
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			result.put("STACKTRACE", sw.toString());
			e.printStackTrace(System.err);
		}

		finally{
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Tools.toJSON(result));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}