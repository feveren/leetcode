package rent.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class _076_minWindow {
    public static void main(String[] args) {
        String src = "ADOBECODEBANC";
        String target = "ABC";
//        String src = "a";
//        String target = "aa";
        System.out.println(midWindow(src, target));
    }

    private static String midWindow(String src, String target) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        for (char c : target.toCharArray()) {
            addOne(need, c);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;
        char[] srcs = src.toCharArray();
        while (right < srcs.length) {
            char c = srcs[right];
            right++;
            if (!need.containsKey(c)) {
                continue;
            }
            addOne(window, c);
            if (window.get(c).equals(need.get(c))) {
                valid++;
            }
            if (valid < need.size()) {
                continue;
            }
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char remove = srcs[left];
                left++;
                if (window.containsKey(remove)) {
                    int count = window.get(remove);
                    if (count > 0) {
                        window.put(remove, count - 1);
                    }
                    if (window.get(remove) < need.get(remove)) {
                        valid--;
                    }
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : src.substring(start, start + len);
    }

    private static void addOne(Map<Character, Integer> map, char key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + 1);
        } else {
            map.put(key, 1);
        }
    }
}
