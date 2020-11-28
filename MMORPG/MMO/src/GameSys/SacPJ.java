package GameSys;
import Entities.StaticEntity.Objet;

public class SacPJ 
{
	private Objet[] inventaire;
	private SacPJ sac;
	
	public SacPJ()
	{
		this.inventaire = new Objet[9];
	}
	
	public SacPJ(int taille)
	{
		this.inventaire = new Objet[taille];
	}
	
	public SacPJ(SacPJ invObj)
	{
		this.sac = invObj;
	}

	
	public Objet getInventaireID(int id)
    {
        return this.inventaire[id];
    }
	public void setInventaireID(int id, Objet obj)
    {
        this.inventaire[id] = obj;
    }
	
	public int getInventaireTaille()
    {
       return this.inventaire.length;
    }
	public void setInventaireTaille(int t)
    {
		this.inventaire = new Objet[t];
    }
	
	public void CreationInventaire()
	{
		for(int i = 0; i < this.inventaire.length; i++)
		{
			this.inventaire[i] = null;
		}		
	}
	
	
	
	public void AjoutObjet(Objet obj)
	{
		for(int i = 0; i < this.inventaire.length; i++)
		{
			if(this.inventaire[i] == null) 
			{
				this.inventaire[i] = obj;
				break;
			}
			else
			{
				//System.out.println(i);
			}
		}		
	}
	
	public void EquiperObjet(int e)
	{
		for(int i = 0; i < this.inventaire.length; i++)
		{
    		if(this.inventaire[i] == null){
    			
    		}
    		else 
    		{
    			if(e == (i+1)) 
    			{
    				this.inventaire[i].setStatusEquip(true);
    			}
    		}
		}
	}
	
	
	
	public void JeterObjet(int e)
	{
		for(int i = 0; i < this.inventaire.length; i++)
		{
    		if(this.inventaire[i] == null){
    			
    		}
    		else 
    		{
    			if(e == (i+1)) 
    			{
    				this.inventaire[i] = null;
    			}
    		}
		}
	}
	
	public void AffichageObjetEquip()
	{
		for(int i = 0; i < this.inventaire.length; i++)
		{
    		if(this.inventaire[i] == null){
    			
    		}
    		else if(this.inventaire[i].getStatusEquip() == true)
    		{
    			this.inventaire[i].AffichageObjet();
    		}
    		
		}
	}
	
	public void AffichageInventaire()
	{
		System.out.println();
		System.out.println("Votre inventaire : ");
		
		for(int i = 0; i < this.inventaire.length; i++)
		{
    		if(this.inventaire[i] == null){
    			
    		}
    		else if(this.inventaire[i].getStatusEquip() == true)
    		{
    			System.out.println((i+1) + " - " + this.inventaire[i].NomObjet() + " (équipé)");
    		}
    		else 
    		{
    			System.out.println((i+1) + " - " + this.inventaire[i].NomObjet());
    		}
		}
	}
	public static void main(String[] args)
    {
    	SacPJ sac = new SacPJ();
    	sac.CreationInventaire();
    	System.out.println(sac.getInventaireTaille());
    }
}
