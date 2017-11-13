package com.tic_tac_toe;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class GameController {

	private static final String INFO = "Welcome! please choose play mode!\n"
			+ "human versus machine: Please enter 1;\n"
			+ "machine versus machine: Please enter 2;\n"
			+ "human versus human: Please enter 3;\n";
	GUI gui;
	Client player1;
	Client player2;
	Server server;
	Integer playMode;
	public LinkedList<Integer> clientIdList;
	public Integer flag;

	
	public GameController() throws IOException {
		clientIdList = new LinkedList<>();
		gui = new GUI();
		flag = 0;
		player1 = new Client(flag);
		player2 = new Client(flag);
		clientIdList.add(player1.getClientId());
		clientIdList.add(player2.getClientId());
		server = new Server(clientIdList);

		
	}
	
	public static void main(String[] args) throws IOException {
		GameController gc = new GameController();
		gc.startGame();
	}
	

	
	public void startGame() {
		System.out.println(INFO);
		choosePlayMode();
		this.flag = 0;
		server.start();
		player1.start();
		player2.start();
		
		while(true) {
			int[] data = this.server.data;
			int position = data[1];
			int id = data[0];
			Client c = id==player1.getClientId()? player1:player2;
			gui.update(position, c.getCharacter());
			gui.show();
		}
		
		
	}
	

	public void choosePlayMode() {
		Scanner scanner = new Scanner(System.in);
		playMode = scanner.nextInt();
		scanner.close();
		if(playMode ==1 ) {
			player1.setType("human");
			player1.setCharacter("client");
			player2.setType("machine");
			player2.setCharacter("server");
			System.out.println("You have choosen human versus machine mode!\n Game Start~");
		}
		else if(playMode == 2) {
			player1.setType("machine");
			player1.setCharacter("client");
			player2.setType("machine");
			player2.setCharacter("server");
			System.out.println("You have choosen machine versus machine mode!\n Game Start~");
		}
		else {
			player1.setType("human");
			player1.setCharacter("client");
			player2.setType("human");
			player2.setCharacter("server");
			System.out.println("You have choosen human versus human mode!\n Game Start~");
		}
	}
}
