/*
* Meteo.java
* Programme qui lit la temperature puis affiche un message:
* -temperature >= 20 affiche "Il fait chaud";
* -temperature < 20 et >= 0 affiche "Il ne fait ni chaud ni froid";
* -temperature < 0 affiche "Il fait froid"
* Auteur: David Normandin
* Date: 2022-05-27
*/

import java.io.*;

public class Meteo {

	public static void main(String[] args) throws IOException {
		int temp;
		String message, saisie;
		BufferedReader clavier = new BufferedReader(new InputStreamReader(
				System.in));
		//
		System.out.print("\nEntrez la temperature en celsius: ");
		saisie = clavier.readLine().replace("C", "").replace("c", "");
		temp = Integer.parseInt(saisie);
		//
		if (temp >= 0) {
			if (temp >= 20) {
				message = "Il fait chaud";
			}else {
				message = "Il ne fait ni chaud ni froid";
			}
		}else {
			message = "Il fait froid";
		}
		//
		System.out.println(message);						
	}

}
