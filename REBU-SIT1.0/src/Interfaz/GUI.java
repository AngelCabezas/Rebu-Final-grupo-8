package Interfaz;

import LogicaSistema.Conductor;
import LogicaSistema.Pasajero;
import LogicaSistema.Usuario;
import LogicaSistema.Viaje;
import java.util.Scanner;

public class GUI {
    private static Scanner scanner;
    private static boolean sesionIniciada;
    private static Usuario us=new Usuario();
    private static Pasajero pasajero=new Pasajero();
    private static Viaje viaje=new Viaje();

    public static void main(String[] args) {
        
        Pasajero pasajero= new Pasajero();
        scanner = new Scanner(System.in);
        sesionIniciada = false;

        System.out.println("REBU-SIT");
        System.out.println();
        mostrarLogin();
        
        if (sesionIniciada) {
            mostrarLogin();
        }

        scanner.close();
    }

    public static  void mostrarLogin() {
        int opcionMenuPrincipal;
        do {
            System.out.println("");
            System.out.println("------------------------------------------");
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Crear usuario");
            System.out.println("0. Salir");
            
            System.out.println("");
            System.out.print("Ingrese una opción: ");

            opcionMenuPrincipal = scanner.nextInt();
            scanner.nextLine(); // Limpiar el salto de línea

            switch (opcionMenuPrincipal) {
                case 1:
                    if(us.iniciarSesion()){
                        System.out.println("0. Exitoso");
                        if(us.validarTipoUsuario()){
                            mostrarMenuPasajero();
                        }else{
                            mostrarMenuConductor();
                        }
                    }else{
                        us.iniciarSesion();
                    }
                    
                    break;
                case 2:
                    Usuario.registrarUsuario();
                    mostrarLogin();
                    break;
            }
            System.out.println();
        } while (opcionMenuPrincipal != 0 && !sesionIniciada);
    }

   

    private static void mostrarMenuPasajero() {
        int variable;
        System.out.println("");
        System.out.println("Has seleccionado la opción 1.");
        System.out.println("");
        System.out.println("----HAS ELEGIDO SER PASAJERO -----");
        System.out.println("ELIGE EL TIPO DE SERVICIO QUE DESEAS SOLICITAR");
        System.out.println("1.- SOLICITAR VIAJE");
        System.out.println("2.- AGENDAR VIAJE");
        System.out.println("3.- SOLICITAR VIAJE COMPARTIDO");
        System.out.println("4.- REGRESAR A MENU PRINCIPAL");
        System.out.println("");
        System.out.print("Ingrese una opción: ");
        variable = scanner.nextInt();

        switch (variable) {
            case 1:{
                System.out.println("1.- SOLICITAR VIAJE");              
                pasajero.seleccionarPuntoLlegadaySalida();
                pasajero.seleccionarRuta();
                viaje.buscarConductor();
                
                try {
                    Thread.sleep(3000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                viaje.mostrarDatosConductor();
                viaje.mostrarDatosConductor();
                if(pasajero.confirmarViaje()){
                    pasajero.confirmarViaje();
                    pasajero.pagar();
                }else{
                    try {
                    Thread.sleep(3000); // Pausa la ejecución durante 3 segundos (3000 milisegundos)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    viaje.buscarConductor();
                    viaje.mostrarDatosConductor();
                    pasajero.confirmarViaje();
                    pasajero.pagar();
                }
                
                break;
            }
            case 2:{
                System.out.println("\n 2.- AGENDAR VIAJE");
                pasajero.agendarViaje();
                mostrarMenuPasajero();
                break;
            }
            case 3:{
                System.out.println("3.- SOLICITAR VIAJE COMPARTIDO");
                int x=pasajero.generarNumeroAleatorio();
                pasajero.solicitarViajeCompartido(x);
                break;
            }
            case 4:{
                System.out.println("4.- REGRESAR A MENU PRINCIPAL");
                mostrarLogin();
            }
        }
    }

    private static void mostrarMenuConductor() {
        System.out.println("");
        System.out.println("------------------------------------------");
        System.out.println("");
        int variable2;
        System.out.println("----- HAS ELEGIDO SER CONDUCTOR -----");
        System.out.println("ELIGE EL TIPO DE SERVICIO QUE VAS A SOLICITAR");
        System.out.println("1.- OFRECER VIAJE");
        System.out.println("2.- REGRESAR A MENU PRINCIPAL");
        
        System.out.println("");
        System.out.print("Ingrese una opción: ");
        variable2 = scanner.nextInt();

        switch (variable2) {
            case 1:{
                System.out.println("1.- Ofrecer viaje");
                Conductor conductor = new Conductor();
                conductor.aceptarViaje();
                break;
            }
            case 2:{
                System.out.println("2.- REGRESAR A MENU PRINCIPAL");
                mostrarLogin();
                break;
            }
        }
    }
}
