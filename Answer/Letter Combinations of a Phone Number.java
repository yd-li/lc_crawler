/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    public ArrayList<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();

        if (digits == null || digits.equals("")) {
            return result;
        }

        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put(&#39;0&#39;, new char[] {});
        map.put(&#39;1&#39;, new char[] {});
        map.put(&#39;2&#39;, new char[] { &#39;a&#39;, &#39;b&#39;, &#39;c&#39; });
        map.put(&#39;3&#39;, new char[] { &#39;d&#39;, &#39;e&#39;, &#39;f&#39; });
        map.put(&#39;4&#39;, new char[] { &#39;g&#39;, &#39;h&#39;, &#39;i&#39; });
        map.put(&#39;5&#39;, new char[] { &#39;j&#39;, &#39;k&#39;, &#39;l&#39; });
        map.put(&#39;6&#39;, new char[] { &#39;m&#39;, &#39;n&#39;, &#39;o&#39; });
        map.put(&#39;7&#39;, new char[] { &#39;p&#39;, &#39;q&#39;, &#39;r&#39;, &#39;s&#39; });
        map.put(&#39;8&#39;, new char[] { &#39;t&#39;, &#39;u&#39;, &#39;v&#39;});
        map.put(&#39;9&#39;, new char[] { &#39;w&#39;, &#39;x&#39;, &#39;y&#39;, &#39;z&#39; });

        StringBuilder sb = new StringBuilder();
        helper(map, digits, sb, result);

        return result;
    }

    private void helper(Map<Character, char[]> map, String digits, 
        StringBuilder sb, ArrayList<String> result) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : map.get(digits.charAt(sb.length()))) {
            sb.append(c);
            helper(map, digits, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}