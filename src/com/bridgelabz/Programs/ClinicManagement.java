package com.bridgelabz.Programs;

import java.util.Scanner;

import com.bridgelabz.Utility.Util;

public class ClinicManagement {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter patient");

		String patient=scanner.nextLine();
		Util.addNewAppointment(patient);
		
		
	}

}
