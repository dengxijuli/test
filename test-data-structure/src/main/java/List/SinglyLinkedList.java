package List;

/**
 * @program: test
 * @description: 单向链表（无哨兵）
 * @author: dx
 * @create: 2023/7/4 16:21
 */
public class SinglyLinkedList {
    private Node head;  //头部节点

    public static class Node {  //节点类
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    /**
     * 头部添加
     */
    public void addFirst(int value) {
        this.head = new Node(value, this.head);
    }

    /**
     * 尾部添加 , 尾部添加多个
     */
    public void addLast(int value) {
        Node last = findLast();
        if (last == null) {
            addFirst(value);
            return;
        }
        last.next = new Node(value, null);
    }

    public void addLast(int first, int... rest) {
        Node subLast = new Node(first, null);
        Node curr = subLast;
        for (int value : rest) {
            curr.next = new Node(value, null);
            curr = curr.next;
        }
        Node last = findLast();
        if (last == null) {
            this.head = subLast;
            return;
        }
        last.next = subLast;
    }

    private Node findLast() {
        Node curr = this.head;
        if (curr == null) {
            return null;
        }
        while (curr != null) {
            curr = curr.next;
        }
        return curr;
    }

    /**
     * while遍历
     */
    public void loop() {
        Node curr = this.head;

        while (curr != null) {
            int value = curr.value;
            System.out.println(value);
            curr = curr.next;
        }
    }

    /**
     * 根据索引获取节点的值,或节点
     */

    private Node findNode(int index) {
        int i = 0;
        for (Node curr = this.head; curr != null; curr = curr.next, i++) {
            if (index == i) {
                return curr;
            }
        }
        return null;
    }

    private IllegalArgumentException illegalIndex(int index) {
        return new IllegalArgumentException(String.format("index [%d] 不合法%n", index));
    }

    public Integer getValue(int index) {
        int target = 0;
        Node node = findNode(index);
        if (node != null) {
            return node.value;
        }
        throw illegalIndex(index);
    }

    /**
     * 插入
     */
    public void insert(int index, int value) {
        if (index == 0) {
            addFirst(value);
            return;
        }
        Node prev = findNode(index - 1); // 找到上一个节点
        if (prev == null) { // 找不到
            throw illegalIndex(index);
        }
        prev.next = new Node(value, prev.next);
    }

    /**
     * 删除
     */
    public void remove(int index) {
        if (index == 0) {
            if (this.head != null) {
                this.head = this.head.next;
                return;
            } else {
                throw illegalIndex(index);
            }
        }
        Node prev = findNode(index - 1);
        Node curr;
        if (prev != null && (curr = prev.next) != null) {
            prev.next = curr.next;
        } else {
            throw illegalIndex(index);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = 0;
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[index];
            index++;
        }
        int temp = 0;
        for (int i = 0; i < m + n; i++) {
            for (int j = i + 1; j < m + n; j++) {
                if (nums1[i] > nums1[j]) {
                    temp = nums1[i];
                    nums1[i] = nums1[j];
                    nums1[j] = temp;
                }
            }
        }
    }
}
