package com.bridgelabz.Programs;

import com.bridgelabz.Utility.Util;

public class DeckOfCards {
	public static void main(String args[]) {
	String []suits={"Clubs", "Diamonds", "Hearts", "Spades"};
	String []rank={"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
   Util.shuffle(suits, rank);
}
}
