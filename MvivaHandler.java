package com.pelatro.asi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MvivaHandler implements HttpHandler{
	 JSONObject obj;
	 JSONObject obj2 = new JSONObject();
	 JSONObject mvivaoffers= new JSONObject();
	 JSONArray arr ;
	 JSONArray array;
	 String channel_name;
	 String query;
	 static HashMap<String, String> result = new HashMap<>();
		static HashMap<String, String> hm; 
		static HashMap<String, String> queryHm=new HashMap<>();
		static String json="";
		static String line="";
		static String text="";
		static OutputStream os = null;
		static String res = "Status code : 400  Malformed Request";
		
		static boolean value1 = true;
		static boolean value2 = true;
		static boolean value3 = true;
	 
	 public void getParams() {
		 for(String param: query.split("&")) {
	    		String[] entry = param.split("=");
	    		queryHm.put(entry[0], entry[1]);
	    	}
	 }

	 @Override
    public void handle(HttpExchange t) throws IOException {
    	OwnerInfo oi = new OwnerInfo();
    	ChannelName chn= new ChannelName();
    	os = t.getResponseBody();
    	OfferStatus o = new OfferStatus();
		query=t.getRequestURI().getQuery();
		if(query!=null && !query.isEmpty()) {
    	   getParams();
		}
		else {
			  t.sendResponseHeaders(400, res.length());
		      os.write(res.getBytes());
		
		}
	
		text= oi.getFile(queryHm);
		if(text != null) {
    	int detail = Integer.parseInt(queryHm.get("detail").replace("\"", ""));
		if(detail==1) {
			try {
				obj = new JSONObject(text);
				arr= obj.getJSONObject("mVivaOffers").getJSONArray( "offers" );
				System.out.println( "offers " + arr );
				mvivaoffers.put( "lookbackPeriodInDays", 30 );
				mvivaoffers.put( "offers", arr);
				System.out.println( "mviva "+ mvivaoffers.toString()  );
				obj2.put( "mVivaOffers",  mvivaoffers);
				System.out.println( "obj2 "+obj2.toString() );
				
				array=obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" );
				System.out.println( "Array "+array );
				channel_name=queryHm.get("channel_name");
				//System.out.println( channel_name );
				//System.out.println( "sa "+obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( 0 ));
				//obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( 0 );
				obj2=chn.channels( queryHm,  obj2);
				obj2=o.getStatus( queryHm, obj2 );
				System.out.println( "aaa "+obj2.toString() );
				json=obj2.toString(4);
//				System.out.println( array.length() );
				//int length = array.length();
				}
			catch ( Exception e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(detail==3) {
			try {
				obj = new JSONObject(text);
				arr= obj.getJSONObject("mVivaOffers").getJSONArray( "rewardsHistory" );
				mvivaoffers.put( "lookbackPeriodInDays", 30 );
				mvivaoffers.put( "rewardsHistory", arr);
				obj2.put( "mVivaOffers",  mvivaoffers);
				json=obj2.toString(4);
				System.out.println( "obj21 "+obj2.toString() );
			}
			catch ( JSONException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			try {
				obj=new JSONObject(text);
				obj=chn.channels( queryHm,  obj);
				obj=o.getStatus( queryHm, obj );
				System.out.println( "aaa "+obj.toString() );
				json=obj.toString(4);
			}
			catch ( JSONException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		else {
			System.out.println(text);
			String notFound = "Status code : 404  Owner not present";
    		t.sendResponseHeaders(404, notFound.length());
			os.write(notFound.getBytes());
		}

    	String response = json;
        t.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
}
    }



