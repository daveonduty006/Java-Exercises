@SuppressWarnings("serial")
public class Fiction extends Emission {
	
	// attributs d'instance
	private String titre, realisateur;
	private int annee;
	private boolean estRediffusion;
	
	// constructeurs
	Fiction(int idEmission) {
		super(idEmission);
	}
	
	Fiction(char typeEmission, int idEmission, String nomEmission, 
			int heureDebut, int heureFin, String titre, int annee, 
			String realisateur, boolean estRediffusion) {
		super(typeEmission, idEmission, nomEmission, heureDebut, heureFin);
		setTitre(titre);
		this.annee= annee;
		setRealisateur(realisateur);
		this.estRediffusion= estRediffusion;
	}
	
	// accesseurs
    public String getTitre() {
        return this.titre;
    }
    
    public int getAnnee() {
        return this.annee;
    }
    
    public String getRealisateur() {
        return this.realisateur;
    }
    
    public boolean getEstRediffusion() {
        return this.estRediffusion;
    }
	
	// mutateurs
    public void setTitre(String unTitre) {
		if (unTitre.length() == 25) {
			this.titre= unTitre;
		}else if (unTitre.length() > 25) {
			this.titre= unTitre.substring(0, 25);
		}else if (unTitre.length() < 25) {
			while (unTitre.length() != 25) {
				unTitre += " ";
			}
			this.titre= unTitre;
		}
    }
    
    public void setAnnee(int uneAnnee) {
    	this.annee= uneAnnee;
    }
    
    public void setRealisateur(String unRealisateur) {
		this.realisateur= unRealisateur;
    }
    
    public void setEstRediffusion(boolean uneValeurBool) {
    	this.estRediffusion= uneValeurBool;
    }
	
	// autres méthodes
    @Override
    public String toString() {
        String rep = super.toString();
        if (this.realisateur.length() < 16) {
        	rep += this.titre+"\t"+this.annee+"\t"+this.realisateur+"\t\t";
        }else {
        	rep += this.titre+"\t"+this.annee+"\t"+this.realisateur+"\t";
        }
        rep += this.estRediffusion? "Oui\n" : "Non\n";
        return rep;
    }

}
