package com.tic_tac_toe;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;


public class Client extends Thread{

	private Integer id;
	public String type; //human or machine
	private boolean active;
	private Socket socket;
	private DataOutputStream output;
	private Integer flag;
	private int[] data;
	private String character;
	
	public Client(int flag) {
		int id = generateClientId();
		socket = new Socket();
		setClientId(id);
		data = new int[2];
		this.flag = flag;
		active = true;
	}
	

	public void setCharacter(String character) {
		this.character = character;
	}
	
	public String getCharacter() {
		return this.character;
	}
	
	public void init() throws UnknownHostException, IOException {
		socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 707));
		output = new DataOutputStream(socket.getOutputStream());
		output.flush();
		System.out.println("Connected to Server");
	}
	
	
	public Integer getClientId() {
		return this.id;
	}
	
	public Integer generateClientId() {
		Random random = new Random();
		return random.nextInt(100);
	}
	
	public void setClientId(Integer id) {
		this.id = id;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public synchronized void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public synchronized Integer getFlag() {
		return this.flag;
	}
	
	public static void main(String ars[]) {
		Client c = new Client(0);
		System.out.println("player id "+c.id+"'s turn");
		System.out.println("please input the position");
		
		Scanner scanner = new Scanner(System.in);
		Integer position = scanner.nextInt();
		System.out.println(position);
	}
	
	
	
	public void run() {
		try {
			init();
			while (active) {
				if(flag==0 && type.equals("human")&& character.equals("client")) {
					System.out.println("player id "+this.id+"'s turn");
					System.out.println("please input the position");
					Scanner scanner = new Scanner(System.in);
					if(scanner.hasNext()) {
						Integer position = scanner.nextInt();
						output.write(this.id);
						output.write(position);
						setFlag(1);
					}
				}
				else if(flag ==1 && type.equals("human")&&character.equals("server")) {
					System.out.println("player id "+this.id+"'s turn");
					Scanner scanner = new Scanner(System.in);
					if(scanner.hasNext()) {
						Integer position = scanner.nextInt();
						output.write(this.id);
						output.write(position);
						setFlag(0);
					}
				}
				else if(flag ==0 && type.equals("machine") && character.equals("client")){
					
				}
				else if(flag ==1 && type.equals("machine") && character.equals("server")){
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
