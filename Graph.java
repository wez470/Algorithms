import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.lang.IllegalArgumentException;

/*
 * Graph class that can do dijkstras.
 */
public class Graph {
    private Map<Integer, Node> nodes = new HashMap<>();
   
    public Graph() {
    }

    public void addNode(int id, int x, int y) {
        nodes.put(id, new Node(x, y, id)); 
    }

    public void addEdge(int id1, int id2, int weight) {
        Node n1 = nodes.get(id1);
        Node n2 = nodes.get(id2);
        n1.addEdge(n2, weight);
        n2.addEdge(n1, weight);
    }

    public double dijkstras(int srcId, int destId) {
        if (nodes.get(srcId) == null || nodes.get(destId) == null) {
            throw new IllegalArgumentException("src or dest id does not exist");
        }

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> idToIndex = new HashMap<>();
        Map<Integer, Integer> indexToId = new HashMap<>();
        int index = 0;
        for (Integer id : nodes.keySet()) {
            idToIndex.put(id, index);
            indexToId.put(index, id);
            index++;
        }
        double[] dists = new double[nodes.size()];
        for (int i = 0; i < dists.length; i++) {
            dists[i] = Double.POSITIVE_INFINITY;
        }
        dists[idToIndex.get(srcId)] = 0;
        
        while (visited.size() < nodes.size()) {
            // Get lowest distance node
            double min = Double.POSITIVE_INFINITY;
            int minIndex = -1;
            for (int i = 0; i < dists.length; i++) {
                if (!visited.contains(i)) {
                    if (dists[i] < min) {
                        min = dists[i];
                        minIndex = i;
                    }
                }
            }
            Node currNode = nodes.get(indexToId.get(minIndex));

            for (Edge edge : currNode.getEdges()) {
                Node neighbour = edge.getNeighbour();
                int neighbourIndex = idToIndex.get(neighbour.getId());
                if (dists[minIndex] + edge.getWeight() < dists[neighbourIndex]) {
                    dists[neighbourIndex] = dists[minIndex] + edge.getWeight();
                }
            }

            visited.add(minIndex);
        }

        return dists[idToIndex.get(destId)];
    }

    public static void main(String[] args) {
        // '0' --- 2 --- '1'
        //  |             |
        //  5             1
        //  |             |
        // '2' --- 1 --- '3'
        //  |             |
        //  3             1
        //  |             |
        // '4' --- 4 --- '15'
        Graph g = new Graph();
        g.addNode(0, 0, 0);
        g.addNode(1, 1, 0);
        g.addNode(2, 0, 1);
        g.addNode(3, 1, 1);
        g.addNode(4, 0, 2);
        g.addNode(15, 1, 2);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 5);
        g.addEdge(1, 3, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 4, 3);
        g.addEdge(3, 15, 1);
        g.addEdge(4, 15, 4);
        System.out.println(g.dijkstras(0, 4)); // 7
        System.out.println(g.dijkstras(15, 0)); // 4
        System.out.println(g.dijkstras(0, 2)); // 4
    }
    
    private class Node {
        private int x;
        private int y;
        private int id;
        private List<Edge> edges = new ArrayList<>();

        public Node(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        public void addEdge(Node n, int weight) {
            edges.add(new Edge(weight, n));
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getId() {
            return id;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }

    private class Edge {
        private int weight;
        private Node otherNode;

        public Edge(int w, Node n) {
            weight = w;
            otherNode = n;
        }

        public int getWeight() {
            return weight;
        }

        public Node getNeighbour() {
            return otherNode;
        }
    }
}
