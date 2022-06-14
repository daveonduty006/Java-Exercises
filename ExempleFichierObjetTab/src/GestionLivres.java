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
	    for (Livre unLivre: biblio) {
	    	if (unLivre != null) {
	    	sortie.append(unLivre.toString()); 
	    	}
	    }
		JOptionPane.showMessageDialog(null, sortie, null, 
			JOptionPane.PLAIN_MESSAGE);
	}
}

