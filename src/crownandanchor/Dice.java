package crownandanchor;

public class Dice {
protected int diceFace;

public Dice(int diceFace) throws IllegalArgumentException {
	if (diceFace<1 || diceFace>6) throw new IllegalArgumentException();
	this.diceFace = diceFace;
}

public Dice() {
	throwDice();
}

public void throwDice() {
  double randomNo=Math.random();
  randomNo*=6;
  randomNo++;
  diceFace=(int) randomNo;
	
}

public int getDiceFace() {
	return diceFace;
}

}
