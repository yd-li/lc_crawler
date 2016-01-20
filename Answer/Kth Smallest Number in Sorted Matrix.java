/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
     
    public class Point {
        public int x, y, val;
        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    } 
    
    Comparator<Point> comp = new Comparator<Point>() {
        public int compare(Point left, Point right) {
            return left.val - right.val;
        }
    };
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        if (k > matrix.length * matrix[0].length) {
            return 0;
        }
        return horizontal(matrix, k);
    }
    
    private int horizontal(int[][] matrix, int k) {
        Queue<Point> heap = new PriorityQueue<Point>(k, comp);
        for (int i = 0; i < Math.min(matrix.length, k); i++) {
            heap.offer(new Point(i, 0, matrix[i][0]));
        }
        for (int i = 0; i < k - 1; i++) {
            Point curr = heap.poll();
            if (curr.y + 1 < matrix[0].length) {
                heap.offer(new Point(curr.x, curr.y + 1, matrix[curr.x][curr.y + 1]));
            }
        }
        return heap.peek().val;
    }
    
    private int vertical(int[][] matrix, int k) {
        Queue<Point> heap = new PriorityQueue<Point>(k, comp);
        for (int i = 0; i < Math.min(matrix[0].length, k); i++) {
            heap.offer(new Point(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Point curr = heap.poll();
            if (curr.x + 1 < matrix.length) {
                heap.offer(new Point(curr.x + 1, curr.y, matrix[curr.x + 1][curr.y]));
            }
        }
        return heap.peek().val;
    }
}
