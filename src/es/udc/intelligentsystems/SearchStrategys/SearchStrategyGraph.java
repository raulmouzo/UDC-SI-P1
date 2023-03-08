package es.udc.intelligentsystems.SearchStrategys;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SearchStrategyGraph implements SearchStrategy{
    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        State actualState;
        Node actualNode;
        ArrayList<State> explored = new ArrayList<>();
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(new Node(null, null, p.getInitialState()));
        while(!frontier.isEmpty()){
            actualNode = frontier.poll();
            actualState = actualNode.getState();
            if(p.isGoal(actualState)){
                return reconstructSol(actualNode);
            }
            explored.add(actualState);
            for(Action act : p.actions(actualState)){
                State nextState = p.result(actualState, act);
                Node nextNode = new Node(actualNode, act, nextState);
                if(!explored.contains(nextState) && !frontier.contains(nextNode)){
                    frontier.add(nextNode);
                }
            }
        }
        throw new Exception("The problem has no solution\n");
    }
    public static Node[] reconstructSol(Node node){
        ArrayList<Node> nodesSol = new ArrayList<>();
        while(node != null){
            nodesSol.add(node);
            node = node.getFather();
        }
        Collections.reverse(nodesSol);
        return nodesSol.toArray(new Node[0]);
    }
}
