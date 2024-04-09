package com.dma.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class AppendSelectionsServlet
 */
@WebServlet(name = "SetHiddenFromZipFile", urlPatterns = { "/SetHiddenFromZipFile" })
public class SetHiddenFromZipFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetHiddenFromZipFileServlet() {
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
			
			//Needed if read file line by line and load a List<String> 
//			List<String> lines = new ArrayList<String>();
			Map<String, List<String>> isHiddens = new HashMap<String, List<String>>();
			String entryDir = "";
			Path tmpDir = Files.createTempDirectory("isHidden");
            String dirSep = FileSystems.getDefault().getSeparator();

			
			//Either handle multipart/form-data ajax enctype
			if(ServletFileUpload.isMultipartContent(request)){
				
				List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : items) {
					if (!item.isFormField()) {
						//Item is the file (and not a field)
						//Either read file line by line and load a List<String> for future use
//						LineNumberReader reader = new LineNumberReader(new BufferedReader(new InputStreamReader(item.getInputStream())));
//						String line;
//					    while ((line = reader.readLine()) != null) {
//					    	lines.add(line);
//					    }
//						result.put("READING", "OK");
					    //Or write file in project
						
						Path file = Paths.get(tmpDir + dirSep + item.getName());
						Files.copy(new BufferedInputStream(item.getInputStream()), file, StandardCopyOption.REPLACE_EXISTING);
						file.toFile().setReadable(true, false);
						file.toFile().setWritable(true, false);
						result.put("WRITING", item.getName());
						
						try {
							@SuppressWarnings("unused")
							ZipFile zipFile = new ZipFile(file.toFile());
						}
						catch(ZipException ze) {
							result.put("STATUS", "KO");
							throw new Exception("File uploaded is not a valid zip file.");
						}
						
						BufferedOutputStream dest = null;
						int BUFFER = Long.bitCount(Files.size(file));
						ZipInputStream zis = new ZipInputStream(new BufferedInputStream(Files.newInputStream(file))); 
						ZipEntry entry;
						while((entry = zis.getNextEntry()) != null) {
					            int count;
					            byte datas[] = new byte[BUFFER];
					            // write the files to the disk
					            Path p = Paths.get(tmpDir + dirSep + entry.getName());
					            System.out.println("FileSystems.getDefault().getSeparator(): " + FileSystems.getDefault().getSeparator());
					            try {
					            entryDir = entry.getName().substring(0, entry.getName().lastIndexOf(dirSep));
					            }
					            catch(StringIndexOutOfBoundsException sioobe) {
					            	entryDir = "";
					            }
					            System.out.println("entryDir: " + entryDir);
					            if(!Files.exists(p.getParent())) {
						            System.out.println("Creating: " + p.getParent().toString());
					            	Files.createDirectories(p.getParent());
					            }
								System.out.println("Extracting: " + entry);
					            FileOutputStream fos = new FileOutputStream(tmpDir + dirSep + entry.getName());
					            dest = new BufferedOutputStream(fos, BUFFER);
					            while ((count = zis.read(datas, 0, BUFFER)) != -1) {
					               dest.write(datas, 0, count);
					            }
					            dest.flush();
					            dest.close();
				        }
						zis.close();
					    
					}
					else {
						//Item is field (and not a file)
						if (item.isFormField()) {
							item.getFieldName();
				            String value = item.getString();
				            parms = Tools.fromJSON(new ByteArrayInputStream(value.getBytes()));
				            result.put("PARMS", parms);
						}
					}
				}
			}
			//Or handle ajax json dataType
			else {
				parms = Tools.fromJSON(request.getInputStream());
				result.put("PARMS", parms);

			}
			
			if(parms != null && parms.get("csvExt") != null && parms.get("csvCS") != null && parms.get("csvSep") != null) {
				
				String csvExt = (String) parms.get("csvExt");
				Charset csvCS = Charset.forName((String) parms.get("csvCS"));
				String csvSep = (String) parms.get("csvSep");
				
				File[] files = new File(tmpDir.toString() + dirSep +  entryDir).listFiles((dir, name) -> name.endsWith(csvExt));
				for(File fic: files) {
					System.out.println(fic.getName());
					String table = StringUtils.split(fic.getName(), ".csv")[0];
					List<String> lines = Files.readAllLines(fic.toPath(), csvCS);
					List<String> fields = Arrays.asList(lines.get(0).split(csvSep));
					List<String> contents = Arrays.asList(lines.get(1).split(csvSep));
					List<String> emptyFields = new ArrayList<String>();
					
					int i = 0;
					for(String content: contents) {
						
						if(content.isEmpty() || content.equalsIgnoreCase("0")) {
							emptyFields.add(fields.get(i));
						}
						i++;
					}
					
					isHiddens.put(table, emptyFields);
				}
				
				result.put("DATAS", isHiddens);
				FileUtils.forceDelete(tmpDir.toFile());
			}
			else {
				result.put("STATUS", "KO");
				result.put("ERROR", "Input parameters are not valid.");
				result.put("TROUBLESHOOTING", "...");
				throw new Exception();
			}			
			result.put("STATUS", "OK");
			
			
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("STATUS", "KO");
			result.put("EXCEPTION", e.getClass().getName());
			result.put("MESSAGE", e.getMessage());
			result.put("TROUBLESHOOTING", "If MalformedInputException, try uploading with another charset.");
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