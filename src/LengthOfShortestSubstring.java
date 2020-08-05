public class LengthOfShortestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        char[] sc = s.toCharArray();
        int length = 1;
        int startIndex = 0;
        for (int i = 1; i < sc.length; i++) {
            for (int j = startIndex; j < i; j++) {
                if (sc[i] == sc[j]) {
                    startIndex = j + 1;
                    break;
                }
            }
            int tmp = i - startIndex + 1;
            length = Math.max(tmp, length);
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfShortestSubstring().lengthOfLongestSubstring("pwwkew"));
        System.out.println(new LengthOfShortestSubstring().lengthOfLongestSubstring("a"));
        System.out.println(new LengthOfShortestSubstring().lengthOfLongestSubstring("au"));
        System.out.println(new LengthOfShortestSubstring().lengthOfLongestSubstring("dvdf"));
    }
}
