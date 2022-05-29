/*
 * Programme qui permet de lire une chaine de caracteres et 
 * d'afficher les caracteres de la chaine lue dans une boite de dialogue, 
 * un par ligne
 * Auteur: David Normandin
 * Date: 2022-05-29
 */

import javax.swing.*;

public class ExerciceChaine1 {

	public static void main(String[] args) {
		String chaine;
		String nouvelleChaine="";
		//
		chaine = JOptionPane.showInputDialog("Entrez une chaine de caracteres");
		//
		for (int i=0; i < chaine.length(); i++) {
			nouvelleChaine += chaine.charAt(i)+"\n";
		}
		//
		JOptionPane.showMessageDialog(null, nouvelleChaine, "RESULTATS", 
				JOptionPane.PLAIN_MESSAGE);
		//
		System.exit(0);
		}

}
