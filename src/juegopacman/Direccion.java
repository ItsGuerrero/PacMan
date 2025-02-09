package juegopacman;

import java.util.Scanner;

public class Direccion extends Thread{
    
    private Tablero tablero;
    
    public Direccion(Tablero tablero){
        this.tablero = tablero;
    }
    
    @Override
    public void run() {
        while(!tablero.juegoTerminado()){
            Scanner sc = new Scanner(System.in);
            
            String direccionIntroducida = sc.next().toUpperCase();
            switch (direccionIntroducida) {
                case "W": tablero.setDireccionPacman("ARRIBA"); break;
                case "S": tablero.setDireccionPacman("ABAJO"); break;
                case "A": tablero.setDireccionPacman("IZQUIERDA"); break;
                case "D": tablero.setDireccionPacman("DERECHA"); break;
                default: System.out.println("Entrada inválida, usa W/A/S/D.");
            }
        }
    }
    
}
