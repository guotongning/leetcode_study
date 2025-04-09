package main.java.arr;

/**
 * 数组相关算法题
 */
public class Main {

    /**
     * 有序数组
     */
    public static final int[] ORDERLY_NUMS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
//        System.out.println(search(ORDERLY_NUMS, 11));
        System.out.println(removeElement(ORDERLY_NUMS, 5));
    }

    /**
     * 二分查找
     * 示例1
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例2
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     */
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                l = mid + 1;
            }
            if (nums[mid] > target) {
                r = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并原地修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 示例 1: 给定 nums = [3,2,2,3], val = 3, 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。 你不需要考虑数组中超出新长度后面的元素。
     * 示例 2: 给定 nums = [0,1,2,2,3,0,4,2], val = 2, 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
     */
    public static int removeElement(int[] nums, int val) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            if (nums[l] == val) {
                nums[l] = nums[r--];
            } else {
                l++;
            }
        }
        return l;
    }
}
