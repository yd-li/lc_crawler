/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    
    /**
     * @param nums: A list of integers.
     * @return: The maximum number inside the window at each moving.
     */
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        // write your code here
    	ArrayList<Integer> ans = new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>();
        int i = 0;

        
        for(int now : nums) {
            i++;
            
    		while((!deque.isEmpty() && now > deque.peekLast())) {
    			deque.pollLast();
    		} 
    		deque.offer(now);
    		if(i > k  && deque.peekFirst() == nums[i - k - 1])
    		         deque.pollFirst();
    		if(i >= k) {
    			ans.add(deque.peekFirst());
    		}
            
        }
        return ans;

    }
}
