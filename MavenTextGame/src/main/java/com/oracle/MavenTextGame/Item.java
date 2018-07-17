package com.oracle.MavenTextGame;

public class Item{
	private String name;
	private String effect;
	public Item(String newName, String effect) {
		name = newName;
		this.effect = effect;
	}
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}
}