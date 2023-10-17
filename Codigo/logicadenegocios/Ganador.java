package logicaof;



import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.*;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Ganador {
    private int ganar = 0; // Inicialmente no se ha ganado
    private int contador1 = 0;
    private int contador2 = 0;
    List<int[]> todonumero = new ArrayList<>();
    List<String> tipoJuegos = new ArrayList<>();
    List<List<String>> ganadoresPartida = new ArrayList<List<String>>(); // guarda los ganadores de la partida
    List<List<String>> cartoncodigo = new ArrayList<List<String>>();
    private Map<Character, Set<String>> jugadoresGanadores = new HashMap<>();
    
    public void enontrarGanadores(Juego pJuego, char tipoJuego) {
        List<CartonBingo> cartones = pJuego.getCartones(); // extrae la matriz de matrices
        List<String> ganador = new ArrayList<String>(); // guarda la cedula de la última partida
        List<String> codigos = new ArrayList<String>(); // guarda la cedula de la última partida
        
        ArrayList<Integer> numeros = pJuego.getNumerosCantados();
        Stream<Integer> streamNumerosCantados = numeros.stream();
        int[] pnumerosCantados = streamNumerosCantados.mapToInt(Integer::intValue).toArray();
       
        for (CartonBingo carton : cartones) {
            int[][] matriz = carton.getMatriz();
            String codigo = carton.getIdentificador();
            boolean esGanador = false;

            // Verifica si el jugador ya ha ganado en este tipo de juego y cartón
            if (!jugadoresGanadores.containsKey(tipoJuego)) {
                jugadoresGanadores.put(tipoJuego, new HashSet<>());
            }

            if (!jugadoresGanadores.get(tipoJuego).contains(carton.getJugadorAsig().getCedula())) {
                switch (tipoJuego) {
                    case 'X':
                        esGanador = getjuegoEnX(matriz, pnumerosCantados);
                        break;
                    case 'L':
                        esGanador = getJuegoL(matriz, pnumerosCantados);
                        break;
                    case 'E':
                        esGanador = getjuegoEnE(matriz, pnumerosCantados);
                        break;
                    case 'Z':
                        esGanador = getjuegoEnZ(matriz, pnumerosCantados);
                        break;
                    // Agregar otros tipos de juego si es necesario
                }

                if (esGanador) {
                    String Cedula =carton.getJugadorAsig().getCedula()+", ";

                    jugadoresGanadores.get(tipoJuego).add(carton.getJugadorAsig().getCedula());
                    ganador.add(Cedula);
                    codigos.add(codigo);
                }
            }
        }
      
        setPartidas(pnumerosCantados, tipoJuego, ganador, codigos);
        numeros.clear();
        streamNumerosCantados.close();
        pnumerosCantados = new int[0];
        getcartonGanador(codigos);
    }
    
    public List<String> getcartonGanador(List<String> codigos){
      if (codigos.isEmpty()) {
        return null;
      } else {
        return codigos;
      }
    }
         
    
  
  public void setPartidas(int[] pNumerosCantados,char ptipoJuego,List<String> ganador,List<String>codigos){
    if(pNumerosCantados == null || pNumerosCantados.length == 0){
        throw new IllegalArgumentException("El arreglo pNumerosCantados no puede ser nulo o vacío");
    }

    todonumero.add(pNumerosCantados);
    tipoJuegos.add(String.valueOf(ptipoJuego));
    ganadoresPartida.add(ganador);
    cartoncodigo.add(codigos);
    String numeros=Arrays.toString(pNumerosCantados);
   
    System.out.println("Partida guardada:");
    System.out.println("Números sorteados: " +numeros);
    System.out.println("Tipo de juego: " + ptipoJuego);
    System.out.println("Ganadores: " + ganador);
    System.out.println("Códigos de los cartones: " + codigos);

    GuardarPartida guardarPartida = new GuardarPartida();
    guardarPartida.guardarPartida(numeros, ptipoJuego, ganador);
  }

    
  
  
   public boolean getjuegoEnX(int[][] pMatriz, int[] pNumeroCantado) {
    contador1 = 0;
    contador2 = 4;
    for (int[] subfila : pMatriz) {
      setjuegoEnX2(subfila, pNumeroCantado);
      contador1 += 1;
      contador2 -= 1;
    }
    if (ganar == 9) {
      ganar = 0;
      return true;
    } 
    else {
      ganar = 0;
      return false;
    }
  }

  public void setjuegoEnX2(int[] pfila, int[] pNumeroCantado) {
    int NUM = pfila[contador1];
    int NUM2 = pfila[contador2];
    for (int numero : pNumeroCantado) {
      if (NUM == numero) {
        ganar += 1;
      } else if (NUM2 == numero) {
        ganar += 1;
      }
    }
  }

  public boolean getJuegoL(int[][] pMatriz, int[] pNumeroCantado) {
    for (int[] pfila : pMatriz) {
      for (int elemento : pfila) {
        for (int numero : pNumeroCantado) {
          if (elemento == numero) {
            ganar += 1;
            break;
          }
        }
      }
    }
    
    if (ganar == 25) {
      ganar = 0;
      return true;
    }
    else {
      ganar = 0;
      return false;
    }
  }

  public boolean getjuegoEnE(int[][] pMatriz, int[] pNumeroCantado) {
    int contador1 = 0;
    for (int elemento : pNumeroCantado) {
      if (pMatriz[0][0] == elemento) {
        ganar += 1;
      } 
      else if (pMatriz[0][4] == elemento) {
        ganar += 1;
      }
      else if (pMatriz[4][0] == elemento) {
        ganar += 1;
      } 
      else if (pMatriz[4][4] == elemento) {
        ganar += 1;
      }
    }
    if (ganar == 4) {
      ganar = 0;
      return true;
    } 
    else {
      ganar = 0;
      return false;
    }
  }

  public boolean getjuegoEnZ(int[][] pMatriz, int[] pNumeroCantado) {
    contador1 = 0;
    contador2 = 0;
    int[] fila1 = pMatriz[0];
    int[] fila2 = pMatriz[4];
    for (int numero : pNumeroCantado) {
      if (pMatriz[1][3] == numero) {
        ganar += 1;
      } else if (pMatriz[2][2] == numero) {
        ganar += 1;
      } else if (pMatriz[3][1] == numero) {
        ganar += 1;
      }
       else if (pMatriz[0][4] == numero) {
        ganar += 1;
      }
       else if (pMatriz[4][0] == numero) {
        ganar += 1;
      }
    }

    for (int numero : pNumeroCantado) {
      contador2=0;
      if (numero == pMatriz[4][0]) {
          ganar += 1;
        }
        else if (numero == pMatriz[4][1]) {
          ganar += 1;
        }
        else if (numero == pMatriz[4][2]) {
          ganar += 1;
        }
        else if (numero == pMatriz[4][3]) {
          ganar += 1;
        }
        else if (numero == pMatriz[4][4]) {
          ganar += 1;
        }
    }
  
    for (int numero : pNumeroCantado) {
      contador1=0;
       
      if (numero == pMatriz[4][0]) {
        ganar += 1;
      }
      else if (numero == pMatriz[4][1]) {
        ganar += 1;
      }
      else if (numero == pMatriz[4][2]) {
        ganar += 1;
      }
      else if (numero == pMatriz[4][3]) {
        ganar += 1;
      }
      else if (numero == pMatriz[4][4]) {
        ganar += 1;
      }
    }
      
      if (ganar == 15) {
      ganar = 0;
      return true;
    } else {
      ganar = 0;
      return false;
    }
  }
   public   List<List<String>> getcedulas() {
    return ganadoresPartida;
  }
  
   public List<List<String>> getGanadores() {
    return cartoncodigo;
   }

   public List<int[]> getTodoNumero() {
     return todonumero;
   }

  public List<String> getTipoJuegos() {
    return tipoJuegos;
  }

}