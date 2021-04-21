import java.util.ArrayList;
import java.util.Scanner;

public class Tren {

	private String idTren;
	private int capacitat;


	//Constructor 1
	public Tren(String idTren,int capacitat) {
		this.idTren = idTren;
		this.capacitat = capacitat;

	}


	//*********************************************************
	//Constructor INTERACTIU
	public Tren() {

		boolean validatIdTren = false;

		System.out.println("Creació del TREN\n>>>>>>>>>>>>>>>>>>> ");

		do{
			idTren = Validacio.validaCadena("\tIndentificador del Tren? ");
			validatIdTren = Renfe.isIdTrenValid(idTren);
			if (!validatIdTren)
				System.out.println("\tERROR: Identificador de TREN existent");
		} while (!validatIdTren);

		this.capacitat = Validacio.validaSencer("\n\tNum capacitat del Tren? ", 1000);



		System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<<< ");
	}

	//*********************************************************
	public void modificaTren() {

		System.out.println("\tModificació de la SALA\nPrem tecla INTRO per matenir informació anterior ");

		System.out.println("\tIndentificador del TREN: " +this.getIdTren());

		this.setCapacitat(Validacio.validaSencerDefecte("\tCapacitat del TREN? ",400, this.getCapacitat()));

		System.out.println("\n<<<<<<<<<<<<<<<<<<<<< ");
		System.out.println(this);
	}


	//*********************************************************
	public void esborraTren() {
		System.out.println("TREN esborrat!");
	}


	//*********************************************************
	//metode ToString
	@Override
	public String toString() {
		return "TREN [\n\t idTren="	+ idTren 
				
				+  ",\n\t  capacitat=" + capacitat
						+ "\n\t}]";
	}


	//*********************************************************


	//GETTERS & SETTERS
	//*********************************************************

	public String getIdTren() {
		return idTren;
	}

	public void setIdTren(String idTren) {
		this.idTren = idTren;
	}

	public int getCapacitat() {
		return capacitat;
	}

	public void setCapacitat(int capacitat) {
		this.capacitat = capacitat;
	}

	//*********************************************************


}
