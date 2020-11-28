package Entities.StaticEntity;
import java.util.*;

public class Dague extends Armes {
	public Dague() {
		this.posXobj = 0;
		this.posYobj = 0;
	}

	public String NomObjet() {
		this.nomObj = "Dague";
		return (this.nomObj);
	}

	public String DegatsArme() {
		this.valDarme = 0;
		this.resteArme = 4;
		return (this.valDarme + "D + " + this.resteArme);
	}

	public String ManiabiliteArme() {
		this.maniDarme = 1;
		this.maniArme = 2;
		return (this.maniDarme + "D + " + this.maniArme);
	}

}
