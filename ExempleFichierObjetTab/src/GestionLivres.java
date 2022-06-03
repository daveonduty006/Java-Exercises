
public class GestionLivres {

	public static void main(String[] args) {
		Livre biblio[] = new Livre[10];	
		Livre Livre1 = new Livre();
		Livre Livre2 = new Livre(100, "Titanic", 400);
		Livre Livre3 = new Livre(Livre2);
		//	
		biblio[0] = new Livre(200, "Le Soleil Vert", 350);
		biblio[1] = Livre2;
		biblio[2] = new Livre(biblio[0]);
		//
		for (Livre unLivre: biblio) {
			if (unLivre != null) {
				System.out.println(unLivre);
			}else {
				break;
			}
		}
		//
	}

}
