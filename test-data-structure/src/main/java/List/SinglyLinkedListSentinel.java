package List;

import java.security.SecureRandom;
import java.util.*;

/**
 * @program: test
 * @description: 单向链表，带有哨兵
 * @author: dx
 * @create: 2023/7/5 14:14
 */
public class SinglyLinkedListSentinel {

    private Node head = new Node(Integer.MIN_VALUE, null);

    public static class Node {  //节点类
        int value;
        SinglyLinkedList.Node next;

        public Node(int value, SinglyLinkedList.Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new SinglyLinkedListSentinel().grayCode(2);
        System.out.println(Arrays.toString(list.toArray()));

    }

    public List<Integer> grayCode(int n) {
        if (n <= 0) {
            return null;
        }
        int size = (int) Math.pow(2, n); // 计算集合大小
        Set<Integer> set = new HashSet<>(); // 创建Set集合
        List<Integer> list;
        SecureRandom random = new SecureRandom(); // 创建SecureRandom类
        random.setSeed(System.currentTimeMillis()); // 使用当前时间作为种子
        int target = 1;
        do {
            // 生成随机数，并添加到Set集合中
            while (set.size() < size) {
                int num = random.nextInt(size);
                if (!set.contains(num)) {
                    set.add(num);
                }
            }
            list = new ArrayList<>(set); // 将Set集合转换为List集合
            if (list.get(0) == 0 && list.get(size - 1) % 2 != 0) {
                for (int i = 1; i < size - 1; i++) {
                    int num = (i - 1) ^ i;
                    int pow = 1;
                    while (pow < num) {
                        pow *= 2; // 不断进行2的指数运算
                    }
                    if (pow != num) {
                        target = 0;
                    } else {
                        target = 1;
                    }
                }
            } else {
                target = 0;
            }

        } while (target == 0);
        return list;
    }


}
