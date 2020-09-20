package rent.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class _003_lengthOfLongestSubstring {
    public static void main(String[] args) {
        _003_lengthOfLongestSubstring helper = new _003_lengthOfLongestSubstring();
        System.out.println(helper.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(helper.lengthOfLongestSubstring("bbbbb"));
        System.out.println(helper.lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int len = 0;
        while (right < s.length()) {
            char add = s.charAt(right);
            window.put(add, window.getOrDefault(add, 0) + 1);
            right++;
            while (window.get(add) > 1) {
                char remove = s.charAt(left);
                window.put(remove, window.get(remove) - 1);
                left++;
            }
            len = Math.max(len, right - left);
        }
        return len;
    }
}
