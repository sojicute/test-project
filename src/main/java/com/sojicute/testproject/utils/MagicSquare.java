package com.sojicute.testproject.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Log4j2
@Component
public class MagicSquare {

    private static final int N = 3;

    public int getCost(String str) {

        int[] inputArray = convertToIntegerArray(str);

        int minCost = Integer.MAX_VALUE;

        int[] a = new int[9];

        for (int i = 0; i < 9; i++) {
            a[i] = (i+1);
        }


        do {
            int[][] matrix = convertToMatrix(a);

            if (isMagicSquare(matrix)) {
                int cost = diff(convertToMatrix(inputArray), matrix);
                if (cost < minCost) {
                    minCost = cost;
                }
            }

        } while (nextPermutation(a));

        return minCost;
    }

    // Алгоритм перестановки
    private boolean nextPermutation(int[] array) {

        int i = array.length - 1;

        while (i > 0 && array[i - 1] >= array[i])
            i--;


        if (i <= 0)
            return false;


        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;


        // Перестановка соседних элементов
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;


        j = array.length - 1;

        // Меняет последовательность элементов
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return true;
    }

    private boolean isMagicSquare(int[][] mat) {

        int magicSum = N*(N*N+1)/2;

        for (int i = 0; i < N; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < N; j++) {
                rowSum += mat[i][j];
                colSum += mat[j][i];
            }
            if (rowSum != colSum || colSum != magicSum ) {
                return false;
            }
        }

        return true;
    }

    private int diff(int[][] a, int[][] b) {
        int cost = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost += Math.abs(a[i][j]-b[i][j]);
            }
        }
        return cost;
    }

    public int[][] convertToMatrix(int[] array) {

        int[][] matrix = new int[3][3];

        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = array[index];
                index++;
            }
        }

        return matrix;
    }

    public int[] convertToIntegerArray(String str) {
        return Stream.of(str.split(""))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

}

