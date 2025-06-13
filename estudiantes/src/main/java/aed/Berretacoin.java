package aed;

import java.util.ArrayList;


public class Berretacoin {

    private ListaEnlazada<Bloque> berretacoin;
    private Heap<Usuario> usuariosPorMonto;
    private ArrayList<Heap<Usuario>.HandleHeap> handlesUsuarios; // handles[id-1]
    private int cant_Bloques;
    


    public Berretacoin(int n_usuarios){
        berretacoin = new ListaEnlazada<>();
        usuariosPorMonto = new Heap<Usuario>();
        cant_Bloques = 0;

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
        Bloque bloque = new Bloque(cant_Bloques);
        ArrayList<TransaccionConHandle> listaTxHandle = new ArrayList<>();
        
        for(int i=0;i<transacciones.length;i++){   //O(nb * LOG P)  
            
             //ARMAMOS LA LISTA DE TRANSACCIONES CON HANDLE
            ListaEnlazada<Transaccion>.HandleLista handle = bloque.agregarTx(transacciones[i]);     
            TransaccionConHandle txH = new TransaccionConHandle(transacciones[i], handle);
            listaTxHandle.add(txH);
            
            int montoTx = transacciones[i].monto();
            actualizarSaldoVendedor(transacciones[i],montoTx); 
            if (!transacciones[i].esDeCreacion()) {
                // ACTUALIZAR SALDO DEL COMPRADOR Y VENDEDOR EN EL HEAP    0(LOG P)
                actualizarSaldoComprador(transacciones[i],-montoTx);
             
            }
           
            
        }


        bloque.ArmarTxXMonto(listaTxHandle);  // O(nb)
        berretacoin.agregarAtras(bloque);  
        cant_Bloques = cant_Bloques + 1;

    }

    private void actualizarSaldoComprador(Transaccion tx, int montoTx){
        int id_comprador =  tx.id_comprador();
        Heap<Usuario>.HandleHeap handle_comprador = handlesUsuarios.get(id_comprador - 1);
        Usuario comprador = usuariosPorMonto.obtenerValor(handle_comprador);
        comprador.ActualizarSaldo(montoTx); 
        usuariosPorMonto.actualizarValor(handle_comprador, comprador);
    }


    private void actualizarSaldoVendedor(Transaccion tx, int montoTx){
        int id_vendedor =  tx.id_vendedor();
        Heap<Usuario>.HandleHeap handle_vendedor = handlesUsuarios.get(id_vendedor - 1);
        Usuario vendedor = usuariosPorMonto.obtenerValor(handle_vendedor);
        vendedor.ActualizarSaldo(montoTx); 
        usuariosPorMonto.actualizarValor(handle_vendedor, vendedor);
    }




    public Transaccion txMayorValorUltimoBloque(){    // H  E  C  H  O
        return berretacoin.Ultimo().TxMayorMonto();
    }



    public Transaccion[] txUltimoBloque(){           // H  E  C  H  O
        return berretacoin.Ultimo().TxsEnLista(); 
    }



    public int maximoTenedor(){                     // H  E  C  H  O  
        return usuariosPorMonto.proximo().Id();
    }


 
    public int montoMedioUltimoBloque(){            // H  E  C  H  O
        return this.berretacoin.Ultimo().MontoMedio();
    }



    public void hackearTx(){
        
        Bloque bloque = berretacoin.Ultimo();
        Transaccion tx = bloque.eliminarTxMayor();
        int montoTx = tx.monto();
        if (!tx.esDeCreacion()) {
            // ACTUALIZAR SALDO DEL COMPRADOR Y VENDEDOR EN EL HEAP    0(LOG P)
        actualizarSaldoComprador(tx, montoTx);
         
        }
        actualizarSaldoVendedor(tx, -montoTx);



    }
}
