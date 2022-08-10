import java.awt.Font;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class GestionLivres {
	
	static final String FICHIER_LIVRES_TXT = "src/donnees/livres.txt";
	static final String FICHIER_LIVRES_OBJ = "src/donnees/livres.obj";
	static final String TITRE = "GESTION DES LIVRES";
	static ArrayList<Livre> listeLivres;
	static BufferedReader tmpLivresRead;
	static BufferedWriter tmpLivresWrite;
	static ObjectInputStream tmpLivresReadObj;
	static ObjectOutputStream tmpLivresWriteObj;
	static JTextArea sortie;
	static JScrollPane defiler;
	
	public static void main(String[] args) throws Exception{
		File f = new File(FICHIER_LIVRES_OBJ);
		if (f.exists()) {
			chargerLivresFichierObj();
		}else {
			chargerLivresFichierTxt();
		}
		int choix;
		do {
			choix = menuGeneral();
			switch (choix) {
				case 1:
					listerLivres();
					break;
				case 2:
					listerLivresTries();
					break;
				case 3:
					listerLivreParticulier();
					break;
				case 4:
					listerLivresAuteur();
					break;
				case 5:
					supprimerLivre();
					break;
				case 6:
					sauvegarderLivres();
					afficherMessage("Merci d'avoir utilisé notre système");
					break;
				default:
					afficherMessage("Choix invalide. Les options sont [1-6] !");
					break;
			}
		} while (choix != 6);
		System.exit(0);
	}
	
	public static int menuGeneral() {
		String contenu = "1. Afficher tous les livres\n"+
	                     "2. Afficher tous les livres triés\n"+
				         "3. Afficher par numéro\n"+
	                     "4. Afficher les livres d'un auteur en particulier\n"+
				         "5. Supprimer un livre\n"+
	                     "6. Terminer\n\n";
		contenu += "Entrez votre choix parmis[1-6] : ";
		return Integer.parseInt(JOptionPane.showInputDialog(null, contenu, 
			TITRE, JOptionPane.PLAIN_MESSAGE));
	}
	
	public static void afficherEntete() {
		sortie = new JTextArea(20, 60);
		sortie.setFont(new Font("monospace", Font.PLAIN, 12));
		defiler = new JScrollPane(sortie);
		sortie.append("\t\t\tLISTE DES LIVRES\n\n");
		sortie.append("NUMÉRO\tTITRE\t\tAUTEUR\tANNÉE\tPAGES\tCATÉGORIE\n");
	}
	
	private static void afficherMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, TITRE, 
			JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void sauvegarderLivres() throws IOException {
		try {
			tmpLivresWriteObj = new ObjectOutputStream(new FileOutputStream(FICHIER_LIVRES_OBJ));
			tmpLivresWriteObj.writeObject(listeLivres);
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un problème est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
		} catch (Exception e) {
			System.out.println("Un problème est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {
			tmpLivresWriteObj.close();
		}
	}
	
	public static void supprimerLivre() {
		int num;
		String msg= "Entrez le numéro du livre à supprimer: ";
		num = Integer.parseInt(JOptionPane.showInputDialog(null, msg, TITRE,
			JOptionPane.PLAIN_MESSAGE));
		listeLivres.removeIf((unLivre) -> unLivre.getNum() == num);
	}
	
	public static void listerLivresAuteur() {
		int auteur;
		String msg= "Entrez le numéro de l'auteur recherché: ";
		do {
			auteur= Integer.parseInt(JOptionPane.showInputDialog(null, msg, 
				    TITRE, JOptionPane.PLAIN_MESSAGE));
		} while (! String.valueOf(auteur).chars().allMatch(Character::isDigit));
		afficherEntete();
		for (Livre unLivre: listeLivres) {
			if (unLivre.getAuteur() == auteur) {
				sortie.append(unLivre.toString());
			}
		}
		JOptionPane.showMessageDialog(null, defiler, TITRE, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void listerLivreParticulier() {
		int num;
		String msg= "Entrez le numéro du livre recherché: ";
		do {
			num= Integer.parseInt(JOptionPane.showInputDialog(null, msg, 
				 TITRE, JOptionPane.PLAIN_MESSAGE));
		} while (! String.valueOf(num).chars().allMatch(Character::isDigit));
		Livre LivreBidon= new Livre();
		LivreBidon.setNum(num);
		int pos= listeLivres.indexOf(LivreBidon);
		if (pos != -1) {
			Livre LivreTrouve= listeLivres.get(pos);
			afficherEntete();
			sortie.append(LivreTrouve.toString());
			JOptionPane.showMessageDialog(null, defiler, TITRE, JOptionPane.PLAIN_MESSAGE);
		}else {
			msg= "Livre inexistant!";
			JOptionPane.showMessageDialog(null, msg, TITRE, JOptionPane.PLAIN_MESSAGE);	
		}
	}
	
	public static void listerLivresTries() {
		afficherEntete();
		Collections.sort(listeLivres);
		listeLivres.forEach((unLivre) -> {
			sortie.append(unLivre.toString());
		});
		sortie.append("\nNombre de livres = " + listeLivres.size());
		JOptionPane.showMessageDialog(null, defiler, TITRE, JOptionPane.PLAIN_MESSAGE);		
	}
	
	public static void listerLivres() {
		afficherEntete();
		listeLivres.forEach((unLivre) -> {
			sortie.append(unLivre.toString());
		});
		sortie.append("\nNombre de livres = " + listeLivres.size());
		JOptionPane.showMessageDialog(null, defiler, TITRE, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static void chargerLivresFichierObj() throws Exception {
		try {
			tmpLivresReadObj = new ObjectInputStream (new FileInputStream(FICHIER_LIVRES_OBJ));
			listeLivres = (ArrayList<Livre>) tmpLivresReadObj.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un problème est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
		} catch (Exception e) {
			System.out.println("Un problème est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {
			tmpLivresReadObj.close();
		}
	}
	
	public static void chargerLivresFichierTxt() throws Exception {
		try {
			String ligne;
			String elems[] = new String[6];
			listeLivres = new ArrayList<>();
			tmpLivresRead = new BufferedReader(new FileReader(FICHIER_LIVRES_TXT));
			ligne = tmpLivresRead.readLine();
			while (ligne != null) {
				elems = ligne.split(";");
				listeLivres.add(new Livre(Integer.parseInt(elems[0]), 
							              elems[1], Integer.parseInt(elems[2]),
							              Integer.parseInt(elems[3]),
							              Integer.parseInt(elems[4]), elems[5]));
				ligne = tmpLivresRead.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable. Vérifiez le chemin et nom du fichier.");
		} catch (IOException e) {
			System.out.println("Un problème est arrivé lors de la manipulation du fichier. Vérifiez vos données.");
		} catch (Exception e) {
			System.out.println("Un problème est arrivé lors du chargement du fichier. Contactez l'administrateur.");
		} finally {
			tmpLivresRead.close();
		}
	}

}
