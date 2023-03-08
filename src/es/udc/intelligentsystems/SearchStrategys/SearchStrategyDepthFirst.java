package es.udc.intelligentsystems.SearchStrategys;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Stack;


public class SearchStrategyDepthFirst implements SearchStrategy{

    @Override
    public Node[] solve(SearchProblem p) throws Exception {
        State actualState;
        Node actualNode;
        Stack<Node> frontier = new Stack<>();
        ArrayList<State> explored = new ArrayList<>();
        frontier.add(new Node(null, null, p.getInitialState()));
        int expandedNodes = 0;
        int createdNodes = 1;

        while(!frontier.isEmpty()){
            actualNode = frontier.pop();
            actualState = actualNode.getState();

            //System.out.println("Node: " + createdNodes);
            //System.out.println(actualNode);

            if(p.isGoal(actualState)){
                System.out.println("Created nodes: " + createdNodes);
                System.out.println("Expanded nodes: " + expandedNodes);
                return reconstructSol(actualNode);
            }
            explored.add(actualState);
            expandedNodes++;
            for(Action act : p.actions(actualState)){
                createdNodes++;
                State nextState = p.result(actualState, act);
                Node nextNode = new Node(actualNode, act, nextState);
                if(!frontier.contains(nextNode) && !explored.contains(nextState)){
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
