import java.time.LocalDate;

public class Date
{
	private int jour;
	private int mois;
	private int an;
	
	static public String tabMois[] = {null,"Janvier","F�vrier","Mars","Avril","Mai","Juin","Juillet","Ao�t","Septembre","Octobre","Novembre","D�cembre"};
	static public LocalDate dateActuelle = LocalDate.now();
	
	public Date()
	{
		jour = mois = 1;
		an = 2000;
	}
	
	public Date(int jour, int mois, int an)
	{
		this.jour = jour;
		this.mois = mois;
		this.an = an;
	}
	// Les m�thodes set et get habituelles
	public int getJour() {
		return this.jour;
	}
	public int getMois() {
		return this.mois;
	}
	public int getAn() {
		return this.an;
	}
	//Valider trois 
	public static String validerDate(int jour, int mois, int an, boolean etat[]) {
		String message="";//Servira comme message par d�faut
		int nbJours=0;
		
		//Valider le mois
		if(mois < 1 || mois > 12) {
			etat[1]=false;
			message+="Mois "+mois+" n'est un mois valide [1-12]"+"\n";
		}else {etat[1] = true;} 
		
		//Valider le jour
		if (etat[1]) {
			nbJours=determinerNbJoursMois(mois, an);
			if(jour > nbJours || jour <= 0 ) {
				etat[0]=false;
				message+="Jour invalide pour le mois de "+tabMois[mois].toLowerCase()+"\n";
			}else {etat[0]=true;}
			
		}else {
			message+="Impossible de valider le jour puisque votre mois est invalide";
		}	
		
		
		
		
		//Valider Ann�e
		int anneActuelle = dateActuelle.getYear();
		if (an < anneActuelle) {
			etat[2]=false;
			message+="Ann�e "+an+ " ne peut pas �tre inf�rieure � l'ann�e actuelle, soit "+anneActuelle+"\n";
		}else {etat[2]=true;}
		return message;
	}
	
	public void setJour(int jour) {
		int nbJours=determinerNbJoursMois(this.mois, this.an);
		if(jour > nbJours || jour < 1) {
			System.out.println("Jour invalide pour le mois de "+tabMois[this.mois].toLowerCase());
		}else {this.jour = jour;}
	}
	
	public void setMois(int mois) {
		int nbJours;
		if(mois < 1 || mois > 12) {
			System.out.println("Mois "+mois+" n'est un mois valide [1-12]");
		}else { 
			nbJours=determinerNbJoursMois(mois, this.an);
			if (this.jour > nbJours) {
				System.out.println("Mois "+tabMois[mois].toLowerCase()+" n'est un mois valide pour le jour actuel du vol qu'est "+this.jour);
			}else { this.mois = mois; }
		}
	}
	
	public void setAn(int an) {
		if (an < 0) {
			System.out.println("Ann�e "+an+ " ne peut pas �tre une ann�e n�gative");
		}else {this.an = an;}
		//Ce code est bon pour le labo sur les Vols mais pas bon en g�n�ral
		/*int anneActuelle = dateActuelle.getYear();
		if (an < anneActuelle) {
			System.out.println("Ann�e "+an+ " ne peut pas �tre inf�rieure � l'ann�e actuelle, soit "+anneActuelle);
		}else {this.an = an;}*/
	}
	
	public static boolean estBissextile(int an)
	{
		return (an % 4 == 0 && an % 100 != 0) || (an % 400 == 0);
	}
		
	public static int determinerNbJoursMois(int mois, int an)
	{
		int nbJours;
		int tabJrMois[] = {0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if (mois == 2 && estBissextile(an))
			nbJours = 29;
		else
			nbJours = tabJrMois[mois];
		return nbJours;
	}
	
	public String toString()
	{
		String leJour, leMois;
		leJour = Utilitaires.ajouterCaractereGauche('0', 2, this.jour+"");
		leMois = Utilitaires.ajouterCaractereGauche('0', 2, this.mois+"");
		return leJour + "/" + leMois + "/" + this.an;
	}
} // fin de la classe Date