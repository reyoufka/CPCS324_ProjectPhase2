public class AllSourceSPAlg extends ShortestPathAlgorithm{

    private static final int NO_PARENT = -1;
    public AllSourceSPAlg() {
    }

    
    public AllSourceSPAlg(Graph g) {
        super(g);
    }
    
    public int getMinimumVertex(boolean[] mst, int[] key) {
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < g.verticesNo; i++) {
            if (mst[i] == false && minKey > key[i]) {
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

        public void dijkstra_GetMinDistances(int sourceVertex,boolean is_testcase) {
            boolean[] spt = new boolean[g.verticesNo];
            int[] distance = new int[g.verticesNo];
            int INFINITY = Integer.MAX_VALUE;

            //Initialize all the distance to infinity
            for (int i = 0; i < g.verticesNo; i++) {
                distance[i] = INFINITY;
            }

            int[] parents = new int[g.verticesNo];
 
            // The starting vertex does not
            // have a parent
            parents[sourceVertex] = NO_PARENT;
            

            //start from the vertex 0
            distance[sourceVertex] = 0;

            //create SPT
            for (int i = 0; i < g.verticesNo; i++) {

                //get the vertex with the minimum distance
                int vertex_U = getMinimumVertex(spt, distance);
                if(vertex_U==-1)
                    break;
                //include this vertex in SPT
                spt[vertex_U] = true;

                //iterate through all the adjacent vertices of above vertex and update the keys
                for (int vertex_V = 0; vertex_V < g.verticesNo; vertex_V++) {
                    //check of the edge between vertex_U and vertex_V
                    if (g.vertices[vertex_U][vertex_V]!=null && g.vertices[vertex_U][vertex_V].weight > 0) {
                        //check if this vertex 'vertex_V' already in spt and
                        // if distance[vertex_V]!=Infinity

                        if (spt[vertex_V] == false &&  g.vertices[vertex_U][vertex_V].weight != INFINITY) {
                            //check if distance needs an update or not
                            //means check total weight from source to vertex_V is less than
                            //the current distance value, if yes then update the distance

                            int newKey =  g.vertices[vertex_U][vertex_V].weight + distance[vertex_U];
                            if (newKey < distance[vertex_V]) {
                                parents[vertex_V] = vertex_U;
                                distance[vertex_V] = newKey;
                            }
                        }
                    }
                }
            }
            if(!is_testcase){
            //print shortest path tree
                 printDijkstra(sourceVertex, distance,parents);
            }
            
        }

        public void printDijkstra(int sourceVertex, int[] key,int[] parents) {
            for (int i = 0; i < g.verticesNo; i++) {
                if(i!=sourceVertex){
                    System.out.print("Source Vertex: " + g.labels.get(sourceVertex) + " to vertex " + g.labels.get(i)
                            + " distance: " + key[i] +"    ");
                    System.out.print("Path: ");
                    printPath(i, parents);
                    System.out.println("");
                }
            }
        }    
        private  void printPath(int currentVertex,
                                  int[] parents)
        {

            // Base case : Source node has
            // been processed
            if (currentVertex == NO_PARENT)
            {
                return;
            }
            printPath(parents[currentVertex], parents);
            System.out.print(g.labels.get(currentVertex) + " ");
        }
}
