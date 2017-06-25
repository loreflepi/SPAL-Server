/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.reglas;

/**
 * Actividad is the class that is in charge to stock the information related
 * with activities.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class Actividad {

	/**
	 * Unique ID of activity.
	 */
	int id_actividad;
	/**
	 * Number that represents whether if the activity was approved (1) or not
	 * (0).
	 */
	int aprobado;
	/**
	 * The Activity word.
	 */
	String palabra;
	/**
	 * The ID difficulty.
	 */
	int dificultad;
	/**
	 * Number that represents whether if the activity was skipped or not.
	 */
	int saltar;
	/**
	 * The ID of the next Activity.
	 */
	int nuevact;
	/**
	 * The ID difficulty of the next Activity.
	 */
	boolean dificultadct;
	/**
	 * Score that user got.
	 */
	int puntaje;
	/**
	 * The number of stars gotten by the user.
	 */
	int recompensa;

	/**
	 * Default class constructor.
	 */
	public Actividad() {

	}

	/**
	 * Class constructor.
	 * 
	 * @param nuevact
	 *            The ID of the next Activity.
	 * @param pasar
	 *            The ID difficulty of the next Activity.
	 * @param recompensa
	 *            The number of stars gotten by the user.
	 * @param puntaje
	 *            Score that user got.
	 */
	public Actividad(int nuevact, boolean pasar, int recompensa, int puntaje) {
		this.nuevact = nuevact;
		this.dificultadct = pasar;
		this.recompensa = recompensa;
		this.puntaje = puntaje;
	}

	/**
	 * Class constructor.
	 * 
	 * @param palabra
	 *            The Activity word.
	 * @param dificultad
	 *            The ID difficulty.
	 */
	public Actividad(String palabra, int dificultad) {
		this.palabra = palabra;
		this.dificultad = dificultad;
	}

	/**
	 * Class constructor.
	 * 
	 * @param aprobado
	 *            Number that represents whether if the activity was approved
	 *            (1) or not (0).
	 * @param dificultad
	 *            The ID difficulty.
	 * @param actividad
	 *            Unique ID of activity.
	 * @param nuevaAct
	 *            The ID of the next Activity.
	 * @param puntaje
	 *            Score that user got.
	 * @param recompensa
	 *            The number of stars gotten by the user.
	 * @param nuevadif
	 *            The ID difficulty of the next Activity.
	 */
	public Actividad(int aprobado, int dificultad, int actividad, int nuevaAct, int puntaje, int recompensa,
			boolean nuevadif) {
		this.aprobado = aprobado;
		this.dificultad = dificultad;
		this.id_actividad = actividad;
		this.nuevact = nuevaAct;
		this.puntaje = puntaje;
		this.recompensa = recompensa;
		this.dificultadct = nuevadif;
	}

	/**
	 * Class constructor.
	 * 
	 * @param nuevact
	 *            The ID of the next Activity.
	 * @param dificultad
	 *            The ID difficulty.
	 */
	public Actividad(int nuevact, int dificultad) {
		this.nuevact = nuevact;
		this.dificultad = dificultad;
	}

	/**
	 * Class constructor.
	 * 
	 * @param id_actividad
	 *            Unique ID of activity.
	 * @param aprobado
	 *            Number that represents whether if the activity was approved
	 *            (1) or not (0).
	 * @param palabra
	 *            The Activity word.
	 * @param dificultad
	 *            The ID difficulty.
	 * @param nuevact
	 *            The ID of the next Activity.
	 */
	public Actividad(int id_actividad, int aprobado, String palabra, int dificultad, int nuevact) {
		this.id_actividad = id_actividad;
		this.aprobado = aprobado;
		this.palabra = palabra;
		this.dificultad = dificultad;
		this.nuevact = nuevact;
	}

	/**
	 * @return the id_actividad
	 */
	public int getId_actividad() {
		return id_actividad;
	}

	/**
	 * @param id_actividad
	 *            the id_actividad to set
	 */
	public void setId_actividad(int id_actividad) {
		this.id_actividad = id_actividad;
	}

	/**
	 * @return the aprobado
	 */
	public int getAprobado() {
		return aprobado;
	}

	/**
	 * @param aprobado
	 *            the aprobado to set
	 */
	public void setAprobado(int aprobado) {
		this.aprobado = aprobado;
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

	/**
	 * @return the saltar
	 */
	public int getSaltar() {
		return saltar;
	}

	/**
	 * @param saltar
	 *            the saltar to set
	 */
	public void setSaltar(int saltar) {
		this.saltar = saltar;
	}

	/**
	 * @return the nuevact
	 */
	public int getNuevact() {
		return nuevact;
	}

	/**
	 * @param nuevact
	 *            the nuevact to set
	 */
	public void setNuevact(int nuevact) {
		this.nuevact = nuevact;
	}

	/**
	 * @return the dificultadct
	 */
	public boolean isDificultadct() {
		return dificultadct;
	}

	/**
	 * @param dificultadct
	 *            the dificultadct to set
	 */
	public void setDificultadct(boolean dificultadct) {
		this.dificultadct = dificultadct;
	}

	/**
	 * @return the puntaje
	 */
	public int getPuntaje() {
		return puntaje;
	}

	/**
	 * @param puntaje
	 *            the puntaje to set
	 */
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	/**
	 * @return the recompensa
	 */
	public int getRecompensa() {
		return recompensa;
	}

	/**
	 * @param recompensa
	 *            the recompensa to set
	 */
	public void setRecompensa(int recompensa) {
		this.recompensa = recompensa;
	}

}
