package com.bridgelabz.Programs;

import java.io.IOException;

public class CommercialProcessing {
	public static void main(String args[]) throws IOException {
		StockAccount stock=new StockAccount("/home/bridgeit/Desktop/stockaccount");
	stock.buy(500,"!");
	stock.sell(200,"@");
	
	
	}

}
