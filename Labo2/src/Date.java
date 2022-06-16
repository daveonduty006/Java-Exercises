
public class Date {
	
	// les attributs d'instance
	private int jour;
	private int mois;
	private int an;
	
	// les constructeurs
	Date() { 
	}
	
	Date(int unJour, int unMois, int unAn) {
		this.jour= unJour;
		this.mois= unMois;
		this.an= unAn;
	}
	
	// les accesseurs
	public int getJour() {
		return this.jour;
	}
	
	public int getMois() {
		return this.mois;
	}
	
	public int getAn() {
		return this.an;
	}
	
	//les autres méthodes
	@Override
	public String toString() {
		return this.jour+"/"+this.mois+"/"+this.an;
	}
		
}
