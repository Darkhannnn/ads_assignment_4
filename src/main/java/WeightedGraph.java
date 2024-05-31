import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<V, Vertex<V>> vertices;
    private boolean directed;

    public WeightedGraph(boolean directed) {
        this.directed = directed;
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.put(data, new Vertex<>(data));
    }
    public void addEdge(V source, V destination, double weight) {
        this.addVertex(source);
        this.addVertex(destination);
        vertices.get(source).addAdjacentVertex(vertices.get(destination), weight);
        if (!directed) {
            vertices.get(destination).addAdjacentVertex(vertices.get(source), weight);
        }
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Map<V, Vertex<V>> getVertices() {
        return vertices;
    }
}
