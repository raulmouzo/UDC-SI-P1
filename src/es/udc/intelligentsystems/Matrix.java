package es.udc.intelligentsystems;

public class Matrix {
    public static String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            sb.append("[ ");
            for (int num : row) {
                sb.append(num).append(" ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
    public static boolean matrixCompare(int[][] matrix1, int[][] matrix2) {
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            return false; // matrices have different dimensions
        }

        for(int i = 0; i < matrix1.length; i++) {
            for(int j = 0; j < matrix1[0].length; j++) {
                if(matrix1[i][j] != matrix2[i][j]) {
                    return false; // corresponding elements are different
                }
            }
        }

        return true; // matrices are equal
    }
    public static int matrixHashCode(int[][] matrix) {
        int hashCode = 1;
        final int prime = 31;

        for (int[] row : matrix) {
            for (int val : row) {
                hashCode = prime * hashCode + val;
            }
        }

        return hashCode;
    }
    public static int[][] copyMatrix(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = new int[original[i].length];
            for (int j = 0; j < original[i].length; j++) {
                copy[i][j] = original[i][j];
            }
        }
        return copy;
    }
}
