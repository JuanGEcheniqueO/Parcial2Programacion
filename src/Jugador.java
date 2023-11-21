import java.util.Scanner;
public record Jugador(String nombre) {
    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public void ponerFicha(int fila, int columna, TableroDeJuego tablero) {
        boolean posicionValida = false;

        while (!posicionValida) {
            // Verificar si la posición está ocupada
            if (tablero.esCasillaVacia(fila, columna)) {
                // Obtener el símbolo del jugador (X o O)
                char simbolo = tablero.obtenerSimboloJugador(this);

                // Colocar la ficha en el tablero
                tablero.colocarFicha(fila, columna, simbolo);

                // La posición es válida, salir del bucle
                posicionValida = true;
            } else {
                System.out.println("La posición está ocupada. Elige otra.");
                // Volver a solicitar la entrada del usuario
                System.out.println("Ingrese la fila para colocar la ficha (0-2):");
                fila = scanner.nextInt();
                System.out.println("Ingrese la columna para colocar la ficha (0-2):");
                columna = scanner.nextInt();
            }
        }
    }
}

