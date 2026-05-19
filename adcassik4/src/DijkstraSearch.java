import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {
    private final Map<Vertex<V>, Double> distances;

    private static class Node<V> implements Comparable<Node<V>> {
        final Vertex<V> vertex;
        final double distance;

        Node(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node<V> other) {
            return Double.compare(this.distance, other.distance);
        }
    }

    public DijkstraSearch(WeightedGraph<V> graph, V sourceData) {
        super(graph.getVertex(sourceData));
        this.distances = new HashMap<>();
        if (this.source != null) {
            dijkstra(graph);
        }
    }

    private void dijkstra(WeightedGraph<V> graph) {
        PriorityQueue<Node<V>> pq = new PriorityQueue<>();

        for (Vertex<V> v : graph.getVertices().values()) {
            distances.put(v, Double.MAX_VALUE);
        }

        distances.put(source, 0.0);
        pq.add(new Node<>(source, 0.0));

        while (!pq.isEmpty()) {
            Node<V> current = pq.poll();
            Vertex<V> u = current.vertex;

            if (current.distance > distances.get(u)) continue;

            for (Map.Entry<Vertex<V>, Double> entry : u.getAdjacentVertices().entrySet()) {
                Vertex<V> v = entry.getKey();
                double weight = entry.getValue();
                double newDist = distances.get(u) + weight;

                if (newDist < distances.get(v)) {
                    distances.put(v, newDist);
                    edgeTo.put(v, u);
                    pq.add(new Node<>(v, newDist));
                }
            }
        }
    }

    public double getDistanceTo(V targetData) {
        Vertex<V> target = edgeTo.keySet().stream()
                .filter(v -> v.getData().equals(targetData))
                .findFirst().orElse(null);
        if (target == null && source.getData().equals(targetData)) return 0.0;
        return target != null ? distances.get(target) : Double.MAX_VALUE;
    }
}
