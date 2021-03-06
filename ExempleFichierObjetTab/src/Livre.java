
public class Livre {
	
	//Attributs de classe
	public static int nbLivres = 0;

	//Attributs d'instance
	private int num;
	private String titre;
	private int pages;
	
	//Constructeur par défault
	Livre(){
		++nbLivres;
	}
	
	//Constructeur par paramètres
	Livre(int num, String titre, int pages) {
		this.setNum(num); //validation de données pour attributs d'instance num et pages
		this.titre = titre;
		this.setPages(pages);
		++nbLivres;
	}
	
	//Constructeur paramétré avec valeur de pages indéterminée
	Livre(int num, String titre) {
		this(num, titre, 0); //appelle constructeur à 3 paramètres avec une valeur de pages indéterminée
	}
	
	//Constructeur de copie
	Livre(Livre unLivre) {
		this.num = unLivre.num;
		this.titre = unLivre.titre; // Constructeur de copie, alternative: utiliser nomObjet.clone
		this.pages = unLivre.pages;
		++nbLivres;
	}
	
	//Les accesseurs
	public int getNum() {
		return this.num;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	//Les mutateurs
	public void setNum(int num) {
		if (num > 0) {
			this.num = num;
		}else {
			System.out.println("numéro de livre invalide!");
		}
	}
	
	public void setTitre(String nouveauTitre) {
		this.titre = nouveauTitre;
	}
	
	public void setPages(int pages) {
		if (pages > 0) {
			this.pages = pages;
		}else {
			System.out.println("nombre de pages invalide");
		}
	}
	
	//Retourne le contenu d'un objet selon un format voulu
	public String toString() {
		if (this.titre.length() > 17) {
		return (this.num+"\t"+this.titre+"\t"+this.pages+"\n");	
		}else {
		return (this.num+"\t"+this.titre+"\t\t"+this.pages+"\n");
		}
	}
	
}
