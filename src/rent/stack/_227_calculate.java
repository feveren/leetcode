package rent.stack;

import java.util.LinkedList;

/**
 * 227. 基本计算器 II
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 *
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 *
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 *
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 *
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * https://leetcode-cn.com/problems/basic-calculator-ii/
 */
public class _227_calculate {
    public static void main(String[] args) {
        _227_calculate helper = new _227_calculate();
        System.out.println(helper.calculate(" 3+5 / 2 "));
    }

    public int calculate(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int num = 0;
        char sign = '+';
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            boolean isDigit = isDigit(c);
            if (isDigit) {
                num = num * 10 + (c - '0');
            }
            if ((!isDigit && c != ' ') || i == length - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                num = 0;
                sign = c;
            }
        }
        num = 0;
        while (!stack.isEmpty()) {
            num += stack.pop();
        }
        return num;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
