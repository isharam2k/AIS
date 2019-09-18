package com.pelatro.asi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class NbOffers implements HttpHandler  {

	HashMap<String, String> hm;
	
	private static final String KEY="[U3VuaWw6U0s=]";
	
	HashMap<String, String> queryHm=new HashMap<>();
	
	String json="";
	
	String line="";
	String text="";
	String response="";
	OutputStream os;
	 String query;
	 String res = "Status code : 400  Malformed Request";
	 String notFound = "Status code : 404  Owner not present";
		String ie = "Status code : 500 Internal Error";
		JSONObject o;
		String msisdn;
	 
	 public void getParams() {
		 for(String param: query.split("&")) {
	    		String[] entry = param.split("=");
	    		queryHm.put(entry[0], entry[1]);
	    	}
	 }
	 
	 
	@Override
	public void handle( HttpExchange nb ) throws IOException {
		String k = nb.getRequestHeaders().get( "Basic" ).toString();
//		System.out.println( k.equals(key) );
		if(k.equals(KEY)) {
		NbOffers n= new NbOffers();
		ChannelName chn= new ChannelName();
		JSONObject o = new JSONObject();
		JSONArray arr = null;
		query=nb.getRequestURI().getQuery();
		if(query!=null && !query.isEmpty()) {
	    	   getParams();
			}
			else {
				  nb.sendResponseHeaders(400, res.length());
			      os.write(res.getBytes());
			
			}
		

		if(hm.containsKey(msisdn)!=false) {
			getFile(queryHm);
		}
		else {
			nb.sendResponseHeaders(404, notFound.length());
			os.write(notFound.getBytes());
		}
    
		if(text != null) {
    	text= n.getFile(queryHm);
    	try {
			o = new JSONObject(text);
			o=chn.channels( queryHm, o );
			json=o.toString(4);
			System.out.println(o.toString(4));
		}
		catch ( JSONException e ) {
			e.printStackTrace();
		}}
		else {
			nb.sendResponseHeaders(500, ie.length());
        	os.write(ie.getBytes());
		}
		
		//System.out.println( text.toString() );

    	 response = json;
       nb.sendResponseHeaders(200, response.getBytes().length);
       OutputStream os = nb.getResponseBody();
       os.write(response.getBytes());
       os.flush();
       os.close();
	}
	 
	} 
	
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
		catch ( Exception e ) {
			e.printStackTrace();
			return null;
		}
		 o = new JSONObject();
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
	
}

