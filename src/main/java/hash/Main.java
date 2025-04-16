package main.java.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * hash表相关的算法题
 */
public class Main {
    public static void main(String[] args) {
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
}
