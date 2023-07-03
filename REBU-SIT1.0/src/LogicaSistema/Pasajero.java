package LogicaSistema;

import Cobranza.Multa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Pasajero extends Usuario {
    private String puntoLlegada;
    private String puntoSalita;

    public Pasajero(String puntoLlegada, String puntoSalita) {
        this.puntoLlegada = puntoLlegada;
        this.puntoSalita = puntoSalita;
    }

    public Pasajero(String puntoLlegada, String puntoSalita, int id_Cedula, String nombUsuario, String contrasenia, String correoElectronico, String tipoUsuario) {
        super(id_Cedula, nombUsuario, contrasenia, correoElectronico, tipoUsuario);
        this.puntoLlegada = puntoLlegada;
        this.puntoSalita = puntoSalita;
    }

    public Pasajero() {
    }

    public String getPuntoLlegada() {
        return puntoLlegada;
    }

    public void setPuntoLlegada(String puntoLlegada) {
        this.puntoLlegada = puntoLlegada;
    }

    public String getPuntoSalita() {
        return puntoSalita;
    }

    public void setPuntoSalita(String puntoSalita) {
        this.puntoSalita = puntoSalita;
    }

    
    
    Scanner scanner=new Scanner(System.in);
          
    public void seleccionarPuntoLlegadaySalida() {
        System.out.println("Ingrese su punto de partida:");
        String Salida = scanner.nextLine();
        System.out.println("Ingrese su punto de Llegada:");
        String Llegada = scanner.nextLine();

        System.out.println("su punto de partida es: " + Salida + " su punto de Llegada es:" + Llegada);
        try {
            Thread.sleep(1000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void seleccionarRuta() {
        System.out.println("");
        System.out.println("SELECCIONA UNA RUTA:");
        Ruta ruta = new Ruta();
        ruta.calcularCostosRutas();
        System.out.println("");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1) {
            System.out.println("has escogido la ruta Express ");
            System.out.println("");
        } else {
            System.out.println("has escogido la ruta Normal ");
            System.out.println("");

        }
    }
    


    public boolean confirmarViaje() {
        if (solicitarViaje()) {
            System.out.println("------------------------------");
            System.out.println("Su viaje se realiza con normalidad");
            System.out.println("------------------------------");

            try {
                Thread.sleep(1000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
               
        } else {
            System.out.println("En busca de un nuevo conductor");
            seleccionarRuta();
            return false;
        }
    }
    
    public void agendarViaje(){
        
        System.out.println("\nIngrese fecha de viaje (DD/MM/AA)");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese la hora:");
        String hora = scanner.nextLine();
        System.out.println("Se ha regisrado correctamente la fecha y hora");
        
        
        try {
            Thread.sleep(2000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nIngrese su punto de partida:");
        String Salida = scanner.nextLine();
        System.out.println("Ingrese su punto de Llegada:");
        String Llegada = scanner.nextLine();

        
        try {
            Thread.sleep(1000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        seleccionarRuta();
        
        System.out.println("Se agendado correctamente el viaje");
        System.out.println("Su viaje se realizara la fecha:"+fecha+ " con hora:"+hora );
        System.out.println("su punto de partida es: " + Salida + " su punto de Llegada es:" + Llegada);
        
        try {
            Thread.sleep(2000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        System.out.println("\nDesea cancelar la multa:");
        System.out.println("1.- <--SI-->");
        System.out.println("2.- <--NO-->");
        String multa=scanner.nextLine();
        
        if(multa.equalsIgnoreCase("1")){
            Multa mul=new Multa();
            mul.aplicarMulta();
        }else{
            System.out.println("\nGracias por confiar en nosotros");
            //System.exit(0);
        }
        
        try {
            Thread.sleep(4000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
    }
    

    public boolean solicitarViaje() {
        System.out.println("¿La persona que llego es la misma registrada en el sistema?");
        System.out.println("1.- Si ");
        System.out.println("2.- No");
        int opcion = Integer.parseInt(scanner.nextLine());
        if (opcion == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void pagar() {
        System.out.println("--------------------------------"); 
        System.out.println("----->Es momento de pagar<------"); 
        System.out.println("----->Cancela al conductor<-----"); 
        System.out.println("--------------------------------");
        System.out.println("Gracias por la confianza ");
        System.exit(0);
    }
    
    
    public void solicitarViajeCompartido(int num) {
        seleccionarPuntoLlegadaySalida();
        seleccionarRuta();
        try {
            Thread.sleep(1000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        System.out.println("Tus acompañantes seran:");
        List<String> nombres = nombresPasajeros(num);
        for (String nombre : nombres) {
            System.out.println(nombre);
        }

    }
    private List<String> nombresPasajeros(int cantidad) {
    List<String> nombresAleatorios = new ArrayList<>();
    List<String> nombres = Arrays.asList("Juan Acosta", 
            "María Llerena", "Pedro Infante", "Luisa Echeniche", "Carlos Jacome");

    Random random = new Random();
    for (int i = 0; i < cantidad; i++) {
        int indiceAleatorio = random.nextInt(nombres.size());
        nombresAleatorios.add(nombres.get(indiceAleatorio));
    }

    return nombresAleatorios;
}

    public  int generarNumeroAleatorio() {
    Random random = new Random();
    int numeroAleatorio = random.nextInt(3) + 1;
    return numeroAleatorio;
}
}
