package com.bridgelabz.Programs;
import java.util.Scanner;

public class StockMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char ch = 0;
		Stock stock=new Stock();
		stock.initializeJSON();
		do {
		stock.addStock();
		System.out.println("Do you want to enter more stock");
		ch=sc.nextLine().charAt(0);
		}while(ch!='n');
		System.out.println("enter the stock name for the price");
		String str=sc.nextLine();
		System.out.println(stock.getSingleValue(str));
		System.out.println(stock.returnSize());
          sc.close();
		StockPortfolio spf=new StockPortfolio();
		long total=spf.getTotal();
		System.out.println(total);
	}

}
