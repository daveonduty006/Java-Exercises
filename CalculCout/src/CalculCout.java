/*
* CalculCout.java
* Programme qui lit une quantité et un prix, 
* puis calcule et affiche le coût brut de l'achat, 
* le montant du rabais et le coût net de l'achat
* Auteur: David Normandin
* Date: 2022-05-26
*/

import javax.swing.*;
import java.text.DecimalFormat;

public class CalculCout {

	public static void main(String[] args) {
		final double TAUX_RABAIS = 0.1;
		final int SEUIL_RABAIS = 5;
		int qte;
		double prixUnit, coutBrut, coutNet, montantRabais=0;
		String saisie;
		//
		saisie = JOptionPane.showInputDialog("Quantité achetée du produit: ");
		qte = Integer.parseInt(saisie);
		saisie = JOptionPane.showInputDialog("Prix unitaire du produit: ");
		prixUnit = Double.parseDouble(saisie);
		//
		coutBrut = qte * prixUnit;		
		if (qte > SEUIL_RABAIS) {
			montantRabais = coutBrut * TAUX_RABAIS;
			coutNet = coutBrut - montantRabais;
		}else {
			coutNet = coutBrut;
		}
		//
		DecimalFormat df = new DecimalFormat(".00");
		JOptionPane.showMessageDialog(null, "Coût Brut: "+df.format(coutBrut)+
           "$\nMontant du Rabais: "+df.format(montantRabais)+"$\nCoût Net: "+
		   df.format(coutNet)+"$");
		//
		System.exit(0);
	}

}
