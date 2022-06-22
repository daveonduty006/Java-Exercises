public class Livre implements Comparable<Livre>{
	
	// attributs de classe
	public static String tabCategs[]= {"Horreur", "Drame", "Comédie", 
									   "Jeunesse", "Poésie", "Biographie",
									   "Cuisine"};
	public static int nbLivres= 0;
	
	// attributs d'instance
	private int num;
	private String titre;
	private int pages;
	private int categorie;
	
	// constructeurs
	Livre() {
		nbLivres++;
	}
	
	Livre(int num, String titre, int pages, int categorie) {
		this.setNum(num);
		this.titre= titre;
		this.setPages(pages);
		this.setCategorie(categorie);
		nbLivres++;
	}
	
	// accesseurs
	public int getNum() {
		return this.num;
	}
	
	public String getTitre() {
		return this.titre;
	}
	
	public int getPages() {
		return this.pages;
	}
	
	public int getCategorie() {
		return this.categorie;
	}
	
	// mutateurs
	public void setNum(int num) {
		if (num > 0) {
			this.num= num;
		}else {
			System.out.println("numéro de livre invalide!");
		}
	}
	
	public void setTitre(String titre) {
		this.titre= titre;
	}
	
	public void setPages(int pages) {
		if (pages > 0) {
			this.pages= pages;
		}else {
			System.out.println("nombre de pages invalide!");
		}
	}
	
	public void setCategorie(int categorie) {
		int nbCategs= tabCategs.length;
		if (categorie >= 0 && categorie < nbCategs) {
			this.categorie= categorie;
		}else {
			System.out.println("catégorie de livre invalide!");
		}
	}
	
	// autres méthodes
	public String setCategorieString() {
		return tabCategs[this.categorie];
	}
	
	@Override
	public int compareTo(Livre unLivre) {
		return this.num - unLivre.num;
	}
	
	@Override
	public String toString() {
		if (this.titre.length() > 15) {
			return this.num+"\t"+this.titre+"\t"+this.pages+"\t"+
				setCategorieString()+"\n";
		}else if (this.titre.length() > 7) {
			return this.num+"\t"+this.titre+"\t\t"+this.pages+"\t"+
				setCategorieString()+"\n";
		}else {
			return this.num+"\t"+this.titre+"\t\t\t"+this.pages+"\t"+
				setCategorieString()+"\n";
		}
	}
	
}
