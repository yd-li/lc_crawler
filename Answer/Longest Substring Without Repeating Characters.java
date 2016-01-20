/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        HashSet<Character> set = new HashSet<Character>();
        
        int leftBound = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
                    set.remove(s.charAt(leftBound));
                    leftBound ++;
                }
                leftBound ++;
            } else {
                set.add(s.charAt(i));
                max = Math.max(max, i - leftBound + 1);
            }
        }
        
        return max;
    }
}

public class Solution {
    /**
     * @param s: a string
     * @return: an integer 
     */
     //方法一：
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[256]; // map from character&#39;s ASCII to its last occured index
        Arrays.fill(map, -1);
        
        int slow = 0;
        int fast = 0;
        int ans = 0;
        for (fast = 0; fast < s.length(); fast++) {
            int ch = s.charAt(fast);
            while (map[ch]!=-1 && slow < fast) {
                int temp = s.charAt(slow);
                map[temp] = -1;
                slow ++;
            }
            map[ch] = 0;
            ans = Math.max(ans, fast-slow + 1);
        }
        
        return ans;
    }
    // 方法二：
    // public int lengthOfLongestSubstring(String s) {
    //     int[] map = new int[256]; // map from character&#39;s ASCII to its last occured index
    //     int ans = 0;
    //     int slow = 0;

    //     Arrays.fill(map, -1);
        
    //     for (int fast = 0; fast < s.length(); fast++) {
    //         int ch = s.charAt(fast);
    //         if (map[ch] >= slow) {
    //             ans = Math.max(ans, fast - slow);
    //             slow = map[ch] + 1;
    //         }
    //         map[ch] = fast;
    //     }
        
    //     return Math.max(ans, s.length() - slow);
    // }

    

}


