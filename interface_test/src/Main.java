import java.util.function.BinaryOperator;
public class Main {

    public static void main(String[] args) {
        BinaryOperator<Integer> add =(a, b) -> a + 5*b;
        assert 17 == add.apply(2, 3);

        int sum=add.apply(5,6);
        System.out.println("sum is " + sum);
    }
}
