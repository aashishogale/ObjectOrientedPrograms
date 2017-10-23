package com.bridgelabz.Programs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.bridgelabz.Utility.Util;

/***************************************************************************
 * Purpose : To create class for Stock portfolio
 *
 * @author Aashish
 * @version 1.0
 * @since 13-10-2017
 ****************************************************************************/
public class StockPortfolio {
	public long getTotal() {

		long total = 0;

		String file = "/home/bridgeit/workspace/ObjectOrientedPrograms/src/com/bridgelabz/Programs/stock.json";

		JSONArray jarray = Util.getjsonArray(file, "stock");
		System.out.println("size" + jarray.size());

		for (Object obj1 : jarray) {
			JSONObject jsonObject = (JSONObject) obj1;

			long price = (long) jsonObject.get("price");
			long number = (long) jsonObject.get("number");

			total = total + price * number;

		}

		return total;

	}

}
