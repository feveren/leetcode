package rent.array;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 数组全排序
 */
public class AllOrder {
    public static void main(String[] args) {
        int[] array = new int[] { 1, 2, 3, 4 };
        List<Set<Integer>> result = order(array);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, ListUtils.toString(result.get(i))));
        }
    }

    private static List<Set<Integer>> order(int[] array) {
        List<Set<Integer>> result = new ArrayList<>();
        Set<Integer> track = new LinkedHashSet<>();
        order(array, track, result);
        return result;
    }

    private static void order(int[] array, Set<Integer> track, List<Set<Integer>> result) {
        if (track.size() == array.length) {
            result.add(new LinkedHashSet<>(track));
            return;
        }
        for (int i : array) {
            if (track.contains(i)) {
                continue;
            }
            track.add(i);
            order(array, track, result);
            track.remove(i);
        }
    }
}
