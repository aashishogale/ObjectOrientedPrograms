package com.bridgelabz.Programs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.Utility.Util;

public class StockAccount {
	Scanner sc=new Scanner(System.in);
	String userfile= "/home/bridgeit/Desktop/useraccount";
	String file=new String();
	 StockAccount(String filename) {
		 JSONObject stock=new JSONObject();
	      this.file=filename;
		    
	 	  JSONArray array=new JSONArray(); 

	     getInput(array);
	    stock.put("stockaccount", array);

	    Util.appendFile(stock.toJSONString(),filename);
		 JSONObject stock1=new JSONObject();
	      
		    
	 	  JSONArray array1=new JSONArray(); 

	     
	    stock1.put("useraccount", array1);

	    Util.appendFile(stock.toJSONString(),userfile);
	    
	 }
	 public void buy(int amount,String symbol) throws IOException, IOException {
		 System.out.println("enter user name");
		 String name=sc.nextLine();
		 long totalprice=0;
			JSONParser parser = new JSONParser();
			long total=0;
		    try {
		    
		    	
		    	/*String jsonstring="";
		    	for(String str:farray){
		    		jsonstring=jsonstring+str;
		    	}*/
		    	JSONObject jobj = (JSONObject)parser.parse(new FileReader(file));
		    	
		    	JSONArray jarray=(JSONArray) jobj.get("stockaccount");
		    	 
		        
		        for(Object obj1: jarray) {
		        	JSONObject jsonObject = (JSONObject) obj1;
		        	if( jsonObject.get("symbol").equals(symbol)) {
		        	
		        		long number=(long) jsonObject.get("number");
		        		totalprice=number*(long)jsonObject.get("price");
		        		if(number>amount){
		        			int newnumber=(int) (number-amount);
		        			jsonObject.replace("number", newnumber);
		        			
		        		}
		        	}
		        }
		        	
		            	/*String uarray[]=Util.readFile(userfile);
				    	
				    	String ujsonstring="";
				    	for(String str:uarray){
				    		jsonstring=jsonstring+str;
				    	}*/
				    	JSONObject ujobj = (JSONObject)parser.parse(new FileReader(userfile));
				    	
				    	JSONArray ujarray=(JSONArray) ujobj.get("useraccount");
				    	
				        for(Object uobj: jarray) {
				        	JSONObject jsonObject = (JSONObject) uobj;
				        	if(jsonObject.get("name").equals(name)) {
				        		jsonObject.replace("price", (long)jsonObject.get("price")-totalprice);
				        	    jsonObject.replace("balance", (long)jsonObject.get("balance")-amount);
				        	
				        	}
				        	
				        	
				        }
				        
		        		
		        
		        
		        
		        
		        
		    }
		        catch (ParseException ie1) {
		            ie1.printStackTrace();
		        }

		        
		        	
		  
		   
	 }
	 public void sell(int amount,String symbol) {
		 System.out.println("enter user name");
		 String name=sc.nextLine();
		 long totalprice=0;
			JSONParser parser = new JSONParser();
			long total=0;
		    try {
		    	String farray[]=Util.readFile(file);
		    	
		    	String jsonstring="";
		    	for(String str:farray){
		    		jsonstring=jsonstring+str;
		    	}
		    	JSONObject jobj = (JSONObject)parser.parse(jsonstring);
		    	
		    	JSONArray jarray=(JSONArray) jobj.get("stockaccount");
		    	 System.out.println("size"+jarray.size());
		        
		        for(Object obj1: jarray) {
		        	JSONObject jsonObject = (JSONObject) obj1;
		        	if( jsonObject.get("symbol").equals(symbol)) {
		        		long number=(long) jsonObject.get("number");
		        		totalprice=number*(long)jsonObject.get("price");
		        	jsonObject.replace("number",(int)jsonObject.get("number")+amount);
		        	
		    
		        			
		        		}
		        	}
		        
		        	
		        String uarray[]=Util.readFile(userfile);
		    	
		    	String ujsonstring="";
		    	for(String str:uarray){
		    		jsonstring=jsonstring+str;
		    	}
		    	JSONObject ujobj = (JSONObject)parser.parse(jsonstring);
		    	
		    	JSONArray ujarray=(JSONArray) ujobj.get("useraccount");
		    	
		        for(Object uobj: jarray) {
		        	JSONObject jsonObject = (JSONObject) uobj;
		        	if(jsonObject.get("name").equals(name)) {
		        		jsonObject.replace("price", (long)jsonObject.get("balance")+totalprice);
		        	    jsonObject.replace("balance", (long)jsonObject.get("number")+amount);
		        	
		        	}
		        	
		        	
		        }
				        
		        		
	 }
		        
		        
		        
		        
		    
		        catch (ParseException ie1) {
		            ie1.printStackTrace();
		        }

		        
		        	
		  
		   
	 }
	 
		public void getInput(JSONArray jsonarray) {
System.out.println("enter the no of objects to add");
int number=sc.nextInt();
		
			 	  JSONObject temp=new JSONObject();
			 	  for(int i=0;i<number;i++) {
		    	  System.out.println("enter stock symbol");
		     
		    temp.put("name",sc.next());
		      System.out.println("enter number of stock");
		      temp.put("number",sc.nextInt());
		      System.out.println("enter price");
		     temp.put("price", sc.nextInt());
		     jsonarray.add(temp);
			 	  }
		      
		}
		public void getuserInput(JSONArray jsonarray) {
			System.out.println("enter the no of objects to add");
			int number=sc.nextInt();
				for(int i=0;i<number;i++) {
			 	  JSONObject temp=new JSONObject();
		    	  System.out.println("enter user name");
		     
		    temp.put("name",sc.next());
		      System.out.println("enter number of stock");
		      temp.put("number",sc.nextInt());
		      System.out.println("enter balance");
		     temp.put("balance", sc.nextInt());
		     jsonarray.add(temp);
				}
		      
		}
	 
}
