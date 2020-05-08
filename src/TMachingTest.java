import java.util.Arrays;

public class TMachingTest {

    public void test(String cadena) {

        TMaching maching = new TMaching(cadena);

        boolean r = maching.reconocer();
        System.out.println(r ? "Cadena Aceptada" : "\nCadena no Aceptada");
        System.out.println();
    }

    public static void main(String[] args) {

        TMachingTest machingTest = new TMachingTest();

        machingTest.test("");
        machingTest.test("abc");
        machingTest.test("aabbcc");
        machingTest.test("aaabbbccc");
        machingTest.test("aaaabbbbcccc");

        machingTest.test("aabccccc");
        machingTest.test("aabbbbbcc");
        machingTest.test("a");
        machingTest.test("abbc");
        machingTest.test("accb");

    }
}