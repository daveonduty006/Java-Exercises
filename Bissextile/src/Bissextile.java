/*
 * Programme qui permet de determiner si une annee est bissextile
 * Auteur: David Normandin
 * Date: 2022-05-29
 */

import javax.swing.*;

public class Bissextile {

	public static boolean estBissextile(int annee) {
		boolean check = false;
		if (annee % 100 == 0) {
			if (annee % 400 == 0) {
				check = true;
			}
			return check;
		}
		if (annee % 4 == 0) {
			check = true;
		}
		return check;
	}

	public static void main(String[] args) {
		int annee;
		boolean reponse;
		String message= " n'est pas bissextile";
		//
		annee = Integer.parseInt(JOptionPane.showInputDialog("Entrez une annee"));
		//
		reponse = estBissextile(annee);
		if (reponse) {
			message = " est bissextile";
		}
		//
		JOptionPane.showMessageDialog(null, "L'annee "+annee+message);
		//
		System.exit(0);
	}

}
