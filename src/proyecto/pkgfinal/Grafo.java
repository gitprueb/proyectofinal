
package proyecto.pkgfinal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Grafo {
    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Grafo() {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void agregarNodo(Nodo nodo) {
        nodos.add(nodo);
    }

    public void agregarArista(Nodo origen, Nodo destino, double peso) {
        Arista arista = new Arista(origen, destino, peso);
        aristas.add(arista);
    }

    public List<Nodo> obtenerNodos() {
        return nodos;
    }

    public List<Arista> obtenerAristasSalientes(Nodo nodo) {
        List<Arista> aristasSalientes = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.getOrigen() == nodo) {
                aristasSalientes.add(arista);
            }
        }
        return aristasSalientes;
    }

    public Nodo obtenerNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equalsIgnoreCase(nombre)) {
                return nodo;
            }
        }
        return null;
    }
}




