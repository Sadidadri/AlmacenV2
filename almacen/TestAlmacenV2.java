package almacen;

import excepciones.AlmacenVacioException;
import excepciones.CodigoNoEncontradoException;
import excepciones.MercanciaNegativaException;
import excepciones.PNegativoException;
import excepciones.StockNegativoException;
import utiles.Menu;
import utiles.Teclado;

/**
 * Programa que se encarga de la gestion de un almacen
 * 
 * @author d18momoa
 *
 */
public class TestAlmacenV2 {
	// Creacion de los elementos estaticos de la clase Test
	static Gestisimal almacen = new Gestisimal();

	public static void main(String[] args) {
		Menu menu = new Menu("Bienvenido al menu Gestisimal:",
				new String[] { "Listado", "Alta", "Baja", "Modificación", "Entrada de mercancía", "Salida de mercancía" });
		int opcion = 0;
		do {
			opcion = menu.gestionar(); // Pide al usuario introducir un numero para escoger la opcion
			switch (opcion) {
			case 1: // Muestra el almacen
				try {
					mostrarLista();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			case 2: // Agnade un elemento a la lista
				try {
					System.out.println("Vamos a dar de alta un articulo, rellene los siguientes datos:");
					annadirArticulo();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			case 3: // borra un elemento de la lista
				try {
					borrarArticulo();
					System.out.println("##Borrado finalizado##");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			case 4: // Modifica un elemento de la lista
				try {
					modificarArticulo();
					System.out.println("##Modificado satisfactoriamente##");
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			case 5: // Aumenta el stock de un elemento de la lista
				try {
					entraMercancia();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			case 6: // Decrementa el stock de un elemento de la lista
				try {
					saleMercancia();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		} while (opcion != menu.getSalir());

	}

	/**
	 * Muestra el contenido del almacen
	 * 
	 * @throws AlmacenVacioException
	 */
	public static void mostrarLista() throws AlmacenVacioException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		} else {
			System.out.println("Tenemos:");
			System.out.println(almacen);
		}
	}

	/**
	 * Registra un articulo nuevo al almacen pidiendole los datos al usuario
	 * 
	 * @throws PNegativoException
	 * @throws StockNegativoException
	 */
	public static void annadirArticulo() throws PNegativoException, StockNegativoException {
		String descripcion = Teclado.leerCadena("Introduzca nombre del producto:");
		double pC = Teclado.leerDecimal("Precio de Compra:");
		double pV = Teclado.leerDecimal("Precio de Venta:");
		int stock = Teclado.leerEntero("Stock");
		almacen.annadirNuevoArticulo(descripcion, pC, pV, stock);
	}

	/**
	 * Borra un articulo mediante el codigo indicado por el usuario
	 * 
	 * @throws CodigoNoEncontradoException
	 * @throws AlmacenVacioException
	 */
	public static void borrarArticulo() throws CodigoNoEncontradoException, AlmacenVacioException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		} else {
			int code = Teclado.leerEntero("Introduzca el codigo del articulo a borrar:");
			almacen.borrarArticulo(code);
		}
	}

	/**
	 * Modifica el contenido de un articulo ya existente
	 * 
	 * @throws AlmacenVacioException
	 * @throws CodigoNoEncontradoException
	 * @throws StockNegativoException
	 * @throws PNegativoException
	 */
	public static void modificarArticulo()
			throws AlmacenVacioException, CodigoNoEncontradoException, StockNegativoException, PNegativoException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		} else {
			System.out.println("Introduzca el codigo del articulo a modificar:");
			int code = Teclado.leerEntero();

			if (almacen.compruebaCodigo(code)) {
				Articulo articulo = almacen.get(code);
				// Muestra el articulo a modificar
				System.out.println("Este es el articulo a modificar:");
				System.out.println(articulo);
				System.out.println("Introduzca los datos del producto.");
				String descripcionNueva = Teclado.leerCadena("Descripción:");
				double pCompraNuevo = Teclado.leerDecimal("Precio de Compra:");
				double pVentaNuevo = Teclado.leerDecimal("Precio de Venta:");
				int stockNuevo = Teclado.leerEntero("Stock");
				almacen.modificar(articulo, descripcionNueva, pCompraNuevo, pVentaNuevo, stockNuevo);
			}
		}
	}

	/**
	 * Aumenta el stock de un articulo cuyo codigo es indicado por el usuario
	 * 
	 * @throws AlmacenVacioException
	 * @throws CodigoNoEncontradoException
	 * @throws StockNegativoException
	 * @throws MercanciaNegativaException
	 */
	public static void entraMercancia()
			throws AlmacenVacioException, CodigoNoEncontradoException, StockNegativoException, MercanciaNegativaException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		} else {
			int code = Teclado.leerEntero("Vamos a aumentar el stock, indique el codigo del articulo:");
			if (almacen.compruebaCodigo(code)) {
				int cantidad = Teclado.leerEntero("Indique la cantidad a aumentar:");
				almacen.entraMercancia(code, cantidad);
			}
		}
	}

	/**
	 * Decrementa el stock de un articulo cuyo codigo es indicado por el usuario
	 * 
	 * @throws AlmacenVacioException
	 * @throws CodigoNoEncontradoException
	 * @throws StockNegativoException
	 * @throws MercanciaNegativaException
	 */
	public static void saleMercancia()
			throws AlmacenVacioException, CodigoNoEncontradoException, StockNegativoException, MercanciaNegativaException {
		if (almacen.compruebaSiEstaVacio()) {
			throw new AlmacenVacioException("El almacen esta vacio, no hay elementos a mostrar");
		} else {
			int code = Teclado.leerEntero("Vamos a disminuir el stock, indique el codigo del articulo:");
			if (almacen.compruebaCodigo(code)) {
				int cantidad = Teclado.leerEntero("Indique la cantidad a disminuir:");
				almacen.saleMercancia(code, cantidad);
			}
		}
	}
}
