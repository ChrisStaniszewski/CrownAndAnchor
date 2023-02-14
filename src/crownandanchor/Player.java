package crownandanchor;

import java.io.Serializable;

public class Player implements Serializable {
	private String name;
	private int stake;
	private int banker;
	public Player(String name,int stake) {
		if (stake<0) throw new IllegalArgumentException("Stake less than 0");
		if (name==null||name.isBlank()) throw new IllegalArgumentException("Illegal name value");
		this.name=name;
		this.stake=stake;
		this.banker=100;
	}
	public void incStake(int amount) throws IllegalArgumentException {
		if (amount<0) throw new IllegalArgumentException("Amount less than 0");
		stake+=amount;
	}
	
	public void decStake(int amount) throws IllegalArgumentException {
		if (amount<0||amount>stake) throw new IllegalArgumentException();
        stake-=amount; 
	}
	public void incBanker(int amount) throws IllegalArgumentException{
		if (amount<0) throw new IllegalArgumentException("Amount less than 0");
		banker+=amount;
	}
	public void decBanker(int amount) throws IllegalArgumentException {
		if (amount<0||amount>banker) throw new IllegalArgumentException();
		banker-=amount;
		
	}
	
	public int getStake() {
		return stake;
	}
	
	public int getBanker() {
		return banker;
	}
	
	public String getName() {
		return name;
	}
	

}
