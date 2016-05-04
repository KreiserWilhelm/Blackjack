
import java.util.Random;
public class Deck {
	private int topcard;
	private Card[] Deck; 
	public Deck () {
		int cardposition=0; 
		topcard=-1; 
		Deck=new Card[52];
		for (int suitnum=1;suitnum<=4;suitnum++) {
			for (int cardnum=1;cardnum<=13;cardnum++) {
				Deck[cardposition]=new Card(cardnum,suitnum);
				cardposition++;
			}
		}
	}
	public void Shuffle() {
		Card cardholder=new Card(0,0); 
		for (int i=0;i<52;i++) {
			Random rand = new Random();
			int  j = rand.nextInt(51) + 0;
			cardholder=Deck[i]; 
			Deck[i]=Deck[j];
			Deck[j]=cardholder; 
		}
		topcard=-1; 
	}
	public Card Deal() {
		topcard++; 
		return Deck[topcard];
	}
	public String toString() {
		String s=""; 
		for (int i=0;i<52;i++) {
			s+=String.format("%s\n" , Deck[i]); 
		}
		return s; 
	}
	public static void main(String[] args) { 
		 Deck deck=new Deck(); 
		 deck.Shuffle();
		 System.out.print(deck);

	}
	
}
