/*
 * saisir au clavier la couleur et le c�t� de 4 objets Carre
 * afficher la couleur et le c�t� de chaque carr� demand�
 * rejeter une valeur n�gative pour le c�t� en affichant un message appropri� (on ne cr�e
 * pas l'objet dans ce cas)
 * calculer et afficher le p�rim�tre de chaque objet Carre cr��
 * comptabiliser le nombre de carr�s de couleur rouge et l�afficher
 * comptabiliser le nombre d�erreurs et l�afficher
 */

import java.io.*;
import javax.swing.*;

public class TestCarre {

	public static void main(String[] args) throws IOException {
		final int NB_CARRES= 4;
		int erreur=0, nbCarreRouge=0;
		Carre tabCarres[]= new Carre[NB_CARRES];
		JTextArea sortie = new JTextArea();
		//
		for (int i=0; i < NB_CARRES; i++) {
			instanceCarre(tabCarres, i, sortie);
			if (tabCarres[i] != null) {
				if ("rouge".equals(tabCarres[i].getCouleur())) {
					nbCarreRouge++;
				}
			}else {
				erreur++;
			}	
		}		
		//
		sortie.append("\n"+nbCarreRouge+" carre(s) de couleur rouge, "+
			erreur+" erreur(s)");
		JOptionPane.showMessageDialog(null, sortie, "Creation de carres",
				JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
		//
	}
	
	static void instanceCarre(Carre t[], int i, JTextArea s) 
			throws IOException {
		int cote;
		String couleur;
		BufferedReader clavier= new BufferedReader(new InputStreamReader(
			System.in));
		//
		System.out.print("Entrez la couleur du carre no "+(i+1)+": ");
		couleur= clavier.readLine().toLowerCase();
		System.out.print("Entrez la largeur de ses cotes: ");
		cote= Integer.parseInt(clavier.readLine());
		s.append("Pour "+(i+1)+", vous avez demande un carre "+couleur+
			" de largeur "+cote+"\n");
		//
		if (cote > 0) {
			Carre carre= new Carre(couleur, cote);
			t[i]= carre;
			s.append("Carre cree, son perimetre est "+
				carre.getPerimetre()+"\n\n");
		}else {
			s.append("-----> Cote invalide, carre non-cree\n\n");
		}
	}

}
