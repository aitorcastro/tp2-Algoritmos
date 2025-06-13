package aed;

public class TransaccionConHandle {
    private Transaccion tx;
    private ListaEnlazada<Transaccion>.HandleLista handle;

    public TransaccionConHandle(Transaccion tx, ListaEnlazada<Transaccion>.HandleLista handle) {
        this.tx = tx;
        this.handle = handle;
    }
    public Transaccion Transaccion() {
        return this.tx;
    }

    public ListaEnlazada<Transaccion>.HandleLista Handle() {
        return this.handle;
    }

    @Override
    public int compareTo(TransaccionConHandle otra) {
        return this.tx.compareTo(otra.tx);
    }
    
}
