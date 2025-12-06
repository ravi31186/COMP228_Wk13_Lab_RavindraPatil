package application;

import java.security.SecureRandom;
import java.util.Hashtable;

public class LottoGenerator {

    SecureRandom rand = new SecureRandom();
    Hashtable<Integer, Integer> table = new Hashtable<>();
    int min, max;

    public LottoGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getNumber() {

        int num;

        do {
            num = rand.nextInt((max - min) + 1) + min;
        } while (table.containsKey(num));

        table.put(num, num);
        return num;
    }
}
