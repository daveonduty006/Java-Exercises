import java.io.*;

public class Avion implements Serializable {

	private static final long serialVersionUID = -8808570102272639678L;

	// attributs de classe
	public static int nbAvions= 0;
	
	// attributs d'instance
	private int num, places;
	private String type, rayon;
	private boolean classePremiere, classeAffaires, classeEconomique;
	
	// constructeur
	Avion(int num, String type, int places, String rayon, 
		  boolean classePremiere, boolean classeAffaires, 
		  boolean classeEconomique) {
		this.num= num;
		setType(type);
		setPlaces(places);
		setRayon(rayon);
		this.classePremiere= classePremiere;
		this.classeAffaires= classeAffaires;
		this.classeEconomique= classeEconomique;
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
	
	public boolean getClassePremiere() {
		return this.classePremiere;
	}
	
	public boolean getClasseAffaires() {
		return this.classeAffaires;
	}
	
	public boolean getClasseEconomique() {
		return this.classeEconomique;
	}
	
	// mutateurs
	public void setType(String unType) {
		if (unType.length() == 20) {
			this.type= unType;
		}else if (unType.length() > 20) {
			this.type= unType.substring(0, 20);
		}else if (unType.length() < 20){
			while (unType.length() != 20) {
				unType += " ";
			}
			this.type= unType;
		}
	}
	
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
	public String affichageJTA() {
		String rep= this.num+"\t\t"+this.type+"\t\t"+this.places+"\t\t"+this.rayon+"\t\t";
	    rep += this.classePremiere? "Oui\t\t\t" : "Non\t\t\t";
	    rep += this.classeAffaires? "Oui\t\t\t" : "Non\t\t\t";
	    rep += this.classeEconomique? "Oui\n" : "Non\n";
	    return rep;
	}
	
	@Override
	public boolean equals(Object obj) {
		Avion autreAvion= (Avion) obj;
		if (this.num == autreAvion.getNum()) {
				return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.num);
	}
		
}
