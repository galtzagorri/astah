package videojuego;

import java.util.ArrayList;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import videojuego.DescripcionVideoJuego;

public class ControladorVideoJuego {
	
	private Map<String, DescripcionVideoJuego> listaDescripciones;
	
	public ControladorVideoJuego() {
		super();
		listaDescripciones = new HashMap<String, DescripcionVideoJuego>();
	}
	
	public void crearDescripcionVideoJuego(String titulo,String modeloConsola,Year añoLanzamiento,String genero,int edadMinima,String idDescripcion,String creador,float costeAlquiler)
			throws ExcepcionDescripcion {
		if (!listaDescripciones.containsKey(idDescripcion)) {
			DescripcionVideoJuego nuevaDescripcion = new DescripcionVideoJuego(titulo,modeloConsola,añoLanzamiento,genero,edadMinima,idDescripcion,creador,costeAlquiler);
			listaDescripciones.put(idDescripcion, nuevaDescripcion);
		} else {
			throw new ExcepcionDescripcion(CausaExcepcionDescripcion.YA_EXISTE, idDescripcion);
		}
	}
	
	public String verDescripcionVideoJuego(String idDescripcion) throws ExcepcionDescripcion{

			DescripcionVideoJuego estaDescripcion = listaDescripciones.get(idDescripcion);
			
			if (estaDescripcion != null) {

				return estaDescripcion.verFichaCompleta();
			} else {

				throw new ExcepcionDescripcion(CausaExcepcionDescripcion.NO_EXISTE, idDescripcion);
			}
		}
	
	public void modificarDescripcionVideoJuego(String titulo,String modeloConsola,Year añoLanzamiento,String genero,int edadMinima,String idDescripcion,String creador,float costeAlquiler)
			throws ExcepcionDescripcion {
		DescripcionVideoJuego estaDescripcion = listaDescripciones.get(idDescripcion);
		if (estaDescripcion != null) {
			estaDescripcion.setTitulo(titulo);
			estaDescripcion.setModeloConsola(modeloConsola);
			estaDescripcion.setAñoLanzamiento(añoLanzamiento);
			estaDescripcion.setGenero(genero);
			estaDescripcion.setEdadMinima(edadMinima);
			estaDescripcion.setCreador(creador);
			estaDescripcion.setCosteAlquiler(costeAlquiler);
		} else {
			throw new ExcepcionDescripcion(CausaExcepcionDescripcion.NO_EXISTE, idDescripcion);
			}
		}
	
	public void eliminarDescripcionVideoJuego(String idDescripcion) throws ExcepcionDescripcion {

		
		DescripcionVideoJuego estaDescripcion = listaDescripciones.remove(idDescripcion);

		if (estaDescripcion == null) {
			throw new ExcepcionDescripcion(CausaExcepcionDescripcion.NO_EXISTE, idDescripcion);
		}
	}
	
	public List<String> listarDescripciones() {
		List<String> listado = new ArrayList<String>();
		for (DescripcionVideoJuego j : listaDescripciones.values()){
			String ficha = j.verFichaBreve();	
			listado.add(ficha);
		}
		return listado;
	}
	
	public List<String>listarCopias(String idDescripcion){
		DescripcionVideoJuego estaDescripcion = listaDescripciones.get(idDescripcion);
		return estaDescripcion.listarCopias();
	}
	
	public void crearCopiaVideoJuego(String idDescripcion) throws ExcepcionCopia{
		DescripcionVideoJuego estaDescripcion = listaDescripciones.get(idDescripcion);
		Estado estado = Estado.DISPONIBLE;
		estaDescripcion.crearCopia(estaDescripcion,estado);
	}
	
	public String verCopiaVideoJuego(String idCopia, String idDescripcion) throws ExcepcionCopia{
		DescripcionVideoJuego estaDescripcion = listaDescripciones.get(idDescripcion);
		return estaDescripcion.verCopia(idCopia);
	}

	public CopiaVideoJuego obtenerCopia(String idDescripcion,String idCopia) {
		DescripcionVideoJuego estaDescripcion = listaDescripciones.get(idDescripcion);
		return estaDescripcion.obtenerCopia(idCopia);		
	}
	
}
