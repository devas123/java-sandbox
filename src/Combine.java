import java.util.*;

public class Combine {
    public List<List<Integer>> combine(int n, int t) {
        // init first combination
        if (t > n) {
            return Collections.emptyList();
        }
        Integer[] c = new Integer[t + 2];
        for (int i = 1; i <= t; ++i)
            c[i - 1] = i;
        c[t] = n + 1;
        c[t + 1] = 0;

        List<List<Integer>> output = new LinkedList<>();
        int j = t - 1;
        int x = -1;
        boolean easy;
        while (j < t) {
            output.add(Arrays.asList(Arrays.copyOfRange(c, 0, t)));
            if (j >= 0) {
                x = j + 1;
            }
            if (c[0] + 1 < c[1]) {
                c[0] += 1;
                easy = true;
            } else {
                easy = false;
                j = 1;
                while (true) {
                    c[j - 1] = j;
                    x = c[j] + 1;
                    if (x != c[j + 1]) {
                        break;
                    }
                    j++;
                }
            }
            if (j >= t) {
                break;
            }
            if (!easy) {
                c[j] = x;
                j -= 1;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        new Combine().combine(2, 2).forEach(System.out::println);
    }
}
