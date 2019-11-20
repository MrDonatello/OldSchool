package net.thumbtack.school.matrix;

import java.awt.List;
import java.util.*;

public class MatrixNonSimilarRows {

    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {

        this.matrix = matrix;
    }

    public Set<int[]> getNonSimilarRows() {

        Set<int[]> set = new HashSet<>();
        Set<Integer> integers = new TreeSet<>();
        int b[];
        int[][] tmp = new int[matrix.length][];
        int[] crutch = {Integer.MIN_VALUE};

        for (int y = 0; y < matrix.length; y++) {
            for (int j = 0; j < matrix[y].length; j++) {
                integers.add(matrix[y][j]);
            }
            b = new int[integers.size()];

            for (int i = 0; i < integers.size(); ) {
                for (int a : integers) {
                    b[i] = a;
                    i++;
                }
            }
            tmp[y] = b;
            integers.clear();
        }
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 1 + i; j < tmp.length; j++) {
                if (Arrays.equals(tmp[i], crutch)) {
                    break;
                }
                if (!Arrays.equals(tmp[i], tmp[j])) {
                    set.add(matrix[i]);
                }
                if (Arrays.equals(tmp[i], tmp[j])) {
                    set.add(matrix[i]);
                    tmp[j] = crutch;
                }
            }
        }
        set.add(matrix[matrix.length - 1]);
        return set;
    }

//__________________________________________________________________________________//

    public Set<int[]> getNonSimilarRowsUpdate() {

        Set<int[]> set = new HashSet<>();

        java.util.List<int[]> list = new ArrayList<>(Arrays.asList(matrix));

        for (int [] i :list) {

            //беру первый уаляю из лист 2. сравниваю(содержит ли contains (или retainALL - оставить элименты в этом списке каторые есть в другом списке)) зарание скопированный в другой лит первый элемент
        }


        Set<Integer> integers = new TreeSet<>();
        int b[];
        int[][] tmp = new int[matrix.length][];
        int[] crutch = {Integer.MIN_VALUE};

        for (int y = 0; y < matrix.length; y++) {
            for (int j = 0; j < matrix[y].length; j++) {
                integers.add(matrix[y][j]);
            }
            b = new int[integers.size()];

            for (int i = 0; i < integers.size(); ) {
                for (int a : integers) {
                    b[i] = a;
                    i++;
                }
            }
            tmp[y] = b;
            integers.clear();
        }
        for (int i = 0; i < tmp.length; i++) {
            for (int j = 1 + i; j < tmp.length; j++) {
                if (Arrays.equals(tmp[i], crutch)) {
                    break;
                }
                if (!Arrays.equals(tmp[i], tmp[j])) {
                    set.add(matrix[i]);
                }
                if (Arrays.equals(tmp[i], tmp[j])) {
                    set.add(matrix[i]);
                    tmp[j] = crutch;
                }
            }
        }
        set.add(matrix[matrix.length - 1]);
        return set;
    }
}