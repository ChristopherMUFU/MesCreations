package Entities.StaticEntity;

public class VetementLambeaux extends Armures
{
	public VetementLambeaux()
	{
		this.posXobj = 0;
		this.posYobj = 0;
	}
	public String NomObjet()
	{
		this.nomObj = "Vêtement en lambeaux ";
		return (this.nomObj);
	}
	
	public String SoliditeArmure()
	{
		this.valDarmure = 0;
		this.resteArmure = 2;
		
		return (this.valDarmure +"D + "+ this.resteArmure);
	}
	
	public String PoidsArmure()
	{
		this.poidsDarmure = 0;
		this.poidsArmure = 1;
		
		return (this.poidsDarmure +"D + "+ this.poidsArmure);
	}
	
	
}
