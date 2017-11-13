package com.tic_tac_toe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientController extends Thread{
	
	private DataInputStream input;
	private DataOutputStream output;
	Server server;
	Socket socket;
	boolean active;
	Integer id;
	
	public ClientController(Integer id, Server server, Socket socket) throws IOException {
		this.server = server;
		this.socket = socket;
		input = new DataInputStream(socket.getInputStream());		
		output = new DataOutputStream(socket.getOutputStream());
		output.flush();
		
	}
	
	public void run() {
		try {
				int[] data = new int[2];
				for(int i=0; i<2; i++) {
					data[i] = input.read();
				}
				server.receive(data);
				socket.close();
				server.remove(this);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			server.remove(this);
		}
	}
	
	
	public void setActive(boolean active) {
		this.active = active;
		
	}

}
