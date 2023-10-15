package logicadenegocios;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

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
  private static String ARCHIVO_JUGADORES= "C:\\Users\\Eyden\\Desktop\\Jugadores.csv";
  
  public Juego() {
    cartones = new ArrayList<CartonBingo>();
    jugadores = new ArrayList<Jugador>();
    identificador = generarIdentificador();
    
  }
  
  public void agregarJugador(String pNombre, String pCedula, String pEmail) {
    for (Jugador jugador: jugadores) {
      if (buscarJugador(pCedula) != null) {
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

    // Supongamos que Jugador tiene un método getDatos() que devuelve un array de Strings
    for (Jugador jugador : jugadores) {
      datos.add(jugador.getDatos());
    }

    try {
      // Crear una instancia de FileWriter para abrir el archivo CSV en modo de escritura
      FileWriter fileWriter = new FileWriter(ARCHIVO_JUGADORES);

      // Crear una instancia de CSVPrinter con el formato CSV deseado
      CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);

      // Escribir los datos en el archivo CSV utilizando CSVPrinter
      for (String[] fila : datos) {
        csvPrinter.printRecord((Object[]) fila);
      }

      // Cerrar el archivo CSV
      csvPrinter.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public Jugador buscarJugador(String pCedula) {
      
    for (Jugador jugador: jugadores) {
      if (pCedula.equals(jugador.getCedula())) {
        
        return jugador;
      }
    }
    return null;
  }
  
  public void cargarJugadores() {
    try (FileReader lector = new FileReader(ARCHIVO_JUGADORES);
         CSVParser csvParser = CSVParser.parse(lector, CSVFormat.DEFAULT)) {
      for (CSVRecord csvRecord : csvParser) {
        String nombre = csvRecord.get(0);
        String cedula = csvRecord.get(1);
        String email = csvRecord.get(2);
        
        
        Jugador jugador = new Jugador(nombre, cedula, email);
        jugadores.add(jugador);
      }
    } catch (IOException e){
      e.printStackTrace();
    }
  }
}
