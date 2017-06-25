/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.silabas;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Difficulty is a utilitarian class that is in charge of stocking the
 * information of the difficulty of syllables. This information is saved on an
 * XML.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
@XmlRootElement
public class Difficulty {

	/**
	 * List of syllables that conforms the word.
	 */
	List<Syllable> syllables;

	/**
	 * Default class constructor.
	 */
	public Difficulty() {

	}

	/**
	 * @return the syllables
	 */
	public List<Syllable> getSyllables() {
		return syllables;
	}

	/**
	 * @param syllables
	 *            the syllables to set
	 */
	@XmlElement(name = "syllable")
	public void setSyllables(List<Syllable> syllables) {
		this.syllables = syllables;
	}

}
