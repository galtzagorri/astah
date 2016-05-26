package arranque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.List;


import tarjetas.ExcepcionTarjeta;
import usuarios.ControladorUsuarios;
import usuarios.ExcepcionUsuario;
import usuarios.MetodoMensajeria;
import videojuego.ControladorVideoJuego;
import videojuego.ExcepcionDescripcion;
import videojuego.ExcepcionCopia;


/**
 * Clase con un main() de pruebas para la iteración 0, entregada por el profesor.
 *
 */
public class PruebasTiendaVideojuegos1 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros en línea de comandos, pero se ignoran.
	 */
	public static void main(String[] args) {

		//Crea una instancia de controlador de videojuegos
		ControladorVideoJuego cvj = new ControladorVideoJuego();
		
		//Crea una instancia de controlador de usuarios
		ControladorUsuarios cu = new ControladorUsuarios(cvj);
		//Crea una instancia de controlador de alquileres

		////////////////////////////////////////////////////////
		// CASOS DE USO PREVIOS
		////////////////////////////////////////////////////////	
		try {
			//Creo un formateador de fechas para crear dos fechas auxiliares con valores concretos
			SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yy");
			Date nacimiento1 = formateador.parse("01/01/01");
			Date nacimiento2 = formateador.parse("02/02/02");

			// Caso de uso "crear socio"
			cu.crearSocio("edugom", "miclave", "Eduardo", "Gómez Sánchez", nacimiento1, "00000000A", "666666666", "edugom@tel.uva.es", MetodoMensajeria.SMS);
			cu.crearSocio("mperez", "suclave", "María", "Pérez Juárez", nacimiento2, "11111111B", "666777777", "mperez@tel.uva.es", MetodoMensajeria.CORREO);

			// Caso de uso "ingresar dinero en tarjeta"
			cu.realizarIngresoEnTarjetaSocio("edugom", (float)80.00);
			cu.realizarIngresoEnTarjetaSocio("mperez", (float)80.00);

		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionTarjeta et) {
			//Si se llega hasta aquí alguna operación con tiendavideojuegos.tarjetas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + et.getConcepto() + "', por la siguiente causa: " + et.getCausa().toString());
		} catch (ParseException pe) {
			//Si se llega hasta aquí es que no se han podido leer las cadenas de fechas en la creación de las fechas de nacimiento
			System.out.println("Ha fallado la creación de las fechas de nacimiento");
		}

		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE ÉXITO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 1 - ESCENARIOS DE ÉXITO");
		System.out.println("===============================================");

		//Variables auxiliares para tener listados y fichas
		List<String> listado;
		String ficha;

		try {
			// Caso de uso "crear descripción de videojuego"
			System.out.println("\nCreo seis descripciones de videojuegos");
			cvj.crearDescripcionVideoJuego("God of War 3", "PlayStation 3", Year.of(2010), "ACCION", 18, "00000000", "SCE Santa Monica Studio", (float)50.0);
			cvj.crearDescripcionVideoJuego("Pro Evolution Soccer 2015","XBox One",Year.of(2014),"DEPORTES", 3,"11111111", "Konami",(float)3.0);
			cvj.crearDescripcionVideoJuego("Gran Turismo","PlayStation",Year.of(1997),"SIMULACION",0,"22222222",  "Polyphony Digital",     (float)3.0);
			cvj.crearDescripcionVideoJuego("Dragon Ball: Raging Blast","XBox 360",Year.of(2010),"ACCION",12,"33333333",  "Spike",     (float)5.0);
			cvj.crearDescripcionVideoJuego("Minecraft","Wii U",Year.of(2011),"ESTRATEGIA",7,"44444444A",  "Mojang AB",     (float)2.0);
			cvj.crearDescripcionVideoJuego("Minecraft","PlayStation 3",Year.of(2011),"ESTRATEGIA",7,"44444444B",  "Mojang AB",  (float)2.0);

			//Función de listar, que forma parte de los casos de uso "ver descripción de videojuego", "modificar descripción de videojuego" o "borrar descripción de videojuego"
			System.out.println("\nListo las descripciones de videojuego existentes");
			listado = cvj.listarDescripciones();
			for(String s : listado) {
				System.out.println(s);
			}

			// Caso de uso "ver descripción de videojuego"
			System.out.println("\nMuestro los datos completos de la descripción de videojuego con identificador '00000000'");
			ficha = cvj.verDescripcionVideoJuego("00000000");
			System.out.println(ficha);

			//Caso de uso "modificar descripción de videojuego"
			System.out.println("\nModifico el género y el precio de la descripción de videojuego con identificador '00000000'");
			cvj.modificarDescripcionVideoJuego("God of War 3", "PlayStation 3", Year.of(2010), "ACCION", 18, "00000000", "SCE Santa Monica Studio", (float)5.0);

			// Caso de uso "ver descripción de videojuego" otra vez, para ver que la modificación ha tenido éxito
			System.out.println("\nMuestro de nuevo los datos completos de la descripción de videojuego con identificador '00000000'");
			ficha = cvj.verDescripcionVideoJuego("00000000");
			System.out.println(ficha);

			//Caso de uso "eliminar descripción de videojuego"
			System.out.println("\nElimino la descripción de videojuego con identificador '00000000'");
			cvj.eliminarDescripcionVideoJuego("00000000");

			//Listo de nuevo, para comprobar que la eliminación ha sido efectiva
			System.out.println("\nListo de nuevo las descripciones de videojuego existentes");
			listado = cvj.listarDescripciones();
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "crear copia de videojuego"
			System.out.println("\nCreo algunas copias de videojuegos");
			cvj.crearCopiaVideoJuego("11111111"); 				//Dos copias del PES2015
			cvj.crearCopiaVideoJuego("11111111"); 				
			cvj.crearCopiaVideoJuego("33333333");				//Una copia del Dragon Ball
			cvj.crearCopiaVideoJuego("44444444A"); 				//Tres copias del Mincraft para Wii
			cvj.crearCopiaVideoJuego("44444444A"); 				
			cvj.crearCopiaVideoJuego("44444444A");
			cvj.crearCopiaVideoJuego("44444444B");				//Una copia del Mincraft para PS3

			//Función de listar, que forma parte de los casos de uso "ver copia de videojuego", "modificar copia de videojuego" o 
			//"borrar copia de videojuego", además de casos de uso de alquilar
			System.out.println("\nListo las copias de videojuego existentes para la descripción con identificador '44444444A'");
			listado = cvj.listarCopias("44444444A");
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "ver copia de videojuego"
			//NOTA: dependiendo del diseño que se haya seguido, puede ser necesario pasar al controlador el id del videojuego y el id de la copia, o sólo uno de los dos. Debe cambiarse la invocación como proceda
			System.out.println("\nMuestro los datos de la copia de videojuego con identificador '44444444A-1'");
			ficha = cvj.verCopiaVideoJuego("44444444A-1","44444444A");
			System.out.println(ficha);
			
			//Caso de uso "alquilar videojuego"
			//NOTA: dependiendo del diseño que se haya seguido, puede ser necesario pasar al controlador el id del videojuego y el id de la copia, o sólo uno de los dos. 
			//NOTA: También puede ocurrir que este mensaje se diriga a otro controlador
			//NOTA: Debe cambiarse esta invocación como proceda (y todas las relacionadas)
			
			System.out.println("\nEl usuario 'edugom' va a alquilar las copias '11111111-2' y '44444444A-1' durante 2 días");
			cu.crearAlquiler("edugom","11111111","11111111-2", 2);
			cu.crearAlquiler("edugom","44444444A","44444444A-1", 2);
			System.out.println("\nLa usuaria 'mperez' va a alquilar la copia '44444444A-2' durante 4 días");
			cu.crearAlquiler("mperez","44444444A","44444444A-2", 4);
			
			//Caso de uso "listar alquileres socio"
			System.out.println("\nListo los alquileres del usuario 'edugom'");
			listado = cu.listarAlquileresSocio("edugom");
			for(String s : listado) {
				System.out.println(s);
			}
			

		} catch (ExcepcionTarjeta et){
			
		} catch (ExcepcionCopia ec){
			
		} catch (ExcepcionDescripcion ed){
			
		}
			
	}

}