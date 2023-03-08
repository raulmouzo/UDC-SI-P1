package es.udc.intelligentsystems.SearchStrategys;

import es.udc.intelligentsystems.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class SearchStrategyA implements InformedSearchStrategy{
    @Override
    public Node[] solve(SearchProblem p, Heuristic h) throws Exception {
        State actualState;
        Node actualNode;
        ArrayList<State> explored = new ArrayList<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        frontier.add(new Node(null, null, p.getInitialState(), h));
        int expandedNodes = 0;
        int createdNodes = 1;

        while(!frontier.isEmpty()){
            actualNode = frontier.poll();
            actualState = actualNode.getState();
            //System.out.println("Probando: \n" + estadoActual);
            if(p.isGoal(actualState)){
                System.out.println("Expanded nodes: " + expandedNodes);
                System.out.println("Created nodes: " + createdNodes);
                return reconstructSol(actualNode);
            }
            explored.add(actualState);
            expandedNodes++;
            for(Action acc: p.actions(actualState)){
                createdNodes++;
                State nextState = p.result(actualState, acc);
                Node nextNode = new Node(actualNode, acc, nextState, h);
                if(!explored.contains(nextState)) {
                    float f = nextNode.getF();
                    if(!frontier.contains(nextNode)){
                        frontier.add(nextNode);
                    } else {
                        for(Node nodoF: frontier){
                            if(nodoF.equals(nextNode) && f < nodoF.getF()){
                                frontier.remove(nodoF);
                                frontier.add(nextNode);
                            }
                        }
                    }
                }
            }
        }
        throw new Exception("No se ha encontrado solución\n");
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
