package utils;

import java.util.Random;

public class Utils {
    public static String getRandom(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
