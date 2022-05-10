package cpcs324_project_phase2;

//-----------------------------------------------------------------------------------------------//
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////               Class :ShortestPathAlgorithm       ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

/**
 * This class is a superclass representing the general characteristics of an
 * algorithm for computing the minimum spanning tree, it has two subclasses:
 * SingleSourceSPAlg and AllSourceSPAlg, it contains the following attributes
 * and methods.
 *
 * @author Enas, Munera, Randa
 */
public class ShortestPathAlgorithm {

    //----------------------------Attributes Section----------------------------
    //deleare graph object to store the graph
    Graph graph;

    //----------------------------Constructors section----------------------------
    /**
     * Constructor with specific parameter
     *
     * @param graph : graph object
     */
    public ShortestPathAlgorithm(Graph graph) {
        this.graph = graph;
    }

}
