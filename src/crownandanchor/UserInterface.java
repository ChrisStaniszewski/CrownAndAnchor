package crownandanchor;

import java.util.Scanner;
/**
 * 
 * @author Krzysztof Staniszewski
 * @version 1.0
 *
 */
public class UserInterface {
	private Scanner sc;

	public UserInterface() {
		sc=new Scanner(System.in);
	}
	
	/**
	 * Reads a string from the standard input and checks it against provided pattern
	 * @param prompt message to be displayed on the standard output
	 * @param regexp Pattern to be matched 
	 * @return
	 */
	public String getString(String prompt, String regexp) {
		System.out.print(prompt);
		while (!sc.hasNext(regexp)) {
//			System.out.println(sc.next());
			System.out.println(sc.next() + " is not a valid input - try again!");
		}  
		return sc.next();
	}
	public String getPlayerName() {
    	return getString("Enter your name: ","[a-zA-Z][a-zA-Z_0-9]{3,}");
    }
	
	public char getYesNo(String prompt) {
	   	return getString(prompt,"[YyNn]").toLowerCase().charAt(0);
	    		
	}

	public int getInt(String prompt) {
		System.out.print(prompt);
		while(!sc.hasNextInt()) {
			sc.nextLine();
			System.out.println("Not an integer - try again!");
		}
		return sc.nextInt();
	}
	
	public int getInt(String prompt, int minVal) {
		 int i;	
		 while(!((i=getInt(prompt))>=minVal))
			 System.out.println("Value below minimal - try again!");
		return i;
	}
	
	public int getInt(String prompt, int minVal, int maxVal) {
		 int i;	
		 while(!((i=getInt(String.format("%s [%d..%d] ",prompt,minVal,maxVal)))>=minVal && i<=maxVal) )
			 System.out.println("Value outside the range - try again");
		return i;
	}
	
	
	
	public String getWhatOn() {
		String[] options=new String[CADice.SYMBOLS.length];
		//prepare options texts
		for (int i=0;i<options.length;i++) 
			options[i]=String.format("%d\t%s",i+1,CADice.SYMBOLS[i] );
		displayMenu("Select a symbol to bet on",options);
		return CADice.SYMBOLS[getInt("Your choice: ",1,options.length)-1];
	}
	

	private char getChar(String prompt, char[] allowed) {
        return getString(prompt,"^["+String.valueOf(allowed)+"]$").charAt(0);
	}

	private void displayMenu(String title, String[] options) {
		
		System.out.println(title);
		for (String s:options) {
			System.out.println(s);
		}
		
	}

	public int getBetType() {
		String[] options=new String[Game.BETS.length];
		//prepare options texts
		for (int i=0;i<options.length;i++) 
			options[i]=String.format("%d\t%s",i+1,Game.BETS[i] );
		displayMenu("Select a bet type",options);
		return getInt("Your choice: ",1,options.length);
	}

	public void displayMessage(String message) {
		System.out.println(message);
	}

	public void displayErrorMessage(String message) {
		System.err.println(message);
		
	}

}
