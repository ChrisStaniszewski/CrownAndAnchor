package crownandanchor;

import java.util.ArrayList;
import java.io.*;
import java.text.NumberFormat;
/**
 * Implements logic of the Crown and Anchor dice game 
 * @author Krzysztof Staniszewski
 * @version 1.0
 */
public class Game {
	/**
	 * Names of the bet types 
	 */
	public static final String[] BETS= {"Ordinary","Double or Nothing","All In"};
	private CADice[] dices;
	private  Player aPlayer;
	private ArrayList<Bet> bets;
	private UserInterface ui;
	
	/*
	 * Saves state of the player to the file
	 * @throws IOException 
	 * @param player object of the Player class to be serialised
	 */
	private void saveGame(Player player) {
		File f = new File(player.getName().toLowerCase()+".ser");
		ui.displayMessage("Saving player state to file "+f.getName());
		try (ObjectOutputStream oo=new ObjectOutputStream(new FileOutputStream(f)))
		{
			oo.writeObject(player);
			ui.displayMessage("Player state saved sucsessfully" );
			
		}

		catch(IOException e) {
			ui.displayErrorMessage(e.getMessage());
		}
	}
	
	
	private Player restorePlayerFromFile(File f) {
		Player player=null;
		ui.displayMessage("Restoring player state from file "+f.getName());
		try (ObjectInputStream oi=new ObjectInputStream(new FileInputStream(f))){
			player=(Player) oi.readObject();
			ui.displayMessage("Player  restored successfully" );

		}

		catch (ClassNotFoundException e) {
			ui.displayErrorMessage(e.getMessage());
		}

		catch (IOException e) {
			ui.displayErrorMessage(e.getMessage());
		}

		return player;
	}
	
	private Player restartGame() {
		Player player=null;
		String playerName=ui.getPlayerName();

		File f=new File(playerName+".ser");
		if (f.exists()&&ui.getYesNo("Do you want to restore the previously saved game?")=='y') 
				player=restorePlayerFromFile(f);
		
		if (player==null) 
			//player was not restored, so get the stake amount and create a new Player object 
			player = new Player(playerName,ui.getInt("How much do you want to deposit? ",0));
		return player;
	}
	
	/**
	 * Runs the game session
	 */
	public void play() {
		aPlayer=restartGame();
		
		boolean bankruptcy;
		ui.displayMessage("Initial balance:");
		displayBalance();
        do {
        	placeBets();
            rollDices();
            workOutWinnings();
            displayBalance();
            bankruptcy=aPlayer.getBanker()==0 || aPlayer.getStake()==0;
        } while(!bankruptcy && getEndRoundOption()=='y');
        if (!bankruptcy) saveGame(aPlayer);
//        displayBalance();
        if (aPlayer.getStake()==0) ui.displayMessage("Player bankrupted");
        else  if (aPlayer.getBanker()==0) ui.displayMessage("Banker bankrupted");
        		
        ui.displayMessage("Game over");
	
	}

	private void displayBets() {
		ui.displayMessage("Current bets");
		ui.displayMessage("=======================================");
		for (Bet b:bets) ui.displayMessage(b.displayBet());
		ui.displayMessage("=======================================");
	}

	private void displayBalance() {
		ui.displayMessage(String.format("Player: %7s Banker %7s",
				NumberFormat.getCurrencyInstance().format(aPlayer.getStake()),
				NumberFormat.getCurrencyInstance().format(aPlayer.getBanker())
				));
	}
	private void placeBets() {
		bets.clear();
		Bet bet=null;
		do {
			
			int bt=ui.getBetType();
			int minBet=bt;
			String whatOn=ui.getWhatOn();
			if (aPlayer.getStake()>=minBet) {
			int amount=ui.getInt("How much do you want to bet? ",minBet,aPlayer.getStake());
			StringBuilder sb = new StringBuilder("Place ");
			sb.append(NumberFormat.getCurrencyInstance().format(amount));
			sb.append(" ");
			sb.append(BETS[bt-1]+" bet on ");
			sb.append(whatOn);
			sb.append("? [Y/N]");
			if (ui.getYesNo(sb.toString())=='y'){
				bet= switch (bt) {
				case 2 -> new DoubleOrNothing(whatOn,amount);
				case 3 -> new AllIn(whatOn,amount);
				default ->new Ordinary(whatOn,amount);
				};
				bets.add(bet);
				aPlayer.decStake(amount);
				aPlayer.incBanker(amount);
			}
			}else {
				ui.displayMessage("You don't have enough cash to make this type of bet");
			}
			displayBets();
			displayBalance();
		} while (bets.size()==0 ||aPlayer.getStake()>0&&ui.getYesNo("Do you want to place another bet? [Y/N] ")=='y');
		
			
		
		
	}
	private void rollDices() {
		StringBuilder sb = new StringBuilder();
		ui.displayMessage("Rolling dices ...");
		for (CADice d:dices) d.throwDice();
		ui.displayMessage("Rolled symbols :");
		for (CADice d:dices) {sb.append(d.getDiceSymbol());sb.append(" ");}
		ui.displayMessage(sb.toString());
		ui.displayMessage("=============================");
	}

	private void workOutWinnings() {
		
		int total=0;
		for (Bet b:bets) {
          int winning=b.workOutWinnings(dices);
          total+=winning;
          ui.displayMessage(String.format("%s winning: %s",b.displayBet(),
        		          NumberFormat.getCurrencyInstance().format(winning)));
		}
		
		aPlayer.incStake(total);
		aPlayer.decBanker(total);
		
		
	}
	private char getEndRoundOption() {
		char answer=ui.getYesNo("Do you want to play again? [y/n]");
        if (answer=='y') updatePlayerStake();
		return answer;
	}
	
	private void updatePlayerStake() {
		aPlayer.incStake(ui.getInt("How much do you want to add to your stake? ", 0));
	}
	
	/**
	 * Creates new game object
	 */
	public Game() {
		ui=new UserInterface();
		dices = new CADice[3];
		for (int i=0;i<dices.length;i++) dices[i]=new CADice();
		aPlayer=null;
		bets=new ArrayList<Bet>();
	}
}
