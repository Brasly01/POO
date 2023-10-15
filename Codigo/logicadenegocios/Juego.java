package logicadenegocios;


import java.util.*;

/**
 * Write a description of class Juego here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Juego {
    
  private ArrayList<CartonBingo> cartones;
  private ArrayList<Jugador> jugadores;
  private String identificador;
  
  
  public Juego() {
    cartones = new ArrayList<CartonBingo>();
    jugadores = new ArrayList<Jugador>();
    identificador = generarIdentificador();
    
  }
  
  public void agregarJugador(String pNombre, String pCedula, String pEmail) {
    for (Jugador jugador: jugadores) {
      if (pCedula == jugador.getCedula()) {
        return;
      }
    }
    jugadores.add(new Jugador(pNombre, pCedula, pEmail));
  }
  
  public void generarCartones(int cantidad) {
    if (0 < cantidad && cantidad < 501) {
      for (int i = 0; i < cantidad; i++) {
        cartones.add(new CartonBingo(identificador));
      }
    } else {
      return;
    }
  }
  
  public void enviarCartones (int pCantidad, String pCedula) {
    Jugador j = buscarJugador(pCedula);
    int contador = 0;
    
    for (CartonBingo carton: cartones) {
      if (carton.getJugadorAsig() == null && contador < pCantidad) {
        contador ++;
        carton.asignarAJugador(j);
      }
    }
  }
  
  public ArrayList<CartonBingo> getCartones() {
    return cartones;
  }
  
  public ArrayList<Jugador> getJugadores() {
    return jugadores;
  }
  
  public String generarIdentificador() {
    Random random = new Random();
    String resultado = "";
    String letrasPermitidas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    for (int i = 0; i < 3; i++) {
      int indiceAleatorio = random.nextInt(letrasPermitidas.length());
      char letra = letrasPermitidas.charAt(indiceAleatorio);
      resultado += letra;
    }

    return resultado;
  }
  
  public void generarCSV() {
    List<String[]> datos = new ArrayList<String[]>();
    
    for (Jugador jugador: jugadores) {
      datos.add(jugador.getDatos());
    }
    
    String archCSV = "C:\\Users\\Eyden\\Desktop\\Jugadores.csv";
    //CSVWriter writer = new CSVWriter(new FileWriter(archCSV));

    //writer.writeAll(paises);

    //writer.close();
  }
  
  public Jugador buscarJugador(String pCedula) {
      
    for (Jugador jugador: jugadores) {
      if (pCedula == jugador.getCedula()) {
        return jugador;
      }
    }
    return null;
  }
}
