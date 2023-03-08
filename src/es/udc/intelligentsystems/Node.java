package es.udc.intelligentsystems;

public class Node implements Comparable<Node>{
    private final Node father;
    private final Action action;
    private final State state;

    public Node(Node father, Action action, State state) {
        this.father = father;
        this.action = action;
        this.state = state;
    }


    //Heuristic
    private float f;
    private float cost;

    public Node(Node father, Action action, State state, Heuristic heuristic) {
        this.father = father;
        this.action = action;
        this.state = state;
        if(father != null){
            this.cost = father.cost + action.getCost();
            if(heuristic != null){
                this.f = cost + heuristic.evaluate(state);
            }
        }
    }


    public Node getFather() {
        return father;
    }

    public Action getAction() {
        return action;
    }

    public State getState() {
        return state;
    }

    public float getF() {
        return f;
    }

    public float getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Node node = (Node) obj;

        return state.equals(node.getState());
    }

    @Override
    public String toString() {
        return state.toString();
    }

    @Override
    public int compareTo(Node o) {
        if(o.f == this.f) return 0;
        return o.f < this.f ? 1 : -1;
    }
}
