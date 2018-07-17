package com.oracle.MavenTextGame;
import java.util.ArrayList;
import java.util.Scanner;
public class Combat implements CombatUI{
	private Scanner read; 
	public boolean playerTurn(Room area,GameState state, Player hero) {
		boolean isInvalid = true, ownsMagicWand = false, ownsHealPotion = false;
		System.out.println("Available Actions:");
		System.out.println("attack");
		ArrayList<Item> itemList = hero.getInventory(); 
		for(Item ownedItem : itemList){
			if(ownedItem != null) {
				if(ownedItem.getName().equals("wand")) {
					ownsMagicWand = true;
					System.out.println("use " + ownedItem.getName());
				}
				else if(ownedItem.getName().equals("potion")) {
					ownsHealPotion = true;
					System.out.println("use " + ownedItem.getName());
				}
			}
		}
		read = new Scanner(System.in);
		
		while(isInvalid) {
			System.out.print("> ");
			String input = read.nextLine();
			Scanner tokenizer = new Scanner(input);
			String word = null;
			if(tokenizer.hasNext()) word = tokenizer.next();
			if(word.equals("attack")) {
				isInvalid = false;
				tokenizer.close();
				if(state.getState().equals("battle")) {
					System.out.println("You swing your sword at the " + area.getEnemy().getName());
					System.out.println("The " + area.getEnemy().getName() + "takes 25 damage!");
					area.getEnemy().setHP(area.getEnemy().getHP() - 25);
					if(area.getEnemy().getHP() <= 0) {
						System.out.println("The " + area.getEnemy().getName() + " has been defeated.");
						System.out.println("You have obtained a " + area.getEnemy().getItemDrop().getName());
						hero.addItem(area.getEnemy().getItemDrop());
						updateRooms(area,area.getEnemy().getName());
						return true;
					}
				}
				else if(state.getState().equals("boss battle")) {
					System.out.println("You swing your sword at the " + area.getBoss().getName());
					System.out.println("The " + area.getBoss().getName() + "takes 25 damage!");
					area.getBoss().setHP(area.getBoss().getHP() - 25);
					if(area.getBoss().getHP() <= 0) {
						System.out.println("The " + area.getBoss().getName() + " has been defeated.");
						return true;
					}
				}
				return false;
			}
			else if(word.equals("use")) {
				if(tokenizer.hasNext() && hero.getInventory().size() > 0) {
				String secondWord = tokenizer.next();
				tokenizer.close();
				if(secondWord.equals("wand") && ownsMagicWand) {
					
					System.out.println("A powerful lighting bolt shoots out of the wand");
					if(state.getState().equals("battle")) {
						System.out.println(area.getEnemy().getName() + " takes 50 damage!");
						area.getEnemy().setHP(area.getEnemy().getHP() - 50);
						if(area.getEnemy().getHP() <= 0) {
							System.out.println("The " + area.getEnemy().getName() + " has been defeated.");
							System.out.println("You have obtained a " + area.getEnemy().getItemDrop().getName());
							hero.addItem(area.getEnemy().getItemDrop());
							updateRooms(area,area.getEnemy().getName());
							return true;
						}
					}
					else if(state.getState().equals("boss battle")) {
						System.out.println(area.getBoss().getName() + " takes 50 damage!");
						area.getBoss().setHP(area.getBoss().getHP() - 50);
						if(area.getBoss().getHP() <= 0) {
							System.out.println("The " + area.getBoss().getName() + " has been defeated.");
							return true;
						}
					}
					return false;
				}
				else if(secondWord.equals("potion") && ownsHealPotion) {
					System.out.println("You " + hero.getInventory().get(hero.getItemIndex("potion")).getEffect());
					hero.setHP(200);
					hero.getInventory().remove(hero.getItemIndex("potion"));
					return false;
				}
				else System.out.println("You do not own that item");
			}
			
		}
		else {
			System.out.println("Invalid Command");
			isInvalid = true;
			}
		}
	
	System.out.println("Input or logic error");
	return true;
}
	public boolean enemyTurn(Room area,GameState state, Player hero){
		if(state.getState().equals("battle")) {
			if(area.getEnemy().getName().equals("Harpy")) {
				System.out.println(area.getEnemy().getName() + " uses " + area.getEnemy().getAttackList()[0]);
				hero.setHP(hero.getHP() - 20);
				System.out.println("You take 20 damage" + " current HP is: " + hero.getHP());
				return isDead(area,hero);
			}
			else if(area.getEnemy().getName().equals("Demon")) {
				if(area.getEnemy().getHP() <= 50) {
					System.out.println(area.getEnemy().getName() + " uses " + area.getEnemy().getAttackList()[1]);
					hero.setHP(hero.getHP() - 40);
					System.out.println("You take 40 damage" + " current HP is: " + hero.getHP());
				}
				else {
					System.out.println(area.getEnemy().getName() + " uses " + area.getEnemy().getAttackList()[0]);
					hero.setHP(hero.getHP() - 25);
					System.out.println("You take 25 damage" + " current HP is: " + hero.getHP());
				}
				return isDead(area,hero);
			}
			else return true;
		}
		else if(state.getState().equals("boss battle")) {
			if(area.getBoss().getHP() <= 75) {
				System.out.println(area.getBoss().getName() + " uses " + area.getBoss().getAttackList()[1]);
				hero.setHP(hero.getHP() - 50);
				System.out.println("You take 50 damage!" + " current HP is: " + hero.getHP());
			}
			else {	
				System.out.println(area.getBoss().getName() + " uses " + area.getBoss().getAttackList()[0]);
				hero.setHP(hero.getHP() - 25);
				System.out.println("You take 25 damage." + " current HP is: " + hero.getHP());		
			}
			return isDead(area,hero);
		}
		return false;
	}
	public boolean startBossBattle(Room area,GameState state, Player hero) {
		boolean isOver = false;
		System.out.println("The " + area.getBoss().getName() + " speaks to you:");
		System.out.println(area.getBoss().getIntro());
		while(!isOver) {
			if(playerTurn(area,state,hero)) isOver = true;
			else if(enemyTurn(area, state, hero)) isOver = true;
		}
		if(area.getBoss().getHP() <= 0) System.out.print(area.getBoss().getOutro());
		else if(hero.getHP() <= 0) System.out.println("Defeated by the wizard");
		return true;
	}
	public boolean isDead(Room area, Player hero) {
		if(hero.getHP() <= 0 && area.getEnemy() != null) {
			System.out.println("You have been defeated by " + area.getEnemy().getName());
			return true;
		}
		else if(hero.getHP() <= 0) return true; 
		else return false;
	}
	public void updateRooms(Room area,String name) {
		area.setEnemy(null);
		if(name.equals("Harpy")) {
			area.setEncounter("a corpse of a harpy");
			area.getExit("hallway").setEncounter("an evil presence upstairs");
			area.getExit("hallway").getExit("outside").setEncounter("an eerie silence");
		}
		else if(name.equals("Demon")) {
			area.setEncounter("a corpse of a demon");
			area.getExit("floor2").setEncounter("a faint magical presence upstairs");
		}
	}
}