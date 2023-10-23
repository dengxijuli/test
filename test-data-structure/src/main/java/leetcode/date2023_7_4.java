package leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @program: test
 * @description:
 * @author: dx
 * @create: 2023/7/4 15:56
 */
public class date2023_7_4 {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1 + i; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1};
    }

    public static void main(String[] args) {
        date2023_7_4 date2023_7_4 = new date2023_7_4();
        int[] nums = {2, 7, 11, 15};
        System.out.println(Arrays.toString(date2023_7_4.twoSum(nums, 9)));
    }
}
