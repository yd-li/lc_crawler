/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

// version 1: enumeration
public class Solution {
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        
        int n = A.length;
        int answer = 1;
        
        // from left to right
        int length = 1; // just A[0] itself
        for (int i = 1; i < n; i++) {
            if (A[i] > A[i - 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }
        
        // from right to left
        length = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (A[i] > A[i + 1]) {
                length++;
            } else {
                length = 1;
            }
            answer = Math.max(answer, length);
        }
        
        return answer;
    }
}

// version 2: Memorization Search, can not get accepted, just show you how to write a memorization search code
public class Solution {
    private int[] flag;
    private int[] dp;
    private int[] A;
    private int n;
    
    public static int VISITED = 1;
    /**
     * @param A an array of Integer
     * @return  an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] A) {
        if (A == null) {
            return 0;
        }
        
        // initialize
        n = A.length;
        flag = new int[n];
        dp = new int[n];
        this.A = A;

        // memorization search
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = dfs(i);
            ans = Math.max(ans, dp[i]);
        }
        
        return ans;
    }
    
    int dfs(int index)   {
        if (flag[index] == VISITED) {
            return dp[index];
        }
        
        int ans = 1;
        if (index - 1 >= 0 && A[index] < A[index - 1]) {
            ans = dfs(index - 1) + 1;
        } 
        if (index + 1 < n && A[index] < A[index + 1]) {
            ans = Math.max(ans,  dfs(index + 1) + 1);
        }
        flag[index] = VISITED;
        dp[index] = ans;
        return ans;
    }
}