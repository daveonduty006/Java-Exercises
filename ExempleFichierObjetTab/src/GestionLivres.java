import java.io.*;
import javax.swing.*;

public class GestionLivres {
	
	static final int NB_LIVRES= 30;
	static final String FICHIER_LIVRES= "src/data/livre.txt";
	static Livre biblio[];
	static BufferedReader tmpLivres;
	static JTextArea sortie;
	
	public static void main(String[] args) throws Exception {
		chargerLivres();
		afficherListeLivres();
	   /*Livre Livre1 = new Livre();
		*Livre Livre2 = new Livre(100, "Titanic", 400);
		*Livre Livre3 = new Livre(Livre2);	
		*biblio[0] = new Livre(200, "Le Soleil Vert", 350);
		*biblio[1] = Livre2;
		*biblio[2] = new Livre(biblio[0]);
		*for (Livre unLivre: biblio) {
		*	if (unLivre != null) {
		*		System.out.println(unLivre);
		*	}else {
		*		break;
		*	}
		}*/
	}

	public static void chargerLivres() throws Exception {
		try {
			int i=0;
			String ligne;
			String elems[]= new String[3];
			biblio= new Livre[NB_LIVRES];
			tmpLivres= new BufferedReader(new FileReader(FICHIER_LIVRES));	
			ligne= tmpLivres.readLine();
			while (i < NB_LIVRES && ligne != null) { 
				elems= ligne.split(";");
				biblio[i++]= new Livre(Integer.parseInt(elems[0]), 
					elems[1], Integer.parseInt(elems[2]));
				ligne= tmpLivres.readLine();
			}
			
		}catch(FileNotFoundException e) {
			System.out.println("Fichier introuvable, vérifiez le chemin et nom du fichier");			
		}catch(IOException e) {
			System.out.println("Un problème est arrivé lors de la manipulation du fichier");
		}catch(Exception e) {
			System.out.println("Un problème est arrivé lors du chargement du fichier");
		}finally { //execute si erreur ou pas
			tmpLivres.close();
		}
	}
	
	public static void afficherListeLivres() {
		sortie= new JTextArea();
		int taille= Livre.nbLivres;
		sortie.append("\tLISTE DES LIVRES\n\n");
		sortie.append("NUMÉRO\tTITRE\t\tPAGES\n");
		for (int i=0; i < taille; i++) {
			sortie.append(biblio[i].toString());
		}
		JOptionPane.showMessageDialog(null, sortie, null, JOptionPane.PLAIN_MESSAGE);
	}
}
