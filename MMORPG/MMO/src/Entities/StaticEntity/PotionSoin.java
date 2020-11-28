package Entities.StaticEntity;

public abstract class PotionSoin extends Potion 
{
	public abstract int PVreg();
	
	// Fonction pour créer la potion !!!
	
	public void AffichageObjet()
	{
		System.out.println();
        System.out.println(" Caractéristiques de votre Potion :");
        System.out.println(" - Nom           : " + this.NomObjet());
        System.out.println(" - PV régénéré   : "+ this.PVreg());
        System.out.println();
	}
	
}