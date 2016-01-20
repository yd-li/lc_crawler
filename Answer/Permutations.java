/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
         ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
         if (num == null || num.length == 0) {
             return rst; 
         }

         ArrayList<Integer> list = new ArrayList<Integer>();
         helper(rst, list, num);
         return rst;
    }
    
    public void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, int[] num){
        if(list.size() == num.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = 0; i<num.length; i++){
            if(list.contains(num[i])){
                continue;
            }
            list.add(num[i]);
            helper(rst, list, num);
            list.remove(list.size() - 1);
        }
        
    }
}

// Non-Recursion

class Solution {
    /**
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> nums) {
        ArrayList<ArrayList<Integer>> permutations
             = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.size() == 0) {
            return permutations;
        }
        
        int n = nums.size();
        ArrayList<Integer> stack = new ArrayList<Integer>();
        
        stack.add(-1);
        while (stack.size() != 0) {
            Integer last = stack.get(stack.size() - 1);
            stack.remove(stack.size() - 1);
            
            // increase the last number
            int next = -1;
            for (int i = last + 1; i < n; i++) {
                if (!stack.contains(i)) {
                    next = i;
                    break;
                }
            }
            if (next == -1) {
                continue;
            }
            
            // generate the next permutation
            stack.add(next);
            for (int i = 0; i < n; i++) {
                if (!stack.contains(i)) {
                    stack.add(i);
                }
            }
            
            // copy to permutations set
            ArrayList<Integer> permutation = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                permutation.add(nums.get(stack.get(i)));
            }
            permutations.add(permutation);
        }
        
        return permutations;
    }
}

