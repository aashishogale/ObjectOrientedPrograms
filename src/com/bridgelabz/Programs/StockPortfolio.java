package com.bridgelabz.Programs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.Utility.Util;
/***************************************************************************
* Purpose : To create class for Stock portfolio
*
* @author   Aashish
* @version  1.0
* @since    13-10-2017
****************************************************************************/
public class StockPortfolio {
	public long getTotal() {
		Stock stock=new Stock();


		JSONParser parser = new JSONParser();
		long total=0;
	    try {
	    	String farray[]=Util.readFile("/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/stock.json");
	    	
	    	String jsonstring="";
	    	for(String str:farray){
	    		jsonstring=jsonstring+str;
	    	}
	    	JSONObject jobj = (JSONObject)parser.parse(jsonstring);
	    	
	    	JSONArray jarray=(JSONArray) jobj.get("stock");
	    	 System.out.println("size"+jarray.size());
	        
	        for(Object obj1: jarray) {
	        	JSONObject jsonObject = (JSONObject) obj1;
	        	
	        		long price=(long) jsonObject.get("price");
	        		long number=(long) jsonObject.get("number");
	        	
	        		total=total+price*number;
	        		
	        	}
	       
	        
	        	
	  
	        	return total;
	       
	    }
	       

	  
	    catch (ParseException ie1) {
	        ie1.printStackTrace();
	    }

		return total;
	
		
	}
			
		}
	


