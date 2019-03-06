package ru.gorbach.additional.hw9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class intersectLists {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(Arrays.asList("A","B","C","B","B","D"));
        List<String> list2 = new ArrayList<>(Arrays.asList("A","B","D","B","A","E"));

        System.out.println("----------First list to intersect----------");
        System.out.println(list1);

        System.out.println("----------Second list to intersect---------");
        System.out.println(list2);

        List<String> result = intersect(list1,list2);
        System.out.println("----------Result of intersection-----------");
        System.out.println(result);
    }

    public static List<String> intersect(List<String> list1, List<String> list2){
        List<String> result = new ArrayList<>();

        Iterator<String> iterator1 = list1.iterator();
        while(iterator1.hasNext()){
            String value = iterator1.next();

            Iterator<String> iterator2 = list2.iterator();
            while (iterator2.hasNext()){
                if (value.equals(iterator2.next())){
                    result.add(value);
                    iterator2.remove();
                    break;
                }
            }
        }
        return result;
    }
}
