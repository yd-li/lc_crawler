/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    private int[] father;
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it&#39;s a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // tree should have n nodes with n-1 edges
        if (n - 1 != edges.length) {
            return false;
        }
        
        // union-find is a data structure that can union two sets and check 
        // whether two element in the same set.
        // search it on google for more information
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
        
        for (int i = 0; i < edges.length; i++) {
            if (find(edges[i][0]) == find(edges[i][1])) {
                return false;
            }
            union(edges[i][0], edges[i][1]);
        }
        
        return true;
    }
    
    int find(int node) {
        if (father[node] == node) {
            return node;
        }
        father[node] = find(father[node]);
        return father[node];
    }
    
    void union(int node1, int node2) {
        father[find(node1)] = find(node2);
    }
}
