package com.tic_tac_toe;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;



public class Server extends Thread{
	
	private final static Integer PORT = 707;
	private ServerSocket socket;
	private boolean active;
	private boolean close;
	private LinkedList<ClientController> clients;
	public LinkedList<Integer> clientIdList;
	public int[] data;
	
	
	public static void main(String[] args) throws IOException {

	}
	
	public Server() throws IOException {
		socket = new ServerSocket(PORT);
		clients = new LinkedList<>();
		active = true;
		close = false;
	}
	
	public Server(LinkedList<Integer> clientIdList) throws IOException {
		socket = new ServerSocket(PORT);
		clients = new LinkedList<>();
		this.clientIdList = clientIdList;
		active = true;
		close = false;
	}
	
	public void run() {
		try {
			Integer id;
			while(!close) {
				if(active) {
					Socket s = socket.accept();
					if(!clientIdList.isEmpty()) {
						id = clientIdList.removeFirst();
						ClientController client = new ClientController(id,this, s);
						clients.add(client);
						client.start();
					}
				}
			}
			for (int i = 0; i < clients.size(); i++) {
				clients.get(i).setActive(false);
			}
			socket.close();
			
		}catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public boolean isActive() {
		return this.active;
	}
	
	public boolean isClosed() {
		return this.close;
	}
	
	public void receive(int[] data) {
		this.data = data;
	}
	
	public void remove(ClientController c) {
		clients.remove(c);
	}
	
}
