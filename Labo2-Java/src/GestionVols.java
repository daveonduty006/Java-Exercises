import java.util.Collections;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.*;
import java.io.*;

public class GestionVols {
	
	static final int MAX_PLACES = 340;
	static final String COMPAGNIE = "CIE AIR RELAX";
	static final String FICHIER_VOLS = "src/Donnees/Cie_Air_Relax.txt";
	static BufferedReader tmpVolsRead;
	static JTextArea sortie;
	static ArrayList<Vol> tabVols;

	public static void main(String[] args) throws Exception {
		int choix;
		chargerVols();
		//
		do {
			choix= menu();
			switch(choix) {
				case 1:
					listeVols();
					break;
				case 2:
					//insererVol();
					break;
				case 3:
					//retirerVol();
					break;
				case 4:
					//modifierDate();
					break;
				case 5:
					//reserverVol();
					break;
				case 6:
					//ecrireFichier();
					JOptionPane.showMessageDialog(null,
							"Merci d'avoir utilisé le sytème de réservation de " + COMPAGNIE, "Au revoir",
							JOptionPane.PLAIN_MESSAGE);
					break;
				default:
					afficherMessage("Choix invalide. Les options sont [1-6]!");
			}
		}while (choix != 6);
		//
	}
	
	private static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", 
			                          JOptionPane.PLAIN_MESSAGE);
	}
	
	private static int menu() {
		/*
		 * Fix le cas où un caractère non-numérique est entré dans le choix, avec un try-catch NumberFormatException.
		 * Si le parseInt ne fonctionne pas -1 est retourné.
		 */
		int choix;
		String message= "                       GESTION DES VOLS\n"+
				        "1. Liste des vols\n"+
				        "2. Ajout d'un vol\n"+
				        "3. Retrait d'un vol\n"+
				        "4. Modification de la date de départ\n"+
				        "5. Réservation d'un vol\n"+
				        "6. Terminer\n"+
				        "                       Faites votre choix: ";
		try {
			choix= Integer.parseInt(
				   JOptionPane.showInputDialog(
				   null, message, COMPAGNIE, JOptionPane.PLAIN_MESSAGE));
			return choix;
		}catch (NumberFormatException e) {
			return -1;
		}
	}

	public static void listeVols() {
		sortie= new JTextArea();
		Font f= new Font("Courier New", Font.PLAIN, 12);
		sortie.setFont(f);
		sortie.append("\t\t\tLISTE DES VOLS\n\n"+
				      "Numéro\tDestination\t\tDépart\t\tRéservations\n");
		//
		Collections.sort(tabVols);
		for (Vol unVol : tabVols) {
			sortie.append(unVol.toString());
		}
		JOptionPane.showMessageDialog(null, sortie, COMPAGNIE,
				                      JOptionPane.PLAIN_MESSAGE);
	}

	public static void chargerVols() throws Exception {
		try {
			tabVols= new ArrayList<>();
			tmpVolsRead= new BufferedReader(new FileReader(FICHIER_VOLS));
			String elemsVol[]= new String[4];
			String elemsDate[]= new String[3];
			String ligne;
			//
			ligne= tmpVolsRead.readLine();
			while (ligne != null) {
				elemsVol= ligne.split(";");
				elemsDate= elemsVol[2].split(" ");
				Date dateInstance= new Date(Integer.parseInt(elemsDate[0]),
						                    Integer.parseInt(elemsDate[1]),
						                    Integer.parseInt(elemsDate[2]));
				tabVols.add(new Vol(Integer.parseInt(elemsVol[0]),
						            elemsVol[1], dateInstance,
						            Integer.parseInt(elemsVol[3])));
				ligne= tmpVolsRead.readLine();
			}
			//
		}catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		}catch (IOException e) {
			System.out.println("Un problème est subvenu lors de la manipulation du fichier. Vérifiez vos données.");
		}catch (Exception e) {
			System.out.println("Un problème est subvenu lors du chargement du fichier. Contactez l'administrateur.");
		}finally {
			tmpVolsRead.close();
		}
	}

}
