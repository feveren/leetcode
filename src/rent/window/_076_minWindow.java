package rent.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 *
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 *
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * https://leetcode-cn.com/problems/minimum-window-substring/
 */
public class _076_minWindow {
    public static void main(String[] args) {
        _076_minWindow helper = new _076_minWindow();
        String src = "ADOBECODEBANC";
        String target = "ABC";
//        String src = "a";
//        String target = "aa";
        System.out.println(helper.minWindow(src, target));
    }

    public String minWindow(String s, String t) {
        // 统计需要的数据
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;
        int start = -1, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            if (need.containsKey(c)) {
                int count = window.getOrDefault(c, 0) + 1;
                window.put(c, count);
                // 如果该字符的数量==需要的数量时，valid++
                if (count == need.get(c)) {
                    valid++;
                }
            }
            // 扩大窗口
            right++;
            // 所有字符都满足了需要的数量时
            while (valid == need.size()) {
                // 和之前的子串长度比较
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char removed = s.charAt(left);
                if (need.containsKey(removed)) {
                    int count = window.get(removed);
                    // 如果删除了需要的字符
                    if (count == need.get(removed)) {
                        valid--;
                    }
                    window.put(removed, count - 1);
                }
                // 收缩窗口
                left++;
            }
        }
        if (start == -1 || len == Integer.MAX_VALUE) {
            return null;
        }
        return s.substring(start, start + len);
    }
}
