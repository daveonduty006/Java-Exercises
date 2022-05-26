/*
* Facture.java
* Programme qui calcule la facture d’un client avec les taxes,
* puis affiche le coût avant taxes et le coût total
* Auteur: David Normandin
* Date: 2022-05-25
*/

import java.text.DecimalFormat;
import javax.swing.*;

public class Facture {

	public static void main(String[] args) {
		// déclaration des variables
		final double TAUX_TPS = 0.05,    // constantes pour les taxes
				     TAUX_TVQ = 0.09975;
	    double prixUnit,                 // prix unitaire du produit acheté
			   coutAvantTaxes,           // coût avant taxes
			   coutTotal;                // coût total avec taxes
		int qte;                         // quantité du produit acheté
		String saisie;                   // chaîne pour la lecture
		// lecture des données en entrée
		saisie = JOptionPane.showInputDialog("Entrez le prix du produit acheté");
		prixUnit = Double.parseDouble(saisie);
		saisie = JOptionPane.showInputDialog("Entrez la quantité du produit acheté");
		qte = Integer.parseInt(saisie);
		// traitement des données
		coutAvantTaxes = prixUnit * qte;
		coutTotal = coutAvantTaxes + 
				    ((coutAvantTaxes*TAUX_TPS)+(coutAvantTaxes*TAUX_TVQ));
		// affichage des données pertinentes
		DecimalFormat df = new DecimalFormat(".00");
		JOptionPane.showMessageDialog(null, "Sous-total = "+coutAvantTaxes+
				                      "$\nTotal = "+df.format(coutTotal)+"$");
		// fin du programme
		System.exit(0);
	}

}
