package crownandanchor;

import java.text.NumberFormat;

public class AllIn extends Bet {
	public String displayBet() {
		return String.format( FORMAT,"All In",whatOn, 
				 NumberFormat.getCurrencyInstance().format(amount));
	}
	
	public int workOutWinnings(CADice[] dices) {
		return switch (countMatches(dices)) {
 		case 3 -> 10 * amount;
		default -> 0;
		};
	}
	
	public AllIn(String whatOn, int amount) {
		super('A',whatOn,amount);
		if (amount<3) throw new IllegalArgumentException();

	}
}
