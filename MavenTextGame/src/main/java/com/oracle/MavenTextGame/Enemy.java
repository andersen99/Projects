package com.oracle.MavenTextGame;

public class Enemy{
	private int hitPoints;
	private String name;
	private String[] attackList;
	public Enemy() {
		
	}
	public Enemy(int maxHP, String[] attacks, String newName) {
		hitPoints = maxHP;
		name = newName;
		attackList = attacks;
	}
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	public int getHP() {
		return hitPoints;
	}
	public void setHP(int newHP) {
		hitPoints = newHP;
	}
	public String[] getAttackList() {
		return attackList;
	}
	public void setAttackList(String[] attacks) {
		attackList = attacks;
	}
}