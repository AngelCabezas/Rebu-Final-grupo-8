package LogicaSistema;

public class Viaje {

    private String ruta;

    public Viaje(String ruta) {
        this.ruta = ruta;
    }

    public Viaje() {
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void buscarConductor(){
        System.out.println("Buscando CONDUCTOR");
          
    }
    public void mostrarDatosConductor(){
        Conductor conductor = new Conductor();
            System.out.println("su conductor se llama:" + conductor.nombreConductor());
            System.out.println("con C.I:" + conductor.cedulaConductor());
            System.out.println("");
    }
}
