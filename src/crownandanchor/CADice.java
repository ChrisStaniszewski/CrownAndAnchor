package crownandanchor;

public class CADice extends Dice {
	public static final String[] SYMBOLS= {"Crown","Anchor","Diamond","Spade","Club","Heart"};
	public String getDiceSymbol() {
		return SYMBOLS[diceFace-1];
	}
	public CADice(int diceFace) {
		super(diceFace);
	}
	
	public CADice() {
	}
}
