package GameSys;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import Entities.Entity;
import Entities.DynamicEntity.*;
import Entities.StaticEntity.*;

public class ActionJoueur
{
// Attributs
    private int pointAction;
    private int coutAtt;
    private int coutPot;
    private int coutDep;
    
	private String nom;
	private PJ j;
	private World map;
	private SacPJ sac;
	private Objet obj;
	//private BaseDonnees bdd;

// Constructeurs
    public ActionJoueur()
    {
        this.pointAction = 0;
        this.coutAtt = 3;
        this.coutDep = 2;
    	this.coutPot = 0;
    }
    public ActionJoueur(int pa, World map, String nom, PJ j, SacPJ sac) 
    {
    	this.pointAction = pa;
    	this.map = map;
    	this.nom = nom;
    	this.sac = sac;
    	//this.bdd = bdd;
    	this.j = j;
    	this.coutAtt = 3;
    	this.coutDep = 2;
    	this.coutPot = 0;
    }
// Accesseurs



// Methodes
    public void GainPA()
    {
    	this.pointAction += 5;
    }
    
    public void DeplacerPJ() 
    {
    	// lié map
    	if(this.pointAction >= this.coutDep)
    	{
    		this.pointAction -= this.coutDep;
    		
			Scanner sc = new Scanner(System.in);
			String choixCote;
			
			System.out.println();
			System.out.println("Choisissez un coté");
			System.out.println("H - Vers le haut");
			System.out.println("B - Vers le bas");
			System.out.println("G - Vers la gauche"); // condition spé pour attaque
			System.out.println("D - Vers la droite"); // condition spé pour ramasser un obj
			System.out.print("Que choisissez vous ? (tapez H, B, G ou D) : ");
			choixCote = sc.nextLine();
			System.out.println();
			/*
			while(choixCote != "H" || choixCote != "B" || choixCote != "G" || choixCote != "D")
			{
				System.out.println("La valeur entrée est incorrecte, veuillez réessayer.");
				System.out.print("Que choisissez vous ? (tapez H, B, G ou D) : ");
				choixCote = sc.nextLine();
				System.out.println();
			}
			*/
			if(choixCote.equals("H")) 
			{
				j.deplacer("H", this.map.getEntities());
				this.map.AfficherMap();
				this.AffichageAllStats();
				this.UtilisationAction();
			}
			else if(choixCote.equals("B"))
			{
				j.deplacer("B", this.map.getEntities());
				this.map.AfficherMap();
				this.AffichageAllStats();
				this.UtilisationAction();
			}
			else if(choixCote.equals("G"))
			{	
				j.deplacer("G", this.map.getEntities());
				this.map.AfficherMap();
				this.AffichageAllStats();
				this.UtilisationAction();
			}
			else if(choixCote.equals("D"))
			{
				j.deplacer("D", this.map.getEntities());
				this.map.AfficherMap();
				this.AffichageAllStats();
				this.UtilisationAction();
			}
    	}
    	else
    	{
    		System.out.println();
			System.out.println("Vous n'avez pas assez de PA, choisissez une autre action");
			this.UtilisationAction();
    	}
    	
    }
    
    public void AffichageAllStats()
    {
    	j.AffichageStats();	
    	sac.AffichageObjetEquip();
    	j.AffichageStatsCap();
    }
    
    
    
    public void GestionInventaire() 
    {
    	//Affichage du sac et des objet équipé
    	//this.sac = j.getSacPJ();
    	sac.AffichageInventaire();
    	
    	Scanner sc = new Scanner(System.in);
    	int choixInv;
    	
    	System.out.print("Ecrivez le chiffre de l'objet dont vous voulez interagir : ");
    	choixInv = Integer.parseInt(sc.nextLine());
    	
    	while(this.sac.getInventaireID(choixInv - 1) == null)
		{
			System.out.println("La valeur entrée est incorrecte, veuillez réessayer.");
			System.out.print("Ecrivez le chiffre de l'objet dont vous voulez interagir : ");
			choixInv = Integer.parseInt(sc.nextLine());
			System.out.println();
		}
    	
    	if(this.sac.getInventaireID(choixInv - 1) != null)
    	{
    		this.obj = sac.getInventaireID(choixInv-1);
    		if(obj.getValPA() > 0) // à verif
			{
				int choixUtil;
				
				System.out.println("Vous souhaitez : ");
		    	System.out.println("1 - Utiliser l'objet ( " + obj.getValPA() + "PA) ");
		    	System.out.println("2 - Jeter l'objet");
		    	System.out.print("Que choisissez vous ? (tapez 1 ou 2) : ");
		    	choixUtil = Integer.parseInt(sc.nextLine());
		    	
		    	if(choixUtil == 1) // Potion pour chaque type ( Boost / soin / off )
		    	{
		    		// faire getters pvReg / boost  +++ créer une fonction attaquePotion
		    		// UtilisationPotion()
		    		obj.setStatusEquip(true);
		    		j.ConversionCap(sac);
		    		this.sac.AffichageInventaire();
		    		this.AffichageAllStats();
		    		this.UtilisationAction();
		    	}
		    	else
		    	{
		    		obj = null;
		    		this.sac.AffichageInventaire();
		    		this.AffichageAllStats();
		    		this.UtilisationAction();
		    	}
		    	
			}
			else if(obj.getStatusEquip() == true)
			{
				// Desequip / jeter
				int choixEquip;
				
				System.out.print("Vous souhaitez : ");
		    	System.out.println("1 - Enlever l'objet");
		    	System.out.println("2 - Jeter l'objet");
		    	System.out.print("Que choisissez vous ? (tapez 1 ou 2) : ");
		    	choixEquip = Integer.parseInt(sc.nextLine());
		    	
		    	if(choixEquip == 1)
		    	{
		    		obj.setStatusEquip(false);
		    		j.ConversionCap(sac);
		    		this.sac.AffichageInventaire();
		    		this.AffichageAllStats();
		    		this.UtilisationAction();
		    	}
		    	else
		    	{
		    		obj = null;
		    		j.ConversionCap(sac);
		    		this.sac.AffichageInventaire();
		    		this.AffichageAllStats();
		    		this.UtilisationAction();
		    	}

			}
			else
			{
				// Equip / jeter
				int choixEquip;
				
				System.out.println("Vous souhaitez : ");
		    	System.out.println("1 - Equiper l'objet");
		    	System.out.println("2 - Jeter l'objet");
		    	System.out.print("Que choisissez vous ? (tapez 1 ou 2) : ");
		    	choixEquip = Integer.parseInt(sc.nextLine());
		    	
		    	if(choixEquip == 1)
		    	{
		    		obj.setStatusEquip(true);
		    		j.ConversionCap(sac);
		    		System.out.println("L'objet a bien été équipé.");
		    		System.out.println();
		    		this.sac.AffichageInventaire();
		    		this.AffichageAllStats();
		    		this.UtilisationAction();
		    	}
		    	else
		    	{
		    		obj = null;
		    		this.sac.AffichageInventaire();
		    		this.AffichageAllStats();
		    		this.UtilisationAction();
		    	}
			}
		}
    }
    		
    
    
    public void Attaquer(Entity[][] entities) // avec Arme ou Potion (en tapant l'indice) ? 
    {
    	if(this.pointAction >= this.coutAtt)
    	{
    		
	    	boolean hTest = false;
	    	boolean bTest = false;
	    	boolean gTest = false;
	    	boolean dTest = false;
	    	
	    	if(entities[j.getLignePJ()-1][j.getColPJ()] != null && entities[j.getLignePJ()-1][j.getColPJ()].getRepresentation() != " o ")
	    	{
	    		hTest = true;
	    	}
	    	if(entities[j.getLignePJ()+1][j.getColPJ()] != null && entities[j.getLignePJ()+1][j.getColPJ()].getRepresentation() != " o ")
	    	{
	    		bTest = true;
	    	}
	    	if(entities[j.getLignePJ()][j.getColPJ()-1] != null && entities[j.getLignePJ()][j.getColPJ()-1].getRepresentation() != " o ")
	    	{
	    		gTest = true;
	    	}
	    	if(entities[j.getLignePJ()][j.getColPJ()+1] != null && entities[j.getLignePJ()][j.getColPJ()+1].getRepresentation() != " o ")
	    	{
	    		dTest = true;
	    	}
	    	
	    	Scanner sc = new Scanner(System.in);
	    	String choixDir;
	    	System.out.println("Nous avons un ennemi en : ");
	    	
	    	if(hTest == true)
	    	{
	    		System.out.println("H - Vers le haut");
	    	}
	    	if(bTest == true)
	    	{
	    		System.out.println("B - Vers le bas");
	    	}
	    	if(gTest == true)
	    	{
	    		System.out.println("G - Vers la gauche");
	    	}
	    	if(dTest == true)
	    	{
	    		System.out.println("D - Vers la droite");
	    	}
	    	
	    	System.out.print("Choisissez une lettre correspondant à l'emplacement d'un ennemi : ");
	    	
	    	choixDir = sc.nextLine();
	    	System.out.println();
	    	
	    	if(choixDir.equals("H"))
	    	{
	    		int pa = this.pointAction;
	    		int count = 0;
	    		while(this.coutAtt <= pa)
	            {
	    			pa -= coutAtt;
	    			count += 1;
	            }
	    		System.out.println("Avec vos PA, vous pouvez réaliser au maximum " + count + " attaque.");
	    		System.out.print("Sélectionnez un nombre d'attaque (entre 1 et le max) : ");
	    		int countAtt = Integer.parseInt(sc.nextLine());
	    		this.pointAction -= countAtt * coutAtt;
	    		PNJ pnjMap = this.map.RecherchePNJ(j.getLignePJ()-1, j.getColPJ());
	    		CombatPJvsPNJ cb = new CombatPJvsPNJ(j, pnjMap, countAtt, this.map.getEntities(), map);
	        	cb.CombatPJversPNJ();
	        	this.AffichageAllStats();
	    		this.UtilisationAction();
	    	}
	    	else if(choixDir.equals("B"))
	    	{
	    		int pa = this.pointAction;
	    		int count = 0;
	    		while(this.coutAtt <= pa)
	            {
	    			pa -= coutAtt;
	    			count += 1;
	            }
	    		System.out.println("Avec vos PA, vous pouvez réaliser au maximum " + count + " attaque.");
	    		System.out.print("Sélectionnez un nombre d'attaque (entre 1 et le max) : ");
	    		int countAtt = Integer.parseInt(sc.nextLine());
	    		this.pointAction -= countAtt * coutAtt;
	    		PNJ pnjMap = this.map.RecherchePNJ(j.getLignePJ()+1, j.getColPJ());
	    		CombatPJvsPNJ cb = new CombatPJvsPNJ(j, pnjMap, countAtt, this.map.getEntities(), map);
	        	cb.CombatPJversPNJ();
	        	this.AffichageAllStats();
	    		this.UtilisationAction();
	    	}
	    	else if(choixDir.equals("G"))
	    	{
	    		int pa = this.pointAction;
	    		int count = 0;
	    		while(this.coutAtt <= pa)
	            {
	    			pa -= coutAtt;
	    			count += 1;
	            }
	    		System.out.println("Avec vos PA, vous pouvez réaliser au maximum " + count + " attaque.");
	    		System.out.print("Sélectionnez un nombre d'attaque (entre 1 et le max) : ");
	    		int countAtt = Integer.parseInt(sc.nextLine());
	    		this.pointAction -= countAtt * coutAtt;
	    		PNJ pnjMap = this.map.RecherchePNJ(j.getLignePJ(), j.getColPJ()-1);
	    		CombatPJvsPNJ cb = new CombatPJvsPNJ(j, pnjMap, countAtt, this.map.getEntities(), map);
	        	cb.CombatPJversPNJ();
	        	this.AffichageAllStats();
	    		this.UtilisationAction();
	    	}
	    	else if(choixDir.equals("D"))
	    	{
	    		int pa = this.pointAction;
	    		int count = 0;
	    		while(this.coutAtt <= pa)
	            {
	    			pa -= coutAtt;
	    			count += 1;
	            }
	    		System.out.println("Avec vos PA, vous pouvez réaliser au maximum " + count + " attaque.");
	    		System.out.print("Sélectionnez un nombre d'attaque (entre 1 et le max) : ");
	    		int countAtt = Integer.parseInt(sc.nextLine());
	    		this.pointAction -= countAtt * coutAtt;
	    		PNJ pnjMap = this.map.RecherchePNJ(j.getLignePJ(), j.getColPJ()+1);
	    		CombatPJvsPNJ cb = new CombatPJvsPNJ(j, pnjMap, countAtt, this.map.getEntities(), map);
	        	cb.CombatPJversPNJ();
	        	this.AffichageAllStats();
	    		this.UtilisationAction();
	    	}
    	}
    }
    
    
    
    public void RamasserObjet(Entity[][] entities)
    {
    	boolean hTest = false;
    	boolean bTest = false;
    	boolean gTest = false;
    	boolean dTest = false;
    	
    	if(entities[j.getLignePJ()-1][j.getColPJ()] != null && entities[j.getLignePJ()-1][j.getColPJ()].getRepresentation().equals(" o "))
    	{
    		hTest = true;
    	}
    	if(entities[j.getLignePJ()+1][j.getColPJ()] != null && entities[j.getLignePJ()+1][j.getColPJ()].getRepresentation().equals(" o "))
    	{
    		bTest = true;
    	}
    	if(entities[j.getLignePJ()][j.getColPJ()-1] != null && entities[j.getLignePJ()][j.getColPJ()-1].getRepresentation().equals(" o "))
    	{
    		gTest = true;
    	}
    	if(entities[j.getLignePJ()][j.getColPJ()+1] != null && entities[j.getLignePJ()][j.getColPJ()+1].getRepresentation().equals(" o "))
    	{
    		dTest = true;
    	}
    	
    	Scanner sc = new Scanner(System.in);
    	String choixDir;
    	System.out.println("Nous avons un objet en : ");
    	
    	if(hTest == true)
    	{
    		System.out.println("H - Vers le haut");
    	}
    	if(bTest == true)
    	{
    		System.out.println("B - Vers le bas");
    	}
    	if(gTest == true)
    	{
    		System.out.println("G - Vers la gauche");
    	}
    	if(dTest == true)
    	{
    		System.out.println("D - Vers la droite");
    	}
    	
    	System.out.print("Choisissez une lettre correspondant à l'emplacement d'un objet : ");
    	
    	choixDir = sc.nextLine();
    	System.out.println();
    	
    	if(choixDir.equals("H"))
    	{
    		Objet objMap = this.map.RechercheObjet(j.getLignePJ()-1, j.getColPJ());
    		this.sac.AjoutObjet(objMap);
    		entities[j.getLignePJ()-1][j.getColPJ()] = null;
    		map.AfficherMap();
    		System.out.println();
    		System.out.println("L'objet a été récupéré.");
    		this.sac.AffichageInventaire();
    		this.AffichageAllStats();
    		this.UtilisationAction();
    	}
    	else if(choixDir.equals("B"))
    	{
    		Objet objMap = this.map.RechercheObjet(j.getLignePJ()+1, j.getColPJ());
    		this.sac.AjoutObjet(objMap);
    		entities[j.getLignePJ()+1][j.getColPJ()] = null;
    		map.AfficherMap();
    		System.out.println();
    		System.out.println("L'objet a été récupéré.");
    		this.sac.AffichageInventaire();
    		this.AffichageAllStats();
    		this.UtilisationAction();
    	}
    	else if(choixDir.equals("G"))
    	{
    		Objet objMap = this.map.RechercheObjet(j.getLignePJ(), j.getColPJ()-1);
    		this.sac.AjoutObjet(objMap);
    		entities[j.getLignePJ()][j.getColPJ()-1] = null;
    		map.AfficherMap();
    		System.out.println();
    		System.out.println("L'objet a été récupéré.");
    		this.sac.AffichageInventaire();
    		this.AffichageAllStats();
    		this.UtilisationAction();
    	}
    	else if(choixDir.equals("D"))
    	{
    		Objet objMap = this.map.RechercheObjet(j.getLignePJ(), j.getColPJ()+1);
    		this.sac.AjoutObjet(objMap);
    		entities[j.getLignePJ()][j.getColPJ()+1] = null;
    		map.AfficherMap();
    		System.out.println();
    		System.out.println("L'objet a été récupéré.");
    		this.sac.AffichageInventaire();
    		this.AffichageAllStats();
    		this.UtilisationAction();
    	}
    }
    
    public void FinTour(Entity[][] entities, World map) 
    {	
    	// while + boolean pour le cas ou le joueur est inconscient
    	System.out.println();
    	System.out.println("Début du tour ennemi :");
    	System.out.println();
    	
    	for(int i = 0; i < map.getTabPNJ().size(); i++)
		{
    		int count = 1;
    		boolean hTest = false;
        	boolean bTest = false;
        	boolean gTest = false;
        	boolean dTest = false;
        	
        	if(entities[map.ChoixPNJ(i).getPosXpnj()-1][map.ChoixPNJ(i).getPosYpnj()] != null && entities[map.ChoixPNJ(i).getPosXpnj()-1][map.ChoixPNJ(i).getPosYpnj()] == entities[j.getLignePJ()][j.getColPJ()])
        	{
        		hTest = true;
        	}
        	if(entities[map.ChoixPNJ(i).getPosXpnj()+1][map.ChoixPNJ(i).getPosYpnj()] != null && entities[map.ChoixPNJ(i).getPosXpnj()+1][map.ChoixPNJ(i).getPosYpnj()] == entities[j.getLignePJ()][j.getColPJ()])
        	{
        		bTest = true;
        	}
        	if(entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()-1] != null && entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()-1] == entities[j.getLignePJ()][j.getColPJ()])
        	{
        		gTest = true;
        	}
        	if(entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()+1] != null && entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()+1] == entities[j.getLignePJ()][j.getColPJ()])
        	{
        		dTest = true;
        	}
        	
        	if(hTest == true || bTest == true || gTest == true || dTest == true)
        	{
        		CombatPJvsPNJ cb = new CombatPJvsPNJ(j, map.ChoixPNJ(i), count, entities, map);
	        	cb.CombatPNJversPJ();
        	}
        	else
        	{
        		// déplacement au hasard
        		Random random = new Random();
        		int direction = random.nextInt(4) + 1;
        		//System.out.println("Choix direction de "+ i + " : "+ direction);
        		if(direction == 1 && entities[map.ChoixPNJ(i).getPosXpnj()-1][map.ChoixPNJ(i).getPosYpnj()] == null)
        		{
        			entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()] = null;
        			map.ChoixPNJ(i).placer(map.ChoixPNJ(i).getPosXpnj(), map.ChoixPNJ(i).getPosYpnj()+1, entities);
        		}
        		else if(direction == 2 && entities[map.ChoixPNJ(i).getPosXpnj()+1][map.ChoixPNJ(i).getPosYpnj()] == null)
        		{
        			entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()] = null;
        			map.ChoixPNJ(i).placer(map.ChoixPNJ(i).getPosXpnj()+2, map.ChoixPNJ(i).getPosYpnj()+1, entities);
        		}
        		else if(direction == 3 && entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()-1] == null)
        		{
        			entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()] = null;
        			map.ChoixPNJ(i).placer(map.ChoixPNJ(i).getPosXpnj()+1, map.ChoixPNJ(i).getPosYpnj(), entities);
        		}
        		else if(direction == 4 && entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()+1] == null)
        		{
        			entities[map.ChoixPNJ(i).getPosXpnj()][map.ChoixPNJ(i).getPosYpnj()] = null;
        			map.ChoixPNJ(i).placer(map.ChoixPNJ(i).getPosXpnj()+1, map.ChoixPNJ(i).getPosYpnj()+2, entities);
        		}
        	}
		} 
    	map.AfficherMap();
    	System.out.println();
    	System.out.println("Fin du tour ennemi :");
    	System.out.println();
    	this.pointAction += 2;
    	System.out.println("Vous gagnez 2PA.");
    	System.out.println();
		this.AffichageAllStats();
		this.UtilisationAction();
    }
    
    
    public void InfosPNJ(World map)
    {
    	for(int i = 0; i < map.getTabPNJ().size(); i++)
    	{
    		map.ChoixPNJ(i).AffichageStatsCapPNJ();
    	}
    	
    	this.UtilisationAction();
    }
    
    public void LevelUp(PJ j)
    {
    	j.MonterNiveau();
    	this.AffichageAllStats();
		this.UtilisationAction();
    }
    
    
    public void CreerRep() throws IOException {
		String rep = this.nom;
		File newDirectory = new File(rep);
		boolean isCreated = newDirectory.mkdirs();
		if (isCreated) {
			System.out.printf("Sauvegarde réussi, vous pouvez retrouver vos fichier de sauvegarde à l'adresse :\n %s",newDirectory.getCanonicalPath());
			System.out.println();
			} else if (newDirectory.exists()) {
				System.out.println("Votre partie a bien été sauvegardée.");
			} else {
				System.out.println(
						"Une erreur est survenue, veuillez contacter le service après-vente si cette erreur persiste.");
				return;
			}
	}
    
    public void sauvegarderPartie() throws IOException {
		this.CreerRep();
		/*this.map.sauvegarder(this.nom);*/
		this.j.sauvegarder(this.nom);
    }	
    
    public void ChargerPartie(String nomPartie) {
		/*this.map.loadMap(nomPartie);*/
		this.map.AfficherMap();
		this.j.loadJoueur(nomPartie);
		this.j.AffichageStats();
	}
    
    public void OptionSL() {
		System.out.println();
		System.out.println("Veuillez choisir une option :");
		System.out.println("1 - Sauvegarder la partie");
		System.out.println("2 - Charger une partie");
		System.out.println("3 - Quitter la partie");
		System.out.print("Que choisissez vous ? (tapez 1, 2 ou 3) : ");
		Scanner sc = new Scanner(System.in);
		int choix = Integer.parseInt(sc.nextLine());
		if (choix == 1)
			try {
				this.sauvegarderPartie();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (choix == 2) 
		{
			System.out.println();
			System.out.println("Quelle partie voulez-vous charger ?");
			Scanner s = new Scanner(System.in);
			String choice = sc.nextLine();
			System.out.println(choice);
			/*if (choice.equals(this.nom))  {*/
			this.ChargerPartie(choice);
				System.out.println("Partie chargée");
			/*}*/
			/*else { System.out.println("La sauvegarde n'existe pas");
		}*/
		}
		else if (choix == 3)
		{
			
		}
	}
    
    
    public void UtiliserPotion() 
    {
    	// lié sac
    }
    
    
    
    
    
    public void UtilisationAction()
    {
    	
    	Scanner sc = new Scanner(System.in);
    	int choixAction;
    	System.out.println();
    	System.out.println("Vous avez " + this.pointAction + " point d'Action, vous pouvez : ");
    	System.out.println("1 - Vous déplacer (coût 2PA)");
    	System.out.println("2 - Gérer votre inventaire");
    	System.out.println("3 - Attaquer (coût 3PA)");
    	System.out.println("4 - Ramasser un objet");
    	System.out.println("5 - Monter de niveau");
    	System.out.println("6 - Voir les infos sur les ennemis");
    	System.out.println("7 - Terminer le tour");
    	System.out.println("8 - Voir les options du jeu");
    	// Option pour finir le tour ???
    	System.out.print("Que choisissez vous ? (tapez un chiffre entre 1 et 8) : ");
    	
    	choixAction = Integer.parseInt(sc.nextLine());
    	System.out.println();
    	
    	
    	while(choixAction > 8 || choixAction < 1)
		{
			System.out.println("La valeur entrée est incorrecte, veuillez réessayer.");
			System.out.print("Que choisissez vous ? (tapez un chiffre entre 1 et 6) : ");
			choixAction = Integer.parseInt(sc.nextLine());
			System.out.println();
		}
    	
    	if(choixAction == 1)
    	{
    		this.DeplacerPJ();
    	}
    	else if(choixAction == 2)
    	{
    		this.GestionInventaire();
    	}	
    	else if(choixAction == 3)
    	{
    		this.Attaquer(map.getEntities());
    	}
    	else if(choixAction == 4)
    	{
    		this.RamasserObjet(map.getEntities());
    	}
    	else if(choixAction == 5)
    	{
    		this.LevelUp(this.j);
    	}
    	else if(choixAction == 6)
    	{
    		this.InfosPNJ(this.map);
    	}
    	else if(choixAction == 7)
    	{
    		this.FinTour(this.map.getEntities(), this.map);
    	}
    	else if(choixAction == 8)
    	{
    		this.OptionSL();
    	}
    }    
}
