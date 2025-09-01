package main.java.hash;

import java.util.*;

/**
 * hash表相关的算法题
 */
public class Main {
    public static void main(String[] args) {
//        System.out.println(happyNum(19));
//        int[] nums = new int[]{0, 0, 0};
//        List<List<Integer>> res = threeSum(nums);
//        System.out.println(res);

        int[] nums1 = new int[]{3, 0, -2, -1, 1, 2};
        List<List<Integer>> lists = threeSum(nums1);
        System.out.println(lists);
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
     * 示例1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     */
    public static boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            record[t.charAt(i) - 'a']--;
        }
        for (int count : record) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 两个数组求交集
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> unique = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int num : nums1) {
            unique.add(num);
        }
        for (Integer num : nums2) {
            if (unique.contains(num)) {
                res.add(num);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 快乐数
     * 快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * 示例：
     * 输入：19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */
    public static boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getSum(n);
        }
        return n == 1;
    }


    private static int getSum(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }

    /**
     * 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * <p>
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     * <p>
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * <p>
     * 示例：
     * <p>
     * 输入：19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     * <p>
     * #
     */
    public static boolean happyNum(int num) {
        Set<String> nums = new HashSet<>();
        return check(num + "", nums);
    }

    public static boolean check(String num, Set<String> nums) {
        int sum = 0;
        for (char c : num.toCharArray()) {
            int n = c - '0';
            sum += n * n;
        }
        if (sum == 1) {
            return true;
        }
        num = sum + "";
        if (nums.add(num)) {
            return check(num, nums);
        }
        return false;
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * <p>
     * 注意： 答案中不可以包含重复的三元组。
     * <p>
     * 示例：
     * <p>
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为： [ [-1, 0, 1], [-1, -1, 2] ]
     * <p>
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums.length == 0 || nums.length == 1) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //对l和r进行去重
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    r--;
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

}
