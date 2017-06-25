/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.rservices.dto;

/**
 * Tutor is the class that is in charge to stock the information related with
 * tutors users.
 * 
 * @author Lorena Mar�a Pinz�n Acevedo
 * @author Esteban Villafrades Iannini
 */
public class Tutor {

	/**
	 * The tutor's username.
	 */
	String user;
	/**
	 * The tutor's password.
	 */
	String pass;
	/**
	 * Tutor's name.
	 */
	String nombre;
	/**
	 * Tutor's lastname.
	 */
	String apellido;
	/**
	 * Tutor's valid email.
	 */
	String mail;
	/**
	 * School were tutor works.
	 */
	int colegio;
	/**
	 * There are two types of tutors, Administrator and Tutor.
	 */
	int tipo;
	/**
	 * Represents whether if the tutor has access to SPAL or not, an
	 * Administrator accepts the request of tutor's registration.
	 */
	int Acceso;
	/**
	 * The autogenerated ID for the tutor.
	 */
	int id;
	/**
	 * Profile picture for the tutor.
	 */
	byte[] imagen;

	/**
	 * Class constructor.
	 * 
	 * @param tipo
	 *            The type of tutor user.
	 * @param id
	 *            ID for the tutor.
	 */
	public Tutor(int tipo, int id) {
		this.tipo = tipo;
		this.id = id;
	}

	/**
	 * Default constructor of the class.
	 */
	public Tutor() {

	}

	/**
	 * Class constructor
	 * 
	 * @param id
	 *            ID for the tutor.
	 * @param user
	 *            The tutor's username.
	 * @param nombre
	 *            Tutor's name.
	 * @param apellido
	 *            Tutor's lastname.
	 * @param mail
	 *            Tutor's valid email address.
	 * @param colegio
	 *            The school for which tutor works.
	 */
	public Tutor(int id, String user, String nombre, String apellido, String mail, int colegio) {
		this.id = id;
		this.user = user;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.colegio = colegio;
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
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the colegio
	 */
	public int getColegio() {
		return colegio;
	}

	/**
	 * @param colegio
	 *            the colegio to set
	 */
	public void setColegio(int colegio) {
		this.colegio = colegio;
	}

	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the acceso
	 */
	public int getAcceso() {
		return Acceso;
	}

	/**
	 * @param acceso
	 *            the acceso to set
	 */
	public void setAcceso(int acceso) {
		Acceso = acceso;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the imagen
	 */
	public byte[] getImagen() {
		return imagen;
	}

	/**
	 * @param imagen
	 *            the imagen to set
	 */
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

}
