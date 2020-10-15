package info.pablogiraldo.tienda_bd.model;

public class Product implements Comparable<Product> {

	private int id;
	private String name;
	private String info;
	private float price;
	private int iva;
	private int stock;

	public Product() {

	}

	public Product(int id, String nombre, String info, float precioSinIva, int iva, int stock) {
		this.id = id;
		this.name = nombre;
		this.info = info;
		this.price = precioSinIva;
		this.iva = iva;
		this.stock = stock;
	}

	public Product(String nombre, String info, float precioSinIva, int iva, int stock) {
		this.name = nombre;
		this.info = info;
		this.price = precioSinIva;
		this.iva = iva;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}

	@Override
	public int compareTo(Product compareproduct) {
		int compareids = ((Product) compareproduct).getId();
		return compareids - this.id;
	}
}
