package Entities.StaticEntity;
import java.util.*;
//import sysJeu.Map;

public class TapetteMouche extends Armes
{
	public TapetteMouche()
	{
		this.posXobj = 0;
		this.posYobj = 0;
		this.tabCoor = new int[]{posXobj, posYobj};
	}
	
	public String NomObjet()
	{
		this.nomObj = "Tapette à mouche";
		return(this.nomObj);
	}
	
	public String DegatsArme()
	{
		this.valDarme = 0;
		this.resteArme = 1;	
		return(this.valDarme + "D + "+ this.resteArme);
	}
	
	public String ManiabiliteArme() 
	{
		this.maniDarme = 1;
		this.maniArme = 0;
		return(this.maniDarme + "D + "+ this.maniArme);
	}
	
}
