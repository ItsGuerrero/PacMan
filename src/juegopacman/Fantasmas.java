package juegopacman;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

public class Fantasmas extends Thread {

    private String nombre = "F";
    private int posX;
    private int posY;

    private int posXold;
    private int posyold;

    private int posXinicial;
    private int posYinicial;

    private Tablero tablero;
    private Random random;

    public Fantasmas(Tablero tablero) {
        this.tablero = tablero;
        this.random = new Random();
        fantasmasRandoms();

        this.posXinicial = posX;
        this.posYinicial = posY;

    }

    public Fantasmas(Tablero tablero, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.tablero = tablero;
        this.random = new Random();

        this.posXinicial = posX;
        this.posYinicial = posY;
        tablero.getTablero()[posX][posY] = getNombre();
    }

    @Override
    public void run() {
        while (!tablero.juegoTerminado()) {
            moverFantasma(posX, posY, posXold, posXold);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fantasmas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getPosXold() {
        return posXold;
    }

    public void setPosXold(int posXold) {
        this.posXold = posXold;
    }

    public int getPosYold() {
        return posyold;
    }

    public void setPosYold(int posyold) {
        this.posyold = posyold;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Este metodo coge los parametros seleccionados y permite mover a los
     * fantasmas dejando atras "." o " " según lo q habia, controla q no pueda
     * atravesar paredes
     *
     * @param posX
     * @param posY
     * @param posXold
     * @param posYold
     */
    public void moverFantasma(int posX, int posY, int posXold, int posYold) {
        int movimiento = random.nextInt(4) + 1;

        int posXnew = posX;
        int posYnew = posY;

        switch (movimiento) {
            case 1: // Movimiento hacia arriba
                posXnew--;
                break;
            case 2: // Movimiento hacia la derecha
                posYnew++;
                break;
            case 3: // Movimiento hacia abajo
                posXnew++;
                break;
            case 4: // Movimiento hacia la izquierda
                posYnew--;
                break;
        }
        if (tablero.esMovimientoValido(posXnew, posYnew, nombre)) {

            if (tablero.getTablero()[posXnew][posYnew].equals(" ")) {
                tablero.getTablero()[posXnew][posYnew] = getNombre();
                tablero.getTablero()[posX][posY] = " ";
            } else {
                tablero.getTablero()[posXnew][posYnew] = getNombre();
                tablero.getTablero()[posX][posY] = ".";
            }

            setPosX(posXnew);
            setPosY(posYnew);

        }
    }

    /**
     * Metodo q genera posiciones randoms para los fantasmas con cada inicio
     * de una partida nueva, este se metera en el constructor del fantasma
     * si queremos que se cree con las posiciones random
     * 
     */
    public void fantasmasRandoms() {
        String[][] matriz = tablero.getTablero();
        boolean posicionEncontrada = false;

        while (!posicionEncontrada) {
            int posXrandom = random.nextInt(14) + 1;
            int posYrandom = random.nextInt(18) + 1;

            if (!matriz[posXrandom][posYrandom].equals("F")
                    && !matriz[posXrandom][posYrandom].equals("|")
                    && !matriz[posXrandom][posYrandom].equals("_")
                    && !matriz[posXrandom][posYrandom].equals(tablero.getObjetivos())
                    && !matriz[posXrandom][posYrandom].equals("P")) {

                matriz[posXrandom][posYrandom] = getNombre();
                setPosX(posXrandom);
                setPosY(posYrandom);
                posicionEncontrada = true;
            }
        }
    }

    /**
     * Metodo que resetea las posicones actuales de los personajes 
     * y los pone en su posicion inicial
     * 
     */
    public void resetPosicion() {
        tablero.getTablero()[posX][posY] = " ";
        this.posX = posXinicial;
        this.posY = posYinicial;
        tablero.getTablero()[posXinicial][posYinicial] = nombre;
    }
}