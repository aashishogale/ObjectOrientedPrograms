package com.bridgelabz.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.bridgelabz.Programs.Queue;

public class Util {
	static Scanner scanner = new Scanner(System.in);
	static Random random;
	static int array[] = new int[52];
	static String addressbooks = "/home/bridgeit/Desktop/addressbook.json";
	static String doctorbook = "/home/bridgeit/Desktop/Doctor.json";
	static String patientbook = "/home/bridgeit/Desktop/Patient.json";
	static String appointment = "/home/bridgeit/Desktop/appointment.json";
	static Random random1 = new Random();

	public static String[] readFile(String filePath) {
		String words[] = {};
		ArrayList<String> lines = new ArrayList<String>();
		String line, infoObject[] = {};
		BufferedReader bufferedReader;
		FileReader file;
		int index = 0;
		try {
			file = new FileReader(filePath);
			bufferedReader = new BufferedReader(file);
			while ((line = bufferedReader.readLine()) != null) {
				infoObject = line.split(",|\\s");
				for (int i = 0; i < infoObject.length; i++) {
					lines.add(infoObject[i]);

				}
			}
			words = lines.toArray(new String[lines.size()]);
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return words;
	}

	/**
	 * This method appends word on filePath
	 * 
	 * @param word
	 * @param filePath
	 * 
	 */
	public static void appendFile(String word, String filePath) {

		try {
			FileWriter writer = new FileWriter(filePath, false);
			PrintWriter out = new PrintWriter(writer);
			out.println(word);
			out.close();
			writer.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method write given string on given filePath
	 * 
	 * @param word
	 *            -Array of String to write
	 * @param filePath
	 *            -File path with file name
	 */
	public static void writeFile(String word[], String filePath) {

		try {
			FileWriter writer = new FileWriter(filePath, false);
			PrintWriter out = new PrintWriter(writer);
			for (int i = 0; i < word.length; i++) {
				out.write(word[i] + " ");
			}
			out.close();
			writer.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static {
		random = new Random();
	}

	public static int generateRandom(int number) {
		int randomnum;
		randomnum = random.nextInt(number);
		return randomnum;
	}

	public static int generateIndex() {

		int index = Util.generateRandom(52);
		for (int i = 0; i < 52; i++) {
			if (array[i] == index) {
				return (generateIndex());
			}
		}

		return index;

	}

	public static void shuffle(String suits[], String rank[]) {
		Queue<Queue<String>> allplayers = new Queue<Queue<String>>();
		Queue<String> queue;
		String unsorteddeck[] = new String[9];
		String player[][] = new String[4][9];
		String randomCard = "";
		String deck[] = new String[52];
		int suitSelect = 0;
		int rankSelect = 0;
		int flag = 0;
		int k = 0;
		int m = 0;
		int l = 0;
		int index = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				deck[k++] = suits[i] + "-" + rank[j];
			}

		}

		for (int i = 0; i < 4; i++) {
			queue = new Queue<String>();
			l = 0;

			for (int j = 0; j < 9; j++) {

				index = Util.generateIndex();
				array[m++] = index;
				player[i][j] = deck[index];
				unsorteddeck[l++] = deck[index];

			}
			List<String> tempdeck = Arrays.asList(unsorteddeck);
			Collections.sort(tempdeck, new Comparator<String>() {
				String arr1[] = new String[2];
				String arr2[] = new String[2];

				public int compare(String o1, String o2) {
					arr1 = o1.split("-");
					arr2 = o2.split("-");
					if (arr1[1].equals("Jack")) {
						o1 = o1 + "11";
					}
					if (arr1[1].equals("Queen")) {
						o1 = o1 + "12";
					}
					if (arr1[1].equals("King")) {
						o1 = o1 + "13";
					}
					if (arr1[1].equals("Ace")) {
						o1 = o1 + "14";
					}
					if (arr2[1].equals("Jack")) {
						o2 = o2 + "11";
					}
					if (arr2[1].equals("Queen")) {
						o2 = o2 + "12";
					}
					if (arr2[1].equals("King")) {
						o2 = o2 + "13";
					}
					if (arr2[1].equals("Ace")) {
						o2 = o2 + "14";
					}

					return extractInt(o1) - extractInt(o2);
				}

				int extractInt(String s) {
					String num = s.replaceAll("\\D", "");
					// return 0 if no digits found
					return num.isEmpty() ? 13 : Integer.parseInt(num);
				}
			});

			unsorteddeck = (String[]) tempdeck.toArray();
			for (int n = 0; n < unsorteddeck.length; n++) {
				queue.enqueue(unsorteddeck[n]);
			}

			allplayers.enqueue(queue);
		}

		for (int i = 0; i < 4; i++) {
			System.out.print((i + 1) + "   player  ");
			queue = allplayers.dequeue();
			for (int j = 0; j < 9; j++) {

				System.out.println(queue.dequeue());
			}
			System.out.println("");

		}
	}

	public static void addAddressBook() {
		JSONObject jsonobject = new JSONObject();
		JSONObject jsonobject1 = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONArray array1 = new JSONArray();
		try {
			JSONObject parseObject = (JSONObject) parser.parse(new FileReader(addressbooks));

			JSONArray parseArray = (JSONArray) parseObject.get("addressbook");
			System.out.println("enter the number of  names  to add");
			int number = scanner.nextInt();
			for (int i = 0; i < number; i++) {
				JSONObject infoObject = new JSONObject();
				System.out.println("enter first name");

				infoObject.put("firstname", scanner.next());
				System.out.println("enter last name");

				infoObject.put("lastname", scanner.next());
				System.out.println("enter address");

				infoObject.put("address", scanner.next());
				System.out.println("enter city");

				infoObject.put("city", scanner.next());
				System.out.println("enter state");

				infoObject.put("state", scanner.next());

				System.out.println("enter zipcode");
				infoObject.put("zip", scanner.nextInt());
				System.out.println("enter phone number");
				infoObject.put("phno", scanner.nextInt());
				parseArray.add(infoObject);
			}

			jsonobject.put("addressbook", parseArray);
			Util.appendFile(jsonobject.toJSONString(), addressbooks);

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createAddressBook() {

		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();

		try {
			BufferedReader br = new BufferedReader(new FileReader(addressbooks));
			if (br.readLine() != null) {
				Util.addAddressBook();
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("enter the number of  names  to add");
		int number = scanner.nextInt();
		for (int i = 0; i < number; i++) {
			JSONObject infoObject = new JSONObject();
			System.out.println("enter first name");

			infoObject.put("firstname", scanner.next());
			System.out.println("enter last name");

			infoObject.put("lastname", scanner.next());
			System.out.println("enter address");

			infoObject.put("address", scanner.next());
			System.out.println("enter city");

			infoObject.put("city", scanner.next());
			System.out.println("enter state");

			infoObject.put("state", scanner.next());

			System.out.println("enter zipcode");
			infoObject.put("zip", scanner.nextInt());
			System.out.println("enter phone number");
			infoObject.put("phno", scanner.nextInt());
			array1.add(infoObject);
		}

		jsonobject1.put("addressbook", array1);
		Util.appendFile(jsonobject1.toJSONString(), addressbooks);
	}

	public static void editAddressBook(String name) {

		int number;
		String[] valuelist = { "address", "city", "state", "zip", "phno" };
		System.out.println("1.address,2 city,3 state,4 zip,5 phno");

		number = scanner.nextInt();
		System.out.println("enter value");
		String cde = scanner.next();
		JSONParser parser = new JSONParser();
		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(addressbooks));

			JSONArray parseArray = (JSONArray) arrayobject.get("addressbook");
			for (Object name1 : parseArray) {
				JSONObject nameselect = (JSONObject) name1;

				if (nameselect.get("firstname").equals(name)) {
					nameselect.replace(valuelist[number - 1], cde);
					System.out.println(nameselect.get(valuelist[number - 1]));
				}

			}

			arrayobject.put("addressbook", parseArray);
			Util.appendFile(arrayobject.toJSONString(), addressbooks);

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sortByName() {
		JSONParser parser = new JSONParser();

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(addressbooks));

			JSONArray parseArray = (JSONArray) arrayobject.get("addressbook");
			for (int i = 0; i < parseArray.size() - 1; i++) {
				// JSONObject tempObject=new JSONObject();
				JSONObject nameselect = (JSONObject) parseArray.get(i);
				JSONObject nameselect1 = (JSONObject) parseArray.get(i + 1);

				String tempname1 = (String) nameselect.get("lastname");
				String tempname2 = (String) nameselect1.get("lastname");

				if (tempname1.compareToIgnoreCase(tempname2) > 0) {
					parseArray.set(i, nameselect1);
					parseArray.set(i + 1, nameselect);
				}

			}

			arrayobject.put("addressbook", parseArray);
			Util.appendFile(arrayobject.toJSONString(), addressbooks);

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void sortByzip() {
		JSONParser parser = new JSONParser();

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(addressbooks));

			JSONArray parseArray = (JSONArray) arrayobject.get("addressbook");
			for (int i = 0; i < parseArray.size() - 1; i++) {
				// JSONObject tempObject=new JSONObject();
				JSONObject nameselect = (JSONObject) parseArray.get(i);
				JSONObject nameselect1 = (JSONObject) parseArray.get(i + 1);

				long tempzip1 = (Long) nameselect.get("zip");
				long tempzip2 = (Long) nameselect1.get("zip");

				if (tempzip1 > tempzip2) {
					parseArray.set(i, nameselect1);
					parseArray.set(i + 1, nameselect);
				}

			}

			arrayobject.put("addressbook", parseArray);
			Util.appendFile(arrayobject.toJSONString(), addressbooks);

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void deletePerson(String name) {
		JSONParser parser = new JSONParser();
		int flag = 0;

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(addressbooks));

			JSONArray parseArray = (JSONArray) arrayobject.get("addressbook");
			for (int i = 0; i < parseArray.size(); i++) {

				JSONObject nameselect = (JSONObject) parseArray.get(i);

				String tempname1 = (String) nameselect.get("firstname");

				if (tempname1.compareToIgnoreCase(name) == 0) {
					int j = i;
					parseArray.remove(i);
					flag = 1;
					break;
				}

			}
			if (flag == 0) {
				System.out.println("name not found");
			}

			arrayobject.put("addressbook", parseArray);
			Util.appendFile(arrayobject.toJSONString(), addressbooks);

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void viewlist() {
		JSONParser parser = new JSONParser();

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(addressbooks));

			System.out.println(arrayobject.toJSONString());

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addNewDoctor() {
		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();

		try {
			BufferedReader br = new BufferedReader(new FileReader(doctorbook));
			if (br.readLine() != null) {
				Util.addDoctor();
				return;
			}

			JSONObject infoObject = new JSONObject();
			System.out.println("enter  name");

			infoObject.put("name", scanner.next());
			System.out.println("enter specializtion");

			infoObject.put("special", Util.getSpecial());
			System.out.println("enter availability");

			infoObject.put("avail", Util.getAvail());

			infoObject.put("id", Util.getDoctorid());

			array1.add(infoObject);
			jsonobject1.put("doctor", array1);
			Util.appendFile(jsonobject1.toJSONString(), doctorbook);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addDoctor() {
		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();
		JSONParser parser = new JSONParser();
		try {

			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(doctorbook));

			JSONArray parseArray = (JSONArray) arrayobject.get("doctor");
			JSONObject infoObject = new JSONObject();
			System.out.println("enter  name");
			String name = scanner.nextLine();
			infoObject.put("name", name);

			infoObject.put("special", Util.getSpecial());
			System.out.println("enter availability");

			infoObject.put("avail", Util.getAvail());

			infoObject.put("id", Util.getDoctorid());
			parseArray.add(infoObject);
			jsonobject1.put("doctor", parseArray);
			Util.appendFile(jsonobject1.toJSONString(), doctorbook);

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void addNewPatient(String patient) {
		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();

		try {
			BufferedReader br = new BufferedReader(new FileReader(patientbook));
			if (br.readLine() != null) {
				Util.addPatient(patient);
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			JSONObject infoObject = new JSONObject();


			infoObject.put("name", patient);
			System.out.println("enter mobile id");

			infoObject.put("mobile", scanner.nextInt());
			System.out.println("enter age");

			infoObject.put("age", scanner.nextInt());

			infoObject.put("id", Util.getPatientid());

			array1.add(infoObject);
		

		jsonobject1.put("patient", array1);
		Util.appendFile(jsonobject1.toJSONString(), patientbook);

	}

	public static void addPatient(String patient) {
		JSONObject jsonobject1 = new JSONObject();

		JSONParser parser = new JSONParser();
		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(patientbook));

			JSONArray parseArray = (JSONArray) arrayobject.get("patient");

		
				JSONObject infoObject = new JSONObject();
			

				infoObject.put("name", patient);
				System.out.println("enter mobile id");
				infoObject.put("mobile", scanner.nextInt());
				System.out.println("enter age");

				infoObject.put("age", scanner.nextInt());

				infoObject.put("id", Util.getPatientid());
				parseArray.add(infoObject);

			
			jsonobject1.put("patient", parseArray);
			Util.appendFile(jsonobject1.toJSONString(), patientbook);
		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean searchDoctor(String name) {

		
int flag=0;
		JSONParser parser = new JSONParser();

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(doctorbook));

			JSONArray parseArray = (JSONArray) arrayobject.get("doctor");
			for (int i = 0; i < parseArray.size() - 1; i++) {
				// JSONObject tempObject=new JSONObject();
				JSONObject nameselect = (JSONObject) parseArray.get(i);

				if (nameselect.get("name").equals(name)) {
					System.out.println("element found at" + i + nameselect.toJSONString());
					flag = 1;
					return true;

				}

			}
			if (flag == 0) {
				return false;
			}

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static boolean searchPatient() {

		int number;
		String[] valuelist = { "name", "mobile", "id" };
		System.out.println("1.name 2.avail 3.id 4.special");
		int flag = 0;
		number = scanner.nextInt();
		System.out.println("enter value");
		String value = scanner.next();
		JSONParser parser = new JSONParser();

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(doctorbook));

			JSONArray parseArray = (JSONArray) arrayobject.get("doctor");
			for (int i = 0; i < parseArray.size() - 1; i++) {
				// JSONObject tempObject=new JSONObject();
				JSONObject nameselect = (JSONObject) parseArray.get(i);

				if (nameselect.get(valuelist[i]).equals(value)) {
					System.out.println("element found at" + i + nameselect.toJSONString());
					flag = 1;
					return true;

				}

			}
			if (flag == 0) {
				return false;
			}

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public static long getDoctorid() {

		JSONParser parser = new JSONParser();

		try {
			BufferedReader br = new BufferedReader(new FileReader(doctorbook));
			if (br.readLine() == null) {

				return 1;
			}
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(doctorbook));

			JSONArray parseArray = (JSONArray) arrayobject.get("doctor");

			JSONObject nameselect = (JSONObject) parseArray.get(parseArray.size() - 1);

			long id = (long) nameselect.get("id");

			return id + 1;

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public static long getPatientid() {

		JSONParser parser = new JSONParser();

		try {
			BufferedReader br = new BufferedReader(new FileReader(patientbook));
			if (br.readLine() == null) {

				return 1;
			}
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(patientbook));

			JSONArray parseArray = (JSONArray) arrayobject.get("patient");

			JSONObject nameselect = (JSONObject) parseArray.get(parseArray.size() - 1);
			long id = (long) nameselect.get("id");
			return id + 1;

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public static void addNewAppointment(String patient) {
		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();

		try {
			BufferedReader br = new BufferedReader(new FileReader(appointment));
			if (br.readLine() != null) {
				Util.addAppointment(patient);
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject infoObject = new JSONObject();
		System.out.println("enter  doctor name");
		String doctor = scanner.nextLine();
		if (searchDoctor(doctor)) {
			infoObject.put("doctor", doctor);

			JSONArray patientArray = new JSONArray();
			

			
			patientArray.add(patient);
			infoObject.put("patients", patientArray);
			infoObject.put("patientcount", 1);
			Util.addNewPatient(patient);
			

		} else {
			System.out.println("doctor not found");
		}

		array1.add(infoObject);

		jsonobject1.put("appointment", array1);
		Util.appendFile(jsonobject1.toJSONString(), appointment);

	}

	private static void addAppointment(String patient) {
		JSONObject jsonobject1 = new JSONObject();
		JSONParser parser = new JSONParser();
		JSONArray array1 = new JSONArray();
		JSONObject arrayobject = new JSONObject();
		JSONArray patientArray=new JSONArray();
		JSONObject infoObject = new JSONObject();
		try {
			arrayobject = (JSONObject) parser.parse(new FileReader(appointment));
		

		JSONArray parseArray = (JSONArray) arrayobject.get("appointment");

		
		System.out.println("enter  doctor name");
		String doctor = scanner.nextLine();
		if (!searchDoctor(doctor)) {
			System.out.println("Doctor not found");
			
		}
		if (searchAppointment(doctor)) {

			JSONObject tempObject = new JSONObject();
			for (int i = 0; i < parseArray.size(); i++) {

				JSONObject nameselect = (JSONObject) parseArray.get(i);
				if (nameselect.get("doctor").equals(doctor)) {
			//System.out.println(nameselect.toJSONString());
					 patientArray = (JSONArray) nameselect.get("patients");
						

						
						
						
					break;
				}
			}

			
			patientArray.add(patient);
			tempObject.put("patients", patientArray);
			int number = (int) tempObject.get("patientcount");
			tempObject.replace("patientcount", number + 1);

			parseArray.add(tempObject);
			jsonobject1.put("appointment", parseArray);
			Util.appendFile(jsonobject1.toJSONString(), appointment);
			Util.addNewPatient(patient);
			
		}
		 else {

			infoObject.put("doctor", doctor);

		 patientArray = new JSONArray();
			

			
			patientArray.add(patient);
			infoObject.put("patients", patientArray);
			infoObject.put("patientcount", 1);

			parseArray.add(infoObject);
			infoObject.put("appointment", parseArray);
			Util.appendFile(infoObject.toJSONString(), appointment);
			Util.addNewPatient(patient);
		}} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	private static boolean searchAppointment(String doctor) {

		int flag = 0;
		JSONParser parser = new JSONParser();

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(appointment));

			JSONArray parseArray = (JSONArray) arrayobject.get("appointment");
			for (int i = 0; i < parseArray.size() - 1; i++) {
				// JSONObject tempObject=new JSONObject();
				JSONObject nameselect = (JSONObject) parseArray.get(i);

				if (nameselect.get("doctor").equals(doctor)) {
					System.out.println("element found at" + i + nameselect.toJSONString());
					flag = 1;
					return true;

				}

			}
			if (flag == 0) {
				return false;
			}

		} catch (ParseException ie1) {
			ie1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return false;
	}

	// TODO Auto-generated method stub

	public static String getSpecial() {
		String array[] = { "Heart", "Brain", "Lungs" };
		System.out.println("1.heart,2.brain,3.lungs");
		int index = 0;
		System.out.println("enter the specialization");
		index = scanner.nextInt();
		return array[index - 1];
	}

	public static String getAvail() {
		String array[] = { "AM", "PM", "Both" };
		System.out.println("1.Am,2.Pm,3.both");
		int index = 0;
		System.out.println("enter the Availability");
		index = scanner.nextInt();
		return array[index - 1];
	}
}
