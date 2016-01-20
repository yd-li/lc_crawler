/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
 	public int romanToInt(String s) {
	    if (s == null || s.length()==0) {
 return 0;
	    }
	    Map<Character, Integer> m = new HashMap<Character, Integer>();
	    m.put(&#39;I&#39;, 1);
	    m.put(&#39;V&#39;, 5);
	    m.put(&#39;X&#39;, 10);
	    m.put(&#39;L&#39;, 50);
	    m.put(&#39;C&#39;, 100);
	    m.put(&#39;D&#39;, 500);
	    m.put(&#39;M&#39;, 1000);

	    int length = s.length();
	    int result = m.get(s.charAt(length - 1));
	    for (int i = length - 2; i >= 0; i--) {
	        if (m.get(s.charAt(i + 1)) <= m.get(s.charAt(i))) {
	            result += m.get(s.charAt(i));
	        } else {
	            result -= m.get(s.charAt(i));
	        }
	    }
	    return result;
	}
}
