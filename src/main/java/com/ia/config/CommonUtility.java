package com.ia.config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.springframework.web.multipart.MultipartFile;

public class CommonUtility 
{ 
	public static int NO_OFF_RECORDS = 10;
	public static  String REQUEST_URL = "";
	public static String ENRICH_URL = "http://74.207.234.174:5000";
	public static double TOTAL_RECEORD = 0;
	public static int LOGIN_USER_ID = 0;
	public static int USER_ROLE_ID = 0;
  
	
	 public static double getCsvCount(String filename) throws IOException {
		    InputStream is = new BufferedInputStream(new FileInputStream(filename));
		    try {
		    byte[] c = new byte[1024];
		    double count = 0;
		    int readChars = 0;
		    boolean empty = true;
		    while ((readChars = is.read(c)) != -1) {
		        empty = false;
		        for (int i = 0; i < readChars; ++i) {
		            if (c[i] == '\n') {
		                ++count;
		            }
		        }
		    }
		    return (count == 0 && !empty) ? 1 : count;
		    } finally {
		    is.close();
		   }
		}
	 
	 public static boolean checkUniqueDomain(String csvFile) {
	        String line = "";
	        String cvsSplitBy = ",";
	        int count = 0;
	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        	HashMap<String,String> hashMap = new HashMap<>();
	            while ((line = br.readLine()) != null) {
	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);
	                hashMap.put(country[1], country[1]);
	                count++;
	            }
	            if(count==hashMap.size()) {
	            	TOTAL_RECEORD = count;
	            	return true;
	            }
	           // System.out.println("Total =---->"+count +"---"+hashMap.size());
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 return false;
		 
	 }
	 
	 public static String getDate(String convertDate) {
		 
		 Date date = new Date();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		 try {
			date = dateFormat.parse(convertDate);
			
			convertDate = format.format(date);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return convertDate;
	 }
	 
	 public static String fileUpload(MultipartFile file,String dataDirectory) {
			String orignalFileName = file.getName();
			if (!file.isEmpty()) {
				try {
					orignalFileName = file.getOriginalFilename();
					byte[] bytes = file.getBytes();

					File dir = new File(dataDirectory);
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					dataDirectory = dir.getAbsolutePath() + File.separator + orignalFileName;
					File serverFile = new File(dir.getAbsolutePath() + File.separator + orignalFileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					System.out.println("File uploaded success fully"+dataDirectory);
					return orignalFileName;
					//return "You successfully uploaded file=" + name;
				} catch (Exception e) {
					//return "You failed to upload " + name + " => " + e.getMessage();
					System.out.println("Error ---"+e);
				}
			} else {
				//return "You failed to upload " + name + " because the file was empty.";
				System.out.println("File is empty");
			}
		 return "";
	 }
	 
 public static String fileUploads(MultipartFile file) {
		 
		 String path = "";
			String orignalFileName = file.getName();

			if (!file.isEmpty()) {
				try {
					orignalFileName = file.getOriginalFilename();
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					path = rootPath + File.separator + "tmpFiles";
					/*String rootPath = request.getContextPath()+"/resources/";
					path = rootPath + File.separator + "upload";*/
					File dir = new File(path);
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					path = dir.getAbsolutePath() + File.separator + orignalFileName;
					File serverFile = new File(dir.getAbsolutePath() + File.separator + orignalFileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					
					//System.out.println(ext+"---path-->"+path+"----"+orignalFileName+"--------------"+dir.getAbsolutePath());
					System.out.println("Server File Location="+ serverFile.getAbsolutePath());
					return serverFile.getAbsolutePath();
					//return "You successfully uploaded file=" + name;
				} catch (Exception e) {
					//return "You failed to upload " + name + " => " + e.getMessage();
					System.out.println("Error ---"+e);
				}
			} else {
				//return "You failed to upload " + name + " because the file was empty.";
				System.out.println("File is empty");
			}
		 
		 
		 return "";
	 }
	 
} 
