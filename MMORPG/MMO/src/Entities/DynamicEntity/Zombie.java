package Entities.DynamicEntity;

import Entities.StaticEntity.*;

public class Zombie extends PNJ 
{	
	public Zombie() {
		super();
		
		
	}
	
	public String NomPNJ()
	{
		this.nomPNJ = "Zombie";
		return (this.nomPNJ);
	}
    public String RepresentationPNJ()
    {
    	this.representation = " z ";
    	return (this.representation);
    }
    public int xpPNJ()
    {
    	this.nombreXP = 10;
    	return (this.nombreXP);
    }
    public void PVpnj()
    {
    	this.pointsVie = 1;
    }
    public void InitiativePNJ()
    {
    	this.valDinitiativePNJ = 1;
    	this.resteInitiativePNJ = 0;
    }
    public void DegatsPNJ()
    {
    	this.valDdegatsPNJ = 1;
    	this.resteDegatsPNJ = 0;
    }
    public void AttaquePNJ()
    {
    	this.valDattaquePNJ = 1;
    	this.resteAttaquePNJ = 0;
    }
    public void DefensePNJ()
    {
    	this.valDdefensePNJ = 0;
    	this.resteDefensePNJ = 2;
    }
    public void EsquivePNJ()
    {
    	this.valDesquivePNJ = 0;
    	this.resteEsquivePNJ = 1;
    }
    public Objet LootPNJ()
    {
    	VetementLambeaux vl = new VetementLambeaux();
    	vl.CreationArmure();
    	return vl;	
    }
}
