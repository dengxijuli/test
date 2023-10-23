package com.ttsx.day1;

/**
 * 1056. 易混淆数
 */


public class sd20230818 {

    public boolean confusingNumber(int n) {
        int[] nums = {2, 3, 4, 5, 7,6,9};
        int[] nums1 = {6,9};
        String sn = String.valueOf(n);
        for (int i : nums) {
            if (sn.contains(String.valueOf(i))) {
                return false;
            }
        }
        for (int i : nums1) {
            if (sn.contains(String.valueOf(i))){
                return true;
            }
        }
        return false;


    }
}
