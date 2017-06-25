package spal.puj.edu.co.silabas;

/**
 * SeparaSilabas is the class in charge of making the syllabic division, it has
 * the rules to complete this task.
 * 
 * @author Edmond Duke See <a href=
 *         "http://www.lawebdelprogramador.com/codigo/Java/1912-Separador-de-silabas.html">Orginal</a>
 * 
 *         Adapted by Lorena María Pinzón Acevedo and Esteban Villafrades.
 */
public class SeparaSilabas {

	/**
	 * The word that will be divided.
	 */
	private String cadena;

	/**
	 * Principal class constructor.
	 * 
	 * @param x
	 *            The word that will be divided.
	 */
	public SeparaSilabas(String x) {
		cadena = x;
	}

	/**
	 * Default constructor.
	 */
	public SeparaSilabas() {
		cadena = "";
	}

	/**
	 * Search letter by letter for the possible vowels.
	 * 
	 * @param c
	 *            The character that will be searched.
	 * @return The new ASCII code to make the proper division.
	 */
	private static int letra(char c) {
		int i = -1;
		int ascii;
		ascii = (int) c;
		if (ascii != -1) {
			switch (ascii) {
			case 97: // a
				i = 1;
				break;
			case 101: // e
				i = 2;
				break;
			case 104: // h
				i = 6;
				break;
			case 105: // i
				i = 4;
				break;
			case 111: // o
				i = 3;
				break;
			case 117: // u
				i = 5;
				break;
			case 225: // á
				i = 1;
				break;
			case 233: // é
				i = 2;
				break;
			case 237: // í
				i = 4;
				break;
			case 243: // ó
				i = 3;
				break;
			case 250: // ú
				i = 5;
				break;
			case 252: // ü
				i = 5;
				break;
			default:
				i = 19;
				break;
			}
		}
		return i;
	}

	/**
	 * Principal class that decides what kind of syllabic division should do.
	 * 
	 * @param str
	 *            The word that will be divided.
	 * @return The new String with syllabic division.
	 */
	private static String silaba(String str) {
		String temp = "";
		String s = "";
		char x, y, z;
		if (str.length() < 3) {
			if (str.length() == 2) {
				x = str.charAt(0);
				y = str.charAt(1);
				if (letra(x) < 6 && letra(y) < 6) {
					if (hiato(x, y))
						s = str.substring(0, 1);
					else
						s = str;
				} else
					s = str;
			} else
				s = str;
		} else {
			x = str.charAt(0);
			y = str.charAt(1);
			z = str.charAt(2);
			if (letra(x) < 6) {
				if (letra(y) < 6) {
					if (letra(z) < 6) {
						if (hiato(x, y)) {
							s = str.substring(0, 1);
						} else {
							if (hiato(y, z)) {
								s = str.substring(0, 2);
							} else {
								s = str.substring(0, 3);
							}
						}
					} else {
						if (hiato(x, y)) {
							s = str.substring(0, 1);
						} else {
							s = str.substring(0, 2);
						}
					}
				} else {
					if (letra(z) < 6) {
						if (letra(y) == 6) {
							if (hiato(x, z)) {
								s = str.substring(0, 1);
							} else {
								s = str.substring(0, 3);
							}
						} else {
							s = str.substring(0, 1);
						}
					} else {
						if (consonantes1(y, z)) {
							s = str.substring(0, 1);
						} else {
							s = str.substring(0, 2);
						}
					}
				}
			} else {
				if (letra(y) < 6) {
					if (letra(z) < 6) {
						temp = str.substring(0, 3);
						if (temp.equals("que") || temp.equals("qui") || temp.equals("gue") || temp.equals("gui")) {
							s = str.substring(0, 3);
						} else {
							if (hiato(y, z)) {
								s = str.substring(0, 2);
							} else {
								s = str.substring(0, 3);
							}
						}
					} else {
						s = str.substring(0, 2);
					}
				} else {
					if (letra(z) < 6) {
						if (consonantes1(x, y)) {
							s = str.substring(0, 3);
						} else {
							s = str.substring(0, 1);
						}
					} else {
						if (consonantes1(y, z)) {
							s = str.substring(0, 1);
						} else {
							s = str.substring(0, 1);
						}
					}
				}
			}
		}
		return s;
	}

	/**
	 * Gets the portion of the word that is one syllable.
	 * 
	 * @param str
	 *            The portion of word that has not been revised.
	 * @return The syllable substring.
	 */
	private static String silabaRest(String str) {
		String s2;
		s2 = silaba(str);
		return str.substring(s2.length());
	}

	/**
	 * Make checks with possible forms of hiatus to see which is the most
	 * indicated.
	 * 
	 * @param v
	 *            First letter that conforms the hiatus.
	 * @param v2
	 *            Second letter that conforms the hiatus.
	 * @return Whether the word is a hiatus or not.
	 */
	private static boolean hiato(char v, char v2) {
		boolean cer = false;
		if (letra(v) < 4) { // VA + ?
			if (letra(v2) < 4) // VA + VA
				cer = true;
			else { // VA+ VC
				if (v2 == 'í' || v2 == 'ú') {
					cer = true;
				} else {
					cer = false;
				}
			}
		} else { // VC + ?
			if (letra(v2) < 4) { // VC + VA
				if (v == 'í' || v == 'ú') {
					cer = true;
				} else {
					cer = false;
				}
			} else {// VC + VC
				if (v == v2) {
					cer = true;
				} else
					cer = false;
			}
		}
		return cer;
	}

	/**
	 * Check for double consonants.
	 * 
	 * @param a
	 *            The first consonant.
	 * @param b
	 *            The second consonant.
	 * @return Whether there are double consonants or not.
	 */
	private static boolean consonantes1(char a, char b) {
		boolean cer;
		cer = false;
		if (a == 'b' || a == 'c' || a == 'd' || a == 'f' || a == 'g' || a == 'p' || a == 'r' || a == 't') {
			if (b == 'r') {
				cer = true;
			}
		}
		if (a == 'b' || a == 'c' || a == 'f' || a == 'g' || a == 'p' || a == 't' || a == 'l') {
			if (b == 'l') {
				cer = true;
			}
		}
		if (b == 'h') {
			if (a == 'c') {
				cer = true;
			}
		}
		return cer;
	}

	/**
	 * Gets all the consonants of the word.
	 * 
	 * @param str
	 *            The original word.
	 * @return Whether the whole word is composed by consonants or not.
	 */
	private static boolean strConsonantes(String str) {
		boolean cer;
		int i, k;
		char c[];
		cer = false;
		k = 0;
		c = str.toCharArray();
		for (i = 0; i < str.length(); i++) {
			if (letra(c[i]) > 5) {
				k = k + 1;
			}
		}
		if (k == str.length()) {
			cer = true;
		}
		return cer;
	}

	/**
	 * Gets the possible combinations of vowel + vowel. Compare if is a hiatus
	 * or not.
	 * 
	 * @param s1
	 *            The original word.
	 * @param s2
	 *            Original word copy.
	 * @return Whether the word is a hiatus (false) or not (true).
	 */
	private static boolean strVVstr(String s1, String s2) {
		boolean cer;
		char c1, c2;
		c1 = s1.charAt(s1.length() - 1);
		c2 = s2.charAt(0);
		cer = false;
		if (letra(c1) < 6 && letra(c2) < 6) {
			if (hiato(c1, c2)) {
				cer = false;
			} else {
				cer = true;
			}
		}
		return cer;
	}

	/**
	 * Makes the syllabic division contemplating all possible cases.
	 * 
	 * @return The word divided by syllables.
	 */
	public String silabear() {
		String temp;
		String s = "";
		int i, k;
		k = cadena.length();
		temp = cadena;
		for (i = 0; i < k; i++) {
			temp = silaba(cadena);
			if (i == 0) {
				s = s + temp;
			} else {
				if (strConsonantes(temp)) {
					s = s + temp;
				} else {
					if (strVVstr(s, temp)) {
						s = s + temp;
					} else {
						if (strConsonantes(s)) {
							s = s + temp;
						} else {
							s = s + "-" + temp;
						}
					}
				}
			}
			i = i + temp.length() - 1;
			cadena = silabaRest(cadena);
		}
		return s;
	}

	/**
	 * @return the cadena
	 */
	public String getCadena() {
		return cadena;
	}

	/**
	 * @param cadena
	 *            the cadena to set
	 */
	public void setCadena(String cadena) {
		this.cadena = cadena;
	}

}
