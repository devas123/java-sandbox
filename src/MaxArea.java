public class MaxArea {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        while (left < right) {
            int tmp = (right - left) * Math.min(height[left], height[right]);
            if (tmp > result) {
                result = tmp;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new MaxArea().maxArea(new int[] { 1,8,6,2,5,4,8,3,7 }));
    }
}
