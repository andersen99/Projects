package com.oracle.MavenTextGame;
import java.util.ArrayList;
public class Player{
	private int hitPoints;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int index;
	public Player() {
		hitPoints = 100;
	}
	public int getItemIndex(String usedItem) {
		index = 0;
		for(Item list : inventory) {
			if(list.getName().equals(usedItem)) return index;
			index ++;
		}
		return -1;
	}
	public int getHP() {
		return hitPoints;
	}
	public void setHP(int newHP) {
		hitPoints = newHP;
	}
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	public void addItem(Item newItem) {
		inventory.add(newItem);
	}
}