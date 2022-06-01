/*
 * Modularite.java
 * Programme qui permet de saisir un nombre entier et 
 * d’afficher la somme de 1 jusqu’a ce nombre.
 * Le processus recommence tant qu’on repond O (pour oui)
 * a la question "Voulez-vous recommencer (O/N)?"
 * Auteur: David Normandin
 * Date: 2022-05-31
 */

import java.io.*;
import javax.swing.*;

public class Modularite {

	public static void main(String[] args) throws IOException {
		int number;
		boolean running= true;
		//
		while (running) {
			number = Integer.parseInt(JOptionPane.showInputDialog(
					"Entrez un nombre: "));
			showSumsByOne(number);
			if ('N' == obtainValidResponse()) {
				running = false;
			}
		}
		//
		System.exit(0);
	}
	
	public static void showSumsByOne(int nb) {
		for (int sum=0; sum < nb; sum++) {
			System.out.println(sum+" + 1 = "+(sum+1));
		}
	}
	
	public static char obtainValidResponse() throws IOException {
		char saisie;
		BufferedReader clavier= new BufferedReader(new InputStreamReader(
				System.in));
		//
		do {
			System.out.print("Voulez-vous recommencer (O/N)? ");
			saisie = clavier.readLine().toUpperCase().charAt(0);	
		}while (saisie != 'O' && saisie != 'N');
		//
		return saisie;		
	}

}
