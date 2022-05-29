/*
* Logement.java
* Programme qui lit un codeLogement, puis affiche un message:
* -codeLogement = 'P' affiche "Vous etes proprietaire";
* -codeLogement = 'L' affiche "Vous etes locataire";
* -codeLogement = 'E' ou 'X' affiche "Vous habitez chez vos parents";
* -autre valeur de codeLogement affiche "Vous etes un sans-abri"
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
				messageSortie = "Vous etes proprietaire";
				break;
			case 'L':
				messageSortie = "Vous etes locataire";
				break;
			case 'E':
			case 'X':
				messageSortie = "Vous habitez chez vos parents";
				break;
			default:
				messageSortie = "Vous etes sans domicile fixe";			
		}
		//
		JOptionPane.showMessageDialog(null, messageSortie);
		//
		System.exit(0);
	}

}
