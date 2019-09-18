package com.pelatro.asi;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.json.JSONObject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ProvisionHandler implements HttpHandler{
	
	String query = null;
	
	FileReader fr = null;
	
	BufferedReader br =null;
	
	String text;
	
	String notFound = "Status code : 404  Owner not present";
	
	String invalid = "Status code : 412 Invalid Offer";
	
	String res = "Status code : 400  Malformed Request";
	
	String ak = "Status code : 200 Acknowledgement of provisioning";
	
	String ie = "Status code : 500 Internal Error";
	
	ArrayList<String> arr = new ArrayList<>(100);
	
	ArrayList<String> arrid = new ArrayList<String>(100);

		   @Override
		    public void handle(HttpExchange he) throws IOException {
		     System.out.println("Serving the request");

		     if (he.getRequestMethod().equalsIgnoreCase("POST")) {
		    	 try {
		    		 
	                    // REQUEST Headers
	                    Headers requestHeaders = he.getRequestHeaders();
	                    Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();
	 
	                    int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
	 
	                    // REQUEST Body
	                    
	                    InputStream is = he.getRequestBody();
	                    OutputStream os = he.getResponseBody();
	                    byte[] data = new byte[contentLength];
	                    int length = is.read(data);
	                    String s = new String(data);
	                    if(s !=null && ! s.isEmpty()) {
	                    System.out.println(s);
	                    JSONObject jsnObj = new JSONObject(s.toString());
	                    String msisdn = jsnObj.getString("owner");
	                    String tckid = jsnObj.getString("id");
	                    System.out.println("owner"+jsnObj.getString("owner"));
	                    try {
	                    fr = new FileReader( "/home/anishar/Documents/ASI/pvid.txt" );
	                    br = new BufferedReader(fr );
	        			text = br.readLine();
	        			while(text !=null && text.length()!=0) {
	        				arr.add(text);
	        				text = br.readLine();
	        			}}
	        			 catch(FileNotFoundException | NullPointerException e) {
		                    	System.out.println("Internal error");
		                    	he.sendResponseHeaders(500, ie.length());
		                    	os.write(ie.getBytes());
		                    	System.exit(1);
		                    }
	                    
	        			System.out.println(arr);
	        			if(arr.contains(msisdn)!=true) {
	        				System.out.println("1");
	        				he.sendResponseHeaders(404, notFound.length());
	    					os.write(notFound.getBytes());
	        			}
	        			else {
	        				try {
	        			fr = new FileReader( "/home/anishar/Documents/ASI/tckid.txt" );
	                    br = new BufferedReader(fr );
	        			text = br.readLine();
	        			while(text !=null && text.length()!=0) {
	        				arrid.add(text);
	        				text = br.readLine();
	        			}}catch(FileNotFoundException | NullPointerException e) {
	                    	System.out.println("Internal error");
	                    	he.sendResponseHeaders(500, ie.length());
	                    	os.write(ie.getBytes());
	                    	System.exit(1);
	                    }
                    
	        				
	        			if(arrid.contains(tckid)!=true) {
	        				he.sendResponseHeaders(412, invalid.length());
	    					os.write(invalid.getBytes());
	        			}
	        			else {
	        				he.sendResponseHeaders(200, ak.length());
	        				os.write(ak.getBytes());
	        			}
	                    }
	                    }
	                    else {
	                    	 he.sendResponseHeaders(400, res.length());
	   				      os.write(res.getBytes());
	                    }
	                   
	                    //os.write(data);
	 
	                   // he.close();
	 
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	 
	        }
	    }
	
			  
			  
			 
					    

