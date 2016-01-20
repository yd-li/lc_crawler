/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    // Sort & search: space O(1), time O(n logn)
    // HashMap: space O(n), time O(n)
    public int longestConsecutive(int[] num) {
        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
        for(int i: num){
            hs.put(i, 0);
        }
        int maxl = 1;
        for(int i: num){
            if (hs.get(i) == 1) continue;

            int tmp = i;
            int current_max = 1;
            while(hs.containsKey(tmp+1)){
                current_max ++;
                tmp ++;
                hs.put(tmp, 1);
            }

            tmp = i;
            while(hs.containsKey(tmp-1)){
                current_max ++;
                tmp --;
                hs.put(tmp, 1);
            }

            maxl = Math.max(current_max, maxl);
        }

        return maxl;
    }
}
