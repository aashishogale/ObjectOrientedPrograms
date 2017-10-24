package com.bridgelabz.programs;

import com.bridgelabz.utility.Util;

/***************************************************************************
 * Purpose : To create class for shuffling deck of cards
 *
 * @author Aashish
 * @version 1.0
 * @since 14-10-2017
 ****************************************************************************/
public class DeckOfCards {
	public static void main(String args[]) {
	String []suits={"Clubs", "Diamonds", "Hearts", "Spades"};
	String []rank={"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
   Util.shuffle(suits, rank);
}
}
