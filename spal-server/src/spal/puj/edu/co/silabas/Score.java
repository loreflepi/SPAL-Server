/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.silabas;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Score is a utilitarian class that is in charge of calculate the complexity
 * and the score of an Activity.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class Score {

	/**
	 * The original word String.
	 */
	private static String word;
	/**
	 * The answer given by the user.
	 */
	private static String answer;

	/**
	 * Default class constructor.
	 */
	public Score() {

	}

	/**
	 * Calculates the complexity of the word, search the mayor syllable
	 * difficulty.
	 * 
	 * @param word
	 *            The word to calculate complexity.
	 * @return The maximum difficulty.
	 */
	public int calculateComplexity(String word) {
		Difficulty dif = readXMLDifficulty();
		Letter ltr = readXMLLetters();
		String result = null;
		String res[] = word.split("-");
		int maximum = -1;
		for (String s : res) {
			System.out.println(s);
			String structure = getStructure(s);
			boolean found_syllable = false;
			for (Syllable syllable : dif.getSyllables()) {
				if (!found_syllable && syllable.getStructure().toLowerCase().equalsIgnoreCase(structure)) {
					if (syllable.getFactor() > maximum) {
						maximum = syllable.getFactor();
					}
					found_syllable = true;
				}
			}
		}
		System.out.println("ID Maximo: " + maximum);
		return maximum;
	}

	/**
	 * Class constructor.
	 * 
	 * @param word
	 *            The word of the Activity.
	 * @param answer
	 *            The answer given by the user.
	 */
	public Score(String word, String answer) {
		this.word = stripAccents(word).toLowerCase();
		this.answer = stripAccents(answer).toLowerCase();
		Silabear();
	}

	/**
	 * Read the XML named Letters that contains the information related to
	 * Letters.
	 * 
	 * @return Letter object with the score and the character information.
	 */
	private Letter readXMLLetters() {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Letter.class);
			Unmarshaller ums = ctx.createUnmarshaller();
			Letter ltr = (Letter) ums.unmarshal(new File("xml-spal/Letters.xml"));
			return ltr;
		} catch (JAXBException ex) {
			System.out.println("Falló lectura del XML");
			Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	/**
	 * Read the XML named Letters that contains the information related to
	 * Difficulty.
	 * 
	 * @return Difficulty object with the syllables and it's difficulty..
	 */
	private Difficulty readXMLDifficulty() {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Difficulty.class);
			Unmarshaller ums = ctx.createUnmarshaller();
			Difficulty dif = (Difficulty) ums.unmarshal(new File("xml-spal/Difficulties.xml"));
			return dif;
		} catch (JAXBException ex) {
			System.out.println("Falló lectura del XML");
			Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	// Método que obtiene la palabra
	/**
	 * @deprecated Gets the word. Separates the word, in case of being several
	 *             and inserts them in a list of Strings.
	 * @param cadena
	 * @return
	 */
	private ArrayList<String> getPalabras(String cadena) {
		ArrayList<String> palabras = new ArrayList<String>();
		String palabra = "";
		cadena = cadena.trim().toLowerCase() + " ";
		char[] c = cadena.toCharArray();
		int i;
		for (i = 0; i < cadena.length(); i++) {
			if (c[i] == ' ') {
				palabras.add(palabra);
				palabra = "";
			} else
				palabra = palabra + String.valueOf(c[i]);
		}
		return palabras;
	}

	// Método que permite separar por sílabas la palabra
	/**
	 * Make the division of syllables.
	 * 
	 * @return An ArrayList with the score of the user and the maximum possible
	 *         score.
	 */
	public ArrayList<Integer> Silabear() {
		SeparaSilabas s = new SeparaSilabas();
		ArrayList<Integer> total = new ArrayList<Integer>();
		s.setCadena(word);
		try {
			total = maximumScore(s.silabear());
			return total;
		} catch (JAXBException ex) {
			Logger.getLogger(Score.class.getName()).log(Level.SEVERE, null, ex);
		}
		return total;
	}

	// Método que permite obtener el puntaje máximo
	/**
	 * Gets the maximum possible score.
	 * 
	 * @param silabas
	 *            Word divided by syllables with the sign -.
	 * @return An ArrayList with the score of the user and the maximum possible
	 *         score.
	 * @throws JAXBException
	 *             Coundn't read the XML files.
	 */
	private ArrayList<Integer> maximumScore(String silabas) throws JAXBException {
		Difficulty dif = readXMLDifficulty();
		Letter ltr = readXMLLetters();
		ArrayList<Integer> puntajes = new ArrayList();
		String result = null;
		String res[] = silabas.split("-");
		int word_length = 0;
		int total_answer_score = 0;
		int total_word_score = 0;
		for (String s : res) {
			int factor = 13;
			String structure = getStructure(s);
			boolean found_syllable = false;
			for (Syllable syllable : dif.getSyllables()) {
				if (!found_syllable && syllable.getStructure().toLowerCase().equalsIgnoreCase(structure)) {
					factor = syllable.getFactor();

					found_syllable = true;
				}
			}
			int answer_score = 0;
			int word_score = 0;
			for (int j = 0; j < s.length(); j++) {
				boolean found = false;
				for (Character c : ltr.getCharacters()) {
					if (!found && c.getIdentificator().equalsIgnoreCase(s.charAt(j) + "")) {
						word_score += c.getScore();
						if (s.charAt(j) == answer.charAt(word_length)) {
							answer_score += c.getScore();
						}
						found = true;
					}
				}
				word_length++;
			}
			total_answer_score += (factor * answer_score);
			total_word_score += (factor * word_score);
		}
		puntajes.add(total_answer_score);
		puntajes.add(total_word_score);
		return puntajes;

	}

	/**
	 * Gets the syllabic structure of the word.
	 * 
	 * @param s
	 *            Word Syllable from which it is desired to obtain the
	 *            structure.
	 * @return
	 */
	private String getStructure(String s) {
		String structure = "";
		for (int i = 0; i < s.length(); i++) {
			char r = s.charAt(i);
			if (r == 'a' || r == 'e' || r == 'i' || r == 'o' || r == 'u') {
				structure += "V";
			} else {
				structure += "C";
			}
		}
		return structure;
	}

	/**
	 * Replaces all the special characters with accent mark with plain
	 * characters.
	 * 
	 * @param word Original word.
	 * @return The new String.
	 */
	public static String stripAccents(String word) {
		String original = "ÁáÉéÍíÓóÚúÜüÑñ";
		String replacement = "AaEeIiOoUuUuNn";
		char[] array = word.toCharArray();
		for (int index = 0; index < array.length; index++) {
			int pos = original.indexOf(array[index]);
			if (pos > -1) {
				array[index] = replacement.charAt(pos);
			}
		}
		return new String(array).toUpperCase();
	}
}