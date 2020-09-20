package rent.stack;

import java.util.LinkedList;

/**
 * 224. 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 * 输入: "1 + 1"
 * 输出: 2
 *
 * 示例 2:
 * 输入: " 2-1 + 2 "
 * 输出: 3
 *
 * 示例 3:
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 *
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class _224_calculate {
    public static void main(String[] args) {
        _224_calculate helper = new _224_calculate();
        System.out.println(helper.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate(String s) {
        return calculate(s, 0)[0];
    }

    public int[] calculate(String s, int start) {
        LinkedList<Integer> stack = new LinkedList<>();
        int num = 0;
        char sign = '+';
        int length = s.length();
        int i;
        for (i = start; i < length; i++) {
            char c = s.charAt(i);
            boolean isDigit = isDigit(c);
            if (isDigit) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                int[] nums = calculate(s, i + 1);
                num = nums[0];
                i = nums[1];
            }
            if ((!isDigit && c != ' ') || i == length - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                }
                num = 0;
                sign = c;
            }
            if (c == ')') {
                break;
            }
        }
        return new int[] {sum(stack), i};
    }

    private int sum(LinkedList<Integer> stack) {
        int num = 0;
        while (!stack.isEmpty()) {
            num += stack.pop();
        }
        return num;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
