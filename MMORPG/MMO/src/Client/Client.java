package Client;
import java.net.*;
import java.util.Arrays;
import java.util.Scanner;

import Entities.DynamicEntity.PJ;
import GameSys.ActionJoueur;
import GameSys.World;
import Server.Server;

import java.io.*;

public class Client{
	
    static final String serverName = "localhost";
    static final int serverPort = 6223;
    private String playerName;
    
    private Socket socket;
    
    private static PJ player = new PJ();
    
    private String map;
    
    private ActionJoueur aj;
    
    public Client() throws UnknownHostException, IOException, ClassNotFoundException {
    	try {
    	socket = new Socket(serverName, serverPort);
        System.out.println("Joueur connecté !");
        System.out.println("Bienvenue sur Town of Demons , merci de bien vouloir choisir un pseudo :");
        
        Scanner sc = new Scanner(System.in);
        playerName = sc.next();
        
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(playerName);
        String test = in.readUTF();
        System.out.println(test);
        
        int choice = Launcher();
        out.writeInt(choice);
        
        ObjectOutputStream objout = new ObjectOutputStream(socket.getOutputStream());
        if (choice == 1) {
        	createPJ(playerName);
        }
    	objout.writeObject(player);
    	int length=in.readInt();
    	byte[] data=new byte[length];
    	in.readFully(data);
    	String str=new String(data,"UTF-32");
    	System.out.println(str);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

        /*playGame(in);*/
        /*ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
 
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());*/

        
        /*Object objrecu =in.readObject();
        
        map = (World) objrecu;
        map.AfficherMap();
        
        out.writeObject(map);
    
	/*
	 * 
	 */
	public int Launcher() {
		
		System.out.println("Vous voilà enfin prêt, souhaitez vous désormais");
		System.out.println("-1 Créer une partie");
		System.out.println("-2 Reprendre la partie");
		System.out.println("-3 Quitter le serveur");
		System.out.print("Que choisissez vous ? : ");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		return choice;	
	}
	
	public PJ createPJ(String nom) throws ClassNotFoundException, IOException {
		player.CreationPJ();
		return player;
	}
	
	public void playGame(DataInputStream in) throws IOException, ClassNotFoundException {
		
		while (true) {
			map = new String(in.readUTF());
			System.out.println(map);
			return;
		}
	}
	
	public World conversionWorld(Object o, ObjectInputStream in) throws ClassNotFoundException, IOException {
		o = in.readObject();
		return (World) o;
	}
    
    public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, IOException {
		Client c1 = new Client();
	}
}
