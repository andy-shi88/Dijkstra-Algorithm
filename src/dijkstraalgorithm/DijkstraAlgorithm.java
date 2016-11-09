package dijkstraalgorithm;

import Structure.Edge;
import Structure.Graph;
import Structure.Node;
import java.util.ArrayList;

public class DijkstraAlgorithm {

    private static ArrayList<Node> initial_graph; //store the before optimization version of the graph
    private static Graph graph;
    public static void main(String[] args) {   
        //initialize variables
        initial_graph = new ArrayList<Node>();
        graph = new Graph();
//initializing nodes
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        //        initial_graph.add(a);
//        initial_graph.add(b);
//        initial_graph.add(c);
//        initial_graph.add(d);
//        initial_graph.add(e);
//        initial_graph.add(f);
//        initial_graph.add(g);
        a.addOutNode(b, 10);
        a.addOutNode(c, 10);
        a.addOutNode(d, 10);
        b.addOutNode(c, 5);
        c.addOutNode(d, 5);
        c.addOutNode(e, 30);
        d.addOutNode(e, 20);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);
        graph.setSource(e);
//        findShortestPath(a, d);
        System.out.println("Before SPT");
        printGraph();
        System.out.println("End of graph");
        generateSPTree(graph);
        System.out.println("SPT");
        printGraph();
        System.out.println("End of SPT");
        
    }
    
    private static void printGraph() {
        for(int i = 0; i < graph.getNodes().size(); i++) {
            Node curr_node = graph.getNodes().get(i);
            System.out.println(curr_node.getName());
            for (int j =0; j< curr_node.getOutgoings().size(); j++) {
                Edge currEdge = curr_node.getOutgoings().get(j);
                Node currDest = currEdge.getDestination(curr_node);
                System.out.println("------" + currDest.getName() + "(" + currEdge.getDistance()+ ")(" + 
                        currDest.getAcumulate_cost() + ")");
            }
        }
    }
    /*
    * @param: graph store every node that need to be optimized, or need to be found the shortest path so we can use it later
    *function: loop through every node (vertices), and every edge (connection) in the graph, to set the new accumulated cost
    *           and remove the long distance one
    */
    private static void generateSPTree(Graph graph) { //get SPT (shortest path tree)
        graph.getNodes().get(0).setAcumulate_cost(0); //accumulate cost of source should be zero
        for (int i =0 ; i < graph.getNodes().size(); i++) { //loop through every node in the graph
            Node curr_node = graph.getNodes().get(i);
            for (int j = 0; j < curr_node.getOutgoings().size(); j++) { //loop through the outgoing edges of i-th node
                Edge curr_edge = curr_node.getOutgoings().get(j);
                if(!curr_edge.isVisited()) { //check only if it's not visited yet
                    Node curr_dest = curr_edge.getDestination(curr_node);
                    //destination index of current edge
                    int curr_destEdgeIndex = getIndexOfEdge(curr_dest, curr_edge);
                    curr_edge.setVisited(true); //visited
                    //calculate the cost
                    double temp_cost = curr_node.getAcumulate_cost() + curr_edge.getDistance();
                    //check if current path will generate better acumulate cost or not to destination
                    if(curr_dest.getAcumulate_cost() > temp_cost) {
                        //set the accumulated cost of current destination of the current edge
                        curr_dest.setAcumulate_cost(temp_cost);
                        int temp_lastChangedDestEdgeIndex = curr_dest.getLastChangedEdgeIndex();
                        if(!(temp_lastChangedDestEdgeIndex < 0)) { //if edge haven't been changed
                            Edge temp_destSourceEdge = curr_dest.getOutgoings().get(temp_lastChangedDestEdgeIndex);
                            Node temp_destSource = temp_destSourceEdge.getDestination(curr_dest);
                            int temp_destSourceEdgeIndex = getIndexOfEdge(temp_destSource, temp_destSourceEdge);
                            //remove the previous less effective edge both dest and source
                            curr_dest.removeEdgeAtIndex(curr_dest.getLastChangedEdgeIndex());
                            //remove the previous less effective edge at the source node
                            temp_destSource.removeEdgeAtIndex(temp_destSourceEdgeIndex);
                            if(j > curr_dest.getLastChangedEdgeIndex()) {//change the last changed edge position                            
                                curr_dest.setLastChangedEdgeIndex(curr_destEdgeIndex - 1); //positioned to the right of lastchanged index
                            } else {
                                curr_dest.setLastChangedEdgeIndex(curr_destEdgeIndex); //positioned to the left of lastchanged index
                            }
                        }else {
                            curr_dest.setLastChangedEdgeIndex(curr_destEdgeIndex);
                        }
                    } else {
                        //no use of this edge, remove on both at the source and the destination
                        curr_node.removeEdgeAtIndex(j);
                        curr_dest.removeEdgeAtIndex(curr_destEdgeIndex);
                        j--;
                    }
                }
            } 
        }
    }
    private static int getIndexOfEdge(Node dest, Edge edge) {
        int result = 0;
        for (int i = 0; i < dest.getOutgoings().size(); i++) {
            Edge curr_edge = dest.getOutgoings().get(i);
            if(curr_edge == edge) {
                result = i;
            }
        }
        return result;
    }
    
}
