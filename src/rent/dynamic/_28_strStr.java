package rent.dynamic;

import java.util.Arrays;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * https://leetcode-cn.com/problems/implement-strstr/
 */
public class _28_strStr {
    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "bba"));
    }

    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        int len1 = haystack.length();
        int len2 = needle.length();
        if (len2 > len1) {
            return -1;
        }
        int[] next = buildNext(needle);
        int i = 0;
        int j = 0;
        while (i < len1 && j < len2) {
            if (j == - 1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == len2 ? i - j : -1;
    }

    private static int[] buildNext(String pattern) {
        int[] next = new int[pattern.length() + 1];
        next[0] = -1;
        int len = 0;
        int i = 1;
        while (i < pattern.length()) {
            if (len == -1 || pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                i++;
                next[i] = len;
            } else {
                len = next[len];
            }
        }

//        int[] next = new int[pattern.length()];
//        int len = 0;
//        int i = 1;
//        while (i < pattern.length()) {
//            if (pattern.charAt(i) == pattern.charAt(len)) {
//                next[i] = len + 1;
//                len++;
//            } else {
//                if (len == 0) {
//                    next[len] = 0;
//                    len = 0;
//                } else {
//                    len = next[len - 1];
//                    continue;
//                }
//            }
//            i++;
//        }
        System.out.println(Arrays.toString(next));
        return next;
    }
}
