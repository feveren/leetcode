package rent.stack;

import java.util.LinkedList;

/**
 * 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * https://leetcode-cn.com/problems/min-stack/
 */
public class _155_MinStack {
    public static void main(String[] args) {

    }

    class MinStack {
        LinkedList<Integer> mStack;
        LinkedList<Integer> mMinStack;

        /** initialize your data structure here. */
        public MinStack() {
            mStack = new LinkedList<>();
            mMinStack = new LinkedList<>();
        }

        public void push(int x) {
            mStack.push(x);
            if (mMinStack.isEmpty() || x <= getMin()) {
                mMinStack.push(x);
            }
        }

        public void pop() {
            Integer val = mStack.pop();
            if (val != null && val.equals(getMin())) {
                mMinStack.pop();
            }
        }

        public int top() {
            Integer val = mStack.peek();
            return val == null ? -1 : val;
        }

        public int getMin() {
            Integer val = mMinStack.peek();
            return val == null ? -1 : val;
        }
    }
}
