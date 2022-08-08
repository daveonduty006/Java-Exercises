import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionVols {

	static final String FICHIER_VOLS_TEXTE = "src/data/Cie_Air_Relax.txt";
	static final String FICHIER_VOLS_OBJ = "src/data/Cie_Air_Relax.obj";
	static Map<Integer,Vol> listeMapVols;
	static BufferedReader tmpVolsRead;
	static BufferedWriter tmpVolsWrite;
	static ObjectInputStream tmpVolsReadObj;
	static ObjectOutputStream tmpVolsWriteObj;
	static JTextArea sortie;

	public static void main(String[] args) throws Exception {
		chargerVols();
		int choix, choixMap, choixVol;
	}
	
	public static void chargerVols() {
		File f = new File(FICHIER_VOLS_OBJ);
		if (f.exists()) {
			chargerVolsObj();
		}else {
			chargerVolsTexte();
		}
	}
	
	public static void chargerVolsTexte() {
		try {
			String ligne;
			String elems[] = new String[7];
			listeMapVols= new HashMap<Integer,Vol>();
			tmpLivresRead = new BufferedReader(new FileReader(FICHIER_LIVRES_TEXTE));
			ligne = tmpLivresRead.readLine();// Lire la premiére ligne du fichier
			while (ligne != null) {// Si ligne == null alors ont a atteint la fin du fichier
				elems = ligne.split(";");// elems[0] contient le num�ro du livre;elems[1] le titre et elems[2] les pages
				// A;200;Un excellent livre;850;5;1640;Potable
				if(elems[0].equalsIgnoreCase("A")){
					biblio.add(new LivreAncien(Integer.parseInt(elems[1]), elems[2], Integer.parseInt(elems[3]),
							Integer.parseInt(elems[4]), Integer.parseInt(elems[5]), elems[6]));
				} else if(elems[0].equalsIgnoreCase("E")){
					// E;888;UN LIVRE;300;0;true;true
					biblio.add(new LivreElectronique(Integer.parseInt(elems[1]), elems[2], Integer.parseInt(elems[3]),
							Integer.parseInt(elems[4]), Boolean.parseBoolean(elems[5]), 
							Boolean.parseBoolean(elems[6])));
				}
				ligne = tmpLivresRead.readLine();
			} // fin while

		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un probléme est arrivé lors de la manipulation du fichier. Vérifiez vos donn�es.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Un probléme est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {// Exécuté si erreur ou pas
			tmpLivresRead.close();
		}
	}

}