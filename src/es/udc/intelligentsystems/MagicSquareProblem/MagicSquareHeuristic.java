package es.udc.intelligentsystems.MagicSquareProblem;

import es.udc.intelligentsystems.Heuristic;
import es.udc.intelligentsystems.State;

public class MagicSquareHeuristic extends Heuristic {
    public static int getScore(int cte, int score, boolean completed){
        if(score == cte && completed){
            return 0;
        } else if(score < cte && !completed){
            return 1;
        } else return 1000;
    }
    @Override
    public float evaluate(State e) {
        MagicSquareProblem.MagicSquareState msState = (MagicSquareProblem.MagicSquareState) e;
        int score = 0;
        boolean col, row,dia1,dia2;
        int sumRow, sumCol, sumDia1, sumDia2;
        int[][] matriz = msState.getMatrix();
        int n = matriz.length;
        int constanteMagica = (n*(n*n + 1))/2;

        for(int i=0;i < n;i++){
            col = row = true;
            sumRow = sumCol = 0;
            for(int j=0;j < matriz[0].length;j++){
                if(matriz[i][j] == 0) row = false;
                if(matriz[j][i] == 0) col = false;
                sumRow += matriz[i][j];
                sumCol += matriz[j][i];
            }
            score += getScore(constanteMagica,sumRow,row);
            score += getScore(constanteMagica,sumCol,col);

        }
        dia1=dia2=true;
        sumDia1 = sumDia2 = 0;
        for(int i=0;i < matriz.length;i++){
            if(matriz[i][i] == 0) dia1 = false;
            if(matriz[i][n-1-i] == 0) dia2 = false;
            sumDia1 += matriz[i][i];
            sumDia2 += matriz[i][n-1-i];
        }
        score += getScore(constanteMagica,sumDia1,dia1);
        score += getScore(constanteMagica,sumDia2,dia2);

        return score;
    }
}
