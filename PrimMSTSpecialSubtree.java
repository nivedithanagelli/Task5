import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class PrimMSTSpecialSubtree {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        HashSet<Integer> mst = new HashSet<Integer>();
        
        HashMap<Integer, ArrayList<Edge>> graph = new HashMap<>();
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        
        for(int i=1; i<=N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 1; i <= E; i++) {
            input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);
            long W = Long.parseLong(input[2]);
            graph.get(A).add(new Edge(A,B,W));
            graph.get(B).add(new Edge(A,B,W));
        }

        int S = Integer.parseInt(br.readLine());
        edges.addAll(graph.get(S));

        long cost = 0;
        while(!(mst.size()==N)) {
            Edge temp = edges.poll();
            int node1 = temp.node1;
            int node2 = temp.node2;
            if(mst.contains(node1) && mst.contains(node2)) {

            } else {
                cost += temp.weight;
                if(!mst.contains(node1)) {
                    mst.add(node1);
                    edges.addAll(graph.get(node1));
                }
                if(!mst.contains(node2)) {
                    mst.add(node2);
                    edges.addAll(graph.get(node2));
                }
            }
        }

        System.out.println(cost);

    }

}

class Edge implements Comparable<Edge> {

    int node1;
    int node2;
    long weight;

    public Edge(int node1, int node2, long weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return (this.weight - o.weight < 0) ? -1 : 1;
    }
}
