import java.util.Random;

public class Main {
    public static void main(String[] args) {
     final Random random = new Random();
     StringListImpl stringListImpl = new StringListImpl();
     IntegerListImpl integerListImpl = new IntegerListImpl();
        for (int i = 0; i < 99999; i++) {
            integerListImpl.add(random.nextInt(100));

        }
        long start = System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - start);
    }
}