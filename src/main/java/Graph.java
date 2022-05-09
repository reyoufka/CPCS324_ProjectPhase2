import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Graph {
    int verticesNo;
    int edgeNo;
    boolean isDigraph;
    ArrayList<Character> labels;
    Vertex[][] vertices ;

    public Graph( boolean isDigraph) {
        this.isDigraph = isDigraph;
        edgeNo=0;
        verticesNo=0;
        labels=new ArrayList<>();
    }

    public static Graph makeGraph(int veticesNo,int edgeNo){
        Graph g=new Graph(true);
        g.verticesNo=veticesNo;
        g.vertices=new Vertex[veticesNo][veticesNo];
        Random rand = new Random();
        //to make sure that graph is connected we need to check, For each vertix we need at least one edge
        for (int i = 0; i < veticesNo; i++) {
              int r_weight = rand.nextInt(10)+1; //random number between 1 and 10
             int rNode=rand.nextInt(veticesNo); 
             if (rNode==i && i>0)
                 rNode--;
             if (rNode==i && i==0)
                 rNode++;
             g.addEdge(i, rNode, r_weight); //num of edges increase each time the addEdge invoked
        }
        int numEdges=edgeNo-veticesNo;
        do{
            for (int i = 0; i < veticesNo; i++) {
                for (int j = 0; j < veticesNo; j++) {
                    if(i!=j){
                        if(g.vertices[i][j]==null){
//                            if(rand.nextBoolean()){
                            int rProbability=rand.nextInt(1000);
                            if(rProbability<1){ //to make graph balanced
                                int r_weight = rand.nextInt(10)+1;
                                 g.addEdge(i, j, r_weight);
                                 numEdges--;
                                 if(numEdges==0)
                                     break;
                            }
                        }
                     }
                }
                if(numEdges==0)
                    break;
            }
        }while(numEdges!=0);
        
        return g;
    }

    public static Graph readGraphFromFile(String fileName){
        try {
            File inFile=new File(fileName);
            
            Scanner input=new Scanner(inFile);
            String gname=input.next();
            int gtype=input.nextInt();
            Graph g;
            if(gtype==0)
             g=new Graph(false);
            else
             g=new Graph(true);
            
            ArrayList<Character> sources=new ArrayList<>();
            ArrayList<Character> targets=new ArrayList<>();
            ArrayList<Integer> weights=new ArrayList<>();
            while(input.hasNext()){
                sources.add(input.next().charAt(0));
                targets.add(input.next().charAt(0));
                weights.add(input.nextInt());
            }
            
            for (int i = 0; i < sources.size(); i++) {
                g.addVertLabel(sources.get(i));
                g.addVertLabel(targets.get(i));
            }
            //after above loop we know the number ov vertices in the graph
            g.vertices=new Vertex[g.verticesNo][g.verticesNo];
            //now we will ad edges
            for (int i = 0; i < sources.size(); i++) {
                g.addEdge(g.getVertPos(sources.get(i)), g.getVertPos(targets.get(i)), weights.get(i));
            }            
            input.close();
            return g;
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    public void addEdge(int v,int u,int w){
        
        if(isDigraph){ //directed graph
            edgeNo++;
            vertices[v][u]=new Vertex(w);
        }
        else{
            vertices[v][u]=new Vertex(w);
            vertices[u][v]=new Vertex(w);
            edgeNo+=2;
        }
    }
    public int getVertPos(char v) {
        for (int i = 0; i < verticesNo; i++) {
            if(labels.get(i)==v)
                return i;
        }
        return -1;
    }
    public boolean addVertLabel(Character vLabel) {
        if(getVertPos(vLabel)==-1){
           labels.add(vLabel);
           verticesNo++;
           return true;
        }
        return false;
    }
    public void increaseVerticesNo(){
        verticesNo++;
    }


   public void printGraph() {
        System.out.println("Graph: (Adjacency Matrix)");
        System.out.print(" ");
        for (int i = 0; i < verticesNo; i++) {
            System.out.print(" "+labels.get(i)+" " );
        }
        System.out.println("");
        for (int i = 0; i < verticesNo; i++) {
            System.out.print(labels.get(i)+" ");
            for (int j = 0; j <verticesNo ; j++) {
                if(vertices[i][j]==null)
                System.out.print( "0  ");    
                else
                System.out.printf( "%-2d ",vertices[i][j].weight);
            }
            System.out.println();
        }
       /* for (int i = 0; i < verticesNo; i++) {
            System.out.print("Vertex " + i + " is connected to:");
            for (int j = 0; j <verticesNo ; j++) {
                if(vertices[i][j]!=null && vertices[i][j].weight>0){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
        */
    }
    
}
