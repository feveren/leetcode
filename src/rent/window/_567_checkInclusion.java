package rent.window;

import java.util.HashMap;
import java.util.Map;

/**
 * 567. 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 注意：
 * 1. 输入的字符串只包含小写字母
 * 2. 两个字符串的长度都在 [1, 10,000] 之间
 */
public class _567_checkInclusion {
    public static void main(String[] args) {
        System.out.println(checkInclusion("eidbaooo", "ab"));
        System.out.println(checkInclusion("eidboaoo", "ab"));
    }

    private static boolean checkInclusion(String src, String target) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : target.toCharArray()) {
            int count = need.getOrDefault(c, 0);
            need.put(c, count + 1);
        }
        int left = 0, right = 0;
        int valid = 0;
        int len = target.length();
        char[] chars = src.toCharArray();
        for (char c : chars) {
            right++;
            if (need.containsKey(c)) {
                int count = window.getOrDefault(c, 0);
                window.put(c, count + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (right - left >= len) {
                if (valid == need.size()) {
                    return true;
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
        return false;
    }
}
