/*
 * Programme qui permet de lire 10 salaires au clavier et 
 * de les memoriser dans un tableau. 
 * Le programme calcule ensuite le salaire moyen, 
 * le nombre de salaires au dessus de 30000 et 
 * le nombre de salaires de moins que 20000
 * Auteur: David Normandin
 * Date: 2022-05-29
 */

import javax.swing.*;
import java.text.DecimalFormat;

public class ExerciceTableau {

	public static void main(String[] args) {
		int nbGrandSal= 0;
		int nbPetitSal= 0;
		double salMoyen= 0;
		double[] tabSalaires= new double[10];
		String saisie;
		DecimalFormat df = new DecimalFormat("0.00");
		//
		for (int i=0; i < tabSalaires.length; i++) {
			saisie = JOptionPane.showInputDialog("Entrez le salaire "+(i+1));
			tabSalaires[i] = Double.parseDouble(saisie);
			if (tabSalaires[i] > 30000) {
				nbGrandSal += 1;
			}else if (tabSalaires[i] < 20000) {
				nbPetitSal += 1;
			}
			salMoyen += tabSalaires[i];
		}
		salMoyen /= tabSalaires.length;
		//
		JOptionPane.showMessageDialog(null, "Salaire moyen: "+df.format(salMoyen)+
				"$\n\nSalaires au-dessus de 30,000$: "+nbGrandSal+
				"\nSalaires en-dessous de 20,000$: "+nbPetitSal,
				"RESULTATS", JOptionPane.PLAIN_MESSAGE);
		//
		System.exit(0);
	}

}
