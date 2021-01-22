import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestUnits {
    @Test
    public void testProducer() {
        AlbumDataCallback<String> callback = new AlbumDataCallback<>();
        Type type = ((ParameterizedType) callback.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Class returnType = (Class)type;
        System.out.println(type);
    }

    public abstract class HttpCallBack<T> {

    }

    public class AlbumDataCallback<T> extends HttpCallBack<T> {

    }

    public class AlbumStringCallback extends AlbumDataCallback<String> {

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
