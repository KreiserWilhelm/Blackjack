import java.util.Scanner; 
import java.awt.*;
import java.io.File;
public class BlackJack {
	//add betting options
	//add exit option
      private static File saved=new File ("Casino table.png");
	  private static boolean startaction;
	  private static boolean gameplay; 
	  private static boolean round; 
	  private static boolean playerturn; 
	  private static boolean dealerturn; 
	  private static int mymoney; 
	  private static int bet; 
	  boolean hit=false; 
	  boolean stay=false; 
	  public static void main(String[] args) {
		  StdDraw.setCanvasSize(652, 299);
		  StdDraw.setXscale(0, 2);
		  StdDraw.setYscale(0, 2);
		  StdDraw.picture (1,1, "Casino table.png");
		  StdDraw.setPenColor(StdDraw.WHITE);
		  StdDraw.setPenRadius(.07);
		  
		

		  gameplay=true; 
		  mymoney=1000; 
		  Scanner scan=new Scanner(System.in); 
		  Deck deck=new Deck();
		  Hand myhand=new Hand();
		  Hand dealerhand=new Hand(); 
		  do {
			  StdDraw.picture (1,1, "Casino table.png");
			  StdDraw.picture(.15, 1.9, "src/textbox2.png");
			 
			  bet=0; 
			  showMoney();
			  myhand.clearHand(); 
			  dealerhand.clearHand();
			  deck.Shuffle();
			  System.out.println("shuffle deck");
			  System.out.println("Your money balance is "+mymoney+" dollars.");
			  System.out.println("Place your bet, it must be a multiple of 10.");
			  System.out.println("You cannot bet more than your money balance.");
			
			  
			 StdDraw.picture (1,1, "Casino table.png");
			  StdDraw.picture(.15, 1.9, "src/textbox2.png");	
			  StdDraw.text(1.82, 1.92,"Balance:"+(mymoney-bet));
			  StdDraw.text(.18, 1.9, "Place your bet!"); 
			  StdDraw.setPenColor(StdDraw.BLACK); 
			  StdDraw.filledRectangle(1.9, 1.3, .1, .1);
			  StdDraw.filledRectangle(1.9, 1.6, .1, .1);
			  StdDraw.filledRectangle(1.9, .7, .1, .1);
			  StdDraw.filledRectangle(1.9,  1, .1, .1);

			  StdDraw.setPenColor(StdDraw.WHITE);
			  StdDraw.text(1.9, 1.6, "10");
			  StdDraw.text(1.9, 1.3, "50");
			  StdDraw.text(1.9, .7, "All In");
			  StdDraw.text(1.9,  1, "200");

			  
			  
			  
			while (bet != 10 && bet != 200 && bet != 50 && bet != mymoney){
				double x=0; 
		          double y=0; 
				  if (StdDraw.mousePressed()) {
					 x=StdDraw.mouseX(); 
					 y=StdDraw.mouseY(); 
				  }
				if (x<=2&&x>=1.8&& y>=1.2 &&y<=1.4){
				  bet = 50;
			  }
			 
			  else if (x<=2&&x>=1.8 && y>= 1.5 && y<= 1.7 ) {
				  bet = 10;
			  }
			  
			  else if (x <= 2 && x >= 1.8 && y >= .6 && y <= .8){
				  bet = mymoney;
			  }
			  
			  else if ((x <= 2 && x >= 1.8 && y >= .9 && y <= 1.1)){
				  bet = 200;
			  }
			}
			StdDraw.picture(1.9, .7, "src/box.png");
			StdDraw.picture(1.9, 1, "src/box.png");
			StdDraw.picture(1.9, 1.3, "src/box.png");
			StdDraw.picture(1.9, 1.6, "src/box.png");
			 // StdDraw.picture(1.9, .7, "src/textbox22.png");
			 // StdDraw.picture(1.9, 1, "src/textbox2.png");
			 //  bet=scan.nextInt();
			// scan.nextLine();
			 /* while (bet%10!=0 || bet>mymoney) {
				  System.out.println("Redo your bet, it must be a multiply of 10.");
				  System.out.println("You cannot bet more than your money balance");
				  bet=scan.nextInt(); 
				  scan.nextLine();
			  }*/
			
			  showMoney(); 
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
		  StdDraw.show(500);
		  StdDraw.show();
		  StdDraw.picture(.2,.35,"src/"+myhand.toStringNext(1)+".png"); 
		  StdDraw.show(500);
		  StdDraw.show();
		  StdDraw.picture(1,1.65,"src/"+dealerhand.toStringNext(0)+".png"); 
		  StdDraw.show(500);
		  StdDraw.show();
		  StdDraw.picture(1.1,1.65,"src/BACK.png");
		  StdDraw.show(500);
		  StdDraw.show();
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
			  StdDraw.picture(1,1.65,"src/"+dealerhand.toStringNext(0)+".png"); 
			  StdDraw.picture(1.1,1.65,"src/"+dealerhand.toStringNext(1)+".png"); 
			  showText("Dealer has blackjack!");
			  showText("You lose!");
			 
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
		 while (playerturn) {
			 //System.out.println("Hit or Stay"); 
			StdDraw.picture(.15, 1.9, "src/textbox2.png");
			 StdDraw.text(.18, 1.9, "Hit or Stay?");
			  StdDraw.setPenColor(StdDraw.BLACK); 
			  StdDraw.filledRectangle(1.9, .1, .1, .1);
			  StdDraw.setPenColor(StdDraw.WHITE);
			  StdDraw.text(1.9, .1, "Hit");
			  StdDraw.setPenColor(StdDraw.BLACK); 
			  StdDraw.filledRectangle(1.9, .4, .1, .1);
			  StdDraw.setPenColor(StdDraw.WHITE);
			  StdDraw.text(1.9, .4, "Stay");
			 
			 
			 StdDraw.show(5);
			 // showText("Hit or Stay?");
			 //decision = scan.nextLine();
		
          double x=0; 
          double y=0; 
		  if (StdDraw.mousePressed()) {
			 x=StdDraw.mouseX(); 
			 y=StdDraw.mouseY(); 
		  }
		  
		  if (x<=2&&x>=1.8&&y>=0&&y<=.2) {     //TODO:
			  myhand.dealCardToHand(deck.Deal());
			  System.out.print("my hand:"+myhand); 
			  System.out.print("Your count is " + myhand.getHandCount());
			  StdDraw.picture(.2+.1*(n-1),.35,"src/"+myhand.toStringNext(n)+".png");
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
		 
		  else if (x<=2&&x>=1.8&&y>=.3&&y<=.5) {
			 
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
	      StdDraw.show(500);
	      StdDraw.show();
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
				  showText("Dealer Busts!");
				  round=false;
				  startaction=false;
				  dealerturn=false; 
				  mymoney=mymoney+bet; 
				  //System.exit(1); 

		  }
		  }
		  if (dealerhand.getHandCount()>=17 && !myhand.bust() && !dealerhand.bust()) {
			  System.out.println("Dealer stays"); 
			  showText("Dealer stays"); 
			  dealerturn=false; 
			  
		  }
		  }
		 if(myhand.getHandCount() == dealerhand.getHandCount() && !myhand.bust() && !dealerhand.bust()){
			  System.out.println("You push");
			  showText("You push");
			  round=false; 
			  startaction=false; 
			  dealerturn=false; 
			  //System.exit(1);                                     //TODO:Change to money
		  }
		else if(myhand.getHandCount() > dealerhand.getHandCount() &&!myhand.bust()&& !myhand.bust() && !dealerhand.bust()){
			  System.out.println("You win!");
			  showText("You win"); 
			  mymoney=mymoney+bet; 
			  round=false; 
			  startaction=false; 
			  dealerturn=false; 
			  //System.exit(1);									//TODO: Change to money
		}
		 
		 else if (myhand.getHandCount() < dealerhand.getHandCount() && !myhand.bust() && !dealerhand.bust()){
			  System.out.println("You lose");
			  showText("You lose!");
			  mymoney=mymoney-bet; 
			  round=false;
			  startaction=false; 
			  dealerturn=false; 
			  //System.exit(1);									//TODO: Change to money
		  }
		  
		  
		  }
	}
		 
		  System.out.println("You now have:"+mymoney);
		  showMoney(); 
		  if (mymoney<=0) {
			  bet=0; 
			  System.out.println("Game Over");
			  showMoney(); 
			  showText("Game Over"); 
			  gameplay=false; 
		  }
		  else {
		 while(!StdDraw.mousePressed()){
		  bet=0; 
		  StdDraw.show(0);
		  StdDraw.picture(.15, 1.9, "src/textbox2.png");
		  StdDraw.setPenColor(StdDraw.BLACK); 
		  StdDraw.filledRectangle(1.9, .1, .1, .1);
		  StdDraw.setPenColor(StdDraw.WHITE);
		  StdDraw.text(1.9, .1, "No");
		  StdDraw.setPenColor(StdDraw.BLACK); 
		  StdDraw.filledRectangle(1.9, .4, .1, .1);
		  StdDraw.setPenColor(StdDraw.WHITE);
		  StdDraw.text(1.9, .4, "Yes");
		 StdDraw.text(.15, 1.9, "Continue?"); 
		 StdDraw.show(5);
		  
		  //StdDraw.picture(1, 1, "src/Casino table.png");
	     // System.out.print("Do you wish to continue: Yes or No?");
	      showMoney(); 
		 
		 //StdDraw.show(5);
	      //showText ("Continue: Yes or No?");
		  
		
		 
		 double  x=0; 
        double   y=0; 
		  if (StdDraw.mousePressed()) {
			 x=StdDraw.mouseX(); 
			 y=StdDraw.mouseY(); 
		  }
		  
		  if (x<=2&&x>=1.8&&y>=0&&y<=.2) {
		  
		 // String answer=scan.nextLine(); 
		  //if (answer.equals("No")) {
			  gameplay=false; 
			  StdDraw.picture(.15, 1.9, "src/textbox2.png");
			  StdDraw.text(.18, 1.9,"Game Over"); 
		  }
		 }
		  }
}   while (gameplay); 		  
}
	  public static void showText(String s)  { 
      StdDraw.picture(.15, 1.9, "src/textbox2.png");
	  StdDraw.text(.18, 1.9,s); 
	  StdDraw.show(2000);
	  StdDraw.show();
	  }
	  public static void showMoney()  { 
	     //StdDraw.show();
		  StdDraw.picture(1.85, 1.9, "src/textbox2.png");
		  StdDraw.text(1.82, 1.92,"Balance:"+(mymoney-bet));
		  StdDraw.text(1.82, 1.80,"Bet:"+bet);
		  StdDraw.show();
		  }
}
