import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Font;

public class GestionLivres {
	
	static final String FICHIER_LIVRES= "src/data/livres.txt";
	static JTextArea sortie;
	static BufferedReader tmpLivresRead;
	static BufferedWriter tmpLivresWrite;
	static ArrayList<Livre> biblio;

	public static void main(String[] args) throws Exception {
		chargerLivres();
		int choix;
		//
		do {
			choix= menuPrincipal();
			switch(choix) {
				case 1:
					listerLivres();
					break;
				default:
					System.out.println("Choix invalide!");
			}
		}while (choix != 6);
	}
	
    public static void listerLivres() {
    	enTeteJTextArea();
    	Collections.sort(biblio);
    	biblio.forEach((unLivre) -> {
    		sortie.append(unLivre.toString());
    	});
    	sortie.append("\nNombre de livres= "+biblio.size());
    	JOptionPane.showMessageDialog(null, sortie, null, 
    		JOptionPane.PLAIN_MESSAGE);
    }
	
	public static int menuPrincipal() {
		String menu;
		menu= "1. Lister les livres\n"+
			  "2. Lister les livres par catégorie\n"+
			  "3. Ajouter un livre\n"+
			  "4. Enlever un livre\n"+
			  "5. Modifier un livre\n"+
			  "6. Quitter\n\n"+
			  "Choisissez une option: ";
		return Integer.parseInt(showInputDialog(menu, "MENU PRINCIPAL"));		  
	}

	public static void enTeteJTextArea() {
		sortie = new JTextArea();
		sortie.setFont(new Font("Courier New", Font.PLAIN, 12));
		sortie.append("\t\tLISTE DES LIVRES\n\n");
		sortie.append("NUMÉRO\tTITRE\t\t\tPAGES\tCATÉGORIE\n");
	}
	
	public static String showInputDialog(String message, String titre) {
		return JOptionPane.showInputDialog(null, message, titre,
				JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void chargerLivres() throws Exception {
		biblio= new ArrayList<>();
		String elems[]= new String[4];
		tmpLivresRead= new BufferedReader(new FileReader(FICHIER_LIVRES));
		//
		try {
			String ligne= tmpLivresRead.readLine();
			while (ligne != null) {
				elems= ligne.split(";");
				biblio.add(new Livre(Integer.parseInt(elems[0]),
						             elems[1],
						             Integer.parseInt(elems[2]),
						             Integer.parseInt(elems[3])));
				ligne= tmpLivresRead.readLine();
			}
		}catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et "+
							   "nom du fichier.");
		}catch (IOException e) {
			System.out.println("Un problème est arrivé lors de la "+
							   "manipulation du fichier. Vérifiez vos "+
							   "données.");
		}catch (Exception e) {
			System.out.println("Un problème est arrivé lors du chargement du "+
		                       "fichier. Contactez l'administrateur.");
		}finally {
			tmpLivresRead.close();
		}
	}
	
}
