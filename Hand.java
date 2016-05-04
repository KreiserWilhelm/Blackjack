
public class Hand {
private Card[] Hand; 
private int nextCardPosition;
private int numOfAces; 

public Hand () { //maximum amount of cards in a hand is 12 because 4 aces, 4 2's, 4 3's =24, at this point you would automatically lose the hand and not receive any further cards
	Hand=new Card[12]; //an  ArrayList, creates new entry and attaches it to the list, allow you very easily add new elements
	nextCardPosition = 0;
}
public void dealCardToHand(Card newcard) { 
	Hand[nextCardPosition]=newcard; 
	nextCardPosition++; 
}
public int getHandCount() {
	numOfAces=0; 
	int handcount=0; 
	for (int i=0;i<=(nextCardPosition-1);i++) {
		handcount+=Hand[i].getValue();
		if (Hand[i].getCardString().equals("ACE")){
			numOfAces++;  
		}
	}
	for (int i=0;i<=3;i++) {
	if (numOfAces>i&&handcount>21) { // to account for ace taking a value of 1 instead of 11
		handcount=handcount-10; 
	}
	}
	return handcount; 
}
public boolean bust() {
	if (this.getHandCount()>21) {
		return true; 
	}
	return false; 
}
public boolean blackjack() {
	if (this.getHandCount()==21&&nextCardPosition==2) {
		return true; 
	}
	return false; 
}
public void clearHand() {
	for (int i=0;i<=11;i++) {
		Hand[i]=null; 
	}
	nextCardPosition = 0;
}
public String toDealerString () {
	String s=String.format("Mystery Card %s", Hand[1]); 
	return s; 
}

public String toString () {
	String s=""; 
	for (int i=0;i<12;i++) {
		if (Hand[i]!=null) {
		s += String.format("%s\n" , Hand[i]);
		}
	} 
	return s; 
	//add all the cards with spaces
	//array might have a toString method defined
}
public String toStringNext (int n) {
	String s=""+Hand[n]; 
	return s; 
}



}
