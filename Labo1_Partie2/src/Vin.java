import java.text.*;

public class Vin {
	
	// attributs de classe
	public static enum typesCouleur{ROUGE, BLANC, ROSE};
	public static int nbVins= 0;
	public static double prixTotal= 0;
	
	// attributs d'instance	
	private int type;
	private double prix;
	private String nom, origine;
	
	// constructeur paramétré de vin rouge
	Vin(String nom, String origine, double prix) { 
		this(nom, 1, origine, prix);}
	
	// constructeur par 4 paramètres	
	Vin(String nom, int type, String origine, double prix) {
		this.nom= nom;
		this.type= type;
		this.origine= origine;
		this.prix= prix;}
	
	// accesseurs	
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
	
	// autres methodes
	private String afficheCouleur() {
		Vin.typesCouleur TYPE_COULEUR= typesCouleur.values()[this.type-1];
		switch(TYPE_COULEUR) {
		
		}
	}
	
	public String toString() {
		String stringVin = this.afficheCouleur();
		DecimalFormat df= new DecimalFormat("0,00 $");
		return this.nom+" est un "+stringVin+" de "+this.origine+
			" et son prix est de "+df.format(this.prix);}
}
