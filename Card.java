import java.util.HashMap;

public class Card {
private String suit;
private String cardstring; 
private final int suitnum; 
private int value;
private final int cardnum; 
private HashMap<Integer, String> map; //creates a key, use a hash function to determine whether or not this key corresponds to button, does it almost intantaneously

public Card (int cardnum,int suitnum) {
	this.cardnum=cardnum; 
	this.suitnum=suitnum; 
/*	map.put(1, "ACE");
	map.get(1); this is also an alternative
	[] create array of stirng, use index to match/map values with strings*/ 
}
public int getSuitNum() {
	return suitnum; 
}
public String getSuit() {
	suit="ERROR NO SUIT FOUND";
	if (suitnum==1){
	suit="CLUBS"; 
	}
	if (suitnum==2){
	suit="DIAMONDS";
	}
	if (suitnum==3){
	suit="HEARTS";
	}
	if (suitnum==4) {
	suit="SPADES";
	}
	return suit; 
}
public int getValue(){
	value=0;
	if (cardnum==1) {
		value=11;;
	}
	if (cardnum>=2 && cardnum<=10) {
		value=cardnum;
	}
	if (cardnum>=11&&cardnum<=13) {
		value=10;
	}
	return value; 
}
public String getCardString() {
	cardstring="ERROR with cardString method";
	if (cardnum==1) {
		cardstring="ACE";
	}
	if (cardnum==2) {
		cardstring="TWO";
	}
	if (cardnum==3) {
		cardstring="THREE";
	}
	if (cardnum==4) {
		cardstring="FOUR";
	}
	if (cardnum==5) {
		cardstring="FIVE";
	}
	if (cardnum==6) {
		cardstring="SIX";
	}
	if (cardnum==7) {
		cardstring="SEVEN";
	}
	if (cardnum==8) {
		cardstring="EIGHT";
	}
	if (cardnum==9) {
		cardstring="NINE";
	}
	if (cardnum==10) {
		cardstring="TEN";
	}
	if (cardnum==11) {
		cardstring="JACK";
	}
	if (cardnum==12) {
		cardstring="QUEEN";
	}
	if (cardnum==13) {
		cardstring="KING";
	}
	String nameofcard=cardstring; 
	return nameofcard; 
}
public String toString() {
	String s=getCardString() + " of " +getSuit(); 
	return s; 
}
}

