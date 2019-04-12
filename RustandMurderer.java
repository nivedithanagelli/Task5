import java.io.*;
import java.util.*;

public class RustandMurderer {

    static ArrayList<Integer>[] adj;
    static boolean[] vis;
    
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            String[] inp = br.readLine().split(" ");
            int n = Integer.parseInt(inp[0]);
            adj = new ArrayList[n+1]; 
            for(int i=0; i<n+1; i++)
                adj[i] = new ArrayList<Integer>();
            vis = new boolean[n+1];
            int m = Integer.parseInt(inp[1]);
            
            while(m-- > 0){
                String[] inp2 = br.readLine().split(" ");
                int a = Integer.parseInt(inp2[0]);

                int b = Integer.parseInt(inp2[1]);

                adj[a].add(b);
                adj[b].add(a);
            }
            int s = Integer.parseInt(br.readLine());
          
            int[] ans = bfs(s, n);
            for(int i=1; i<ans.length; i++)
                if(i!=s)
                    System.out.print(ans[i] + " ");
            System.out.println();
            
        }
    }
    
    static int[] bfs(int s, int n){
        boolean[] vis = new boolean[n+1];
        int[] dis = new int[n+1];
        ArrayDeque<Integer> Q = new ArrayDeque<Integer>();
        Q.add(s);
        vis[s] = true;
        
        TreeSet<Integer> l1 = new TreeSet<Integer>();
        TreeSet<Integer> l2 = new TreeSet<Integer>();
        for(int i=1; i<n+1; i++)
            if(i!=s)
                l1.add(i);
        
        while(Q.size() > 0){
            int u = Q.removeFirst();
            Iterator<Integer> it = adj[u].iterator();
            while(it.hasNext()){
                int temp = it.next();
                if(!vis[temp]){
                    l1.remove(temp);
                    l2.add(temp);
                }
            }
            it = l1.iterator();
            while(it.hasNext()){
                int temp = it.next();
                vis[temp] = true;
                dis[temp] = dis[u] + 1;
                Q.addLast(temp);
            }
            l1 = l2;
            l2 = new TreeSet<Integer>();
        }
        return dis;
    }
}
