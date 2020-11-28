package GameSys;
import java.util.Random;

import Entities.Entity;
import Entities.DynamicEntity.*;
import Entities.StaticEntity.*;

public class CombatPJvsPNJ 
{
	private int firstAttaque;
	private int testAttaque;
	private int degats;
	private int pvRestant;
	private int xpRecup;
	
	private PJ j;
	private PNJ m;
	private int count;
	private Entity[][] entities;
	private World map;
	
	public CombatPJvsPNJ(PJ j, PNJ m, int count, Entity[][] grille, World map)
	{
		this.j = j;
		this.m = m;
		this.count = count;
		this.entities = grille;
		this.map = map;
	}
	
	public void CombatPJversPNJ() 
	{
		// Choix pour pls ennemi de choisir quel coté attaquer
    	Random random = new Random();
    	
    	System.out.println();
    	System.out.println("Vous passez à l'offensive.");
    	
    	this.firstAttaque = (random.nextInt(7) * j.getValDinitiative() + j.getResteInitiative()) - 
    						(random.nextInt(7) * m.getValDinitiativePNJ() + m.getResteInitiativePNJ());
    	if(this.firstAttaque >= 0)
    	{
    		System.out.println("L'ennemi n'était pas préparé de votre arrivée, vous en profiter pour attaquer.");
    		AttaquePJ(j, m, count, entities); // ajouter un multiplicateur + while
    	}
    	
    	else
    	{
    		System.out.println("L'ennemi vous vois venir et attaque en premier !");
    		AttaquePNJ(j, m);
    		System.out.println("Vous ripostez.");
    		AttaquePJ(j, m, count, entities);
    	}
	}
	
	public void CombatPNJversPJ() 
	{
		// Choix pour pls ennemi de choisir quel coté attaquer
    	Random random = new Random();
    	
    	this.firstAttaque = (random.nextInt(7) * m.getValDinitiativePNJ() + m.getResteInitiativePNJ()) - 
    						(random.nextInt(7) * j.getValDinitiative() + j.getResteInitiative());
    	if(this.firstAttaque >= 0)
    	{
    		System.out.println();
    		System.out.println("Un ennemi profite de votre inattention pour vous attaquer !");
    		AttaquePNJ(j, m); // ajouter un multiplicateur + while
    	}
    	
    	else
    	{
    		System.out.println("Vous repèrez un ennemi proche de vous et vous décidez de le prendre de court en attaquant le premier.");
    		AttaquePJ(j, m, count, entities);
    		System.out.println("L'ennemi riposte !");
    		AttaquePNJ(j, m);
    	}
	}
	
	public void AttaquePJ(PJ j, PNJ m, int count, Entity[][] grille)
	{
		int nbAtt = 0;
		while(count > 0)
		{
			nbAtt += 1;
			Random random = new Random();
			
			this.testAttaque = (random.nextInt(7) * j.getValDattaque() + j.getResteAttaque()) - 
							   (random.nextInt(7) * m.getValDesquivePNJ() + m.getResteEsquivePNJ());
			if(this.testAttaque >= 0)
	    	{
				System.out.println("Votre attaque " + nbAtt + " parvient à toucher l'ennemi.");
	    		this.degats = (random.nextInt(7) * j.getValDdegats() + j.getResteDegats()) - 
						   	  (random.nextInt(7) * m.getValDdefensePNJ() + m.getResteDefensePNJ());
	    		if(this.degats <= 0)
	    		{
	    			System.out.println("Votre attaque " + nbAtt + " a eu aucun effet sur l'ennemi.");
	    			count -= 1;
	    		}
	    		else
	    		{
	    			System.out.println("Vous infliger " + this.degats + " de dégâts.");
		    		this.pvRestant = m.getPointsVie() - this.degats;
		    		
		    		if(this.pvRestant <= 0)
		    		{
		    			System.out.println("L'ennemi est mort.");
		    			this.xpRecup = j.getPointXP() + m.getNombreXP();
		    			j.setPointXP(this.xpRecup);
		    			System.out.println("Vous remportez "+ m.getNombreXP() +"XP.");
		    			System.out.println();
		    			
		    			Objet loot = m.LootPNJ();
		    			grille[m.getPosXpnj()][m.getPosYpnj()] = null;
		    			loot.placer(m.getPosXpnj()+1, m.getPosYpnj()+1, grille);
		    			map.SupressionPNJ(m);
		    			map.EnregistrementObj(loot);
		    			map.AfficherMap();
		    			break;
		    		}
		    		
		    		else
		    		{
		    			m.setPointsVie(this.pvRestant);
		    			System.out.println("Il reste " + this.pvRestant + " PV à l'ennemi.");
		    			this.xpRecup = j.getPointXP() + 1;
		    			j.setPointXP(this.xpRecup);
		    			System.out.println("Vous gagnez 1XP.");
		    			System.out.println();
		    			count -= 1;
		    		}
	    		}	
	    	}
			else 
			{
				System.out.println("Votre attaque " + nbAtt + " rate l'ennemi.");
				System.out.println();
				count -= 1;
			}
		}
	}
	
	public void AttaquePNJ(PJ j, PNJ m)
	{
		Random random = new Random();
		
		this.testAttaque = (random.nextInt(7) * m.getValDattaquePNJ() + m.getResteAttaquePNJ()) - 
						   (random.nextInt(7) * j.getValDesquive() + j.getResteEsquive());
		if(this.testAttaque >= 0)
    	{
			System.out.println("L'ennemi parvient à vous toucher.");
    		this.degats = (random.nextInt(7) * m.getValDdegatsPNJ() + m.getResteDegatsPNJ()) - 
					   	  (random.nextInt(7) * j.getValDdefense() + j.getResteDefense());
    		if(this.degats <= 0)
    		{
    			System.out.println("L'attaque de l'ennemi n'a aucun effet sur vous.");
    		}
    		else
    		{
    			System.out.println("Vous subisssez "+ this.degats + " de dégâts.");
    			this.pvRestant = j.getPointsVie() - this.degats;
    			j.NiveauBlessure(this.pvRestant);
    			System.out.println("Il vous reste "+ this.pvRestant + " PV.");
        		j.setPointsVie(this.pvRestant);
        		System.out.println();
    		}
    		
    	}
		else 
		{
			System.out.println("Vous esquivez l'attaque ennemi.");
			System.out.println();
		}
	}
	
}

