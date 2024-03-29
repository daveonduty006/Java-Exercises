import java.io.*;
import javax.swing.*;
import java.util.ArrayList;

public class GestionLivres {
	
	static final String FICHIER_LIVRES= "src/data/livre.txt";
	static ArrayList<Livre> biblio;
	static BufferedReader tmpLivres;
	static JTextArea sortie;
	
	public static void main(String[] args) throws Exception {
		int choix;
		chargerLivres();
		do {
			choix= menu();
			switch(choix) {
				case 1:
					afficherListeLivres();
					break;
				case 2:
					System.out.println("TBD");
					break;
				case 3:
					System.out.println("TBD");
					break;
				case 4:
					System.out.println("TBD");
					break;	
				case 5:
					System.out.println("Merci d'avoir utilis� notre syst�me");
					break;
				default:
					System.out.println("Votre choix est invalide");
			}
		}while (choix != 5);
		System.exit(0);
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
			String ligne;
			String elems[]= new String[3];
			biblio= new ArrayList<Livre>();
			tmpLivres= new BufferedReader(new FileReader(FICHIER_LIVRES));	
			ligne= tmpLivres.readLine();
			while (ligne != null) { 
				elems= ligne.split(";");
				biblio.add(new Livre(Integer.parseInt(elems[0]), 
					elems[1], Integer.parseInt(elems[2])));
				ligne= tmpLivres.readLine();
			}			
		}catch(FileNotFoundException e) {
			System.out.println("Fichier introuvable, v�rifiez le chemin et "+
				"nom du fichier");			
		}catch(IOException e) {
			System.out.println("Un probl�me est arriv� lors de la manipulation"+ 
				" du fichier");
		}catch(Exception e) {
			System.out.println("Un probl�me est arriv� lors du chargement du"+
				" fichier");
		}finally { //ex�cute si erreur ou pas
			tmpLivres.close();
		}
	}
	
	public static void afficherListeLivres() {
		sortie= new JTextArea();
		sortie.append("\tLISTE DES LIVRES\n\n");
		sortie.append("NUM�RO\tTITRE\t\tPAGES\n");
		biblio.forEach((unLivre) -> sortie.append(unLivre.toString()));
		JOptionPane.showMessageDialog(null, sortie, null, 
			JOptionPane.PLAIN_MESSAGE);
	}
	
	public static int menu() {
		String options= "1. Liste des Livres\n2. Ajouter un livre\n"+
			"3. Retirer un livre\n4. Mettre � jour un livre\n5. Quitter\n"+
			"Choisissez une option";
		JOptionPane.showInputDialog(null, options, "MENU PRINCIPAL", 
			JOptionPane.PLAIN_MESSAGE);
		return Integer.parseInt(JOptionPane.showInputDialog(null, options, 
			"MENU PRINCIPAL", JOptionPane.PLAIN_MESSAGE));	
	}
	
}
