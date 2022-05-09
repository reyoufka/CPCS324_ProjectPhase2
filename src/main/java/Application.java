import java.util.Scanner;

public class Application {

    static int[][] testcases={{1000,5000},{2000, 10000},{3000, 15000},{5000, 25000},{10000,50000},{15000,75000}};
    public static void main(String[] args) {
        
        Graph g1 = Graph.readGraphFromFile("inputfile.txt");
        g1.printGraph();
        System.out.println("");
        System.out.println("This program generates random graphs, and runs several algorithms\n");
        System.out.println("Floyd-Warshall Algorithm Result: ");
        SingleSourceSPAlg algo2 = new SingleSourceSPAlg(g1);
        algo2.floydWarshall(false);
        System.out.println("");
        //===============================================
        System.out.println("Dijkstra Algorithm Result: (chosen Source = A)");
        AllSourceSPAlg algo = new AllSourceSPAlg(g1);
        algo.dijkstra_GetMinDistances(0,false);
        System.out.println("");
        Scanner input = new Scanner(System.in);
       
        char c;
        do{
            System.out.println("\nPlease choose your case:");
            System.out.println("A- n = 1000,  m = 5000");
            System.out.println("B- n = 2000,  m = 10000");
            System.out.println("C- n = 3000,  m = 15000");
            System.out.println("D- n = 5000,  m = 25000");
            System.out.println("E- n = 10000, m = 50000");
            System.out.println("F- n = 15000, m = 75000");
            System.out.println("G- Exit\n");
            System.out.print("Enter your choice: ");
            c = input.next().charAt(0);
            c = Character.toLowerCase(c);
            
            switch(c){
                case 'a':{
                    TestAlgo(0);}
                break;
                case 'b':{
                    TestAlgo(1);}
                break;
                case 'c':{
                    TestAlgo(2);}
                break;
                case 'd':{
                    TestAlgo(3);}
                break;
                case 'e':{
                    TestAlgo(4);}
                break;
                case 'f':{
                    TestAlgo(5);}
                break;
                case 'g':{
                    System.out.println("");
                    System.out.println("Goodbye ..");}
                break;
                default:{
                    System.out.println("Invalid input, retype your choice ..");
                    System.out.println("");
                }
            }
        }while(c!='G'&&c!='g');

        
        
    }

    private static void TestAlgo(int i) {
            System.out.println("Test Case: {n = "+testcases[i][0]+", m = "+testcases[i][1]+"}");
            Graph g_testcase = Graph.makeGraph(testcases[i][0], testcases[i][1]);
            SingleSourceSPAlg single_testcase = new SingleSourceSPAlg(g_testcase);
            long start1 = System.currentTimeMillis();
            single_testcase.floydWarshall(true);
            long finish1 = System.currentTimeMillis();
            long timeElapsed1 = finish1 - start1;
            System.out.println("Total runtime Of floydWarshall algorithm for all pairs: "+timeElapsed1+" ms.");
            
            AllSourceSPAlg all_testcase = new AllSourceSPAlg(g_testcase);
            long start2 = System.currentTimeMillis();
            all_testcase.dijkstra_GetMinDistances(0,true);
            long finish2 = System.currentTimeMillis();
            long timeElapsed2 = finish2 - start2;
            System.out.println("Total run time Of Dijkstra algorithm for chosen source to rest of vertices: "+timeElapsed2+" ms.");
    }
}
