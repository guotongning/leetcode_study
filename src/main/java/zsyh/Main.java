package main.java.zsyh;

public class Main {
    public static void main(String[] args) {
//        System.out.println(mask("123", 1, 2, '*'));
        String s = "BAAABAB";
        System.out.println(test(s));
    }

    public static String mask(String originStr, int startIndex, int endIndex, char targetChar) {
        // write code here
        if (originStr == null || originStr.isEmpty()) {
            return "";
        }
        if (endIndex >= originStr.length()) {
            endIndex = originStr.length();
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        char[] cs = originStr.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (i + 1 >= startIndex && i + 1 <= endIndex) {
                cs[i] = targetChar;
            }
        }
        return new String(cs);
    }


    public static int test(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        char[] cs = s.toCharArray();
        int i = 0, j = cs.length - 1;
        int delF = 0;
        while (i < j) {
            if (cs[i] == 'A') {
                i++;
                continue;
            }
            if (cs[i] == 'B' && cs[i + 1] == 'A') {
                delF++;
                i++;
                continue;
            }
            if (cs[i] == 'B') {
                if (cs[j] == 'B') {
                    j--;
                } else if (cs[j] == 'A') {
                    delF++;
                    j--;
                }
            }
        }
        int m = 0, n = cs.length - 1;
        int delR = 0;
        while (m < n) {
            if (cs[n] == 'B') {
                n--;
                continue;
            }
            if (cs[n] == 'A' && cs[n - 1] == 'B') {
                delR++;
                n--;
                continue;
            }
            if (cs[n] == 'A') {
                if (cs[m] == 'A') {
                    m++;
                } else if (cs[m] == 'B') {
                    delR++;
                    m++;
                }
            }
        }
        return Math.min(delF, delR);
    }
}
