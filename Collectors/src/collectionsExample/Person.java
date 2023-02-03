package collectionsExample;

import java.util.*;

class Person implements Comparable<Person> {
    public String name;
    public Person(String name) {
        this.name = name;
    }
    public int compareTo(Person person) {
        return name.compareTo(person.name);

    }
}
