/*
 * Programme qui permet de calculer et d'afficher le cout de l'achat d'un client 
 * (il y a plusieurs clients a traiter, 
 * mais on suppose qu'un client n'effectue qu'un seul achat) et qui va afficher 
 * (apres avoir traite tous les clients de la journee) la quantite totale vendue pour chaque produit.
 * Auteur: David Normandin
 * Date: 2022-06-03
 */

import javax.swing.*;
import java.text.*;

public class ExerciceTableaux {

	public static void main(String[] args) {
		final int NB_PROD= 8;
		int tabQteTotale[]= new int[NB_PROD];
		int tabNoProd[]=  {234,  125, 657, 987,  213, 934,  678,  776};
		double tabPrix[]= {45.99,9.50,5.75,12.35,9.75,87.45,56.99,76.56};		
		//
		traiterLesClients(tabNoProd, tabPrix, tabQteTotale, NB_PROD);
		afficherResultats(tabNoProd, tabQteTotale, NB_PROD);
		//
		System.exit(0);
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

