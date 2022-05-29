/*
 * Programme offrant a l'usager un menu. 
 * Lorsque l'usager selectionne :
 * -le choix 1 affiche "Choix 1";
 * -le choix 2 affiche "Choix 2"; 
 * -le choix 3 affiche "Au revoir";
 * -un autre choix affiche "Choix invalide".
 * Le menu doit toujours revenir tant que le choix n'est pas 3.
 * Auteur: David Normandin
 * Date: 2022-05-29
 */

import javax.swing.*;

public class Menu {

	public static void main(String[] args) {
		String choix;
		//		
		do {
			choix = JOptionPane.showInputDialog("MENU\n1. Choix 1\n2. Choix 2\n"
					+ "3. Quitter\n  Choisissez une option");
		//
			switch(choix) {
				case "1":
				case "2":
					JOptionPane.showMessageDialog(null, "Choix "+choix);
					break;
				case "3":
					JOptionPane.showMessageDialog(null, "Au Revoir!");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Choix invalide");					
			}
		}while (!"3".equals(choix));	
		//
		System.exit(0);
	}
}
