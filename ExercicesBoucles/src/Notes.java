/* Notes.java
 * Programme qui permet de lire 10 notes (nombres reels), 
 * trouver et afficher la note la plus elevee, 
 * calculer et afficher la moyenne de la classe. 
 * De plus, on desire afficher le nombre d'eleves qui ont coule (note moins de 60).
 * Auteur: David Normandin
 * Date: 2022-05-27
*/

import java.text.*;
import javax.swing.*;

public class Notes {

	public static void main(String[] args) {
		final int NB_NOTES=10;
		final double PASSAGE=60;
		int nbEchec=0;
		double note, moyenne=0, meilleurNote=0;		
		String saisie;
		DecimalFormat df = new DecimalFormat("0.0");
		//
		for (int i = 1; i <= NB_NOTES; i++) {
			saisie = JOptionPane.showInputDialog(
					   "Entrez la note "+i+": ");
			note = Double.parseDouble(saisie);
			if (note >= meilleurNote) {
				meilleurNote = note;				
			}
			if (note < PASSAGE) {
				nbEchec++;
			}
			moyenne += note;
		}
		moyenne /= NB_NOTES;
		//
		JOptionPane.showMessageDialog(null, "Moyenne: "+df.format(moyenne)+
				"%\nNombre d'echecs: "+nbEchec+"\nMeilleure note: "+
				meilleurNote+"%");
		//
		System.exit(0);
	}

}
