package Entities.StaticEntity;
import java.util.*;

public class TenueCuir extends Armures
{
	public TenueCuir()
	{
		this.posXobj = 0;
		this.posYobj = 0;
	}
	public String NomObjet()
	{
		this.nomObj = "Tenue en cuir";
		return (this.nomObj);
	}
	
	public String SoliditeArmure()
	{
		this.valDarmure = 1;
		this.resteArmure = 2;
		
		return (this.valDarmure +"D + "+ this.resteArmure);
	}
	
	public String PoidsArmure()
	{
		this.poidsDarmure = 0;
		this.poidsArmure = 3;
		
		return (this.poidsDarmure +"D + "+ this.poidsArmure);
	}
	
	
}
