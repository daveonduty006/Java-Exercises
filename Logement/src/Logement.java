/*
* Logement.java
* Programme qui lit un codeLogement, puis affiche un message:
* -codeLogement = 'P' affiche "Vous êtes propriétaire";
* -codeLogement = 'L' affiche "Vous êtes locataire";
* -codeLogement = 'E' ou 'X' affiche "Vous habitez chez vos parents";
* -autre valeur de codeLogement affiche "Vous êtes un sans-abri"
* Auteur: David Normandin
* Date: 2022-05-27
*/

import javax.swing.*;

public class Logement {

	public static void main(String[] args) {
		char codeLogement;
		String messageSortie;
		//
		codeLogement = JOptionPane.showInputDialog("Entrez un codeLogement: ")
				       .toUpperCase().charAt(0);
		//
		switch(codeLogement) {
			case 'P':
				messageSortie = "Vous êtes propriétaire";
				break;
			case 'L':
				messageSortie = "Vous êtes locataire";
				break;
			case 'E':
			case 'X':
				messageSortie = "Vous habitez chez vos parents";
				break;
			default:
				messageSortie = "Vous êtes sans domicile fixe";			
		}
		//
		JOptionPane.showMessageDialog(null, messageSortie);
		//
		System.exit(0);
	}

}
