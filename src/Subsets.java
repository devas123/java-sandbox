import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        subsets(nums, 0, ret, new ArrayList<>());
        return ret;
    }

    private void subsets(int[] nums, int level, List<List<Integer>> ret, List<Integer> path) {
        if (level == nums.length) {
            ret.add(new ArrayList<>(path));
            return;
        }
        subsets(nums, level + 1, ret, path);
        path.add(nums[level]);
        subsets(nums, level + 1, ret, path);
        path.remove(path.size() - 1);
    }
    public List<List<Integer>> subsetsNoLoop(int[] nums) {
        int n = nums.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < f.length; i++) {
            f[i] = i;
        }
        int[] a = new int[n];
        f[n] = n;
        List<List<Integer>> result = new LinkedList<>();
        int j = 0;
        ArrayList<Integer> p = new ArrayList<>(n);
        while (j < n) {
            if (a[j] == 0) {
                int finalJ = j;
                p.removeIf(i -> i == nums[finalJ]);
            } else {
                p.add(nums[j]);
            }
            result.add(p);
            p = new ArrayList<>(p);
            j = f[0];
            f[0] = 0;
            if (j == n) {
                break;
            }
            f[j] = f[j + 1];
            f[j + 1] = j + 1;
            a[j] = 1 - a[j];
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> ls = new Subsets().subsets(new int[]{1, 2, 3});
        ls.forEach(System.out::println);
    }
}
