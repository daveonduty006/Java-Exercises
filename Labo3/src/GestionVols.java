import java.awt.Font;
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
		creerVolsSpecialises();
		int choix, choixMap, choixVol;
	}
	
	public static void chargerFlotte() {
		listeFlotte= new ArrayList<>();
		listeFlotte.add(new Avion(1, "Boeing 747", 348, "Long-Courrier", true, true, true));
		listeFlotte.add(new Avion(2, "Boeing 737", 166, "Long-Courrier", true, true, true));
		listeFlotte.add(new Avion(3, "Boeing 727", 149, "Moyen-Courrier", true, true, true));
		listeFlotte.add(new Avion(4, "Cessna Citation XLS", 12, "Court-Courrier", true, false, false));		
	}
	
	@SuppressWarnings("unchecked")
	public static void chargerVols() {
		try{
			File f= new File(FICHIER_VOLS_OBJ);
			if (f.exists()) {
				listeMapVols= (HashMap<Integer, Vol>) Utilitaires.chargerFichierObjets(FICHIER_VOLS_OBJ);
			} else {
				ArrayList<ArrayList<String>> listeAttributs = Utilitaires.chargerFichierTexte(FICHIER_VOLS_TEXTE, ";");
				listeAttributs.forEach((donneesVol) -> {
					String elemsDate[]= new String[3];
					char type= donneesVol.get(0).charAt(0); 
					int num= Integer.parseInt(donneesVol.get(1));
					String dest= donneesVol.get(2);
				    elemsDate= donneesVol.get(3).split(" ");
				    Date dateInstance= new Date(Integer.parseInt(elemsDate[0]), Integer.parseInt(elemsDate[1]),
		                                        Integer.parseInt(elemsDate[2]));
				    int res= Integer.parseInt(donneesVol.get(4));
					listeMapVols.put(num, new Vol(type, num, dest, dateInstance, res));
				});
			}
		}catch(Exception e){
			System.out.println("Problème");
		}
	}
	
}