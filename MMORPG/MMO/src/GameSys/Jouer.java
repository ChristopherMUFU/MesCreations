package GameSys;
import Entities.DynamicEntity.PJ;

public class Jouer
{
    public static void main(String[] args)
    {
    	GestionPartie gp = new GestionPartie();
    	// Fonction CommencerPartie();
		gp.NouvellePartie();
		gp.JouerPartie();
    }
}
