package Entities.StaticEntity;

public abstract class Armures extends Objet
{
	public abstract String SoliditeArmure();
	public abstract String PoidsArmure();
	
	public void CreationArmure()
	{
		this.RepresentationObj();
		this.NomObjet();
		this.SoliditeArmure();
		this.PoidsArmure();
	}
	
	public void AffichageObjet()
	{
		System.out.println();
        System.out.println(" Stats de votre Armure :");
        System.out.println(" - Nom        : " + this.NomObjet());
        System.out.println(" - Solidité   : " + this.SoliditeArmure());
        System.out.println(" - Poids      : " + this.PoidsArmure());  
        System.out.println();
	}
}