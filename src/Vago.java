import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;


public class Vago {
	private String idVago;
	private int nPlaces;
//	private BigDecimal preu;
	private boolean assignatVago;
	private Seient[][] seients;
	//Constant
	final int nColumnes = 4;

	public enum lletraSeient {A,B,C,D}


	//*********************************************************
	//Constructor 1
	public Vago(String idVago, int nPlaces) {

		this.idVago = idVago;
		this.nPlaces = nPlaces;
//		this.preu = preu;
		
		int nFiles = nPlaces/nColumnes;
		this.seients = new Seient[nFiles][nColumnes];
		for (int i=0; i < nFiles; i++){
			int j=0;
			for (lletraSeient lletra: lletraSeient.values()){
				this.seients[i][j] = new Seient(String.format("%02d" ,i+1)+lletra);
				j++;
			}
		}
		this.assignatVago=false;
	}

	//*********************************************************
	//Constructor INTERACTIU
	
	public Vago() {
		boolean validatIdVago = false;

		System.out.println("Creació del VAGÓ\n>>>>>>>>>>>>>>>>>>> ");

		do{
			idVago = Validacio.validaCadena("\tIndentificador del VAGÓ? ");
			validatIdVago = Renfe.isIdVagoValid(idVago);
			if (!validatIdVago)
				System.out.println("\tERROR: Identificador de VAGÓ existent");
		} while (!validatIdVago);

		nPlaces = Validacio.validaSencer("\n\tNum places del VAGÓ? ",100);

		int nFiles = nPlaces/nColumnes;
		this.seients = new Seient[nFiles][nColumnes];
		for (int i=0; i < nFiles; i++){
			int j=0;
			for (lletraSeient lletra: lletraSeient.values()){
				this.seients[i][j] = new Seient(String.format("%02d" ,i+1)+lletra);
				j++;
			}
		}
		this.assignatVago=false;

		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	//*********************************************************
	//Modifica el VAGO

	public void modificaVago() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>");
		System.out.println("\tModificació del VAGO\n>>>>>>>>>>>>>>>>>>>>>");
		System.err.println("\tPrem tecla INTRO per matenir informació anterior ");

		System.out.println("\tIndentificador del VAGÓ: " +this.getIdVago());
		this.setnPlaces(Validacio.validaSencerDefecte("\tNum de places del VAGÓ? ",100, this.getnPlaces()));

		boolean reinicia = Validacio.validaBoolea("\tReiniciar assignació seients? (S/N) ");

		if(reinicia) {
			//Creacio SEIENTS
			int nFiles = nPlaces/nColumnes;
			for (int i=0; i < nFiles; i++){
				int j=0;
				for (lletraSeient lletra: lletraSeient.values()){
					this.seients[i][j] = new Seient(String.format("%02d" ,i+1)+lletra);
					j++;
				}
			}
		}else{//resposta = N. Deixa coses com estan
		}

		this.setAssignaVago(Validacio.validaBooleaDefecte("\tVAGÓ assignat a alguna CLASSE? ",this.isAssignatVago()));
		System.out.println("\t=======================");
		System.out.println(this);

		
	}

	//*********************************************************
	//Esborra el VAGO

	public void esborraVago() {
		System.out.println("VAGÓ esborrat!");
	}

	//*********************************************************
	//Mostra la distribució de SEIENTS al VAGO
	public void mapaVago(){
		System.out.println("\n\t ====================  MAPA VAGO  ====================");
		//CAPÇALERA del VAGO
		System.out.print("\t Seient->  ");
		for (lletraSeient lletra: lletraSeient.values())
			System.out.print(lletra +"  ");

		//COS de la SALA
		int nFiles = nPlaces/nColumnes;
		System.out.println();
		for (int i=0; i < nFiles; i++){
			System.out.print("\t Fila "+String.format("%02d" ,i+1)+": ");
			for (int j=0; j < nColumnes; j++){
				System.out.print(" "+this.seients[i][j].iconaSeient()+" ");
			}//endfor	
			System.out.println();
		}//endfor
		System.out.println("\n\t SIMBOLOGIA: X=ocupat; O=lliure; ?=reservat\n\n");
		System.out.println("\n\t ========================================================");
	}

	//*********************************************************
	//Retorna DATA en format espanyol
	
	public String mostraDataFormatada(Calendar diaHora){
		int day = diaHora.get(Calendar.DAY_OF_MONTH);
		int month = diaHora.get(Calendar.MONTH);
		int year = diaHora.get(Calendar.YEAR);
		int hour = diaHora.get(Calendar.HOUR_OF_DAY);
		int minute = diaHora.get(Calendar.MINUTE);

		 return day+"/"+month+"/"+year+" "+hour+":"+minute;
	}


	//*********************************************************
	//Mostra BITLLET de compra del TRAJECTE
	public void imprimirBitllet(Seient s, Vago v, Classe c, Tren t, Trajecte tra){
		System.out.println("Imprimint el seu Bitllet de tren...");
		System.out.println("******************************************");
		System.out.println("* ********** BITLLET TREN ***********++* *");
		System.out.println("* Origen TRAJECTE: "+ tra.getOrigen() +" *");
		System.out.println("* Destí TRAJECTE: "+ tra.getDesti() +" *");
		System.out.println("* Dia i hora TRAJECTE: "+ mostraDataFormatada(tra.getDiaHora()) +" *");
		System.out.println("* Duració TRAJECTE: "+ tra.getDuracio() +" min *");
		System.out.println("* ------- DADES DEL SEIENT ------------ *");
		System.out.println("* TREN: "+ t.getIdTren() +" *");
		System.out.println("* CLASSE: "+ c.getNomClasse() +" *");
		System.out.println("* VAGO: "+ v.getIdVago() +" *");
		System.out.println("* Seient: "+(s.getIdSeient())+" *");
		System.out.println("* Preu: "+ c.getPreu()+" € *");
		System.out.println("*****************************************");
	}


	//*********************************************************
	//Metode ToString
	@Override
	public String toString() {
		return "VAGO [idVago=" + idVago 
				+ "\n\t nPlaces=" + nPlaces 
				+ "\n\t assignatVago="+ assignatVago  
				+ "\n\t seients="+ getSeients() +"]";
	}


	//GETTERS & SETTERS
	//*********************************************************
	
	public String getIdVago() {
		return idVago;
	}

	public void setIdVago(String idVago) {
		this.idVago = idVago;
	}

	public int getnPlaces() {
		return nPlaces;
	}

	public void setnPlaces(int nPlaces) {
		this.nPlaces = nPlaces;
	}

	public int getnColumnes() {
		return nColumnes;
	}

	public boolean isAssignatVago() {
		return assignatVago;
	}

	public void setAssignaVago(boolean assignatVago) {
		this.assignatVago = assignatVago;
	}

	public  Seient[][] getSeients() {
		return seients;
	}


	public  void setSeients(Seient[][] seients) {
		this.seients = seients;
	}


}
