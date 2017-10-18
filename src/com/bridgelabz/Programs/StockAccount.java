package com.bridgelabz.Programs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.Utility.Util;
/***************************************************************************
* Purpose : To create class for Stock Account
*
* @author   Aashish
* @version  1.0
* @since    13-10-2017
****************************************************************************/
public class StockAccount {
	Scanner sc = new Scanner(System.in);
	String userfile = "/home/bridgeit/Desktop/useraccount.json";
	String file = new String();
	JSONObject stock = new JSONObject();
	JSONObject stock1 = new JSONObject();
	JSONArray array = new JSONArray();
	JSONArray array1 = new JSONArray();
	LinkedList<String> list=new LinkedList<String>();
	Stack<String> stack=new Stack<String>();
	Queue<String> queue = new Queue<String>();
	StockAccount(String filename) {
		
		this.file = filename;

	

		getInput();
	
	

		
		getUserInput();
	

	}

	public void buy(int amount, String symbol,String name)  {
		
	
		long totalprice = 0;
		JSONParser parser = new JSONParser();
		long total = 0;
		try {

			/*
			 * String jsonstring=""; for(String str:farray){
			 * jsonstring=jsonstring+str; }
			 */
			JSONObject jobj = (JSONObject) parser.parse(new FileReader(file));

			JSONArray jarray = (JSONArray) jobj.get("stockaccount");

			for (Object obj1 : jarray) {
				JSONObject jsonObject = (JSONObject) obj1;
				if (jsonObject.get("symbol").equals(symbol)) {

					long number = (long) jsonObject.get("number");
					totalprice = amount* (long) jsonObject.get("price");
					System.out.println(totalprice);
					if (number > amount) {
						int newnumber = (int) (number - amount);
						jsonObject.replace("number", newnumber);

					}
					
				}
			}
			
			stock.put("stockaccount", jarray);
			Util.appendFile(stock.toJSONString(), file);
			/*
			 * String uarray[]=Util.readFile(userfile);
			 * 
			 * String ujsonstring=""; for(String str:uarray){
			 * jsonstring=jsonstring+str; }
			 */
			JSONObject ujobj = (JSONObject) parser.parse(new FileReader(userfile));

			JSONArray ujarray = (JSONArray) ujobj.get("useraccount");

			for (Object uobj : ujarray) {
				JSONObject jsonObject = (JSONObject) uobj;
				if (jsonObject.get("name").equals(name)) {
					jsonObject.replace("balance", (long) jsonObject.get("balance") - totalprice);
					jsonObject.replace("number", (long) jsonObject.get("number") +amount);

				}

			}
			stock1.put("useraccount", ujarray);
			Util.appendFile(stock1.toJSONString(), userfile);
			stack.push(symbol);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			queue.enqueue(String.valueOf(dateFormat.format(date)));

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
	}

	public void sell(int amount, String name,String symbol) {
		
		long totalprice = 0;
		JSONParser parser = new JSONParser();
		long total = 0;
		try {
			
			JSONObject jobj = (JSONObject) parser.parse(new FileReader(file));

			JSONArray jarray = (JSONArray) jobj.get("stockaccount");


			for (Object obj1 : jarray) {
				JSONObject jsonObject = (JSONObject) obj1;
				if (jsonObject.get("symbol").equals(symbol)) {
					long number = (long) jsonObject.get("number");
					totalprice = amount * (long) jsonObject.get("price");
					jsonObject.replace("number", (long) jsonObject.get("number") + amount);

				}
			}
			stock.put("stockaccount", jarray);
			Util.appendFile(stock.toJSONString(), file);

			
			JSONObject ujobj = (JSONObject) parser.parse(new FileReader (userfile));

			JSONArray ujarray = (JSONArray) ujobj.get("useraccount");

			for (Object uobj : ujarray) {
				JSONObject jsonObject = (JSONObject) uobj;
				if (jsonObject.get("name").equals(name)) {
					jsonObject.replace("balance", (long) jsonObject.get("balance") + totalprice);
					jsonObject.replace("number", (long) jsonObject.get("number") - amount);

				}

			}
			stack.push(symbol);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			queue.enqueue(String.valueOf(dateFormat.format(date)));
			stock1.put("useraccount", ujarray);
			Util.appendFile(stock1.toJSONString(), userfile);
			

		}

		catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getInput() {
		System.out.println("enter the no of objects to add");
		int number = sc.nextInt();
       int amount=0;
       String symbol="";
       
		
		for (int i = 0; i < number; i++) {
			JSONObject temp = new JSONObject();
			System.out.println("enter stock symbol");
        symbol=sc.next();
			temp.put("symbol", symbol);
			System.out.println("enter number of stock");
			amount=sc.nextInt();
			temp.put("number", amount);
			System.out.println("enter price");
			temp.put("price", sc.nextInt());
			array.add(temp);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
					
			list.addShare(String.valueOf(amount),String.valueOf(dateFormat.format(date)), symbol);
		
		}
		stock.put("stockaccount", array);

		Util.appendFile(stock.toJSONString(), file);
		
}
	

	public void getUserInput() {
		System.out.println("enter the number of  names  to add");
		int number = sc.nextInt();
		for (int i = 0; i < number; i++) {
			JSONObject temp = new JSONObject();
			System.out.println("enter user name");

			temp.put("name", sc.next());
			System.out.println("enter number of stock");
			temp.put("number", sc.nextInt());
			System.out.println("enter balance");
			temp.put("balance", sc.nextInt());
		array1.add(temp);
		}
		stock1.put("useraccount", array1);

		Util.appendFile(stock1.toJSONString(), userfile);
		

	}
	public void removeStock(String symbol) {
		
		JSONParser parser = new JSONParser();
		int index=0 ;
		try {
			
			JSONObject jobj = (JSONObject) parser.parse(new FileReader(file));

			JSONArray jarray = (JSONArray) jobj.get("stockaccount");
			for (Object obj1 : jarray) {
				
				JSONObject jsonObject = (JSONObject) obj1;
				if (jsonObject.get("symbol").equals(symbol)) {
			jarray.remove(obj1);
			break;
				}
				index++;
			}
			stock.put("stockaccount", jarray);
			Util.appendFile(stock.toJSONString(), file);
			list.deleteElement(symbol);
			
		}
			catch (ParseException ie1) {
				ie1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
	public long valueOf() {
		  JSONParser parser = new JSONParser();
		long total=0;

		    try {

		    	JSONObject jobj = (JSONObject)parser.parse(new FileReader(file));
		    	/*for(int i=0;i<farray.length;i++) {
		    		jobj= (JSONObject)parser.parse(farray[i]);
		    	}*/
		    	JSONArray jarray=(JSONArray) jobj.get("stockaccount");
		        
		        for(Object obj1: jarray) {
		        	JSONObject jsonObject = (JSONObject) obj1;
		       long value=(long) jsonObject.get("number")*(long)jsonObject.get("price");
		        	total=total+value;
		        	System.out.println(value);
		        }
		    }
		       


		    catch (ParseException ie1) {
		        ie1.printStackTrace();
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return total;
	}
	public void printReport() {
		  JSONParser parser = new JSONParser();
			
			    try {

			    	JSONObject jobj = (JSONObject)parser.parse(new FileReader(file));
			    	System.out.println(jobj.toJSONString());
			        }
			    
			       


			    catch (ParseException ie1) {
			        ie1.printStackTrace();
			    } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
		
		public void viewlist() {
			list.viewShare();
		}
		public void viewStack() {
			stack.view();
		}
		public void viewQueue() {
			queue.display();
		}
	}
	


