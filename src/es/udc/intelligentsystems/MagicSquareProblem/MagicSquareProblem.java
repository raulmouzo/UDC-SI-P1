package es.udc.intelligentsystems.MagicSquareProblem;

import es.udc.intelligentsystems.Action;
import es.udc.intelligentsystems.Matrix;
import es.udc.intelligentsystems.SearchProblem;
import es.udc.intelligentsystems.State;

import java.awt.*;
import java.util.ArrayList;

public class MagicSquareProblem extends SearchProblem {
    public static class MagicSquareState extends State {
        private final int[][] matrix;
        private final int N;
        private final ArrayList<Integer> remainingNumbers;

        private static ArrayList<Integer> calcRemainingNumbers(int[][] matrix) {
            ArrayList<Integer> remainingNumbers = new ArrayList<>();
            int n = matrix.length;
            for (int i = 1; i <= n*n; i++) {
                remainingNumbers.add(i);
            }
            for (int[] row : matrix) {
                for (int num : row) {
                    if (num != 0) {
                        remainingNumbers.remove(Integer.valueOf(num));
                    }
                }
            }
            return remainingNumbers;
        }
        public MagicSquareState(int[][] matrix, int n) {
            if (n != matrix[0].length || n != matrix.length) {
                throw new IllegalArgumentException();
            }
            this.matrix = matrix;
            N = n;
            this.remainingNumbers = calcRemainingNumbers(matrix);
        }

        @Override
        public String toString() {
            return Matrix.matrixToString(matrix);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MagicSquareState that = (MagicSquareState) obj;
            return Matrix.matrixCompare(matrix, that.matrix);
        }
        public int hashCode() {
            return Matrix.matrixHashCode(matrix);
        }
        public int[][] getMatrix() {
            return matrix;
        }
    }

    public static class MagicSquareAction extends Action {

        private final Point pos;
        private final int num;

        public MagicSquareAction(int i, int j, int num) {
            this.pos = new Point(i,j);
            this.num = num;
        }

        @Override
        public String toString() {
            return "Action add number to square";
        }

        @Override
        public boolean isApplicable(State st) {
            MagicSquareState msState = (MagicSquareState) st;
            return (msState.matrix[pos.x][pos.y] == 0 &&
                    msState.remainingNumbers.contains(num));
        }
        @Override
        public State applyTo(State st) {
            MagicSquareState msState = (MagicSquareState) st;
            int[][] matrix = Matrix.copyMatrix(msState.matrix);
            matrix[pos.x][pos.y] = num;
            return new MagicSquareState(matrix,matrix.length);
        }
    }
    public MagicSquareProblem(MagicSquareState initialState){
        super(initialState);
    }
    public Action[] actions(State st){
        MagicSquareState msState = (MagicSquareState) st;
        ArrayList<MagicSquareAction> actions = new ArrayList<>();
        MagicSquareAction action;

        for(int i = 0 ;i < msState.N; i++){
            for(int j = 0; j < msState.N; j++){
                for(int n = 0; n < msState.remainingNumbers.size();n++){
                    action = new MagicSquareAction(i, j, msState.remainingNumbers.get(n));
                    if(action.isApplicable(st)){
                        actions.add(action);
                    }
                }
            }
        }
        return actions.toArray(new MagicSquareAction[0]);
    }
    @Override
    public boolean isGoal(State st) {
        MagicSquareState msState = (MagicSquareState) st;
        int[][] matrix = msState.matrix;
        int n = matrix.length;
        int magicSum = n * (n * n + 1) / 2;

        // Check rows and columns
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            if (rowSum != magicSum || colSum != magicSum) {
                return false;
            }
        }

        // Check diagonals
        int diagonalSum1 = 0;
        int diagonalSum2 = 0;
        for (int i = 0; i < n; i++) {
            diagonalSum1 += matrix[i][i];
            diagonalSum2 += matrix[i][n - i - 1];
        }
        return diagonalSum1 == magicSum && diagonalSum2 == magicSum;

        // Matrix is a magic square
    }
}
