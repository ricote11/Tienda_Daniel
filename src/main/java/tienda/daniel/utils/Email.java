package tienda.daniel.utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.LogManager;




import tienda.daniel.controllers.CarritoController;

public class Email {
	
	
	private static org.apache.log4j.Logger logger = LogManager.getLogger(Email.class);
	public static  void enviarEmail() {
	String destinatario = "danielricomomp@gmail.com";

	try
	{
		// Propiedades de la conexion
		Properties prop = new Properties();
		// Nombre del servidor de salida
		prop.setProperty("mail.smtp.host", "smtp.gmail.com");
		// Habilitamos TLS
		prop.setProperty("mail.smtp.starttls.enable", "true");
		// Indicamos el puerto
		prop.setProperty("mail.smtp.port", "587");
		// Indicamos el usuario
		prop.setProperty("mail.smtp.user", "tiendaonlineserbatic@gmail.com");
		// Indicamos que requiere autenticaciÃ³n
		prop.setProperty("mail.smtp.auth", "true");

		// Creamos un objeto sesion
		Session sesion = Session.getDefaultInstance(prop);
		// TODO
		sesion.setDebug(true);
		// Creamos un objeto mensaje a traves de la sesion
		MimeMessage mensaje = new MimeMessage(sesion);

		// Indicamos la cuenta desde la que se va a enviar
		mensaje.setFrom(new InternetAddress("tiendaonlineserbatic@gmail.com"));

		// AÃ±adimos el recipiente al mensaje al que va a ir dirigido el mensaje
		mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

		// Creamos el asunto del mensaje
		mensaje.setSubject("Ejemplo con JavaMail");

		// Creamos el cuerpo del mensaje
		mensaje.setText("Esto es una prueba con JavaMail");

//		mensaje.setText(
//				"Esto es una prueba <br> con <b>JavaMail</b>",
//				"ISO-8859-1",
//				"html");

		// Utilizamos un objeto transport para hacer el envio indicando el protocolo
		Transport t = sesion.getTransport("smtp");
		// Hacemos la conexion
		t.connect("tiendaonlineserbatic@gmail.com", "serbatic");
		// Enviamos el mensaje
		t.sendMessage(mensaje, mensaje.getAllRecipients());

		// Cerramos la conexion
		t.close();

	}catch(AddressException ex)
	{
		Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
	}catch(MessagingException ex1)
	{
	
	 Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex1);
	}
	}
}
