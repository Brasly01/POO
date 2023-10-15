package aplicacion;

import logicadenegocios.*;

import java.util.*;

/**
 * Write a description of class Juego here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Aplc {
    
  public static void main(String args[]) {
      
      
    Juego jg = new Juego();
    String msg = "";    
    jg.agregarJugador("Eyden Snc", "70329", "es@aa");
    jg.generarCartones(10);
    
    jg.enviarCartones(2, "70329");
    
    ArrayList<CartonBingo> cbs = jg.getCartones();
    
    for (CartonBingo c: cbs) {
      int[][] matriz1 = c.getMatriz();
    
      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
        
          msg += String.valueOf(matriz1[i][j]) + "   ";     
        }
        msg+= "\n";
      }
      msg+= c.getIdentificador();
      if (c.getJugadorAsig() == null) {
        msg+= "\n\n";
        continue;
      }
      msg+= "\n";
      msg+= c.getJugadorAsig().toString();
      msg+= "\n\n";
    }
    System.out.println(msg);
    
    jg.agregarJugador("Eyden Su", "703100722", "esdja");
    jg.agregarJugador("Eyden Su", "703100722", "esdja");
    jg.agregarJugador("Eyden Su", "703120722", "esdja");
    jg.agregarJugador("Eyden Su", "743100722", "esdja");
    
    for (Jugador jugador: jg.getJugadores()) {
      System.out.println(jugador.toString());
    }
    
    jg.generarCSV();
  }
  
  
}
