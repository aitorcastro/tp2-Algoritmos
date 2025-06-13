package aed;

public class ListaEnlazada<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    private class Nodo {
        T dato;
        Nodo anterior;
        Nodo siguiente;

        Nodo(T d) {
            dato = d;
            anterior = null;
            siguiente = null;
        }
    }

    public class HandleLista {
        private Nodo nodo;

        private HandleLista(Nodo nodo) {
            this.nodo = nodo;
        }

        public T obtenerValor() {
            return nodo.dato;
        }
    }

    public ListaEnlazada() {
        //throw new UnsupportedOperationException("No implementada aun");
        primero = null;
        ultimo = null;
        longitud = 0;
    }

    public int longitud() {
        //throw new UnsupportedOperationException("No implementada aun");
        return longitud;
    }

    public HandleLista agregarAtras(T elem) {
        //throw new UnsupportedOperationException("No implementada aun");
        Nodo nuevoNodo = new Nodo(elem);
        if (ultimo == null){
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        }
        else {
            nuevoNodo.anterior = ultimo;
            ultimo.siguiente = nuevoNodo;
            ultimo = nuevoNodo;
        }
        longitud++;
        return new HandleLista(nuevoNodo);
    }

    public T obtener(int i) {
        //throw new UnsupportedOperationException("No implementada aun");
        Nodo actual = primero;
        for(int j = 0; j < i; j++){
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public T obtenerConHandle(HandleLista handle) {
        return handle.obtenerValor();
    }

    public T Ultimo(){
        return ultimo.dato;
    }

    public T eliminar(HandleLista handle) {
        //throw new UnsupportedOperationException("No implementada aun");
        T eliminado = handle.obtenerValor();
        if (longitud == 1){
            primero = null;
            ultimo = null;
        }
        else if(handle.nodo.anterior == null){
            primero = primero.siguiente;
            primero.anterior = null;
        }
        else if(handle.nodo.siguiente == null){
            ultimo = ultimo.anterior;
            ultimo.siguiente = null;
        }
        else{
            Nodo actual = handle.nodo;
            actual.anterior.siguiente = actual.siguiente;
            actual.siguiente.anterior = actual.anterior;
        }
        longitud--;
        handle.nodo = null;
        return eliminado;
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados
        private Nodo anterior;
        private Nodo siguiente;

        public ListaIterador(){
            anterior = null;
            siguiente = primero;
        }

        public boolean haySiguiente() {
	        //throw new UnsupportedOperationException("No implementada aun");
            return siguiente != null;
        }
        
        public boolean hayAnterior() {
	        //throw ew UnsupportedOperationException("No implementada aun");
            return anterior != null;
        }

        public T siguiente() {
	        //throw new UnsupportedOperationException("No implementada aun");
            T dato = siguiente.dato;
            anterior = siguiente;
            siguiente = siguiente.siguiente;
            return dato;
        }
        

        public T anterior() {
	        //throw new UnsupportedOperationException("No implementada aun");
            T dato = anterior.dato;
            siguiente = anterior;
            anterior = anterior.anterior;
            return dato;
        }
    }

    public Iterador<T> iterador() {
	    //throw new UnsupportedOperationException("No implementada aun");
        return new ListaIterador();
    }

}