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

	// constructeurs
	Vol(int num) {
		this.num= num;
	}
	
	Vol(char type, int num, String dest, Date depart, int res) {
		this.type= type;
		this.num= num;
		setDestination(dest);
		this.depart= depart;
		setRes(res);
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

	// mutateurs
	public void setDestination(String uneDestination) {
		if (uneDestination.length() > 20) {
			this.destination= uneDestination.substring(0, 20);
		}else {
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
			   this.depart.ecritureFichier()+";"+this.res+"\n";
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
		if (this.destination.length() < 8) {
			return this.type+"\t"+this.num+"\t"+this.destination+"\t\t\t"+ 
				   this.depart.toString()+"\t"+ this.res+"\t";
		}else if (this.destination.length() < 15) {
			return this.type+"\t"+this.num+"\t"+this.destination+"\t\t"+ 
				   this.depart.toString()+"\t"+ this.res+"\t";
		}else {
			return this.type+"\t"+this.num+"\t"+this.destination+"\t"+ 
				   this.depart.toString()+"\t"+ this.res+"\t";
		}
	}

}