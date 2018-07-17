package com.oracle.MavenTextGame;
public class GameState{
    private String state;
    public GameState(){
    	state = "exploring";
    }
    public String getState() {
    	return state;
    }
	public void enterCombat(Room area){
		if(area.getEnemy() != null) {
			state = "battle";
			System.out.println("Entering combat");
		}
		else if(area.getBoss() != null) {
			state = "boss battle";
			System.out.println("Entering boss battle");
		}
		else System.out.println("There are no enemies to fight");
	}
	public void endCombat(){
		state = "exploring";
	}
}