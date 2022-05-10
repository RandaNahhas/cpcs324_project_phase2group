package cpcs324_project_phase2;
//-----------------------------------------------------------------------------------------------//
///////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////               Class : VERTEX                     ////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//-----------------------------------------------------------------------------------------------//

/**
 * This class to create vertex's object with its label and adjacent vertices to
 * it
 *
 * @author Enas, Munera, Randa
 */
public class Vertex {

    //----------------------------Attributes Section----------------------------
    //Decleare label variable to store the label of vertex 
    char label;
    //Decleare boolean variable to know if the vertex visited or not (intillay all vertices not visited)
    boolean isVisited = false;
    //decleare position variable to store the position of vertex
    int position;

    //----------------------------Constructors Section--------------------------
    /**
     * Default constructor of Vertex class
     */
    public Vertex() {
    }

    /**
     * Constructor with specific label value of the vertex position
     *
     * @param position
     */
    public Vertex(int position) {
        this.position = position;

    }

    //----------------------------Methods Section-------------------------------
    /**
     * This method will return the position of the vertex
     *
     * @return position of the vertex
     */
    public int getVerPos() {
        return this.position;
    }

}
