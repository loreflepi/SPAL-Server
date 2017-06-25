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
 * Letter is a utilitarian class that is in charge of stocking the information
 * of the characters that can conform a word. This information is saved on an
 * XML.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
@XmlRootElement
public class Letter {

	/**
	 * List of characters that conforms a word.
	 */
	private List<Character> characters;

	/**
	 * Default class constructor.
	 */
	public Letter() {
	}

	/**
	 * @return the characters
	 */
	public List<Character> getCharacters() {
		return characters;
	}

	/**
	 * @param characters
	 *            the characters to set
	 */
	@XmlElement(name = "character")
	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

}
