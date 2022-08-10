import java.io.*;

public class Livre implements Serializable, Comparable<Livre>{
	
	private static final long serialVersionUID = 2040890116313738038L;

	public static int nbLivres = 0;
	
	private int num;
	private String titre;
	private int auteur;
	private int annee;
	private int pages;
	private String categ;
	
	//

	Livre(){
	}
	
	Livre(int num, String titre, int auteur, int annee, int pages, String categ){ 
		this.setNum(num);
		this.setTitre(titre);
		this.setAuteur(auteur);
		this.setAnnee(annee);
		this.setPages(pages);
		this.setCategorie(categ);
		++nbLivres;
	}
	
	//
	
	public int getNum() {
		return this.num;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getAuteur() {
		return this.auteur;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	public String getCategorie() {
		return this.categ;
	}
	
	//
	
	public void setNum(int num) {
		if(num > 0) {
			this.num = num;
		}else {
			System.out.println("Numéro de livre invalide !");
		}
	}
	
	public void setTitre(String titre) {
		if (titre.length() > 16) {
			this.titre = titre.substring(0, 17)+"... ";
		}else {
			while (titre.length() < 21) {
				titre += '-';
			}
			this.titre = titre;
		}
	}
	
	public void setAuteur(int auteur) {
		if(auteur > 0) {
			this.auteur = auteur;
		}else {
			System.out.println("Numéro d'auteur invalide !");
		}
	}
	
	public void setAnnee(int annee) {
		if(annee > 0) {
			this.annee = annee;
		}else {
			System.out.println("Année de publication du livre invalide !");
		}
	}
	
	public void setPages(int pages) {
		if(pages > 0) {
			this.pages = pages;
		} else {
			System.out.println("Nombre de pages invalide !");
		}
	}
	
	public void setCategorie(String categ) {
		this.categ = categ;
	}
	
	//
	
	@Override
	public boolean equals(Object obj) {
		Livre autreLivre = (Livre) obj;
		if (this.num == autreLivre.num || this.auteur == autreLivre.auteur) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int compareTo(Livre unLivre){
		return (int) (this.num - unLivre.num);
	}

	@Override
	public String toString() {
		return this.num+"\t"+this.titre+"\t"+this.auteur+"\t"+
	           this.annee+"\t"+this.pages+"\t"+this.categ+"\n";
	}	

}
