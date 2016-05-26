package videojuego;


public class CopiaVideoJuego {
	
	private DescripcionVideoJuego descripcionVideoJuego;
	private String idCopia;
	private Estado estado;
	
	public CopiaVideoJuego(DescripcionVideoJuego descripcionVideoJuego, String idCopia, Estado estado){
		super();
		this.descripcionVideoJuego=descripcionVideoJuego;
		this.idCopia=idCopia;
		this.estado=estado;
	}
	
	public String verFichaBreve() {
		return descripcionVideoJuego.getIdDescripcion() + ": '" + idCopia + "' esta " + estado.toString();
	}
	
	public String verFichaCompleta() {
		return "Descripcion: " + descripcionVideoJuego.verFichaCompleta() + 
				"\nID Copia: " + idCopia + 
				"\nEstado: " + estado.toString();
	}
	
	public void setEstado(Estado estado) {
		this.estado=estado;		
	}
	
	public Estado getEstado(){
		return estado;
	}

	public DescripcionVideoJuego getDescripcionVideojuego() {
		return descripcionVideoJuego;
	}

	public String getIdCopia() {
		return idCopia;
	}

	public void alquilarCopia(String idCopia, int dias) {
		
	}

	public void cambiarDescripcion(String idCopia) {
		
	}

	public float getPrecioDiario() {
		return descripcionVideoJuego.getCosteAlquiler();
	}

	public String getTitulo() {
		return descripcionVideoJuego.getTitulo();
	}
	
}
