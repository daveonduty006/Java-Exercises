
public class Cours {

	private String sigle; // sigle du cours
	private int eval;     // evaluation du cours

	public Cours(String sigle, int eval) { //constructeur par parametres
		this.sigle= sigle;
		this.eval= eval;
	}
	
	public String getSigle() {
		return this.sigle;
	}
	
	public int getEval() {
		return this.eval;
	}
	
	public String appreciation() {
		String message;
		switch(this.getEval()) {
			case 1:
				message = "pas du tout";
				break;
			case 2: 
				message = "un peu";
				break;	
			case 3: 
				message = "beaucoup";
				break;	
			case 4: 
				message = "a la folie";
				break;	
			default:
				message = "{valeur de Eval invalide}";
		}
		return message;
	}

}
