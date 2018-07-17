package com.oracle.MavenTextGame;
import java.util.HashMap;
import java.util.Set;

public class Room {
	private String description;
	private String encounter;
	private HashMap<String, Room> exits;
	private StandardEnemy monster;
	private BossEnemy boss;

	public Room(String description, String encounter,StandardEnemy newEnemy, BossEnemy newBoss) {
		this.description = description;
		this.encounter = encounter;
		monster = newEnemy;
		boss = newBoss;
		exits = new HashMap<>();
	}
	public StandardEnemy getEnemy() {
		return monster;
	}
	public BossEnemy getBoss() {
		return boss;
	}
	public String getEncounter() {
		return encounter;
	}
	public Room getExit(String direction) {
		return exits.get(direction);
	}

	private String getExitString() {
		String returnString = "Exits:";
		Set<String> keys = exits.keySet();
		for (String exit : keys) {
			returnString += " " + exit;
		}
		return returnString;
	}

	public String getLongDescription() {
		return "\nYou are " + description + ".\n" + "There is " + encounter + ".\n" + getExitString();
	}

	public String getShortDescription() {
		return description;
	}
	public void setExits(String direction, Room neighbor)
	{
		exits.put(direction, neighbor);
	}
	public void setEncounter(String encounter) {
		this.encounter = encounter;
	}
	public void setEnemy(StandardEnemy monster) {
		this.monster = monster;
	}
	public void setBoss(BossEnemy boss) {
		this.boss = boss;
	}
}