package Entities.DynamicEntity;

import Entities.Entity;

abstract class Personnage extends Entity
{
// Attributs
    protected int pointsVie;
    protected int force;
    protected int adresse;
    protected int resistance;

    protected int valDforce;
    protected int resteForce;
    protected int valDadresse;
    protected int resteAdresse;
    protected int valDresistance;
    protected int resteResistance;
    
	private String lettre;

// Constructeurs
    public Personnage(){
        this.pointsVie = 100;
        this.force = 0;
        this.adresse = 0;
        this.resistance = 0;
    }

    public Personnage(String lettre,int pv, int force, int adresse, int resistence)
    {
    	this.setRepresentation(lettre);
        this.pointsVie = pv;
        this.force = force;
        this.adresse = adresse;
        this.resistance = resistence;
    }

// Accessseurs(à faire)
    public String getLettre() {
    	return this.lettre;
    }
    
    public void setLettre(String s) {
    	this.lettre=s;
    }
    public int getPointsVie()
    {
        return this.pointsVie;
    }
    public void setPointsVie(int pv)
    {
        this.pointsVie = pv;
    }

    public int getForce()
    {
        return this.force;
    }
    public void setForce(int f)
    {
        this.force = f;
    }

    public int getAdresse()
    {
        return this.adresse;
    }
    public void setAdresse(int a)
    {
        this.adresse = a;
    }

    public int getResistance()
    {
        return this.resistance;
    }
    public void setResistance(int r)
    {
        this.resistance = r;
    }

// Methodes
    public void ConversionD()
    {
        this.valDforce = 0;
        this.resteForce = 0;
        for(int i = this.force; i >= 3; i-=3)
        {
            this.valDforce += 1;
            this.resteForce = i;
        }
        if(this.resteForce == 0) // Cas ou le nb sélectionner est inf à 3
            this.resteForce = this.force;
        else
            this.resteForce -= 3;

        this.valDadresse = 0;
        this.resteAdresse = 0;
        for(int i = this.adresse; i >= 3; i-=3)
        {
            this.valDadresse += 1;
            this.resteAdresse = i;
        }
        if(this.resteAdresse == 0) // Cas ou le nb sélectionner est inf à 3
            this.resteAdresse = this.adresse;
        else
            this.resteAdresse -= 3;


        this.valDresistance = 0;
        this.resteResistance = 0;
        for(int i = this.resistance; i >= 3; i-=3)
        {
            this.valDresistance += 1;
            this.resteResistance = i;
        }
        if(this.resteResistance == 0) // Cas ou le nb sélectionner est inf à 3
            this.resteResistance = this.resistance;
        else
            this.resteResistance -= 3;
    }

    public void AffichageStats()
    {
        System.out.println();
        System.out.println(" Stats Personnage :");
        System.out.println(" - PV         : " + this.pointsVie);
        System.out.println(" - Force      : " + this.valDforce + "D + " + this.resteForce);
        System.out.println(" - Adresse    : " + this.valDadresse + "D + " + this.resteAdresse);
        System.out.println(" - Resistence : " + this.valDresistance + "D + " + this.resteResistance);
        System.out.println();
    }

    abstract void deplacer(String direction,Entity[][] grille);
}
