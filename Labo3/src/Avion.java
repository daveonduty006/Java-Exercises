import java.io.*;
public class Avion implements Serializable {

	private static final long serialVersionUID = -8808570102272639678L;

	// attributs de classe
	public static int nbAvions= 0;
	
	// attributs d'instance
	private int num, places;
	private String type, rayon;
	private boolean[] classes= new boolean[3];
	
	// constructeur
	Avion(int num, String type, int places, String rayon, boolean[] classes) {
		this.num= num;
		this.type= type;
		setPlaces(places);
		setRayon(rayon);
		this.classes= classes;
		nbAvions++;
	}
	
	// accesseurs
	public int getNum() {
		return this.num;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getPlaces() {
		return this.places;
	}
	
	public String getRayon() {
		return this.rayon;
	}
	
	public boolean[] getClasses() {
		return classes;
	}
	
	// mutateurs
	public void setPlaces(int places) {
		if (places > 0) {
			this.places= places;
		}else {
			System.out.println("nombre de places invalide");
		}
	}
	
	public void setRayon(String rayon) {
		if (rayon.equalsIgnoreCase("Court-Courrier") || 
			rayon.equalsIgnoreCase("Moyen-Courrier") ||
			rayon.equalsIgnoreCase("Long-Courrier")) {
			this.rayon= rayon;
		}else {
			System.out.println("rayon d'action invalide");
		}
	}
	
	// autres méthodes
	@Override
	public String toString() {
		return String.valueOf(this.num);
	}
		
}
