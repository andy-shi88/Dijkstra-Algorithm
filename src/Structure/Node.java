package Structure;

import java.util.ArrayList;

public class Node {
    private String _name;
    private ArrayList<Edge> _outgoings;
    private double acumulate_cost;
    private int lastChangedEdgeIndex;
    public Node (String name) {
        this._name = name;
        this._outgoings = new ArrayList<Edge>();
        this.acumulate_cost = Double.MAX_VALUE;
        this.lastChangedEdgeIndex = -1;
    }
    
    public void addOutNode(Node out, double distance) {
        Edge outEdge = new Edge(this, out, distance);
        this.getOutgoings().add(outEdge);
        out.getOutgoings().add(outEdge);
    }
    
    public void removeEdgeAtIndex(int index) {
        this._outgoings.remove(index);
    }
    
    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public ArrayList<Edge> getOutgoings() {
        return _outgoings;
    }

    public void setOutgoings(ArrayList<Edge> _outgoings) {
        this._outgoings = _outgoings;
    }

    public double getAcumulate_cost() {
        return acumulate_cost;
    }

    public void setAcumulate_cost(double acumulate_cost) {
        this.acumulate_cost = acumulate_cost;
    }

    public int getLastChangedEdgeIndex() {
        return lastChangedEdgeIndex;
    }

    public void setLastChangedEdgeIndex(int lastChangedEdgeIndex) {
        this.lastChangedEdgeIndex = lastChangedEdgeIndex;
    }
    
}
