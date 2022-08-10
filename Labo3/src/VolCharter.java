@SuppressWarnings("serial")
public class VolCharter extends Vol {

	// attributs de classe
	public static int nbVolsCH= 0;

	// attributs d'instance
	private boolean reservable, repas, bar, servicesPay, console, wifi, 
	                alimentation;
	
	// constructeur
	VolCharter(char type, int num, String dest, Date depart, int res,
			   Avion avion, boolean reservable, boolean repas, 
			   boolean bar, boolean servicesPay, boolean console, 
			   boolean wifi, boolean alimentation) {
		super(type, num, dest, depart, res, avion);
		this.reservable= reservable;
		this.repas= repas;
		this.bar= bar;
		this.servicesPay= servicesPay;
		this.console= console;
		this.wifi= wifi;
		this.alimentation= alimentation;
		nbVolsCH++;
	}
	
	// accesseurs	
	public boolean getReservable() {
		return this.reservable;
	}
	
	public boolean getRepas() {
		return this.repas;
	}
	
	public boolean getBar() {
		return this.bar;
	}
	
	public boolean getServicesPay() {
		return this.servicesPay;
	}
	
	public boolean getConsole() {
		return this.console;
	}
	
	public boolean getWifi() {
		return this.wifi;
	}
	
	public boolean getAlimentation() {
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
		return super.toString()+this.reservable+"\t"+this.repas+"\t"+
	           this.bar+"\t"+this.servicesPay+"\t"+this.console+"\t"+
			   this.wifi+"\t"+this.alimentation+"\n";
	}
	
}