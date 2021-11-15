package tienda.daniel.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="productos")
public class Productos implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	

	private int id_categoria;
	
	private String nombre;

	private String descripcion;
	private double precio;
	private Integer stock;
	private Date fecha_alta;
	private Date fecha_baja;
	private float impuesto;
	private String imagen;
	private int id_proveedor;

	public Productos(Integer id, Integer id_categoria, String nombre, String descripcion, double precio, Integer stock,
			Date fecha_alta, Date fecha_baja, float impuesto, String imagen, int id_proveedor) {
		super();
		this.id = id;
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fecha_alta = fecha_alta;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
		this.imagen = imagen;
		this.id_proveedor = id_proveedor;
	}
	

	public Productos(int id, String nombre, String descripcion, double precio, Integer stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
	
	}


	public Productos() {
		// TODO Auto-generated constructor stub
	}


	public int getId_proveedor() {
		return id_proveedor;
	}


	public void setId_proveedores(int id_proveedor) {
		this.id_proveedor = id_proveedor;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Date getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Date getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
