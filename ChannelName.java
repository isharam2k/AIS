package com.pelatro.asi;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelName {
	//JSONObject obj=null;
	JSONArray arr =null;
	String json="";
	String channelComb[] = new String[9];
	String allChannel[]= {"SMS","MMS","IVR","PNS","myAIS","USSDInteractive","QUEUESYSTEM","IVR","Superscreen"};
	JSONObject channels(HashMap<String, String> queryHm,JSONObject obj2){
		try {
			System.out.println( "zzz "+obj2.toString() );
			arr = obj2.getJSONObject("mVivaOffers").getJSONArray("offers");
			int length=arr.length();
			
		if(queryHm.get("channel_name")==null) {
			if(length>0) {
			for(int i=0;i<length;i++) {
			for(int j=0;j<allChannel.length;j++) {
				try {
				if(arr.getJSONObject(i).getJSONObject("channels").getJSONObject(allChannel[j])==null) {
					System.out.println( "if "+i );
				}else {
					System.out.println( "else "+i +"channel "+allChannel[j]);
				}
		}catch (JSONException e) {
			obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
			System.out.println( "sasa "+obj2.toString() );
			length--;i--;
			System.out.println( "catch "+i );
			System.out.println( length );
			
		}
			}
			}
			}
			}else if(queryHm.get("channel_name").contains(",")) {
				channelComb=queryHm.get("channel_name").split(",");
//				length=arr.length();
				if(length>0) {
				for(int i=0;i<length;i++) {
				for(int j=0;j<channelComb.length;j++) {
					try {
					if(arr.getJSONObject(i).getJSONObject("channels").getJSONObject(channelComb[j])==null) {
						System.out.println( "if "+i );
					}else {
						System.out.println( "else "+i +"channel "+channelComb[j]);
					}
			}catch (JSONException e) {
				obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
				System.out.println( "sasa "+obj2.toString() );
				length--;i--;
				System.out.println( "catch "+i );
				System.out.println( length );
				
			}
				}
				}
				}
			}else {
				String channel_name=queryHm.get("channel_name").replace("\"", "");
				if(length>0) {
					for(int i=0;i<length;i++) {
						try {
						if(arr.getJSONObject(i).getJSONObject("channels").getJSONObject(channel_name)==null){
							//System.out.println("Array "+array.getJSONObject( i ));
							System.out.println( i );
							obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
							System.out.println( "sasac "+obj2.toString() );
						}else {
							System.out.println( i );
						}
						
					}catch (Exception e) {
						// TODO: handle exception
						obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
						System.out.println( "sasa "+obj2.toString() );
						length--;i--;
						System.out.println( "catch "+i );
						System.out.println( length );
					}
					}
				}
			}
		}catch (Exception e) {
			
		}
//		}else if(queryHm.get("channel_name").contains(",")) {
//			json+=channelCombination(queryHm,text);
//		}else
//		{
//			json+=getOneChannel(queryHm,text);
//		}
//		
		return obj2;
	}
	
}
