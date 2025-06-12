package aed;

public class Transaccion implements Comparable<Transaccion> {
    private int id;
    private int id_comprador;
    private int id_vendedor;
    private int monto;


    public Transaccion(int id, int id_comprador, int id_vendedor, int monto) {
        this.id = id;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.monto = monto;
    }
    
    public boolean esDeCreacion(){
        return id_comprador == 0 && monto == 1;
    }

    @Override
    public int compareTo(Transaccion otro) {
        if (this.monto != otro.monto) {
            return this.monto - otro.monto;  
        } 
        else {
            return this.id - otro.id;  
        }
    }


    @Override
    public boolean equals(Object otro){
        boolean otroEsNull = (otro == null);
        boolean claseDistinta = (otro.getClass() != this.getClass());
        
        if (otroEsNull || claseDistinta) {
            return false;
        }
        
        Transaccion t = (Transaccion)otro;
        boolean idsIguales = t.id == this.id;
        boolean compraIguales = t.id_comprador == this.id_comprador;
        boolean vendeIguales = t.id_vendedor == this.id_vendedor;
        boolean montosIguales = t.monto == this.monto;
        
        return idsIguales && compraIguales && vendeIguales && montosIguales;
    }

    public int monto() {
        return monto;
    }

    public int id_comprador() {
        return id_comprador;
    }
    
    public int id_vendedor() {
        return id_vendedor;
    }
}