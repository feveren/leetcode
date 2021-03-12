import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestUnits {

    @Test
    public void testProducer() {
        Class<Base> clazz = Base.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName() + " " + Arrays.toString(method.getParameterTypes()));
        }
    }

    static class Base {
        public void baseTest() {

        }
    }

    static class Child extends Base {
        public void childTest() {

        }

        public void childTest2() {

        }

        private void childTest3() {

        }
    }

    @Test
    public void testRandom10() {
//        for (int i = 1; i <= 7; i++) {
//            for (int j = 1; j <= 7; j++) {
//                System.out.println((i - 1) * 7 + j);
//            }
//        }
        for (int i = 0; i < 50; i++) {
            System.out.println(random10());
        }
    }

    public int random10() {
        int i = (random7() - 1) * 7 + random7();
        if (i <= 40) {
            return i % 10 + 1;
        }
        return random10();
    }

    public int random7() {
        return (int) (Math.random() * 7) + 1;
    }
}
