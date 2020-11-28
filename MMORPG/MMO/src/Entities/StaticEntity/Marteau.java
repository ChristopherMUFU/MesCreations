package Entities.StaticEntity;
import java.util.Random;

public class Marteau extends Armes
{
	public Marteau()
	{
		this.posXobj = 0;
		this.posYobj = 0;
	}
	
	public String NomObjet()
	{
		this.nomObj = "Marteau";
		return(this.nomObj);
	}
	
	public String DegatsArme()
	{
		Random random = new Random();
		this.valDarme = 2;
		this.resteArme = 0;	
		return(this.valDarme + "D + "+ this.resteArme);
	}
	
	public String ManiabiliteArme() 
	{
		this.maniDarme = 0;
		this.maniArme = 0;
		return(this.maniDarme + "D + "+ this.maniArme);
	}
}
