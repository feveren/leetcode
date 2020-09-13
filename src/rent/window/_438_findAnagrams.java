package rent.window;

import rent.utils.ListUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 */
public class _438_findAnagrams {
    public static void main(String[] args) {
        System.out.println(ListUtils.toString(findAnagrams("cbaebabacd", "abc")));
        System.out.println(ListUtils.toString(findAnagrams("abab", "ab")));
    }

    private static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            int count = need.getOrDefault(c, 0);
            need.put(c, count + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int len = p.length();
        List<Integer> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            right++;
            if (need.containsKey(c)) {
                int count = window.getOrDefault(c, 0);
                window.put(c, count + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (right - left == len) {
                if (valid == need.size()) {
                    result.add(left);
                }
                char remove = chars[left];
                left++;
                if (window.containsKey(remove)) {
                    int count = window.getOrDefault(remove, 0);
                    if (count == need.get(remove)) {
                        valid--;
                    }
                    window.put(remove, count - 1);
                }
            }
        }
        return result;
    }
}
