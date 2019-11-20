package net.thumbtack.school.introduction;

public class FirstSteps {

    public int sum(int x, int y) {

        return x + y;
    }

    public int mul(int x, int y) {

        return x * y;
    }

    public int div(int x, int y) {

        return x / y;
    }

    public int mod(int x, int y) {

        return x % y;
    }

    public boolean isEqual(int x, int y) {

        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {

        return x >= xLeft && x <= xRight && y >= yTop && y <= yBottom;
    }

    public int sum(int[] array) {

        int sum = 0;
        for (int anArray : array) {
            sum += anArray;
        }
        return sum;
    }

    public int mul(int[] array) {

        int sum = 0;
        if (array.length != 0) {
            sum = array[0];
            for (int i = 1; i < array.length; i++) {
                sum *= array[i];
            }
        }
        return sum;
    }

    public int min(int[] array) {

        int min = Integer.MAX_VALUE;
        for (int anArray : array) {
            if (anArray < min) {
                min = anArray;
            }
        }
        return min;
    }

    public int max(int[] array) {

        int max = Integer.MIN_VALUE;
        for (int anArray : array) {
            if (anArray > max) {
                max = anArray;
            }
        }
        return max;
    }

    public double average(int[] array) {

        double sum = 0;
        if (array.length != 0) {
            for (int anArray : array) {
                sum += anArray;
            }
            sum /= array.length;
        }
        return sum;
    }

    public boolean isSortedDescendant(int[] array) {

        boolean sorted = true;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] <= array[i + 1]) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public void cube(int[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] = (int) Math.pow(array[i], 3);
        }
    }

    public boolean find(int[] array, int value) {

        boolean find = false;
        for (int anArray : array) {
            if (anArray == value) {
                find = true;
            }
        }
        return find;
    }

    public void reverse(int[] array) {

        int tmp;
        for (int i = 0; i < array.length / 2; i++) {
            tmp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = tmp;
        }
    }

    public boolean isPalindrome(int[] array) {

        boolean palindrome = true;
        for (int i = 0; i < array.length / 2; i++) {
            if (array[i] != array[array.length - i - 1]) {
                palindrome = false;
                break;
            }
        }
        return palindrome;
    }

    public int sum(int[][] matrix) {

        int sum = 0;
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                sum += anAMatrix;
            }
        }
        return sum;
    }

    public int max(int[][] matrix) {

        int max = Integer.MIN_VALUE;
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                if (anAMatrix > max) {
                    max = anAMatrix;
                }
            }
        }
        return max;
    }

    public int diagonalMax(int[][] matrix) {

        int diagonalMax = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][i] > diagonalMax) {
                diagonalMax = matrix[i][i];
            }
        }
        return diagonalMax;
    }

    public boolean isSortedDescendant(int[][] matrix) {

        boolean sort = true;
        for (int[] aMatrix : matrix) {
            if (!isSortedDescendant(aMatrix)) {
                sort = false;
                break;
            }
        }
        return sort;
    }
}