package juegopacman;

public class Pacman extends Thread {

    private String nombre = "P";
    private int ObjetivosConsumidos;
    private int vidasRestantes;
    private int posX;
    private int posY;
    private Tablero tablero;
    

    public Pacman(Tablero tablero) {
        this.tablero = tablero;
    }

    public Pacman() {

    }

    @Override
    public void run() {

        while (!tablero.juegoTerminado()) {
            tablero.moverPacman();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public String getNombre() {
        return nombre;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
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

    public int getVidasRestantes() {
        return vidasRestantes;
    }

    public void setVidasRestantes(int vidasRestantes) {
        this.vidasRestantes = vidasRestantes;
    }

    public int getObjetivosConsumidos() {
        return ObjetivosConsumidos;
    }

    public void setObjetivosConsumidos(int ObjetivosConsumidos) {
        this.ObjetivosConsumidos = ObjetivosConsumidos;
    }

}