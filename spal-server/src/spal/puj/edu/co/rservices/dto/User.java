/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.rservices.dto;

import java.sql.Date;

/**
 * User is the class that is in charge to stock the information related with
 * users.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class User {

	/**
	 * The name of the user.
	 */
	String nombre;
	/**
	 * Lastname of user.
	 */
	String apellido;
	/**
	 * Username.
	 */
	String user;
	/**
	 * The user password.
	 */
	String pass;
	/**
	 * The selected color for the GUI.
	 */
	int color;
	/**
	 * Selected avatar.
	 */
	int avatar;
	/**
	 * The tutor ID in charge of the user.
	 */
	int tutor;
	/**
	 * The genre of the user (1 male / 2 female).
	 */
	int genero;
	/**
	 * The font type selected by user.
	 */
	int font;
	/**
	 * Birth date of the user.
	 */
	Date cumple;

	/**
	 * Default constructor of the class.
	 */
	public User() {

	}

	/**
	 * Class constructor.
	 * 
	 * @param nombre
	 *            The name of the user.
	 * @param apellido
	 *            The lastname of the user.
	 * @param tutor
	 *            The tutor ID in charge of the user.
	 */
	public User(String nombre, String apellido, int tutor) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.tutor = tutor;
	}

	/**
	 * Class constructor.
	 * 
	 * @param nombre
	 *            The name of the user.
	 * @param apellido
	 *            The lastname of the user.
	 */
	public User(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
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
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	/**
	 * @return the avatar
	 */
	public int getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(int avatar) {
		this.avatar = avatar;
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
	 * @return the genero
	 */
	public int getGenero() {
		return genero;
	}

	/**
	 * @param genero
	 *            the genero to set
	 */
	public void setGenero(int genero) {
		this.genero = genero;
	}

	/**
	 * @return the font
	 */
	public int getFont() {
		return font;
	}

	/**
	 * @param font
	 *            the font to set
	 */
	public void setFont(int font) {
		this.font = font;
	}

	/**
	 * @return the cumple
	 */
	public Date getCumple() {
		return cumple;
	}

	/**
	 * @param cumple
	 *            the cumple to set
	 */
	public void setCumple(Date cumple) {
		this.cumple = cumple;
	}

}