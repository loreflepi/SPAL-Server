/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.reglas;

import java.util.ArrayList;
import java.util.Random;

import spal.puj.edu.co.persistence.BDConnector;
import spal.puj.edu.co.silabas.Score;

/**
 * Regla is the class that is in charge of generating the response for the
 * client. Assigns activity to user.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class Regla {

	/**
	 * Allows to obtain the appropriate difficulty of the student by the answer
	 * that he/she gives.
	 * 
	 * @param palabra
	 *            Original Activity word string.
	 * @param respuesta
	 *            Answer string given by the student.
	 * @param saltar
	 *            Number that represents whether if the activity was skipped (1)
	 *            or not (0).
	 * @return List of integers with the next information: In position 0 the
	 *         score that student got; in position 1 the appropriate difficulty
	 *         for the student and in the position 2 the reward that student
	 *         got.
	 */
	public ArrayList<Integer> nivel(String palabra, String respuesta, int saltar) {
		ArrayList<Integer> retorno = new ArrayList<Integer>();
		if (saltar == 1) {
			retorno.add(0);
			retorno.add(2);
			retorno.add(0);
			return retorno;
		}
		Score p = new Score(palabra, respuesta);
		ArrayList<Integer> total = new ArrayList<Integer>();
		total = p.Silabear();
		int max = 0;
		int min = 0;
		int med = 0;
		int puntaje = 0;
		System.out.println("Puntaje respuesta: " + total.get(0));
		System.out.println("Puntaje total palabra: " + total.get(1));
		retorno.add(total.get(0));
		max = (int) (total.get(1) * .80);
		min = (int) (total.get(1) * .10);
		med = (int) (total.get(1) * .50);
		if (total.get(0).compareTo(total.get(1)) == 0) {
			retorno.add(3);
		}
		if (total.get(0) <= min) {
			retorno.add(1);
		}
		if (total.get(0) >= med && total.get(0) < total.get(1)) {
			retorno.add(9);
		}
		if (total.get(0) > min && total.get(0) < med) {
			retorno.add(2);
		}

		if (total.get(0) >= (int) total.get(1) * 0.50 && total.get(0) < (int) total.get(1) * 0.60) {
			retorno.add(-1);
			return retorno;
		}
		if (total.get(0) >= (int) total.get(1) * 0.40 && total.get(0) < (int) total.get(1) * 0.50) {
			retorno.add(-2);
			return retorno;
		}
		if (total.get(0) >= (int) total.get(1) * 0.30 && total.get(0) < (int) total.get(1) * 0.40) {
			retorno.add(-3);
			return retorno;
		}
		if (total.get(0) >= (int) total.get(1) * 0.20 && total.get(0) < (int) total.get(1) * 0.30) {
			retorno.add(-4);
			return retorno;
		}
		if (total.get(0) < (int) total.get(1) * 0.20) {
			retorno.add(-5);
			return retorno;
		}
		if (total.get(0) >= (int) total.get(1) * 0.60 && total.get(0) < (int) total.get(1) * 0.70) {
			retorno.add(1);
			return retorno;
		}
		if (total.get(0) >= (int) total.get(1) * 0.70 && total.get(0) < (int) total.get(1) * 0.80) {
			retorno.add(2);
			return retorno;
		}
		if (total.get(0) >= (int) total.get(1) * 0.80 && total.get(0) < (int) total.get(1) * 0.90) {
			retorno.add(3);
			return retorno;
		}
		if (total.get(0) >= (int) total.get(1) * 0.80 && total.get(0) < (int) total.get(1) * 0.99) {
			retorno.add(4);
			return retorno;
		}
		if (total.get(0).compareTo(total.get(1)) == 0) {
			retorno.add(5);
			return retorno;
		}
		return null;
	}

	/**
	 * Assign an activity to the user in an scenario where he/she has never
	 * entered.
	 * 
	 * @param user
	 *            User ID.
	 * @param escenario
	 *            Scenario ID.
	 * @return The Activity for the user to develop.
	 */
	public Actividad nuevoU(int user, int escenario) {
		BDConnector login = new BDConnector();
		Actividad nueva = new Actividad();
		nueva = login.actividadMinima(escenario);
		return nueva;
	}

	/**
	 * Calculates the penalty for the use of helps.
	 * 
	 * @param ayuda
	 *            Integer number that represents the number of helps.
	 * @param pista
	 *            Boolean that represents whether if the user played the clue
	 *            (true) or not (false).
	 * @return The penalization.
	 */
	public int Ayudas(int ayuda, boolean pista) {
		if (ayuda == 0 && pista == false) {
			return 0;
		}
		if (ayuda == 1 && pista == false) {
			return 2;
		}
		if (ayuda == 1 && pista == true) {
			return 3;
		}
		if (ayuda == 2 && pista == false) {
			return 2;
		}
		if (ayuda == 2 && pista == true) {
			return 3;
		}
		if (ayuda == 3 && pista == false) {
			return 4;
		}
		if (ayuda == 3 && pista == true) {
			return 5;
		}
		return -1;
	}

	/**
	 * Finds an appropriate new activity given the progress of the user.
	 * 
	 * @param palabra
	 *            The word of the activity he/she did.
	 * @param respuesta
	 *            The answer made by user.
	 * @param dificultad
	 *            The difficulty of the word.
	 * @param actividad
	 *            The ID of the activity.
	 * @param saltar
	 *            Number that represents whether if the activity was skipped (1)
	 *            or not (0).
	 * @param esc
	 *            Scenario ID.
	 * @param ayuda
	 *            The helps received by the application.
	 * @param pista
	 *            Boolean that represents whether if the user played the clue
	 *            (true) or not (false).
	 * @return The Activity for the user to develop.
	 */
	public Actividad actividad(String palabra, String respuesta, int dificultad, int actividad, int saltar, int esc,
			int ayuda, boolean pista) {
		if (respuesta.contains("Ã±")) {
			respuesta = respuesta.replace("Ã±", "n");
		}
		int nueva_act = 0;
		BDConnector bd = new BDConnector();
		boolean dificultadact = false;
		Actividad acti = new Actividad();
		ArrayList<Integer> nivel = nivel(palabra, respuesta, saltar);
		int ayu = Ayudas(ayuda, pista);
		if (nivel.get(1) == 1) {
			int dificultadminima = bd.getMinimaDificultad(esc);
			if (dificultadminima == dificultad) {
				nueva_act = dificultad;
			} else {
				nueva_act = dificultad - 1;
			}
			acti.setAprobado(1);
			int nueva = bd.darActividadMenor(nueva_act, esc);
			acti.setDificultad(dificultad);
			acti.setNuevact(nueva);
			acti.setPuntaje(nivel.get(0) - ayu);
			acti.setRecompensa(nivel.get(2));
			dificultadact = false;
			acti.setDificultadct(dificultadact);
		}
		if (nivel.get(1) == 2) {
			if (saltar == 1) {
				if (bd.isActividadSimilar(dificultad, esc) && bd.countActividadSimilar(dificultad, esc)>1) {
					nueva_act = bd.darActividadIgual(dificultad, esc);
					while (nueva_act == actividad) {
						nueva_act = bd.darActividadIgual(dificultad, esc);
					}
				} else {
					nueva_act = actividad;
				}
			} else {
				nueva_act = actividad;
			}
			acti.setAprobado(2);
			acti.setDificultad(dificultad);
			acti.setNuevact(nueva_act);
			acti.setPuntaje(nivel.get(0) - ayu);
			acti.setRecompensa(nivel.get(2));
			dificultadact = false;
			acti.setDificultadct(dificultadact);
		}

		if (nivel.get(1) == 9) {
			nueva_act = dificultad;
			acti.setAprobado(9);
			acti.setDificultad(dificultad);
			acti.setNuevact(actividad);
			acti.setPuntaje(nivel.get(0) - ayu);
			acti.setRecompensa(nivel.get(2));
			dificultadact = true;
			acti.setDificultadct(dificultadact);
		}

		if (nivel.get(1) == 3) {
			Random r = new Random();
			int numeroActividades=bd.countActividadSimilar(dificultad, esc);
			int ran=1;
			if(numeroActividades>0){
				ran=r.nextInt(numeroActividades+1);
			}
			if(ran==1 || bd.isActividadSimilar(dificultad, esc)==false){
				int dificultadmax = bd.getMaximaDificultad(esc);
				if (dificultadmax == dificultad) {
					nueva_act = dificultad;
				} else {
					nueva_act = dificultad + 1;
				}
				int nueva = bd.darActividadSuperior(nueva_act, esc);
				acti.setNuevact(nueva);
			}
			else{
				if (saltar == 0) {
					if (bd.isActividadSimilar(dificultad, esc)) {
						nueva_act = bd.darActividadIgual(dificultad, esc);						
						while (nueva_act == actividad) {
							nueva_act = bd.darActividadIgual(dificultad, esc);
						}
					} else {
						nueva_act = actividad;
					}
				} else {
					nueva_act = actividad;
				}
				acti.setNuevact(nueva_act);
			}
			acti.setAprobado(3);
			acti.setDificultad(dificultad);
			
			acti.setPuntaje(nivel.get(0) - ayu);
			acti.setRecompensa(nivel.get(2));
			dificultadact = false;
			acti.setDificultadct(dificultadact);
		}
		System.out.println(acti.getPuntaje());
		return new Actividad(acti.getAprobado(), acti.getDificultad(), actividad, acti.getNuevact(), acti.getPuntaje(),
				acti.getRecompensa(), acti.isDificultadct());
	}

}
