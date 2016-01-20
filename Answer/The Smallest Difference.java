/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    /**
     * @param A, B: Two integer arrays.
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        // write your code here
        Arrays.sort(A);
        Arrays.sort(B);
        
        int idA = 0, idB = 0; 
        int ans = Integer.MAX_VALUE;
        for(idA = 0; idA < A.length && idB < B.length; idA++) {
            while(idB + 1 < B.length) {
                if(B[idB + 1] > A[idA]) {
                    break;
                }
                idB++;
            }
            if(idB < B.length) {
                ans = Math.min(ans, Math.abs(B[idB]- A[idA]));
            }
            if(idB + 1 < B.length) {
                ans = Math.min(ans, Math.abs(B[idB + 1]- A[idA]));
            }
        }
        
        return ans;
    }
}

