package collectionsExample;

import java.util.LinkedList;
import java.util.List;

public class CollectionsExercise {

    public static void main(String[] args) {
        List<String> listOfItems=new LinkedList<String>();
        listOfItems.add("Mike");
        listOfItems.add("Bob");
        listOfItems.add("Alice");

        for(String a:listOfItems) {
            System.out.println(a);
        }

    }
}
