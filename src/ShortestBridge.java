import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class ShortestBridge {

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            System.out.println(i);
        }
        System.out.println(new ShortestBridge().shortestBridge(new int[][] {
                {0,1,0},
                {0,0,0},
                {0,0,1}})); // 2
        System.out.println("-");
        System.out.println(new ShortestBridge().shortestBridge(new int[][] {{0,1},{1,0}})); // 1
    }

    public static class SearchNode {
        final int i;
        final int j;
        final int dist;

        @Override
        public String toString() {
            return "SearchNode{" +
                    "i=" + i +
                    ", j=" + j +
                    ", dist=" + dist +
                    '}';
        }

        public SearchNode(int i, int j, int distance) {
            this.i = i;
            this.j = j;
            this.dist = distance;
        }
    }


    Stack<Integer> stack;
    PriorityQueue<SearchNode> priorityQueue;
    boolean[][] visited;

    public void addNeighborsDfs(int n, int i, int j) {
        if (i >= 1) {
            if (!visited[i - 1][j]) {
                stack.push((n * (i - 1) + j));
            }
        }
        if (i < n - 1) {
            if (!visited[i + 1][j]) {
                stack.push(n * (i + 1) + j);
            }
        }
        if (j >= 1) {
            if (!visited[i][j - 1]) {
                stack.push((n * i + j - 1));
            }
        }
        if (j < n - 1) {
            if (!visited[i][j + 1]) {
                stack.push((n * i + j + 1));
            }
        }
    }

    public int shortestBridge(int[][] A) {
        final int n = A.length;
        int answer = 0;
        stack = new Stack<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(sn -> sn.dist));
        visited = new boolean[n][n];
        stack.push(0);
        while (!stack.isEmpty()) {
            int next = stack.pop();
            int i = next / n;
            int j = next % n;
            visited[i][j] = true;
            addNeighborsDfs(n, i, j);
            if (A[i][j] > 0) {
                priorityQueue.add(new SearchNode(i, j, 0));
                break;
            }
        }
        while (!priorityQueue.isEmpty()) {
            SearchNode node = priorityQueue.poll();
            if (node.dist == 0) {
                // we're still on the source island;
                if (node.i < n - 1 && !visited[node.i + 1][node.j]) {
                    visited[node.i + 1][node.j] = true;
                    priorityQueue.add(new SearchNode(node.i + 1, node.j, A[node.i + 1][node.j] == 0 ? 1 : 0));
                }
                if (node.i > 0 && !visited[node.i - 1][node.j]) {
                    visited[node.i - 1][node.j] = true;
                    priorityQueue.add(new SearchNode(node.i - 1, node.j, A[node.i - 1][node.j] == 0 ? 1 : 0));
                }
                if (node.j < n - 1 && !visited[node.i][node.j + 1]) {
                    visited[node.i][node.j + 1] = true;
                    priorityQueue.add(new SearchNode(node.i, node.j + 1, A[node.i][node.j + 1] == 0 ? 1 : 0));
                }
                if (node.j > 0 && !visited[node.i][node.j - 1]) {
                    visited[node.i][node.j - 1] = true;
                    priorityQueue.add(new SearchNode(node.i, node.j - 1, A[node.i][node.j - 1] == 0 ? 1 : 0));
                }
            } else {
                //we're off the 1st island
                if (node.i < n - 1 && !visited[node.i + 1][node.j]) {
                    visited[node.i + 1][node.j] = true;
                    if (A[node.i + 1][node.j] == 0) {
                        priorityQueue.add(new SearchNode(node.i + 1, node.j, node.dist + 1));
                    } else {
                        answer = node.dist;
                        break;
                    }
                }
                if (node.i > 0 && !visited[node.i - 1][node.j]) {
                    visited[node.i - 1][node.j] = true;
                    if (A[node.i - 1][node.j] == 0) {
                        priorityQueue.add(new SearchNode(node.i - 1, node.j, node.dist + 1));
                    } else {
                        answer = node.dist;
                        break;
                    }
                }
                if (node.j < n - 1 && !visited[node.i][node.j + 1]) {
                    visited[node.i][node.j + 1] = true;
                    if (A[node.i][node.j + 1] == 0) {
                        priorityQueue.add(new SearchNode(node.i, node.j + 1, node.dist + 1));
                    } else {
                        answer = node.dist;
                        break;
                    }
                }
                if (node.j > 0 && !visited[node.i][node.j - 1]) {
                    visited[node.i][node.j - 1] = true;
                    if (A[node.i][node.j - 1] == 0) {
                        priorityQueue.add(new SearchNode(node.i, node.j - 1, node.dist + 1));
                    } else {
                        answer = node.dist;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}
