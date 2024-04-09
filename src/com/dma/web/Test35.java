package com.dma.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.zeroturnaround.zip.ZipUtil;


public class Test35 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Path zip = Paths.get("/home/fr054721/Documents/datacc/isHidden.zip");

		if(!Files.exists(zip)){
			System.out.println("ERROR: " + zip.toString() + " is missing !!!");
		}
		else {
			
			try {
				@SuppressWarnings("unused")
				ZipFile zipFile = new ZipFile(zip.toFile());
			}
			catch(ZipException ze) {
				throw new Exception("ERROR: " + zip.getFileName() + " is not a valid zip file !!!");
			}
			
			String csvExt = ".csv";
			Charset csvCS = Charset.forName("UTF-16");
			String csvSep = "\t";
			
            String dirSep = FileSystems.getDefault().getSeparator();
			BufferedOutputStream dest = null;
			int BUFFER = Long.bitCount(Files.size(zip));
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(Files.newInputStream(zip))); 
			ZipEntry entry;
			Path tmpdir = Files.createTempDirectory("isHidden");
			String entryDir = "";
			while((entry = zis.getNextEntry()) != null) {
		            int count;
		            byte datas[] = new byte[BUFFER];
		            // write the files to the disk
		            Path p = Paths.get(tmpdir + dirSep + entry.getName());
		            System.out.println("FileSystems.getDefault().getSeparator(): " + FileSystems.getDefault().getSeparator()); 
		            entryDir = entry.getName().substring(0, entry.getName().lastIndexOf(dirSep));
		            System.out.println("entryDir: " + entryDir);
		            if(!Files.exists(p.getParent())) {
			            System.out.println("Creating: " + p.getParent().toString());
		            	Files.createDirectories(p.getParent());
		            }
					System.out.println("Extracting: " + entry);
		            FileOutputStream fos = new FileOutputStream(tmpdir + dirSep + entry.getName());
		            dest = new BufferedOutputStream(fos, BUFFER);
		            while ((count = zis.read(datas, 0, BUFFER)) != -1) {
		               dest.write(datas, 0, count);
		            }
		            dest.flush();
		            dest.close();
	        }
			zis.close();
			
			Map<String, List<String>> isHiddens = new HashMap<String, List<String>>();
			
			File[] files = new File(tmpdir.toString() + dirSep +  entryDir).listFiles((dir, name) -> name.endsWith(csvExt));
			for(File file: files) {
				System.out.println(file.getName());
				String table = StringUtils.split(file.getName(), ".csv")[0];
				List<String> lines = Files.readAllLines(file.toPath(), csvCS);
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
			
			FileUtils.forceDelete(tmpdir.toFile());
			System.out.println(Tools.toJSON(isHiddens));
			
		}
		
	}

}
