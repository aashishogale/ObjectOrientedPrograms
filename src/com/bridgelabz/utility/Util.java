package com.bridgelabz.utility;

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

import com.bridgelabz.programs.Queue;

/***************************************************************************
 * Purpose : To create class for providing utility methods
 *
 * @author Aashish
 * @version 1.0
 * @since 20-10-2017
 ****************************************************************************/
public class Util {
	public static Scanner scanner = new Scanner(System.in);
	public static Random random;
	public static int array[] = new int[52];
	public static String addressbooks = "/home/bridgeit/Desktop/addressbook.json";
	public static String doctorbook = "/home/bridgeit/Desktop/Doctor.json";
	public static String patientbook = "/home/bridgeit/Desktop/Patient.json";
	public static String appointment = "/home/bridgeit/Desktop/appointment.json";
	public static Random random1 = new Random();

	public static String[] readFile(String filePath) {
		String words[] = {};
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		String infoObject[] = {};
		BufferedReader bufferedReader;
		FileReader file;
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

	/**
	 * This method generate random number
	 * 
	 * @param number-limit
	 * @return randomnum-random integer
	 * 
	 */
	public static int generateRandom(int number) {
		int randomnum;
		randomnum = random.nextInt(number);
		return randomnum;
	}

	/**
	 * This method generate random and uniqueindex for deck
	 * 
	 *
	 * @return index
	 * 
	 */

	public static int generateIndex() {

		int index = Util.generateRandom(52);
		for (int i = 0; i < 52; i++) {
			if (array[i] == index) {
				return (generateIndex());
			}
		}

		return index;

	}

	/**
	 * This method shuffles the deck
	 * 
	 * @param suits-array
	 *            of suits,rank,arrayof ranks
	 *
	 * 
	 */
	public static void shuffle(String suits[], String rank[]) {
		Queue<Queue<String>> allplayers = new Queue<Queue<String>>();
		Queue<String> queue;
		String unsorteddeck[] = new String[9];
		String player[][] = new String[4][9];
		String deck[] = new String[52];
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

				public int compare(String original1, String original2) {
					arr1 = original1.split("-");
					arr2 = original2.split("-");
					if (arr1[1].equals("Jack")) {
						original1 = original1 + "11";
					}
					if (arr1[1].equals("Queen")) {
						original1 = original1 + "12";
					}
					if (arr1[1].equals("King")) {
						original1 = original1 + "13";
					}
					if (arr1[1].equals("Ace")) {
						original1 = original1 + "14";
					}
					if (arr2[1].equals("Jack")) {
						original2 = original2 + "11";
					}
					if (arr2[1].equals("Queen")) {
						original2 = original2 + "12";
					}
					if (arr2[1].equals("King")) {
						original2 = original2 + "13";
					}
					if (arr2[1].equals("Ace")) {
						original2 = original2 + "14";
					}

					return extractInt(original1) - extractInt(original2);
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

	/**
	 * This method gets the jsonarray for given file
	 * 
	 * @param filepath,name
	 * @return jsonArray
	 * 
	 */
	public static JSONArray getjsonArray(String filepath, String name) {
		JSONParser parser = new JSONParser();
		JSONArray parseArray = new JSONArray();
		JSONObject parseObject;
		try {
			parseObject = (JSONObject) parser.parse(new FileReader(filepath));
			parseArray = (JSONArray) parseObject.get(name);
			return parseArray;
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parseArray;

	}

	/**
	 * This method gets the adress into the json array
	 * 
	 * @param newArray
	 * @return newArray
	 * 
	 */
	public static JSONArray getAddress(JSONArray newArray) {
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
			newArray.add(infoObject);
		}
		return newArray;

	}

	/**
	 * This method will add new names
	 * 
	 *
	 * @return
	 * 
	 */

	public static void createAddressBook() {

		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();

		if (checkEmpty(addressbooks)) {
			array1 = Util.getAddress(array1);

		} else {
			JSONArray parseArray = Util.getjsonArray(addressbooks, "addressbook");
			array1 = Util.getAddress(parseArray);

		}

		jsonobject1.put("addressbook", array1);
		Util.appendFile(jsonobject1.toJSONString(), addressbooks);
	}

	/**
	 * This method will edit addressbook
	 * 
	 * @param name
	 * @return
	 * 
	 */

	public static void editAddressBook(String name) {

		int number;
		String[] valuelist = { "address", "city", "state", "zip", "phno" };
		System.out.println("1.address,2 city,3 state,4 zip,5 phno");

		number = scanner.nextInt();
		System.out.println("enter value");
		String cde = scanner.next();
		new JSONParser();
		JSONObject arrayobject = new JSONObject();

		JSONArray parseArray = Util.getjsonArray(addressbooks, "addressbook");
		for (Object name1 : parseArray) {
			JSONObject nameselect = (JSONObject) name1;

			if (nameselect.get("firstname").equals(name)) {
				nameselect.replace(valuelist[number - 1], cde);
				System.out.println(nameselect.get(valuelist[number - 1]));
			}

		}

		arrayobject.put("addressbook", parseArray);
		Util.appendFile(arrayobject.toJSONString(), addressbooks);

	}

	/**
	 * This method will sort the addressbook by name
	 * 
	 * @param
	 * @return
	 * 
	 */

	public static void sortByName() {
		new JSONParser();

		JSONObject arrayobject = new JSONObject();

		JSONArray parseArray = Util.getjsonArray(addressbooks, "addressbook");
		for (int i = 0; i < parseArray.size() - 1; i++) {

			JSONObject nameselect = (JSONObject) parseArray.get(i);
			for (int j = i + 1; j < parseArray.size(); j++) {
				JSONObject nameselect1 = (JSONObject) parseArray.get(j);

				String tempname1 = (String) nameselect.get("lastname");
				String tempname2 = (String) nameselect1.get("lastname");

				if (tempname1.compareToIgnoreCase(tempname2) > 0) {
					parseArray.set(i, nameselect1);
					parseArray.set(j, nameselect);
				}

			}
		}
		arrayobject.put("addressbook", parseArray);
		Util.appendFile(arrayobject.toJSONString(), addressbooks);

	}

	/**
	 * This method will sort the addressbook by zip
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static void sortByzip() {
		new JSONParser();

		JSONObject arrayobject = new JSONObject();

		JSONArray parseArray = Util.getjsonArray(addressbooks, "addressbook");
		for (int i = 0; i < parseArray.size() - 1; i++) {
			// JSONObject tempObject=new JSONObject();

			JSONObject nameselect = (JSONObject) parseArray.get(i);
			for (int j = i + 1; j < parseArray.size(); j++) {
				JSONObject nameselect1 = (JSONObject) parseArray.get(j);

				long tempzip1 = (Long) nameselect.get("zip");
				long tempzip2 = (Long) nameselect1.get("zip");

				if (tempzip1 > tempzip2) {
					parseArray.set(i, nameselect1);
					parseArray.set(j, nameselect);
				}

			}
		}

		arrayobject.put("addressbook", parseArray);
		Util.appendFile(arrayobject.toJSONString(), addressbooks);

	}

	/**
	 * This method will delete the person
	 * 
	 * @param name
	 * @return
	 * 
	 */

	public static void deletePerson(String name) {
		new JSONParser();
		int flag = 0;

		JSONObject arrayobject = new JSONObject();

		JSONArray parseArray = Util.getjsonArray(addressbooks, "addressbook");
		for (int i = 0; i < parseArray.size(); i++) {

			JSONObject nameselect = (JSONObject) parseArray.get(i);

			String tempname1 = (String) nameselect.get("firstname");

			if (tempname1.compareToIgnoreCase(name) == 0) {
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

	}

	/**
	 * This method will show the doctorlist
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static void viewlist() {
		Util.viewList(addressbooks);
	}

	/**
	 * This method will get the information for doctor
	 * 
	 * @param docarray
	 * @return docarray
	 * 
	 */
	public static JSONArray getDoctor(JSONArray docarray) {
		JSONObject infoObject = new JSONObject();
		System.out.println("enter  name");

		infoObject.put("name", scanner.next());
		System.out.println("enter specializtion");

		infoObject.put("special", Util.getSpecial());
		System.out.println("enter availability");

		infoObject.put("avail", Util.getAvail());

		infoObject.put("id", Util.getDoctorid());
		infoObject.put("patientcount", 0);
		docarray.add(infoObject);
		return docarray;

	}

	/**
	 * This method will add the new doctor
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static void addNewDoctor() {
		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();

		if (checkEmpty(doctorbook)) {
			array1 = Util.getDoctor(array1);

		} else {
			JSONArray parseArray = Util.getjsonArray(doctorbook, "doctor");
			array1 = Util.getDoctor(parseArray);

		}

		jsonobject1.put("doctor", array1);
		Util.appendFile(jsonobject1.toJSONString(), doctorbook);

	}

	/**
	 * This method will take the patient information to the jsonobject
	 * 
	 * @param paarray,patient
	 * @return paarray
	 * 
	 */

	public static JSONArray getPatient(JSONArray paArray, String patient) {
		JSONObject infoObject = new JSONObject();

		infoObject.put("name", patient);
		System.out.println("enter mobile id");

		infoObject.put("mobile", scanner.nextInt());
		System.out.println("enter age");

		infoObject.put("age", scanner.nextInt());

		infoObject.put("id", Util.getPatientid());

		paArray.add(infoObject);
		return paArray;
	}

	/**
	 * This method will add new patient
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static void addNewPatient(String patient) {
		JSONObject jsonobject1 = new JSONObject();

		JSONArray array1 = new JSONArray();

		if (checkEmpty(patientbook)) {
			array1 = Util.getPatient(array1, patient);

		} else {
			JSONArray parseArray = Util.getjsonArray(patientbook, "patient");
			array1 = Util.getPatient(parseArray, patient);

		}

		jsonobject1.put("patient", array1);
		Util.appendFile(jsonobject1.toJSONString(), patientbook);

	}

	/**
	 * This method will search for the doctor
	 * 
	 * @param name
	 * @return
	 * 
	 */

	public static boolean searchDoctor(String name) {

		int flag = 0;

		JSONArray parseArray = Util.getjsonArray(doctorbook, "doctor");
		for (int i = 0; i < parseArray.size(); i++) {

			JSONObject nameselect = (JSONObject) parseArray.get(i);

			if (nameselect.get("name").equals(name)) {
				System.out.println("element found at" + i + nameselect.toJSONString());
				flag = 1;
				return true;

			}

		}
		return flag == 1;

	}

	/**
	 * This method will search for the patient
	 * 
	 * @param name
	 * @return
	 * 
	 */

	public static boolean searchPatient() {

		String[] valuelist = { "name", "mobile", "id" };
		System.out.println("1.name 2.avail 3.id 4.special");
		int flag = 0;
		scanner.nextInt();
		System.out.println("enter value");
		String value = scanner.next();
		new JSONParser();

		new JSONObject();

		JSONArray parseArray = Util.getjsonArray(patientbook, "patient");
		for (int i = 0; i < parseArray.size() - 1; i++) {
			// JSONObject tempObject=new JSONObject();
			JSONObject nameselect = (JSONObject) parseArray.get(i);

			if (nameselect.get(valuelist[i]).equals(value)) {
				System.out.println("element found at" + i + nameselect.toJSONString());
				flag = 1;
				return true;

			}

		}
		return flag==1;
	}

	/**
	 * This method will generate id for doctor
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static long getDoctorid() {

		if (checkEmpty(doctorbook)) {

			return 1;
		}

		JSONArray parseArray = Util.getjsonArray(doctorbook, "doctor");

		long id = parseArray.size();
		return id + 1;

	}

	/**
	 * This method will generate id for patient
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static long getPatientid() {

		if (checkEmpty(patientbook)) {

			return 1;
		}

		JSONArray parseArray = Util.getjsonArray(patientbook, "patient");

		JSONObject nameselect = (JSONObject) parseArray.get(parseArray.size() - 1);
		long id = (long) nameselect.get("id");
		return id + 1;

	}

	/**
	 * This method will add new appointment in a empty file
	 * 
	 * @param patient
	 * @return
	 * 
	 */

	public static void addNewAppointment(String patient) {
		JSONObject jsonobject1 = new JSONObject();
		JSONObject doctorobject = new JSONObject();
		JSONArray array1 = new JSONArray();
		JSONArray doctorArray = new JSONArray();

		if (!checkEmpty(appointment)) {
			Util.addAppointment(patient);
			return;
		}

		JSONObject infoObject = new JSONObject();
		scanner.nextLine();
		System.out.println("enter  doctor name");
		String doctor = scanner.nextLine();
		if (searchDoctor(doctor) && checkAppointment(doctor)) {
			infoObject.put("doctor", doctor);

			doctorArray = Util.getjsonArray(doctorbook, "doctor");

			for (int i = 0; i < doctorArray.size() - 1; i++) {
				// JSONObject tempObject=new JSONObject();
				JSONObject nameselect = (JSONObject) doctorArray.get(i);

				if (nameselect.get("name").equals(doctor)) {

					long number = (long) nameselect.get("patientcount");
					nameselect.replace("patientcount", number + 1);

				}

			}

			doctorobject.put("doctor", doctorArray);
			infoObject.put("patient", patient);

			array1.add(infoObject);
			Util.addNewPatient(patient);
			jsonobject1.put("appointment", array1);
			Util.appendFile(jsonobject1.toJSONString(), appointment);

		} else {
			System.out.println("doctor not found");
		}

		Util.appendFile(doctorobject.toJSONString(), doctorbook);
		jsonobject1.put("appointment", array1);
		Util.appendFile(jsonobject1.toJSONString(), appointment);

	}

	/**
	 * This method will add appointment in a file
	 * 
	 * @param patient
	 * @return
	 * 
	 */
	private static void addAppointment(String patient) {
		JSONObject jsonobject1 = new JSONObject();

		JSONObject doctorobject = new JSONObject();

		JSONArray parseArray = Util.getjsonArray(appointment, "appointment");
		JSONArray doctorArray = Util.getjsonArray(doctorbook, "doctor");

		scanner.nextLine();
		System.out.println("enter  doctor name");
		String doctor = scanner.nextLine();

		if (searchDoctor(doctor) && checkAppointment(doctor)) {

			JSONObject tempObject = new JSONObject();

			for (int i = 0; i < doctorArray.size() - 1; i++) {
				// JSONObject tempObject=new JSONObject();
				JSONObject nameselect = (JSONObject) doctorArray.get(i);

				if (nameselect.get("name").equals(doctor)) {

					long number = (long) nameselect.get("patientcount");
					nameselect.replace("patientcount", number + 1);

				}

			}
			tempObject.put("patient", patient);
			tempObject.put("doctor", doctor);

			parseArray.add(tempObject);
			jsonobject1.put("appointment", parseArray);
			doctorobject.put("doctor", doctorArray);
			Util.appendFile(jsonobject1.toJSONString(), appointment);
			Util.appendFile(doctorobject.toJSONString(), doctorbook);
			Util.addNewPatient(patient);

		} else {
			System.out.println("pls enter another doctor");
		}

	}

	// TODO Auto-generated method stub
	/**
	 * This method will give the chosen speciality
	 * 
	 * @param
	 * @return special
	 * 
	 */
	public static String getSpecial() {
		String array[] = { "Heart", "Brain", "Lungs" };
		System.out.println("1.heart,2.brain,3.lungs");
		int index = 0;
		System.out.println("enter the specialization");
		index = scanner.nextInt();
		String special = array[index - 1];
		return special;
	}

	/**
	 * This method will give the chosen availability
	 * 
	 * @param
	 * @return avail
	 * 
	 */
	public static String getAvail() {
		String array[] = { "AM", "PM", "Both" };
		System.out.println("1.Am,2.Pm,3.both");
		int index = 0;
		System.out.println("enter the Availability");
		index = scanner.nextInt();
		String avail = array[index - 1];
		return avail;
	}

	/**
	 * This method will return if appointment doctor is available
	 * 
	 * @param name-doctors
	 *            name
	 * @return
	 * 
	 */
	public static boolean checkAppointment(String name) {

		int flag = 0;
		new JSONParser();

		new JSONObject();

		JSONArray parseArray = Util.getjsonArray(doctorbook, "doctor");
		for (int i = 0; i < parseArray.size(); i++) {
			// JSONObject tempObject=new JSONObject();
			JSONObject nameselect = (JSONObject) parseArray.get(i);

			if (nameselect.get("name").equals(name) && (long) nameselect.get("patientcount") < 5) {

				flag = 1;
				return true;

			}

		}
		if (flag == 0) {
			return false;
		}

		return false;

	}

	/**
	 * This method will sort the file by popularity
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static void sortBydoctor() {
		new JSONParser();

		JSONObject arrayobject = new JSONObject();

		JSONArray parseArray = Util.getjsonArray(doctorbook, "doctor");
		for (int i = 0; i < parseArray.size() - 1; i++) {
			// JSONObject tempObject=new JSONObject();
			JSONObject nameselect = (JSONObject) parseArray.get(i);
			for (int j = i + 1; j < parseArray.size(); j++) {

				long tempdoctor = (Long) nameselect.get("patientcount");

				JSONObject nameselect1 = (JSONObject) parseArray.get(j);
				long tempdoctor1 = (Long) nameselect1.get("patientcount");
				if (tempdoctor < tempdoctor1) {
					parseArray.set(i, nameselect1);
					parseArray.set(j, nameselect);

				}

			}
		}
		arrayobject.put("doctor", parseArray);
		System.out.println(arrayobject.toJSONString());
		Util.appendFile(arrayobject.toJSONString(), doctorbook);

	}

	/**
	 * This method will print the jsonstring
	 * 
	 * @param filepath
	 * @return
	 * 
	 */
	public static void viewList(String filepath) {
		JSONParser parser = new JSONParser();

		try {
			JSONObject arrayobject = (JSONObject) parser.parse(new FileReader(filepath));

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

	/**
	 * This method will print the doctor file
	 * 
	 * @param
	 * @return
	 * 
	 */

	public static void viewDoctorlist() {
		Util.viewList(doctorbook);
	}

	/**
	 * This method will print the patient file
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static void viewPatientlist() {
		Util.viewList(patientbook);
	}

	/**
	 * This method will print the appointment file
	 * 
	 * @param
	 * @return
	 * 
	 */
	public static void viewAppoitnment() {
		Util.viewList(appointment);

	}

	/**
	 * This method will check if file is empty
	 * 
	 * @param filepath
	 * @return
	 * 
	 */
	public static boolean checkEmpty(String filepath) {
		try {
			BufferedReader breader = new BufferedReader(new FileReader(filepath));
			return breader.readLine() == null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
