import java.io.*;
import java.util.*;

public class Exercice1 {
	
	final static int TAILLE_ENREG= 52;
	static int compteur;
	static int num;
	static String nom;
	static String prenom;
	static double salaire;
	static Map<Integer,Integer> MapEnregs;
	static RandomAccessFile donnee;
	static BufferedReader in;
	
	public static void main(String[] args) throws Exception {
	    in= new BufferedReader(new InputStreamReader(System.in));
	    chargerEnregs();
	    boolean sortie= false;
		int choix= 0;
        do{       
        	do{
                try{
                    System.out.println("Menu");
                    System.out.println("====\n");
                    System.out.println("1. Afficher les employés");
                    System.out.println("2. Ajouter un employé");                 
                    System.out.println("3. Modifier le salaire d'un employé");
                    System.out.println("4. Calculer le salaire moyen"); 
                    System.out.println("5. Rechercher un employé");  
                    System.out.println("6. Quitter");      
                    choix= Integer.parseInt(in.readLine());
                }catch(NumberFormatException e)
                    {}
            }while(choix < 1 || choix > 6);
		switch(choix) {
			case 1:
				//afficherEnregs();
				break;
			case 2:
				//ajouterEnreg();
				break;
			case 3:
				//modifierEnreg();
				break; 
			case 4:
				//calculerEnreg();
				break; 
			case 5:
				//rechercherEnreg();
				break; 
			case 6:
				donnee.close();
				System.exit(0); 
			}
		}while(sortie != true); 
        donnee.close();
        System.exit(0);  
	}
	
	public static void chargerEnregs() throws IOException {
		File fic= new File("employe.bin");
		if (fic.exists()) { 
			{}
		}else{
			donnee= new RandomAccessFile(fic, "rw");
			compteur= 0;
			num= 100;
			nom= "Tavares   ";
			prenom= "Antonio   ";
			salaire= 5500.00;
		    donnee.writeInt(num);
		    donnee.writeUTF(nom);
		    donnee.writeUTF(prenom);
		    donnee.writeDouble(salaire);
			MapEnregs= new TreeMap<>();
			MapEnregs.put(num, compteur*TAILLE_ENREG);
		}
	}
	
	
}


/*    
    do{       
    	do{
            try{
                System.out.println("Menu");
                System.out.println("====\n");
                System.out.println("1. Afficher les donnees");
                System.out.println("2. Calculer le salaire moyens");
                System.out.println("3. Ajouter un employe");
                System.out.println("4. Modifier le salaire d'un employe");
                System.out.println("5. Quitter");      
                choix= Integer.parseInt(in.readLine());
            }catch(NumberFormatException e)
                {}
        }while(choix < 1 || choix > 5);
    	
    	switch(choix) {
            case 1:
                System.out.println();                     
                donnee.seek(0);                    
                for(int i=0; i < compteur; i++) {
                    try{
                    	System.out.print(donnee.readInt());
                        System.out.print(donnee.readChar());
                        System.out.print(donnee.readUTF());
                        System.out.print(donnee.readChar());
                        System.out.print(donnee.readUTF());
                        System.out.print(donnee.readChar());
                        System.out.print(donnee.readDouble());
                        System.out.print(donnee.readChar());                   
                    }catch(EOFException e)
                        {}
                }
                System.out.println();             
                break;
                
            case 2:
                System.out.println();  
                moyenne= 0;   
                donnee.seek(0);                    
                for(int i=0; i < compteur; i++) {    
                    try{
                    	donnee.readInt();
                        donnee.readChar();
                        donnee.readUTF();
                        donnee.readChar();
                        donnee.readUTF();
                        donnee.readChar();
                        moyenne += donnee.readDouble();
                        donnee.readChar();                 
                    }catch(EOFException e)
                        {}
                }
                System.out.println("La moyenne des salaires est de : "+(moyenne/compteur));                                        
                System.out.println();             
                break;

            case 3:
                System.out.println();                          
                donnee.seek(donnee.length());
                compteur+=1;                  
                try{
                	donnee.writeInt(compteur);
                    donnee.writeChar(' ');
                    System.out.println("Entrez le nom du nouvel employe");
                    donnee.writeUTF(in.readLine());
                    donnee.writeChar(' ');
                    System.out.println("Entrez le prenom du nouvel employe");
                    donnee.writeUTF(in.readLine());
                    donnee.writeChar(' ');
                    System.out.println("Entrez le salaire du nouvel employe");
                    donnee.writeDouble(Double.parseDouble(in.readLine()));
                    donnee.writeChar('\n');                    
                }catch(EOFException e)
                    {}                     
                System.out.println();             
                break;

            case 4 :
                System.out.println();  
                donnee.seek(0);
                do{
                    System.out.println("Entrez le numéro de l'employé a qui vous voulez"+
                                       " change le salaire");
                    numero= Integer.parseInt(in.readLine());
                }while(numero < 0 || numero > compteur);                 
                System.out.println("Entrez le montant du nouveau salaire");
                newSalaire= Double.parseDouble(in.readLine());                   
                int numDonnee= 0;                   
                try{
                    for(int i=0; i < compteur; i++) {
                        numDonnee= donnee.readInt();                                    
                        if(numero == numDonnee) {
                            donnee.readChar();
                            donnee.readUTF();
                            donnee.readChar();
                            donnee.readUTF();
                            donnee.readChar();
                            donnee.writeDouble(newSalaire);
                            donnee.readChar(); 
                        }else{
                            donnee.readChar();
                            donnee.readUTF();
                            donnee.readChar();
                            donnee.readUTF();
                            donnee.readChar();
                            donnee.readDouble();
                            donnee.readChar();                             
                        }             
                    }
                }catch(EOFException e)
                    {}                             
                System.out.println();              
                break;
            
            case 5:
                System.out.println();  
                sortie= true;              
                break; 
    	}
    }while(sortie != true);

    donnee.close();
    System.exit(0);    
*/