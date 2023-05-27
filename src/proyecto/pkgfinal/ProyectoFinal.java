
package proyecto.pkgfinal;
import java.util.*;

public class ProyectoFinal {
    public static void main(String[] args) {
        // Crear grafo de ejemplo
        Grafo grafo = new Grafo();

        // Crear nodos
        Nodo nodoA = new Nodo("Santa Cruz del Quiche");
        Nodo nodoB = new Nodo("Chichicastenango");
        Nodo nodoC = new Nodo("Chiche");
        Nodo nodoD = new Nodo("Chinique");
        Nodo nodoE = new Nodo("San Antonio Ilotenango");
        Nodo nodoF = new Nodo("San Pedro Jocopilas");
        Nodo nodoG = new Nodo("Patzite");
        Nodo nodoH = new Nodo("Zacualpa");
        Nodo nodoI = new Nodo("Joyabaj");
        Nodo nodoJ = new Nodo("Pachalum");
        Nodo nodoK = new Nodo("Canilla");
        Nodo nodoL = new Nodo("San Andres Sajcabaja");
        Nodo nodoM = new Nodo("San Bartolome Jocotenango");
        Nodo nodoN = new Nodo("Sacapulas");
        Nodo nodoO = new Nodo("Cunen");
        Nodo nodoP = new Nodo("San Juan Cotzal");
        Nodo nodoQ = new Nodo("Uspantan");
        Nodo nodoR = new Nodo("Chicaman");
        Nodo nodoS = new Nodo("Chajul");
        Nodo nodoT = new Nodo("Nebaj");
        Nodo nodoU = new Nodo("Ixcan");

        // Agregar nodos al grafo
        grafo.agregarNodo(nodoA);
        grafo.agregarNodo(nodoB);
        grafo.agregarNodo(nodoC);
        grafo.agregarNodo(nodoD);
        grafo.agregarNodo(nodoE);
        grafo.agregarNodo(nodoF);
        grafo.agregarNodo(nodoG);
        grafo.agregarNodo(nodoH);
        grafo.agregarNodo(nodoI);
        grafo.agregarNodo(nodoJ);
        grafo.agregarNodo(nodoK);
        grafo.agregarNodo(nodoL);
        grafo.agregarNodo(nodoM);
        grafo.agregarNodo(nodoN);
        grafo.agregarNodo(nodoO);
        grafo.agregarNodo(nodoP);
        grafo.agregarNodo(nodoQ);
        grafo.agregarNodo(nodoR);
        grafo.agregarNodo(nodoS);
        grafo.agregarNodo(nodoT);
        grafo.agregarNodo(nodoU);

        // Agregar aristas con pesos al grafo
        grafo.agregarArista(nodoA, nodoB, 25.4);
        grafo.agregarArista(nodoA, nodoC, 12.7);
        grafo.agregarArista(nodoA, nodoD, 8.9);
        grafo.agregarArista(nodoA, nodoE, 15.2);
        grafo.agregarArista(nodoA, nodoF, 10.5);
        grafo.agregarArista(nodoB, nodoG, 6.3);
        grafo.agregarArista(nodoB, nodoH, 14.8);
        grafo.agregarArista(nodoC, nodoI, 9.1);
        grafo.agregarArista(nodoD, nodoJ, 5.6);
        grafo.agregarArista(nodoE, nodoK, 11.2);
        grafo.agregarArista(nodoF, nodoL, 7.9);
        grafo.agregarArista(nodoG, nodoM, 13.4);
        grafo.agregarArista(nodoH, nodoN, 4.3);
        grafo.agregarArista(nodoI, nodoO, 8.7);
        grafo.agregarArista(nodoJ, nodoP, 3.2);
        grafo.agregarArista(nodoK, nodoQ, 10.9);
        grafo.agregarArista(nodoL, nodoR, 6.5);
        grafo.agregarArista(nodoM, nodoS, 9.3);
        grafo.agregarArista(nodoN, nodoT, 5.8);
        grafo.agregarArista(nodoO, nodoU, 12.1);

         Scanner scanner = new Scanner(System.in);
        String opcion = null;

        do {
            // Obtener el nodo de origen y destino desde el usuario
            System.out.print("Ingrese el nodo de origen (o '0' para salir): ");
            String origen = scanner.nextLine();

            if (origen.equals("0")) {
                break;
            }

            System.out.print("Ingrese el nodo de destino: ");
            String destino = scanner.nextLine();

            Nodo nodoOrigen = grafo.obtenerNodo(origen);
            Nodo nodoDestino = grafo.obtenerNodo(destino);

            if (nodoOrigen == null || nodoDestino == null) {
                System.out.println("Los nodos ingresados no son válidos.");
                continue;
            }

            // Ejecutar algoritmo de Dijkstra y obtener el resultado
            ResultadoDijkstra resultado = dijkstra(grafo, nodoOrigen, nodoDestino);

            if (resultado == null) {
                System.out.println("No se encontró un recorrido válido desde " + nodoOrigen.getNombre() + " hasta " + nodoDestino.getNombre());
            } else {
                // Imprimir el recorrido más corto
                System.out.println("El recorrido más corto desde " + nodoOrigen.getNombre() + " hasta " + nodoDestino.getNombre() + " es:");
                List<Nodo> recorrido = resultado.getRecorrido();
                for (int i = 0; i < recorrido.size(); i++) {
                    Nodo nodo = recorrido.get(i);
                    System.out.print(nodo.getNombre());
                    if (i < recorrido.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();

                // Imprimir la distancia total
                System.out.println("Distancia total: " + resultado.getDistanciaTotal());
            }

            System.out.print("¿Desea ingresar otro origen y destino? (s/n): ");
            opcion = scanner.nextLine();
        } while (!opcion.equalsIgnoreCase("n"));
    }

    public static ResultadoDijkstra dijkstra(Grafo grafo, Nodo nodoInicial, Nodo nodoDestino) {
        Map<Nodo, Double> distancias = new HashMap<>();
        Map<Nodo, Nodo> predecesores = new HashMap<>();

        // Inicializar todas las distancias como infinito excepto el nodo inicial
        for (Nodo nodo : grafo.obtenerNodos()) {
            if (nodo.equals(nodoInicial)) {
                distancias.put(nodo, 0.0);
            } else {
                distancias.put(nodo, Double.POSITIVE_INFINITY);
            }
        }

        Set<Nodo> nodosVisitados = new HashSet<>();

        while (!nodosVisitados.contains(nodoDestino)) {
            Nodo nodoActual = obtenerNodoConMenorDistancia(distancias, nodosVisitados);

            if (nodoActual == null) {
                // No se encontró un recorrido válido
                return null;
            }

            nodosVisitados.add(nodoActual);

            // Obtener los vecinos del nodo actual
            List<Arista> aristas = grafo.obtenerAristasSalientes(nodoActual);

            // Calcular las distancias mínimas a los vecinos del nodo actual
            for (Arista arista : aristas) {
                Nodo vecino = arista.getDestino();
                double distanciaDesdeOrigen = distancias.get(nodoActual) + arista.getPeso();
                if (distanciaDesdeOrigen < distancias.get(vecino)) {
                    distancias.put(vecino, distanciaDesdeOrigen);
                    predecesores.put(vecino, nodoActual);
                }
            }
        }

        // Construir el recorrido más corto desde el nodo inicial hasta el nodo destino
        List<Nodo> recorrido = construirRecorrido(nodoInicial, nodoDestino, predecesores);

        // Obtener la distancia total
        double distanciaTotal = distancias.get(nodoDestino);

        return new ResultadoDijkstra(recorrido, distanciaTotal);
    }

    public static Nodo obtenerNodoConMenorDistancia(Map<Nodo, Double> distancias, Set<Nodo> nodosVisitados) {
        Nodo nodoConMenorDistancia = null;
        double menorDistancia = Double.POSITIVE_INFINITY;

        for (Map.Entry<Nodo, Double> entry : distancias.entrySet()) {
            Nodo nodo = entry.getKey();
            double distancia = entry.getValue();

            if (!nodosVisitados.contains(nodo) && distancia < menorDistancia) {
                nodoConMenorDistancia = nodo;
                menorDistancia = distancia;
            }
        }

        return nodoConMenorDistancia;
    }

    public static List<Nodo> construirRecorrido(Nodo nodoInicial, Nodo nodoDestino, Map<Nodo, Nodo> predecesores) {
        List<Nodo> recorrido = new ArrayList<>();
        Nodo nodoActual = nodoDestino;

        while (nodoActual != null) {
            recorrido.add(0, nodoActual);
            nodoActual = predecesores.get(nodoActual);
        }

        if (!recorrido.get(0).equals(nodoInicial)) {
            return Collections.emptyList(); // No se encontró un recorrido válido
        }

        return recorrido;
    }
}
































