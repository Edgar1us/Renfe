import java.math.BigDecimal;
import java.util.ArrayList;

public class Classe {

	private String idClasse;
	private TIPUSCLASSE nomClasse;
	private String descClasse;
	private BigDecimal preu;
	private boolean assignadaClasse;
	private ArrayList<Vago> vagonsClasse;

	public enum TIPUSCLASSE {Turista, TuristaPlus, Preferent}


	//*********************************************************	
	//Constructor 1
	public Classe(String idClasse, Classe.TIPUSCLASSE nomClasse, String descClasse, int preu) {
		this.idClasse = idClasse;
		this.nomClasse = nomClasse;
		this.descClasse = descClasse;
		this.preu = new BigDecimal(preu);
		this.assignadaClasse = false;
		this.vagonsClasse = new ArrayList <Vago>();
	}

	//*********************************************************
	//Constructor INTERACTIU
	public Classe() {

		int numeroClasse = 0;

		System.out.println("Creació de la CLASSE\n>>>>>>>>>>>>>>>>>>>>>");

		this.idClasse = Validacio.validaCadena("\tIdentificador de la Classe? ");

		numeroClasse = Validacio.validaSencer("\n\tClasse? (1: Turista, 2: TuristaPlus, 3: Preferent)", 3);
		setNomClasse(numeroClasse);

		this.descClasse = Validacio.validaCadena("\tDescripció de la Classe? ");

		this.preu = Validacio.validaMoneda("Preu del seient de la Classe? ");

		this.assignadaClasse = false;

		this.vagonsClasse = new ArrayList <Vago>();

		Vago vago = afegirVago(" Afegir VAGONS a la nova Classe? [S/N]");
		if (vago != null)
			this.vagonsClasse.add(vago);

		System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<<< ");
	}

	
	
	//*********************************************************
	public void modificaClasse() {

		System.out.println("\tModificació de la CLASSE\nPrem tecla INTRO per matenir informació anterior ");

		System.out.println("\tIndentificador de la CLASSE: " +this.getIdClasse());

		this.setNomClasse(Validacio.validaSencerDefecte("\n\tTipus de la CLASSE? (1: Turista, 2: TuristaPlus, 3: Preferent)", 3, getNomClassePosition()));

		this.setDescClasse(Validacio.validaCadenaDefecte("\tDescripció de la CLASSE? ",this.getDescClasse()));

		this.setPreu(Validacio.validaMonedaDefecte("\\tPreu del seient de la CLASSE? ", this.getPreu()));

		boolean reinicia = Validacio.validaBoolea("\tReiniciar els VAGONS de la CLASSE? (S/N) ");

		if(reinicia) {
			//Creacio VAGONS
			this.setVagonsClasse(new ArrayList <Vago>());
		}else{//resposta = N. Deixa coses com estan
		}

		this.setAssignadaClasse(Validacio.validaBooleaDefecte("\tVAGÓ assignat a alguna CLASSE? ",this.isAssignadaClasse()));

		System.out.println("\n<<<<<<<<<<<<<<<<<<<<< ");
		System.out.println(this);
	}

	

	//*********************************************************
	public void esborraClasse() {
		System.out.println("Classe esborrada!");
	}


	
	//*********************************************************
	//Afegeix un nou VAGO a la CLASSE
	//Retorna el VAGO afegit
	public Vago afegirVago(String message) {

		if (Validacio.validaBoolea(message)) {

			if (Renfe.numVagonsLliures() == 0) {//No hi ha vagons lliures
				System.out.println("\tNo hi ha VAGONS disponibles per assignar ");
				return null;
			}else {  	//Hi ha vagons lliures
				Renfe.llistaVagonsLliures();
				int vagoSeleccionat = Validacio.validaSencer(" Selecciona un VAGÓ lliure: ",Renfe.vagons.size());
				Vago vago = Renfe.vagons.get(vagoSeleccionat-1);
				vago.setAssignaVago(true);
				return vago;
			}
		}
		else
			return null;


	}

	//*********************************************************
	/*	
	public Vago llevarVago(String message) {

		if (Validacio.validaBoolea(message)) {
			USB 		if (Renfe.numVagonsLliures() == 0) {//No hi ha vagons lliures
				System.out.println("\tNo hi ha VAGONS disponibles per assignar ");
				return null;
			}else {  	//Hi ha vagons lliures
				Renfe.llistaVagonsLliures();
				int vagoSeleccionat = Validacio.validaSencer(" Selecciona un VAGÓ lliure: ",Renfe.vagons.size());
				Vago vago = Renfe.vagons.get(vagoSeleccionat-1);
				vago.setAssignaVago(true);
				return vago;
			}
		}
		else
			return null;		
	}
	 */

	//*********************************************************

	//---------------------------------
	//Llista TOTS els VAGONS de la CLASSE
	public int llistarVagonsClasse() {

		for (int i = 0; i < vagonsClasse.size(); i++) {
			System.out.println(" " + (i+1) + ": " + vagonsClasse.get(i));
		}
		return vagonsClasse.size();
	}

	@Override
	public String toString() {
		return "CLASSE [\n\t idClasse=" + this.idClasse
				+ "\n\t nomClasse=" + this.nomClasse 
				+ ",\n\t  descClasse=" + this.descClasse
				+ "\n\t preu=" + this.preu 
				+ ",\n\t vagons=" + this.vagonsClasse +"]";
	}


	//GETTERS & SETTERS
	//*********************************************************

	public String getIdClasse() {
		return idClasse;
	}

	public void setIdClasse(String idClasse) {
		this.idClasse = idClasse;
	}

	public TIPUSCLASSE getNomClasse() {
		return nomClasse;
	}

	public int getNomClassePosition() {
		return nomClasse.ordinal();
	}


	public void setNomClasse(int nomClasse) {
		switch (nomClasse) {

		case 1: 
			this.nomClasse = TIPUSCLASSE.Turista;
			break;
		case 2: 
			this.nomClasse = TIPUSCLASSE.TuristaPlus;
			break;				
		default: 
			this.nomClasse = TIPUSCLASSE.Turista;
			break;
		}
	}



	public String getDescClasse() {
		return descClasse;
	}



	public void setDescClasse(String descClasse) {
		this.descClasse = descClasse;
	}


	public BigDecimal getPreu() {
		return preu;
	}

	public void setPreu(BigDecimal preu) {
		this.preu = preu;
	}

	public boolean isAssignadaClasse() {
		return assignadaClasse;
	}



	public void setAssignadaClasse(boolean assignadaClasse) {
		this.assignadaClasse = assignadaClasse;
	}



	public ArrayList<Vago> getVagonsClasse() {
		return vagonsClasse;
	}



	public void setVagonsClasse(ArrayList<Vago> vagonsClasse) {
		this.vagonsClasse = vagonsClasse;
	}








}