import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {
    private WeightedGraph<V> graph;

    public BreadthFirstSearch(WeightedGraph<V> graph, V source) {
        super(source);
        this.graph = graph;
        search();
    }

    protected void search() {
        Queue<V> queue = new LinkedList<>();
        marked.add(source);
        queue.add(source);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            for (Vertex<V> neighbor : graph.getVertex(v).getAdjacentVertices().keySet()) {
                V neighborData = neighbor.getData();
                if (!marked.contains(neighborData)) {
                    marked.add(neighborData);
                    edgeTo.put(neighborData, v);
                    queue.add(neighborData);
                }
            }
        }
    }
}
