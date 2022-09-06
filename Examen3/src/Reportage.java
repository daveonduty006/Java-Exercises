@SuppressWarnings("serial")
public class Reportage extends Emission {

	// attributs d'instance
	private String sujet, presentateur;
	
	// constructeurs
	Reportage(int idEmission) {
		super(idEmission);
	}
	
	Reportage(char typeEmission, int idEmission, String nomEmission, 
			  int heureDebut, int heureFin, String sujet, String presentateur) {
		super(typeEmission, idEmission, nomEmission, heureDebut, heureFin);
		this.sujet= sujet;
		this.presentateur= presentateur;
	}
	
	// accesseurs
    public String getSujet() {
        return this.sujet;
    }
    
    public String getPresentateur() {
        return this.presentateur;
    }
	
	// mutateurs
    public void setSujet(String unSujet) {
        this.sujet = unSujet;
    }
    
    public void setPresentateur(String unPresentateur) {
        this.presentateur = unPresentateur;
    }
	
	// autres méthodes
    @Override
    public String toString() {
        String rep = super.toString();
        rep += this.sujet+"\t\t"+this.presentateur+"\n";
        return rep;
    }
    
}
