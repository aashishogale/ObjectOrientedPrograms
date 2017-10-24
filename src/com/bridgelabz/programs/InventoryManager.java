package com.bridgelabz.programs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.bridgelabz.utility.Util;

public class InventoryManager {
	public Scanner scanner = new Scanner(System.in);
	public JSONParser parser = new JSONParser();

	public String file = "/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/Inventory.json";

	/**
	 * purpose:to get the info of the inventory
	 * 
	 * @param
	 * @return count
	 */

	public void getInfo() {

		JSONArray invArray = Util.getjsonArray(file, "inventory");

		JSONObject inventoryObject = new JSONObject();
		System.out.println("enter the number of objects");
		int number = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < number; i++) {

			JSONObject temp = new JSONObject();
			System.out.println("enter name");
			String name = scanner.nextLine();
			temp.put("name", name);
			System.out.println("enter weight");
			temp.put("weight", scanner.nextInt());
			System.out.println("enter price");
			temp.put("price", scanner.nextInt());
			scanner.nextLine();
			invArray.add(temp);
		}

		inventoryObject.put("inventory", invArray);
		Util.appendFile(inventoryObject.toJSONString(), file);

	}

	/**
	 * purpose:to get the new info of the inventory
	 * 
	 * @param
	 * @return count
	 */

	public void getnewInfo() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			if (br.readLine() != null) {
				getInfo();
				return;
			}
			JSONArray invArray = new JSONArray();

			JSONObject inventoryObject = new JSONObject();
			System.out.println("enter the number of objects");
			int number = scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < number; i++) {

				JSONObject temp = new JSONObject();
				System.out.println("enter name");
				String name = scanner.nextLine();
				temp.put("name", name);
				System.out.println("enter weight");
				temp.put("weight", scanner.nextInt());
				System.out.println("enter price");
				temp.put("price", scanner.nextInt());
				scanner.nextLine();
				invArray.add(temp);
			}

			inventoryObject.put("inventory", invArray);
			Util.appendFile(inventoryObject.toJSONString(), file);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * purpose:to get the calculated value
	 * 
	 * @param name
	 * @return
	 */
	public long calculateValue(String name) {
		long total = 0;

		JSONArray invArray = Util.getjsonArray(file, "inventory");

		for (Object obj1 : invArray) {
			JSONObject jsonObject = (JSONObject) obj1;
			if (jsonObject.get("name").equals(name)) {
				long price = (long) jsonObject.get("price");
				long weight = (long) jsonObject.get("weight");

				total = price * weight;

			}

		}
		return total;

	}

	public void viewlist() {
		Util.viewList(file);
	}
}
