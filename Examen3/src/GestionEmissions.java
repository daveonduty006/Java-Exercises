import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionEmissions {
	
	static final String PROGRAMME= "Système de Gestion des Émissions";
	static final String FICHIER_EMISSIONS_TEXTE = "src/donnees/emissions.txt";
	static final String FICHIER_EMISSIONS_OBJ = "src/donnees/emissions.obj";
	static Map<Integer,Emission> listeEmissions;	
	static JTextArea sortie;
	static JScrollPane sp;

	public static void main(String[] args) throws Exception {
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		UIManager.put("OptionPane.noButtonText", "Non");
		UIManager.put("OptionPane.yesButtonText", "Oui");
		chargerEmissions();
		int choix, choixListe;
		do {
			choix= menuGeneral();
			switch (choix) {
				case 1:
					do {
						choixListe= menuChoixListe();
						switch (choixListe) {
							case 1: 
								listerTous();
								JOptionPane.showMessageDialog(null, sp, "LISTE DE TOUTES LES ÉMISSIONS", JOptionPane.PLAIN_MESSAGE);
								break;
							case 2:
								listerReportages();
								JOptionPane.showMessageDialog(null, sp, "LISTE DES REPORTAGES", JOptionPane.PLAIN_MESSAGE);
								break;
							case 3:
								listerFictions();
								JOptionPane.showMessageDialog(null, sp, "LISTE DES ÉMISSIONS DE FICTION", JOptionPane.PLAIN_MESSAGE);
								break;
							case 4:
								break;
							default:
								afficherMessage("Choix invalide !");
						}
					}while (choixListe != 4);
					break;
				case 2:
					listerFilmsRealisateur();
					break;
				case 3:
					supprimerEmission();
					break;
				case 4:
					Utilitaires.sauvegarderFichierObjets(listeEmissions, FICHIER_EMISSIONS_OBJ);
					afficherMessage("Merci d'avoir utilisé notre système !");
					break;
				default:
					afficherMessage("Choix invalide !");
			}
		}while (choix != 4);
		System.exit(0);
	}
		
		
		
	private static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}
	
	private static int menuGeneral() {
		String contenu = "1. Afficher les émissions\n2. Afficher les films d'un réalisateur\n"+
	                     "3. Supprimer une émission\n4. Terminer\n\n";
		contenu += "Entrez votre choix: ";
		return Integer.parseInt(
			   JOptionPane.showInputDialog(
		       null, contenu, PROGRAMME, JOptionPane.PLAIN_MESSAGE));
	}
	
	private static int menuChoixListe() {
		sortie= new JTextArea(20,100);
		sortie.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		sp= new JScrollPane(sortie);
		String contenu = "1. Liste de toutes les émissions\n2. Liste des reportages\n"+
		                 "3. Liste des émissions de fiction\n4. Terminer\n\n";
		contenu += "Entrez votre choix: ";
		return Integer.parseInt(
			   JOptionPane.showInputDialog(
		       null, contenu, "LISTES DES ÉMISSIONS", JOptionPane.PLAIN_MESSAGE));
	}
	
	public static void supprimerEmission() {
		int id= Integer.parseInt(
			    JOptionPane.showInputDialog(
			    null, "Entrez le id de l'émission: ", "RETRAIT D'UNE ÉMISSION", JOptionPane.PLAIN_MESSAGE));
		if (listeEmissions.containsKey(id)) {
			String infoEmission= listeEmissions.get(id).typeEmission+"......"+
					             listeEmissions.get(id).idEmission+"......"+
					             listeEmissions.get(id).nomEmission+"......"+
					             listeEmissions.get(id).heureDebut+"......"+
					             listeEmissions.get(id).heureFin+"\n\n";
			infoEmission += "Voulez-vous vraiment retirer cette émission ?";
			int confirmerRetrait= JOptionPane.showConfirmDialog(
                    			  null, infoEmission, "RETRAIT D'UNE ÉMISSION", 
                    			  JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (confirmerRetrait == JOptionPane.YES_OPTION) {
				listeEmissions.remove(id);
				afficherMessage("Émission id "+id+" retiré !");
				sortie= new JTextArea(20,100);
				sortie.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
				sp= new JScrollPane(sortie);
				listerTous();
				JOptionPane.showMessageDialog(null, sp, 
					"LISTE DES ÉMISSIONS SUITE À LA SUPPRESSION DE L'ÉMISSION "+id, JOptionPane.PLAIN_MESSAGE);
			}
		}else {
			afficherMessage("Émission introuvable !");
		}
	}
	
	public static void listerFilmsRealisateur() {
		sortie= new JTextArea(20,100);
		sortie.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		sp= new JScrollPane(sortie);
		String realisateur= JOptionPane.showInputDialog(
			    			null, "Entrez le nom du réalisateur: ", "FILMS D'UN RÉALISATEUR", 
			    			JOptionPane.PLAIN_MESSAGE);
		sortie.append("\n\nTYPE\tID\tNOM DE L'ÉMISSION\t\tHEURE DE DÉBUT\t"+
	                  "HEURE DE FIN\tTITRE\t\t\t\tANNÉE\tRÉALISATEUR\t\tREDIFFUSION?\n"); 
		Emission uneEmission;
		for (Integer id : listeEmissions.keySet()) {
			uneEmission = listeEmissions.get(id);
			Fiction uneFiction;
			if (uneEmission instanceof Fiction) {
				uneFiction = (Fiction) uneEmission;
				if (uneFiction.getRealisateur().equalsIgnoreCase(realisateur)) {
					sortie.append(uneFiction.toString());
				}
			}
		}
		JOptionPane.showMessageDialog(null, sp, 
			"LISTE DES FILMS DU RÉALISATEUR "+realisateur.toUpperCase(), JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void listerFictions() {
		Emission uneEmission;
		sortie.append("\t\t\t\tLISTE DES ÉMISSIONS DE FICTION\n\n");
		sortie.append("TYPE\tID\tNOM DE L'ÉMISSION\t\tHEURE DE DÉBUT\t"+
		              "HEURE DE FIN\tTITRE\t\t\t\tANNÉE\tRÉALISATEUR\t\tREDIFFUSION?\n"); 
		for (Integer idEmission : listeEmissions.keySet()) {
			uneEmission= listeEmissions.get(idEmission);
			Fiction uneFiction;
			if (uneEmission instanceof Fiction) {
				uneFiction= (Fiction) uneEmission;
				sortie.append(uneFiction.toString());
			}
		}
		sortie.append("\n\n\n");
	}
	
	public static void listerReportages() {
		Emission uneEmission;
		sortie.append("\t\t\t\tLISTE DES REPORTAGES\n\n");
		sortie.append("TYPE\tID\tNOM DE L'ÉMISSION\t\tHEURE DE DÉBUT\t"+
		              "HEURE DE FIN\tSUJET DU REPORTAGE\tPRÉSENTATEUR\n"); 
		for (Integer idEmission : listeEmissions.keySet()) {
			uneEmission= listeEmissions.get(idEmission);
			Reportage unReportage;
			if (uneEmission instanceof Reportage) {
				unReportage= (Reportage) uneEmission;
				sortie.append(unReportage.toString());
			}
		}
		sortie.append("\n\n\n");
	}
	
	public static void listerTous() {
		listerReportages();
		listerFictions();
	}
	
	@SuppressWarnings("unchecked")
	public static void chargerEmissions() {
		try{
			File f= new File(FICHIER_EMISSIONS_OBJ);
			if (f.exists()) {
				listeEmissions= (HashMap<Integer,Emission>) Utilitaires.chargerFichierObjets(FICHIER_EMISSIONS_OBJ);
			} else {
				listeEmissions= new HashMap<Integer,Emission>();
				ArrayList<ArrayList<String>> listeAttributs = Utilitaires.chargerFichierTexte(FICHIER_EMISSIONS_TEXTE, ";");
				listeAttributs.forEach((donneesEmission) -> {
					char type= donneesEmission.get(0).charAt(0);
					int id= Integer.parseInt(donneesEmission.get(1));
					String nom= donneesEmission.get(2);
				    int debut= Integer.parseInt(donneesEmission.get(3));
				    int fin= Integer.parseInt(donneesEmission.get(4));
				    if (type == 'R') {
				    	String sujet= donneesEmission.get(5);
				    	String presentateur= donneesEmission.get(6);
						listeEmissions.put(id, new Reportage(type, id, nom, debut, fin, sujet, presentateur));
				    }else if (type == 'F') {
				    	String titre= donneesEmission.get(5);
				    	int annee= Integer.parseInt(donneesEmission.get(6));
				    	String realisateur= donneesEmission.get(7);
				    	boolean estRediffusion= Boolean.parseBoolean(donneesEmission.get(8));
				    	listeEmissions.put(id, new Fiction(type, id, nom, debut, fin, titre, annee, realisateur, 
				    			                           estRediffusion));
				    }
			    });
			}
		}catch(Exception e){
			System.out.println("Problème");
		}
	}

}
