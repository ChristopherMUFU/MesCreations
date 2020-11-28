package Entities.DynamicEntity;

import java.util.*;
import Entities.*;
import Entities.StaticEntity.*;


public abstract class PNJ extends Personnage
{
// Attributs
	protected String nomPNJ;
	protected String representationPNJ;
    protected int nombreXP; // Dans les stats de bases

    protected int valDinitiativePNJ;
    protected int valDdegatsPNJ;
    protected int valDattaquePNJ;
    protected int valDdefensePNJ;
    protected int valDesquivePNJ;

    protected int resteInitiativePNJ;
    protected int resteDegatsPNJ;
    protected int resteAttaquePNJ;
    protected int resteDefensePNJ;
    protected int resteEsquivePNJ;
    
    protected int posXpnj;
	protected int posYpnj;


// Constructeurs
    public PNJ()
    {
        super();
        this.nomPNJ = "";
		this.representation = "";
		this.nombreXP = 0;
		
		this.valDinitiativePNJ = 0;
    	this.resteInitiativePNJ = 0;
    	
    	this.valDdegatsPNJ = 0;
    	this.resteDegatsPNJ = 0;
    	
    	this.valDattaquePNJ = 0;
    	this.resteAttaquePNJ = 0;
    	
    	this.valDdefensePNJ = 0;
    	this.resteDefensePNJ = 0;
    	
    	this.valDesquivePNJ = 0;
    	this.resteEsquivePNJ = 0;
    }

    public PNJ(String lettre, int pv, int force, int adresse, int resistance)
    {
        super(lettre, pv, force, adresse, resistance);
    }

// Accesseurs
    
    public String getRepresentationPNJ() 
    {
    	return this.representationPNJ;
    }
    public int getNombreXP()
    {
    	return this.nombreXP;
    }
    public int getValDinitiativePNJ()
    {
        return this.valDinitiativePNJ;
    }
    public int getValDdegatsPNJ()
    {
        return this.valDdegatsPNJ;
    }
    public int getValDattaquePNJ()
    {
        return this.valDattaquePNJ;
    }
    public int getValDdefensePNJ()
    {
        return this.valDdefensePNJ;
    }
    public int getValDesquivePNJ()
    {
        return this.valDesquivePNJ;
    }
    
    public int getResteInitiativePNJ()
    {
        return this.resteInitiativePNJ;
    }
    public int getResteDegatsPNJ()
    {
        return this.resteDegatsPNJ;
    }
    public int getResteAttaquePNJ()
    {
        return this.resteAttaquePNJ;
    }
    public int getResteDefensePNJ()
    {
        return this.resteDefensePNJ;
    }
    public int getResteEsquivePNJ()
    {
        return this.resteEsquivePNJ;
    }
    public int getPosXpnj()
	{
		return this.posXpnj;
	}
	public int getPosYpnj()
	{
		return this.posYpnj;
	}

// Méthodes
    
    public abstract String NomPNJ();
    public abstract String RepresentationPNJ();
    public abstract int xpPNJ();
    //public abstract int PointsViePNJ();
    public abstract void InitiativePNJ();
    public abstract void DegatsPNJ();
    public abstract void AttaquePNJ();
    public abstract void DefensePNJ();
    public abstract void EsquivePNJ();
    public abstract void PVpnj();
    public abstract Objet LootPNJ();
    
    public void GainXP()
    {
    	// à voir
    }
    
    public void CreationPNJ()
    {
    	this.NomPNJ();
    	this.RepresentationPNJ();
    	this.xpPNJ();
    	this.PVpnj();
    	this.InitiativePNJ();
    	this.DegatsPNJ();
    	this.AttaquePNJ();
    	this.DefensePNJ();
    	this.EsquivePNJ();
    	this.LootPNJ();
    }


    public void AffichageStatsCapPNJ()
    {
        System.out.println(" Stats des Capacités du "+ this.nomPNJ +" :");
        System.out.println(" - Représentaion : " + this.representation);
        System.out.println(" - PV            : " + this.pointsVie);
        System.out.println(" - Initiative    : " + this.valDinitiativePNJ + "D + " + this.resteInitiativePNJ);
        System.out.println(" - Dégâts        : " + this.valDdegatsPNJ + "D + " + this.resteDegatsPNJ);
        System.out.println(" - Attaque       : " + this.valDattaquePNJ + "D + " + this.resteAttaquePNJ);
        System.out.println(" - Defense       : " + this.valDdefensePNJ + "D + " + this.resteDefensePNJ);
        System.out.println(" - Esquive       : " + this.valDesquivePNJ + "D + " + this.resteEsquivePNJ);
        System.out.println(" - Nombre XP     : " + this.nombreXP);
        System.out.println();
    }
    
    
	public void deplacer(String direction, Entity[][] grille) {
	}

	public void placer(Entity[][] entities) {
		int min = 0;
		int lmax = entities.length - 1;
		int lamax = entities[0].length - 1;
		int i = (int) Math.floor(Math.random() * (lmax - min + 1)) + min;
		int j = (int) Math.floor(Math.random() * (lamax - min + 1)) + min;
		while (i <= 0 || i >= entities.length - 1 || j <= 0 || j >= entities[0].length - 1) {
			i = (int) Math.floor(Math.random() * (lmax - min + 1)) + min;
			j = (int) Math.floor(Math.random() * (lamax - min + 1)) + min;
		}
		if (entities[i][j] == null) {
			this.posXpnj = i;
			this.posYpnj = j;
			entities[i][j] = this;
		}
	}
	public void placer(int h,int w,Entity[][] grille) 
    {
        h = h-1;
        w= w-1;
        
        if (h<0 || h>grille.length || w<0 || w>grille[0].length) 
        {
          return;
        }
        if (grille[h][w] == null) 
        {
        	this.posXpnj = h;
            this.posYpnj = w;
            grille[h][w] = this;
        }
    }
	/*Méthode qui permet de placer un PNJ à la position que l'on veut pour faire des tests. 
	 * Entrée: grille 
	 * Sortie : Grile mise à jour
	 */
	/*public void placer(String[][] grille) {
		grille[6][6] =this.getLettre();
	}*/
}
