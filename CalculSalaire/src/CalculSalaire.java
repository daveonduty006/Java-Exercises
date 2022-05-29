/*
* CalculSalaire.java
* Programme qui permet de lire a la console le salaire net d'un employe. 
* Si le salaire net (salaireNet) est inferieur a 10000 ou superieur ou egal a 100000, 
* le message "Le salaire est hors norme" s'affiche.
* Auteur: David Normandin
* Date: 2022-05-26
*/

import java.io.*;

public class CalculSalaire {

	public static void main(String[] args) throws IOException {
		final int MIN_SAL = 10000;
		final int MAX_SAL = 100000;
		double salaireNet;
		String saisie;
		BufferedReader clavier = new BufferedReader(new InputStreamReader
				(System.in));
		//
		System.out.print("\nVeuillez entrer le salaire net de l'employe: ");
		saisie = clavier.readLine().replace("$","");
		salaireNet = Double.parseDouble(saisie);
		//
		if (salaireNet < MIN_SAL || salaireNet >= MAX_SAL) {
			System.out.println("Le salaire est hors norme");
		}
	}

}
