import java.io.*;

public class Emission implements Serializable, Comparable<Emission> {

	private static final long serialVersionUID = -6980992278551668436L;
	
	// attributs d'instance
	protected char typeEmission;
	protected int idEmission, heureDebut, heureFin;
	protected String nomEmission;
	
	// constructeurs
	Emission(int idEmission) {
		this.idEmission= idEmission;
	}
	
	Emission(char typeEmission, int idEmission, String nomEmission, 
			 int heureDebut, int heureFin) {
		setTypeEmission(typeEmission);
		setIdEmission(idEmission); 
		setNomEmission(nomEmission);
		setHeureDebut(heureDebut);
		setHeureFin(heureFin);
	}
	
	// accesseurs
	public char getTypeEmission() {
		return this.typeEmission;
	}
	
	public int getIdEmission() {
		return this.idEmission;
	}
	
	public String getNomEmission() {
		return this.nomEmission;
	}
	
	public int getHeureDebut() {
		return this.heureDebut;
	}
	
	public int getHeureFin() {
		return this.heureFin;
	}
	
	// mutateurs
	public void setTypeEmission(char unTypeEmission) {
		if (unTypeEmission == 'R' || unTypeEmission == 'F') {
			this.typeEmission= unTypeEmission;
		}else {
			System.out.println("type d'émission invalide");
		}
	}
	
	public void setIdEmission(int unIdEmission) {
		this.idEmission= Integer.parseInt(
				         String.valueOf(unIdEmission).substring(0, 4));
	}
	
	public void setNomEmission(String unNomEmission) {
		if (unNomEmission.length() == 25) {
			this.nomEmission= unNomEmission;
		}else if (unNomEmission.length() > 25) {
			this.nomEmission= unNomEmission.substring(0, 25);
		}else if (unNomEmission.length() < 25) {
			while (unNomEmission.length() != 25) {
				unNomEmission += " ";
			}
			this.nomEmission= unNomEmission;
		}
	}
	
	public void setHeureDebut(int uneHeureDebut) {
		if (uneHeureDebut > 24 && uneHeureDebut < 0) {
			System.out.println("heure de début invalide");
		}else {
			this.heureDebut= uneHeureDebut;
		}
	}
	
	public void setHeureFin(int uneHeureFin) {
		if (uneHeureFin > 24 && uneHeureFin < 0) {
			System.out.println("heure de fin invalide");
		}else {
			this.heureFin= uneHeureFin;
		}
	}
	
	// autres méthodes	
	@Override
	public boolean equals(Object obj) {
		Emission autreEmission= (Emission) obj;
		if (this.idEmission == autreEmission.getIdEmission()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Emission uneEmission) {
		return this.idEmission - uneEmission.getIdEmission();
	}
	
	@Override
	public String toString() {
		return this.typeEmission+"\t"+this.idEmission+"\t"+
	           this.nomEmission+"\t"+this.heureDebut+"\t\t"+
			   this.heureFin+"\t\t";
	}
	
	
}
