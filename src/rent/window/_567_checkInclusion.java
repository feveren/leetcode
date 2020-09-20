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
        _567_checkInclusion helper = new _567_checkInclusion();
        System.out.println(helper.checkInclusion("eidbaooo", "ab"));
        System.out.println(helper.checkInclusion("eidboaoo", "ab"));
    }

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        for (int i = 0; i < s2.length(); i++) {
            char c = s2.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;
        while (right < s1.length()) {
            char add = s1.charAt(right);
            if (need.containsKey(add)) {
                int count = window.getOrDefault(add, 0) + 1;
                if (count == need.get(add)) {
                    valid++;
                }
                window.put(add, count);
            }
            right++;
            while (right - left >= need.size()) {
                if (valid == need.size()) {
                    return true;
                }
                char remove = s1.charAt(left);
                if (need.containsKey(remove)) {
                    int count = window.getOrDefault(remove, 0);
                    if (count == need.get(remove)) {
                        valid--;
                    }
                    window.put(remove, count - 1);
                }
                left++;
            }
        }
        return false;
    }
}
