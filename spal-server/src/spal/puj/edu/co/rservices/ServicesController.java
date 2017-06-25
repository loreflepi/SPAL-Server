/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.rservices;

import java.io.IOException;
import java.sql.Blob;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.drools.compiler.compiler.DroolsParserException;
import org.jboss.resteasy.util.Base64;
import sun.misc.BASE64Decoder;

import spal.puj.edu.co.persistence.BDConnector;
import spal.puj.edu.co.persistence.Reporte;
import spal.puj.edu.co.reglas.Actividad;
import spal.puj.edu.co.reglas.Auxiliar;
import spal.puj.edu.co.reglas.Regla;
import spal.puj.edu.co.rservices.dto.Tutor;
import spal.puj.edu.co.rservices.dto.User;
import spal.puj.edu.co.silabas.Score;
import spal.puj.edu.co.user.Administrador;
import spal.puj.edu.co.user.Mail;

/**
 * ServicesController is the class that is in charge of casting the objects to
 * JSON and the Web Services in general.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
@Path("/spal")
public class ServicesController {

	/**
	 * List of tutors, in first position we have the username and in the second
	 * one we got the password.
	 */
	public static ArrayList<String> tutor = new ArrayList<String>();

	/**
	 * Default constructor of the class.
	 */
	public ServicesController() {

	}

	/**
	 * GET method for tutor login.
	 * 
	 * @param user
	 *            Tutor username.
	 * @param pass
	 *            Tutor password.
	 * @return The object of the tutor who logins.
	 * @throws SQLException
	 *             The query fails / the tutor username and password didn't
	 *             match.
	 */
	@GET
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Tutor getLogin(@QueryParam("user") String user, @QueryParam("pass") String pass) throws SQLException {
		Tutor u = BDConnector.LoginTutor(user, pass);
		if (u.getId() != 0 && u.getTipo() != 0) {
			System.out.println("Correcto");
			u.setUser(user);
			u.setPass(pass);
			tutor.add(user);
			tutor.add(pass);
			if (u.getTipo() == 2) {
				System.out.println("Tutor");
			}
			if (u.getTipo() == 3) {
				System.out.println("Administrador");
			}
		} else {
			System.out.println("No autorizado");
		}
		return u;
	}

	/**
	 * GET method to register a tutor.
	 * 
	 * @param user
	 *            The username that is being registered (the wanted one).
	 * @param pass
	 *            The password for him/her.
	 * @param nombre
	 *            His/her name.
	 * @param apellido
	 *            His/her lastname.
	 * @param mail
	 *            Valid email address.
	 * @param colegio
	 *            The school where she/he works at.
	 * @return The object of the tutor who is being registered.
	 * @throws SQLException
	 *             The query fails.
	 */
	@GET
	@Path("/registro")
	@Produces(MediaType.APPLICATION_JSON)
	public Tutor getRegistro(@QueryParam("user") String user, @QueryParam("pass") String pass,
			@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido,
			@QueryParam("mail") String mail, @QueryParam("colegio") int colegio) throws SQLException {
		Tutor u = new Tutor();
		if (BDConnector.RegistroTutor(user, pass, nombre, apellido, mail, colegio) == true) {
			System.out.println("Correcto");
			u.setUser(user);
			u.setPass(pass);
			u.setApellido(apellido);
			u.setNombre(nombre);
			u.setMail(mail);
			u.setColegio(colegio);
		}
		return u;
	}

	/**
	 * GET method to register a user (student).
	 * 
	 * @param user
	 *            The username that is being registered (the wanted one).
	 * @param cumple
	 *            Date of birth of the student.
	 * @param nombre
	 *            His/her name.
	 * @param apellido
	 *            His/her lastname.
	 * @param genero
	 *            The genre of the student (1 male, 2 female).
	 * @param tut
	 *            The ID of the tutor in charge.
	 * @return The object of the user who is being registered.
	 * @throws SQLException
	 *             The query fails.
	 */
	@GET
	@Path("/insertar")
	@Produces(MediaType.APPLICATION_JSON)
	public User getInsertar(@QueryParam("user") String user, @QueryParam("cumple") Date cumple,
			@QueryParam("nombre") String nombre, @QueryParam("apellido") String apellido,
			@QueryParam("genero") int genero, @QueryParam("tutor") int tut) throws SQLException {
		User u = new User();
		Tutor t = new Tutor();
		t = BDConnector.LoginTutor(tutor.get(0), tutor.get(1));
		u.setTutor(t.getId());
		tut = u.getTutor();
		if (BDConnector.InsertarEstudiante(user, nombre, cumple, apellido, genero, tut) == true) {
			System.out.println("Correcto");
			u.setUser(user);
			u.setCumple(cumple);
			u.setApellido(apellido);
			u.setNombre(nombre);
			u.setGenero(genero);
			u.setTutor(t.getId());
		}
		return u;
	}

	/**
	 * Accepts a tutor's request (just for administrator).
	 * 
	 * @param id
	 *            The ID of the tutor to accept.
	 * @param email
	 *            The email of the tutor to send him/her the notification that
	 *            he have been accepted.
	 * @return The object of the tutor who is being registered.
	 * @throws SQLException
	 *             The query fails.
	 */
	@GET
	@Path("/administrador")
	@Produces(MediaType.APPLICATION_JSON)
	public Tutor getAdmin(@QueryParam("id") int id, @QueryParam("email") String email) throws SQLException {
		Tutor tu = new Tutor();
		if (BDConnector.AceptarTutor(id, email)) {
			tu.setId(id);
			tu.setMail(email);
		}
		return tu;
	}

	/**
	 * Charges the tutors that have not been accepted.
	 * 
	 * @return The List of tutors that have not been accepted yet.
	 * @throws SQLException
	 *             The query fails.
	 */
	@GET
	@Path("/admin")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Tutor> getAdmin() throws SQLException {
		ArrayList<Tutor> todos = BDConnector.TutorSinAceptar();
		if (todos != null) {
			System.out.println("Correcto");
		}
		return todos;
	}

	/**
	 * Change tutor's password.
	 * 
	 * @param email
	 *            Tutor's valid email from which she/he want to change the
	 *            password.
	 * @param contra
	 *            The new password to assign in the tutor's account.
	 * @return The object of the tutor who made the change.
	 * @throws SQLException
	 *             The query fails.
	 */
	@GET
	@Path("/cambio")
	@Produces(MediaType.APPLICATION_JSON)
	public Tutor getCambio(@QueryParam("email") String email, @QueryParam("contra") String contra) throws SQLException {
		Tutor t = new Tutor();
		if (BDConnector.Cambiarcontra(email, contra)) {
			System.out.println("Correcto");
			t.setPass(contra);
			t.setMail(email);
		}
		return t;
	}

	/**
	 * Gets the students in charge of a tutor.
	 * 
	 * @param nombreTutor
	 *            The name of the accepted tutor that is on charge of the
	 *            students and that is registrated in SPAL.
	 * @return The List of students.
	 * @throws SQLException
	 *             The query fails.
	 */
	@GET
	@Path("/estudiantes")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getEstudiantes(@QueryParam("tutor") String nombreTutor) throws SQLException {
		ArrayList<User> todos = BDConnector.todosEstudiantes(nombreTutor);
		if (todos != null) {
			System.out.println("Correcto");

		}
		return todos;
	}

	/**
	 * GET method that send an email to an specific email address.
	 * 
	 * @param email
	 *            Email address.
	 * @param asunto
	 *            The header of the message that will be sent.
	 * @param mensaje
	 *            The body of the message.
	 * @return An object of Administrator, which sends the mail.
	 */
	@GET
	@Path("/contacto")
	@Produces(MediaType.APPLICATION_JSON)
	public Administrador getContacto(@QueryParam("email") String email, @QueryParam("asunto") String asunto,
			@QueryParam("mensaje") String mensaje) {
		Mail mail = new Mail();
		Administrador admin = new Administrador();
		if (mail.sendMailContact(email, asunto, mensaje)) {
			System.out.println("Correcto");
			admin.setEmail(email);
			admin.setAsunto(asunto);
			admin.setMensaje(mensaje);
		} else {
			System.out.println("No se envió");
		}
		return admin;
	}

	/**
	 * POST method that consumes an XML, the idea is to convert a login image.
	 * 
	 * @param obj
	 *            A LinkedHashMap that has the username as key string and a
	 *            Base64 image as content.
	 * @throws IOException
	 *             The reading of the XML fails.
	 */
	@POST
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public void consumeXML(LinkedHashMap<String, String> obj) throws IOException {
		String usu = obj.get("usu");
		String ima = obj.get("ima");
		BDConnector login = new BDConnector();
		System.out.println(ima);
		String[] result = ima.split(",");
		String imageString = result[1];
		byte[] imageByte;
		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(imageString);
		if (login.Convertirimagen(imageByte, usu)) {
			System.out.println("Bien");
		}
	}

	/**
	 * POST method that consumes an XML, the idea is to convert a login image.
	 * 
	 * @param obj
	 *            A LinkedHashMap that has the username as key string and a
	 *            Base64 image as content.
	 * @throws IOException
	 *             The reading of the XML fails.
	 */
	@POST
	@Path("/updatest")
	@Produces(MediaType.TEXT_PLAIN)
	public void consumeXML1(LinkedHashMap<String, String> obj) throws IOException {
		String usu = obj.get("usu");
		String ima = obj.get("ima");
		BDConnector login = new BDConnector();
		System.out.println(ima);
		String[] result = ima.split(",");
		String imageString = result[1];
		byte[] imageByte;
		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(imageString);
		if (login.Convertirimagenest(imageByte, usu)) {
			System.out.println("Bien");
		}
	}

	/**
	 * Creates the report for an specific user.
	 * 
	 * @param user
	 *            The user that is been seek for the report.
	 * @return A List with the data of the Report.
	 */
	@GET
	@Path("/reporte")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Reporte> getReporte(@QueryParam("user") String user) {
		BDConnector login = new BDConnector();
		return login.getReporte(user);
	}

	/**
	 * GET method that finds an appropriate activity for a given user.
	 * 
	 * @param user
	 *            The ID of the user that is requesting a new Activity.
	 * @param act
	 *            The ID of the activity he/she just did.
	 * @param esc
	 *            The scenario ID in which he/she is making the activities.
	 * @param respuesta
	 *            The answer of the user.
	 * @param sesion
	 *            The active session in which user is.
	 * @param skip
	 *            Whether if the activity was skipped (1) or not (0).
	 * @param ayuda
	 *            The number of helps received from the application.
	 * @param pista
	 *            Whether if the user played the clue (true) or not (false).
	 * @return An object to send, ready to be casted as a JSON that Client will
	 *         understand.
	 */
	@GET
	@Path("/asignar")
	@Produces(MediaType.APPLICATION_JSON)
	public Auxiliar getAsignar(@QueryParam("user") int user, @QueryParam("id_actividad") int act,
			@QueryParam("id_escenario") int esc, @QueryParam("respuesta") String respuesta,
			@QueryParam("id_sesion") int sesion, @QueryParam("skipped") int skip, @QueryParam("ayuda") int ayuda,
			@QueryParam("pista") boolean pista) {
		BDConnector login = new BDConnector();
		Actividad acti = new Actividad();
		Actividad a = new Actividad();
		Actividad activ = new Actividad();
		Regla r = new Regla();
		User u = new User();
		Auxiliar aux = null;
		Actividad total = new Actividad();
		ArrayList<Actividad> actividad = new ArrayList<Actividad>();
		acti = login.Asignarpuntaje(act);
		activ = login.verificarGame(user, esc);
		System.out.println("activ " + act);
		total = login.actividadMinima(esc);
		total.setRecompensa(2);
		login.agregarGame(a.getPuntaje(), respuesta, skip, sesion, user, act);
		aux = new Auxiliar(total);
		a = r.actividad(acti.getPalabra(), respuesta, acti.getDificultad(), act, skip, esc, ayuda, pista);
		if (a.getAprobado() == 3) {
			System.out.println(
					a.getNuevact() + " " + a.isDificultadct() + " " + a.getRecompensa() + " " + a.getPuntaje());
			total = new Actividad(a.getNuevact(), a.isDificultadct(), a.getRecompensa(), a.getPuntaje());
			aux = new Auxiliar(total);
		}
		if (a.getAprobado() == 2 || a.getAprobado() == 1 || a.getAprobado() == 9) {
			System.out.println(
					a.getNuevact() + " " + a.isDificultadct() + " " + a.getRecompensa() + " " + a.getPuntaje());
			total = new Actividad(a.getNuevact(), a.isDificultadct(), a.getRecompensa(), a.getPuntaje());
			aux = new Auxiliar(total);
		}
		actividad.add(total);
		if (ayuda != -1 && act != 0) {
			login.agregarGame(a.getPuntaje(), respuesta, skip, sesion, user, act);
			u = login.datosUsuario(user);
			String completo = u.getNombre() + " " + u.getApellido();
			login.agregarReport(completo, u.getTutor(), a.getId_actividad(), esc, respuesta, acti.getPalabra(),
					a.getDificultad());
		}
		System.out.println(aux.getNuevact());
		return aux;
	}

	/**
	 * @deprecated Method that gives an activity if user has never entered to
	 *             the application.
	 * @param user
	 *            The user ID that is requesting the Activity.
	 * @param esc
	 *            The scenario ID in which he/she is making the activities.
	 * @param ayuda
	 *            The number of helps received from the application.
	 * @return An object to send, ready to be casted as a JSON that Client will
	 *         understand.
	 */
	@GET
	@Path("/nuevo")
	@Produces(MediaType.APPLICATION_JSON)
	public Actividad getNuevoUsuario(@QueryParam("user") int user, @QueryParam("id_escenario") int esc,
			@QueryParam("ayuda") int ayuda) {
		BDConnector login = new BDConnector();
		Actividad activ = new Actividad();
		Regla r = new Regla();
		ArrayList<Actividad> actividad = new ArrayList<Actividad>();
		if (login.usuarioNuevo(user) == 1) {
			actividad.add(activ);
		}
		return activ;
	}
}
