package com.pelatro.asi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OwnerInfo {

	HashMap<String, String> hm;

	//HashMap<String, String> queryHm;
	String getFile( HashMap<String, String> queryHm ) {
		FileReader msisdnFile;
		BufferedReader br;
		String line[] = new String[2];
		String text;
		String json = "";
		hm = new HashMap<>();
		try {
			msisdnFile = new FileReader( "/home/anishar/Documents/ASI/msisdn.txt" );
			br = new BufferedReader( msisdnFile );
			text = br.readLine();
			while ( text != null && text.length() != 0 ) {
				line = text.split( ":" );
				hm.put( line[0], line[1] );
				text = br.readLine();
			}

		}
		catch (  NullPointerException | IOException e ) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String msisdn = queryHm.get( "owner" ).replace( "\"", "" );
		if(hm.containsKey(msisdn)!=false) {
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
		}
		else {
			System.out.println("not present");
			return null;
		}
		return json;
	}

	
}
