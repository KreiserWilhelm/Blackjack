
import java.util.Scanner; 
import java.awt.*;
import java.io.File;
public class Gameplay {
	//add betting options
	//add exit option
	//hi zach
      private static File saved=new File ("Casino table.png");
	  private static boolean startaction;
	  private static boolean gameplay; 
	  private static boolean round; 
	  private static boolean playerturn; 
	  private static boolean dealerturn; 
	  private static int mymoney; 
	  private static int bet; 
	  public static void main(String[] args) {
		  StdDraw.setCanvasSize(652, 299);
		  StdDraw.setXscale(0, 2);
		  StdDraw.setYscale(0, 2);
		  StdDraw.picture (1,1, "Casino table.png");
		  StdDraw.setPenColor(StdDraw.WHITE);
		  StdDraw.setPenRadius(.1);
		  gameplay=true; 
		  mymoney=1000; 
		  Scanner scan=new Scanner(System.in); 
		  Deck deck=new Deck();
		  Hand myhand=new Hand();
		  Hand dealerhand=new Hand(); 
		  do {
			  StdDraw.picture (1,1, "Casino table.png");
			  StdDraw.show();
			  myhand.clearHand(); 
			  dealerhand.clearHand();
			  deck.Shuffle();
			  System.out.println("shuffle deck");
			  System.out.println("Your money balance is "+mymoney+" dollars.");
			  System.out.println("Place your bet, it must be a multiple of 10.");
			  System.out.println("You cannot bet more than your money balance.");
			  bet=scan.nextInt(); 
			  while (bet%10!=0 || bet>mymoney) {
				  System.out.println("Redo your bet, it must be a multiply of 10.");
				  System.out.println("You cannot bet more than your money balance");
				  bet=scan.nextInt(); 
			  }
			  round=true;
			  startaction=true; 
			  playerturn=true;
			  dealerturn=false; 
		  while (round) {
		  System.out.println("new round");
		  myhand.dealCardToHand(deck.Deal());
		  myhand.dealCardToHand(deck.Deal());
		  dealerhand.dealCardToHand(deck.Deal());
		  dealerhand.dealCardToHand(deck.Deal());
		  System.out.println("my hand: \n"+myhand);
		  StdDraw.picture(.1,.35,"src/"+myhand.toStringNext(0)+".png"); 
		  StdDraw.picture(.2,.35,"src/"+myhand.toStringNext(1)+".png"); 
		  StdDraw.picture(1,1.65,"src/"+dealerhand.toStringNext(0)+".png"); 
		  StdDraw.picture(1.1,1.65,"src/BACK.png");
		  System.out.println("dealer hand: \n"+dealerhand.toDealerString()); 
		  if (myhand.blackjack() && dealerhand.blackjack()){
			  System.out.println("You push!");
			  showText("You Push!");
			  round=false; 
			  startaction=false; 
			  playerturn=false; 
			  //System.exit(1);
		  }
		  else if (myhand.blackjack() ){
			  System.out.println("Blackjack!");
			  showText("Blackjack!");
			  round=false; 
			  startaction=false; 
			  playerturn=false;
			  mymoney=mymoney+(int) (1.5*bet); 
			  //System.exit(1);                //TODO:Change these from exit 
		  }
		  else if (dealerhand.blackjack()){
			  System.out.println("Dealer has blackjack! You lose!");
			  showText("Dealer has blackjack! You lose!");
			 
			  round=false; 
			  startaction=false; 
			  playerturn=false; 
			  mymoney=mymoney-bet; 
			  //System.exit(1);
		  }
		  while (startaction) {
	     System.out.println("startaction");
	     playerturn = true;
	     dealerturn=false; 
	     String decision; 
	     int n=2; 
	     int d=2; 
		 while (playerturn){
			 System.out.println();
			 System.out.println("Hit or Stay"); 
			 decision = scan.next();
		  if (decision.equals("Hit")) {
			  myhand.dealCardToHand(deck.Deal());
			  System.out.print("my hand:"+myhand); 
			  System.out.print("Your count is " + myhand.getHandCount());
			  StdDraw.picture(.2+.1*(n-1),.35,"src/"+myhand.toStringNext(n)+".png");
			  StdDraw.show();
			  n++; 
			  showText("Your count is " + myhand.getHandCount());
			  if (myhand.bust()) {
				  System.out.println();
				  System.out.println("You Bust!");
                  showText("You Bust!");
                  
				  round=false;
				  startaction=false; 
				  playerturn=false;
				  dealerturn=false; 
				  mymoney=mymoney-bet; 
			  }
		  }
		 
		  else if (decision.equals("Stay")) {
			 
			 // StdDraw.save("src/Current table.png");
			  System.out.println("You chose to stay");
			//  showText("You chose to stay"); 
			  StdDraw.picture(1,1.65,"src/"+dealerhand.toStringNext(0)+".png"); 
			  StdDraw.picture(1.1,1.65,"src/"+dealerhand.toStringNext(1)+".png"); 
			  //StdDraw.show(0);
			  System.out.println("Dealer's hand: " + dealerhand.toString());
			  playerturn = false;
			  dealerturn=true; 
		  }
	      }
		 while (dealerturn) {
		  if (dealerhand.getHandCount()<17) {	  
			  dealerhand.dealCardToHand(deck.Deal());
			  try {
				    Thread.sleep(1000);                 //1000 milliseconds is 1 second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			  System.out.println("Dealer hits. New dealerhand: \n"+dealerhand);
			  StdDraw.picture(1+.1*(d),1.65,"src/"+dealerhand.toStringNext(d)+".png"); 
			  d++; 
			  System.out.println("Dealer's count is " + dealerhand.getHandCount());
			  if (dealerhand.bust()) {
				  System.out.print("Dealer Busts! You Win");
				  showText("Dealer Busts! You Win");
				  StdDraw.clear(); 
				  round=false;
				  startaction=false;
				  dealerturn=false; 
				  mymoney=mymoney+bet; 
				  //System.exit(1); 

		  }
		  }
		  if (dealerhand.getHandCount()>=17 && !myhand.bust() && !dealerhand.bust()) {
			  System.out.println("Dealer stays"); 
			  showText("Dearl stays");
			  StdDraw.clear(); 
			  dealerturn=false; 
			  
		  }
		  }
		 if(myhand.getHandCount() == dealerhand.getHandCount() && !myhand.bust() && !dealerhand.bust()){
			  System.out.println("You push");
			  showText("You push");
			  StdDraw.clear(); 
			  round=false; 
			  startaction=false; 
			  dealerturn=false; 
			  //System.exit(1);                                     //TODO:Change to money
		  }
		else if(myhand.getHandCount() > dealerhand.getHandCount() &&!myhand.bust()&& !myhand.bust() && !dealerhand.bust()){
			  System.out.println("You win!");
			  showText("You win");
			  StdDraw.clear(); 
			  mymoney=mymoney+bet; 
			  round=false; 
			  startaction=false; 
			  dealerturn=false; 
			  //System.exit(1);									//TODO: Change to money
		}
		 
		 else if (myhand.getHandCount() < dealerhand.getHandCount() && !myhand.bust() && !dealerhand.bust()){
			  System.out.println("You lose");
			  showText("You lose!");
			  StdDraw.clear(); 
			  mymoney=mymoney-bet; 
			  round=false;
			  startaction=false; 
			  dealerturn=false; 
			  //System.exit(1);									//TODO: Change to money
		  }
		  
		  
		  }
	}
		  System.out.println("You now have:"+mymoney);
		  if (mymoney<=0) {
			  System.out.println("Game Over");
			  showText("Game Over");
			  StdDraw.clear(); 
			  gameplay=false; 
		  }
		  else {
		  StdDraw.picture(1, 1, "src/Casino table.png");
	      System.out.print("Do you wish to continue: Yes or No?");
		  showText("Do you wish to continue: Yes or No?"); 
		  String answer=scan.next(); 
		  if (answer.equals("No")) {
			  gameplay=false; 
		  }
		  }
}   while (gameplay); 		  
}
	  public static void showText(String s)  { 
	  StdDraw.save("src/Current table.png");
	  StdDraw.text(1, .9, s);
	  StdDraw.show(2000);
	  StdDraw.clear();
	  StdDraw.picture(1, 1, "src/Current table.png"); 
	  }
}
