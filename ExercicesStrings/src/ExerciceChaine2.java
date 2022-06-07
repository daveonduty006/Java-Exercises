/*
 * Programme qui permet de lire une chaine de caracteres et de remplacer
 * tous les espaces par des $. 
 * Si la phrase ne possede aucun espace, on doit plutot convertir la chaine
 * lue en majuscules.
 * Auteur: David Normandin
 * Date: 2022-05-29
 */

import javax.swing.*;

public class ExerciceChaine2 {

	public static void main(String[] args) {
		String chaine;
		String nouvelleChaine="";
		//
		chaine = JOptionPane.showInputDialog("Entrez une chaine de caracteres");
		//
		if (chaine.contains(" ")) {
			nouvelleChaine = chaine.replace(' ', '$');
		}else {
			nouvelleChaine = chaine.toUpperCase();
		}
		//
		JOptionPane.showMessageDialog(null, nouvelleChaine, "RESULTATS", 
				JOptionPane.PLAIN_MESSAGE);
		//
		System.exit(0);
	}

}
