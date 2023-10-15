package logicadenegocios;

public class Jugador {
  private String nombre;
  private String cedula;
  private String email;
  
  Jugador (String pNombre, String pCedula, String pEmail) {
    nombre = pNombre;
    cedula = pCedula;
    email = pEmail;
  }
  
  public String getCedula() {
    return cedula;
  }
  
  public String getEmail() {
    return email;
  }
  
  public String toString() {
    String cadena = "";
    
    cadena += "Nombre del jugador: " + nombre + "\n";
    cadena += "Cédula            : " + cedula + "\n";
    cadena += "Email             : " + email + "\n";
    
    return cadena;
  }
  
  public String[] getDatos() {
    String [] datos = {nombre, cedula, email};
    return datos;
  }
}

