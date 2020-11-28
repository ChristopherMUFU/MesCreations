package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import Entities.Entity;
import Entities.DynamicEntity.PJ;
import GameSys.World;

public class Server {
	
	/*port utilisé*/
	private static final int PORT = 6223;
	
	private ServerSocket ss;
	
	private static World map = new World(40,40);
	
	private static ArrayList<PJ> pjlist = new ArrayList<PJ>();
	
	private static int numPlayers = 0;
	
	private volatile boolean exit = false;
	
	public Server() throws IOException {
        
		ServerSocket s = new ServerSocket(PORT);
        System.out.println("Bienvenue sur le serveur du jeu, vous êtes connecter à l'adresse :" + s);
        System.out.println("Chargement du serveur,Veuillez patienter...");
        System.out.println("En attente de joueur....");
        
        while(true) {
        	Socket socket = s.accept();
        	System.out.println("Connexion acceptée");
        	ServerThread sthread = new ServerThread(socket);
        	sthread.start(); 
        }   
    }
	
	public void stop() {
		exit = true;
	}
	
	public void playGame(PJ player,Socket client) throws IOException {
		
		System.out.println("Lancement du jeu en cours...");
		System.out.println("Chargement du monde");
		map.GenererMap();
		System.out.println("Chargement du personnage...");
		player.placer(4, 4, map.getEntities());
		
		
		while (true) {
			System.out.println(player.toString());
			return;
		}
		
	}
	
	/*
	 * inner class pour le Thread du serveur
	 */
	class ServerThread extends Thread {
		
		/*DataInputStream inputStream;
		DataOutputStream outputStream;*/
		Socket client;
		
		public ServerThread(Socket client) throws IOException {
			/*this.inputStream = new DataInputStream(client.getInputStream());
			this.outputStream = new DataOutputStream(client.getOutputStream());*/
			this.client = client;
			numPlayers++;
		}
		
		public void run() {
			System.out.println("Done");
			try {
				DataOutputStream out = new DataOutputStream(this.client.getOutputStream());
				out.writeInt(numPlayers);
				DataInputStream in = new DataInputStream(this.client.getInputStream());
				String identite = in.readUTF();
				int choice = in.readInt();
				
				ObjectInputStream pjin = new ObjectInputStream(this.client.getInputStream());
				pjlist.add((PJ) pjin.readObject());
				pjlist.get(0).AffichageStats();
				if (choice == 1) {
				}
				System.out.println(map.StockerMap());
				String str="foo";
				byte[] data=str.getBytes("UTF-32");
				System.out.println(data.toString());
				out.writeInt(data.length);
				out.write(data);
				/*out.writeUTF(map.StockerMap());*/
				this.client.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	
	public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
		new Server();
	}
}
