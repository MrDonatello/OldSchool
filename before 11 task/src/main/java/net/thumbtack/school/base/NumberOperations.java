package net.thumbtack.school.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberOperations {

    public static Integer find(int[] array, int value) {

        Integer integer = null;
        for (int i = 0; i < array.length; i++) {

            if (array[i] == value) {
                integer = i;
            }
        }
        return integer;
    }

    public static Integer find(double[] array, double value, double eps) {

        Integer integer = null;
        for (int i = 0; i < array.length; i++) {

            if (array[i] <= Math.abs(value + eps) && array[i] >= Math.abs(value - eps)) {
                integer = i;
            }
        }
        return integer;
    }

    public static Double calculateDensity(double weight, double volume, double min, double max) {

        Double double_return = null;
        if (weight / volume <= max && weight / volume >= min) {

            double_return = weight / volume;
        }
        return double_return;
    }

    public static Integer find(BigInteger[] array, BigInteger value) {

        Integer integer = null;
        for (int i = 0; i < array.length; i++) {

            if (array[i].equals(value)) {
                integer = i;
            }
        }
        return integer;
    }

    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max) {

        BigDecimal bigDecimal = null;
        int div_1;
        int div_2;

        div_1 = weight.divide(volume).compareTo(max);
        div_2 = weight.divide(volume).compareTo(min);
        if (div_1 <= 0 && div_2 >= 0) {
            bigDecimal = weight.divide(volume);
        }
        return bigDecimal;
    }
}
