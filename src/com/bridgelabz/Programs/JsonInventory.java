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

public class JsonInventory {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		 // File file=new File("/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs");
	      JSONObject inventory=new JSONObject();
	      Scanner sc=new Scanner(System.in);
	      
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
	        try  {
	        	FileWriter file = new FileWriter("/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/inventory.json");
	            file.write(inventory.toJSONString());
	            file.close();
	      } catch (IOException e) {
	            e.printStackTrace();
	      }
	

    JSONParser parser = new JSONParser();

    try {

    	JSONObject jobj = (JSONObject)parser.parse(new FileReader("/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/inventory.json"));
    	JSONArray jarray=(JSONArray) jobj.get("inventory");
        
        for(Object obj1: jarray) {
        	JSONObject jsonObject = (JSONObject) obj1;
        	long value=(long) jsonObject.get("price");
        	System.out.println(value);
        }
       


    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException ie) {
        ie.printStackTrace();
    } catch (ParseException ie1) {
        ie1.printStackTrace();
    }

}
}

