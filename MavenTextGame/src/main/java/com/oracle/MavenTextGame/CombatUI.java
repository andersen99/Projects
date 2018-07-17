package com.oracle.MavenTextGame;
public interface CombatUI{
	public boolean playerTurn(Room area,GameState state, Player hero);
	public boolean enemyTurn(Room area,GameState state, Player hero);
	public boolean startBossBattle(Room area,GameState state, Player hero);
	public boolean isDead(Room area, Player hero);
	public void updateRooms(Room area,String name);
}