package aed;

public class TransaccionConHandle {
    private Transaccion tx;
    private ListaEnlazada<Transaccion>.HandleLista handle;

    public TransaccionConHandle(Transaccion tx, ListaEnlazada<Transaccion>.HandleLista handle) {
        this.tx = tx;
        this.handle = handle;
    }
    
}
