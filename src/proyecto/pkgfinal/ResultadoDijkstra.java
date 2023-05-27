
package proyecto.pkgfinal;

import java.util.List;

class ResultadoDijkstra {
    private List<Nodo> recorrido;
    private double distanciaTotal;

    public ResultadoDijkstra(List<Nodo> recorrido, double distanciaTotal) {
        this.recorrido = recorrido;
        this.distanciaTotal = distanciaTotal;
    }

    public List<Nodo> getRecorrido() {
        return recorrido;
    }

    public double getDistanciaTotal() {
        return distanciaTotal;
    }
}















