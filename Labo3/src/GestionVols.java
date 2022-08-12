import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionVols {

	static final String COMPAGNIE= "Cie Air Relax";
	static final String FICHIER_VOLS_TEXTE = "src/data/Cie_Air_Relax.txt";
	static final String FICHIER_VOLS_OBJ = "src/data/Cie_Air_Relax.obj";
	static List<Avion> listeFlotte;
	static Map<Integer,Vol> listeMapVols;	
	static JTextArea sortie;
	static JScrollPane sp;

	public static void main(String[] args) throws Exception {
		chargerFlotte();
		chargerVols();
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
								JOptionPane.showMessageDialog(null, sp, null, JOptionPane.PLAIN_MESSAGE);
								break;
							case 2: 
								listerVolsReguliers();
								JOptionPane.showMessageDialog(null, sp, null, JOptionPane.PLAIN_MESSAGE);
								break;
							case 3: 
								listerVolsBasPrix();
								JOptionPane.showMessageDialog(null, sp, null, JOptionPane.PLAIN_MESSAGE);
								break;
							case 4: 
								listerVolsCharter();
								JOptionPane.showMessageDialog(null, sp, null, JOptionPane.PLAIN_MESSAGE);
								break;
							case 5: 
								listerVolsPrives();
								JOptionPane.showMessageDialog(null, sp, null, JOptionPane.PLAIN_MESSAGE);
								break;
							case 0:
								break;
							default:
								afficherMessage("Choix invalide !");
						}
					} while (choixListe != 0);
					break;
				default:
					afficherMessage("Choix invalide !");
			}
		} while (choix != 0);
	}
	
	private static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}
	
	private static int menuChoixListe() {
		sortie= new JTextArea(20,100);
		sortie.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		sp= new JScrollPane(sortie);
		String contenu = "1. Tous\n2. Vols Réguliers\n3. Vols Bas-Prix\n"+
	                     "4. Vols Charter\n5. Vols Privés\n0. Terminer\n\n";
		contenu += "Entrez votre choix: ";
		return Integer.parseInt(
			   JOptionPane.showInputDialog(
		       null, contenu, COMPAGNIE, JOptionPane.PLAIN_MESSAGE));
	}
	
	private static int menuGeneral() {
		String contenu = "1. Liste des vols\n2. Ajout d'un vol\n3. Retrait d'un vol\n"+
	                     "4. Modification de la date de départ\n5. Réservation d'un vol\n"+
				         "0. Terminer\n\n";
		contenu += "Entrez votre choix: ";
		return Integer.parseInt(
			   JOptionPane.showInputDialog(
		       null, contenu, COMPAGNIE, JOptionPane.PLAIN_MESSAGE));
	}
	
	private static void instancierVolsSpecialises(ArrayList<ArrayList<String>> listeAttributs) {
		listeAttributs.forEach((donneesVol) -> {
			String elemsDate[]= new String[3];
		    elemsDate= donneesVol.get(3).split(" ");
		    Date dateInstance= new Date(Integer.parseInt(elemsDate[0]), Integer.parseInt(elemsDate[1]),
                                        Integer.parseInt(elemsDate[2]));
		    Avion avionInstance= listeFlotte.get(Integer.parseInt(donneesVol.get(5))-1);
			int numVol= Integer.parseInt(donneesVol.get(1));
			if (donneesVol.get(0).equalsIgnoreCase("R")) {
				listeMapVols.put(numVol, new VolRegulier(donneesVol.get(0).charAt(0), numVol, donneesVol.get(2),
						                                 dateInstance, Integer.parseInt(donneesVol.get(4)),
						                                 avionInstance, Boolean.parseBoolean(donneesVol.get(6)), 
						                                 Boolean.parseBoolean(donneesVol.get(7)),
						                                 Boolean.parseBoolean(donneesVol.get(8)),
						                                 Boolean.parseBoolean(donneesVol.get(9)),
						                                 Boolean.parseBoolean(donneesVol.get(10)),
						                                 Boolean.parseBoolean(donneesVol.get(11)),
						                                 Boolean.parseBoolean(donneesVol.get(12))));
			} else if (donneesVol.get(0).equalsIgnoreCase("B")) {
				listeMapVols.put(numVol, new VolBasPrix(donneesVol.get(0).charAt(0), numVol, donneesVol.get(2),
														dateInstance, Integer.parseInt(donneesVol.get(4)),
														avionInstance, Boolean.parseBoolean(donneesVol.get(6)), 
														Boolean.parseBoolean(donneesVol.get(7)),
														Boolean.parseBoolean(donneesVol.get(8)),
														Boolean.parseBoolean(donneesVol.get(9)),
														Boolean.parseBoolean(donneesVol.get(10)),
														Boolean.parseBoolean(donneesVol.get(11)),
														Boolean.parseBoolean(donneesVol.get(12))));
			} else if (donneesVol.get(0).equalsIgnoreCase("C")) {
				listeMapVols.put(numVol, new VolCharter(donneesVol.get(0).charAt(0), numVol, donneesVol.get(2),
														dateInstance, Integer.parseInt(donneesVol.get(4)),
														avionInstance, Boolean.parseBoolean(donneesVol.get(6)), 
														Boolean.parseBoolean(donneesVol.get(7)),
														Boolean.parseBoolean(donneesVol.get(8)),
														Boolean.parseBoolean(donneesVol.get(9)),
														Boolean.parseBoolean(donneesVol.get(10)),
														Boolean.parseBoolean(donneesVol.get(11)),
														Boolean.parseBoolean(donneesVol.get(12))));
			} else if (donneesVol.get(0).equalsIgnoreCase("P")) {
				listeMapVols.put(numVol, new VolPrive(donneesVol.get(0).charAt(0), numVol, donneesVol.get(2),
													  dateInstance, Integer.parseInt(donneesVol.get(4)),
													  avionInstance, Boolean.parseBoolean(donneesVol.get(6)), 
													  Boolean.parseBoolean(donneesVol.get(7)),
													  Boolean.parseBoolean(donneesVol.get(8)),
													  Boolean.parseBoolean(donneesVol.get(9)),
													  Boolean.parseBoolean(donneesVol.get(10)),
													  Boolean.parseBoolean(donneesVol.get(11)),
													  Boolean.parseBoolean(donneesVol.get(12))));
			}
		});
	}
	
	public static void listerVolsPrives() {
		Vol unVol;
		sortie.append("\t\t\t\t\tLISTE DES VOLS PRIVÉS\n\n");
		sortie.append("TYPE VOL\tNUMÉRO VOL\tDESTINATION\t\tDÉPART\t\t"+
		              "RÉSERVATIONS\tNUMÉRO AVION\tRÉSERVABLE\tREPAS\t\t"+
				      "SERVICE BAR\tSERVICES PAYANTS\tCONSOLE\t\tWIFI\t\t"+
		              "PRISE ALIM.\n"); 
		for (Integer numVol : listeMapVols.keySet()) {
			unVol= listeMapVols.get(numVol);
			VolPrive unVolPrive;
			if (unVol instanceof VolPrive) {
				unVolPrive= (VolPrive) unVol;
				sortie.append(unVolPrive.toString());
			}
		}
		sortie.append("\nNombre de vols privés: "+VolPrive.nbVolsPV+"\n\n\n");
	}
	
	public static void listerVolsCharter() {
		Vol unVol;
		sortie.append("\t\t\t\t\tLISTE DES VOLS CHARTER\n\n");
		sortie.append("TYPE VOL\tNUMÉRO VOL\tDESTINATION\t\tDÉPART\t\t"+
		              "RÉSERVATIONS\tNUMÉRO AVION\tRÉSERVABLE\tREPAS\t\t"+
				      "SERVICE BAR\tSERVICES PAYANTS\tCONSOLE\t\tWIFI\t\t"+
		              "PRISE ALIM.\n"); 
		for (Integer numVol : listeMapVols.keySet()) {
			unVol= listeMapVols.get(numVol);
			VolCharter unVolCharter;
			if (unVol instanceof VolCharter) {
				unVolCharter= (VolCharter) unVol;
				sortie.append(unVolCharter.toString());
			}
		}
		sortie.append("\nNombre de vols charter: "+VolCharter.nbVolsCH+"\n\n\n");
	}
	
	public static void listerVolsBasPrix() {
		Vol unVol;
		sortie.append("\t\t\t\t\tLISTE DES VOLS BAS-PRIX\n\n");
		sortie.append("TYPE VOL\tNUMÉRO VOL\tDESTINATION\t\tDÉPART\t\t"+
		              "RÉSERVATIONS\tNUMÉRO AVION\tRÉSERVABLE\tREPAS\t\t"+
				      "SERVICE BAR\tSERVICES PAYANTS\tCONSOLE\t\tWIFI\t\t"+
		              "PRISE ALIM.\n"); 
		for (Integer numVol : listeMapVols.keySet()) {
			unVol= listeMapVols.get(numVol);
			VolBasPrix unVolBasPrix;
			if (unVol instanceof VolBasPrix) {
				unVolBasPrix= (VolBasPrix) unVol;
				sortie.append(unVolBasPrix.toString());
			}
		}
		sortie.append("\nNombre de vols bas-prix: "+VolBasPrix.nbVolsBP+"\n\n\n");
	}
	
	public static void listerVolsReguliers() {
		Vol unVol;
		sortie.append("\t\t\t\t\tLISTE DES VOLS RÉGULIERS\n\n");
		sortie.append("TYPE VOL\tNUMÉRO VOL\tDESTINATION\t\tDÉPART\t\t"+
		              "RÉSERVATIONS\tNUMÉRO AVION\tRÉSERVABLE\tREPAS\t\t"+
				      "SERVICE BAR\tSERVICES PAYANTS\tCONSOLE\t\tWIFI\t\t"+
		              "PRISE ALIM.\n"); 
		for (Integer numVol : listeMapVols.keySet()) {
			unVol= listeMapVols.get(numVol);
			VolRegulier unVolRegulier;
			if (unVol instanceof VolRegulier) {
				unVolRegulier= (VolRegulier) unVol;
				sortie.append(unVolRegulier.toString());
			}
		}
		sortie.append("\nNombre de vols réguliers: "+VolRegulier.nbVolsReg+"\n\n\n");
	}
	
	public static void listerTous() {
		listerVolsReguliers();
		listerVolsBasPrix();
		listerVolsCharter();
		listerVolsPrives();
	}
	
	@SuppressWarnings("unchecked")
	public static void chargerVols() {
		try{
			File f= new File(FICHIER_VOLS_OBJ);
			if (f.exists()) {
				listeMapVols= (HashMap<Integer, Vol>) Utilitaires.chargerFichierObjets(FICHIER_VOLS_OBJ);
			} else {
				listeMapVols= new HashMap<Integer, Vol>();
				ArrayList<ArrayList<String>> listeAttributs = Utilitaires.chargerFichierTexte(FICHIER_VOLS_TEXTE, ";");
				instancierVolsSpecialises(listeAttributs);
			}
		}catch(Exception e){
			System.out.println("Problème");
		}
	}
	
	public static void chargerFlotte() {
		listeFlotte= new ArrayList<>();
		listeFlotte.add(new Avion(1, "Boeing 747", 416, "Long-Courrier", true, true, true));
		listeFlotte.add(new Avion(2, "Airbus A220", 133, "Moyen-Courrier", true, true, true));
		listeFlotte.add(new Avion(3, "Boeing 737", 130, "Court-Courrier", true, true, true));
		listeFlotte.add(new Avion(4, "Cessna Citation XLS", 9, "Court-Courrier", true, false, false));
		listeFlotte.add(new Avion(5, "Boeing 747", 416, "Long-Courrier", true, true, true));
		listeFlotte.add(new Avion(6, "Airbus A220", 133, "Moyen-Courrier", true, true, true));
		listeFlotte.add(new Avion(7, "Boeing 737", 130, "Court-Courrier", true, true, true));
		listeFlotte.add(new Avion(8, "Cessna Citation XLS", 9, "Court-Courrier", true, false, false));
	}
	
}