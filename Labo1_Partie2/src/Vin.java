import java.text.*;

public class Vin {
	
	// attributs de classe
	public static int nbVins= 0;
	public static double prixTotal= 0;
	public static String tabTypes[]= {"vin rouge", "vin blanc", "vin rosé"};
	
	// attributs d'instance	
	private int type;
	private double prix;
	private String nom, origine;
	
	// constructeur de vin rouge (par défaut)	
	Vin(String nom, String origine, double prix) { 
		this.nom= nom;
		this.origine= origine;
		this.prix= prix;
		this.type= 1;}
	
	// constructeur par 4 paramètres	
	Vin(String nom, int type, String origine, double prix) {
		this.nom= nom;
		this.type= type;
		this.origine= origine;
		this.prix= prix;}
	
	// accesseurs
	private String getStringVin() {
		try {
			return tabTypes[this.type-1];
		}catch (ArrayIndexOutOfBoundsException e) {
			return "{type invalide}";}}
	
	public String getNom() {
		return this.nom;}
	
	public int getType() {
		return this.type;}
	
	public String getOrigine() {
		return this.origine;}
	
	public double getPrix() {
		return this.prix;}
	
	// mutateurs
	public void setNom(String nouveauNom) {
		this.nom= nouveauNom;}
	
	public void setType(int nouveauType) {
		this.type= nouveauType;}
	
	public void setOrigine(String nouvelleOrigine) {
		this.origine= nouvelleOrigine;}
	
	public void setPrix(double nouveauPrix) {
		this.prix= nouveauPrix;}
	
	// autres méthodes
	public String toString() {
		String stringVin = this.getStringVin();
		DecimalFormat df= new DecimalFormat("0,00 $");
		return this.nom+" est un "+stringVin+" de "+this.origine+
			" et son prix est de "+df.format(this.prix);}
}
