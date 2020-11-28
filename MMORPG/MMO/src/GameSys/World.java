package GameSys;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import Entities.Entity;
import Entities.DynamicEntity.*;
import Entities.StaticEntity.*;


import java.beans.*;

/*Classe Map*/
public class World implements CreationMap,Serializable {

	/* attributs de classe Map */
	private ArrayList<Objet> objets;
	private ArrayList<PNJ> monstres;
	private int longueur;
	private int largeur;
	private String joueur;
	

	private Entity[][] entities;
	/*
	 * constructeur de la classe Map Par défaut
	 */
	public World() {
		this.objets = new ArrayList<Objet>();
		this.monstres = new ArrayList<PNJ>();
		this.entities = new Entity[this.largeur][this.longueur];
	}

	/* Membre à membre */
	public World(int l, int la) {
		this.objets = new ArrayList<Objet>();
		this.monstres = new ArrayList<PNJ>();
		this.entities = new Entity[l][la];
	}

	/* Accesseurs */
	public Entity[][] getEntities() {
		return this.entities;
	}
	

	public int getLongueur() {
		return this.longueur;
	}

	public int getLargeur() {
		return this.largeur;
	}

	public void setLongueur(int l) {
		this.longueur = l;
	}

	public void setLargeur(int la) {
		this.largeur = la;
	}

	public String getJoueur() {
		return this.joueur;
	}

	public void setJoueur(String joueur) {
		this.joueur = joueur;
	}
	

	/* Méthode qui permet de dessiner la grille */
	/* Entrée: - */
	/* Sortie: Grille formatée */
	
	
	public void CreerMap() {
		for (int i = 0; i <= this.entities.length - 1; i++) {
			this.entities[i] = new Entity[this.entities[0].length];
			for (int j = 0; j <= this.entities[i].length - 1; j++) {
				if (i == 0 || i == this.entities.length - 1 || j == 0 || j == this.entities[i].length - 1) {
					this.entities[i][j] = new Obstacle();
				} else {
					this.entities[i][j] = null;
				}
			}
		}
	}

	/* Méthode qui permet d'ajouter aléatoirement des obstacles dans la grille */
	/* Entrée: - */
	/* Sortie: grille mise à jour avec les obstacles */
	public void ajoutObstacle() {
		int e = 0;
		while (e<5) {
		placerObstacle(5+e, 5);
		e++;
	}
	}
	
	
	
	public void placerObstacle(int x,int y) {
		
		this.entities[x][y] = new Obstacle();
	}
	
	
	
	public ArrayList<Objet> getTabObj()
	{
		return this.objets;
	}
	
	public void EnregistrementObj(Objet obj)
	{
		objets.add(obj);
	}
	public void SupressionObj(Objet obj)
	{
		objets.remove(obj);
	}
	
	public Objet RechercheObjet(int x, int y) 
	{
		for(int i = 0; i < getTabObj().size(); i++)
		{
			if(objets.get(i).getPosXobj() == x && objets.get(i).getPosYobj() == y)
			{
				return objets.get(i);
			}
		}
		return null;
	}
	/*
	 * Méthode permettant de placer des objets aléatoirement sur la grille Entrée :
	 * nom de l'objet Sortie : Grille mise à jour
	 */
	public void placerObjet()
	{
		Dague d1 = new Dague();
		d1.CreationArme();
		d1.placer(this.entities);
		this.EnregistrementObj(d1);
		
		Dague d2 = new Dague();
		d2.CreationArme();
		d2.placer(this.entities);
		this.EnregistrementObj(d2);
		
		TenueCuir tc = new TenueCuir();
		tc.CreationArmure();
		tc.placer(4, 3, this.entities);
		this.EnregistrementObj(tc);
		//System.out.println(tc.getPosXobj());
		//System.out.println(tc.getPosYobj());
	}
	
	
	
	public ArrayList<PNJ> getTabPNJ()
	{
		return this.monstres;
	}
	
	public void EnregistrementPNJ(PNJ pnj)
	{
		monstres.add(pnj);
	}
	public void SupressionPNJ(PNJ pnj)
	{
		monstres.remove(pnj);
	}
	
	public PNJ RecherchePNJ(int x, int y) 
	{
		for(int i = 0; i < getTabObj().size(); i++) // à modif le getTabObj ( c'est des PNJ )
		{
			if(monstres.get(i).getPosXpnj() == x && monstres.get(i).getPosYpnj() == y)
			{
				return monstres.get(i);
			}
		}
		return null;
	}
	
	public PNJ ChoixPNJ(int i) 
	{
		return monstres.get(i);
	}
	

	/*
	 * Méthode permettant de placer des ennemis aléatoirement sur la grille Entrée :
	 * nom de l'ennemi Sortie : Grille mise à jour
	 */
	public void placerEnnemi() 
	{
		Vampire v = new Vampire();
		v.CreationPNJ();
		v.placer(this.entities);
		this.EnregistrementPNJ(v);
		
		Demon d = new Demon();
		d.CreationPNJ();
		d.placer(this.entities);
		this.EnregistrementPNJ(d);
		
		Zombie z = new Zombie();
		z.CreationPNJ();
		z.placer(2, 4, this.entities);
		//z.placer(5, 7, this.entities);
		this.EnregistrementPNJ(z);
		
		//System.out.println(z.getPosXpnj());
		//System.out.println(z.getPosYpnj());
	}

	
	/* Méthode qui permet d'afficher la grille */
	/* Entrée: - */
	/* Sortie: - */
	public void AfficherMap() {
		String map = "";
		for (int i = 0; i <= this.entities.length - 1; i++) {
			for (int j = 0; j <= this.entities[0].length - 1; j++) {
				if (this.entities[i][j]!=null) {
					map = map + this.entities[i][j].getRepresentation();
				} else {
					map = map + " . ";
				}
			}
			map = map + "\n";
		}
		System.out.println(map);
	}

/*
 * Méthode permettant de sauvegarder la map selon le nom attribué à la partie
 */
	/*public void sauvegarder(String nomPartie) {
		try {
			BufferedWriter bw = new BufferedWriter(
			new FileWriter(nomPartie + File.separator+ "saveMap" + nomPartie + ".txt"));
			bw.write(StockerMap());
			bw.close();
		} catch (Exception e) {
		}
	}*/

/*
 * Méthode permettant de charger la map selon la nom attribué à la partie
 */
	/*public void loadMap(String nomPartie) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(nomPartie + File.separator +"saveMap" + nomPartie + ".txt"));
			String line = br.readLine();
			char[] convertline = line.toCharArray();
			this.grille = new String[CountFileLines(nomPartie + File.separator + "saveMap" + nomPartie + ".txt")][convertline.length];
			for (int i = 0; i < this.grille.length; i++) {
				for (int j = 0; j < this.grille[0].length; j++) {
					this.grille[i][j] = String.valueOf(convertline[j]);
				}
				line = br.readLine();
				convertline = line.toCharArray();
			}
			br.close();
		} catch (Exception e) {
		}
	}*/

	public int CountFileLines(String nomFichier) {
		int nb = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(nomFichier));
			while (reader.readLine() != null) {
				nb += 1;
			}
			reader.close();
		} catch (Exception e) {
		}
		return nb;
	}
/*
 * Méthode permettant de stocker dans une chaine de caractère la map du jeu
 */
	/*public String StockerMap() {
		String map = "";
		for (int i = 0; i <= this.grille.length - 1; i++) {
			for (int j = 0; j <= this.grille[0].length - 1; j++) {
				if (this.grille[i][j] != "") {
					map = map + this.grille[i][j];
				} else {
					map = map + ".";
				}
			}
			map = map + "\n";
		}
		return map;
	}*/
	
	/*
	 * 
	 * 
	 */

	/*
	 * Méthode permettant de générer une map avec le placement aléatoire de monstre, potions etc.
	 */
	public void GenererMap() {
		this.CreerMap();
		this.ajoutObstacle();
		this.placerObjet();
		this.placerEnnemi();
	}
	

	public static void main(String[] args) {
		World m = new World(40,40);
		m.CreerMap();
		m.AfficherMap();
		m.ajoutObstacle();
		m.AfficherMap();
		PJ j = new PJ("J", 100, 50, 15, 12);
		j.placer(6, 7,m.entities);
		m.AfficherMap();
		m.placerEnnemi();
		/*m.loadMap("test");*/
		m.placerObjet();
		m.AfficherMap();
		//System.out.println(m.RechercheObjet(3,2));
		//System.out.println(m.RecherchePNJ(1,3));
		System.out.println(Arrays.deepToString(m.getEntities()).replace("], ", "]\n"));
	}
}
