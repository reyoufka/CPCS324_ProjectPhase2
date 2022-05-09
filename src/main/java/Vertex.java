public class Vertex {
     boolean isVisited;
    int weight;

    public Vertex() {
        weight=0;
        isVisited=false;
    }

    public Vertex(int weight) {
        this.weight = weight;
        isVisited=false;
    }

    
    public Vertex(boolean isVisited, int weight) {
        this.isVisited = isVisited;
        this.weight = weight;
    }

    
}
