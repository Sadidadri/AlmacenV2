package almacen;
import java.util.Scanner;

import excepciones.AlmacenVacioException;
import excepciones.CodigoNoEncontradoException;
import excepciones.MercanciaNegativaException;
import excepciones.PNegativoException;
import excepciones.StockNegativoException;
/**
 * Programa que se encarga de la gestion de un almacen
 * @author d18momoa
 *
 */
public class TestAlmacen {
	//Creacion de los elementos estaticos de la clase Test
	static Scanner entrada = new Scanner(System.in);
	static Gestisimal almacen = new Gestisimal();
	
	public static void main(String[] args) {	
		System.out.println("Bienvenido al menu Gestisimal:");
		do {
			mostrarMenu(); 
			System.out.println("Elige una opción:");
			int opcion = entrada.nextInt(); //Pide al usuario introducir un numero para escoger la opcion
			entrada.nextLine();
			switch(opcion) {	
				case 1: //Muestra el almacen
					try {
						mostrarLista();
					}catch(Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				case 2: //Agnade un elemento a la lista
					try {	
					System.out.println("Vamos a dar de alta un articulo, rellene los siguientes datos:");
					annadirArticulo();
					} catch (Exception e) {
					System.err.println(e.getMessage());
					}		 
					break;
				case 3: //borra un elemento de la lista
					try {
						borrarArticulo();
						System.out.println("##Borrado finalizado##");
					}
					catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				case 4: //Modifica un elemento de la lista
					try {
						modificarArticulo();
						System.out.println("##Modificado satisfactoriamente##");
					}
					catch (Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				case 5: //Aumenta el stock de un elemento de la lista
					try {
						entraMercancia();
					}
					catch(Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				case 6: //Decrementa el stock de un elemento de la lista
					try {
						saleMercancia();
					}
					catch(Exception e) {
						System.err.println(e.getMessage());
					}
					break;
				case 7: //Sale del programa
					System.out.println("Saliendo...");
					System.exit(1);
					break;
				default:System.err.println("Has introducido una opcion incorrecta");
					break;
				}
			}while(true);		
		
	}
	/**
	 * Muestra las opciones del menu
	 */
	public static void mostrarMenu() {
		System.out.println("1. Listado\n"+
			"2. Alta\n"+
			"3. Baja\n"+
			"4. Modificación\n"+
			"5. Entrada de mercancía\n"+
			"6. Salida de mercancía\n"+
			"7. Salir\n");
	}
	/**
	 * Muestra el contenido del almacen
	 * @throws AlmacenVacioException
	 */
	public static void mostrarLista() throws AlmacenVacioException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		}
		else {
			System.out.println("Tenemos:");
			System.out.println(almacen);
		}
	}
	/**
	 * Registra un articulo nuevo al almacen pidiendole los datos al usuario
	 * @throws PNegativoException
	 * @throws StockNegativoException
	 */
	public static void annadirArticulo() throws PNegativoException, StockNegativoException {
		System.out.println("Introduzca nombre del producto:");
		String descripcion = entrada.nextLine();
		System.out.println("Precio de Compra:");
		double pC = entrada.nextDouble();entrada.nextLine();
		System.out.println("Precio de Venta:");
		double pV = entrada.nextDouble();entrada.nextLine();
		System.out.println("Stock:");
		int stock = entrada.nextInt();entrada.nextLine();
		almacen.annadirNuevoArticulo(descripcion, pC, pV, stock);
	}
	/**
	 * Borra un articulo mediante el codigo indicado por el usuario
	 * @throws CodigoNoEncontradoException
	 * @throws AlmacenVacioException
	 */
	public static void borrarArticulo() throws CodigoNoEncontradoException, AlmacenVacioException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		}
		else {
			System.out.println("Introduzca el codigo del articulo a borrar:");
			int code = entrada.nextInt();entrada.nextLine();
			almacen.borrarArticulo(code);
		}
	}
	/**
	 * Modifica el contenido de un articulo ya existente
	 * @throws AlmacenVacioException
	 * @throws CodigoNoEncontradoException
	 * @throws StockNegativoException
	 * @throws PNegativoException
	 */
	public static void modificarArticulo() throws AlmacenVacioException, CodigoNoEncontradoException, StockNegativoException, PNegativoException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		}
		else {
			System.out.println("Introduzca el codigo del articulo a modificar:");
			int code = entrada.nextInt();entrada.nextLine();
			
			if(almacen.compruebaCodigo(code)) {
				Articulo articulo = almacen.get(code);
				//Muestra el articulo a modificar
				System.out.println("Este es el articulo a modificar:");
				System.out.println(articulo);
	    	System.out.println("Introduzca los datos del producto.");
	    	System.out.println("Descripción:");
	    	String descripcionNueva = entrada.nextLine();
	    	System.out.println("Precio de Compra:");
	    	double pCompraNuevo = entrada.nextDouble();
	    	System.out.println("Precio de Venta:");
	    	double pVentaNuevo = entrada.nextDouble();
	    	System.out.println("Stock");
	    	int stockNuevo = entrada.nextInt();
	    	almacen.modificar(articulo, descripcionNueva, pCompraNuevo, pVentaNuevo, stockNuevo);
			}
		}
	}
	/**
	 * Aumenta el stock de un articulo cuyo codigo es indicado por el usuario
	 * @throws AlmacenVacioException
	 * @throws CodigoNoEncontradoException
	 * @throws StockNegativoException
	 * @throws MercanciaNegativaException
	 */
	public static void entraMercancia() throws AlmacenVacioException, CodigoNoEncontradoException, StockNegativoException, MercanciaNegativaException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		}else {
			System.out.println("Vamos a aumentar el stock, indique el codigo del articulo:");
			int code = entrada.nextInt();entrada.nextLine();
			if(almacen.compruebaCodigo(code)) {
				System.out.println("Indique la cantidad a aumentar:");
				int cantidad = entrada.nextInt();entrada.nextLine();
				almacen.entraMercancia(code, cantidad);
			}
		}
	}
	/**
	 * Decrementa el stock de un articulo cuyo codigo es indicado por el usuario
	 * @throws AlmacenVacioException
	 * @throws CodigoNoEncontradoException
	 * @throws StockNegativoException
	 * @throws MercanciaNegativaException
	 */
	public static void saleMercancia() throws AlmacenVacioException, CodigoNoEncontradoException, StockNegativoException, MercanciaNegativaException  {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		}else {
			System.out.println("Vamos a disminuir el stock, indique el codigo del articulo:");
			int code = entrada.nextInt();entrada.nextLine();
			if(almacen.compruebaCodigo(code)) {
				System.out.println("Indique la cantidad a disminuir:");
				int cantidad = entrada.nextInt();entrada.nextLine();
				almacen.saleMercancia(code, cantidad);
			}
		}
	}
}
