public class IsMatch {
    /**
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * The matching should cover the entire input string (not partial).
     *
     * Note:
     * s could be empty and contains only lowercase letters a-z.
     * p could be empty and contains only lowercase letters a-z, and characters like . or *.
     */
    public boolean isMatch(String s, String p) {
        char[] schar = s.toCharArray();
        char[] pchar = p.toCharArray();
        boolean[][] dp = new boolean[schar.length + 1][pchar.length + 1];
        dp[schar.length][pchar.length] = true;
        for (int i = schar.length; i >= 0; i--) {
            for (int j = pchar.length - 1; j >= 0; j--) {
                boolean currentMatch = i < schar.length && (schar[i] == pchar[j] || pchar[j] == '.');
                if (j + 1 < pchar.length && pchar[j + 1] == '*') {
                    dp[i][j] = dp[i][j + 2] || currentMatch && dp[i + 1][j];
                } else {
                    dp[i][j] = currentMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {

    }
}
