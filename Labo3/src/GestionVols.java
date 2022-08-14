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
		UIManager.put("OptionPane.cancelButtonText", "Annuler");
		UIManager.put("OptionPane.noButtonText", "Non");
		UIManager.put("OptionPane.yesButtonText", "Oui");
		chargerFlotte();
		chargerVols();
		int choix, choixListe, choixType;
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
				case 2:
					do {
						choixType= menuChoixType();
						switch (choixType) {
							case 1:
							case 2:
							case 3:
							case 4:
								ajouterVol(choixType);
								break;
							case 0:
								break;
							default:
								afficherMessage("Choix invalide !");
						}
					} while (choixType != 0);
					break;
				case 3:
					retirerVol();
					break;
				case 4:
					modifierDepart();
					break;
				case 5:
					entrerReservation();
					break;
				case 0:
					Utilitaires.sauvegarderFichierObjets(listeMapVols, FICHIER_VOLS_OBJ);
					afficherMessage("Merci d'avoir utilisé notre système !");
					break;
				default:
					afficherMessage("Choix invalide !");
			}
		} while (choix != 0);
		System.exit(0);
	}
	
	private static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, "MESSAGES", JOptionPane.PLAIN_MESSAGE);
	}
	
	private static int menuChoixType() {
		String contenu = "1. Ajout d'un vol régulier\n2. Ajout d'un vol bas-prix\n3. Ajout d'un vol charter\n"+
                         "4. Ajout d'un vol privé\n0. Terminer\n\n";
		contenu += "Entrez votre choix: ";
		return Integer.parseInt(
	           JOptionPane.showInputDialog(
               null, contenu, "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
		}
	
	private static int menuChoixListe() {
		sortie= new JTextArea(20,100);
		sortie.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		sp= new JScrollPane(sortie);
		String contenu = "1. Lister tous les vols\n2. Lister les vols réguliers\n3. Lister les vols bas-prix\n"+
	                     "4. Lister les vols charter\n5. Lister les vols privés\n0. Terminer\n\n";
		contenu += "Entrez votre choix: ";
		return Integer.parseInt(
			   JOptionPane.showInputDialog(
		       null, contenu, "LISTES DES VOLS", JOptionPane.PLAIN_MESSAGE));
	}
	
	private static int menuGeneral() {
		String contenu = "1. Liste des vols\n2. Ajout d'un vol\n3. Retrait d'un vol\n"+
	                     "4. Modification du départ d'un vol\n5. Réservation d'un vol\n"+
				         "0. Terminer\n\n";
		contenu += "Entrez votre choix: ";
		return Integer.parseInt(
			   JOptionPane.showInputDialog(
		       null, contenu, COMPAGNIE, JOptionPane.PLAIN_MESSAGE));
	}
	
	private static void instancierVolSpecialise(char type, int num, String dest, Date date, int res, Avion avion, 
			                                    boolean opt1, boolean opt2, boolean opt3, boolean opt4, 
			                                    boolean opt5, boolean opt6, boolean opt7) {
		if (type == 'R') {
			listeMapVols.put(num, new VolRegulier(type, num, dest, date, res, avion, opt1, opt2, opt3, opt4, 
					                              opt5, opt6, opt7));
		} else if (type == 'B') {
			listeMapVols.put(num, new VolBasPrix(type, num, dest, date, res, avion, opt1, opt2, opt3, opt4, 
                                                 opt5, opt6, opt7));
		} else if (type == 'C') {
			listeMapVols.put(num, new VolCharter(type, num, dest, date, res, avion, opt1, opt2, opt3, opt4, 
                                                 opt5, opt6, opt7));
        } else if (type == 'P') {
			listeMapVols.put(num, new VolPrive(type, num, dest, date, res, avion, opt1, opt2, opt3, opt4, 
                                               opt5, opt6, opt7));
        }
	}
	
	private static int obtenirNumVolValide() {
		boolean numVolExiste;
		int numVol;
		do {
			numVol= Integer.parseInt(
					JOptionPane.showInputDialog(
					null, "Entrez le numéro du vol: ", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
			numVolExiste = listeMapVols.containsKey(numVol);
			if (numVolExiste) {
				afficherMessage("Le vol numéro "+numVol+" existe déjà !");
			}
		} while (numVolExiste);
		return numVol;
	}
	
	private static Date obtenirDateValide() {
		String msg;
		JTextField jour= new JTextField();
		JTextField mois= new JTextField();
		JTextField annee= new JTextField();
		Object champs[]= {
			"Jour du départ: ", jour,
			"Mois du départ: ", mois,
			"Année du départ: ", annee };
		do {
			do {
				JOptionPane.showConfirmDialog(
			    null, champs, "DATE DE DÉPART DU VOL", JOptionPane.PLAIN_MESSAGE);
			}while ( (!jour.getText().chars().allMatch(Character::isDigit)
				      || jour.getText().isEmpty())                         
				    ||		                                                       
                     (!mois.getText().chars().allMatch(Character::isDigit)  
				      || mois.getText().isEmpty())		                                               
				    ||
				     (!annee.getText().chars().allMatch(Character::isDigit) 
			          || annee.getText().isEmpty()) );
			boolean etat[]= new boolean[3];
		    msg= Date.validerDate(Integer.parseInt(jour.getText()), 
		    		              Integer.parseInt(mois.getText()), 
		    		              Integer.parseInt(annee.getText()), etat);
		    if (!msg.isEmpty()) {
		    	afficherMessage(msg);
		    }
		} while (!msg.isEmpty());
		Date depart= new Date(Integer.parseInt(jour.getText()),
				              Integer.parseInt(mois.getText()),
				              Integer.parseInt(annee.getText()));
		return depart;
	}
	
	private static Avion obtenirAvionValide(Date depart) {
		int choixAvion;
		boolean avionDisponible;
		Avion avionChoisi;
		sortie= new JTextArea(20, 60);
		sortie.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		sortie.append("NUMÉRO AVION\tTYPE AVION\t\t\tSIÈGES\t\tRAYON D'ACTION\t\t"+
		              "CLASSE PREMIÈRE\t\tCLASSE AFFAIRE\t\tCLASSE ÉCONOMIQUE\n");
		sp= new JScrollPane(sortie);
		listeFlotte.forEach((avion) -> {
			sortie.append(avion.affichageJTA());
		});
		sortie.append("\nNombre d'avions: "+Avion.nbAvions);
		do {
			avionDisponible= true;
			do {
				choixAvion= Integer.parseInt(
					    	JOptionPane.showInputDialog(
					        null, sp, "SÉLECTION DE L'AVION", JOptionPane.PLAIN_MESSAGE))-1;
			} while (choixAvion >= listeFlotte.size());
			avionChoisi= listeFlotte.get(choixAvion);
			for (Integer numVol : listeMapVols.keySet()) {
				Vol unVol= listeMapVols.get(numVol);
				if ( unVol.getAvion() == avionChoisi 
					 && unVol.depart.getJour() == depart.getJour()
					 && unVol.depart.getMois() == depart.getMois() 
					 && unVol.depart.getAnnee() == depart.getAnnee() ) {
					afficherMessage("Cet avion a déjà un vol de prévu pour cette date! ");
					avionDisponible= false;
					break;
				}
			}
		} while (!avionDisponible);
		return avionChoisi;
	}
	
	private static int confirmerReservation(int siegesDisponibles) {
		int nbRes;
		String msg= "Il y a présentement "+siegesDisponibles+" siège(s) libre(s) pour ce vol\n\n";
		msg += "Entrez le nombre de sièges que vous désirez réserver: ";
		do {
			nbRes= Integer.parseInt(
				   JOptionPane.showInputDialog(
				   null, msg, "RÉSERVATION DES SIÈGES", JOptionPane.PLAIN_MESSAGE));
			if (nbRes > siegesDisponibles) {
				afficherMessage("Siège(s) disponible(s) insuffisant pour cette réservation !");
			}
		} while (nbRes > siegesDisponibles);
		return nbRes;
	}
	
	private static boolean obtenirOptionBooleen(int opt) {
		if (opt == JOptionPane.YES_OPTION) {
			return true;
		}else {
			return false;
		}
	}
	
	private static boolean[] confirmerOptionsVol() {
		boolean[] opt= new boolean[7];
		int reservable= JOptionPane.showConfirmDialog(
			            null, "Est-il possible de réserver son siège pour ce vol?", 
			            "AJOUT D'UN VOL", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		opt[0]= obtenirOptionBooleen(reservable);
		int repas= JOptionPane.showConfirmDialog(
	               null, "Est-ce que les repas sont fournis pendant ce vol?", 
	               "AJOUT D'UN VOL", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		opt[1]= obtenirOptionBooleen(repas);
		int bar= JOptionPane.showConfirmDialog(
	              null, "Est-ce que le service au bar est ouvert pendant ce vol?", 
	              "AJOUT D'UN VOL", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		opt[2]= obtenirOptionBooleen(bar);
		int servicesP= JOptionPane.showConfirmDialog(
	                   null, "Est-ce que les services payants habituels sont disponibles pendant ce vol?", 
	                   "AJOUT D'UN VOL", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		opt[3]= obtenirOptionBooleen(servicesP);
		int console= JOptionPane.showConfirmDialog(
                     null, "Est-ce que la console de divertissement est disponible pendant ce vol?", 
                     "AJOUT D'UN VOL", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
	    opt[4]= obtenirOptionBooleen(console);
	    int wifi= JOptionPane.showConfirmDialog(
                  null, "Est-ce que le WiFi est disponible pendant ce vol?", 
                  "AJOUT D'UN VOL", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        opt[5]= obtenirOptionBooleen(wifi);
        int prise= JOptionPane.showConfirmDialog(
                   null, "Est-ce qu'une prise d'alimentation est disponible pendant ce vol?", 
                   "AJOUT D'UN VOL", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        opt[6]= obtenirOptionBooleen(prise);
        return opt;
	}
	
	public static void entrerReservation() {
		int numVol= Integer.parseInt(
		        JOptionPane.showInputDialog(
		        null, "Entrez le numéro du vol: ", "AJOUT D'UNE RÉSERVATION", JOptionPane.PLAIN_MESSAGE));
		if (listeMapVols.containsKey(numVol)) {
			int capaciteTotale= listeMapVols.get(numVol).avion.getPlaces();
			if (listeMapVols.get(numVol).res < capaciteTotale) {
				String infoVol= listeMapVols.get(numVol).type+"......"+listeMapVols.get(numVol).num+"......"+
								listeMapVols.get(numVol).destination.trim()+"......"+
								listeMapVols.get(numVol).depart+"......"+
								listeMapVols.get(numVol).res+"......"+listeMapVols.get(numVol).avion+"\n\n";
				infoVol += "Voulez-vous vraiment ajouter une réservation pour ce vol ?";
				int confirmerRes= JOptionPane.showConfirmDialog(
                 		  		  null, infoVol, "AJOUT D'UNE RÉSERVATION", 
           				          JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if (confirmerRes == JOptionPane.YES_OPTION) {
					int siegesDisponibles= capaciteTotale - listeMapVols.get(numVol).getRes();
					int nbRes= confirmerReservation(siegesDisponibles);
					listeMapVols.get(numVol).res += nbRes;
					afficherMessage(nbRes+" siège(s) réservé(s) pour le vol "+numVol+" !");
				}
			} else {
				afficherMessage("Vol complet !");
			}
		} else {
			afficherMessage("Vol introuvable !");
		}
	}
	
	public static void modifierDepart() {
		int numVol= Integer.parseInt(
			        JOptionPane.showInputDialog(
			        null, "Entrez le numéro du vol: ", "MODIFICATION DU DÉPART D'UN VOL", JOptionPane.PLAIN_MESSAGE));
		if (listeMapVols.containsKey(numVol)) {
			String infoVol= listeMapVols.get(numVol).type+"......"+listeMapVols.get(numVol).num+"......"+
					        listeMapVols.get(numVol).destination.trim()+"......"+
					        listeMapVols.get(numVol).depart+"......"+
					        listeMapVols.get(numVol).res+"......"+listeMapVols.get(numVol).avion+"\n\n";
			infoVol += "Voulez-vous vraiment changer la date de départ de ce vol ?";
			int confirmerModification= JOptionPane.showConfirmDialog(
		                               null, infoVol, "MODIFICATION DU DÉPART D'UN VOL", 
		                               JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (confirmerModification == JOptionPane.YES_OPTION) {
				Date nouveauDepart= obtenirDateValide();
				listeMapVols.get(numVol).depart= nouveauDepart;
				afficherMessage("Départ du vol numéro "+numVol+" modifié pour le "+nouveauDepart+" !");
			}
		} else {
			afficherMessage("Vol introuvable !");
		}
	}
	
	public static void retirerVol() {
		int numVol= Integer.parseInt(
				    JOptionPane.showInputDialog(
				    null, "Entrez le numéro du vol: ", "RETRAIT D'UN VOL", JOptionPane.PLAIN_MESSAGE));
		if (listeMapVols.containsKey(numVol)) {
			String infoVol= listeMapVols.get(numVol).type+"......"+listeMapVols.get(numVol).num+"......"+
					        listeMapVols.get(numVol).destination.trim()+"......"+
					        listeMapVols.get(numVol).depart+"......"+
					        listeMapVols.get(numVol).res+"......"+listeMapVols.get(numVol).avion+"\n\n";
			infoVol += "Voulez-vous vraiment retirer ce vol ?";
			int confirmerRetrait= JOptionPane.showConfirmDialog(
		                          null, infoVol, "RETRAIT D'UN VOL", 
		                          JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if (confirmerRetrait == JOptionPane.YES_OPTION) {
				switch (listeMapVols.get(numVol).type) {
					case 'R': 
						VolRegulier.nbVolsReg -= 1;
						break;
					case 'B': 
						VolBasPrix.nbVolsBP -= 1;
						break;
					case 'C': 
						VolCharter.nbVolsCH -= 1;
						break;
					case 'P': 
						VolPrive.nbVolsPV -= 1;
						break;
				}
				listeMapVols.remove(numVol);
				afficherMessage("Vol numéro "+numVol+" retiré !");
			}
		} else {
			afficherMessage("Vol introuvable !");
		}
	}
	
	public static void ajouterVol(int choixType) {
		int numVol= obtenirNumVolValide();
		char typeVol = 'R';
		switch (choixType) {
			case 1: 
				typeVol= 'R';
				break;
			case 2:
				typeVol= 'B';
				break;
			case 3:
				typeVol= 'C';
				break;
			case 4:
				typeVol= 'P';
				break;
		}
		String dest= JOptionPane.showInputDialog(
				     null, "Entrez la destination: ", "AJOUT D'UN VOL", JOptionPane.PLAIN_MESSAGE);
		Date depart= obtenirDateValide();
		Avion avion= obtenirAvionValide(depart);
		int siegesDisponibles= avion.getPlaces();
		int nbRes= confirmerReservation(siegesDisponibles);
		boolean[] opt= new boolean[7];
		opt= confirmerOptionsVol();
		String infoVol= typeVol+"......"+numVol+"......"+dest+"......"+depart+"......"+nbRes+"......"+
		                avion+"\n\n";
        infoVol += "Voulez-vous vraiment ajouter ce vol ?";
        int confirmerAjout= JOptionPane.showConfirmDialog(
                      		null, infoVol, "AJOUT D'UN VOL", 
                      		JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (confirmerAjout == JOptionPane.YES_OPTION) {
        	instancierVolSpecialise(typeVol, numVol, dest, depart, nbRes, avion, opt[0], opt[1], opt[2],
				                    opt[3], opt[4], opt[5], opt[6]);
        	afficherMessage("Vol numéro "+numVol+" créé !");
        }
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
				listeAttributs.forEach((donneesVol) -> {
					char type= donneesVol.get(0).charAt(0);
					int numVol= Integer.parseInt(donneesVol.get(1));
					String dest= donneesVol.get(2);
					String elemsDate[]= new String[3];
				    elemsDate= donneesVol.get(3).split(" ");
				    Date dateInstance= new Date(Integer.parseInt(elemsDate[0]), Integer.parseInt(elemsDate[1]),
		                                        Integer.parseInt(elemsDate[2]));
				    int res= Integer.parseInt(donneesVol.get(4));
				    Avion avionInstance= listeFlotte.get(Integer.parseInt(donneesVol.get(5))-1);
					boolean opt1= Boolean.parseBoolean(donneesVol.get(6));
					boolean opt2= Boolean.parseBoolean(donneesVol.get(7));
					boolean opt3= Boolean.parseBoolean(donneesVol.get(8));
					boolean opt4= Boolean.parseBoolean(donneesVol.get(9));
					boolean opt5= Boolean.parseBoolean(donneesVol.get(10));
					boolean opt6= Boolean.parseBoolean(donneesVol.get(11));
					boolean opt7= Boolean.parseBoolean(donneesVol.get(12));
				    instancierVolSpecialise(type, numVol, dest, dateInstance, res, avionInstance, opt1, opt2,
				    		                opt3, opt4, opt5, opt6, opt7);
			    });
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