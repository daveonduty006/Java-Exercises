/*
* Facture.java
* Programme qui calcule la facture d’un client avec les taxes,
* puis affiche le cout avant taxes et le cout total
* Auteur: David Normandin
* Date: 2022-05-25
*/

import java.text.DecimalFormat;
import javax.swing.*;

public class Facture {

	public static void main(String[] args) {
		// declaration des variables
		final double TAUX_TPS = 0.05,    // constantes pour les taxes
				     TAUX_TVQ = 0.09975;
	    double prixUnit,                 // prix unitaire du produit achete
			   coutAvantTaxes,           // cout avant taxes
			   coutTotal;                // cout total avec taxes
		int qte;                         // quantite du produit achete
		String saisie;                   // chaine pour la lecture
		// lecture des donnees en entree
		saisie = JOptionPane.showInputDialog("Entrez le prix du produit achete");
		prixUnit = Double.parseDouble(saisie);
		saisie = JOptionPane.showInputDialog("Entrez la quantite du produit "
				+ "achete");
		qte = Integer.parseInt(saisie);
		// traitement des donnees
		coutAvantTaxes = prixUnit * qte;
		coutTotal = coutAvantTaxes + 
				    ((coutAvantTaxes*TAUX_TPS)+(coutAvantTaxes*TAUX_TVQ));
		// affichage des donnees pertinentes
		DecimalFormat df = new DecimalFormat("0.00");
		JOptionPane.showMessageDialog(null, "Sous-total: "+
				df.format(coutAvantTaxes)+"$\nTotal: "+df.format(coutTotal)+"$");
		// fin du programme
		System.exit(0);
	}

}
