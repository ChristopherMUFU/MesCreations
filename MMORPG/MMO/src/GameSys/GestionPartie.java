package GameSys;
import java.io.File;
import java.io.IOException;
import java.util.*;

import Entities.DynamicEntity.PJ;

public class GestionPartie {
	private World map;
	private String nom;
	private PJ j;
	private SacPJ sac;

	public GestionPartie() {
		this.map = null;
		this.nom = "";
	}

	public GestionPartie(World map, String nom) {
		this.map = map;
		this.nom = nom;
	}

	public void NouvellePartie() {
		j = new PJ();
		j.CreationPJ();
		this.DemarrerPartie();
	}
	
/*
 * Méthode permettant de sauvegarder la map et les stats d'un joueur selon sa partie
 */
	public void sauvegarderPartie() throws IOException {
		this.CreerRep();
		/*this.map.sauvegarder(this.nom);*/
		this.j.sauvegarder(this.nom);
}
	
	/*
	 * Méthode permettant de charger la map et les stats d'un joueur selon sa partie
	 */
	
	public void ChargerPartie(String nomPartie) {
		/*this.map.loadMap(nomPartie);*/
		this.map.AfficherMap();
		this.j.loadJoueur(nomPartie);
		this.j.AffichageStats();
	}
/*
 * Méthode qui permet de démarrer une partie
 */
	public void DemarrerPartie()
	{
		Scanner sc = new Scanner(System.in);
		int choixPartie;
		
		System.out.println("Vous voilà enfin prêt, souhaitez vous désormais");
		System.out.println("-1 Créer une partie");
		System.out.println("-2 Rejoindre une partie");
		System.out.print("Que choisissez vous ? (tapez 1 ou 2) : ");
		
		choixPartie = Integer.parseInt(sc.nextLine());
		
		while(choixPartie > 2 || choixPartie < 1)
		{
			System.out.println("La valeur entrée est incorrecte, veuillez réessayer.");
			System.out.print("Que choisissez vous ? (tapez 1 ou 2) : ");
			choixPartie = Integer.parseInt(sc.nextLine());
		}
		
		if(choixPartie == 1)
		{
			String nomServeur;
			System.out.println();
			System.out.print("Choisissez un nom à votre serveur : ");
			nomServeur = sc.nextLine();
			System.out.println();
			this.map = new World(10,10);
			this.map.GenererMap();
			j.placer(4, 4, this.map.getEntities());
			this.map.AfficherMap();
			// Explication règles du jeu
		}
		else if(choixPartie == 2)
		{
			this.map = new World(10,10);
			this.map.GenererMap();
			j.placer(4, 4, this.map.getEntities());
			this.map.AfficherMap();
		}
		else
		{
			
		}
	}
	
	public void JouerPartie()
	{
		ActionJoueur aj = new ActionJoueur(0,map,nom,j,j.getSacPJ());
		aj.GainPA();
		aj.UtilisationAction();
	}
	
	/*this.map.sauvegarder(this.nom);
	this.j.sauvegarder(this.nom);
	this.OptionSL();
	*/
	
	
	/*
	 * Méthode permettant de choisir entre deux options (charger ou sauvegarder)
	 */
	/*
	public void OptionSL() {
		System.out.println();
		System.out.println("Veuillez choisir une option :");
		System.out.println("1-Sauvegarder la partie");
		System.out.println("2-Charger une partie");
		Scanner sc = new Scanner(System.in);
		int choix = Integer.parseInt(sc.nextLine());
		if (choix == 1)
			try {
				this.sauvegarderPartie();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (choix == 2) 
		{
			System.out.println();
			System.out.println("Quelle partie voulez-vous charger ?");
			Scanner s = new Scanner(System.in);
			String choice = sc.nextLine();
			System.out.println(choice);
			if (choice.equals(this.nom))  {
				ChargerPartie(choice);
				System.out.println("Partie chargée");
			}*/
			/*else { System.out.println("La sauvegarde n'existe pas");
		}
		}
	}*/
	
	
	/*
	 * Méthode utilitaire permettant de créer un répertoire vide dans le répertoire par défaut (ici Projet Tutoré)
	 */
	public void CreerRep() throws IOException {
		String rep = this.nom;
		File newDirectory = new File(rep);
		boolean isCreated = newDirectory.mkdirs();
		if (isCreated) {
			System.out.printf("Sauvegarde réussi, vous pouvez retrouver vos fichier de sauvegarde à l'adresse :\n %s",newDirectory.getCanonicalPath());
			System.out.println();
			} else if (newDirectory.exists()) {
				System.out.println("Votre partie a bien été sauvegardée.");
			} else {
				System.out.println(
						"Une erreur est survenue, veuillez contacter le service après-vente si cette erreur persiste.");
				return;
			}
	}
	

}