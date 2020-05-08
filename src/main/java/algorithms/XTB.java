package algorithms;

import java.util.ArrayList;
import java.util.List;

public class XTB {
    public static int solution(int N) { // 0-50
        // write your code in Java SE 8

        List<Integer> numbers = new ArrayList<>();
        numbers.add(0);

        while( numbers.stream().mapToInt(e->e).sum() != N ) {
            if(numbers.get(0) == 9) {
                numbers.add(0, 0);
            }
            numbers.set(0, numbers.get(0)+1);
        }

        StringBuilder builder = new StringBuilder();
        for (Integer number : numbers) {
            builder.append(number);
        }

        return Integer.parseInt( builder.toString() );

    }




}
