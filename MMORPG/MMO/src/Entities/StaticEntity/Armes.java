package Entities.StaticEntity;

public abstract class Armes extends Objet
{
	public abstract String DegatsArme();
	public abstract String ManiabiliteArme();
	
	
	public void CreationArme()
	{
		this.RepresentationObj();
		this.NomObjet();
		this.DegatsArme();
		this.ManiabiliteArme();
	}
	
	public void AffichageObjet()
	{
		System.out.println();
        System.out.println(" Stats de votre Arme :");
        System.out.println(" - Nom         : " + this.NomObjet());
        System.out.println(" - Dégâts      : " + this.DegatsArme());
        System.out.println(" - Maniabilité : " + this.ManiabiliteArme());  
        System.out.println();
	}

	
}
