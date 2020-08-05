public class MinWindow {
    public String minWindow(String s, String t) {
        char[] schar = s.toCharArray();
        char[] tchar = t.toCharArray();

        if (t.length() > s.length() || t.isEmpty()) {
            return "";
        }

        if (s.contains(t)) {
            return t;
        }

        int[] pattern = new int[128];

        for (char c : tchar) {
            pattern[c]++;
        }
        final int numberMatchPattern = tchar.length;
        int start = 0;
        int min = schar.length + 1;
        int minStart = -10;
        int currentMatchpattern = 0;
        for (int end = 0; end < schar.length; end++) {
            if (pattern[schar[end]]-- > 0) {
                currentMatchpattern++;
            }
            if (currentMatchpattern == numberMatchPattern) {
                for (int strt = start; strt <= end; strt++) {
                    if (currentMatchpattern != numberMatchPattern) {
                        start = strt;
                        break;
                    }
                    if (++pattern[schar[strt]] > 0) {
                        currentMatchpattern--;
                    }
                }
                if (end - start + 1 < min) {
                    min = end - start + 1;
                    minStart = start - 1;
                }
            }
        }
        if (minStart >= 0) {
            return s.substring(minStart, minStart + min + 1);
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new MinWindow().minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(new MinWindow().minWindow("a", "b"));
        System.out.println(new MinWindow().minWindow("a", "a"));
        System.out.println(new MinWindow().minWindow("bba", "ab"));
    }
}
