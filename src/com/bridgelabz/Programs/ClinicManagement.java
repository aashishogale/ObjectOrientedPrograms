package com.bridgelabz.Programs;

import java.util.Scanner;

import com.bridgelabz.Utility.Util;

public class ClinicManagement {
	/***************************************************************************
	 * Purpose : To create class for Clinic management
	 *
	 * @author Aashish
	 * @version 1.0
	 * @since 18-10-2017
	 ****************************************************************************/
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char next;
		int option;

		do {
			System.out.println("1.add doctor");
			System.out.println("2.add appointment");
			System.out.println("3.view Doctor list");
			System.out.println("4 view patient information");
			System.out.println("5.view appointment book");
			System.out.println("6 get sorted list");
			System.out.println("enter your choice");
			option = scanner.nextInt();
			switch (option) {
			case 1:
				Util.addNewDoctor();
				break;
			case 2:
				scanner.nextLine();
				System.out.println("enter patient name");
				String patient = scanner.nextLine();
				Util.addNewAppointment(patient);
				break;
			case 3:
				Util.viewDoctorlist();
				break;
			case 4:
				Util.viewPatientlist();
				break;
			case 5:
				Util.viewAppoitnment();
				break;
			case 6:
				Util.sortBydoctor();
				break;
			}
			System.out.println("do you want to continue");
			next = scanner.next().charAt(0);

		} while (next != 'n');

	}

}
