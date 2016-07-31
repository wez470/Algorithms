import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.lang.IllegalArgumentException;
import java.util.PriorityQueue;

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

        Set<Node> visited = new HashSet<>();
        Map<Integer, Integer> idToIndex = new HashMap<>();
        Map<Integer, Integer> indexToId = new HashMap<>();
        Map<Integer, QueueNode> indexToQNode = new HashMap<>();
        int index = 0;
        for (Integer id : nodes.keySet()) {
            idToIndex.put(id, index);
            indexToId.put(index, id);
            index++;
        }

        PriorityQueue<QueueNode> q = new PriorityQueue<>();
        QueueNode qNode = new QueueNode(0, nodes.get(srcId));
        q.add(qNode);
        indexToQNode.put(idToIndex.get(srcId), qNode);
        
        while (!q.isEmpty()) {
            QueueNode currQNode = q.poll();
            Node currNode = currQNode.getNode();
            double currDist = currQNode.getDist();

            for (Edge edge : currNode.getEdges()) {
                Node neighbour = edge.getNeighbour();
                if (!visited.contains(neighbour)) {
                    int neighbourIndex = idToIndex.get(neighbour.getId());
                    if (indexToQNode.containsKey(neighbourIndex)) {
                        if (currDist + edge.getWeight() < indexToQNode.get(neighbourIndex).getDist()) {
                            QueueNode neighbourQNode = indexToQNode.get(neighbourIndex);
                            q.remove(neighbourQNode);
                            QueueNode newQNode = new QueueNode(currDist + edge.getWeight(), neighbour);
                            indexToQNode.put(neighbourIndex, newQNode);
                            q.add(newQNode);
                        }
                    }
                    else {
                        QueueNode newQNode = new QueueNode(currDist + edge.getWeight(), neighbour);
                        indexToQNode.put(neighbourIndex, newQNode);
                        q.add(newQNode);
                    }
                }
            }

            visited.add(currNode);
        }

        return indexToQNode.get(idToIndex.get(destId)).getDist();
    }

    public double aStar(int srcId, int destId) {
        if (nodes.get(srcId) == null || nodes.get(destId) == null) {
            throw new IllegalArgumentException("src or dest id does not exist");
        }

        Set<Node> visited = new HashSet<>();
        Map<Integer, Integer> idToIndex = new HashMap<>();
        Map<Integer, Integer> indexToId = new HashMap<>();
        Map<Integer, AStarQueueNode> indexToQNode = new HashMap<>();
        int index = 0;
        for (Integer id : nodes.keySet()) {
            idToIndex.put(id, index);
            indexToId.put(index, id);
            index++;
        }

        PriorityQueue<AStarQueueNode> q = new PriorityQueue<>();
        AStarQueueNode qNode = new AStarQueueNode(0, 0, nodes.get(srcId));
        q.add(qNode);
        indexToQNode.put(idToIndex.get(srcId), qNode);
        Node destNode = nodes.get(destId);

        while (!q.isEmpty()) {
            AStarQueueNode currQNode = q.poll();
            Node currNode = currQNode.getNode();
            double currDist = currQNode.getDist();

            for (Edge edge : currNode.getEdges()) {
                Node neighbour = edge.getNeighbour();
                if (!visited.contains(neighbour)) {
                    int neighbourIndex = idToIndex.get(neighbour.getId());
                    if (indexToQNode.containsKey(neighbourIndex)) {
                        if (currDist + edge.getWeight() < indexToQNode.get(neighbourIndex).getDist()) {
                            AStarQueueNode neighbourQNode = indexToQNode.get(neighbourIndex);
                            q.remove(neighbourQNode);
                            AStarQueueNode newQNode = new AStarQueueNode(currDist + edge.getWeight(), getDist(neighbour, destNode), neighbour);
                            indexToQNode.put(neighbourIndex, newQNode);
                            q.add(newQNode);
                        }
                    }
                    else {
                        AStarQueueNode newQNode = new AStarQueueNode(currDist + edge.getWeight(), getDist(neighbour, destNode), neighbour);
                        indexToQNode.put(neighbourIndex, newQNode);
                        q.add(newQNode);
                    }
                }
            }

            visited.add(currNode);
        }

        return indexToQNode.get(idToIndex.get(destId)).getDist();
    }

    private double getDist(Node n1, Node n2) {
        return Math.sqrt((n2.getX() - n1.getX()) * (n2.getX() - n1.getX())
                + (n2.getY() - n1.getY()) * (n2.getY() - n1.getY()));
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
        System.out.println(g.aStar(0, 4)); // 7
        System.out.println(g.aStar(15, 0)); // 4
        System.out.println(g.aStar(0, 2)); // 4

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

    private class QueueNode implements Comparable<QueueNode> {
        private double dist;
        private Node node;

        public QueueNode(double dist, Node node) {
            this.dist = dist;
            this.node = node;
        }

        public Node getNode() {
            return node;
        }

        public double getDist() {
            return dist;
        }

        @Override
        public int compareTo(QueueNode other) {
            return Double.compare(dist, other.getDist());
        }
    }

    private class AStarQueueNode implements Comparable<AStarQueueNode> {
        private double dist;
        private double heuristicDist;
        private Node node;

        public AStarQueueNode(double dist, double heuristicDist, Node node) {
            this.dist = dist;
            this.heuristicDist = heuristicDist;
            this.node = node;
        }

        public Node getNode() {
            return node;
        }

        public double getDist() {
            return dist;
        }

        public double getHeuristicDist() {
            return heuristicDist;
        }

        @Override
        public int compareTo(AStarQueueNode other) {
            return Double.compare(dist + heuristicDist, other.getDist() + other.getHeuristicDist());
        }
    }
}
