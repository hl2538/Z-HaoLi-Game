package com.tic_tac_toe;

import java.util.LinkedList;

public class GUI {
	
	LinkedList<String> gui;
	private static final String SHAPE_LINE = "-";
	private static final String SHAPE_STRIP = "|";
	private static final String SHAPE_CLIENT = "X";
	private static final String SHAPE_SERVER = "O";
	private static final Integer LENGTH = 12;
	
	
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.show();
		gui.update(1, "client");
		gui.show();
	}
	
	public GUI() {
		gui = new LinkedList<>();
		for(int i=0; i<LENGTH; i++) {
			if((i+1)%4 ==0) {
				gui.add(SHAPE_STRIP);
			}
			else {
				gui.add(SHAPE_LINE);
			}
		}
	}
	
	public void update(int position, String character) {
		if(character.equals("client")) {
			gui.set(position-1,SHAPE_CLIENT );
		}
		else {
			gui.set(position-1, SHAPE_SERVER);
		}

	}
	
	public void show() {
		for(int i = 0 ; i<gui.size(); i++) {
			System.out.print(gui.get(i));
		}
		System.out.print("\n");
	}
	
	public  LinkedList<String>get() {
		return this.gui;
	}

}
