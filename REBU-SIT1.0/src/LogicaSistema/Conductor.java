package LogicaSistema;

import Cobranza.Multa;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Conductor extends Usuario {

    private int placa;

    public Conductor() {
    }

    public Conductor(int id_Cedula, String nombUsuario, String contrasenia, String correoElectronico, String tipoUsuario) {
        super(id_Cedula, nombUsuario, contrasenia, correoElectronico, tipoUsuario);
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }
    

    Scanner scanner = new Scanner(System.in);

    public String nombreConductor() {
        String nombreAleatorio = ConductorAleatorio();
        return nombreAleatorio;
    }
    
    public String cedulaConductor() {
        String cadenaAleatoria = CedulaAleatoria();
        return cadenaAleatoria;
    }

    private String salidaLlegada() {
        String puntoSalidaLlegada = puntoSalidaLlegadaAleatorio();
        return puntoSalidaLlegada;
    }

    public void aceptarViaje() {
        System.out.println("");
        System.out.println("BUSCANDO POSIBLES VIAJES");
        System.out.println("");
        try {
            Thread.sleep(4000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("");
        System.out.println("Existe un viaje con punto de partida y llegada en:");
        String puntoSalidaLlegada = puntoSalidaLlegadaAleatorio();
        System.out.println(puntoSalidaLlegada);
        try {
            Thread.sleep(2000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Aceptar viaje:");
        System.out.println("1-->SI");
        System.out.println("2-->NO");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1) {
            System.out.println("");
            System.out.println("Dirigete al punto de partida ");
            try {
                Thread.sleep(2000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Deseas continuar con el viaje:");
            System.out.println("1-->SI");
            System.out.println("2-->NO");
            int op = Integer.parseInt(scanner.nextLine());
            if (op == 2) {
                cancelarViaje();
            } else {
                System.out.println("Recoge el pasajero ");
            }
            try {
                Thread.sleep(2000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("");
            cobrar();
            try {
                Thread.sleep(2000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Viaje finalizado");
        } else {
            aceptarViaje();
        }

    }

    public void cancelarViaje() {
        Multa multa=new Multa();
        multa.aplicarMulta();
        aceptarViaje();
    }

    public void cobrar() {
        System.out.println("------HA LLEGADO AL DESTINO-----");
        System.out.println("--------------------------------"); 
        System.out.println("---->Es momento de cobrar<------"); 
        System.out.println("--------------------------------");
    }

    private static String puntoSalidaLlegadaAleatorio() {
        List<String> salidaLlegada = new ArrayList<>();
        salidaLlegada.add("EPN ---> Alameda");
        salidaLlegada.add("UCE ---> Condado");
        salidaLlegada.add("Marin ---> Floresta");
        salidaLlegada.add("Carlos Andrade Marin ---> Calderon");
        salidaLlegada.add("San Luis Shopin ---> Terminal Quitumbe");

        Random random = new Random();
        int indiceAleatorio = random.nextInt(salidaLlegada.size());
        return salidaLlegada.get(indiceAleatorio);
    }

    private static String ConductorAleatorio() {
        List<String> nombres = new ArrayList<>();
        nombres.add("Juan Acosta");
        nombres.add("María Llerena");
        nombres.add("Pedro Infante");
        nombres.add("Luisa Echeniche");
        nombres.add("Carlos Jacome");

        Random random = new Random();
        int indiceAleatorio = random.nextInt(nombres.size());
        return nombres.get(indiceAleatorio);
    }

    private static String CedulaAleatoria() {
        StringBuilder cadena = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 9; i++) {
            int numeroAleatorio = random.nextInt(10); // Genera un número aleatorio entre 0 y 9
            cadena.append(numeroAleatorio);
        }

        return cadena.toString();
    }

}
