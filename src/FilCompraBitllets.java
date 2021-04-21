import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class FilCompraBitllets implements Runnable {
static Scanner teclado = new Scanner(System.in);
	
	
	private String nom;
	private Trajecte trajecte;
	private Tren tren;
	private Classe classeTrajecte;
	private Vago vago;
	private Seient seient;
	private ArrayList<Classe> classesTrajecte;
	private ArrayList<Vago> vagonsClasse;
	private ArrayList<Trajecte> trajectes = new ArrayList<Trajecte>();
	
	public FilCompraBitllets() {
		super();
		System.out.println("Inici de fil: " + nom);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int numTrajecte, numClasseTrajecte, numVagoClasseTrajecte, fila; 

		carregaDadesInicials();
		
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
	
	public int llistaTrajectes(){
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
	
	public void carregaDadesInicials() {
		System.out.println("Càrrega INICIAL de DADES...");
		Vago va1, va2, va3, va4, va5;
		ArrayList<Vago> vagons = new ArrayList<Vago>();
		vagons .add(va1 = new Vago("vago01", 40));
		vagons.add(va2 = new Vago("vago02", 50));
		vagons.add(va3 = new Vago("vago03", 60));
		vagons.add(va4 = new Vago("vago04", 70));
		vagons.add(va5 = new Vago("vago05", 80));

		Classe c1, c2, c3, c4;
		ArrayList<Classe> classes = new ArrayList<Classe>();
		classes.add(c1 = new Classe("c1", Classe.TIPUSCLASSE.Turista, "Classe Turista",16));
		classes.add(c2 = new Classe("c2", Classe.TIPUSCLASSE.TuristaPlus, "Classe TuristaPlus",30));
		classes.add(c3 = new Classe("c3", Classe.TIPUSCLASSE.Preferent, "Classe PreferentA",45));
		classes.add(c4 = new Classe("c4", Classe.TIPUSCLASSE.Preferent, "Classe PreferentB",51));

		Tren t1, t2;
		ArrayList<Tren> trens = new ArrayList<Tren>();
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Trajecte getTrajecte() {
		return trajecte;
	}

	public void setTrajecte(Trajecte trajecte) {
		this.trajecte = trajecte;
	}

	public Tren getTren() {
		return tren;
	}

	public void setTren(Tren tren) {
		this.tren = tren;
	}

	public Classe getClasseTrajecte() {
		return classeTrajecte;
	}

	public void setClasseTrajecte(Classe classeTrajecte) {
		this.classeTrajecte = classeTrajecte;
	}

	public Vago getVago() {
		return vago;
	}

	public void setVago(Vago vago) {
		this.vago = vago;
	}

	public Seient getSeient() {
		return seient;
	}

	public void setSeient(Seient seient) {
		this.seient = seient;
	}

	public ArrayList<Classe> getClassesTrajecte() {
		return classesTrajecte;
	}

	public void setClassesTrajecte(ArrayList<Classe> classesTrajecte) {
		this.classesTrajecte = classesTrajecte;
	}

	public ArrayList<Vago> getVagonsClasse() {
		return vagonsClasse;
	}

	public void setVagonsClasse(ArrayList<Vago> vagonsClasse) {
		this.vagonsClasse = vagonsClasse;
	}

	public ArrayList<Trajecte> getTrajectes() {
		return trajectes;
	}

	public void setTrajectes(ArrayList<Trajecte> trajectes) {
		this.trajectes = trajectes;
	}
	
	

}
