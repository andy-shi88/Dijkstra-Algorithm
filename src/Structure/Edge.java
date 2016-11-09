package Structure;

public class Edge {
    private Node _from;
    private Node _to;
    private boolean visited;
    private double distance;
    
    
    public Edge() {
        this._from = null;
        this._to = null;
        this.visited = false;
        this.distance = 0.0;
    }
    public Edge (Node from, Node to, double distance) {
        this._from = from;
        this._to = to;
        this.distance = distance;
        this.visited = false;
    }

    public Node getFrom() {
        return _from;
    }

    public void setFrom(Node _from) {
        this._from = _from;
    }

    
    public Node getDestination(Node from) {
        if(this._to == from)
            return this._from;
        else
            return this._to;
    }
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
}
