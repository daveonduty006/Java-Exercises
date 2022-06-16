
public class Vol {
	
	// les attributs de classe
	public static int nbVols= 0;
	
	// les attributs d'instance
	private int numero;
	private String destination;
	private Date depart;
	private int reservations;
	
	// les constructeurs
	Vol(int unNumero, String uneDestination, Date uneDate, int nbReservations) {
		numero= unNumero;
		destination= uneDestination;
		setDepart(uneDate);
		setReservations(nbReservations);
		nbVols++;
	}
	
	// les accesseurs
	public int getNumero() {
		return this.numero;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	public Date getDepart() {
		return this.depart;
	}
	
	public int getReservations() {
		return this.reservations;
	}
	
	//les mutateurs
	public void setDepart(Date uneDate) {
		this.depart= uneDate;
	}
	
	public void setReservations(int nbReservations) {
		this.reservations= nbReservations;
	}
	
	//les autres méthodes
	@Override
	public String toString() {
		return this.numero+"\t"+this.destination+"\t"+this.depart+"\t"+
			   this.reservations;
	}

}
