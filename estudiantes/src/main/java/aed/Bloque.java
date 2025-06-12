package aed;

import aed.ListaEnlazada.HandleLista;

public class Bloque {
    //private heap montos;       
    private int monto_total; // siin tener en cuenta la tx de cracion
    // Lo que seria un Bloque
    private ListaEnlazada<Transaccion> bloque;
    private int cant_txs; //menos las de creacion
    private int id;

    public Bloque() {
        bloque = new ListaEnlazada<>();
    }
    
    public void agregarTx(Transaccion tx){
        ListaEnlazada<Transaccion>.HandleLista handle = bloque.agregarAtras(tx); //agrego a LE
        if (!tx.esDeCreacion()) {    
            monto_total = monto_total + tx.monto();
            cant_txs = cant_txs +1;
        }
        TransaccionConHandle txHandle = new TransaccionConHandle(tx, handle);
    }

    public Transaccion[] TxsEnLista(){
        Transaccion[] lista = new Transaccion[this.cant_txs] ;
        int i = 0;
        while ( bloque.Ultimo() == null ) {
            lista[i] = bloque.obtener(i);
        }
        return lista;
    }


    public int Cant_txs(){
        return this.cant_txs;
    }

    public boolean esBloqueCreacion(){
        if (0 <= id && id < 3000) {
            return true;
        }
        return false; 
    }
    
    
    public int Id(){
        return this.id;
    }
    
    public int MontoMedio(){
        if (cant_txs == 0) {
            return 0;
        }
        return monto_total/cant_txs;
    }

}


