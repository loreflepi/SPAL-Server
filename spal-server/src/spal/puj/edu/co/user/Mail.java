/*
 * Pontificia Universidad Javeriana
 * Trabajo de grado (SPAL) ~CIS1710AP05
 * June 2017
 */
package spal.puj.edu.co.user;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Main is the class that is in charge of sending emails to tutors.
 * 
 * @author Lorena María Pinzón Acevedo
 * @author Esteban Villafrades Iannini
 */
public class Mail {

	/**
	 * Default constructor.
	 */
	public Mail() {

	}

	/**
	 * In case of changing the password application will generate an email to
	 * the tutor to alert him/her of him/her new password.
	 * 
	 * @param mail
	 *            Tutor's email.
	 * @param contra
	 *            The new password.
	 * @return If it was possible to send the mail or not.
	 */
	public boolean sendMailPassword(String mail, String contra) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("spal.javeriana@gmail.com", "oiibtrlkinrbgmur");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("spal.javeriana@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject("Cambio de contraseña");
			message.setText("Hola," + "\n\n Has solicitado un cambio de contraseña, tu nueva contraseña es: " + contra
					+ "\n\n Equipo SPAL");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * When the administrator accepts a new tutor, this will be notified.
	 * 
	 * @param mail
	 *            Tutor's email address.
	 * @return If it was possible to send the mail or not.
	 */
	public boolean sendMailAccess(String mail) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("spal.javeriana@gmail.com", "oiibtrlkinrbgmur");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("spal.javeriana@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject("Confirmación");
			message.setText(
					"Hola," + "\n\n El administrador ha confirmado tu información, ya puedes acceder a la plataforma."
							+ "\n\n Equipo SPAL");
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Sends an email when someone wants to make contact with SPAL team.
	 * 
	 * @param mail
	 *            The email of the person that wants to contact SPAL.
	 * @param asunto
	 *            The header of the message.
	 * @param mensaje
	 *            The body of the message.
	 * @return If it was possible to send the mail or not.
	 */
	public boolean sendMailContact(String mail, String asunto, String mensaje) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("spal.javeriana@gmail.com", "oiibtrlkinrbgmur");
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("spal.javeriana@gmail.com"));
			message.setSubject(asunto);
			message.setText(mensaje);
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

}
