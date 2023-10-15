 
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;

public class EnviarCorreo {
  private String usuario= "pruebatecpoo@gmail.com";
  private String clave = "dyol fedu pjgj khit";
  private String servidor = "smtp.gmail.com";
  private String puerto = "587"; 
  private Properties propiedades;
  
  public EnviarCorreo(String destinatario, String tituloCorreo, String cuerpo, String[] archivosAdjuntos) {
    CuentaCorreo ();
    Session sesion = abrirSesion();
    try {
      Message message = new MimeMessage(sesion);
      message.setFrom(new InternetAddress(usuario));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
      message.setSubject(tituloCorreo);
      MimeBodyPart mensajeParte = new MimeBodyPart();
      mensajeParte.setText(cuerpo);
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mensajeParte);
    if (archivosAdjuntos != null) {
      for (String archivoAdjunto : archivosAdjuntos) {
        MimeBodyPart adjuntoParte = new MimeBodyPart();
        File adjunto = new File(archivoAdjunto);
        adjuntoParte.attachFile(adjunto);
        multipart.addBodyPart(adjuntoParte);
      }
    } 
    
    message.setContent(multipart);
    Transport.send(message);
    } catch (MessagingException e) {
    e.printStackTrace();
    } catch (Exception e) {
    e.printStackTrace();
    }
  }
  
  public void CuentaCorreo () {
    propiedades = new Properties();
    propiedades.put("mail.smtp.host", servidor);
    propiedades.put("mail.smtp.port", puerto);
    propiedades.put("mail.smtp.auth", "true");
    propiedades.put("mail.smtp.starttls.enable", "true"); 
  }
  
  private Session abrirSesion() {
    Session sesion = Session.getInstance(propiedades,
      new javax.mail. Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() { 
          return new PasswordAuthentication (usuario, clave);
        }
    });
    return sesion;
   }
  
  
  
  public static void main(String[] args) {
     String destinatario= "braslymorales@gmail.com";
     String tituloCorreo = "sirve?";
     String[] archivosAdjuntos = {
        "/Users/Usuario/Desktop/lab 6/Investigacion.pdf"
    };
     String cuerpo= "aveces";
     

        EnviarCorreo crea1 = new EnviarCorreo(destinatario, tituloCorreo, cuerpo, archivosAdjuntos);
        
  }
  
}
