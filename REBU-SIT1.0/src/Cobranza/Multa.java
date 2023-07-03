package Cobranza;
/**
 * @author angel
 */
public class Multa {
    private float monto;

    public Multa() {
    }

    public Multa(float monto) {
        this.monto = monto;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    
     public void aplicarMulta(){
         System.out.println("------------------------------------------");
         System.out.println("---Tienes una MULTA por haber cancelado---");
         System.out.println("------------------------------------------");
     }
}
