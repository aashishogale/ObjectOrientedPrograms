package com.bridgelabz.Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Util {
	static Random random;
	static  int array[]=new int[52];
	public static String[] readFile(String filePath) {
		String words[] = {};
		ArrayList<String> lines = new ArrayList<String>();
		String line, temp[] = {};
		BufferedReader bufferedReader;
		FileReader file;
		int index = 0;
		try {
			file = new FileReader(filePath);
			bufferedReader = new BufferedReader(file);
			while ((line = bufferedReader.readLine()) != null) {
				temp = line.split(",|\\s");
				for (int i = 0; i < temp.length; i++) {
					lines.add(temp[i]);

				}
			}
			words = lines.toArray(new String[lines.size()]);
			bufferedReader.close();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return words;
	}

	/** This method appends word on filePath
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

	/**This method write given string on given filePath
	 * @param word     -Array of String to write
	 * @param filePath -File path with file name
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
	public static int generateIndex()
	{
		
		int index=Util.generateRandom(52);
		for(int i=0;i<52;i++) {
			if(array[i]==index) {
				return(generateIndex());
			}
		}
			
				
			
				return index;
					
		
	
	
		
		
	}
	public static void shuffle(String suits[],String rank[]) {
		String player[][]=new String[4][9];
		String randomCard="";
		String deck[]=new String[52];
		int suitSelect=0;
		int rankSelect=0;
		int flag=0;
	int k=0;
			int index=0;
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<13;j++) {
				deck[k++]=suits[i]+"-"+rank[j];
			}
			
		}
	
		for(int i=0;i<4;i++) {
			
	 for(int j=0;j<9;j++)	{
				
				
				index=Util.generateIndex();
			player[i][j]=deck[index];
				
			
			
		}
		}
		for(int i=0;i<4;i++) {
			System.out.print(i+"   player  ");
			for (int j=0;j<9;j++) {
		System.out.print(player[i][j]+" ");
		
			}
			System.out.println("");
		
	}
	}
}
