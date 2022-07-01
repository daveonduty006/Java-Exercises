public class Vol implements Comparable<Vol> {

	// attributs de classe
	public static int nbVols= 0;

	// attributs d'instance
	private int numVol, nbRes;
	private String destination;
	private Date depart;

	// constructeur par 4 paramètres
	Vol(int numVol, String dest, Date depart, int nbRes) {
		this.numVol = numVol;
		this.destination = dest;
		this.depart = depart;
		setNbRes(nbRes);
		nbVols++;
	}

	// accesseurs
	public int getNumVol() {
		return this.numVol;
	}

	public String getDestination() {
		return this.destination;
	}

	public Date getDepart() {
		return this.depart;
	}

	public int getNbRes() {
		return this.nbRes;
	}

	// mutateurs
	public void setDepart(Date uneDate) {
		this.depart = uneDate;
	}
	
	public void setNbRes(int unNbRes) {
		if (unNbRes >= 0) {
			this.nbRes = unNbRes;
		} else {
			System.out.println("nombre de réservations invalide");
		}
	}

	// autres méthodes	
	// méthode pour écriture formatée dans le fichier
	public String ecritureFichier() {
		return this.numVol+";"+this.destination+";"+
			   this.depart.ecritureFichier()+";"+this.nbRes+"\n";
	}	
	
	@Override
	public String toString() {
		if (this.destination.length() < 8) {
			return this.numVol+"\t"+this.destination+"\t\t\t"+ 
				   this.depart.toString()+"\t"+ this.nbRes+"\n";
		}else if (this.destination.length() < 15) {
			return this.numVol+"\t"+this.destination+"\t\t"+ 
				   this.depart.toString()+"\t"+ this.nbRes+"\n";
		}else {
			return this.numVol+"\t"+this.destination+"\t"+ 
				   this.depart.toString()+"\t"+ this.nbRes+"\n";
		}
	}

	@Override
	public int compareTo(Vol unVol) {
		return this.numVol - unVol.getNumVol();
	}

}
