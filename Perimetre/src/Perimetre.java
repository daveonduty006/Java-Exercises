/*
 * Perimetre.java
 * Programme qui permet de calculer le perimetre d'un rectangle
 * Auteur: David Normandin
 * Date: 2022-05-31
 */

import java.io.*;
import java.text.DecimalFormat;

public class Perimetre {

	public static void main(String[] args) throws IOException {
		double width, length;
		DecimalFormat df = new DecimalFormat("0.000");
		BufferedReader clavier= new BufferedReader(new InputStreamReader(
				System.in));
		//
		System.out.print("Largeur du Rectangle (chiffre seulement): ");
		width = Double.parseDouble(clavier.readLine());
		System.out.print("Longueur du Rectangle (chiffre seulement): ");
		length = Double.parseDouble(clavier.readLine());
		//
		System.out.println("Le perimetre du rectangle est de "+
				df.format(calcRectPerimeter(width, length)));
	}
	
	public static double calcRectPerimeter(double w, double l) {
		return (2*(w+l));	
	}

}
