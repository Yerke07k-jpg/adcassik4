public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addEdge("Astana", "Almaty", 1200.0);
        graph.addEdge("Astana", "Karaganda", 200.0);
        graph.addEdge("Karaganda", "Almaty", 900.0);
        graph.addEdge("Astana", "Shymkent", 1500.0);
        graph.addEdge("Almaty", "Shymkent", 700.0);

        System.out.println("===== BFS СЫНАУ =====");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, "Astana");
        Vertex<String> targetBfs = graph.getVertex("Shymkent");

        if (bfs.hasPathTo(targetBfs)) {
            System.out.print("Shymkent-ке дейінгі жол: ");
            for (Vertex<String> vertex : bfs.pathTo(targetBfs)) {
                System.out.print(vertex + " -> ");
            }
            System.out.println("СОҢЫ");
        }

        System.out.println("\n===== DIJKSTRA СЫНАУ =====");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, "Astana");
        Vertex<String> targetDijkstra = graph.getVertex("Shymkent");

        if (dijkstra.hasPathTo(targetDijkstra)) {
            System.out.print("Shymkent-ке дейінгі ең қысқа жол: ");
            for (Vertex<String> vertex : dijkstra.pathTo(targetDijkstra)) {
                System.out.print(vertex + " -> ");
            }
            System.out.println("СОҢЫ");
            System.out.println("Жолдың жалпы қашықтығы: " + dijkstra.getDistanceTo("Shymkent"));
        }
    }
}