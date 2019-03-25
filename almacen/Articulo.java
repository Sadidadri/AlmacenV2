package almacen;

import excepciones.MercanciaNegativaException;
import excepciones.PNegativoException;
import excepciones.StockNegativoException;

/**
 * Programa que se encarga de crear los articulos y que luego seran registrados dentro de la lista del almacen
 * @author d18momoa
 *
 */
public class Articulo {
	//Atributos
	private static int contador = 0;
	private int codigo;
	private String descripcion;
	private double precioCompra;
	private double precioVenta;
	private int stock;
	
	//Constructor principal
	Articulo(String descripcion,double precioCompra,double precioVenta,int stock) throws PNegativoException, StockNegativoException {
		this.codigo = generarCodigo();
		this.descripcion = descripcion;
		setPrecioCompra(precioCompra);
		setPrecioVenta(precioVenta);
		setStock(stock);
	}
	//Constructor para comparar codigo
	Articulo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Aumenta una unidad del contador y la devuelve.
	 * @return la unidad aumentada.
	 */
	private int generarCodigo() {
			contador++;
		return contador;
	}
	/**
	 * Obtiene valor de codigo
	 * @return valor de codigo
	 */
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Obtiene el contenido de la descripcion
	 * @return cadena de descripcion
	 */
	private String getDescripcion() {
		return descripcion;
	}
	/**
	 * Obtiene valor del precio de compra
	 * @return valor del precio de compra
	 */
	private double getPrecioCompra() {
		return precioCompra;
	}
	/**
	 * Obtiene valor del precio de venta
	 * @return valor del precio de venta
	 */
	public double getPrecioVenta() {
		return precioVenta;
	}
	/**
	 * Obtiene la cantidad de stock
	 * @return cuanto hay de stock
	 */
	public int getStock() {
		return stock;
	}
/**
 * Establece el valor del precio de compra, controla que no sea negativo, en ese caso lanza una excepcion
 * @param pv
 * @throws PVentaNegativoException 
 */
	private void setPrecioVenta(double pv) throws PNegativoException {
		if(pv >= 0) {
			this.precioVenta = pv;
		}else {
			throw new PNegativoException("Error, el precio de venta no puede ser negativo");
		}		
	}
	/**
	 * Establece el valor del precio de venta, controla que no sea negativo, en ese caso lanza una excepcion
	 * @param pc
	 */
	private void setPrecioCompra(double pc) throws PNegativoException {
		if(pc >= 0) {
			this.precioCompra = pc;
		}else {
			throw new PNegativoException("Error, el precio de compra no puede ser negativo");
		}		
	}
	/**
	 * Establece la cantidad de stock, controla que no sea negativo, en ese caso lanza una excepcion
	 * @param s
	 * @throws StockNegativoException 
	 */
	private void setStock(int s) throws StockNegativoException {
		if(s >= 0) {
			this.stock = s;
		}else {
			throw new StockNegativoException("Error, el stock no puede ser inferior a 0");
		}
	}
	/**
	 * Entra mercancia, el stock del articulo aumenta en las unidades especificadas
	 * @throws StockNegativoException 
	 * @throws MercanciaNegativaException 
	 */
	public void entraMercancia(int cantidad) throws StockNegativoException, MercanciaNegativaException {
		if(cantidad > 0)
			setStock(getStock()+cantidad);
		else
			throw new MercanciaNegativaException("Error, no puedes agnadir un stock negativo");
	}
	/**
	 * Sale mercancia, el stock del articulo decrementa en las unidades especificadas
	 * @throws StockNegativoException 
	 * @throws MercanciaNegativaException 
	 */
	public void saleMercancia(int cantidad) throws StockNegativoException, MercanciaNegativaException {
		if(cantidad > 0)
			setStock(getStock()- cantidad);
		else
			throw new MercanciaNegativaException("Error, no puede salir mercancia negativa");
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	/**
	 * Modifica los atributos del articulo excepto el codigo
	 * @param descripcionIntroducido
	 * @param precioCompraIntroducido
	 * @param precioVentaIntroducido
	 * @param stockIntroducido
	 * @throws StockNegativoException
	 * @throws PNegativoException
	 */
	void modifica(String descripcionIntroducido, double precioCompraIntroducido, double precioVentaIntroducido,int stockIntroducido) throws StockNegativoException, PNegativoException {
		this.descripcion = descripcionIntroducido;
		setPrecioCompra(precioCompraIntroducido);
		setPrecioVenta(precioVentaIntroducido);
		setStock(stockIntroducido);
	}
	/**
	 * Metodo toString de articulo
	 */
	public String toString() {
		return getDescripcion()+": Codigo: "+getCodigo()+" | Precio Compra: "+getPrecioCompra()+"€ | PVP: "+getPrecioVenta()+"€ | Stock: "+getStock()+" |";
	}
}
