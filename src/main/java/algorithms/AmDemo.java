package algorithms;

import java.util.ArrayList;
import java.util.List;

public class AmDemo {

    public static void main(String[] args) {

        int[] input = new int[]{1, 0, 0, 0, 0, 1, 0, 0};

        List<Integer> a = ravCellComplete(input, 1);

        System.out.println(a);
    }

    public static List<Integer> ravCellComplete(int[] states, int days) {
        int[] unchanged = states;
        int[] changed = new int[states.length];

        //days == 0
        if(days == 0) {
            List<Integer> result = new ArrayList<>();
            for(int i=0; i< states.length; i++) {
                result.add(states[i]);
            }
            return result;
        }

        for(int i=0; i<unchanged.length; i++) {
            //1st element
            if(i==0) {
               if(unchanged[i+1] ==0) {
                   changed[i] = 0;
               } else {
                   changed[i] = 1;
               }
            } else if (i == unchanged.length-1) {  //last element
                if(unchanged[i-1] == 0) {
                    changed[i] = 0;
                } else {
                    changed[i] =1;
                }

            } else if (unchanged[i-1] == unchanged[i+1]) { //middle elements
                changed[i] = 0;
            } else {
                changed[i] = 1;
            }


        }

        return ravCellComplete(changed, days-1);

    }
}
