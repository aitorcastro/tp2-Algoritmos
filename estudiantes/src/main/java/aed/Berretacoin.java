package aed;

import java.util.ArrayList;

public class Berretacoin {
    //private heap UsXsaldo;
    //private Usuario[] usuarios;
    private ListaEnlazada<Bloque> berretacoin;
    private Heap<Usuario> usuariosPorMonto;
    private ArrayList<Heap<Usuario>.HandleHeap> handlesUsuarios; // handles[id-1]
    


    public Berretacoin(int n_usuarios){
        berretacoin = new ListaEnlazada<>();
        usuariosPorMonto = new Heap<Usuario>();

        //cargar los usuarios en un array para usar heapify
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        for (int i = 0; i < n_usuarios; i++) {
            usuarios.add(new Usuario(i + 1));
        }

        //hago que usuariosPorMonto sea un heap a partir de hacer heapify a la lista de usuarios
        //dejandome el heaap de usuarios ordenado por monto en O(P)
        //y me devuelve un array con los handles de los usuarios ordenados por id 
        handlesUsuarios = usuariosPorMonto.heapify(usuarios);
    }

    public void agregarBloque(Transaccion[] transacciones){      
        //throw new UnsupportedOperationException("Implementar!");
        
    }

    public Transaccion txMayorValorUltimoBloque(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public Transaccion[] txUltimoBloque(){
        return berretacoin.Ultimo().TxsEnLista(); 
    }

    public int maximoTenedor(){
        throw new UnsupportedOperationException("Implementar!");
    }

    public int montoMedioUltimoBloque(){
        return this.berretacoin.Ultimo().MontoMedio();
    }

    public void hackearTx(){
        throw new UnsupportedOperationException("Implementar!");
        //bloque = berretacoin.ultimo
        //desencolar heap de trransacciones O(1)(encontrar) O(log  nb) (sacar raiz y actualizar)
            //me devuelve handle y tx
        //bloque.eliminartx(handle) O(1) (uso handles)
        //comprador = tx.xomprador
        //vendedor =
        //idtx = 
        //handlesUsuarios[comprador - 1] = handleComp
        //actualizarHeap(handleComp, comprador.saldo + tx.monto) O(logP) 
        //igual vendedor (-) O(logP)
    }
}
