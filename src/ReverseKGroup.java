import java.util.Stack;

public class ReverseKGroup {
    public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode iter = head;
        ListNode result = null;
        ListNode kTail = null;
        while (iter != null) {
            int count = 0;
            while (count < k && iter != null) {
                iter = iter.next;
                count++;
            }
            if (count == k) {
                ListNode kHead = reverseList(kTail, k);
                if (result == null) {
                    result = kHead;
                }
                if (kTail != null) {
                    kTail.next = kHead;
                }
                kTail = head;
                head = iter;
            }
        }
        return result;
    }

    public ListNode reverseList(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode revHead = null;
        ListNode iter = head;
        while(k > 0) {
            ListNode tmp = iter.next;
            iter.next = revHead;
            revHead = iter;
            iter = tmp;
            k--;
        }
        return revHead;
    }
}
