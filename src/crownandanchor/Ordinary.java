package crownandanchor;

import java.text.NumberFormat;

public class Ordinary extends Bet {

	public String displayBet() {
		return String.format(FORMAT,"Ordinary",whatOn, 
				 NumberFormat.getCurrencyInstance().format(amount));
	}

	public int workOutWinnings(CADice[] dices) {

		return switch (countMatches(dices)) {
					case 1 -> 2 * amount;
					case 2 -> 3 * amount;
					case 3 -> 4 * amount;
					default -> 0;
				};
	}

	public Ordinary(String whatOn, int amount) {
		super(whatOn, amount);
		if (amount<1) throw new IllegalArgumentException();

	}
}
