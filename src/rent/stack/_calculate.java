package rent.stack;

import java.util.LinkedList;

public class _calculate {
    public static void main(String[] args) {
        _calculate helper = new _calculate();
        System.out.println(helper.calculate("5 + 2 * (3 - 4) / 2"));
    }

    private int mIndex = 0;

    public int calculate(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        char sign = '+';
        int num = 0;
        int length = s.length();
        while (mIndex < length) {
            char c = s.charAt(mIndex++);
            boolean isDigit = isDigit(c);
            if (isDigit) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                num = calculate(s);
            }
            if ((!isDigit && c != ' ') || mIndex == length) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(num * stack.pop());
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                }
                sign = c;
                num = 0;
            }
            if (c == ')') {
                break;
            }
        }
        return sum(stack);
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
