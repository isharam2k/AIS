package com.pelatro.asi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class IsOfferAvailable implements HttpHandler {

	//HashMap<String, String> hm;
	private static final String KEY="[U3VuaWw6U0s=]";
	static HashMap<String, String> queryHm=new HashMap<>();
	String json="";
	String line="";
	String text="";
	String response="";
	String query;
	String res = "Status code : 400  Malformed Request";
	String notFound = "Status code : 404  Owner not present";
	String ie = "Status code : 500 Internal Error";
	OutputStream os;
	String msisdn;
	HashMap<String, String> hm;
	
	String getFile(HashMap<String, String> queryHm) {
		FileReader msisdnFile;
		BufferedReader br;
		String line[] = new String[2];
		String text;
		String json = "";
		hm = new HashMap<>();
		try {
			msisdnFile = new FileReader( "/home/anishar/Documents/ASI/pvid.txt" );
			br = new BufferedReader( msisdnFile );
			text = br.readLine();
			while ( text != null && text.length() != 0 ) {
				line = text.split( ":" );
				hm.put( line[0], line[1] );
				text = br.readLine();
			}

		}
		catch ( NullPointerException | IOException e ) {
			e.printStackTrace();
			return null;
			
		}
		msisdn = queryHm.get( "owner" ).replace( "\"", "" );
		String path = hm.get( msisdn ).trim();
		path = path.trim();
		FileReader fr;
		try {
			fr = new FileReader( path );
			br = new BufferedReader( fr );
			text = br.readLine();
			while ( text != null ) {
				json += text;
				text = br.readLine();
			}
		}
		catch ( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
	 
	 public void getParams() {
		 for(String param: query.split("&")) {
	    		String[] entry = param.split("=");
	    		queryHm.put(entry[0], entry[1]);
	    	}
	 }
	
	@Override
	public void handle( HttpExchange h ) throws IOException {
		
		if(hm.containsKey(msisdn)!=false) {
			getFile(queryHm);
		}
		else {
			h.sendResponseHeaders(404, notFound.length());
			os.write(notFound.getBytes());
		}
		
		JSONObject jsonObject = new JSONObject();
		String k = h.getRequestHeaders().get( "Basic" ).toString();
		if(k.equals(KEY)) { 
		IsOfferAvailable file = new IsOfferAvailable();
		query=h.getRequestURI().getQuery();
		if(query!=null && !query.isEmpty()) {
	    	   getParams();
			}
			else {
				  h.sendResponseHeaders(400, res.length());
			      os.write(res.getBytes());
			
			}
 
    	text= file.getFile(queryHm).toString();
    	if(text != null) {
    	try {
    		jsonObject = new JSONObject(text);
    		json=jsonObject.toString(4);
		}

		catch ( JSONException e ) {
			e.printStackTrace();
			
		}
    	}
    	else {
    		h.sendResponseHeaders(500, ie.length());
        	os.write(ie.getBytes());
    	}
    	
    	response = json;
        h.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = h.getResponseBody();
		os.write(response.getBytes());
        os.flush();
        os.close();
	}
	}
	}


