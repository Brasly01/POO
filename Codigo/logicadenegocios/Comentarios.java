import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.mail.search.FlagTerm;
import java.util.List;
import java.util.Vector;

public class Comentarios {
  private String host = "imap.gmail.com"; // Servidor IMAP de Gmail
  private String username = "pruebatecpoo@gmail.com";
  private String password = "dyolfedupjgjkhit"; // Tu contraseña de Gmail
  private List<String> listaDeCadenas = new Vector<String>();
  
  public Comentarios () {
    Properties props = new Properties();
    props.setProperty("mail.store.protocol", "imaps");
    props.setProperty("mail.imap.host", host);
    props.setProperty("mail.imap.port", "993");
    
    Session session = Session.getDefaultInstance(props);

    try {
      Store store = session.getStore("imaps");
      store.connect(getHost (), getUsername (), getPassword () );

      Folder inbox = store.getFolder("INBOX");
      inbox.open(Folder.READ_WRITE); // Abre la bandeja de entrada en modo de lectura y escritura

      // Busca los mensajes no leídos
      FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
      Message[] messages = inbox.search(ft);

      for (int i = 0; i < messages.length; i++) {
        Message message = messages[i];

        Object content = message.getContent();
        if (content instanceof Multipart) {
          Multipart multipart = (Multipart) content;
          for (int j = 0; j < multipart.getCount(); j++) {
            BodyPart bodyPart = multipart.getBodyPart(j);

            if (bodyPart.isMimeType("text/html")) {
              // Procesar contenido HTML
              String htmlContent = (String) bodyPart.getContent();
              Document document = Jsoup.parse(htmlContent);
              String textContent = document.text();
              listaDeCadenas.add(textContent);
            }
          }
        }
        
        // Marcar el mensaje como leído
        message.setFlag(Flags.Flag.SEEN, true);
      }
      
        // Cierra la bandeja de entrada y la tienda IMAP
      inbox.close(true); // 'true' para expurgar los mensajes eliminados
      store.close();
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
    
  }

  public String getUsername () {
    return username;
  }
  
  public String getHost () {
    return host;
  }
  
  public String getPassword () {
    return password;
  }
  
  public List<String> getComentarios () {
    return listaDeCadenas; 
  }
  
}
