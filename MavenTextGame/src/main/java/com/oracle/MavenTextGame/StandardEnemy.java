package com.oracle.MavenTextGame;
//import java.util.ArrayList;

public class StandardEnemy extends Enemy{
	private Item itemDrop;
	public StandardEnemy() {
		super();
	}
	public StandardEnemy(int maxHP, String[] attacks,String newName, Item newItem) {
		super(maxHP, attacks, newName);
		itemDrop = newItem;
	}
	public String getName() {
		return super.getName();
	}
	public void setName(String newName) {
		super.setName(newName);
	}
	public String[] getAttackList(){
		return super.getAttackList();
	}
	public Item getItemDrop() {
		return itemDrop;
	}
	public void setItemDrop(Item item) {
		itemDrop = item;
	}
}