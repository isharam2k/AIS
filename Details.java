package com.pelatro.asi;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Details extends ChannelName {
	JSONObject obj=null;
	JSONArray arr =null;
	String json="";
	String getDetails(HashMap<String, String> queryHm,String text) {
		try {
			obj=new JSONObject(text);
			arr = obj.getJSONObject("mVivaOffers").getJSONArray("offers");
			if(queryHm.get("detail")==null) {
				System.out.println("Offers "+obj.getJSONObject("mVivaOffers").getJSONArray("offers").toString(4));
				System.out.println("Reward History " + obj.getJSONObject("mVivaOffers").getJSONArray("rewardsHistory").toString(4));
				json+=obj.getJSONObject("mVivaOffers").getJSONArray("offers").toString(4)+obj.getJSONObject("mVivaOffers").getJSONArray("rewardsHistory").toString(4);
			}else {
				int detail = Integer.parseInt(queryHm.get("detail").replace("\"", ""));
				if(detail==1) {
					//System.out.println("Offers "+obj.getJSONObject("mVivaOffers").getJSONArray("offers").toString(4));
					json+=obj.toString(4);
				}else
				{
					System.out.println("Offers "+obj.getJSONObject("mVivaOffers").getJSONArray("offers").toString(4));
					System.out.println("Reward History " + obj.getJSONObject("mVivaOffers").getJSONArray("rewardsHistory").toString(4));
					json+=obj.getJSONObject("mVivaOffers").getJSONArray("offers").toString(4)+obj.getJSONObject("mVivaOffers").getJSONArray("rewardsHistory").toString(4)+"},";
				}
			}
			
			//JSONObject mVivaOffers =obj.getJSONObject("mVivaOffers");
			//System.out.println(channel_name +" "+ arr.getJSONObject(0).getJSONObject("channels").getJSONObject(channel_name).toString(4));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
