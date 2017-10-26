package com.bridgelabz.programs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.bridgelabz.utility.Util;

/***************************************************************************
 * Purpose : To create class for Stock Account
 *
 * @author Aashish
 * @version 1.0
 * @since 13-10-2017
 ****************************************************************************/
public class StockAccount {
	public Scanner scanner = new Scanner(System.in);
	public String userfile = "/home/bridgeit/Desktop/useraccount.json";
	public String file;
	public JSONObject stock = new JSONObject();
	public JSONObject stock1 = new JSONObject();
	public JSONArray array = new JSONArray();
	public JSONArray array1 = new JSONArray();
	public LinkedList<String> list = new LinkedList<String>();
	public Stack<String> stack = new Stack<String>();
	public Queue<String> queue = new Queue<String>();
	public StockObject stockobj=new StockObject();
	public User user=new User();

	StockAccount(String filename) {

		this.file = filename;

	}

	/**
	 * This method will buy the given stock
	 * 
	 * @param amount
	 *            symbol,name
	 * @return
	 * 
	 */

	public void buy(int amount, String symbol, String name) {

		long totalprice = 0;

		JSONArray jarray = Util.getjsonArray(file, "stockaccount");

		for (Object obj1 : jarray) {
			JSONObject jsonObject = (JSONObject) obj1;
			if (jsonObject.get("symbol").equals(symbol)) {

				long number = (long) jsonObject.get("number");
				totalprice = amount * (long) jsonObject.get("price");
				System.out.println(totalprice);
				if (number > amount) {
					int newnumber = (int) (number - amount);
					jsonObject.replace("number", newnumber);

				}

			}
		}

		stock.put("stockaccount", jarray);
		Util.appendFile(stock.toJSONString(), file);

		JSONArray ujarray = Util.getjsonArray(userfile, "useraccount");

		for (Object uobj : ujarray) {
			JSONObject jsonObject = (JSONObject) uobj;
			if (jsonObject.get("name").equals(name)) {
				jsonObject.replace("balance", (long) jsonObject.get("balance") - totalprice);
				jsonObject.replace("number", (long) jsonObject.get("number") + amount);

			}

		}
		stock1.put("useraccount", ujarray);
		Util.appendFile(stock1.toJSONString(), userfile);
		stack.push(symbol);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		queue.enqueue(String.valueOf(dateFormat.format(date)));

	}

	/**
	 * This method will sell the given stock
	 * 
	 * @param amount
	 *            symbol,name
	 * @return
	 * 
	 */
	public void sell(int amount, String name, String symbol) {

		long totalprice = 0;
		new JSONParser();
		JSONArray jarray = Util.getjsonArray(file, "stockaccount");

		for (Object obj1 : jarray) {
			JSONObject jsonObject = (JSONObject) obj1;
			if (jsonObject.get("symbol").equals(symbol)) {
				jsonObject.get("number");
				totalprice = amount * (long) jsonObject.get("price");
				jsonObject.replace("number", (long) jsonObject.get("number") + amount);

			}
		}
		stock.put("stockaccount", jarray);
		Util.appendFile(stock.toJSONString(), file);

		JSONArray ujarray = Util.getjsonArray(userfile, "useraccount");

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
	
	public void getObject() {
		System.out.println("enter stock name");
		stockobj.setName(scanner.next());
		System.out.println("enter number of stock");
		stockobj.setNumber(scanner.nextInt());
		System.out.println("enter price");
		stockobj.setPrice(scanner.nextInt());

	}

	/**
	 * This method will get the stock information
	 * 
	 * @param stArray
	 * @return
	 * 
	 */

	public JSONArray getstock(JSONArray stArray) {
		System.out.println("enter the no of objects to add");
		int number = scanner.nextInt();
		int amount = 0;
		String symbol = "";

		for (int i = 0; i < number; i++) {
			JSONObject temp = new JSONObject();
			getObject();
		
			temp.put("symbol", stockobj.getName());
			
			
			temp.put("number", stockobj.getnumber());
			
			temp.put("price", stockobj.getPrice());
			stArray.add(temp);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			list.addShare(String.valueOf(amount), String.valueOf(dateFormat.format(date)), symbol);

		}
		return stArray;

	}

	/**
	 * This method will get the input in the stock account
	 * 
	 * @param
	 * @return
	 * 
	 */
	public void getInput() {

		if (Util.checkEmpty(file)) {
			array = getstock(array);
		} else {
			JSONArray parseArray = Util.getjsonArray(file, "stockaccount");
		
			array = getstock(parseArray);
		}

		stock.put("stockaccount", array);

		Util.appendFile(stock.toJSONString(), file);

	}

	/**
	 * This method will get the user information
	 * 
	 * @param userArray
	 * @return
	 * 
	 */
	public JSONArray getUser(JSONArray usearray) {
		System.out.println("enter the number of  names  to add");
		int number = scanner.nextInt();
		for (int i = 0; i < number; i++) {
			JSONObject temp = new JSONObject();
			getUser();

			temp.put("name", user.getName());
			
			temp.put("number", user.getNumber());
			
			temp.put("balance", user.getBalance());
			usearray.add(temp);
		}
		return usearray;

	}
	public void getUser() {
		scanner.nextLine();
		System.out.println("enter user name");
		user.setName(scanner.nextLine());
		System.out.println("enter number of stock");
		user.setNumber(scanner.nextInt());
		System.out.println("enter balance");
		user.setBalance(scanner.nextInt());
		
		
	}

	/**
	 * This method will get the input in the user account
	 * 
	 * @param
	 * @return
	 * 
	 */

	public void getUserInput() {
		if (Util.checkEmpty(userfile)) {
			array1 = getUser(array1);
		} else {
			JSONArray parseArray = Util.getjsonArray(userfile, "useraccount");
			array1 = getUser(parseArray);
		}
		stock1.put("useraccount", array1);

		Util.appendFile(stock1.toJSONString(), userfile);

	}

	/**
	 * This method will remove stock
	 * 
	 * @param symbol
	 * @return
	 * 
	 */

	public void removeStock(String symbol) {

		new JSONParser();
		JSONArray jarray = Util.getjsonArray(file, "stockaccount");
		for (Object obj1 : jarray) {

			JSONObject jsonObject = (JSONObject) obj1;
			if (jsonObject.get("symbol").equals(symbol)) {
				jarray.remove(obj1);
				break;
			}
		}
		stock.put("stockaccount", jarray);
		Util.appendFile(stock.toJSONString(), file);
		list.deleteElement(symbol);

	}

	/**
	 * This method will remove stock
	 * 
	 * @param symbol
	 * @return
	 * 
	 */
	public long valueOf() {
		new JSONParser();
		long total = 0;

		JSONArray jarray = Util.getjsonArray(file, "stockaccount");

		for (Object obj1 : jarray) {
			JSONObject jsonObject = (JSONObject) obj1;
			long value = (long) jsonObject.get("number") * (long) jsonObject.get("price");
			total = total + value;
			System.out.println(value);
		}

		return total;
	}

	public void printReport() {
		

		Util.viewList(file);
	}
	public void viewUsers()
	{
		Util.viewList(userfile);
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
