package com.bridgelabz.programs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.utility.Util;

/***************************************************************************
 * Purpose : To create class Stock
 *
 * @author Aashish
 * @version 1.0
 * @since 13-10-2017
 ****************************************************************************/
public class Stock {
	public String file = "/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/programs/stock.json";
	public StockObject stockobj = new StockObject();
	public static Scanner scanner = new Scanner(System.in);

	public void addNewStock() {
		JSONObject stock = new JSONObject();

		JSONArray array = new JSONArray();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			if (br.readLine() != null) {
				addStock();
				return;
			}
			getObject();
			getInput(array);
			stock.put("stock", array);

			Util.appendFile(stock.toJSONString(), file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addStock() {

		JSONObject jobject = new JSONObject();

		JSONArray jarray = Util.getjsonArray(file, "stock");
		getObject();
		getInput(jarray);
		jobject.put("stock", jarray);
		Util.appendFile(jobject.toJSONString(), file);

	}

	public void getObject() {
		System.out.println("enter stock name");
		stockobj.setName(scanner.next());
		System.out.println("enter number of stock");
		stockobj.setNumber(scanner.nextInt());
		System.out.println("enter price");
		stockobj.setPrice(scanner.nextInt());

	}

	public void getInput(JSONArray jsonarray) {

		JSONObject temp = new JSONObject();

		temp.put("name", stockobj.getName());

		temp.put("number", stockobj.getnumber());

		temp.put("price", stockobj.getPrice());
		jsonarray.add(temp);

	}

	public long getSingleValue(String stockname) {

		long total = 0;

		JSONArray jarray = Util.getjsonArray(file, "stock");

		for (Object obj1 : jarray) {
			JSONObject jsonObject = (JSONObject) obj1;
			if (jsonObject.get("name").equals(stockname)) {
				long price = (long) jsonObject.get("price");
				long number = (long) jsonObject.get("number");

				total = price * number;

			}

		}

		return total;

	}

	public int returnSize() {
		JSONParser parser = new JSONParser();

		try {

			JSONObject jobj = (JSONObject) parser.parse(new FileReader(file));

			JSONArray jarray = (JSONArray) jobj.get("stock");
			return (jarray.size());
		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	public void show() {

		JSONParser parser = new JSONParser();
		try {

			JSONObject jobj = (JSONObject) parser.parse(new FileReader(file));

			System.out.println(jobj.toJSONString());

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
