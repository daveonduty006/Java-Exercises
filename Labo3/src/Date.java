import java.io.*;
import java.time.LocalDate;

public class Date implements Serializable {

	private static final long serialVersionUID = -5841571703569308524L;

	// attributs de classe
    public static String tabMois[]= 
    	{null, "Janvier", "F�vrier", "Mars", "Avril", "Mai", "Juin", 
         "Juillet", "Ao�t", "Septembre", "Octobre", "Novembre", "D�cembre"};
    
    public static LocalDate dateActuelle= LocalDate.now();
    
	// attributs d'instance
    private int jour, mois, an; 
    
    // constructeurs 
    public Date() {
        jour=mois= 1;
        an= 2000;
    }

    public Date(int unJour, int unMois, int uneAnnee) {
        this.jour= unJour;
        this.mois= unMois;
        this.an= uneAnnee;
    }

    // accesseurs
    public int getJour() {
        return this.jour;
    }

    public int getMois() {
        return this.mois;
    }

    public int getAnnee() {
        return this.an;
    }

    // mutateurs
    public void setJour(int jour) {
        int nbJours= determinerNbJoursMois(this.mois, this.an);
        if (jour > nbJours || jour < 1) {
            System.out.println("Jour invalide pour le mois de "+
                               tabMois[this.mois].toLowerCase());
        } else {
            this.jour= jour;
        }
    }

    public void setMois(int mois) {
        int nbJours;
        if (mois < 1 || mois > 12) {
            System.out.println("Mois "+mois+" n'est pas un mois valide [1-12]");
        } else {
            nbJours= determinerNbJoursMois(mois, this.an);
            if (this.jour > nbJours) {
                System.out.println("Mois "+tabMois[mois].toLowerCase()+
                                   " n'est pas un mois valide pour le jour actuel du vol qui est "+
                		           this.jour);
            } else {
                this.mois= mois;
            }
        }
    }

    public void setAn(int an) {
        if (an < 0) {
            System.out.println("Ann�e "+an+" ne peut pas �tre une ann�e n�gative");
        } else {
            this.an= an;
        }
        // !!! Ce code est bon pour le labo sur les Vols mais pas bon en g�n�ral !!!
        
        /* BON CODE EN G�N�RAL
         * int anneeActuelle= dateActuelle.getYear();
         * if (an < anneeActuelle) {
         *     System.out.println("Ann�e "+an+
         *                        " ne peut pas �tre inf�rieure � l'ann�e actuelle, soit "+
         *                        anneActuelle);
         * } else {
         *     this.an= an;}
         */
    }
    
    // autres m�thodes 
    // M�thodes pour valider la date avant de l'envoi au constructeur 
    public static String validerDate(int jour, int mois, int an, boolean etat[]) {
        String message= "";
        int nbJours= 0;
        // Valide le mois
        if (mois < 1 || mois > 12) {
            etat[1]= false;
            message+= "Mois "+mois+" n'est pas un mois valide [1-12]\n";
        } else {
            etat[1]= true;
        }
        // Valide le jour
        if (etat[1]) {
            nbJours= determinerNbJoursMois(mois, an);
            if (jour > nbJours || jour <= 0) {
                etat[0]= false;
                message+= "Jour invalide pour le mois de "+tabMois[mois].toLowerCase()+"\n";
            } else {
                etat[0]= true;
            }
        } else {
            message+= "Impossible de valider le jour puisque votre mois est invalide";
        }
        // Valide l'ann�e
        int anneeActuelle= dateActuelle.getYear();
        if (an < anneeActuelle) {
            etat[2]= false;
            message+= "Ann�e "+an+" ne peut pas �tre inf�rieure � l'ann�e actuelle, soit "+
                      anneeActuelle+"\n";
        } else {
            etat[2]= true;
        }
        return message;
    }
    
    public static int determinerNbJoursMois(int mois, int an) {
        int nbJours;
        int tabJrMois[]= {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (mois == 2 && estBissextile(an)) {
            nbJours= 29;
        } else {
            nbJours= tabJrMois[mois];
        }
        return nbJours;
    }

    public static boolean estBissextile(int an) {
        return (an % 4 == 0 && an % 100 != 0) || (an % 400 == 0);
    }
    
    // m�thode pour �criture format�e dans le fichier
    public String ecritureFichier() {
        String leJour, leMois;
        leJour= Utilitaires.ajouterCaractereGauche('0', 2, this.jour + "");
        leMois= Utilitaires.ajouterCaractereGauche('0', 2, this.mois + "");
        return leJour+" "+leMois+" "+this.an;
    }
    
    @Override
    public String toString() {
        String leJour, leMois;
        leJour= Utilitaires.ajouterCaractereGauche('0', 2, this.jour + "");
        leMois= Utilitaires.ajouterCaractereGauche('0', 2, this.mois + "");
        return leJour+"/"+leMois+"/"+this.an;
    }
    
}