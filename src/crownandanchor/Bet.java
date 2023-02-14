package crownandanchor;

public abstract class Bet {
	protected final String FORMAT="%-17s %-10s %7s";
	protected char typeOfBet;
	protected String whatOn;
	protected int amount;
	public Bet(String whatOn,int amount) {
		this('O',whatOn,amount);
	}
	public Bet(char typeOfBet, String whatOn, int amount) {
		this.typeOfBet=typeOfBet;
		this.whatOn=whatOn;
		this.amount=amount;
	}
	
	protected final int countMatches(CADice[] dices) {
		int matches = 0 ;
		for (CADice dice : dices)
			if (dice.getDiceSymbol().equalsIgnoreCase(whatOn))
				matches++;
		return matches;
	}
	
	public abstract String displayBet();
	public abstract int workOutWinnings(CADice[] dices);
	
}
