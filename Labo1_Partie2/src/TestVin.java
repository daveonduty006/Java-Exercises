/*
 * Créer les trois vins et les afficher
 * Ajouter 2$ au prix du premier vin
 * Modifier le prix et l'origine du deuxième vin
 * Modifier le nom et le type du troisième vin
 * Donner au troisième vin la même origine que le premier
 * Créer un quatrième vin
 * Afficher tous les vins
 */

import javax.swing.*;
import java.util.ArrayList;

public class TestVin {
	
	static ArrayList<Vin> tabVins;
	static JTextArea sortie;

	public static void main(String[] args) {
		instancierVins();
		afficherVins();
		System.exit(0);
	}
	
	public static void instancierVins() {
		tabVins= new ArrayList<>();
		tabVins.add(new Vin("MiamMiam", 2, "Espagne", 8.95));
		tabVins.add(new Vin("Délicieux", "France", 14.5));
		tabVins.add(new Vin("Mystère", 3, "Californie", 10));
	}
	
	public static void afficherVins() {
		sortie= new JTextArea();
		sortie.append("Voici les "+Vin.nbVins+" vins\n");
		sortie.append("Le prix total des vins est de "+
			Vin.df.format(Vin.prixTotal)+"\n");
		tabVins.forEach((unVin) -> sortie.append("              "+
			unVin.toString()));
		JOptionPane.showMessageDialog(null, sortie, "Résultats obtenus",
			JOptionPane.PLAIN_MESSAGE);		
	}

}
