package com.oracle.MavenTextGame;
import com.oracle.util.ConnectionUtil;

import java.io.IOException;
import java.sql.*;
public class App {
	public static void main(String[] args) {
		App game = new App();
		game.play();
	}
	private Parser parser;
	private Room currentRoom;

	public App() {
		createRooms();
		parser = new Parser();
	}

	private void createRooms() {
		Room outside, hallway, greenHouse, secondFloor, bedroom, thirdFloor, library;
		//Declare Connection and Statement objects
        //Statement myStatement = null;
        //PreparedStatement myStatement = null;

        try (Connection conn = ConnectionUtil.getConnection()){
            //Register the driver
            DriverManager.registerDriver(
                new oracle.jdbc.OracleDriver()
            );


            //Create statement object from connection
            //myStatement = conn.createStatement();

            //Perform a SQL INSERT with statement
           // myStatement.execute(
            //    "INSERT INTO bear (bear_name, bear_age, bear_weight, bear_type_id, cave_id) VALUES ('Panda', 10, 300, 1, 2)"
           // );


            String sql = "SELECT * FROM Enemies";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet enemiesResultSet = ps.executeQuery();
			
			String sql2 = "SELECT * FROM Items";
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet itemsResultSet = ps2.executeQuery();
            //Create a ResultSet object for reading data
            //ResultSet enemiesResultSet = myStatement.executeQuery(
            //    "SELECT * FROM Enemies"
            //);

            //loop through rows in ResultSet
			Item item[] = {null,null};
			StandardEnemy[] monSet = {null,null};
			String[] list = {"swipe","screech"};
			String[] list2 = {"slash","inferno"};
			String[] list3 = {"fireball", "meteor"};
			int index = 0;
            while(index < 2) {
            	enemiesResultSet.next();
            	if(enemiesResultSet.getString("EnemyType").equals("Standard")) {
            		if(enemiesResultSet.getString("name").equals("Harpy") && itemsResultSet.next()) {
            			item[0] = new Item(itemsResultSet.getString("ItemName"),itemsResultSet.getString("ItemEffect"));
            			monSet[0] = new StandardEnemy(enemiesResultSet.getInt("HitPoints"), list, enemiesResultSet.getString("name"), item[0]);
            		}
            		else if(enemiesResultSet.getString("name").equals("Demon") && itemsResultSet.next()) {
            			item[1] = new Item(itemsResultSet.getString("ItemName"),itemsResultSet.getString("ItemEffect"));
            			monSet[1] = new StandardEnemy(enemiesResultSet.getInt("HitPoints"), list2, enemiesResultSet.getString("name"), item[1]);
            		}
            	}
            	index++;
            }
            enemiesResultSet.next();
        	BossEnemy boss = new BossEnemy(enemiesResultSet.getInt("HitPoints"), list3, enemiesResultSet.getString("name"),enemiesResultSet.getString("Intro"), 
        				"The wizard momentarily regains his sanity and \nlooks at a pile of skeletons you did not notice coming \ninto the room "
        						+ "and the wizard speaks his last words \n'What have I done?' then goes silent.\n");
                
            ps.close();
            enemiesResultSet.close();
            ps2.close();
            itemsResultSet.close();
        
		//Item newItem = new Item("wand","deal 50 damage");
		//Item newItem2 = new Item("potion" , "heal 75 HP");
		//StandardEnemy monster = new StandardEnemy(50,list,"Harpy",newItem);
		//StandardEnemy monster2 = new StandardEnemy(100,list2,"Demon",newItem2);
		//BossEnemy boss = newBoss
		//		+ "momentarily regains his sanity and \nlooks at a pile of skeletons you did not notice coming \ninto the room "
		//		+ "and the wizard speaks his last words \n'What have I done?' then goes silent.\n");*/
		outside = new Room("standing outside a dark mansion", "a horrible screeching in the distance",null,null);
		hallway = new Room("in the hallway", "screeching coming from the greenHouse",null,null);
		greenHouse = new Room("in the greenHouse", "an angry harpy!",monSet[0],null);
		secondFloor = new Room("on the second floor", "an evil presence in the bedroom", null, null);
		bedroom = new Room("in the bedroom", "a lesser demon!", monSet[1],null);
		thirdFloor = new Room("on the third floor", "a strong magical presence in the library",null,null);
		library = new Room("in the library", "a powerful wizard", null, boss);

		outside.setExits("hallway", hallway);
		hallway.setExits("outside", outside);
		hallway.setExits("greenHouse", greenHouse);
		hallway.setExits("upstairs", secondFloor);
		secondFloor.setExits("bedroom", bedroom);
		secondFloor.setExits("upstairs", thirdFloor);
		secondFloor.setExits("downstairs", hallway);
		bedroom.setExits("floor2", secondFloor);
		thirdFloor.setExits("library",library);
		thirdFloor.setExits("downstairs",secondFloor);
		greenHouse.setExits("hallway", hallway);
		library.setExits("floor3", thirdFloor);

		currentRoom = outside;
        } catch (SQLException | IOException ex) {
            ex.printStackTrace(); 
        }
	}

	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// If there is no second word
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room
		Room nextRoom = currentRoom.getExit(direction);

		if (nextRoom == null) {
			System.out.println("Can't go there!");
		} else {
			currentRoom = nextRoom;
			System.out.println(currentRoom.getLongDescription());
		}
		
	}

	public void play() {
		System.out.println();
		System.out.println("Welcome to Mini Adventure");
		System.out.println("A simple and quick text adventure game.");
		System.out.println("Enter the mansion in front of you if you dare");
		System.out.println("Type 'help' if you need it.");

		System.out.println(currentRoom.getLongDescription());
		Player hero = new Player();
		boolean finished = false;
		while (!finished) {
			Command command = parser.getCommand();
			finished = processCommand(command, hero);
		}
		System.out.println("Thanks for playing.");
	}

	private boolean processCommand(Command command, Player hero) {
		boolean wantToQuit = false;
		GameState state = new GameState();
		if (command.isUnknown()) {
			System.out.println("I don't understand!");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("go")) {
			goRoom(command);
		} else if (commandWord.equals("help")) {
			System.out.println("Here is a list of Commands");
			System.out.println();
			parser.showCommands();
			
		} else if(commandWord.equals("fight")){
			state.enterCombat(currentRoom);
			Combat c = new Combat();
			while(state.getState().equals("battle")) {
				if(c.playerTurn(currentRoom,state, hero)) state.endCombat();
				else if(c.enemyTurn(currentRoom,state, hero)) {
					state.endCombat();
					wantToQuit = true;
				}
			}
			if(state.getState().equals("boss battle")) wantToQuit = c.startBossBattle(currentRoom,state, hero);
		}
		else if (commandWord.equals("look")) System.out.println(currentRoom.getLongDescription());
		else if (commandWord.equals("rest")){
			hero.setHP(100);
			System.out.println("You rest until healed" + "    current HP is: " + hero.getHP());
		}
		else if (commandWord.equals("quit")) {
			wantToQuit = quit(command);
		}

		return wantToQuit;
	}

	private boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else return true;
	}
}