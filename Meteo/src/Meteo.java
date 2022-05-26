/*
* Meteo.java
* Ce programme lit la temperature puis affiche un message :
* temperature >= 20 affiche "Il fait chaud"
* temperature < 20 et >= 0 affiche "Il ne fait ni chaud ni froid"
* temperature < 0 affiche "Il fait froid"
* Auteur:
* Date:
*/

import java.io.*;

public class Meteo {

	public static void main(String[] args) throws IOException {
		int temp;
		String saisie;
		BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
		//
		System.out.print("\nEntrez la température en celsius d'aujourd'hui: ");
		saisie = clavier.readLine().replace("C", "").replace("c", "");
		temp = Integer.parseInt(saisie);
		
		
		
	}

}
