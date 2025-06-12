package aed;
import java.util.ArrayList;

public class Heap<T extends Comparable<T>>{
    private ArrayList<Elemento> datos;

    // Clase interna para manejar elementos y sus handles
    private class Elemento {
        T valor;
        HandleHeap handle;

        Elemento(T valor) {
            this.valor = valor;
            this.handle = new HandleHeap();
        }
    }

    // Clase pública para handles
    public class HandleHeap {
        private int indice;

        public T obtenerValor() {
            return datos.get(indice).valor;
        }
    }
    
    public Heap() {
        datos = new ArrayList<>();
    }

 //definimos metodos auxiliares para facilitar la lectura
   //metodos para obtener el indice dado un indice
    private int indiceHijoIzq(int indice) { return 2 * indice + 1;}
    private int indiceHijoDer(int indice) { return 2 * indice + 2;}
    private int indicePadre(int indice) { return (indice - 1) / 2;}

   //metodos booleanos para ver si tiene o no padra/hijo
    private boolean tieneHijoIzq(int indice) { return indiceHijoIzq(indice) < datos.size(); } 
    private boolean tieneHijoDer(int indice) { return indiceHijoDer(indice) < datos.size(); }
    private boolean tienePadre(int indice) { return indicePadre(indice) >= 0; } //false si es raiz

    //metodos para obtener el valor dado un indice
    private T hijoIzq(int indice) { return datos.get(indiceHijoIzq(indice)).valor; }
    private T hijoDer(int indice) { return datos.get(indiceHijoDer(indice)).valor; }
    private T padre(int indice) { return datos.get(indicePadre(indice)).valor; }

   //indice del hijo mas grande dado un indice
    private int indiceHijoMasGrande(int indice) {
        int res = indiceHijoIzq(indice);
        if (tieneHijoDer(indice) && hijoDer(indice).compareTo(hijoIzq(indice)) > 0) {
            res = indiceHijoDer(indice);
        }
        return res;
    }

    //actualizar índices al intercambiar elementos
    private void intercambiar(int indice1, int indice2) {
        Elemento temp = datos.get(indice1);
        //actualizar los elementos
        datos.set(indice1, datos.get(indice2));
        datos.set(indice2, temp);

        //actualizar los indices de los handles
        datos.get(indice1).handle.indice = indice1;
        datos.get(indice2).handle.indice = indice2;
    }

   //subir un elemento que agregamos al final mientras sea mayor que su padre
    private void siftUp(int indice) {
        while (tienePadre(indice) && datos.get(indice).valor.compareTo(padre(indice)) > 0) {
            intercambiar(indicePadre(indice), indice);
            indice = indicePadre(indice);
        }
    }

   //bajar un elemento que agregamos en la raiz mientras sea menor que sus hijos
    private void siftDown(int indice) {
        while (tieneHijoIzq(indice) && datos.get(indice).valor.compareTo(datos.get(indiceHijoMasGrande(indice)).valor) < 0) {
            intercambiar(indice, indiceHijoMasGrande(indice));
            indice = indiceHijoMasGrande(indice);
        }
    }

 //metodos para implementar el heap
   //ver el elemento de maxima prioridad
    public T proximo() {
        return datos.get(0).valor;
    }

   //metodo para encolar (agregar un elemento) devuelve un handle
    public HandleHeap encolar(T elemento) {
        Elemento nuevoElemento = new Elemento(elemento);
        datos.add(nuevoElemento); //agrega al final

        nuevoElemento.handle.indice = datos.size() - 1;
        siftUp(nuevoElemento.handle.indice);
        return nuevoElemento.handle;
    }

   //eliminar la raiz (desencolar si hablamos de una cola de prioridad)
    public T desencolar() {
        T primero = datos.get(0).valor;
        Elemento ultimo = datos.remove(datos.size() - 1); //guardar el valor del ultimo elemento y eliminarla
        
        //reemplazar a la raiz con la ultima hoja
        if (!datos.isEmpty()) {
            datos.set(0, ultimo);
            ultimo.handle.indice = 0;
            siftDown(0);
        }
        return primero;
    }
    
   //"heapificar" para armar el heap a partir de un array
    public ArrayList<Heap<T>.HandleHeap> heapify(ArrayList<T> lista) {
        datos = new ArrayList<>();
        ArrayList<HandleHeap> handles = new ArrayList<>();
        //guardo los elementos en datos como vienen
        for (int i = 0; i < lista.size(); i++) {
            Elemento nuevo = new Elemento(lista.get(i));
            datos.add(nuevo);
            nuevo.handle.indice = datos.size() - 1;
            handles.add(nuevo.handle);
        }

        //si lo pensamos como un arbol, empiezo en el ultimo nodo no-hoja del arbol y voy haciendo sift down en cada uno (algoritmo de Floyd)
        for (int i = datos.size() / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }

        return handles;
    }

    //cambiar el valor ordenar si hace falta para no romper el invariante del heap
    public void actualizarValor(HandleHeap handle, T nuevoValor) {
        int indice = handle.indice;
        datos.get(indice).valor = nuevoValor;

        //aseguro que se siga manteniendo el invariante del heap
        siftUp(indice);
        siftDown(indice);
    }
    

}
