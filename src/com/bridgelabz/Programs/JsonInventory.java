package com.bridgelabz.Programs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.Utility.Util;

public class JsonInventory {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		 // File file=new File("/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs");
	      JSONObject inventory=new JSONObject();
	      Scanner sc=new Scanner(System.in);
	      long total=0;
	 	  JSONArray array=new JSONArray(); 
	      for(int i=0;i<3;i++) {
		 	  JSONObject temp=new JSONObject();
	    	  System.out.println("enter name");
	      String name=sc.next();
	    temp.put("name",name);
	      System.out.println("enter weight");
	      temp.put("weight",sc.nextInt());
	      System.out.println("enter price");
	     temp.put("price", sc.nextInt());
	     array.add(temp);
	      }
	     
	      inventory.put("inventory", array);
	      sc.close();
	    Util.appendFile(inventory.toJSONString(), "/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/inventory.json");
	

    JSONParser parser = new JSONParser();

    try {
    	String farray[]=Util.readFile("/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/inventory.json");
    	
    	String jsonstring="";
    	for(String str:farray){
    		jsonstring=jsonstring+str;
    	}
    	JSONObject jobj = (JSONObject)parser.parse(jsonstring);
    	/*for(int i=0;i<farray.length;i++) {
    		jobj= (JSONObject)parser.parse(farray[i]);
    	}*/
    	JSONArray jarray=(JSONArray) jobj.get("inventory");
        
        for(Object obj1: jarray) {
        	JSONObject jsonObject = (JSONObject) obj1;
        	long value=(long) jsonObject.get("price");
        	total=total+value;
        	System.out.println(value);
        }
    }
       

  
    catch (ParseException ie1) {
        ie1.printStackTrace();
    }
    System.out.println("final result"+total);

}
}

