package Entities.DynamicEntity;

import Entities.StaticEntity.*;


public class Demon extends PNJ {
	
	public Demon() {
		super();
	}
	
	public String NomPNJ()
	{
		this.nomPNJ = "Demon";
		return (this.nomPNJ);
	}
    public String RepresentationPNJ()
    {
    	this.representation = " d ";
    	return (this.representation);
    }
    public int xpPNJ()
    {
    	this.nombreXP = 50;
    	return (this.nombreXP);
    }
    public void PVpnj()
    {
    	this.pointsVie = 100;
    }
    public void InitiativePNJ()
    {
    	this.valDinitiativePNJ = 3;
    	this.resteInitiativePNJ = 2;
    }
    public void DegatsPNJ()
    {
    	this.valDdegatsPNJ = 4;
    	this.resteDegatsPNJ = 3;
    }
    public void AttaquePNJ()
    {
    	this.valDattaquePNJ = 3;
    	this.resteAttaquePNJ = 0;
    }
    public void DefensePNJ()
    {
    	this.valDdefensePNJ = 2;
    	this.resteDefensePNJ = 0;
    }
    public void EsquivePNJ()
    {
    	this.valDesquivePNJ = 3;
    	this.resteEsquivePNJ = 2;
    }
    public Objet LootPNJ()
    {
    	VetementLambeaux vl = new VetementLambeaux();
    	vl.CreationArmure();
    	return vl;	
    }

}