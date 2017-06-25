/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.reglas;

/**
 * Auxiliar is the class that is in charge to stock information that will be
 * sended to Client as JSON.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class Auxiliar {

	/**
	 * The ID of the next Activity.
	 */
	int nuevact;
	/**
	 * The status to know if next activity will be with buttons or keyboard.
	 */
	boolean dificultadact;
	/**
	 * Score that user got.
	 */
	int puntaje;
	/**
	 * The number of stars gotten by the user.
	 */
	int recompensa;

	/**
	 * Class constructor.
	 * 
	 * @param a
	 *            The Activity that will be packaged as JSON.
	 */
	public Auxiliar(Actividad a) {
		this.nuevact = a.getNuevact();
		this.dificultadact = a.isDificultadct();
		this.recompensa = a.getRecompensa();
		this.puntaje = a.getPuntaje();
	}

	/**
	 * Class constructor.
	 * 
	 * @param nuevact
	 *            The ID of the next Activity.
	 * @param avanzar
	 *            The status to know if next activity will be with buttons or
	 *            keyboard.
	 */
	public Auxiliar(int nuevact, boolean avanzar) {
		this.nuevact = nuevact;
		this.dificultadact = avanzar;
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
	 * @return the dificultadact
	 */
	public boolean isDificultadact() {
		return dificultadact;
	}

	/**
	 * @param dificultadact
	 *            the dificultadact to set
	 */
	public void setDificultadact(boolean dificultadact) {
		this.dificultadact = dificultadact;
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

}
