package es.udc.intelligentsystems.VacuumCleanerProblem;

import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchStrategys.SearchStrategy;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.SearchStrategys.SearchStrategyGraph;

public class Main {

    public static void main(String[] args) throws Exception {
        VacuumCleanerProblem.VacuumCleanerState initialState = new VacuumCleanerProblem.VacuumCleanerState(VacuumCleanerProblem.VacuumCleanerState.RobotPosition.LEFT,
                                                                                                    VacuumCleanerProblem.VacuumCleanerState.DirtPosition.BOTH);
        SearchProblem aspiradora = new VacuumCleanerProblem(initialState);

        SearchStrategy buscador = new SearchStrategyGraph();
        Node[] nodes = buscador.solve(aspiradora);

        int i = 0;

        for(Node node : nodes){

            System.out.println("Node: " + (++i));
            System.out.println(node);

        }
    }
}
