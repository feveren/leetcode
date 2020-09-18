package rent.stack;

import java.util.LinkedList;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * https://leetcode-cn.com/problems/decode-string/
 */
public class _394_decodeString {
    public static void main(String[] args) {
        _394_decodeString helper = new _394_decodeString();
        String s = "3[a2[c]]";
        System.out.println(helper.decodeString(s));
    }

    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        LinkedList<Integer> stackMulti = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        char[] chars = s.toCharArray();
        int multi = 0;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                int num = Integer.parseInt(String.valueOf(c));
                multi = multi * 10 + num;
            }
            else if (c == '[') {
                stackMulti.push(multi);
                stack.push(res.toString());
                multi = 0;
                res.delete(0, res.length());
            }
            else if (c == ']') {
                int m = stackMulti.pop();
                String str = res.toString();
                res.delete(0, res.length());
                res.append(stack.pop());
                for (int i = 0; i < m; i++) {
                    res.append(str);
                }
            }
            else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
