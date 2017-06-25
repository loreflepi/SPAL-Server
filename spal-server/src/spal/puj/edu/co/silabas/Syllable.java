/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.silabas;

import javax.xml.bind.annotation.XmlType;

/**
 * Syllable is the class that is in charge to stock the information related with
 * syllables.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
@XmlType(propOrder = { "name", "structure", "factor" })
class Syllable {

	/**
	 * Difficulty name.
	 */
	private String name;
	/**
	 * The structure of the syllable (example: CVV or CCV), where C represents a
	 * Consonant and V a Vowel.
	 */
	private String structure;
	/**
	 * The score / weight that syllable has within SPAL.
	 */
	private int factor;

	/**
	 * Default constructor.
	 */
	public Syllable() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the structure
	 */
	public String getStructure() {
		return structure;
	}

	/**
	 * @param structure
	 *            the structure to set
	 */
	public void setStructure(String structure) {
		this.structure = structure;
	}

	/**
	 * @return the factor
	 */
	public int getFactor() {
		return factor;
	}

	/**
	 * @param factor
	 *            the factor to set
	 */
	public void setFactor(int factor) {
		this.factor = factor;
	}

}
