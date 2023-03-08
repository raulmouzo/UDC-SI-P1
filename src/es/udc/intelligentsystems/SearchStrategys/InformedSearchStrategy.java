package es.udc.intelligentsystems.SearchStrategys;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchProblem;


public interface InformedSearchStrategy {
    /**
     * Solves a search problem, obtaining a goal state or throwing an exception if none is found
     * @param p Problem to solve
     * @param h Heuristic that assigns a utility value to each state
     * @return Goal state found
     */
    Node[] solve(SearchProblem p, Heuristic h) throws Exception;
}
