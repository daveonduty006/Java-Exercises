import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

public class Utilitaires {

    public static final String MSG1 = " ne peut pas �tre inf�rieure �l'ann�e actuelle, soit ";
    public static final String MSG2 = "Impossible de valider le jour puisque votre mois est invalide";
    static BufferedReader tmpReadTexte;
    static BufferedWriter tmpWriteTexte;
    static ObjectInputStream tmpReadObj;
    static ObjectOutputStream tmpWriteObj;
    static Object obj;


    public static String ajouterEspacesFin(int tailleColMax, String chaine) {
        int nbEspaces = tailleColMax - chaine.length();
        String espaces = "";
        for (int i = 0; i < nbEspaces; i++) {
            espaces += " ";
        }
        System.out.println(espaces.length() + "==>" + chaine.length() + "==>" + (espaces.length() + chaine.length()));
        return espaces;
    }

    public static String ajouterEspacesDebut(int tailleColMax, String chaine) {
        int nbEspaces = (tailleColMax - chaine.length()) / 2;
        String espaces = "";
        for (int i = 0; i < nbEspaces; i++) {
            espaces += " ";
        }
        return espaces;
    }

    public static String ajouterCaractereGauche(char car, int longueur, String ch) {
        String rep = "";
        int nbCar = longueur - ch.length();
        for (int i = 1; i <= nbCar; i++) {
            rep += car;
        }
        return rep + ch;
    }
    
    public static void sauvegarderFichierObjets(Object obj, String fichier) throws IOException {
        try {
            tmpWriteObj = new ObjectOutputStream(new FileOutputStream(fichier));
            tmpWriteObj.writeObject(obj);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable. V�rifiez le chemin et nom du fichier.");
        } catch (IOException e) {
            System.out.println("Un probl�me est arriv� lors de la manipulation du fichier. V�rifiez vos donn�es.");
        } catch (Exception e) {
            System.out.println("Un probl�me est arriv� lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Ex�cut� si erreur ou pas
            tmpWriteObj.close();
        }
    }

    public static Object chargerFichierObjets(String fichier) throws Exception {
        try {
            tmpReadObj = new ObjectInputStream(new FileInputStream(fichier));
            obj = tmpReadObj.readObject();
            tmpReadObj.close();
            return obj;
        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable. V�rifiez le chemin et nom du fichier.");
        } catch (IOException e) {
            System.out.println("Un probl�me est arriv� lors de la manipulation du fichier. V�rifiez vos donn�es.");
        } catch (Exception e) {
            System.out.println("Un probl�me est arriv� lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Ex�cut� si erreur ou pas
            tmpReadObj.close();
        }
        return null;
    }

    public static ArrayList<ArrayList<String>> chargerFichierTexte(String fichier, String delimiteurs) throws Exception {
        String ligne;
        // String elems[] = new String[];
        ArrayList<ArrayList<String>> listeAttributs = new ArrayList<ArrayList<String>>();
        ArrayList<String> attributs;
        try {
            tmpReadTexte = new BufferedReader(new FileReader(fichier));
            StringTokenizer st;
            ligne = tmpReadTexte.readLine();// Lire la premi�re ligne du fichier
            while (ligne != null) {// Si ligne == null alors ont a atteint la fin du fichier
                st= new StringTokenizer(ligne, delimiteurs);
                attributs = new ArrayList<String>();
                while(st.hasMoreTokens()){
                     attributs.add(st.nextToken());
                }
                listeAttributs.add(attributs);// elems[0] contient le num�ro du livre;elems[1] le titre et elems[2] les pages
                // biblio.add(new Object(Integer.parseInt(elems[0]), elems[1], Integer.parseInt(elems[2]),
                //         Integer.parseInt(elems[3])));
                ligne = tmpReadTexte.readLine();
            } // fin while

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable. V�rifiez le chemin et nom du fichier.");
        } catch (IOException e) {
            System.out.println("Un probl�me est arriv� lors de la manipulation du fichier. V�rifiez vos donn�es.");
        } catch (Exception e) {
            System.out.println("Un probl�me est arriv� lors du chargement du fichier. Contactez l'administrateur.");
        } finally {// Ex�cut� si erreur ou pas
            tmpReadTexte.close();      
        }
        return listeAttributs;
    }

}