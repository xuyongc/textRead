package com.xu.textread.utils;

/**
 * @author aniki
 * @CreteDate 2023/2/11 15:23
 **/
public class NumberUtils {

    public static boolean isNullAndLessZero(Long... numbers) {
        for (Long number : numbers) {
            if (number == null || number <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullAndLessZero(Integer... numbers) {
        for (Integer number : numbers) {
            if (number == null || number <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullAndLessZero(Integer number1,Long... numbers) {
        for (Long number : numbers) {
            if (number == null || number <= 0) {
                return false;
            }

            if (number1 == null || number1 < 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isNumberLessZero(Long... numbers) {
        for (Long number : numbers) {
            if (number <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(Long... numbers) {
        for (Long number : numbers) {
            if (number == null) {
                return false;
            }
        }
        return true;
    }
}
