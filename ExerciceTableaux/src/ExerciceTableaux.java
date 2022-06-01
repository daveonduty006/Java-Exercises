/*
 * Programme qui permet de calculer et d'afficher le cout de l'achat d'un client 
 * (il y a plusieurs clients a traiter, 
 * mais on suppose qu'un client n'effectue qu'un seul achat) et qui va afficher 
 * (apres avoir traite tous les clients de la journee) la quantite totale vendue pour chaque produit.
 * Auteur:
 * Date: 
 */

import javax.swing.*;
import java.text.*;

public class ExerciceTableaux {

	public static void main(String[] args) {
		final int NB_PROD= 8;
		int tabNoProd[]=      {234,  125, 657, 987,  213, 934,  678,  776};
		double tabPrixProd[]= {45.99,9.50,5.75,12.35,9.75,87.45,56.99,76.56};
		
		
		//
		/*traiterLesClients( );
		 *afficherResultats( );
		 */
		//
		System.exit(0);
	}
	
	static void traiterLesClients(int tabNoProd[], double tabPrix[],
			int tabQteTotale[], int nbProd) {
		DecimalFormat cash = new DecimalFormat("0.00 $");
		int numero, qte, posiProd; // position du numéro dans le tableau tabNoProd
		double cout;
		char reponse;

		do {
			numero = Integer.parseInt(JOptionPane.showInputDialog(
					"Entrez le numéro du produit à acheter :"));
			qte = Integer.parseInt(JOptionPane.showInputDialog(
					"Entrez la quantité désirée :"));
			if (posiProd != -1) {
				
			}

		}
		
	}

}
