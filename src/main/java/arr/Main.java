package main.java.arr;

import java.util.Arrays;

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
//        System.out.println(removeElement(ORDERLY_NUMS, 5));
//        System.out.println(minSubArrayLen(ORDERLY_NUMS, 15));
        System.out.println(Arrays.deepToString(generateMatrix(5)));
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

    /**
     * 有序数组的平方
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * 示例 1：
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     * 示例 2：
     * 输入：nums = [-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     */
    public static int[] square(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }

    /**
     * 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
     * 示例：
     * 输入：s = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * 提示：
     * 1 <= target <= 10^9
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     */
    public static int minSubArrayLen(int[] nums, int target) {
        int l = 0, s = 0, len = Integer.MAX_VALUE;
        for (int r = 0; r < nums.length; r++) {
            s += nums[r];
            while (s >= target) {
                len = Math.min(len, r - l + 1);
                s -= nums[l++];
            }
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * 示例1
     * 输入：n = 3
     * 输出：[[1,2,3],[8,9,4],[7,6,5]]
     * 示例2
     * 输入：n = 1
     * 输出：[[1]]
     */
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int len = n - 1, val = 1, sx = 0, sy = 0;
        if (n == 1) {
            return new int[][]{{1}};
        }
        while (len > 0) {
            int x = sx, y = sy;
            //填充上边
            for (int i = 0; i < len; i++) {
                matrix[x][y++] = val++;
            }
            //填充右边
            for (int i = 0; i < len; i++) {
                matrix[x++][y] = val++;
            }
            //填充下边
            for (int i = 0; i < len; i++) {
                matrix[x][y--] = val++;
            }
            //填充左边
            for (int i = 0; i < len; i++) {
                matrix[x--][y] = val++;
            }
            //一圈填充完，调整边长。
            len -= 2;
            //定义新一圈的坐标起点
            sx++;
            sy++;
        }
        //n为奇数的时候，填充中心
        if (n % 2 == 1) {
            matrix[sx][sy] = n * n;
        }
        return matrix;
    }

    public static void rangeSum() {

    }
}
