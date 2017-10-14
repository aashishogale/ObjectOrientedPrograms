package com.bridgelabz.Programs;

import java.util.Scanner;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.Utility.Util;

public class Stock {
	String file="/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/stock.json";

	public void initializeJSON() {
		  JSONObject stock=new JSONObject();
	      
	    
	 	  JSONArray array=new JSONArray(); 

	     
	    stock.put("stock", array);

	    Util.appendFile(stock.toJSONString(),file);
	}
	public  void addStock(){
		 JSONParser parser = new JSONParser();
	
		 try {
		    	String farray[]=Util.readFile(file);
		    	
		    	String jsonstring="";
		    	for(String str:farray){
		    		jsonstring=jsonstring+str;
		    	}
		    	JSONObject jobj = (JSONObject)parser.parse(jsonstring);
		    	
		    	JSONArray jarray=(JSONArray) jobj.get("stock");
		    	getInput(jarray);
		    	jobj.put("stock", jarray);
		    	Util.appendFile(jobj.toJSONString(), file);
		    	
		 }
		    	 catch (ParseException ie1) {
		    	        ie1.printStackTrace();
		    	    }
		
		
		
	}
	public void getInput(JSONArray jsonarray) {
		 Scanner sc=new Scanner(System.in);
	
		 	  JSONObject temp=new JSONObject();
	    	  System.out.println("enter stock name");
	     
	    temp.put("name",sc.next());
	      System.out.println("enter number of stock");
	      temp.put("number",sc.nextInt());
	      System.out.println("enter price");
	     temp.put("price", sc.nextInt());
	     jsonarray.add(temp);
	      
	}
public long getSingleValue(String stockname) {
	
	JSONParser parser = new JSONParser();
	long total=0;
    try {
    	String farray[]=Util.readFile(file);
    	
    	String jsonstring="";
    	for(String str:farray){
    		jsonstring=jsonstring+str;
    	}
    	JSONObject jobj = (JSONObject)parser.parse(jsonstring);
    	
    	JSONArray jarray=(JSONArray) jobj.get("stock");
    	 System.out.println("size"+jarray.size());
        
        for(Object obj1: jarray) {
        	JSONObject jsonObject = (JSONObject) obj1;
        	if( jsonObject.get("name").equals(stockname)) {
        		long price=(long) jsonObject.get("price");
        		long number=(long) jsonObject.get("number");
        	
        		total=price*number;
        		
        	}
       
        
        	
  
        }
       
    }
       

  
    catch (ParseException ie1) {
        ie1.printStackTrace();
    }

	
	return total;
	
}
public int returnSize() {
	JSONParser parser = new JSONParser();
	long total=0;
    try {
    	String farray[]=Util.readFile(file);
    	
    	String jsonstring="";
    	for(String str:farray){
    		jsonstring=jsonstring+str;
    	}
    	JSONObject jobj = (JSONObject)parser.parse(jsonstring);
    	
    	JSONArray jarray=(JSONArray) jobj.get("stock");
        return(jarray.size());
    }
    	 catch (ParseException ie1) {
    	        ie1.printStackTrace();
    	    }
	return 0;
   
}
public String[] getKeys() {
	String []array=new String[returnSize()+1];
	JSONParser parser = new JSONParser();
	long total=0;
    try {
    	String farray[]=Util.readFile(file);
    	
    	String jsonstring="";
    	for(String str:farray){
    		jsonstring=jsonstring+str;
    	}
    	JSONObject jobj = (JSONObject)parser.parse(jsonstring);
     	JSONArray jarray=(JSONArray) jobj.get("stock");
     	for(Object obj1: jarray) {
        	JSONObject jsonObject = (JSONObject) obj1;
        	 Set<String> set =jsonObject.keySet();
            array= set.toArray(array);
        		
        	}
   
    	
       
   	return array;
        
    }
    	 catch (ParseException ie1) {
    	        ie1.printStackTrace();
    	    }
	
	return array;
}
}
