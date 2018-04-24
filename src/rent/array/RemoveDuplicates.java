package rent.array;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 * 给定数组 nums = [1,1,2],
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        run(new int[] { 1,1,2 });
        run(new int[] { 0,0,1,1,1,2,2,3,3,4 });
    }

    private static void run(int[] array) {
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] != array[i]) {
                array[count] = array[i];
                count++;
            }
        }
        System.out.println("total = " + count);
        for (int i = 0; i < count; i++) {
            System.out.println(array[i]);
        }
    }
}
