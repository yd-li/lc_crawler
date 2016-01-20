/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
	    public int[] twoSum_hashmap(int[] numbers, int target) {
	    	if(numbers == null || numbers.length < 2) {
	    		return null;
	    	}
	        HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
	        for(int i=0; i<numbers.length; i++){
	            hs.put(numbers[i], i+1);
	        }       
	        
	        int[] a = new int[2];
	        
	        for(int i=0; i<numbers.length ; i++){
	            if ( hs.containsKey( target - numbers[i] )){
	                int index1 = i+1;
	                int index2 = hs.get(target - numbers[i]);
	                if (index1 == index2){
	                    continue;
	                }
	                a[0] = index1;
	                a[1] = index2;
	                return a;
	            }
	        }
	        return a;
	    }
// Can’t use the sort method here, since the question asks for indexes.
    public int[] twoSum_pointer(int[] numbers, int target) {
	    	if(numbers == null || numbers.length < 2) {
	    		return null;
	    	}
	        Arrays.sort(numbers);
	        int left = 0;
	        int right = numbers.length - 1;
	        int[] rst = new int[2];
	        
	        while( left < right){
	            int sum = numbers[left] +  numbers[right];
	            if( sum == target){
	                rst[0] = left + 1;
	                rst[1] = right + 1;
	                break;
	            }else if( sum < target){
	                left++;
	            }else{
	                right--;
	            }
	        }
	        return rst;
	    }

}
