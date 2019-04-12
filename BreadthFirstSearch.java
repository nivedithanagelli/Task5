import java.util.*;

public class BreadthFirstSearch {
    
    static class Node {
        int name; 
        HashSet<Integer> neighbour;
        
        public Node(int name) {
            this.name = name;
            neighbour = new HashSet<Integer>();
        }
        
        public void addEdge(int index) {
            neighbour.add(index);
        }
        
    }
    
    static class Graph {
        int V; //number of Nodes
        int E; //number of Edges
        ArrayList<Node> allNodes;
        
        public Graph (int V, int E) {
            this.V = V;
            this.E = E;
            allNodes = new ArrayList<Node>(V);
            
            for (int i = 0; i < V; i++) {
                allNodes.add(new Node(i + 1));
            }
        }
                
        public void addEdge(int index1, int index2) {
            Node node1 = allNodes.get(index1);
            Node node2 = allNodes.get(index2);
                
            node1.addEdge(index2); /* Bidirectional graph */
            node2.addEdge(index1);
        }
        
        public int[] shortestPath (int startIndex) {
            int distance[] = new int[V];
            boolean seen[] = new boolean[V];
            Queue<Integer> q = new ArrayDeque<Integer>(); 
            
            for (int i = 0; i < distance.length; i++)
                distance[i] = -1;
            
            // Starting from the start node - add to queue
            q.add(startIndex);
            distance[startIndex] = 0;
            
            
            while (!q.isEmpty())
            {
                int currIndex = q.poll();
                Node currNode = allNodes.get(currIndex);
              
                for (int n : currNode.neighbour) {
                    int neighbourIndex = n;
                    
                    if (seen[neighbourIndex])
                        continue; 
                    
                    q.add(neighbourIndex);
                    
                    if (distance[neighbourIndex] > distance[currIndex] + 1 || distance[neighbourIndex] == -1)
                        distance[neighbourIndex] = distance[currIndex] + 1;
                }
                
              
                seen[currIndex] = true;
          
            }
            
            return distance; 
        }
    }
    
    public static void main(String[] args) {     
        Scanner in = new Scanner(System.in);
        final int edgeWeight = 6;
        
        int T = in.nextInt(); /* Number of tests */
        
        for (int t = 0; t < T; t++) {
        	
//        	System.out.println("Running test " + t);
            int V = in.nextInt();
            int E = in.nextInt(); 
            
            Graph g = new Graph(V,E);
            
            /* Setting up the edges based on imput*/
            for (int i = 0; i < E; i++) {
                int index1 = in.nextInt() - 1; /* Scanner gets Node's index + 1*/
                int index2 = in.nextInt() - 1; 
                
                g.addEdge(index1, index2);
            }
            
            int startNodeIndex = in.nextInt() - 1;
            int result[] = g.shortestPath(startNodeIndex);
            
            for (int i = 0; i < V; i++) {
                if (i != startNodeIndex) {
                    System.out.print((result[i] > 0 ? result[i] * edgeWeight : result[i]) + " ");
                }
            }
            
            System.out.println();
            
        }
        
        in.close();
    }
}
