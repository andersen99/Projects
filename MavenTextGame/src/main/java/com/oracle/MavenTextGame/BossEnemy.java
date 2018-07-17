package com.oracle.MavenTextGame;
public class BossEnemy extends Enemy{
	private String introduction;
	private String victory;
	public BossEnemy() {
		super();
	}
	public BossEnemy(int maxHP, String[] attacks,String newName, String intro, String outro) {
		super(maxHP, attacks, newName);
		introduction = intro;
		victory = outro;
	}
	public String getName() {
		return super.getName();
	}
	public String getIntro() {
		return introduction;
	}
	public String getOutro() {
		return victory;
	}
	public void setName(String newName) {
		super.setName(newName);
	}
	public String[] getAttackList(){
		return super.getAttackList();
	}
}