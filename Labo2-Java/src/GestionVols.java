import java.util.Collections;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.*;
import java.io.*;

public class GestionVols {
	
	static final int MAX_PLACES = 340;
	static final int MAX_VOLS = 36;
	static final String COMPAGNIE = "CIE AIR RELAX";
	static final String FICHIER_VOLS = "src/Donnees/Cie_Air_Relax.txt";
	static BufferedReader tmpVolsRead;
	static JTextArea sortie;
	static ArrayList<Vol> tabVols;

	public static void main(String[] args) throws Exception {
		//
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		UIManager.put("OptionPane.noButtonText", "Non");
		UIManager.put("OptionPane.yesButtonText", "Oui");
		//
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
					insererVol();
					break;
				case 3:
					retirerVol();
					break;
				case 4:
					modifierDate();
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
					afficherMessage("Choix invalide. Les options sont [1-6]!",
							        "ATTENTION");
			}
		}while (choix != 6);
		//
	}
	
	private static void afficherMessage(String msg, String titre) {
		JOptionPane.showMessageDialog(null, msg, titre, 
			                          JOptionPane.WARNING_MESSAGE);
	}
	
	private static int menu() {
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
	
	private static int rechercherVol(int numVol) {
		int pos;
		Vol VolBidon= new Vol(numVol);
		pos= tabVols.indexOf(VolBidon);
		return pos;
	}
	
	private static String entrerDestination(String titre) {
		String dest;
		do {
			dest= JOptionPane.showInputDialog(
				  null, "Entrez la destination du vol: ",
				  titre, JOptionPane.QUESTION_MESSAGE);
		}while (dest.chars().allMatch(Character::isDigit));
		return dest;	
	}
	
	private static String[] entrerDate(String infoVol, String titre) {
		String elemsDate[]= new String[3];
		JTextField jour= new JTextField();
		JTextField mois= new JTextField();
		JTextField annee= new JTextField();
		Object champs[]= {
			infoVol,
			"Jour du départ: ", jour,
			"Mois du départ: ", mois,
			"Année du départ: ", annee
			};
		do {
			JOptionPane.showConfirmDialog(null, champs, titre,
				                          JOptionPane.PLAIN_MESSAGE);
		}while (!jour.getText().chars().allMatch(Character::isDigit) ||
				!mois.getText().chars().allMatch(Character::isDigit) ||
				!annee.getText().chars().allMatch(Character::isDigit));
		elemsDate[0]= jour.getText();
		elemsDate[1]= mois.getText();
		elemsDate[2]= annee.getText();
		return elemsDate;
	}
	
	private static String validerDate(int jour, int mois, int annee) {
		String msg;
		boolean etat[]= new boolean[3];
		msg= Date.validerDate(jour, mois, annee, etat);
		return msg;
	}
		
	private static int confirmerOperation(int posVol, String opMsg, String titre) {
		int choix;
		Vol volTrouve= tabVols.get(posVol);
		JTextArea sortie= new JTextArea();
		sortie.append(volTrouve.toString()+"\n"+opMsg);	
		choix= JOptionPane.showConfirmDialog(
			   null, sortie, titre, JOptionPane.YES_NO_OPTION, 
			   JOptionPane.PLAIN_MESSAGE);
		return choix;		
	}
	
	public static void modifierDate() {
		if (!tabVols.isEmpty()) {
			try {
				int pos;
				int numVol= Integer.parseInt(
							JOptionPane.showInputDialog(
					        null, "Entrez le numéro du vol: ",
					        "MODIFICATION DE LA DATE DE DÉPART", 
					        JOptionPane.QUESTION_MESSAGE));
				pos= rechercherVol(numVol);
				if (pos != -1) {
					String msg;
					String elemsDate[]= new String[3];
					Vol volTrouve= tabVols.get(pos);
					String infoVol= volTrouve.getDestination()+" "+
					                volTrouve.getDepart()+"\n\n";
					elemsDate= entrerDate(
							   infoVol, "MODIFICATION DE LA DATE DE DÉPART");
					msg= validerDate(Integer.parseInt(elemsDate[0]),
							         Integer.parseInt(elemsDate[1]),
							         Integer.parseInt(elemsDate[2]));
					if (msg.length() == 0) {
						Date dateInstance= new Date(
								           Integer.parseInt(elemsDate[0]),
						           	       Integer.parseInt(elemsDate[1]),
						           		   Integer.parseInt(elemsDate[2]));
						volTrouve.setDepart(dateInstance);
					}else {
						afficherMessage(msg, "ATTENTION");
					}					
				}
				//
			}catch (NumberFormatException e) {
				afficherMessage("Numéro de vol invalide!", "ATTENTION");
			}			
		}else {
			afficherMessage("Le tableau des vols est vide!", "ATTENTION");
		}
	}		
	
	public static void retirerVol() {
		if (!tabVols.isEmpty()) {
			try {
				int pos;
				int numVol= Integer.parseInt(
							JOptionPane.showInputDialog(
					        null, "Entrez le numéro du vol: ",
					        "RETRAIT D'UN VOL", JOptionPane.QUESTION_MESSAGE));
				pos= rechercherVol(numVol);
				if (pos != -1) {
					int choix= confirmerOperation(
							   pos, "Désirez-vous vraiment retirer ce vol ?",
					           "RETRAIT D'UN VOL");
					if (choix == JOptionPane.YES_OPTION) {
						tabVols.remove(pos);
						Vol.nbVols--;
					}
				}else {
					afficherMessage("Ce vol n'existe pas!", "ATTENTION");		
				}	
				//
			}catch (NumberFormatException e) {
				afficherMessage("Numéro de vol invalide!", "ATTENTION");
			}			
		}else {
			afficherMessage("Le tableau des vols est vide!", "ATTENTION");
		}
	}
		
	public static void insererVol() {
		if (tabVols.size() < MAX_VOLS) {
			try {
				int pos, numVol;
				do {
				numVol= Integer.parseInt(
					    JOptionPane.showInputDialog(
					    null, "Entrez le numéro du nouveau vol (5 chiffres): ",
					    "AJOUT D'UN VOL", JOptionPane.QUESTION_MESSAGE));
				}while (String.valueOf(numVol).length() != 5);
				pos= rechercherVol(numVol);
				if (pos == -1) {
					String msg;
					String elemsDate[]= new String[3];
					String dest= entrerDestination("AJOUT D'UN VOL");
					elemsDate= entrerDate("", "AJOUT D'UN VOL");
					msg= validerDate(Integer.parseInt(elemsDate[0]),
							         Integer.parseInt(elemsDate[1]),
							         Integer.parseInt(elemsDate[2]));
					if (msg.length() == 0) {
						Date dateInstance= new Date(
								           Integer.parseInt(elemsDate[0]),
						           	       Integer.parseInt(elemsDate[1]),
						           		   Integer.parseInt(elemsDate[2]));
						tabVols.add(new Vol(numVol, dest, dateInstance, 0));
					}else {
						afficherMessage(msg, "ATTENTION");
					}
				}else {
					afficherMessage("Ce vol existe déjà!", "ATTENTION");		
				}
				//
			}catch (NumberFormatException e) {
				afficherMessage("Numéro de vol invalide!", "ATTENTION");
			}			
		}else {
			afficherMessage("Nombre maximum de vols atteint!", "ATTENTION");
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
		sortie.append("\nNombre de vols: "+Vol.nbVols);
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
