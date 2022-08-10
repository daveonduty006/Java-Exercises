import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionVols {

	static final String FICHIER_VOLS_TEXTE = "src/data/Cie_Air_Relax.txt";
	static final String FICHIER_VOLS_OBJ = "src/data/Cie_Air_Relax.obj";
	static List<Avion> listeFlotte;
	static Map<Integer,Vol> listeMapVols;	
	static JTextArea sortie;

	public static void main(String[] args) throws Exception {
		chargerFlotte();
		chargerVols();
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
			System.out.println("Probl�me");
		}
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
	
}