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
		int tabQteTotale[]= new int[8];
		int tabNoProd[]=  {234,  125, 657, 987,  213, 934,  678,  776};
		double tabPrix[]= {45.99,9.50,5.75,12.35,9.75,87.45,56.99,76.56};		
		//
		traiterLesClients(tabNoProd, tabPrix, tabQteTotale, NB_PROD);
		afficherResultats(tabNoProd, tabQteTotale, NB_PROD);
		//
		System.exit(0);
	} // fin de la méthode main
	
	static void traiterLesClients(int tabNoProd[], double tabPrix[],
			                      int tabQteTotale[], int NB_PROD) {
		DecimalFormat cash= new DecimalFormat("0.00 $");
		int numero, qte, posiProd; // position du numéro dans le tableau tabNoProd
		double cout;
		char reponse;

		do {
			numero= Integer.parseInt(JOptionPane.showInputDialog(
					"Entrez le numero du produit a acheter :"));
			qte= Integer.parseInt(JOptionPane.showInputDialog(
					"Entrez la quantite desiree :"));
			for (int i=0; tabNoProd[i] != numero; i++) {
				if (tabNoProd[i] ==)
			}
			if (posiProd != -1) {
				cout; 
				JOptionPane.showMessageDialog(null,
					"Le coût de cet achat est de " + cash.format(cout));
			}else {
				JOptionPane.showMessageDialog(null, "No de produit erroné");
				reponse = JOptionPane.showInputDialog(
					"Avez-vous un autre client à traiter O/N ?").charAt(0);
				reponse = Character.toUpperCase(reponse);
		}while (reponse == 'O');		
	} // fin de la méthode traiterLesClients

	static int rechercher(int tab[], int nbEl, int valeurCherchee) {
		int posi = -1;
		boolean trouve = false;
		for (int i = 0; i < nbEl && !trouve; i++)
		if (tab[i] == valeurCherchee) {
			posi = i;
			trouve = true;
		}
		return posi;
	} // fin de la méthode rechercher
		
	static void afficherResultats(int tabNoProd[], int tabQteTotale[],
								  int NB_PROD) {
		
	} // fin de la méthode afficherResultats

} // fin de la classe
