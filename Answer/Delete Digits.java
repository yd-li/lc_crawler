/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

class Solution {
public:
    /**
     *@param A: A positive integer which has N digits, A is a string.
     *@param k: Remove k digits.
     *@return: A string
     */
    string remove(string A, int pos) {
        return A.substr(0, pos) + A.substr(pos + 1, A.length() - pos - 1);
    }
    
    string DeleteDigits(string A, int k) {
        if (A.length() == k) {
            return "";
        }
        
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < A.length(); j++) {
                if (j == A.length() - 1 || A[j + 1] < A[j]) {
                    A = remove(A, j);
                    break;
                }
            }
        }
        
        int i = 0;
        while (i < A.length() - 1 && A[i] == &#39;0&#39;) {
            i++;
        }
        return A.substr(i, A.length() - i);
    }
};
