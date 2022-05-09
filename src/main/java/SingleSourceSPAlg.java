import java.util.ArrayList;
import java.util.List;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    public SingleSourceSPAlg() {
    }

    public SingleSourceSPAlg(Graph g) {
        super(g);
    }
    // Recursive function to print path of given vertex `u` from source vertex `v`
    private  void printPath(int[][] path, int v, int u, List<Character> route)
    {
        if (path[v][u] == v) {
            return;
        }
        printPath(path, v, path[v][u], route);
        route.add(g.labels.get(path[v][u]));
    }
 
    // Function to print the shortest cost with path information between
    // all pairs of vertices
    private  void printSolution(int[][] path,int[][] cost, int n)
    {
        for (int v = 0; v < n; v++)
        {
            for (int u = 0; u < n; u++)
            {
                if (u != v && path[v][u] != -1)
                {
                    List<Character> route = new ArrayList<>();
                    route.add(g.labels.get(v));
                    printPath(path, v, u, route);
                    route.add(g.labels.get(u));
                    System.out.printf("The shortest path from %c —> %c is %-20s Distance=%d\n",
                        g.labels.get(v), g.labels.get(u), route,cost[v][u]);
                }
            }
        }
    }
        // Function to run the Floyd–Warshall algorithm
    public  void floydWarshall(boolean is_testcase)
    {
        // base case
        if (g.vertices ==null || g.verticesNo == 0) {
            return;
        }
 
        // total number of vertices in the `adjMatrix`
        int n = g.verticesNo;
 
        // cost[] and path[] stores shortest path
        // (shortest cost/shortest route) information
        int[][] cost = new int[n][n];
        int[][] path = new int[n][n];
 
        // initialize cost[] and path[]
        for (int v = 0; v < n; v++)
        {
            for (int u = 0; u < n; u++)
            {
                // initially, cost would be the same as the weight of the edge
                if(g.vertices[v][u]==null)
                    cost[v][u]=    Integer.MAX_VALUE;
                else
                    cost[v][u] = g.vertices[v][u].weight;
 
                if (v == u) {
                    path[v][u] = 0;
                }
                else if (cost[v][u] != Integer.MAX_VALUE) {
                    path[v][u] = v;
                }
                else {
                    path[v][u] = -1;
                }
            }
        }
 
        // run Floyd–Warshall
        for (int k = 0; k < n; k++)
        {
            for (int v = 0; v < n; v++)
            {
                for (int u = 0; u < n; u++)
                {
                    // If vertex `k` is on the shortest path from `v` to `u`,
                    // then update the value of cost[v][u] and path[v][u]
 
                    if (cost[v][k] != Integer.MAX_VALUE
                            && cost[k][u] != Integer.MAX_VALUE
                            && (cost[v][k] + cost[k][u] < cost[v][u]))
                    {
                        cost[v][u] = cost[v][k] + cost[k][u];
                        path[v][u] = path[k][u];
                    }
                }
 
                // if diagonal elements become negative, the
                // graph contains a negative-weight cycle
                if (cost[v][v] < 0)
                {
                    System.out.println("Negative-weight cycle found!!");
                    return;
                }
            }
        }
        if(!is_testcase){
        // Print the shortest path between all pairs of vertices
        printSolution(path,cost, n);
        }else{
//            printSolution2(path,cost, n);
        }
    }
    /*
    private void printSolution2(int[][] path, int[][] cost, int n) {
        for (int v = 0; v < n; v++)
        {
            for (int u = 0; u < n; u++)
            {
                if (u != v && path[v][u] != -1)
                {
                    List<Integer> route = new ArrayList<>();
                    route.add(v);
                    printPath2(path, v, u, route);
                    route.add(u);
                    System.out.printf("The shortest path from %d —> %d is %-20s Distance=%d\n",
                        v, u, route,cost[v][u]);
                }
            }
        }
    }

    private void printPath2(int[][] path, int v, int u, List<Integer> route) {
        if (path[v][u] == v) {
            return;
        }
        printPath2(path, v, path[v][u], route);
        route.add(path[v][u]);        
    }*/
    
}
