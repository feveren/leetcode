package rent.strings;

/**
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 *
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class _125_isPalindrome {
    public static void main(String[] args) {
        _125_isPalindrome helper = new _125_isPalindrome();
        System.out.println(helper.isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(helper.isPalindrome("race a car"));
//        System.out.println(helper.isPalindrome("ab"));
//        System.out.println(helper.isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            while (!isValid(chars[left]) && left < right) {
                left++;
            }
            while (!isValid(chars[right]) && left < right) {
                right--;
            }
            if (left < right) {
                if (Character.toLowerCase(chars[left]) != Character.toLowerCase(chars[right])) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }

    private boolean isValid(char c) {
        return (c >= '0' && c <= '9')
                || (c >= 'A' && c <= 'Z')
                || (c >= 'a' && c <= 'z');
    }
}
