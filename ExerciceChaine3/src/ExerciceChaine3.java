/*
 * Programme qui permet de lire une chaine de caractères et de 
 * l'afficher a l'envers.
 * Auteur: David Normandin
 * Date: 2022-05-29
 */

import javax.swing.*;

public class ExerciceChaine3 {

	public static void main(String[] args) {
		String chaine;
		String nouvelleChaine="";
		//
		chaine = JOptionPane.showInputDialog("Entrez une chaine de caracteres");
		//
		for (int i=chaine.length()-1; i > -1; i--) {
			nouvelleChaine += chaine.charAt(i);			
		}
		//
		JOptionPane.showMessageDialog(null, nouvelleChaine, "RÉSULTATS", 
				JOptionPane.PLAIN_MESSAGE);
		//
		System.exit(0);
	}

}
