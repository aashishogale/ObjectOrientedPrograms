package com.bridgelabz.programs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bridgelabz.utility.Util;

/***************************************************************************
 * Purpose : To create class for Regular expression
 *
 * @author Aashish
 * @version 1.0
 * @since 18-10-2017
 ****************************************************************************/

public class RegularExpression {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RegEx regular = new RegEx();
		Scanner scanner = new Scanner(System.in);
		RegularExpression reg = new RegularExpression();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date1 = format.format(date).toString();
		regular.setDate2(date1);
		System.out.println("Enter your name ");
		regular.setName(scanner.nextLine());
		System.out.println("Enter your full name ");
		regular.setFullname(scanner.nextLine());
		System.out.println("Enter your Contact number");
		regular.setContact(scanner.nextLine());

		System.out.println(Util.replaceString(regular));
		scanner.close();
	}

	

}
