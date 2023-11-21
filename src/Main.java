import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del Jugador 1:");
        String nombreJugador1 = scanner.nextLine();

        System.out.println("Ingrese el nombre del Jugador 2:");
        String nombreJugador2 = scanner.nextLine();

        // Crear jugadores
        Jugador jugador1 = new Jugador(nombreJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2);

        // Crear tablero
        TableroDeJuego tablero = new TableroDeJuego(jugador1, jugador2);

        // Mostrar tablero inicial
        tablero.mostrarTablero();

        // Juego
        while (true) {
            System.out.println("Turno de " + jugador1.nombre());
            realizarTurno(jugador1, tablero);

            tablero.mostrarTablero();
            if (tablero.hayGanador()) {
                System.out.println("¡" + jugador1.nombre() + " ha ganado!");
                break;
            }

            if (tablero.tableroLleno()) {
                System.out.println("¡Empate!");
                break;
            }

            System.out.println("Turno de " + jugador2.nombre());
            realizarTurno(jugador2, tablero);

            tablero.mostrarTablero();
            if (tablero.hayGanador()) {
                System.out.println("¡" + jugador2.nombre() + " ha ganado!");
                break;
            }

            if (tablero.tableroLleno()) {
                System.out.println("¡Empate!");
                break;
            }
        }

        scanner.close();
    }

    private static void realizarTurno(Jugador jugador, TableroDeJuego tablero) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la fila para colocar la ficha (0-2):");
        int fila = scanner.nextInt();

        System.out.println("Ingrese la columna para colocar la ficha (0-2):");
        int columna = scanner.nextInt();

        jugador.ponerFicha(fila, columna, tablero);
    }
}