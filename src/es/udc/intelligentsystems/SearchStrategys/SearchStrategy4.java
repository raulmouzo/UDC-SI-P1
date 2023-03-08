package es.udc.intelligentsystems.SearchStrategys;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;

import java.util.ArrayList;
import java.util.Collections;

public class SearchStrategy4 implements SearchStrategy {

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        State actualState = p.getInitialState();
        ArrayList<State> explored = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node actualNode = new Node(null, null, actualState);
        boolean newState;

        while(!p.isGoal(actualState)){
            newState = false;
            explored.add(actualState);
            nodes.add(actualNode);
            for(Action act : p.actions(actualState)){
                State nextState = p.result(actualState,act);
                if(!explored.contains(nextState)){
                    newState = true;
                    actualState = nextState;
                    actualNode = new Node(actualNode, act, actualState);
                    break;
                }
            }
            if(!newState){
                throw new Exception("The problem has no solution\n");
            }
        }
        return reconstructSol(actualNode);
    }

    public static Node[] reconstructSol(Node node){
        ArrayList<Node> nodesSol = new ArrayList<>();
        while(node != null){
            nodesSol.add(node);
            node = node.getFather();
        }
        Collections.reverse(nodesSol);
        return nodesSol.toArray(new Node[nodesSol.size()]);
    }
}
