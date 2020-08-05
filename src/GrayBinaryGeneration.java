import java.util.LinkedList;
import java.util.List;

public class GrayBinaryGeneration {

    private int findPositionOfLast1(int a) {
        if (a == 1) {
            return 1;
        }
       return Integer.numberOfTrailingZeros(a) + 1;
    }

    public List<String> generate(int n) {
        int aInf = 0;
        int a = 0;
        List<String> result = new LinkedList<>();
        result.add(String.format("%" + n + "s", Integer.toBinaryString(a)).replace(" ", "0"));
        int j = 0;
        while (j < n) {
            aInf = 1 - aInf;
            if (aInf == 1) {
                j = 0;
            } else {
                j = findPositionOfLast1(a);
            }
            if (j == n) {
                break;
            } else {
                a ^= 1 << j;
            }
            result.add(String.format("%" + (n) + "s", Integer.toBinaryString(a)).replace(" ", "0"));
        }
        return result;
    }

    public List<String> generateLoopless(int n) {
        int a = 0;
        int[] f = new int[n + 1];
        for (int i = 0; i < f.length; i++) {
            f[i] = i;
        }
        f[n] = n;
        List<String> result = new LinkedList<>();
        int j = 0;
        while (j < n) {
            result.add(String.format("%" + n + "s", Integer.toBinaryString(a)).replace(" ", "0"));
            j = f[0];
            f[0] = 0;
            if (j == n) {
                break;
            }
            f[j] = f[j + 1];
            f[j + 1] = j + 1;
            a ^= 1 << j;
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> result =  new GrayBinaryGeneration().generateLoopless(7);
        result.forEach(System.out::println);
    }
}
