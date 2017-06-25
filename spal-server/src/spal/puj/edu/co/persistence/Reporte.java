/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.persistence;

/**
 * Reporte is the class that is in charge to stock the information related with
 * user's reports.
 *
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class Reporte {

	/**
	 * The name of the user.
	 */
	String nombre;
	/**
	 * ID of the tutor.
	 */
	int tutor;
	/**
	 * ID of the Activity.
	 */
	int actividad;
	/**
	 * Number that represents whether if the activity was skipped or not.
	 */
	int esc;
	/**
	 * The user answer to the activity.
	 */
	String respuesta;
	/**
	 * The word that conforms the activity.
	 */
	String palabra;
	/**
	 * Activity difficulty.
	 */
	int dificultad;

	/**
	 * Primary class contructor.
	 * 
	 * @param nombre
	 *            The name of the user.
	 * @param tutor
	 *            ID of the tutor.
	 * @param actividad
	 *            ID of the Activity.
	 * @param esc
	 *            ID of the Scenario.
	 * @param respuesta
	 *            The user answer to the activity.
	 * @param palabra
	 *            The word that conforms the activity.
	 * @param dificultad
	 *            Activity difficulty.
	 */
	public Reporte(String nombre, int tutor, int actividad, int esc, String respuesta, String palabra, int dificultad) {
		this.nombre = nombre;
		this.tutor = tutor;
		this.actividad = actividad;
		this.esc = esc;
		this.respuesta = respuesta;
		this.palabra = palabra;
		this.dificultad = dificultad;
	}

	/**
	 * Default class contructor.
	 */
	public Reporte() {

	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the tutor
	 */
	public int getTutor() {
		return tutor;
	}

	/**
	 * @param tutor
	 *            the tutor to set
	 */
	public void setTutor(int tutor) {
		this.tutor = tutor;
	}

	/**
	 * @return the actividad
	 */
	public int getActividad() {
		return actividad;
	}

	/**
	 * @param actividad
	 *            the actividad to set
	 */
	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

	/**
	 * @return the esc
	 */
	public int getEsc() {
		return esc;
	}

	/**
	 * @param esc
	 *            the esc to set
	 */
	public void setEsc(int esc) {
		this.esc = esc;
	}

	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the palabra
	 */
	public String getPalabra() {
		return palabra;
	}

	/**
	 * @param palabra
	 *            the palabra to set
	 */
	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

	/**
	 * @return the dificultad
	 */
	public int getDificultad() {
		return dificultad;
	}

	/**
	 * @param dificultad
	 *            the dificultad to set
	 */
	public void setDificultad(int dificultad) {
		this.dificultad = dificultad;
	}

}
