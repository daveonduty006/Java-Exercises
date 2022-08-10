import java.io.*;
public class Vol implements Serializable, Comparable<Vol> {

	private static final long serialVersionUID = 7696732072175105687L;

	// attributs de classe
	public static int nbVols= 0;

	// attributs d'instance
	protected int num, res;
	protected char type;
	protected String destination;
	protected Date depart;
	protected Avion avion;

	// constructeurs
	Vol(int num) {
		this.num= num;
	}
	
	Vol(char type, int num, String dest, Date depart, int res, Avion avion) {
		this.type= type;
		this.num= num;
		setDestination(dest);
		this.depart= depart;
		setRes(res);
		this.avion= avion;
		nbVols++;
	}

	// accesseurs
	public char getType() {
		return this.type;
	}
	
	public int getNum() {
		return this.num;
	}

	public String getDestination() {
		return this.destination;
	}

	public Date getDepart() {
		return this.depart;
	}

	public int getRes() {
		return this.res;
	}
	
	public Avion getAvion() {
		return this.avion;
	}

	// mutateurs
	public void setDestination(String uneDestination) {
		if (uneDestination.length() == 16) {
			this.destination= uneDestination;
		}else if (uneDestination.length() > 16) {
			this.destination= uneDestination.substring(0, 16);
		}else if (uneDestination.length() < 16){
			while (uneDestination.length() != 16) {
				uneDestination += " ";
			}
			this.destination= uneDestination;
		}
	}
	
	public void setDepart(Date uneDate) {
		this.depart= uneDate;
	}
	
	public void setRes(int uneRes) {
		if (uneRes >= 0) {
			this.res= uneRes;
		}else {
			System.out.println("nombre de réservations invalide");
		}
	}

	// autres méthodes	
	// méthode pour écriture formatée dans le fichier
	public String ecritureFichier() {
		return this.type+";"+this.num+";"+this.destination+";"+
			   this.depart.ecritureFichier()+";"+this.res+";"+
			   this.avion.toString()+";";
	}	
	
	@Override
	public boolean equals(Object obj) {
		Vol autreVol= (Vol) obj;
		if (this.num == autreVol.getNum()) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Vol unVol) {
		return this.num - unVol.getNum();
	}
	
	@Override
	public String toString() {
		return this.type+"\t\t"+this.num+"\t\t"+this.destination+"\t"+ 
				   this.depart.toString()+"\t"+ this.res+"\t\t"+
				   this.avion.toString()+"\t\t";
	}

}