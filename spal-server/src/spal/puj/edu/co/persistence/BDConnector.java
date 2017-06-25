/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.persistence;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import spal.puj.edu.co.reglas.Actividad;
import spal.puj.edu.co.rservices.dto.Tutor;
import spal.puj.edu.co.rservices.dto.User;
import spal.puj.edu.co.silabas.Score;
import spal.puj.edu.co.user.ImageTransformation;
import spal.puj.edu.co.user.Mail;

/**
 * BDConnector is the class in charge of the MySQL BDConnector connection
 * properties stings.
 *
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class BDConnector {

	// private static String url =
	// "jdbc:mysql://162.243.36.251:3306/dbo?autoReconnect=true&useSSL=false";
	private static String url = "jdbc:mysql://198.199.64.141:3306/dbo?autoReconnect=true&useSSL=false";
	private static String username = "desarrollo";
	private static String password = "Hola1234*";

	/**
	 * Tutor or Administrator login
	 * 
	 * @param user
	 *            Username.
	 * @param pass
	 *            Password.
	 * @return The Tutor information.
	 * @throws SQLException
	 *             The query failed.
	 */
	public static Tutor LoginTutor(String user, String pass) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Tutor u = new Tutor();
		int tipo = 0;
		int id = 0;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT CASE WHEN Administrator_Tutor=1 THEN 1 ELSE 2 END AS Tipo, ID_Tutor FROM Tutor WHERE binary Username_Tutor=? and Password_Tutor=? and Access_Tutor=1";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			if (rs.next()) {
				tipo = rs.getInt(1);
				id = rs.getInt(2);
			}
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			rs.close();
			ps.close();
			connection.close();
		}
		u.setTipo(tipo);
		u.setId(id);
		return u;
	}

	/**
	 * Tutor registration.
	 * 
	 * @param user
	 *            Username to be added.
	 * @param pass
	 *            Password to be added.
	 * @param nombre
	 *            Name to be added.
	 * @param apellido
	 *            Lastname to be added.
	 * @param mail
	 *            Mail to be added.
	 * @param colegio
	 *            School ID where tutor works.
	 * @return Whether if changes were saved or not.
	 * @throws SQLException
	 *             The query failed.
	 */
	public static boolean RegistroTutor(String user, String pass, String nombre, String apellido, String mail,
			int colegio) throws SQLException {
		boolean response = false;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO Tutor (Name_Tutor, Lastname_Tutor, Email_Tutor, Username_Tutor, Password_Tutor, ID_School, Access_Tutor) VALUES( ?, ?, ?, ?, ?, ?, 0)";
			preparedStmt = connection.prepareStatement(sql);
			preparedStmt.setString(1, nombre);
			preparedStmt.setString(2, apellido);
			preparedStmt.setString(3, mail);
			preparedStmt.setString(4, user);
			preparedStmt.setString(5, pass);
			preparedStmt.setInt(6, colegio);
			preparedStmt.executeUpdate();
			response = true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			preparedStmt.close();
			connection.close();
		}
		return response;
	}

	/**
	 * User registration.
	 * 
	 * @param user
	 *            Username to be added.
	 * @param nombre
	 *            Name to be added.
	 * @param cumple
	 *            Date of birth of the user.
	 * @param apellido
	 *            Lastname to be added.
	 * @param genero
	 *            Genre of the user.
	 * @param tutor
	 *            ID of the Tutor in charge.
	 * @return Whether if student was inserted or not.
	 * @throws SQLException
	 *             The query failed.
	 */
	public static boolean InsertarEstudiante(String user, String nombre, Date cumple, String apellido, int genero,
			int tutor) throws SQLException {
		boolean response = false;
		Connection connection = null;
		PreparedStatement preparedStmt = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "INSERT INTO User (Name_User, Lastname_User, Username_User, ID_Password, ID_Color, ID_Avatar, ID_Tutor, ID_Genre, ID_Font, Birth_User, New_User) VALUES (?, ?, ?, ?, ?, (SELECT ID_Avatar FROM Avatar WHERE ID_Desc_Avatar= ? and ID_Genre= ?), ?, ?, ?, ?, ?)";
			preparedStmt = connection.prepareStatement(sql);
			preparedStmt.setString(1, nombre);
			preparedStmt.setString(2, apellido);
			preparedStmt.setString(3, user);
			preparedStmt.setInt(4, 0);
			preparedStmt.setInt(5, 1);
			preparedStmt.setInt(6, 1);
			preparedStmt.setInt(7, genero);
			preparedStmt.setInt(8, tutor);
			preparedStmt.setInt(9, genero);
			preparedStmt.setInt(10, 1);
			preparedStmt.setDate(11, cumple);
			preparedStmt.setInt(12, 1);
			preparedStmt.executeUpdate();
			response = true;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		} finally {
			preparedStmt.close();
			connection.close();
		}
		return response;
	}

	/**
	 * Method for the administrator to see all tutors who have not been
	 * accepted.
	 * 
	 * @return List of not yet accepted tutors.
	 */
	public static ArrayList<Tutor> TutorSinAceptar() {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<Tutor> tutor = new ArrayList<Tutor>();
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT ID_Tutor, Name_Tutor, Lastname_Tutor, Email_Tutor, Username_Tutor, ID_School  FROM Tutor t WHERE Administrator_Tutor=0 AND Access_Tutor=0";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				tutor.add(new Tutor(rs.getInt(1), rs.getString(5), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getInt(6)));
			}
			rs.close();
			connection.close();
			return tutor;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

	/**
	 * Method for the administrator to accept the tutor.
	 * 
	 * @param id
	 *            ID of the tutor.
	 * @param email
	 *            Email of the tutor.
	 * @return Whether if changes were saved or not.
	 */
	public static boolean AceptarTutor(int id, String email) {
		Connection connection = null;
		PreparedStatement ps = null;
		Mail mail = new Mail();
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "UPDATE Tutor SET Access_Tutor = 1 WHERE ID_Tutor=? AND Email_Tutor=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, email);
			if (ps.executeUpdate() == 1) {
				mail.sendMailAccess(email);
				ps.close();
				connection.close();
				return true;
			}

		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;
	}

	/**
	 * Method to obtain password in SHA256 format.
	 * 
	 * @param input
	 *            The password String that will be converted.
	 * @return The hash of the password.
	 * @throws NoSuchAlgorithmException
	 *             The algorithm is not available.
	 */
	public static String sha256(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	/**
	 * Method to update tutor's password.
	 * 
	 * @param email
	 *            Email of the tutor.
	 * @param contra
	 *            The new tutor's password.
	 * @return Whether if changes were saved or not.
	 */
	public static boolean Cambiarcontra(String email, String contra) {
		Connection connection = null;
		PreparedStatement ps = null;
		Mail mail = new Mail();
		String cont = "";
		try {
			cont = sha256(contra);
			connection = DriverManager.getConnection(url, username, password);
			String sql = "UPDATE Tutor SET Password_Tutor =? WHERE Email_Tutor=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, cont);
			ps.setString(2, email);
			if (ps.executeUpdate() == 1 && mail.sendMailPassword(email, contra) == true) {
				ps.close();
				connection.close();
				return true;
			} else {
				ps.close();
				connection.close();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Gets the users enrolled to a specific tutor.
	 * 
	 * @param user
	 *            Tutor's username.
	 * @return List of users.
	 */
	public static ArrayList<User> todosEstudiantes(String user) {
		Connection connection = null;
		PreparedStatement ps = null;
		ArrayList<User> u = new ArrayList<User>();
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT u.Name_User, u.Lastname_User FROM User u LEFT JOIN Tutor t on u.ID_Tutor=t.ID_Tutor WHERE t.Username_Tutor=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.add(new User(rs.getString(1), rs.getString(2)));
			}
			rs.close();
			connection.close();
			return u;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return u;
	}

	/**
	 * Uploads the tutor's photo to the database.
	 * 
	 * @param ima
	 *            Tutor's image profile picture.
	 * @param usuario
	 *            Tutor's username.
	 * @return Whether if changes were saved or not.
	 */
	public boolean Convertirimagen(byte[] ima, String usuario) {
		Connection connection = null;
		PreparedStatement ps = null;
		BufferedImage image = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "UPDATE Tutor SET Photo_Tutor =? WHERE Username_Tutor=?";
			ps = connection.prepareStatement(sql);
			ByteArrayInputStream bis = new ByteArrayInputStream(ima);
			image = ImageIO.read(bis);
			bis.close();
			ImageTransformation.resizeAdjustMax(100, 100, image, "mod");
			BufferedImage nueva = ImageIO.read(new File("c:\\image\\mod_new.png"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(nueva, "png", baos);
			byte[] imageInByte = baos.toByteArray();
			Blob blob = connection.createBlob();
			blob.setBytes(1, imageInByte);
			ps.setBlob(1, blob);
			ps.setString(2, usuario);
			if (ps.executeUpdate() == 1) {
				ps.close();
				connection.close();
				return true;
			} else {
				ps.close();
				connection.close();
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;

	}

	/**
	 * Uploads the user's photo to the database.
	 * 
	 * @param ima
	 *            User's image profile picture.
	 * @param usuario
	 *            Users's username.
	 * @return Whether if changes were saved or not.
	 * @throws IOException
	 *             Can't read input image file.
	 */
	public boolean Convertirimagenest(byte[] ima, String usuario) throws IOException {
		Connection connection = null;
		PreparedStatement ps = null;
		BufferedImage image = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "UPDATE User SET Photo_User =? WHERE Username_User=?";
			ps = connection.prepareStatement(sql);
			ByteArrayInputStream bis = new ByteArrayInputStream(ima);
			image = ImageIO.read(bis);
			bis.close();
			ImageTransformation.resizeAdjustMax(100, 100, image, "mod");
			BufferedImage nueva = ImageIO.read(new File("c:\\image\\mod_new.png"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(nueva, "png", baos);
			byte[] imageInByte = baos.toByteArray();
			Blob blob = connection.createBlob();
			blob.setBytes(1, imageInByte);
			ps.setBlob(1, blob);
			ps.setString(2, usuario);
			if (ps.executeUpdate() == 1) {
				ps.close();
				connection.close();
				return true;
			} else {
				ps.close();
				connection.close();
				return false;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	/**
	 * Inserts a new Activity.
	 * 
	 * @param word
	 *            Word String.
	 * @param image
	 *            Image that represents the word.
	 * @param sound
	 *            Sound made by the Image.
	 * @param clue
	 *            Readen word.
	 * @param scene
	 *            Scenario ID to which word belongs.
	 */
	public void insertWord(String word, String image, String sound, String clue, int scene) {
		Score s = new Score();
		try {
			Connection db_connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = db_connection.prepareStatement(
					"INSERT INTO Activity (Word_Activity, Image_Activity, Sound_Activity, Clue_Activity, ID_Difficulty, ID_Scene) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, word);
			ps.setString(2, image);
			ps.setString(3, sound);
			ps.setString(4, clue);
			ps.setInt(5, s.calculateComplexity(word));
			ps.setInt(6, scene);
			ps.executeUpdate();
			db_connection.close();
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Inserts data to the report to be viewed by the tutor.
	 * 
	 * @param nombre
	 *            User's username.
	 * @param tutor
	 *            Tutor's ID.
	 * @param actividad
	 *            Activity's ID made by user.
	 * @param esc
	 *            Scenario's ID of the activity.
	 * @param respuesta
	 *            User's answer.
	 * @param palabra
	 *            Correct word String.
	 * @param dificultad
	 *            Difficulty's ID.
	 */
	public void agregarReport(String nombre, int tutor, int actividad, int esc, String respuesta, String palabra,
			int dificultad) {
		Score s = new Score();
		try {
			Connection db_connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = db_connection.prepareStatement(
					"INSERT INTO Report (Name_User,ID_Tutor,ID_Activity,ID_Scene,Response,Word,Difficulty) VALUES (?, ?, ?, ?, ?, ?, ?)");

			ps.setString(1, nombre);
			ps.setInt(2, tutor);
			ps.setInt(3, actividad);
			ps.setInt(4, esc);
			ps.setString(5, respuesta);
			ps.setString(6, palabra);
			ps.setInt(7, dificultad);
			ps.executeUpdate();
			db_connection.close();

		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * Gets children who have done some activity.
	 * 
	 * @param nombre
	 *            Tutor's username.
	 * @return List of reports (name, response, word, difficulty).
	 */
	public ArrayList<Reporte> getReporte(String nombre) {

		Connection connection = null;
		PreparedStatement ps = null;
		int idTutor = getIdTutor(nombre);
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "Select Name_User, Response, Word, Difficulty from Report where ID_Tutor=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, idTutor);
			ResultSet rs = ps.executeQuery();
			ArrayList<Reporte> reportes = new ArrayList<Reporte>();
			while (rs.next()) {
				Reporte reporte = new Reporte();
				reporte.setNombre(rs.getString(1));
				reporte.setRespuesta(rs.getString(2));
				reporte.setPalabra(rs.getString(3));
				reporte.setDificultad(rs.getInt(4));
				reportes.add(reporte);
			}
			rs.close();
			connection.close();
			return reportes;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;

	}

	/**
	 * Method that allows to see the words and the difficulty of each student.
	 * 
	 * @param id_activity
	 *            Activity's ID.
	 * @return The next activity that user should do.
	 */
	public Actividad Asignarpuntaje(int id_activity) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT a.Word_Activity, a.ID_Difficulty FROM Activity a WHERE a.ID_Activity=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id_activity);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Actividad(rs.getString(1), rs.getInt(2));
			}
			rs.close();
			connection.close();
			return new Actividad(null, 0);
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return new Actividad(null, 0);
	}

	/**
	 * Method that allows to obtain the ID of the tutor
	 * 
	 * @param user
	 *            Tutor's username.
	 * @return The Tutor's ID, if not found returns 0.
	 */
	public int getIdTutor(String user) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select ID_Tutor from Tutor where Username_Tutor=? ";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);

			}
			rs.close();
			connection.close();
			return 0;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

	/**
	 * Gets the maximum difficulty by scenario
	 * 
	 * @param escenario
	 *            The seeked Scenario's ID.
	 * @return The maximum Difficulty's ID, if not found returns 0.
	 */
	public int getMaximaDificultad(int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select MAX(ID_Difficulty) FROM Activity WHERE ID_Scene=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			rs.close();
			connection.close();
			return 0;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

	/**
	 * Gets the minimum difficulty by scenario
	 * 
	 * @param escenario
	 *            The seeked Scenario's ID.
	 * @return The minimum Difficulty's ID, if not found returns 0.
	 */
	public int getMinimaDificultad(int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select MIN(ID_Difficulty) FROM Activity WHERE ID_Scene=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}
			rs.close();
			connection.close();
			return 0;

		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

	/**
	 * Gives the activity of less complexity by Scenario.
	 * 
	 * @param dificultad
	 *            Difficulty's ID.
	 * @param escenario
	 *            Scenario's ID.
	 * @return The next Activity that user should do.
	 */
	public int darActividadMenor(int dificultad, int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select ID_Activity FROM Activity WHERE ID_Scene=? AND ID_Difficulty<=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ps.setInt(2, dificultad);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			ArrayList<Integer> actividades = new ArrayList();
			while (rs.next()) {
				nuevo = rs.getInt(1);
				actividades.add(rs.getInt(1));

			}
			Random r = new Random();
			rs.close();
			connection.close();
			if (actividades.size() == 1) {
				return actividades.get(0);
			}
			int index = r.nextInt(actividades.size());
			if (index != 0) {
				index = index - 1;
			}
			int escogida = actividades.get(index);
			return escogida;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

	/**
	 * Compares the activities to know if there's a similar one.
	 * 
	 * @param dificultad
	 *            Difficulty's ID.
	 * @param escenario
	 *            Scenario's ID.
	 * @return Whether if he Activity is similar or not.
	 */
	public boolean isActividadSimilar(int dificultad, int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select ID_Activity FROM Activity WHERE ID_Scene=? AND ID_Difficulty=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ps.setInt(2, dificultad);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			ArrayList<Integer> actividades = new ArrayList<Integer>();
			while (rs.next()) {
				nuevo = rs.getInt(1);
				actividades.add(rs.getInt(1));
			}
			rs.close();
			connection.close();
			if (actividades.size() == 1 || actividades.size() == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return false;

	}
	
	/**
	 * Counts activities with similar difficulties and scene.
	 * 
	 * @param dificultad
	 *            Difficulty's ID.
	 * @param escenario
	 *            Scenario's ID.
	 * @return Number of similar activities.
	 */
	public int countActividadSimilar(int dificultad, int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select ID_Activity FROM Activity WHERE ID_Scene=? AND ID_Difficulty=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ps.setInt(2, dificultad);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			ArrayList<Integer> actividades = new ArrayList<Integer>();
			while (rs.next()) {
				nuevo = rs.getInt(1);
				actividades.add(rs.getInt(1));
			}
			rs.close();
			connection.close();
			return actividades.size();
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return -1;

	}

	/**
	 * Gives an activity with equal complexity by scenario.
	 * 
	 * @param dificultad
	 *            Difficulty's ID.
	 * @param escenario
	 *            Scenario's ID.
	 * @return The next Activity that user should do.
	 */
	public int darActividadIgual(int dificultad, int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select ID_Activity FROM Activity WHERE ID_Scene=? AND ID_Difficulty=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ps.setInt(2, dificultad);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			ArrayList<Integer> actividades = new ArrayList<Integer>();
			while (rs.next()) {
				nuevo = rs.getInt(1);
				actividades.add(rs.getInt(1));

			}
			Random r = new Random();
			rs.close();
			connection.close();
			if (actividades.size() == 1) {
				return actividades.get(0);
			}
			int index = r.nextInt(actividades.size());
			if (index != 0) {
				index = index - 1;
			}
			int escogida = actividades.get(index);
			return escogida;

		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

	/**
	 * Gives an activity with mayor complexity by scenario.
	 * 
	 * @param dificultad
	 *            Difficulty's ID.
	 * @param escenario
	 *            Scenario's ID.
	 * @return The next Activity that user should do.
	 */
	public int darActividadSuperior(int dificultad, int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select ID_Activity FROM Activity WHERE ID_Scene=? AND ID_Difficulty>=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ps.setInt(2, dificultad);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			ArrayList<Integer> actividades = new ArrayList();
			while (rs.next()) {
				nuevo = rs.getInt(1);
				actividades.add(rs.getInt(1));
			}
			Random r = new Random();
			rs.close();
			connection.close();
			if (actividades.size() == 1) {
				return actividades.get(0);
			}
			int index = r.nextInt(actividades.size());
			if (index != 0) {
				index = index - 1;
			}
			int escogida = actividades.get(index);
			return escogida;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

	/**
	 * Identifies if student is new in SPAL.
	 * 
	 * @param user
	 *            User's ID.
	 * @return The status to know if user is new.
	 */
	public int usuarioNuevo(int user) {
		Connection connection = null;
		PreparedStatement ps = null;
		String palabra = null;
		int dificultad = 0;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT New_User FROM User WHERE ID_User=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, user);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			if (rs.next()) {
				nuevo = rs.getInt(1);
				return nuevo;
			}
			rs.close();
			connection.close();
			return 0;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

	/**
	 * Gives an activity to user.
	 * 
	 * @param usuario
	 *            User's ID.
	 * @param escenario
	 *            Scenario's ID.
	 * @return The next Activity that user should do.
	 */
	public Actividad verificarGame(int usuario, int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		String palabra = null;
		int dificultad = 0;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT g.ID_Activity, a.ID_Difficulty FROM Game g LEFT JOIN Activity a ON g.ID_Activity=a.ID_Activity WHERE a.ID_Scene=? AND g.ID_User=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ps.setInt(2, usuario);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			if (rs.next()) {
				nuevo = rs.getInt(1);
				rs.getInt(2);
				return new Actividad(rs.getInt(1), rs.getInt(2));
			}
			rs.close();
			connection.close();
			return new Actividad(0, 0);
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return new Actividad(0, 0);

	}

	/**
	 * Method that allows to obtain the minimum activity for the users who are
	 * new by scene.
	 * 
	 * @param escenario
	 *            Scenario's ID.
	 * @return The next Activity that user should do.
	 */
	public Actividad actividadMinima(int escenario) {
		Connection connection = null;
		PreparedStatement ps = null;
		String palabra = null;
		int dificultad = 0;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "SELECT ID_Activity, ID_Difficulty FROM Activity WHERE ID_Scene=? ORDER BY ID_Difficulty ASC LIMIT 1 ";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, escenario);
			ResultSet rs = ps.executeQuery();
			int nuevo;
			ArrayList<Integer> actividades = new ArrayList<Integer>();
			if (rs.next()) {
				nuevo = rs.getInt(1);
				rs.getInt(2);
				return new Actividad(rs.getInt(1), rs.getInt(2));
			}
			rs.close();
			connection.close();
			return new Actividad(0, 0);
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return new Actividad(0, 0);
	}

	/**
	 * Gets all data from user by an given ID.
	 * 
	 * @param usuario
	 *            User ID.
	 * @return User data object.
	 */
	public User datosUsuario(int usuario) {
		Connection connection = null;
		PreparedStatement ps = null;
		String palabra = null;
		int dificultad = 0;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select Name_User, Lastname_User, ID_Tutor FROM User WHERE ID_User=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, usuario);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new User(rs.getString(1), rs.getString(2), rs.getInt(3));
			}
			rs.close();
			connection.close();
			return new User(null, null, 0);
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return new User(null, null, 0);
	}

	/**
	 * Adds Game history.
	 * 
	 * @param puntaje
	 *            Score given for the Activity.
	 * @param respuesta
	 *            The answer of the User.
	 * @param skip
	 *            Whether activity was skipped or not.
	 * @param sesion
	 *            User's Session.
	 * @param user
	 *            ID of User that made the Activity.
	 * @param actividad
	 *            Activity ID.
	 */
	public void agregarGame(int puntaje, String respuesta, int skip, int sesion, int user, int actividad) {
		try {
			Connection db_connection = DriverManager.getConnection(url, username, password);
			PreparedStatement ps = db_connection.prepareStatement(
					"INSERT INTO Game (Score_Game, Answer_Game, Skipped_Game, ID_Session, ID_User, ID_Activity) VALUES( ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, puntaje);
			ps.setString(2, respuesta);
			ps.setInt(3, skip);
			ps.setInt(4, sesion);
			ps.setInt(5, user);
			ps.setInt(6, actividad);
			ps.executeUpdate();
			db_connection.close();
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	/**
	 * @deprecated Check the Activity.
	 * @param acti
	 *            Activity's ID.
	 * @param user
	 *            User's ID.
	 * @param puntaje
	 *            User's score.
	 * @return User's score.
	 */
	public int comprobarActividad(int acti, int user, int puntaje) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DriverManager.getConnection(url, username, password);
			String sql = "select Score_Game from game where ID_Activity=? and ID_User=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, puntaje);
			ps.setInt(2, user);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rs.getInt(1);
				return puntaje;
			}
			rs.close();
			connection.close();
			return 0;
		} catch (Exception e) {
			Logger.getLogger(BDConnector.class.getName()).log(Level.SEVERE, null, e);
		}
		return 0;
	}

}
