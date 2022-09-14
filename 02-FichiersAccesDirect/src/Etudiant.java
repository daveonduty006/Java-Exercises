import java.io.*;
import java.util.*;
import javax.swing.*;

public class Etudiant {
	
	static int netud;
	static String nom;
	static float note;
	static RandomAccessFile sortie;
	static List<Integer> listeNumEtud;

	public static void main(String[] args) throws Exception {
		sortie= new RandomAccessFile("etud.bin","rw");
		listeNumEtud= new ArrayList<>();
		int choix=0;
		do{
			choix= Integer.parseInt(
				   JOptionPane.showInputDialog(
				   "1-Creer le fichier\n2-Ajout d'enregistrement\n"+
		           "3-Recherche d'un etudiant\n4-Affichage\n5-Quitter\n"+
				   "Entrez votre choix: "));
			switch(choix) {
				case 1:
					creerFichier(sortie);
					break;
				case 2:
					ajouter(sortie);
					break;
				case 3:
					rechercher(sortie);
					break;
				case 4:
					afficher(sortie);
					break; 
				case 5:
					sortie.close();
					System.exit(0); 
			}
		}while(true); 
	}
	
	public static void creerFichier(RandomAccessFile sortie) throws IOException {
		int netudb= 0;
		String nomb= " ";
		float noteb= 0.0f;	
		for(int i=0; i < 20; i++) {
			sortie.seek(i*52);
			listeNumEtud.add(100*i+100);
			sortie.writeInt(netudb);
			sortie.writeUTF(nomb);
			sortie.writeFloat(noteb);
		}
		sortie.close();
	}
	
	public static void ajouter(RandomAccessFile sortie) throws IOException {
		final int tnom= 20;
		int netud; 
		String nom; 
		float note;
		//
		do{
			netud= Integer.parseInt(
				   JOptionPane.showInputDialog(
			       "Entrer le numero de l'etudiant: "));
		}while(netud%100 != 0 || listeNumEtud.contains(netud) || netud==0);
	    listeNumEtud.add(netud);
	    //
	    nom= JOptionPane.showInputDialog(
	    	 "Entrer le nom de l'etudiant: ");
	    if(nom.length() < tnom) {
	    	while(nom.length() != tnom) {
	    		nom+=" ";
	   		}
	    }else if(nom.length() > tnom) {
	        nom= nom.substring(0,tnom);
	    }
	    //
	    note= Float.parseFloat(
	    	  JOptionPane.showInputDialog(
	          "Entrer la note de l'etudiant:"));
	    //
	    sortie.seek((netud/100-1)*52);
	    sortie.writeInt(netud);
	    sortie.writeUTF(nom);
	    sortie.writeFloat(note);
	}
	
	public static void rechercher(RandomAccessFile sortie) throws IOException {
	    final int tnom= 20;
	    int pos, netud, t_enreg; 
	    String nom; double note;
	    t_enreg= 4+(tnom*2)+8;
	    netud= Integer.parseInt(
	    	   JOptionPane.showInputDialog(
	    	   "Entrer le numero de l'etudiant a rechercher:"));
	    pos= ((netud/100)-1)*t_enreg;
	    sortie.seek(pos);
		netud= sortie.readInt();
		nom= sortie.readUTF();
		note= sortie.readFloat();
		JOptionPane.showMessageDialog(
		null,"Numero:"+netud+"\n"+"Nom:"+nom+"\n"+"Note"+note);
	}
	
	public static void afficher(RandomAccessFile sortie) throws IOException {
		int netud; 
		String nom; 
		double note;
		for(int i=0; i < listeNumEtud.size(); i++) {
			sortie.seek(i*52);
			netud =sortie.readInt();
			nom= sortie.readUTF();
			note= sortie.readFloat();
			JOptionPane.showMessageDialog(
			null,"Numero:"+netud+"\n"+"Nom:"+nom+"\n"+"Note"+note);
		} 
	}

}
