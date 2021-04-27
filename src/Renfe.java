import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;



public class Renfe {
static Scanner teclado = new Scanner(System.in);

	static ArrayList<Trajecte> trajectes = new ArrayList <Trajecte>();
	static ArrayList<Tren> trens = new ArrayList <Tren>();
	static ArrayList<Classe> classes = new ArrayList <Classe>();
	static ArrayList<Vago> vagons = new ArrayList <Vago>();


	public static void main(String[] args) throws InterruptedException {

		int opcio=-1;

		//carregaDades Inicials
		carregaDadesInicials();

		do{
			opcio = menu();

			switch(opcio){

			case 1: //Crear VAGO
				System.out.println("Creant VAGO...");
				Vago va = new Vago("vago1",50);
				System.out.println(va);
				vagons.add(va);
				System.out.println("\n\n");
				break;
				//******** 

			case 2: //Modifica VAGO
				System.out.println("Modificant VAGO...");

				if( vagons.size()==0) //NO hi ha VAGONS
					System.out.println("ERROR Modifica VAGO: No hi ha Vagons a modificar");
				else{ //Hi ha VAGONS creats
					int numVagons = llistaVagons();
					int vago = Validacio.validaSencer("\t Tria VAGO a modificar [0 = Anula acció]:", numVagons);
					if(vago == 0)
						System.out.println("Anulant acció");
					else
						vagons.get(vago-1).modificaVago();
				}
				System.out.println("\n\n");
				break;
				//********

			case 3: //Esborrar VAGO
				System.out.println("Esborrant VAGO...");
				if( vagons.size()==0) //NO hi ha VAGONS
					System.out.println("ERROR Esborra VAGO: No hi ha Vagons a esborrar");
				else{ ///Hi ha VAGONS creats
					int numVagons = llistaVagons();
					int vago = Validacio.validaSencer("\t Tria VAGO a modificar [0 = Anula acció]:", numVagons);
					if(vago == 0)
						System.out.println("Anulant acció");
					else {
						vagons.remove(vago-1);
					}

				}
				System.out.println("\n\n");
				break;
				//******** 

			case 4: //Crear CLASSE
				System.out.println("Creant CLASSE...");
				Classe classe = new Classe();
				System.out.println(classe);
				classes.add(classe);
				System.out.println("\n\n");
				break;
				//********

			case 5: //Modifica CLASSE
				System.out.println("Modificant CLASSE...");

				if(classes.size()==0) //NO hi ha CLASSES
					System.out.println("ERROR Modifica CLASSE: No hi ha CLASSES a modificar");
				else{ //Hi ha CLASSES creades
					int numClasses = llistaClasses();
					int numClasse = Validacio.validaSencer("\t Tria CLASSE a modificar:",numClasses);
					if(numClasse == 0)
						System.out.println("Anulant acció");
					else
						classes.get(numClasse-1).modificaClasse();
				}
				System.out.println("\n\n");
				break;
				//********

			case 6: //Esborrar CLASSE
				System.out.println("Esborrant CLASSE...");

				if(classes.size()==0) //NO hi ha CLASSES
					System.out.println("ERROR Esborra CLASSE: No hi ha CLASSES a esborrar");
				else{ //Hi ha CLASSES creades
					int numClasses = llistaClasses();
					int numClasse = Validacio.validaSencer("\t Tria CLASSE a esborrar:",numClasses);
					if(numClasse == 0)
						System.out.println("Anulant acció");
					else
						classes.get(numClasse-1).esborraClasse();
				}
				System.out.println("\n\n");
				break;
				//********

			case 7:
				//Crear TREN
				System.out.println("Creant TREN...");
				Tren tren = new Tren();
				System.out.println(tren);
				trens.add(tren);
				System.out.println("\n\n");
				break;
				//********

			case 8: //Modificar TREN
				System.out.println("Modificant TREN...");
				if(trens.size()==0) //NO hi ha TRENS
					System.out.println("ERROR Modifica TREN: No hi ha TRENS a modificar");
				else{ //Hi ha TRENS creats
					llistaTrens();
					int numTren = Validacio.validaSencer("\t Tria TREN a modificar:",trens.size());
					if(numTren == 0)
						System.out.println("Anulant acció");
					else
						trens.get(numTren-1).modificaTren();
				}
				System.out.println("\n\n");
				break;
				//********

			case 9: //Esborrar TREN
				System.out.println("Esborrant TREN...");
				if(trens.size()==0) //NO hi ha TRENS
					System.out.println("ERROR Esborra TREN: No hi ha TRENS a esborrar");
				else{ //Hi ha TRENS creats
					llistaTrens();
					int numTren = Validacio.validaSencer("\t Tria TREN a esborrar:",trens.size());
					if(numTren == 0)
						System.out.println("Anulant acció");
					else
						trens.remove(numTren-1);
				}
				System.out.println("\n\n");
				break;
				//********

			case 10: //Crear TRAJECTE
				System.out.println("Creant TRAJECTE...");
				Trajecte tra = new Trajecte();
				System.out.println(tra);
				trajectes.add(tra);
				System.out.println("\n\n");
				break;
				//******** 

			case 11: //Modificar TRAJECTE
				System.out.println("Modificant TRAJECTE...");
				if(trajectes.size()==0) //NO hi ha trajectes
					System.out.println("ERROR Modifica TRAJECTE: No hi ha TRAJECTES a modificar");
				else{ //Hi ha TRAJECTES creats
					llistaTrajectes();
					int numTrajecte = Validacio.validaSencer("\t Tria TRAJECTE a modificar:",trajectes.size());
					if(numTrajecte == 0)
						System.out.println("Anulant acció");
					else
						trajectes.get(numTrajecte-1).modificaTrajecte();
				}
				System.out.println("\n\n");

				break;
				//********

			case 12: //Eliminar TRAJECTE
				System.out.println("Esborrant TRAJECTE...");
				if(trajectes.size()==0) //NO hi ha TRAJECTES
					System.out.println("ERROR Esborra TRAJECTE: No hi ha TRAJECTES a esborrar");
				else{ //Hi ha TRAJECTES creats
					llistaTrajectes();
					int numTrajecte = Validacio.validaSencer("\t Tria TRAJECTE a esborrar:",trajectes.size());
					if(numTrajecte == 0)
						System.out.println("Anulant acció");
					else {
						trajectes.get(numTrajecte-1).esborraTrajecte();
						trajectes.remove(numTrajecte-1);
					}
				}
				System.out.println("\n\n");
				System.out.println("\n\n");

				break;
				//********

			case 13: //Associar VAGONS a TRAJECTE
				System.out.println("Associant VAGONS a TRAJECTE...");
				carregaDadesInicials();
				associaClasseVago();


				break;
				//********

			case 14: //Comprar BITLLET
				System.out.println("Comprant ENTRADA...");
				System.out.println("Compra amb fils");
				/*compraBitllet();*/
				
				FilCompraBitllets objFil = new FilCompraBitllets();
				Thread fil1 = new Thread(objFil);
				
				FilCompraBitllets objFil2 = new FilCompraBitllets();
				Thread fil2 = new Thread(objFil2);
				
				fil1.start();
				fil1.join();
				
				fil2.start();
				fil2.join();
				
				
				System.out.println("\n\n");

				break;
				//********

			default: System.out.println("Eixint RENFE...\n Programa finalitzat!!!");
			System.out.println("\n\n");

			}
		}while(opcio!=0);

	}

	//*********************************************************
	public static void associaClasseVago() {
		Classe classe;
		if(trajectes.size()>0 && classes.size()>0 && vagons.size()>0 && numVagonsLliures()>0) {

			int numTrajecte = Validacio.validaSencer(" Selecciona un TRAJECTE: ", llistaTrajectes());
			Trajecte trajecte = trajectes.get(numTrajecte-1);



			//Si es vol afegir una nova CLASSE...
			classe = trajecte.afegirClasse(" Vols afegir una nova CLASSE al TRAJECTE? [S/N]");
			if (classe != null) { //Nova Classe afegida
				trajecte.getClassesTrajecte().add(classe);
				System.out.println("\n\t Afegida la nova Classe al Trajecte \n");

			}else { //NO s'afegeix cap Classe
				int numClassesTrajecte = trajecte.llistarClassesTrajecte();
				int numClasse = Validacio.validaSencer(" Selecciona una CLASSE: ", numClassesTrajecte);
				classe = trajecte.getClassesTrajecte().get(numClasse-1);
			}


			//Si es vol afegir un nou VAGÓ...
			Vago vago = classe.afegirVago(" Vols afegir un nou VAGÓ a la Classe del TRAJECTE? [S/N]");
			if (vago != null) {
				classe.getVagonsClasse().add(vago);
				//classe.getVagonsClasse();
			}
		}

		else {
			System.out.println("No hi ha suficients elements per associar VAGONS a TRAJECTE \n\n");
		}
	}


	//*********************************************************
	//COMPRA INTERACTIVA D'UN UNIC BITLLET
	public static void compraBitllet() throws InterruptedException{

		Trajecte trajecte = null;
		Tren tren = null;
		Classe classeTrajecte = null;
		Vago vago = null;
		Seient seient = null;
		ArrayList<Classe> classesTrajecte;
		ArrayList<Vago> vagonsClasse;
		int numTrajecte, numClasseTrajecte, numVagoClasseTrajecte, fila; 

		//Si NO hi ha TRAJECTES, s'ix del procés de compra
		if (llistaTrajectes() == 0) {
			System.out.println("\t ERROR Renfe:compraBitllet: No hi ha TRAJECTES");
			return;
		}
		//Selecció de TRAJECTE
		numTrajecte = Validacio.validaSencer(" Tria TRAJECTE:",trajectes.size());
		trajecte = trajectes.get(numTrajecte-1);
		//System.out.println(" Seleccionat " +trajecte);
		System.out.println();
		System.out.println();

		//Si NO hi ha TRENS per al TRAJECTE, s'ix del procés de compra
		if ((tren=trajecte.getTren()) == null) {
			System.out.println("\t ERROR  Renfe:compraBitllet: No hi ha TRENS per a esta TRAJECTE");
			return;
		}

		//Si NO hi ha CLASSES per al TRAJECTE, s'ix del procés de compra
		classesTrajecte = trajecte.getClassesTrajecte();
		if (trajecte.llistarClassesTrajecte() == 0) {
			System.out.println("\t ERROR  Renfe:compraBitllet: No hi ha CLASSES per a este TRAJECTE");
			return;
		}
		//Selecció de la CLASSE del TRAJECTE
		numClasseTrajecte = Validacio.validaSencer(" Tria CLASSE:",classesTrajecte.size());
		classeTrajecte = classesTrajecte.get(numClasseTrajecte-1);
		System.out.println();
		System.out.println();


		//Si NO hi ha VAGONS per la CLASSE del TRAJECTE, s'ix del procés de compra
		vagonsClasse =classeTrajecte.getVagonsClasse();
		if(classeTrajecte.llistarVagonsClasse() == 0) {
			System.out.println("\t ERROR  Renfe:compraBitllet: No hi ha VAGONS per a este TRAJECTE");
			return;
		}
		//Selecció del VAGO de la CLASSE del TRAJECTE
		numVagoClasseTrajecte = Validacio.validaSencer(" Tria VAGÓ:",vagonsClasse.size());
		vago = vagonsClasse.get(numVagoClasseTrajecte-1);
		System.out.println();
		System.out.println();

		vago.mapaVago();

		//Selecció del SEIENT
		boolean seientIdentificat = false;
		Seient[][] seients = vago.getSeients();
		int nFiles = vago.getnPlaces()/vago.getnColumnes();

		int i = 0;
		System.out.println("Quants bitllets vols comprar: ");
		int numeroSeients = Integer.parseInt(teclado.nextLine());
		
		while (i < numeroSeients) {
			fila = Validacio.validaSencer(" Tria FILA: [1-"+nFiles+"] ",nFiles);
			do {
				String lletraSeient = Validacio.validaCadena(" Tria SEIENT en la fila: [A, B, C, D]");

				switch(lletraSeient.toUpperCase()) {
				case "A":
					seient = seients[fila-1][0];
					seientIdentificat = true;
					break;
				case "B":
					seient = seients[fila-1][1];
					seientIdentificat = true;
					break;
				case "C":
					seient = seients[fila-1][2];
					seientIdentificat = true;
					break;
				case "D":
					seient = seients[fila-1][3];
					seientIdentificat = true;
					break;
				default:
					System.out.println("Error: selecció del SEIENT incorrecta");
					break;
				}
			}while(!seientIdentificat);
			i++;
		}
		

		System.out.print(">> ");



		if (seient.verificaSeient()){ //Si SEIENT lliure -> reserva SEIENT
			System.out.println("Seient amb Identificador " + seient.getIdSeient() +" reservat");
			seient.reservaSeient();
			//pagament entrada
			if (pagamentBitllet(classeTrajecte.getPreu())) {
				seient.ocupaSeient();
				System.out.println("Seient amb Identificador " + seient.getIdSeient() +" ocupat");
				vago.imprimirBitllet(seient,vago, classeTrajecte, trajecte.getTren(), trajecte);
			}
			else {
				System.out.println("\t ERROR Renfe:pagamentBitllet: S'ha cancel·lat el pagament del bitllet");
				seient.reservaSeient();
			}
		}else{ //NO Reserva
			System.out.println("\t ERROR Renfe:pagamentBitllet: No sha pogut fer reserva del Seient");
			seient.alliberaSeient();
		};

		vago.mapaVago();
	}
	
	public static void compraNBitllets() throws InterruptedException {
		//boolean volComprar = true;
		int i = 0;
		System.out.println("Quants bitllets vols comprar: ");
		int numBitllets = Integer.parseInt(teclado.nextLine());
			
		System.out.println("Vas ha comprar " + numBitllets + " bitllets");
		while (i < numBitllets) {
			compraBitllet();
			i++;
		}
			
			
		
		
		
	}


	//*********************************************************
	//PAGAMENT D'UN BITLLET

	static boolean pagamentBitllet(BigDecimal preu){
		System.out.println("Import a pagar: "+preu);
		System.out.println("\nProcés de pagament...(2seg)");
		//pagant
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Validacio.validaBoolea("Pagat? (S/N)");

	}


	//*********************************************************
	//VISUALITZA EL MENU PRINCIPAL
	public static int menu(){
		int opcio;
		Scanner s = new Scanner(System.in);

		do{
			System.out.println("MENU Aplicació RENFE:");
			System.out.println("====================");

			System.out.println("1.  Crear VAG�");
			System.out.println("2.  Modificar VAG�");
			System.out.println("3.  Eliminar VAG�");

			System.out.println("4.  Crear CLASSE");
			System.out.println("5.  Modificar CLASSE");
			System.out.println("6.  Eliminar CLASSE");

			System.out.println("7.  Crear TREN");
			System.out.println("8.  Modificar TREN");
			System.out.println("9.  Eliminar TREN");

			System.out.println("10.  Crear TRAJECTE");
			System.out.println("11.  Modificar TRAJECTE");
			System.out.println("12.  Eliminar TRAJECTE");

			System.out.println("13. Associar CLASSES/VAGONS a TRAJECTE");
			System.out.println("14. Comprar BITLLET/S");
			System.out.println("0. Eixir aplicaci� RENFE");

			System.out.println("\n\nIntrodueix opci� de menu:");
			String stropcio = s.next();
			opcio=Integer.parseInt(stropcio);
		}while (opcio < 0 || opcio > 14);

		return opcio;
	}

	//*********************************************************
	//CÀRREGA DE DADES INICIALS DEL CINE (VAGONS, CLASSES, TRENS, TRAJECTES...)
	public static void carregaDadesInicials() {
		System.out.println("Càrrega INICIAL de DADES...");
		Vago va1, va2, va3, va4, va5;
		vagons.add(va1 = new Vago("vago01", 40));
		vagons.add(va2 = new Vago("vago02", 50));
		vagons.add(va3 = new Vago("vago03", 60));
		vagons.add(va4 = new Vago("vago04", 70));
		vagons.add(va5 = new Vago("vago05", 80));

		Classe c1, c2, c3, c4;
		classes.add(c1 = new Classe("c1", Classe.TIPUSCLASSE.Turista, "Classe Turista",16));
		classes.add(c2 = new Classe("c2", Classe.TIPUSCLASSE.TuristaPlus, "Classe TuristaPlus",30));
		classes.add(c3 = new Classe("c3", Classe.TIPUSCLASSE.Preferent, "Classe PreferentA",45));
		classes.add(c4 = new Classe("c4", Classe.TIPUSCLASSE.Preferent, "Classe PreferentB",51));

		Tren t1, t2;
		trens.add(t1 = new Tren("tren01", 80));
		trens.add(t2 = new Tren("tren02", 100));

		Trajecte tj1, tj2;
		trajectes.add(tj1 =new Trajecte("VLC-MDR", "VLC", "MDR", Calendar.getInstance(), 450, t1, new ArrayList<Classe>()));
		tj1.getClassesTrajecte().add(c1);
		c1.setAssignadaClasse(true);
		c1.getVagonsClasse().add(va2);
		va2.setAssignaVago(true);

		trajectes.add(tj2 =new Trajecte("VLC-BCN", "VLC", "BCN", Calendar.getInstance(), 350, t2, new ArrayList<Classe>()));
		tj2.getClassesTrajecte().add(c2);
		c2.setAssignadaClasse(true);
		c2.getVagonsClasse().add(va4);
		va4.setAssignaVago(true);
	}

	//*********************************************************
	//Mostra la llista de  VAGONS NO assignats a una CLASSE
	public static int numVagonsLliures(){
		int numVagonsLliures=0;
		if (vagons.size() == 0) System.out.println("\n\t No hi ha cap VAGÓ registrat");
		else {
			//System.out.println("\n******** VAGONS *******");
			for(int i=0; i<vagons.size();i++){
				if (!vagons.get(i).isAssignatVago()) {//si estan lliures, es mostren

					numVagonsLliures++;
				}
			}
		}
		return numVagonsLliures;
	}

	//*********************************************************
	//Mostra la llista de  VAGONS
	public static int llistaVagons(){
		int numVagons=0;
		if (vagons.size() == 0) System.out.println("\n\t No hi ha cap VAGÓ registrat");
		else {
			System.out.println("llistat VAGONS:");
			for(int i=0; i<vagons.size();i++){

				System.out.println("\n "+(i+1)+":\t "+vagons.get(i).toString());
				System.out.println("\t---");
				numVagons++;

			}
			System.out.println();
		}
		return numVagons;
	}

	//*********************************************************
	//Mostra la llista de  VAGONS NO assignats a una CLASSE
	public static void llistaVagonsLliures(){
		int numVagonsLliures=0;
		if (vagons.size() == 0) System.out.println("\n\t No hi ha cap VAGÓ registrat");
		else {
			System.out.println("llistat VAGONS lliures:");
			for(int i=0; i<vagons.size();i++){
				if (!vagons.get(i).isAssignatVago()) {//si estan lliures, es mostren
					System.out.println("\n "+(i+1)+":\t "+vagons.get(i).toString());
					System.out.println("\t---");
					numVagonsLliures++;
				}
			}
			System.out.println();
		}
	}


	//*********************************************************
	//Mostra la llista de  CLASSES NO assignats a un TREN 
	public static int llistaClasses(){
		int numClasses=0;
		if (classes.size() == 0) System.out.println("\n\t No hi ha cap CLASSE registrada");
		else {
			System.out.println("llistat CLASSES:");
			for(int i=0; i<classes.size();i++){
				System.out.println("\n "+(i+1)+":\t "+classes.get(i).toString());
				System.out.println("\t---");
				numClasses++;

			}
			System.out.println();
		}
		return numClasses;
	}

	//*********************************************************
	//Mostra la llista de  CLASSES NO assignats a un TREN 
	public static int llistaClassesLliures(){
		int numClassesLliures=0;
		if (classes.size() == 0) System.out.println("\n\t No hi ha cap CLASSE registrada");
		else {
			System.out.println("llistat CLASSES lliures:");
			for(int i=0; i<classes.size();i++){
				if (!classes.get(i).isAssignadaClasse()) {//si estan lliures, es mostren
					System.out.println("\n "+(i+1)+":\t "+classes.get(i).toString());
					System.out.println("\t---");
					numClassesLliures++;
				}
			}
			System.out.println();
		}
		return numClassesLliures;
	}


	//*********************************************************
	//Mostra la llista de  TRENS 
	public static int llistaTrens(){
		int numTrens=0;
		if (trens.size() == 0) System.out.println("\n\t No hi ha cap TREN disponible");
		else {
			System.out.println("llistat TRENS:");
			//System.out.println("\n******** TRENS *******");
			for(int i=0; i<trens.size();i++){

				System.out.println("\n "+(i+1)+":\t "+trens.get(i).toString());
				System.out.println("\t---");
				numTrens++;

			}
			System.out.println();
		}
		return numTrens;
	}

	//*********************************************************
	public static boolean isIdTrenValid(String idTren) {
		for (Tren tren: trens) {
			if (tren.getIdTren().equalsIgnoreCase(idTren))
				return false;
		}
		return true;
	}

	//*********************************************************
	public static boolean isIdVagoValid(String idVago) {
		for (Vago vago: vagons) {
			if (vago.getIdVago().equalsIgnoreCase(idVago))
				return false;
		}
		return true;
	}

	//*********************************************************
	//Mostra la llista de  TRAJECTES 
	public static int llistaTrajectes(){
		int numTrajectes=0;
		if (trajectes.size() == 0) System.out.println("\n\t No hi ha cap TRAJECTE");
		else {
			System.out.println("llistat TRAJECTES:");
			//System.out.println("\n******** TRENS *******");
			for(int i=0; i<trajectes.size();i++){

				System.out.println("\n "+(i+1)+":\t "+trajectes.get(i).toString());
				System.out.println("\t---");
				numTrajectes++;

			}
			System.out.println();
		}
		return numTrajectes;
	}
}
