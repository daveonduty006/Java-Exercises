/*
 * ...
 * Auteur: David Normandin
 * Date: 2022-06-01
 */

import java.util.*;

public class ExempleStringTokenizer {
	
	static String personne= "Marie Curie;Scientifique;73;France";
	static String sepStrTok = ";#";
	static String sepSplit = ";|#";

	public static void main(String[] args) {
		//avecStringTokenizer();
		avecSplit();
	}
	
	public static void avecStringTokenizer() {
		StringTokenizer str= new StringTokenizer(personne, sepStrTok);
		//
		/*while (str.hasMoreTokens()) {
			System.out.println(str.nextToken());
		}*/
		System.out.println("Nom: "+str.nextToken());
		System.out.println("Profession: "+str.nextToken());
		System.out.println("Age: "+str.nextToken());
		System.out.println("Pays: "+str.nextToken());
	}
	
	public static void avecSplit() {
		String[] tab;
		//
		tab= personne.split(sepSplit);
		//
		/*for (String element: tab) {
			System.out.println(element);
		}*/
		System.out.println("Nom: "+tab[0]);
		System.out.println("Profession: "+tab[1]);
		System.out.println("Age: "+tab[2]);
		System.out.println("Pays: "+tab[3]);
	}

}
