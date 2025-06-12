package aed;

public class Usuario implements Comparable<Usuario> {
    private int id;
    private int saldo;
    //private int handle;

    public Usuario(int id){
        this.id = id;
        this.saldo = 0;
    }

    public int Saldo(){
        return this.saldo;
    }

    public int Id(){
        return this.id;
    }

    public void ActualizarSaldo(int saldo){
        this.saldo = this.saldo + saldo;
    }

    @Override 
    public int compareTo(Usuario otro){
        if (this.saldo != otro.saldo) {
            return this.saldo - otro.saldo;  
        } 
        else {
            return otro.id - this.id; 
        }
    }
    
}