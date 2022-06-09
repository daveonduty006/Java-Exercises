import java.text.DecimalFormat;

public class Produit {
	
	// attributs de classe
	public static DecimalFormat df= new DecimalFormat("0.00 $");

	// attributs d'instance
	private int numProd;
	private double prixProd;
	
	// constructeur paramétré
	public Produit(int numProd, double prixProd) {
		this.numProd= numProd;
		this.prixProd= prixProd;}
	
	// accesseurs
	public int getNumProd() {
		return this.numProd;}
	
	public double getPrixProd() {
		return this.prixProd;}
	
	// mutateurs
	public void setNumProd(int nouveauNumProd) {
		this.numProd= nouveauNumProd;}
	
	public void setPrixProd(double nouveauPrixProd) {
		this.prixProd= nouveauPrixProd;}
	
	// autres méthodes
	public String toString() {
		return "#"+this.numProd+"------>"+df.format(this.prixProd)+"\n";}
}
