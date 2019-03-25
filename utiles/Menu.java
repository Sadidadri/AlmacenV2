package utiles;

/**Crea la clase Menu. Mételo en el paquete utiles. Implementa al menos los siguientes métodos y atributos:
*a.titulo
*b.opciones
*c.numOpciones 
*d.gestionar()
*e.mostrar()
*f.recogerOpcion()
*@author Adrian Moya Moruno
*@version 1.8
*/

public class Menu{
	private int numOpciones;
	private String[] opciones;
	private String titulo;
	private int opcion;
	private final int salir;
	
	public Menu(String titulo, String[] opciones){
		setNumeroOpciones(opciones.length + 1);
		setTitulo(titulo);
		setOpciones(opciones);
		this.salir = getNumOpciones();
	}

	private int getNumOpciones() {
		return numOpciones;
	}

	public int gestionar(){
		mostrar();
		return recogerOpcion();
	}
	
	private void setTitulo(String titulo){
		this.titulo = titulo;
	}
	
	private void setNumeroOpciones(int numOpciones){
		this.numOpciones = numOpciones;
	}
	
	private void setOpciones(String[] opciones){
		this.opciones = new String[numOpciones];
		for(int i=0;i<numOpciones-1;i++)
			this.opciones[i] = (i+1)+ ". " + opciones[i];
		this.opciones[numOpciones-1] = numOpciones + ". Salir";
	}
	
	public void mostrar(){
		System.out.println("\n**"+titulo+"**");
		for(int i=0; i<numOpciones;i++){
			System.out.println(opciones[i]);
		}
	}
	
	public boolean opcionValida(int opcion){
		boolean opcionValida;
		return (opcion>0 && opcion<=numOpciones);
	}
	
	public int recogerOpcion(){
		int opcion;
		do{
			opcion = Teclado.leerEntero("\nIntroduce una opcion valida");
		}while(!opcionValida(opcion));
		return opcion;
	}
	
	public int getSalir() {//
		return salir;
	}
	
}//cierre de la clase

