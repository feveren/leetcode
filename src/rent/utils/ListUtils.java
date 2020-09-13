package rent.utils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class ListUtils {
    public static <T> String toString(List<T> list) {
        return toString(list, ", ");
    }

    public static <T> String toString(List<T> list, String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (t instanceof Collection) {
                Collection collection = (Collection) t;
                boolean first = true;
                for (Object o : collection) {
                    if (!first) {
                        builder.append(s);
                    }
                    first = false;
                    builder.append(o);
                }
                builder.append("\n");
            } else {
                if (i > 0) {
                    builder.append(s);
                }
                builder.append(t);
            }
        }
        return builder.toString();
    }

    public static <T> String toString(Set<T> list) {
        StringBuilder builder = new StringBuilder();
        for (T t : list) {
            builder.append(", " + t);
        }
        builder.delete(0, 1);
        return builder.toString();
    }
}
