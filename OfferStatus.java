package com.pelatro.asi;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OfferStatus {

	JSONArray arr =null;
	String json="";
	
	String offerStatus[]=new String[4];
	JSONObject getStatus(HashMap<String, String> queryHm,JSONObject obj) {
		try {
			arr = obj.getJSONObject("mVivaOffers").getJSONArray("offers");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(queryHm.get("offer_status")==null) {
			try {
				int length=arr.length();
				if(length>0) {
						for(int i=0;i<length;i++) {
							try {
								if(Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=1 && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=0 && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=2 && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=3) {
								//System.out.println("Array "+array.getJSONObject( i ));
								System.out.println( i );
								obj.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
								System.out.println( "sas1 "+obj.toString() );
								length--;i--;
							}else {
								System.out.println( i );
							}
							
						}catch (Exception e) {
							// TODO: handle exception
//							obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
//							System.out.println( "sasa "+obj2.toString() );
//							length--;i--;
//							System.out.println( "catch "+i );
//							System.out.println( length );
						}
						}
				}
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			if(queryHm.get("offer_status").contains(",")) {
				offerStatus=(queryHm.get("offer_status").split(","));
				int offerStatusLength=offerStatus.length;
				int length=arr.length();
				if(length>0) {
				try {
					
					for(int i=0;i<length;i++) {
					//	System.out.println(Integer.parseInt(offerStatus[j]));
						if(offerStatusLength==2) {
						if(Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=Integer.parseInt(offerStatus[0]) && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!= Integer.parseInt(offerStatus[1])) {
							obj.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
							System.out.println( "sasac "+obj.toString() );
							length--;i--;
						}else {
							System.out.println( i );
						}
						}else if(offerStatusLength==3) {
							if(Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=Integer.parseInt(offerStatus[0]) && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!= Integer.parseInt(offerStatus[1]) && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=Integer.parseInt(offerStatus[2])) {
								obj.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
								System.out.println( "sasac "+obj.toString() );
								length--;i--;
							}else {
								System.out.println( i );
							}
						}else {
							if(Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=Integer.parseInt(offerStatus[0]) && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!= Integer.parseInt(offerStatus[1]) && Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=Integer.parseInt(offerStatus[2])&&Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=Integer.parseInt(offerStatus[3])) {
								obj.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
								System.out.println( "sasac "+obj.toString() );
								length--;i--;
							}else {
								System.out.println( i );
							}
						}
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
			else {
				try {
					int length=arr.length();
					if(length>0) {
							for(int i=0;i<length;i++) {
								try {
									if(Integer.parseInt(obj.getJSONObject("mVivaOffers").getJSONArray("offers").getJSONObject(i).get("status").toString())!=Integer.parseInt(queryHm.get("offer_status"))) {
									//System.out.println("Array "+array.getJSONObject( i ));
									System.out.println( i );
									obj.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
									System.out.println( "sas1 "+obj.toString() );
									length--;i--;
								}else {
									System.out.println( i );
								}
								
							}catch (Exception e) {
								// TODO: handle exception
//								obj2.getJSONObject( "mVivaOffers" ).getJSONArray( "offers" ).remove( i );
//								System.out.println( "sasa "+obj2.toString() );
//								length--;i--;
//								System.out.println( "catch "+i );
//								System.out.println( length );
							}
							}
						}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return obj;
	}
}
