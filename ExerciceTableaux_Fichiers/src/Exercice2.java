import javax.swing.*;
import java.io.*;
	
public class Exercice2 {

	public static void main(String args[]) throws IOException {
		final int NB_PARTIS= 3,
				  NB_BUREAUX= 10;
		final String FICHIER_VOTES = "src/data/votes.txt";
		int tabTotParti[]= new int[NB_PARTIS];
		int tabTotBureau[]= new int[NB_BUREAUX];
		int noParti, noBureau, nbVotes;
		String data[];
		String ligne;
		BufferedReader fichier= new BufferedReader(new FileReader(
			FICHIER_VOTES));
		JTextArea sortie= new JTextArea();
		//
		ligne= fichier.readLine();
		while (ligne != null) {
			data = ligne.split(" ");
			noBureau = Integer.parseInt(data[0]);
			noParti = Integer.parseInt(data[1]);
			nbVotes = Integer.parseInt(data[2]);
			switch(noParti) {
				case 1: 
					tabTotParti[0] += nbVotes;
					break;
				case 2:
					tabTotParti[1] += nbVotes;
					break;
				case 3:
					tabTotParti[2] += nbVotes;
					break;
				default:
					System.out.println("numero de parti invalide");
			}
			tabTotBureau[noBureau-1] += nbVotes;
			ligne = fichier.readLine();
		//
		}
		fichier.close(); 
		//
		sortie.append("PARTI\tTOTAL DES VOTES\n  "+
		               1+"\t  "+tabTotParti[0]+"\n  "+
				       2+"\t  "+tabTotParti[1]+"\n  "+
		               3+"\t  "+tabTotParti[2]+"\n");
		sortie.append("\nBUREAU\tTOTAL DES VOTANTS\n  ");
		for (int i=0; i < NB_BUREAUX; i++) {
			sortie.append((i+1)+"\t  "+tabTotBureau[i]+"\n  ");
		}
		JOptionPane.showMessageDialog(null, sortie, "RÉSULTATS DE L'ÉLECTION", 
			JOptionPane.PLAIN_MESSAGE);
		System.exit(0);
		//
	} 
	
}