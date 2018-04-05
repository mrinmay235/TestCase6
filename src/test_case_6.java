import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.*;
import java.net.*;

import java.io.FileReader;
import java.lang.ProcessBuilder.Redirect.Type;

import org.json.simple.JSONObject;

public class test_case_6 {

	public static void main(String[] args) throws IOException {
		String url1 = "http://jobs.mapsted.com/api/Values/GetBuildingData";
		String url2 = "http://jobs.mapsted.com/api/Values/GetAnalyticsData";
			URL urla = new URL(url1);
			URL urlb = new URL(url2);
		 HttpURLConnection conn1 = (HttpURLConnection)urla.openConnection(); 
		 HttpURLConnection conn2 = (HttpURLConnection)urlb.openConnection(); 
		 conn1.connect();
		 conn2.connect();
		 int responsecode1 = conn1.getResponseCode();
		 int responsecode2 = conn2.getResponseCode(); 
		 List<Integer> ontario = new LinkedList<Integer>(Collections.<Integer>emptyList());
		 List<Integer> us = new LinkedList<Integer>(Collections.<Integer>emptyList());
		 if(responsecode1 != 200)
			 throw new RuntimeException("HttpResponseCode1:"  +responsecode1);
			 else
			 {
				 Scanner sc1 = new Scanner(urla.openStream());
				 String inline="";
				 while(sc1.hasNext())
					 inline+=sc1.nextLine();
				 String stringToParse = inline;		 
				 Gson googleJson = new Gson();
				 java.lang.reflect.Type collectionType = new TypeToken<List<building>>(){}.getType();
				 List<building> lcs = new LinkedList<building>((List<building>) new Gson().fromJson( inline , collectionType));
				 for (building element : lcs) {
					   if(element.getstate().equals("Ontario"))
					   {
						   int i = element.getbid();
						   ontario.add(Integer.valueOf(i));	//adding building id of each building in Ontario to "ontario"
					   }
					}
				 
				 for (building element : lcs) {
					   if(element.getcountry().equals("United States"))
					   {
						   int i = element.getbid();
						   us.add(Integer.valueOf(i));		//adding building id of each building in United States to "us"
					   }
					}
				 sc1.close();
			 }
		 if(responsecode2 != 200)
			 throw new RuntimeException("HttpResponseCode2:"  +responsecode2);
			 else
			 {
				 Scanner sc2 = new Scanner(urlb.openStream());
				 String inline="";
				 while(sc2.hasNext())
					 inline+=sc2.nextLine();
				 String stringToParse = inline;		 
				 Gson googleJson = new Gson();
				 java.lang.reflect.Type collectionType = new TypeToken<List<analytics>>(){}.getType();
				 List<analytics> lcs = new LinkedList<analytics>((List<analytics>) new Gson().fromJson( inline , collectionType));
				 
				 double count1=0,count2=0,count3=0, count4=0, count5=0, max=0;
				 int mbid=0;
				 HashMap<Integer, Double> hm = new HashMap<Integer, Double>();
				 for (analytics element : lcs) {		
					 session_infos sis = element.getsis();
					   List<session_info> lt = new LinkedList<session_info>();
					   lt = sis.getsi();
					   for (session_info siel : lt)	
					   {
						   if(!hm.containsKey(siel.getbid()))
							   hm.put(siel.getbid(), (double) 0);
						   List<purchase> p = new LinkedList<purchase>(siel.getPurchase());				   
						   for (purchase pel : p) {
							   Map<String, Double> map = new HashMap<String, Double>(); 
							   map = pel.getPurchase();
							   for (Entry<String, Double> entry : map.entrySet()) 
							    {
								   Double x = 0.0;
								   if(element.getManf().equals("Samsung"))
								   {
									if(entry.getKey().equals("cost"))
								    		count1 += entry.getValue();             // Storing cost of each Samsung device to count1
								   }
								    if(entry.getKey().equals("cost")) {
								    		hm.put(siel.getbid(),x+entry.getValue()); // Storing cost for each building to hm  
								    		if(ontario.contains(siel.getbid()))
								    			count4 += entry.getValue();		// Storing purchase cost for each ontario building
								    		if(us.contains(siel.getbid()))
								    			count5 += entry.getValue();		// Storing purchase cost for each US building
								    }
								    if(entry.getKey().equals("item_id") && entry.getValue()==47)
							    			count2 ++;								//Total number of times item_id=47 was purchased
								    if(entry.getKey().equals("item_category_id") && entry.getValue()==7)
						    				count3 += map.get("cost");				//Total cost for item_category_id=7
								}
						   }
					   }
					}
				 for (Integer key : hm.keySet()) 
				 {
					 if(max<hm.get(key))				// To check hm for the building id with highest value of purchase
					 {
						 mbid=key;
						 max=hm.get(key);
					 }
				 }
				 System.out.println("Total cost of Samsung devices purchased is "+ count1);
				 System.out.println("Total times item_id=47 was purchased is "+ count2);
				 System.out.println("Total purchase cost for item_category_id=7 "+ count3);
				 System.out.println("Total cost of purchases for buildings in Ontario is "+ count4);
				 System.out.println("Total cost of purchases for buildings in US is "+ count5);
				 System.out.println("Building with id "+ mbid + " has the most purchases");
				 System.out.println("Building with id "+ mbid + " has the most purchases");
				 
				 sc2.close();
			 }
	}
}