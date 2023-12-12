import javax.swing.*;

public class ContadorVictorias extends JLabel {
    private int contador;

    public ContadorVictorias(String nombreJugador) {
        this.setText(nombreJugador + ": 0"); // Inicializamos el texto del contador
        this.contador = 0;
    }

    public void incrementarContador() {
        this.contador++;
        this.setText(this.getText().split(":")[0] + ": " + this.contador);
    }
}