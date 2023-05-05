import java.util.Scanner;

public class Juego {

    String[][] tablero;

    public String[][] getTablero() {
        return tablero;
    }

    public void jugar(){
        Scanner s = new Scanner(System.in);

        Jugadores j1 = new Jugadores();
        Jugadores j2 = new Jugadores();

        tablero = new String[7][7];
        vaciarArray(tablero);

        mostrarTablero(tablero);
        bienvenidos();
        System.out.println("Por favor ingresar nombre del jugador 1: ");
        j1.setNombre(s.nextLine());
        System.out.println("Por favor ingresar nombre del jugador 2: ");
        j2.setNombre(s.nextLine());
        j1.setFicha("X");
        j2.setFicha("O");
        int partidasGanadas1 = 0;
        int partidasGanadas2 = 0;

        boolean finalsiono = true;
        while (finalsiono){
            System.out.println(j1.getNombre() + " Ingresar columna de la ficha: ");
            int columna = s.nextInt();
            ponerFicha(tablero,columna,j1);
            mostrarTablero(tablero);
            if (verificarGanador(tablero, j1)){
                finalsiono = false;
                partidasGanadas1++;
            }
            if (finalsiono){
                System.out.println(j2.getNombre() + " Ingresar columna de la ficha: ");
                int columna2 = s.nextInt();
                ponerFicha(tablero,columna2,j2);
                mostrarTablero(tablero);
                if (verificarGanador(tablero, j2)){
                    finalsiono = false;
                    partidasGanadas2++;
                }
            }
            if (verificarArrayLleno(tablero) || !finalsiono){
                System.out.println("Quieren seguir jugando?\n" +
                        "1- Si\n" +
                        "2- No");
                String respuesta = s.next();
                if (respuesta.equals("1")){
                    finalsiono = true;
                    vaciarArray(tablero);
                }else if (respuesta.equals("2")){
                    mensajeDespedida(j1,j2,partidasGanadas1,partidasGanadas2);
                    finalsiono = false;
                }
            }
        }
    }

    public void mensajeDespedida(Jugadores j1, Jugadores j2, int partidas1, int partidas2){
        System.out.println("Muchas gracias por jugar");
        System.out.println(j1.getNombre() + " ha ganado " + partidas1 + " partidas");
        System.out.println(j2.getNombre() + " ha ganado " + partidas2 + " partidas");
    }
    public void mostrarTablero(String[][] s){

        for (int i=0;i<tablero.length;i++){
            for (int x=0; x<tablero[i].length;x++){
                if (x == 0){
                    System.out.print("|");
                }
                System.out.print(s[i][x]);
                System.out.print("|");
            }
            System.out.println("");
        }
    }
    public void bienvenidos(){
        System.out.println("\n Bienvendidos al Cuatro en linea! \n");
    }

    public void vaciarArray(String[][] s){
        for (int i=0;i<tablero.length;i++){
            for (int x=0; x<tablero[i].length;x++){
                s[i][x] = " ";
            }
        }
    }

    public void ponerFicha(String[][] s, int columna, Jugadores j){
        boolean retur = true;

        for (int i=tablero.length-1;i>0;i--){
            if (retur){
                for (int x=tablero[i].length-1; x>0;x--){
                    if (x == columna && s[i][x].equals(" ")){
                        s[i][x] = j.getFicha();
                        retur = false;
                    }
                }
            }

        }
    }

    public boolean verificarGanador(String[][] s, Jugadores ju){
        return (verificarGanadorHorizontal(s,ju) || verificarGanadorVertical(s,ju) || verificadorGanadorDiagonalIzquierda(s,ju) ||verificadorGanadorDiagonalDerecha(s,ju));
    }
    public boolean verificarGanadorHorizontal(String[][] s,Jugadores ju){
        boolean finJuego = false;
        for (int i = 1; i < s.length; i += 1) {
            for (int j = 0; j < s[i].length - 3; j += 1) {
                if (tablero[i][j].equals(ju.getFicha()) && tablero[i][j + 1].equals(ju.getFicha()) && tablero[i][j + 2].equals(ju.getFicha()) && tablero[i][j + 3].equals(ju.getFicha())) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("");
                    System.out.println("HA GANADO: " + ju.getNombre() + " CONECTO 4 \033[35mHORIZONTALMENTE!!!\u001B[0m");
                }
            }
        }
        return finJuego;
    }
    public boolean verificarGanadorVertical(String[][] s,Jugadores ju){
        boolean finJuego = false;
        for (int i = 0; i < s.length; i += 1) {
            for (int j = 0; j < s[i].length - 3; j += 1) {
                if (tablero[j][i].equals(ju.getFicha()) && tablero[j + 1][i].equals(ju.getFicha()) && tablero[j + 2][i].equals(ju.getFicha()) && tablero[j + 3][i].equals(ju.getFicha())) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("");
                    System.out.println("HA GANADO: " + ju.getNombre() + " CONECTO 4 \033[35mVERTICALMENTE!!!\u001B[0m");
                }
            }
        }
        return finJuego;
    }

    public boolean verificadorGanadorDiagonalIzquierda(String[][] s,Jugadores ju) {

        boolean finJuego = false;

        for (int i = 0; i < s.length - 4 + 1; i += 1) {
            for (int j = 0; j < s[i].length - 4 + 1; j += 1) {
                if (tablero[j][i].equals(ju.getFicha()) && tablero[j + 1][i + 1].equals(ju.getFicha()) && tablero[j + 2][i + 2].equals(ju.getFicha()) && tablero[j + 3][i + 3].equals(ju.getFicha())) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + ju.getNombre() + " CONECTO 4 \033[35mDIAGONALMENTE!!!\u001B[0m");
                }
            }
        }
        return finJuego;
    }
    public boolean verificadorGanadorDiagonalDerecha(String[][] s,Jugadores ju) {

        boolean finJuego = false;

        for (int i = s.length; i > 3; i -= 1) {
            for (int j = 0; j < s[i-1].length - 3; j += 1) {
                if (tablero[j][i - 1].equals(ju.getFicha()) && tablero[j + 1][i - 2].equals(ju.getFicha()) && tablero[j + 2][i - 3].equals(ju.getFicha()) && tablero[j + 3][i - 4].equals(ju.getFicha())) {
                    finJuego = true;
                    mostrarTablero(tablero);
                    System.out.println("HA GANADO: " + ju.getNombre() + " CONECTO 4 \033[35mDIAGONALMENTE!!!\u001B[0m");
                }
            }
        }
        return finJuego;
    }

    public boolean verificarArrayLleno(String[][] s){
        for (int i=0;i<s.length;i++){
            for (int x=0; x<s[i].length;x++){
                if (s[i][x].equals(" ")){
                    return false;
                }
            }
        }
        return true;
    }
}


