package LogicaSistema;

import java.util.Random;

public class Ruta {
    private String distancia;
    private int costo;

    public Ruta(String distancia, int costo) {
        this.distancia = distancia;
        this.costo = costo;
    }

    public Ruta() {
    }
    

    public String getDistancia() {
        return distancia;
    }

    public int getCosto() {
        return costo;
    }
    public void setCosto(int costo) {
        this.costo = costo;
    }

    public void calcularCostosRutas() {
        Ruta ruta1 = generarRutaAleatoria();
        Ruta ruta2 = generarRutaAleatoria();

        System.out.println("1-Ruta Express: " + ruta1.getDistancia() + ", Costo: " + costRuta1());
        System.out.println("2-Ruta Normal: " + ruta2.getDistancia() + ", Costo: " + costRuta2());
    }
    
    private int costRuta1(){
        return num();
    }
    
    
    private int costRuta2(){
        int y;
        y=(costRuta1()-3);
        return y;
    }
    /*private int costooo(){
        setCosto(costRuta1);
    }*/

    private static Ruta generarRutaAleatoria() {
        Random random = new Random();
        int longitudRuta = random.nextInt(10) + 3; // Genera una longitud aleatoria entre 1 y 10
        StringBuilder ruta = new StringBuilder();

        for (int i = 0; i < longitudRuta; i++) {
            char direccion = (char) (random.nextInt(6) + 'A'); // Genera una dirección aleatoria (A, B, C, D)
            ruta.append(direccion);
        }

        int numero = random.nextInt(9) + 1; // Genera un número aleatorio del 1 al 9

        return new Ruta(ruta.toString(), numero);
    }
    private static int num(){
        Random random = new Random();
        int numero = random.nextInt(9) + 4;
        return numero;
    }

    
     
    
    
    
}
