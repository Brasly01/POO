package logicadenegocios;

import java.util.Random;

public class CartonBingo {
    
  private int[][] matriz;
  private String identificador;
  private int fil;
  private int col;
  private int[] rango = {15, 30, 45, 60, 75};
  private Jugador jugadorAsig;
  public static int numCartones = 0;
  
  public CartonBingo(String pIdentificador) {
    identificador = pIdentificador;
    matriz = new int[5][5];
    fil = 5;
    col = 5;
    generarIdentificador();
    generarNumerosMatriz();
    jugadorAsig = null;
    
    numCartones++;
  }
  
  public void asignarAJugador(Jugador pJugador) {
    jugadorAsig = pJugador;
  }
  
  public void generarIdentificador() {
    String numCarton = String.valueOf(numCartones);
    
    for (int i = 0; i < (3 - numCarton.length()); i++) {
      identificador += "0";
    }
    
    identificador += numCarton;
  }
  
  public Jugador getJugadorAsig() {
    return jugadorAsig;
  }
  
  public void generarNumerosMatriz() {
    
    for (int i = 0; i < fil; i++) {
      for (int j = 0; j < col; j++) {
        matriz[i][j] = generarNumeroAleatorio(i);

      }
    }
  }
  
  public String getIdentificador() {
    return identificador;
  }
  
  public int[][] getMatriz() {
    return matriz;
  }
  
  public int generarNumeroAleatorio(int pFila) {
    Random random = new Random();
    
    int numeroAleatorio = random.nextInt(rango[pFila]) + 1;
    
    while (esRepetido(pFila, numeroAleatorio) || (numeroAleatorio <= (rango[pFila] - 15))) {
      numeroAleatorio = generarNumeroAleatorio(pFila);
    }
    
    return numeroAleatorio;
  }
  
  private boolean esRepetido(int pFila, int pNum) {
      
    for (int j = 0; j < col; j++) {
      if (matriz[pFila][j] == pNum) {
        return true;
      }
    }
    
    return false;
  }
}
