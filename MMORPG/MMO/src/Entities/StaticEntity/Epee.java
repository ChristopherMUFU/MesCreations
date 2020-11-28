package Entities.StaticEntity;

import java.util.Random;

public class Epee extends Armes
{
	public Epee()
	{
		this.posXobj = 0;
		this.posYobj = 0;
	}
	
	public String NomObjet()
	{
		this.nomObj = "Epée";
		return(this.nomObj);
	}
	
	public String DegatsArme()
	{
		Random random = new Random();
		this.valDarme = 1;
		this.resteArme = 1;	
		return(this.valDarme + "D + "+ this.resteArme);
	}
	
	public String ManiabiliteArme() 
	{
		this.maniDarme = 0;
		this.maniArme = 3;
		return(this.maniDarme + "D + "+ this.maniArme);
	}
}
