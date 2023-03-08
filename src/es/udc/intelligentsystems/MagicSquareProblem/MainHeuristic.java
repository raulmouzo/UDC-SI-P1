package es.udc.intelligentsystems.MagicSquareProblem;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.Node;
import es.udc.intelligentsystems.SearchStrategys.InformedSearchStrategy;
import es.udc.intelligentsystems.SearchStrategys.SearchStrategyA;

public class MainHeuristic {
    public static void main(String[] args) throws Exception {
        int[][] matrix = {
                {4, 0, 2},
                {0, 0, 0},
                {0, 0, 0}};

        MagicSquareProblem magicSquareProblem =
                new MagicSquareProblem(new MagicSquareProblem.MagicSquareState(matrix, matrix.length));
        InformedSearchStrategy searchStrategy = new SearchStrategyA();
        Heuristic h = new MagicSquareHeuristic();
        Node[] nodes = searchStrategy.solve(magicSquareProblem,h);
        int i = 0;
        for(Node node : nodes){

            System.out.println("Node: " + (++i));
            System.out.println(node);

        }
    }
}
