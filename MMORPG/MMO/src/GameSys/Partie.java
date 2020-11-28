package GameSys;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*Classe Partie*/
public class Partie {
	private World map;
	private String nom;
	
	
	public Partie() {
		this.map = new World();
		this.nom="";
	}
	
	public Partie(World map,String nom) {
		this.map = map;
		this.nom = nom;
	}
	
	public void GenererMap() {
		this.map.CreerMap();
		this.map.ajoutObstacle();
		this.map.placerObjet();
		this.map.placerObjet();
		this.map.placerEnnemi();
		this.map.placerEnnemi();
	}
	
	public void sauvegarderPartie() throws IOException {
        String rep = "D:\\workspace\\"+this.nom;
        File newDirectory = new File(rep);
        boolean isCreated = newDirectory.mkdirs();
        if (isCreated) {
            System.out.printf("Sauvegarde réussi, vous pouvez retrouver vos fichier de sauvegarde à l'adresse :\n %s",
                    newDirectory.getCanonicalPath());
            System.out.println();
        } else if (newDirectory.exists()) {
        	System.out.println("Votre partie a bien été sauvegardée.");
        } else {
            System.out.println("Une erreur est survenue, veuillez contacter le service après-vente si cette erreur persiste.");
            return;
        }
		/*this.map.sauvegarder(this.nom);*/
	}
	
	public void EffacerPartie(String nomPartie) {
		
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		System.out.println("Création de la partie en cours....");
		Thread.sleep(2000);
		World m = new World(40,40);
		Partie a = new Partie(m,"brésil");
		System.out.println("Partie de "+a.nom+" créee.");
		Thread.sleep(2000);
		System.out.println("Génération de la map....");
		Thread.sleep(2000);
		a.GenererMap();
		a.map.AfficherMap();
		System.out.println("Sauvegarde de la partie en cours.....");
		Thread.sleep(2000);
		a.sauvegarderPartie();
	}

}
