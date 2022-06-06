
public class Carre {
	
	private String couleur;
	private int cote;
	private int perimetre;

	public Carre(String coul, int nb) {
		this.couleur = coul;
		this.cote = nb;
		calculerPerimetre();
	}
	
	public String getCouleur() { 
		return this.couleur; 
	}
	
	public int getCote() { 
		return this.cote; 
	}
	
	public int getPerimetre() {
		return perimetre;
	}

	public void calculerPerimetre() {
		perimetre = this.cote * 4;
	}
	
}
