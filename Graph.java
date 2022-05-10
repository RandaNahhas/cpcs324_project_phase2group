package cpcs324_project_phase2;
//-----------------------------------------------------------------------------------------------//
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////               Class :GRAPH                       ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//
//imports : 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class to create graph's object take the number vertices number and edge
 * number and make graph accroding to it
 *
 * @author Enas, Munera, Randa
 */
public class Graph {

    //----------------------------Attributes Section----------------------------
    //deleare verticesNo variable  number of vertices of the graph.
    //It should be incremented whenever a method a new object is added to the vertex list. 
    int verticesNo;
    //deleare edgeNo variable to store edge's number of the graph.
    //It should be incremented by one in case add edge in directed graph and by two if it is an undirected graph.
    int edgeNo;
    //decleare boolean variable true 
    //-->if the graph is directed graph, false -->if the graph is undirected
    boolean isDigraph;
    //decleare 2D array to store matrix this matrix will represent the edge between pair of two vertex
    Edge[][]adjMatrix;
    //decleare variable to store the list of vertices of a graph
    Vertex[] vertices;
    //decleare variable to store the total number of edges
    int totalEdges;
    //decleare variable to store the total number of vertices
    int totalVertices;

    //----------------------------Constructors section----------------------------
    /**
     * Default constructor
     */
    public Graph() {

    }

    /**
     * Constructor with specific parameter
     *
     * @param totalVerticesNo -- number of vertices of graph
     * @param totalEdgesNo -- number of edges between the vertices
     * @param isDigraph -- its directed graph or undirected
     */
    public Graph(int totalVerticesNo, int totalEdgesNo, boolean isDigraph) {
        this.totalEdges = totalEdgesNo;
        this.isDigraph = isDigraph;
        this.totalVertices = totalVerticesNo;
        adjMatrix = new Edge[totalVerticesNo][totalVerticesNo];//intially null
        vertices = new Vertex[totalVerticesNo];

    }

    //----------------------------Methods section----------------------------
    /**
     * This method pass the source and target to create edge between them and
     * add it to the adjMatrix[v][u] (and adjMatrix[u][v] if its undirected
     * graph)
     *
     * @param v -- source vertex label
     * @param u -- target vertex label
     * @param w -- weight of the edge to be created
     * @return Edge object was created between v and u (source and target)
     */
    public Edge addEdge(int v, int u, int w) {
        //if the source vertex not already created
        if (vertices[v] == null) {
            //create vertex with v position to be the source
            Vertex src = new Vertex(v);
            //add the source vertex to the vertices list
            vertices[v] = src;
            //increament vertices number by one
            verticesNo++;
        }
        //if the target vertex not already created
        if (vertices[u] == null) {
            //create vertex with u position to be the target
            Vertex target = new Vertex(u);
            //add the target vertex to the vertices list
            vertices[u] = target;
            //increament vertices number by one
            verticesNo++;
        }
        //create edge object with vertex v as source and u as destination
        Edge e = new Edge(vertices[v], vertices[u], w);
        //add the edge to the matrix between the pair (v,u) 
        adjMatrix[v][u] = e;
        //increament edge number by one
        edgeNo++;
        //if is undirected graph create another edge with the destinationId as source and add it to the destiantion adjList
        if (!isDigraph) {
            //create edge object with vertex u as source and v as destination
            Edge e2 = new Edge(vertices[u], vertices[v], w);
            //add the edge to the matrix between the pair (u,v) 
            adjMatrix[u][v] = e2;
            //increament edge number by one
            edgeNo++;
        }
        //return the first edge created
        return e;
    }

    /**
     * This method to make graph 1- first make the nessecary edge with random
     * weight to ensure its connected using addEdge (and addEdge will create
     * source and target vertices if it not already created) 2- make the
     * remaining edge with random weight using addEdge (and addEdge will create
     * source and target vertices if it not already created)
     *
     * @param totalVertices
     * @param totalEdges
     */
    public void makeGraph(int totalVertices, int totalEdges) {
        Random random = new Random();
        // --- STEP 1: create the necessary edges to ensuring the graph is connected ---
        for (int i = 0; i < totalVertices - 1 && edgeNo < totalEdges; i++) {
            //generate random integer from 0 to 50(included) to weight the edge 
            int randomWeight = random.nextInt(50) + 1;
            //invoking addEdge to create edge between the pair of vertices with i and i+1 position
            addEdge(i, i + 1, randomWeight);
        }

        //calculate the remainig edges to generate it randomly
        int remaning = totalEdges - (totalVertices - 1);

        // --- STEP 2: Add the remainig edges randomly ---
        for (int i = 0; i < remaning && edgeNo < totalEdges; i++) {
            //source position is the vertex that will have an adjacent vertex
            int srcPosition = random.nextInt(adjMatrix.length);
            //target postion randomly chooses which vertex to create edge between it and source postion 
            int targetPosition = random.nextInt(adjMatrix.length);
            /* Avoid self-edges or having an adjacent vertex that already exists
                1- A self-edge happens when the vertex with scrPosition = targetPosition, thus the vertex will create edge with itself
                2- An edge that already exists: when we want to add a new edge it already exists
            If one of these cases appeared, the iteration should not be counted and should be ignored without
            affecting the number of wanted edges in the graph
            We will avoid those cases by using the following if statement
             */
            if (adjMatrix[srcPosition][targetPosition] != null || srcPosition == targetPosition) {
                --i;
                continue;
            }
            //generate random integer from 0 to 50(included) to weight the edge
            int randomWeight = random.nextInt(50) + 1;
            //invoking addEdge to create edge between the pair of vertices with srcPosition and targetPOsition position
            addEdge(srcPosition, targetPosition, randomWeight);
        }
    }

    /**
     * This method reads the edges and vertices from the text file whose name is
     * specified by the parameter filename and place data in a Graph object.
     *
     * @param fileName
     * @throws FileNotFoundException
     */
    public void readGraphFromFile(File fileName) throws FileNotFoundException {
        //create scanner object to read from the file
        Scanner readFile = new Scanner(fileName);
        readFile.next();
        //read the integer and store it in the digraph variable
        //0 means flase, 1 means true
        int digraph = readFile.nextInt();
        //if it's digraph set isDigraph variable true
        if (digraph == 1) {
            isDigraph = true;
        }
        //read the number of vertices and store it in the totalVertices variable
        totalVertices = readFile.nextInt();
        //read the number of edges and store it in the totalEdges variable
        totalEdges = readFile.nextInt();
        //initialize the matrix with number of vertices as number of row 
        //and for each row the number of column it will be number of vertice
        adjMatrix = new Edge[totalVertices][totalVertices];
        //initialize the vertices array with number of vertices as size
        vertices = new Vertex[totalVertices];

        /*loop to read source label and target label and  doing some preprocessing 
        then call the addEdge() method to determine the position of the Edge
        and then processing the returned Edge object to set the label attribute of the source
        and target attributes that are of the type Vertex*/
        while (edgeNo < totalEdges) {
            //read source label
            char srcLabel = readFile.next().charAt(0);
            //read target label
            char targetLabel = readFile.next().charAt(0);
            //read weight
            int wieght = readFile.nextInt();
            //invoke addEdge  to add edge between the source and target position 
            //and store the return edge object
            //Note: position= label-65
            Edge edge = addEdge(srcLabel - 65, targetLabel - 65, wieght);
            //call add vertex position method to add the source vertex label
            addVertLabel(srcLabel);
            //calladd vertex position method to add the target vertex label
            addVertLabel(targetLabel);
        }
    }

    /**
     * This method will print the graph after read it from the file and create
     * the matrix
     */
    public void PrintGraphFile() {
        System.out.print("\n   ");
        for (int i = 0; i < totalVertices; i++) {
            System.out.printf("%-4s", vertices[i].label);
        }
        System.out.println("\n--------------------------------------------");
        for (int i = 0; i < totalVertices; i++) {
            System.out.print(vertices[i].label + "| ");
            for (int j = 0; j < totalVertices; j++) {
                if (j == totalVertices - 1) {
                    if (adjMatrix[i][j] == null) {
                        System.out.print("∞ ");
                    } else {
                        System.out.printf("%-2d ", adjMatrix[i][j].weight);
                    }
                    continue;
                }
                if (adjMatrix[i][j] != null) {
                    System.out.print(" ");
                    System.out.printf("%-3d,", adjMatrix[i][j].weight);
                } else {
                    System.out.print("∞ ,");
                }
            }
            System.out.println(" ");

        }
    }

    /**
     * This method to add vertex label
     *
     * @param vLabel : is the label of the vertex
     * @return true if add the label, and false if its already addded
     */
    public boolean addVertLabel(char vLabel) {
        //if the label is equal 0 (default character value)
        if (vertices[vLabel - 65].label == 0) {
            //store the label
            vertices[vLabel - 65].label = vLabel;
            //return true-->the label added sucessfully
            return true;
        }
        //the label not added because it already there
        return false;
    }
}
