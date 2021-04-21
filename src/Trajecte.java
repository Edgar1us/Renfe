import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Trajecte {

	private String idTrajecte;
	private String origen;
	private String desti;
	private Calendar diaHora;
	private int duracio;
	private Tren tren;
	private ArrayList<Classe> classesTrajecte;

	// ---------------------------------
	//Constructor 1
	public Trajecte(String idTrajecte, String origen, String desti, Calendar diaHora, int temps, Tren tren) {
		this.idTrajecte = idTrajecte;
		this.origen = origen;
		this.desti = desti;
		this.diaHora = diaHora;
		this.duracio = temps;
		this.tren = tren;
		this.classesTrajecte = new ArrayList<Classe>();
	}

	// ---------------------------------
	//Constructor 2
	public Trajecte(String idTrajecte, String origen, String desti, Calendar diaHora, int temps, Tren tren,
			ArrayList<Classe> classesTrajecte) {
		super();
		this.idTrajecte = idTrajecte;
		this.origen = origen;
		this.desti = desti;
		this.diaHora = diaHora;
		this.duracio = temps;
		this.tren = tren;
		this.classesTrajecte = classesTrajecte;
	}
	// ---------------------------------
	//Constructor INTERACTIU
	public Trajecte() {

		Vago se = null;

		System.out.println("Creació del TRAJECTE\n>>>>>>>>>>>>>>>>>>>>>> ");

		this.idTrajecte = Validacio.validaCadena("\tIdentificador del Trajecte? ");
		this.origen = Validacio.validaCadena("\tCiutat ORIGEN del Trajecte? ");
		this.desti = Validacio.validaCadena("\tCiutat ORIGEN del Trajecte? "); 
		this.diaHora = Validacio.validaData("\tDia i hora del Trajecte? (dd/mm/aaaa)");
		this.duracio = Validacio.validaSencer("\tDuracio del Trajecte?(min) ",3600*24);

		if (Renfe.trens.size()>0) {
			int trenSeleccionat = Validacio.validaSencer("Selecciona un TREN: ",Renfe.llistaTrens());
			this.tren = Renfe.trens.get(trenSeleccionat-1);
		}
		else {
			this.tren = null;
		}


		this.classesTrajecte = new ArrayList<Classe>();

		//Si es vol afegir una nova CLASSE...
		Classe classe = afegirClasse(" Afegir CLASSES al nou Trajecte? [S/N]");
		if (classe != null)
			this.classesTrajecte.add(classe);

		/*		
		 */		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// ---------------------------------
	//Metode que pregunta si es vol afegir una nova CLASSE i en funció de la resposta, afegeix la CLASSE o no.
	//Si s'afegeix la CLASSE, s'ha d'indicar que ja no està disponible per a altre trajecte. Retorna la CLASSE afegida
	public Classe afegirClasse(String message) {

		if (Validacio.validaBoolea(message)) {

			if (Renfe.llistaClassesLliures() == 0) {//No hi ha CLASSES lliures
				System.out.println("\tNo hi ha CLASSES disponibles per assignar ");
				return null;
			}else {  	//Hi ha vagons lliures
				int classeSeleccionada = Validacio.validaSencer(" Selecciona una CLASSE lliure: ",Renfe.classes.size());
				Classe classe = Renfe.classes.get(classeSeleccionada-1);
				classe.setAssignadaClasse(true);
				return classe;
			}
		}
		else
			return null;
	}

	// ---------------------------------
	//Metode que pregunta si es vol modificar una CLASSE i en funció de la resposta, modifica la CLASSE o no.
	//Retorna la CLASSE modificada
	public Classe modificarClasse(String message) {

		if (Validacio.validaBoolea(message)) {

			if (getClassesTrajecte().size() == 0) {//No hi ha CLASSES assignats
				System.out.println("\tNo hi ha CLASSES per desassignar ");
				return null;
			}
			else {//Hi ha vagons assignats
				int i=0;
				for(Classe classe: getClassesTrajecte()) {
					System.out.println("\n "+(i+1)+":\t "+classe.toString());
					System.out.println("\t---");
				}

				int classeSeleccionada = Validacio.validaSencer(" Selecciona una CLASSE a modificar: ",getClassesTrajecte().size());
				Classe classe = getClassesTrajecte().get(classeSeleccionada-1);

				//Modifiquem la classe 
				classe.modificaClasse();
				return classe;
			}
		}
		else
			return null;
	}


	// ---------------------------------
	//Metode que pregunta si es vol eliminar una CLASSE i en funció de la resposta, lleva la CLASSE o no.
	//Si es lleva la CLASSE, s'ha d'indicar que està disponible per a altre trajecte. Retorna la CLASSE llevada
	public Classe llevarClasse(String message) {

		if (Validacio.validaBoolea(message)) {

			if (getClassesTrajecte().size() == 0) {//No hi ha CLASSES assignats
				System.out.println("\tNo hi ha CLASSES per desassignar ");
				return null;
			}
			else {//Hi ha vagons assignats
				int i=0;
				for(Classe classe: getClassesTrajecte()) {
					System.out.println("\n "+(i+1)+":\t "+classe.toString());
					System.out.println("\t---");
				}

				int classeSeleccionada = Validacio.validaSencer(" Selecciona una CLASSE a desassignar: ",getClassesTrajecte().size());
				Classe classe = getClassesTrajecte().get(classeSeleccionada-1);
				classe.setAssignadaClasse(false);
				//Eliminem la classe de la llista de classes assignades
				getClassesTrajecte().remove(classe);
				return classe;
			}
		}
		else
			return null;
	}


	// ---------------------------------
	//modificacio Trajecte
	public void modificaTrajecte() {
		boolean canviaTren = false;
		boolean canviaClasses = false;


		System.out.println("\tModificació del TRAJECTE\nPrem tecla INTRO per matenir informació anterior ");

		System.out.println("\tIndentificador del TRAJECTE: " +this.getIdTrajecte());

		this.setOrigen(Validacio.validaCadenaDefecte("\tCiutat ORIGEN del TRAJECTE? ",this.getOrigen()));
		this.setDesti(Validacio.validaCadenaDefecte("\tCiutat DESTI del TRAJECTE? ",this.getDesti()));

		this.setDiaHora(Validacio.validaDataDefecte("\tDATA i HORA del TRAJECTE? ",this.getDiaHora()));

		this.setDuracio(Validacio.validaSencerDefecte("\tNacionalitat de la Pelicula? ", 1000, this.getDuracio()));

		canviaTren = Validacio.validaBoolea("\tTren actual: "+ this.getTren().toString() + "\n\t Vols canviar de TREN?");
		if(canviaTren) {
			//mostra la llista de Trens
			int numTrens = Renfe.llistaTrens();
			int nouTren = Validacio.validaSencer("\tNou TREN del TRAJECTE? ",  numTrens);
			this.setTren(Renfe.trens.get(nouTren-1));

		}

		canviaClasses = Validacio.validaBoolea("\t Vols canviar les CLASSES del TRAJECTE?");
		if(canviaClasses) {
			modificarClasse("\t Vols modificar la CLASSE del TRAJECTE?");
			llevarClasse("\t Vols esborrar la CLASSE del TRAJECTE?");
			afegirClasse("\t Vols crear una CLASSE del TRAJECTE?");
		}
	}


	//---------------------------------
	//Esborra TRAJECTE
	public void esborraTrajecte() {
		System.out.println("TRAJECTE esborrat!");
	}


	//*********************************************************
	//Mostra DATA en format espanyol
	public void mostraDataFormatada(){
		int day = this.diaHora.get(Calendar.DAY_OF_MONTH);
		int month = this.diaHora.get(Calendar.MONTH)+1;
		int year = this.diaHora.get(Calendar.YEAR);
		int hour = this.diaHora.get(Calendar.HOUR_OF_DAY);
		int minute = this.diaHora.get(Calendar.MINUTE);
		String minuteStr = String.valueOf(minute);

		if (minuteStr.length()==1) 
			System.out.print(day+"/"+month+"/"+year+" "+hour+":"+"0".concat(minuteStr) + "h ");
		else 
			System.out.print(day+"/"+month+"/"+year+" "+hour+":"+minuteStr + "h ");

	}

	// ---------------------------------
	//metode ToString
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy hh:mm:ss");
		return "TRAJECTE [\n\t idTrajecte=" + idTrajecte 
				+ "\n\t origen=" + origen
				+ "\n\t desti=" + desti 
				+ "\n\t diaHora=" + sdf.format(diaHora.getTime()) 
				+ "\n\t duracio=" + duracio 
				+ "\n\t tren="	+ tren 
				+ "\n\t classesTrajecte={" + classesTrajecte + "}]";
	}


	//---------------------------------
	//Llista TOTES les CLASSES del TRAJECTE
	public int llistarClassesTrajecte() {

		for (int i = 0; i < classesTrajecte.size(); i++) {
			System.out.println(" " + (i+1) + ": " + classesTrajecte.get(i));
		}
		return classesTrajecte.size();
	}




	//---------------------------------

	//---------------------------------

	//GETTERS & SETTERS

	public String getIdTrajecte() {
		return idTrajecte;
	}

	public void setIdTrajecte(String idTrajecte) {
		this.idTrajecte = idTrajecte;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDesti() {
		return desti;
	}

	public void setDesti(String desti) {
		this.desti = desti;
	}

	public Calendar getDiaHora() {
		return diaHora;
	}

	public void setDiaHora(Calendar diaHora) {
		this.diaHora = diaHora;
	}

	public int getDuracio() {
		return duracio;
	}

	public void setDuracio(int duracio) {
		this.duracio = duracio;
	}

	public Tren getTren() {
		return tren;
	}

	public void setTren(Tren tren) {
		this.tren = tren;
	}

	public ArrayList<Classe> getClassesTrajecte() {
		return classesTrajecte;
	}

	public void setClassesTrajecte(ArrayList<Classe> classesTrajecte) {
		this.classesTrajecte = classesTrajecte;
	}


}
