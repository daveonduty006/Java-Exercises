/*
 * Programme qui permet de lire une chaine de caracteres et 
 * de l'afficher en soulignant les consonnes par "="
 * Auteur: David Normandin
 * Date: 2022-05-29
 */

import javax.swing.*;
import java.awt.Font;

public class ExerciceChaine4 {
	
	public static boolean estConsonne(char lettre) {
		return ("AEIOUYaeiouy".indexOf(lettre) == -1);
	}

	public static void main(String[] args) {
		boolean consonneCheck;
		String chaine;
		String soulignement = "";
		JTextArea sortie = new JTextArea();
		sortie.setFont(new Font("Courier", Font.BOLD, 15));
		//
		chaine = JOptionPane.showInputDialog("Entrez une chaine de caracteres");
		//
		for (int i=0; i < chaine.length(); i++) {
			consonneCheck = estConsonne(chaine.charAt(i));
			if (consonneCheck) {
				soulignement += "=";
			}else {
				soulignement += " ";
			}
		}
		//
		sortie.setText(chaine + "\n" + soulignement);
		JOptionPane.showMessageDialog(null, sortie, "RESULTAT", 
				JOptionPane.PLAIN_MESSAGE);
		//
		System.exit(0);
	}

}
