package Structure;

import java.util.ArrayList;

/*
    * Store the nodes and their edges
    * Initialize node's and edge's members
    * Put nodes in good order to apply the djikstra algorithm
*/
    

public class Graph {
    private ArrayList<Node> nodes;
    
    public Graph() {
        this.nodes = new ArrayList<Node>();
    }
    /*
    *reinitialize all edge visited value to @param visited
    */
    public void setAllEdgeVisited(boolean visited) {
        for (int i =0; i< this.nodes.size(); i++) {
            Node curr_node = this.nodes.get(i);
            for (int j = 0; j < curr_node.getOutgoings().size(); j++) {
                Edge curr_edge = curr_node.getOutgoings().get(j);
                if(curr_edge.isVisited() != visited) {
                    curr_edge.setVisited(visited);
                }
            }
        }
    }
    /*
    * function: re-order all the nodes in the graph so we can apply dijkstra Algorithm later on
    *           the order will start from @param: source, followed by its neighbours
    *@param source: starting point of new ordered graph
    */
    public void setSource(Node source) {
        ArrayList<Node> result = new ArrayList<Node>();
        ArrayList<ArrayList<Node>> subresult = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> tempresult = new ArrayList<Node>();
        result.add(source);
        tempresult.add(source); //first element should be the source
        //start ordering
        int count_a = 1;
        ArrayList<Node> temp_resultA = new ArrayList<Node>();
        while (count_a > 0) {
            count_a = 0;//if 0 stop (meaning all edges have been visited 
            for (int i = 0; i< tempresult.size(); i++) {
                Node curr_node = tempresult.get(i);
                 for(int j = 0; j<curr_node.getOutgoings().size(); j++) {  //loop through every neighbour (unvisited one)
                    Edge curr_edge = curr_node.getOutgoings().get(j);
                    if(!curr_edge.isVisited()) {
                        //if not visited yet, get the destination of edge and add them to the new result
                        Node dest = curr_edge.getDestination(curr_node);
                        if(!result.contains(dest)) { //if it's already in the list, no need to put them in
                            result.add(dest);
                            temp_resultA.add(dest);
                        }
                        curr_edge.setVisited(true); //put a mark on visited edge 
                    }
                }
            }
            if (temp_resultA.size() > 0) { //if there are still something to explore
                tempresult.clear();
                tempresult.addAll(temp_resultA);
                temp_resultA.clear();
                count_a = 1;
            }
        }
        
        this.nodes.removeAll(nodes);
        this.setNodes(result);
        this.setAllEdgeVisited(false);
        
    }
    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }
    public void addNode(Node node) {
        this.nodes.add(node);
    }
    public Node getNode(int index) {
        return this.nodes.get(index);
    }
    
}
