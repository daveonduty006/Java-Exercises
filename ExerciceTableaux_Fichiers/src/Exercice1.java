import javax.swing.*;
import java.text.*;
import java.io.*;

public class Exercice1 {

	public static void main(String args[]) throws IOException {
		final int NB_MAX_PROD= 20;
		int tabNoProd[]= new int[NB_MAX_PROD];
		double tabPrixProd[]= new double[NB_MAX_PROD];
		int tabQteTotale[]= new int[NB_MAX_PROD];
		int nbProd; 
		// 
		nbProd = batirTableaux(tabNoProd, tabPrixProd, NB_MAX_PROD);
		//
		traiterLesClients(tabNoProd, tabPrixProd, tabQteTotale, nbProd);
		afficherResultats(tabNoProd, tabQteTotale, nbProd);
		System.exit(0);
		//
	} 

	static int batirTableaux(int tabNoProd[], double tabPrixProd[], 
			int tailleTableaux) throws IOException {
		final String FICHIER_PRODUITS= "src/data/produits.txt";
		int i=0;
		String ligne;
		String elems[];
		BufferedReader ficProd= new BufferedReader(new FileReader(FICHIER_PRODUITS));
		//
		ligne= ficProd.readLine();
		while (i < tailleTableaux && ligne != null) {
			elems= ligne.split(";");
			tabNoProd[i]= Integer.parseInt(elems[0]);
			tabPrixProd[i]= Double.parseDouble(elems[1]);
			++i;
			ligne= ficProd.readLine();
		}
		ficProd.close();
		//
		return i;
	}
	
	static void traiterLesClients(int tabNoProd[], double tabPrix[],
            int tabQteTotale[], int nbProd) {
		DecimalFormat cash= new DecimalFormat("0.00 $");
		int numero, qte, posiProd; 
		double cout;
		char reponse;
		//
		do {
			numero= Integer.parseInt(JOptionPane.showInputDialog(
				"Entrez le numero du produit a acheter :"));
			qte= Integer.parseInt(JOptionPane.showInputDialog(
				"Entrez la quantite desiree :"));
			//
			posiProd= rechercher(tabNoProd, nbProd, numero);
			//
			if (posiProd != -1) {
				tabQteTotale[posiProd]+= qte;
				cout= tabPrix[posiProd] * qte;
				JOptionPane.showMessageDialog(null,
					"Le cout de cet achat est de " + cash.format(cout));
			}else {
				JOptionPane.showMessageDialog(null, "No de produit errone");
			}
			reponse = JOptionPane.showInputDialog(
				"Avez-vous un autre client a traiter O/N ?").charAt(0);
			reponse = Character.toUpperCase(reponse);
			}while (reponse == 'O');
		//
	} 
	
	static int rechercher(int tabNoProd[], int nbProd, int numeroCherche) {
		int posi = -1;
		boolean trouve = false;
		//
		for (int i=0; i < nbProd && !trouve; i++) {
			if (tabNoProd[i] == numeroCherche) {
				posi = i;
				trouve = true;
			}
		}
		//
		return posi;
	} 
	
	static void afficherResultats(int tabNoProd[], int tabQteTotale[],
			int nbProd) {
		JTextArea sortie = new JTextArea();
		//
		sortie.append("Numero du\t\tQuantite\n");
		sortie.append("produit\t\ttotale\n\n");
		for (int i=0; i < nbProd; i++) {
			sortie.append(tabNoProd[i]+"\t\t"+tabQteTotale[i]+"\n");
		}
		//
		JOptionPane.showMessageDialog(null, sortie, "RESULTATS DE LA JOURNEE", 
			JOptionPane.PLAIN_MESSAGE);
		//
	} 
			
}