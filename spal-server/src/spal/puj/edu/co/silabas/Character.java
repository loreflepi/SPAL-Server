/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.silabas;

import javax.xml.bind.annotation.XmlType;

/**
 * Character is a utilitarian class that is in charge of stocking the
 * information related to the possible letters.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
@XmlType(propOrder = { "identificator", "score" })
public class Character {

	/**
	 * The character.
	 */
	private String identificator;

	/**
	 * The score that the character represents.
	 */
	private int score;

	/**
	 * Default constructor.
	 */
	public Character() {

	}

	/**
	 * @return the identificator
	 */
	public String getIdentificator() {
		return identificator;
	}

	/**
	 * @param identificator
	 *            the identificator to set
	 */
	public void setIdentificator(String identificator) {
		this.identificator = identificator;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

}
