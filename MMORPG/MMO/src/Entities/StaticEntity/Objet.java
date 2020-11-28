package Entities.StaticEntity;

import Entities.*;

public abstract class Objet extends Entity
{
	protected String nomObj;
	protected boolean statusEquipement = false;
	
	protected int[] tabCoor;
	//protected Map map;
	protected String nom;
	 
	protected int valDarme;
	protected int resteArme;
	protected int maniArme;
	protected int maniDarme;
	
	protected int valDarmure;
	protected int resteArmure;
	protected int poidsArmure;
	protected int poidsDarmure;
	
	protected int valDpotionBoost;
	protected int restePotionBoost;
	
	protected int pvReg;
	protected int degatsPotion;
	protected int valPA;
	
	protected int posXobj;
	protected int posYobj;
	
	public int[] getCoordonne()
	{
		return this.tabCoor;
	}
	public int getPosXobj()
	{
		return this.posXobj;
	}
	public int getPosYobj()
	{
		return this.posYobj;
	}
	public boolean getStatusEquip()
    {
        return(this.statusEquipement);
    }
    public void setStatusEquip(boolean equip)
    {
        this.statusEquipement = equip ;
    }   
	public boolean StatusObjet()
	{
		this.statusEquipement = false;
		return(this.statusEquipement);
	}
	public void RepresentationObj() 
	{
		this.representation = " o ";
	}
	
	public int getValPA()
	{
		return this.valPA;
	}
	
	public int getValDarme()
	{
		return this.valDarme;
	}
	public int getResteArme()
	{
		return this.resteArme;
	}
	public int getManiDarme()
	{
		return this.maniDarme;
	}
	public int getManiArme()
	{
		return this.maniArme;
	}
	
	public int getValDarmure()
	{
		return this.valDarmure;
	}
	public int getResteArmure()
	{
		return this.resteArmure;
	}
	public int getPoidsDarmure()
	{
		return this.poidsDarmure;
	}
	public int getPoidsArmure()
	{
		return this.poidsArmure;
	}
	
	
	public abstract String NomObjet();
	
	public abstract void AffichageObjet();
	
	/*public Objet getObjet(int x, int y)
	{
		if(this.getPosXobj() == x && this.getPosYobj() == y)
		{
			return this;
		}
		
		return null;
	}*/
	
	public void placer(Entity[][] entities) {
		  int min = 0;
		  int lmax = entities.length;
		  int lamax= entities[0].length;
		  int i =(int) Math.floor(Math.random() * (lmax - min +1)) + min;
		  int j =(int) Math.floor(Math.random() * (lamax - min +1)) + min;
		  while (i<=0 || i>entities.length-1 || j<=0 || j>entities[0].length-1) {
			  i =(int) Math.floor(Math.random() * (lmax - min +1)) + min;
			  j =(int) Math.floor(Math.random() * (lamax - min +1)) + min;
			  }
		  if (entities[i][j] == null) {
			  this.posXobj = i;
			  this.posYobj = j;
			  entities[i][j] = this;
			  
	}
	}
	
	public void placer(int h,int w,Entity[][] grille) 
    {
        h = h-1;
        w= w-1;
        
        if (h<0 || h>grille.length || w<0 || w>grille[0].length) 
        {
          return;
        }
        if (grille[h][w] == null) 
        {
        	this.posXobj = h;
            this.posYobj = w;
            grille[h][w] = this;
        }
    }

	
	public String toString() {
		return "Nom de l'objet"+ this.nomObj +"représentation:"+this.getRepresentation();
	}
}
