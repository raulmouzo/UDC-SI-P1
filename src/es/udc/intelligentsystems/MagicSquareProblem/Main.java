package es.udc.intelligentsystems.MagicSquareProblem;

import es.udc.intelligentsystems.SearchStrategys.SearchStrategy;
import es.udc.intelligentsystems.SearchStrategys.SearchStrategyDepthFirst;
import es.udc.intelligentsystems.Node;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] matrix = {
                {4, 9, 2},
                {3, 5, 0},
                {0, 1, 0}};

        MagicSquareProblem magicSquareProblem =
                new MagicSquareProblem(new MagicSquareProblem.MagicSquareState(matrix, matrix.length));
        SearchStrategy searchStrategy = new SearchStrategyDepthFirst();
        Node[] nodes = searchStrategy.solve(magicSquareProblem);
        int i = 0;
        for(Node node : nodes){

            System.out.println("Node: " + (++i));
            System.out.println(node);

        }
    }
}
