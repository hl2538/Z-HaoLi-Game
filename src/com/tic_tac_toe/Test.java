package com.tic_tac_toe;

import java.io.IOException;
import java.util.LinkedList;

public class Test {
	public static void main(String[] args) throws IOException {
		Client c = new Client(0);
		GUI gui = new GUI();
		LinkedList<Integer> idList = new LinkedList<>();
		idList.add(c.getClientId());
		Server server = new Server(idList);
		server.start();
		int[] data = server.data;
		int position = data[1];
		int id = data[0];
		gui.update(position,"client");
		gui.show();
	}
	
}
