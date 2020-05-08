package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Am {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(20, 4, 8, 2);
        List<Integer> list2 = Arrays.asList(1, 2, 5, 10, 35, 89);

        System.out.println( minimumTime(4, list)); System.out.println( minimumTime(6, list2));;

        System.out.println("23280669894430");
    }

    static int minimumTime(int numOfSubFiles, List<Integer> fileSizes)
    {
        //every value is > 0
        fileSizes.forEach(time -> {
            if(time < 1) {
                throw new IllegalArgumentException();
            }
        });

        //check numOfSubFiles constraint
        if(numOfSubFiles < 1 || numOfSubFiles > 1000000) {
            throw new IllegalArgumentException();
    }

        //check file size constraint
        if(fileSizes.size() >= 1000000) {
            return -1;
        }

        //recursive exit condition
        if(numOfSubFiles <= 2) {
            return fileSizes.stream().mapToInt(Integer::intValue).sum();
        }

        fileSizes.sort(Integer::compareTo);
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addAll(fileSizes);
        List<Integer> result = new ArrayList<>();

        Integer sum = linkedList.get(0) + linkedList.get(1);
        result.add(sum);

        linkedList.removeFirst();
        linkedList.removeFirst();

        linkedList.forEach(entry -> {
            result.add(result.get(result.size()-1) + entry);
        });

        return result.stream().mapToInt(Integer::intValue).sum();
    }

}
