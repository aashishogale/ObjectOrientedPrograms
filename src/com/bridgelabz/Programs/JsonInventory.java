package com.bridgelabz.Programs;

import java.util.Scanner;

public class JsonInventory {

	public static void main(String[] args) {
		InventoryManager imanager = new InventoryManager();
		Scanner scanner = new Scanner(System.in);
		char enter;
		int choice;
		do {
			System.out.println("1.add new stock");
			System.out.println("2.calculate value");
			System.out.println("3 View List");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				imanager.getnewInfo();
				break;
			case 2:
				scanner.nextLine();
				System.out.println("enter name of inventory");
				String name = scanner.nextLine();
				System.out.println("total value of" + name + " " + imanager.calculateValue(name));
				break;
			case 3:
				imanager.viewlist();
				break;

			}
			System.out.println("do you want to continue");
			enter = scanner.next().charAt(0);
		} while (enter != 'n');

	}
}
