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
					afficherMessage("TBD");
					break;
				case 3:
					afficherMessage("TBD");
					break;
				case 4:
					afficherMessage("TBD");
					break;	
				case 5:
					afficherMessage("Merci d'avoir utilisé notre système");
					break;
				default:
					afficherMessage("Votre choix est invalide");
			}
		}while (choix != 5);
		System.exit(0);
	}
	
	public static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", 
				JOptionPane.PLAIN_MESSAGE);
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
			System.out.println("Fichier introuvable, vérifiez le chemin et "+
				"nom du fichier");			
		}catch(IOException e) {
			System.out.println("Un problème est arrivé lors de la manipulation"+ 
				" du fichier");
		}catch(Exception e) {
			System.out.println("Un problème est arrivé lors du chargement du"+
				" fichier");
		}finally { //exécute si erreur ou pas
			tmpLivres.close();
		}
	}
	
	public static void afficherListeLivres() {
		sortie= new JTextArea();
		sortie.append("\tLISTE DES LIVRES\n\n");
		sortie.append("NUMÉRO\tTITRE\t\tPAGES\n");
		biblio.forEach((unLivre) -> sortie.append(unLivre.toString()));
		JOptionPane.showMessageDialog(null, sortie, null, 
			JOptionPane.PLAIN_MESSAGE);
	}
	
	public static int menu() {
		String options= "1. Liste des Livres\n2. Ajouter un livre\n"+
			"3. Retirer un livre\n4. Mettre à jour un livre\n5. Quitter\n"+
			"Choisissez une option";
		return Integer.parseInt(JOptionPane.showInputDialog(null, options, 
			"MENU PRINCIPAL", JOptionPane.PLAIN_MESSAGE));	
	}
	
}
