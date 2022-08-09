@SuppressWarnings("serial")
public class VolBasPrix extends Vol {

	// attributs de classe
	public static int nbVolsBP= 0;

	// attributs d'instance
	private Avion numAvion;
	private boolean reservable, repas, bar, servicesPay, console, wifi, 
	                alimentation;
	
	// constructeur
	VolBasPrix(char type, int num, String dest, Date depart, int res,
			   Avion numAvion, boolean reservable, boolean repas, 
			   boolean bar, boolean servicesPay, boolean console, 
			   boolean wifi, boolean alimentation) {
		super(type, num, dest, depart, res);
		this.numAvion= numAvion;
		this.reservable= reservable;
		this.repas= repas;
		this.bar= bar;
		this.servicesPay= servicesPay;
		this.console= console;
		this.wifi= wifi;
		this.alimentation= alimentation;
		nbVolsBP++;
	}
	
	// accesseurs
	public Avion getNumAvion() {
		return this.numAvion;
	}
	
	public boolean reservable() {
		return this.reservable;
	}
	
	public boolean repas() {
		return this.repas;
	}
	
	public boolean bar() {
		return this.bar;
	}
	
	public boolean servicesPay() {
		return this.servicesPay;
	}
	
	public boolean console() {
		return this.console;
	}
	
	public boolean wifi() {
		return this.wifi;
	}
	
	public boolean alimentation() {
		return this.alimentation;
	}
	
	// mutateurs
	public void setReservable(boolean valeur) {
		this.reservable= valeur;
	}
	
	public void setRepas(boolean valeur) {
		this.repas= valeur;
	}
	
	public void setBar(boolean valeur) {
		this.bar= valeur;
	}
	
	public void setServicesPay(boolean valeur) {
		this.servicesPay= valeur;
	}
	
	public void setConsole(boolean valeur) {
		this.console= valeur;
	}
	
	public void setWifi(boolean valeur) {
		this.wifi= valeur;
	}
	
	public void setAlimentation(boolean valeur) {
		this.alimentation= valeur;
	}
	
	// autres méthodes
	@Override
	public String toString() {
		return super.toString()+this.numAvion.toString()+"\t"+
	           this.reservable+"\t"+this.repas+"\t"+this.bar+"\t"+
	           this.servicesPay+"\t"+this.console+"\t"+this.wifi+"\t"+
	           this.alimentation+"\n";
	}
	
}