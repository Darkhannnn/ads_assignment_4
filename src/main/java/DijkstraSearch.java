import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {
    private WeightedGraph<V> graph;
    private Map<V, Double> distTo;
    public DijkstraSearch(WeightedGraph<V> graph, V source) {
        super(source);
        this.graph = graph;
        distTo = new HashMap<>();
        for (V v : graph.getVertices().keySet()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);
        search();
    }

    protected void search() {
        PriorityQueue<VertexDistance<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(VertexDistance::getDistance));
        pq.add(new VertexDistance<>(source, 0.0));

        while (!pq.isEmpty()) {
            VertexDistance<V> vd = pq.poll();
            V v = vd.getVertex();
            if (marked.contains(v)) continue;
            marked.add(v);

            for (Map.Entry<Vertex<V>, Double> entry : graph.getVertex(v).getAdjacentVertices().entrySet()) {
                V w = entry.getKey().getData();
                double weight = entry.getValue();
                if (distTo.get(w) > distTo.get(v) + weight) {
                    distTo.put(w, distTo.get(v) + weight);
                    edgeTo.put(w, v);
                    pq.add(new VertexDistance<>(w, distTo.get(w)));
                }
            }
        }
    }

    private static class VertexDistance<V> {
        private final V vertex;
        private final double distance;

        public VertexDistance(V vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public V getVertex() {
            return vertex;
        }

        public double getDistance() {
            return distance;
        }
    }
}
