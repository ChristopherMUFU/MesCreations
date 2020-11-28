package Entities.DynamicEntity;

import Entities.StaticEntity.Objet;
import Entities.StaticEntity.VetementLambeaux;

public class Vampire extends PNJ 
{	
	public Vampire() {
		super();
		
	}
	
	public String NomPNJ()
	{
		this.nomPNJ = "Vampire";
		return (this.nomPNJ);
	}
    public String RepresentationPNJ()
    {
    	this.representation = " v ";
    	return (this.representation);
    }
    public int xpPNJ()
    {
    	this.nombreXP = 30;
    	return (this.nombreXP);
    }
    public void PVpnj()
    {
    	this.pointsVie = 60;
    }
    public void InitiativePNJ()
    {
    	this.valDinitiativePNJ = 2;
    	this.resteInitiativePNJ = 1;
    }
    public void DegatsPNJ()
    {
    	this.valDdegatsPNJ = 3;
    	this.resteDegatsPNJ = 0;
    }
    public void AttaquePNJ()
    {
    	this.valDattaquePNJ = 2;
    	this.resteAttaquePNJ = 2;
    }
    public void DefensePNJ()
    {
    	this.valDdefensePNJ = 1;
    	this.resteDefensePNJ = 1;
    }
    public void EsquivePNJ()
    {
    	this.valDesquivePNJ = 2;
    	this.resteEsquivePNJ = 4;
    }
    public Objet LootPNJ()
    {
    	VetementLambeaux vl = new VetementLambeaux();
    	vl.CreationArmure();
    	return vl;	
    }
    
}
