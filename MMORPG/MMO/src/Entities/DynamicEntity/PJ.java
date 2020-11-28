package Entities.DynamicEntity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

import Entities.Entity;
import Entities.StaticEntity.*;
import GameSys.SacPJ;

public class PJ extends Personnage
{
// Attributs
    private int ptsCompetences;
    private String statuBlessure;

    private int pointXP;
    private int ptsXPrequis;
    private int niveauPJ;

    private int valDinitiative;
    private int valDdegats;
    private int valDattaque;
    private int valDdefense;
    private int valDesquive;

    private int resteInitiative;
    private int resteDegats;
    private int resteAttaque;
    private int resteDefense;
    private int resteEsquive;
    
    private SacPJ sac;
    private Objet[] inventaire;
    
    private int ligne;
    private int col;

// Constructeurs
    public PJ()
    {
        super();
        this.sac = new SacPJ();
        
    }

    public PJ(String lettre,int pv, int force, int adresse, int resistance)
    {
        super(lettre,pv, force, adresse, resistance);
    }
    

// Accesseurs(à faire)

    public int getPtsCompetences()
    {
        return this.ptsCompetences;
    }
    public void setPtsCompetences(int pc)
    {
        this.ptsCompetences = pc;
    }

    public int getPointXP()
    {
        return this.pointXP;
    }
    public void setPointXP(int px)
    {
        this.pointXP = px;
    }
    
    public int getValDinitiative()
    {
        return this.valDinitiative;
    }
    public int getValDdegats()
    {
        return this.valDdegats;
    }
    public int getValDattaque()
    {
        return this.valDattaque;
    }
    public int getValDdefense()
    {
        return this.valDdefense;
    }
    public int getValDesquive()
    {
        return this.valDesquive;
    }
    
    public int getResteInitiative()
    {
        return this.resteInitiative;
    }
    public int getResteDegats()
    {
        return this.resteDegats;
    }
    public int getResteAttaque()
    {
        return this.resteAttaque;
    }
    public int getResteDefense()
    {
        return this.resteDefense;
    }
    public int getResteEsquive()
    {
        return this.resteEsquive;
    }
    
    public SacPJ getSacPJ()
    {
    	return this.sac;
    }
    public void setSacPJ(SacPJ sac)
    {
    	this.sac = sac;
    }
    

	
	public int getLignePJ()
	{
		return this.ligne;
	}
	public int getColPJ()
	{
		return this.col;
	}

// Methodes
	public void CreationPJ() {
		this.ptsCompetences = 18;
		Scanner sc = new Scanner(System.in);
		// Texte plus consistant à faire
		System.out.println();
		System.out.println("Vous avez 18 points de compétences à répartir sur votre personnage");
		System.out.println("Veuillez choisir les stats de votre personnage (Force/ Adresse /Resistance)");
		System.out.println();

		System.out.print("Force (valeur entre 0 et " + this.ptsCompetences + ") : ");
		int force = Integer.parseInt(sc.nextLine());
		while (force < 0 || force > this.ptsCompetences) {
			System.out.println("Vous avez entrez une valeur non valide, veuillez réessayer");
			System.out.print("Force (valeur entre 0 et " + this.ptsCompetences + ") : ");
			force = Integer.parseInt(sc.nextLine());
		}
		this.ptsCompetences -= force;

		System.out.print("Adresse (valeur entre 0 et " + this.ptsCompetences + ") : ");
		int adresse = Integer.parseInt(sc.nextLine());
		while (adresse < 0 || adresse > this.ptsCompetences) {
			System.out.println("Vous avez entrez une valeur non valide, veuillez réessayer");
			System.out.print("Adresse (valeur entre 0 et " + this.ptsCompetences + ") : ");
			adresse = Integer.parseInt(sc.nextLine());
		}
		this.ptsCompetences -= adresse;

		System.out.print("Résistance (valeur entre 0 et " + this.ptsCompetences + ") : ");
		int resistance = Integer.parseInt(sc.nextLine());
		while (resistance < 0 || resistance > this.ptsCompetences) {
			System.out.println("Vous avez entrez une valeur non valide, veuillez réessayer");
			System.out.print("Résistance (valeur entre 0 et " + this.ptsCompetences + ") : ");
			resistance = Integer.parseInt(sc.nextLine());
		}
		this.ptsCompetences -= resistance;

		// Cas où il reste des points de compétences
		this.niveauPJ = 1;
		this.pointXP = 0;
		this.ptsXPrequis = 10;
		this.force = force;
		this.adresse = adresse;
		this.resistance = resistance;
		
		this.NiveauBlessure(this.pointsVie);
		this.ConversionD();
		this.setRepresentation(ChoixLettre());
		sac.setInventaireTaille(9);
		//System.out.println(sac.getInventaireTaille());
		this.ChoixEquipement(sac);
	}

	public String ChoixLettre() {
		Scanner sc = new Scanner(System.in);
		String l = "";
		System.out.println();
		System.out.print("Veuillez choisir une lettre majuscule pour votre joueur : ");
		l = sc.nextLine();
		String st = " "+ l + " ";
		this.representation = st;
		
		return representation;
	}

	public void ChoixEquipement(SacPJ sac)
    {
        Scanner sc = new Scanner(System.in);
        int valChoix;

        System.out.println();
        System.out.println("Nous vous proposons pour débuter la partie de choisir entre ces 3 armes : ");
        System.out.println(" 1 - Dague   : arme faible mais très maniable ");
        System.out.println(" 2 - Epée    : arme équilibrée en puissance et en maniabilité ");
        System.out.println(" 3 - Marteau : arme puissante mais peu maniable ");
        System.out.print("Quelle arme choisissez vous ? (Tapez 1, 2 ou 3) : ");
        valChoix = Integer.parseInt(sc.nextLine());
        System.out.println();
        
        while(valChoix > 3 || valChoix < 1)
        {
            System.out.println("La valeur entrée est incorrecte, veuillez réessayer.");
            System.out.print("Quelle arme choisissez vous ? (Tapez 1, 2 ou 3) : ");
            valChoix = Integer.parseInt(sc.nextLine());
        }
        if(valChoix == 1)
        {
        	Dague d = new Dague();
        	sac.AjoutObjet(d);
        	sac.EquiperObjet(1);
        	//d.AffichageObjet();
        	this.ConversionCap(sac);	
        }
        else if(valChoix == 2)
        {
        	Epee e = new Epee();
        	sac.AjoutObjet(e);
        	sac.EquiperObjet(1);
        	//e.AffichageObjet();
        	this.ConversionCap(sac);
        } 
        else if(valChoix == 3)
        {
        	Marteau m = new Marteau();
        	sac.AjoutObjet(m);
        	sac.EquiperObjet(1);
        	//m.AffichageObjet();
        	this.ConversionCap(sac);	
        }
    }
    
 


    public void ConversionCap(SacPJ sac)
    {   	
    	for(int i = 0; i < sac.getInventaireTaille(); i++)
		{
    		if(sac.getInventaireID(i) == null){
    			
    		}
    		else 
    		{
				if(sac.getInventaireID(i).getStatusEquip() == true)
				{
					this.valDinitiative = this.valDadresse - sac.getInventaireID(i).getPoidsDarmure();
			        this.valDdegats = this.valDforce + sac.getInventaireID(i).getValDarme();      // valDarme;
			        this.valDattaque = this.valDadresse + sac.getInventaireID(i).getManiDarme();    // poidsDarme;
			        this.valDdefense = this.valDresistance + sac.getInventaireID(i).getValDarmure();
			        this.valDesquive = this.valDadresse - sac.getInventaireID(i).getPoidsDarmure();
			        
			        this.resteInitiative = this.resteAdresse - sac.getInventaireID(i).getPoidsArmure(); 
			        this.resteDegats = this.resteForce + sac.getInventaireID(i).getResteArme();     // resteArme;
			        this.resteAttaque = this.resteAdresse + sac.getInventaireID(i).getManiArme();   // poidsArme;
			        this.resteDefense = this.resteResistance + sac.getInventaireID(i).getResteArmure();
			        this.resteEsquive = this.resteAdresse - sac.getInventaireID(i).getPoidsArmure();
				}
    		}				
		}
    	
    	//this.AffichageStats();	
    	//this.AffichageStatsCap();
    	
    }
	public void MonterNiveau() {
		Scanner sc = new Scanner(System.in);
		int choixJoueur;

		if (this.pointXP >= this.ptsXPrequis) 
		{
			this.pointXP -= this.ptsXPrequis;
			
			System.out.println("Vous gagnez 1 point de compétences à répartir sur votre personnage");
			System.out.println("Veuillez choisir la stat que vous voulez augmenter : ");
			System.out.println(" 1 - Force ");
			System.out.println(" 2 - Adresse ");
			System.out.println(" 3 - Resistance ");
			System.out.println();
			System.out.print("Quelle stat choisissez vous ? (Tapez 1, 2 ou 3) : ");

			choixJoueur = Integer.parseInt(sc.nextLine());

			while (choixJoueur > 3 || choixJoueur < 1)
			{
				System.out.println("La valeur entrée est incorrecte, veuillez réessayer.");
				System.out.print("Quelle stat choisissez vous ? (Tapez 1, 2 ou 3) : ");
				choixJoueur = Integer.parseInt(sc.nextLine());
			}

			if (choixJoueur == 1) {
				this.force += 1;
				this.ConversionD();
				System.out.println("Votre stat de Force a été augmenté.");
				this.ptsXPrequis += this.ptsXPrequis*2;
				this.niveauPJ += 1;
			} else if (choixJoueur == 2) {
				this.adresse += 1;
				this.ConversionD();
				System.out.println("Votre stat d'Adresse a été augmenté.");
				this.ptsXPrequis += this.ptsXPrequis*2;
				this.niveauPJ += 1;
			} else if (choixJoueur == 3) {
				this.resistance += 1;
				this.ConversionD();
				System.out.println("Votre stat de Résistance a été augmenté.");
				this.ptsXPrequis += this.ptsXPrequis*2;
				this.niveauPJ += 1;
			}
		}
		else
		{
			System.out.println("Vous n'avez pas encore assez de points d'XP.");
		}
	}

	public void NiveauBlessure(int pv) {
		// conditions pour chaque niveau de blessure
		// conditions pour autorisation des actions en fonc du niveau de blessure

		if (pv >= 100) // En forme
		{
			this.statuBlessure = "En forme";
			
		} 
		else if (pv > 90) // Blessures superficielles
		{
			this.statuBlessure = "Blessures superficielles";
			System.out.println("Vous avez des blessures superficielles.");
		} 
		else if (pv > 70) // Légèrement blessé
		{
			this.statuBlessure = "Légèrement blessé";
			System.out.println("Vous êtes légèrement blessé.");
		} 
		else if (pv > 50) // Blessé
		{
			this.statuBlessure = "Blessé";
			System.out.println("Vous êtes blessé.");
		} 
		else if (pv > 30) // Gravement blessé
		{
			this.statuBlessure = "Gravement blessé";
			System.out.println("Vous êtes gravement blessé.");
		} 
		else if (pv > 10) // Inconscient (joueur ne peut plus jouer/ att recup ou être soigné)
		{
			this.statuBlessure = "Inconscient";
			System.out.println("Vous êtes inconscient.");
			System.out.println("Vous devez attendre 3 tours avant de reprendre connaissance.");
		} 
		else if (pv <= 0)
		{
			this.statuBlessure = "Mort";
			System.out.println("Vous êtes mort.");
			// Choix nouvelle partie ou Charger partie
		}
	}
	
	

	public void AffichageStats() {
		System.out.println();
		System.out.println(" Stats de votre Personnage :");
		System.out.println(" - Niveau     : " + this.niveauPJ);
		System.out.println(" - Nombre XP  : " + this.pointXP + " (" + this.ptsXPrequis + "XP requis)");
		System.out.println(" - PV         : " + this.pointsVie + " (" + this.statuBlessure + ")");
		System.out.println(" - Force      : " + this.valDforce + "D + " + this.resteForce);
		System.out.println(" - Adresse    : " + this.valDadresse + "D + " + this.resteAdresse);
		System.out.println(" - Resistance : " + this.valDresistance + "D + " + this.resteResistance);
	}

	public void AffichageStatsCap() 
	{
		System.out.println(" Stats des Capacités de votre Personnage :");
		System.out.println(" - Initiative : " + this.valDinitiative + "D + " + this.resteInitiative);
		System.out.println(" - Dégâts     : " + this.valDdegats + "D + " + this.resteDegats);
		System.out.println(" - Attaque    : " + this.valDattaque + "D + " + this.resteAttaque);
		System.out.println(" - Defense    : " + this.valDdefense + "D + " + this.resteDefense);
		System.out.println(" - Esquive    : " + this.valDesquive + "D + " + this.resteEsquive);
		System.out.println();
	}
	/*
	 * Méthode permettant de déplacer un joueur sur une grille avec une direction donnée en paramètre (H,B,G,D)
	 */
	public void deplacer(String direction, Entity[][] grille) {
		Entity tmp = null;
		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille[0].length; j++) {
				if (estJoueur(grille,i,j)) {
					if (direction.equals("H") && grille[i - 1][j] == null && i - 1 > 0) {
						tmp = grille[i][j];
						grille[i][j] = null;
						grille[i - 1][j] = tmp;
						this.ligne = i-1;
				        this.col = j;
						return;
					} else if (direction.equals("B") && grille[i + 1][j] == null && i + 1 < grille.length) {
						tmp = grille[i][j];
						grille[i][j] = null;
						grille[i + 1][j] = tmp;
						this.ligne = i+1;
				        this.col = j;
						return;
					} else if (direction.equals("D") && grille[i][j + 1] == null && j + 1 < grille[0].length) {
						tmp = grille[i][j];
						grille[i][j] = null;
						grille[i][j + 1] = tmp;
						this.ligne = i;
				        this.col = j+1;
						return;
					} else if (direction.equals("G") && grille[1][j - 1] == null && j - 1 > 0) {
						tmp = grille[i][j];
						grille[i][j] = null;
						grille[i][j - 1] = tmp;
						this.ligne = i;
				        this.col = j-1;
						return;
					}
				}
			}
		}
	}

	public boolean estJoueur(Entity[][] grille,int x,int y) {
		return grille[x][y] instanceof PJ;
	}
	/*
	 * Méthode permettant de placer un PJ sur la grille avec une position(ligne,colonne) donnée en paramètre
	 */
	public void placer(int h, int w,Entity[][] entities) {
		h = h - 1;
		w = w - 1;
		if (h < 0 || h > entities.length || w < 0 || w > entities[0].length) {
			return;
		}
		if (entities[h][w] == null) {
			this.ligne = h;
	        this.col = w;
			entities[h][w] = this;
		}
	}
	
/*
	public void UtiliserPot(PotionSoin p) {
		if (this.getPointsVie()<=20) {
			System.out.println("Vous avez gagner " + (p.getSante() - this.getPointsVie()) +" de vie");
			this.setPointsVie(this.getPointsVie()+p.getSante());
		}
	}
*/

	/*
	 * Méthode permettant de sauvegarder les stats du joueur selon le nom de la partie
	 * 
	 */
	public void sauvegarder(String nomPartie) {

		try {
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(nomPartie + File.separator + "saveStatJoueurs" + nomPartie + ".txt"));
			bw.write(stockerStat());
			bw.close();
		} catch (Exception e) {
		}

	}

	
	/*
	 * Méthode permettant de charger un joueur depuis un fichier et le nom de la partie lui étant associée
	 */
	public void loadJoueur(String nomPartie) {
		try {
			BufferedReader br = new BufferedReader(
					new FileReader(nomPartie + File.separator + "saveStatsJoueurs" + nomPartie + ".txt"));
			String line = br.readLine();
			String[] tab = new String[CountFileLines(nomPartie)];
			int i = 0;
			while (br.readLine() != null) {
				line = br.readLine();
				tab[i] = line;
				i++;
			}
			this.setRepresentation(tab[0]);
			this.pointsVie = Integer.valueOf(tab[1]);
			this.force = Integer.valueOf(tab[2]);
			this.adresse = Integer.valueOf(tab[3]);
			this.resistance = Integer.valueOf(tab[4]);
			br.close();
		}

		catch (Exception e) {
		}

	}

	/*
	 * Méthode utilitaire permettant de compter les lignes d'un fichier
	 */
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
	 * Méthode permettant de vérifier si je joueur se trouve à proximité d'une action
	 */
	public boolean aproximitede() {
		return false;
	}

	/*
	 * Méthode utilitaire permettant de stocker dans une chaine de caractère les stats du PJ
	 */
	public String stockerStat() {
		String stats = "";
		stats = this.getRepresentation() + "\n";
		stats = stats + this.pointsVie + "\n";
		stats = stats + this.adresse + "\n";
		stats = stats + this.force + "\n";
		stats = stats + this.resistance + "\n";
		// Rajouter Cap
		return stats;
	}
	
	
}
