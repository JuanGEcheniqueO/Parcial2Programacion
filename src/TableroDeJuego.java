public class TableroDeJuego {
    private char[][] tablero;
    private Jugador jugador1;
    private Jugador jugador2;
    private ContadorVictorias contadorJugador1;
    private ContadorVictorias contadorJugador2;
    private int turno;

    public TableroDeJuego(Jugador jugador1, Jugador jugador2) {
        this.tablero = new char[3][3];
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        new ContadorVictorias(jugador1.getNombre());
        new ContadorVictorias(jugador2.getNombre());
        inicializarTablero();
        this.turno = 0;
    }

    private void inicializarTablero() {
        // Inicializa el tablero con espacios en blanco
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
    }

    public boolean esCasillaVacia(int fila, int columna) {
        return tablero[fila][columna] == ' ';
    }

    public char obtenerSimboloJugador(Jugador jugador) {
        return (jugador == jugador1) ? 'X' : 'O';
    }

    public void colocarFicha(int fila, int columna, char simbolo) {
        tablero[fila][columna] = simbolo;
    }

    public boolean tableroLleno() {
        // Verificar si todas las casillas están ocupadas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false; // Hay al menos una casilla vacía
                }
            }
        }
        return true; // Todas las casillas están ocupadas
    }
    public boolean hayGanador() {
        // Verificar filas, columnas y diagonales para determinar si hay un ganador
        if (verificarFilas() || verificarColumnas() || verificarDiagonales()) {
            mostrarGanador();
            return true; // Hay un ganador
        }
        return false; // No hay ganador aún
    }

    private void mostrarGanador() {
        System.out.println("¡Tenemos un ganador!");
    }

    private boolean verificarFilas() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != ' ' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {
                return true; // Hay un ganador en la fila i
            }
        }
        return false;
    }

    private boolean verificarColumnas() {
        for (int j = 0; j < 3; j++) {
            if (tablero[0][j] != ' ' && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {
                return true; // Hay un ganador en la columna j
            }
        }
        return false;
    }

    private boolean verificarDiagonales() {
        if (tablero[0][0] != ' ' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) {
            return true; // Hay un ganador en la diagonal principal
        }

        if (tablero[0][2] != ' ' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]) {
            return true; // Hay un ganador en la diagonal secundaria
        }

        return false;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void reiniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
            }
        }
        turno = 0;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public int getTurno() {
        return turno;
    }

    public void incrementarTurno() {
        turno++;
    }
}