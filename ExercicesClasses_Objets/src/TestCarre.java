/*
 * saisir au clavier la couleur et le côté de 4 objets Carre
 * afficher la couleur et le côté de chaque carré demandé
 * rejeter une valeur négative pour le côté en affichant un message approprié (on ne crée
 * pas l'objet dans ce cas)
 * calculer et afficher le périmètre de chaque objet Carre créé
 * comptabiliser le nombre de carrés de couleur rouge et l’afficher
 * comptabiliser le nombre d’erreurs et l’afficher
 */

import java.io.*;
import javax.swing.*;

public class TestCarre {

	public static void main(String[] args) throws IOException {
		final int NB_CARRES= 4;
		int erreur=0;
		Carre tabCarres[]= new Carre[NB_CARRES];
		JTextArea sortie = new JTextArea();
		//
		for (int i=1; i < tabCarres.length; i++) {
			erreur= instanceCarre(tabCarres, i, sortie, erreur);
		}
		//
		
	
	}
	
	static int instanceCarre(Carre t[], int i, JTextArea s, int e) 
			throws IOException {
		int cote;
		String couleur;
		BufferedReader clavier= new BufferedReader(new InputStreamReader(
			System.in));
		//
		System.out.println("Entrez la couleur du carre no "+i);
		couleur= clavier.readLine().toLowerCase();
		System.out.println("Entrez la largeur de ses cotes");
		cote= Integer.parseInt(clavier.readLine());
		s.append("Pour "+i+",vous avez demande un carre "+couleur+
			" de largeur "+cote+"\n");
		//
		if (cote > 0) {
			Carre carre= new Carre(couleur, cote);
			t[i-1]= carre;
			s.append("Carre cree, son perimetre est de "+
				carre.getPerimetre()+"\n\n");
		}else {
			s.append("------> Cote invalide, carre non-cree\n\n");
			e++;
		}
		return e;
	}

}
