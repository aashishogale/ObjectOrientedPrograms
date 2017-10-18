package com.bridgelabz.Programs;

import java.util.Scanner;

import com.bridgelabz.Utility.Util;
/***************************************************************************
* Purpose : To create class for AddressBook
*
* @author   Aashish
* @version  1.0
* @since    14-10-2017
****************************************************************************/

public class AddressBook {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name;
		char enter;
		int choice;

		do {
			System.out.println("1.add names");
			System.out.println("2.edit information");
			System.out.println("3 delete information");
			System.out.println("4.sort information by name");

			System.out.println("5.sort information by zip");
			System.out.println("6.exit");

			System.out.println("enter your choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				Util.createAddressBook();
				Util.viewlist();
				break;
			case 2:
				System.out.println("enter the name");
				name = sc.next();
				Util.editAddressBook(name);
				Util.viewlist();
				break;
			case 3:
				Util.viewlist();
				System.out.println("enter the name");

				name = sc.next();
				Util.deletePerson(name);
				Util.viewlist();
				break;
			case 4:
				Util.sortByName();
				Util.viewlist();
				break;
			case 5:
				Util.sortByzip();
				Util.viewlist();

				break;
			case 6:
				break;
			}

			System.out.println("do you want to continue");
			enter = sc.next().charAt(0);

		} while (enter != 'n');

	}

}
