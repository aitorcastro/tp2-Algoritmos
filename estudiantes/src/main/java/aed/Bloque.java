package aed;

import java.util.ArrayList;

//import aed.ListaEnlazada.HandleLista;

public class Bloque {
    private Heap<TransaccionConHandle> txXMontos;     // MaxHeap de tx por monto 
    private int monto_total; // sin tener en cuenta la tx de cracion
    // Lo que seria un Bloque
    private ListaEnlazada<Transaccion> txs;  // Un bloque es una lista enlazada de tx
    private int cant_txs; 
    private int cant_sin_creacion;//menos las de creacion
    private int id; 



    public Bloque(int Id) {
        txs = new ListaEnlazada<>();
        cant_txs = 0;
        monto_total = 0;
        this.id = Id;
        txXMontos = new Heap<TransaccionConHandle>();
    }
    

    
    public Iterador<Transaccion> Iterador(){
        return txs.iterador();
    }
    
    public Transaccion eliminarTxMayor(){  //O(log nb)
        TransaccionConHandle txH = this.txXMontos.desencolar();
        if (!txH.Transaccion().esDeCreacion()){ 
            cant_sin_creacion = cant_sin_creacion -1;
            monto_total = monto_total - txH.Transaccion().monto();
        }
        cant_txs = cant_txs - 1;
        return txs.eliminar(txH.Handle());
    }



    public ListaEnlazada<Transaccion>.HandleLista  agregarTx(Transaccion tx){       // O(1) 
        ListaEnlazada<Transaccion>.HandleLista handle = txs.agregarAtras(tx); 
        
            if (!tx.esDeCreacion()) {       // si es de creacion no lo contamos para el Monto medio
                monto_total = monto_total + tx.monto();
                cant_sin_creacion = cant_sin_creacion +1;
            }
            cant_txs = cant_txs + 1; 
        return handle;

        }


    public  void ArmarTxXMonto(ArrayList<TransaccionConHandle> txH){   // O (nb) 
        txXMontos.heapify(txH);
    }
    



    public Transaccion[] TxsEnLista(){
        Transaccion[] lista = new Transaccion[this.cant_txs] ;
        Iterador<Transaccion> it = this.Iterador();
        int i = 0;
        while ( it.haySiguiente()) {
            lista[i] = it.siguiente();
            i = i +1 ;
        }
        return lista;
    }

    /*
     * public Transaccion[] TxsEnLista(){
        if (cant_txs != 0) {
            Transaccion[] lista = new Transaccion[this.cant_txs+1] ;
            Iterador<Transaccion> it = this.Iterador();
            int i = 0;
            while ( it.haySiguiente()) {
                lista[i] = it.siguiente();
                i = i +1 ;
            }
            return lista;
        }
        else{
            Transaccion[] lista = new Transaccion[this.cant_txs] ;
            return lista;
        }         
        
    }
     */


    public Transaccion TxMayorMonto(){
        return txXMontos.proximo().Transaccion();
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
        if (cant_sin_creacion == 0) {
            return 0;
        }
        return monto_total/cant_sin_creacion;
    }

}


