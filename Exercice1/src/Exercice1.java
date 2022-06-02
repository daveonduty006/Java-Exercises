import javax.swing.*;
import java.text.*;

public class Exercice1 {

	public static void main(String[] args) {
		final int NB_MAX_PROD = 20;
		int tabNoProd[] =      {234,  125, 657, 987,  213, 934,  678,  776};
		double tabPrixProd[] = {45.99,9.50,5.75,12.35,9.75,87.45,56.99,76.56};
		int tabQte[];
		int nbProd; // le nombre de produits contenus dans le fichier
		
		nbProd =
		traiterLesClients(tabNoProd, tabPrixProd, tabQte, nbProd);
		afficherResultats(tabNoProd, tabQte, nbProd);
		System.exit(0);

	}

}
