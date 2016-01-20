/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

// Non-regex version

public class Solution {
    public boolean isNumber(String s) {
        int len = s.length();
        int i = 0, e = len - 1;
        while (i <= e && Character.isWhitespace(s.charAt(i))) i++;
        if (i > len - 1) return false;
        while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
        // skip leading +/-
        if (s.charAt(i) == &#39;+&#39; || s.charAt(i) == &#39;-&#39;) i++;
        boolean num = false; // is a digit
        boolean dot = false; // is a &#39;.&#39;
        boolean exp = false; // is a &#39;e&#39;
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = true;
            }
            else if (c == &#39;.&#39;) {
                if(exp || dot) return false;
                dot = true;
            }
            else if (c == &#39;e&#39;) {
                if(exp || num == false) return false;
                exp = true;
                num = false;
            }
            else if (c == &#39;+&#39; || c == &#39;-&#39;) {
                if (s.charAt(i - 1) != &#39;e&#39;) return false;
            }
            else {
                return false;
            }
            i++;
        }
        return num;
    }
}

