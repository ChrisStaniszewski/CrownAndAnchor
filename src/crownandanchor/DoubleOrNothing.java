package crownandanchor;

import java.text.NumberFormat;

public class DoubleOrNothing extends Bet {
	public String displayBet() {
			return String.format( FORMAT,"Double or Nothing",whatOn, 
					 NumberFormat.getCurrencyInstance().format(amount));
	}
	
	public int workOutWinnings(CADice[] dices) {
		return switch (countMatches(dices)) {
		case 2,3 -> 5 * amount;
		default -> 0;
	};
	}
	
	public DoubleOrNothing(String whatOn, int amount) {
		super('D',whatOn,amount);
		if (amount<2) throw new IllegalArgumentException();

	}
}
