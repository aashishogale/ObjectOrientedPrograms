package com.bridgelabz.programs;

import java.util.Scanner;

/***************************************************************************
 * Purpose : To create class simple stock
 *
 * @author Aashish
 * @version 1.0
 * @since 13-10-2017
 ****************************************************************************/
public class StockMain {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char enter = 0;
		Stock stock = new Stock();
		int option = 0;

		do {
			System.out.println("enter option");
			System.out.println("1.enter new stock");
			System.out.println("2.get single stock value");
			System.out.println("3.get no of stocks");
			System.out.println("4 get total share value of all stocks");
			System.out.println("5.view stock file");
			option = scanner.nextInt();
			switch (option) {
			case 1:

				stock.addNewStock();
				break;
			case 2:
				scanner.nextLine();
				System.out.println("enter the stock name for the price");
				String str = scanner.nextLine();
				System.out.println(stock.getSingleValue(str));
				break;
			case 3:
				System.out.println(stock.returnSize());
				break;
			case 4:
				StockPortfolio spf = new StockPortfolio();
				long total = spf.getTotal();
				System.out.println(total);
				break;
			case 5:
				stock.show();
				break;
			default:
				System.out.println("enter correct choice");
				break;
			}
			System.out.println("Do you want to continue");
			enter = scanner.next().charAt(0);

		} while (enter != 'n');
		scanner.close();

	}
}
