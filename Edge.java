package cpcs324_project_phase2;
//-----------------------------------------------------------------------------------------------//
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////               Class : EDGE                       ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

/**
 * This class to create edge's object and store the source and target and weight
 * of the edge
 *
 * @author Enas, Munera, Randa
 */
public class Edge {

    //----------------------------Attributes Section----------------------------
    //Decleare source variable to store the source vertex of the edge 
    Vertex source;
    //Decleare destiantion variable to store the source vertex of the edge 
    Vertex target;
    //Decleare parent variable to store the parent vertex of the edge (some algorithm need it)
    int weight;

    //----------------------------Constructors Section----------------------------
    /**
     * Default constructor
     */
    public Edge() {
    }

    /**
     * constructor with specific parameters
     *
     * @param source -- source vertex of the edge
     * @param target -- target vertex of the edge
     * @param weight -- weight of the edge
     */
    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

}
