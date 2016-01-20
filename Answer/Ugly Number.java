/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

class Solution {
    public long kthPrimeNumber(int n) {        
        Queue<Long> Q = new PriorityQueue<Long>();
        HashMap<Long, Boolean> inQ = new HashMap<Long, Boolean>();
        Long[] primes = new Long[3];
        primes[0] = Long.valueOf(3);
        primes[1] = Long.valueOf(5);
        primes[2] = Long.valueOf(7);
        for (int i = 0; i < 3; i++) {
            Q.add(primes[i]);
            inQ.put(primes[i], true);
        }
        Long number = Long.valueOf(0);
        for (int i = 0; i < n; i++) {
            number = Q.poll();
            for (int j = 0; j < 3; j++) {
                if (!inQ.containsKey(primes[j] * number)) {
                    Q.add(number * primes[j]);
                    inQ.put(number * primes[j], true);
                }
            }
        }
        return number;
    }
}