import java.io.*;
import java.util.ArrayList;

public class GestionVols {
	
	static final String FICHIER_VOLS= "src/data/Cie_Air_Relax.txt";
	static final String COMPAGNIE= "CieAirRelax";
	static final int MAX_PLACES= 340;
	static BufferedReader tmpVols;
	static ArrayList<Vol> tabVols;
	

	public static void main(String[] args) throws Exception{
		chargerVols();

	}
	
	public static void chargerVols() throws Exception {
		try {
			tabVols= new ArrayList<Vol>();
			String ligne;
			String elemsVol[]= new String[4];
			String elemsDate[]= new String[3];
			tmpVols= new BufferedReader(new FileReader(FICHIER_VOLS));
			ligne= tmpVols.readLine();
			while (ligne != null) {
				elemsVol= ligne.split(";");
				elemsDate= elemsVol[2].split(" ");
				Date dateInstance= new Date(Integer.parseInt(elemsDate[0]),
									        Integer.parseInt(elemsDate[1]),
									        Integer.parseInt(elemsDate[2]));
				tabVols.add(new Vol(Integer.parseInt(elemsVol[0]),
						            elemsVol[1],
						            dateInstance,
						            Integer.parseInt(elemsVol[3])));
				ligne= tmpVols.readLine();
			}
        }catch (FileNotFoundException e) {
            System.out.println("Erreur fichier introuvable");
        }catch (IOException e) {
            System.out.println("Erreur lors de la manipulation du fichier");
        }catch (Exception e) {
            System.out.println("Erreur lors du chargement du fichier");
        }finally {
            tmpVols.close();
        }
	}

}
