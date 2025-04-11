package main.java.hash;

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
}
