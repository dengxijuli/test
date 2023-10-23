package com.ttsx.day2;

import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = new String(charArray);
            List<String> list = map.getOrDefault(sortedStr, new ArrayList<>());
            list.add(str);
            map.put(sortedStr, list);
        }
        return new ArrayList<>(map.values());
    }
     public static boolean compareStrings(String str1, String str2) {
        // 将两个字符串转换为字符数组
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();

        // 对字符数组进行排序
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // 比较排序后的字符数组是否相等
        return Arrays.equals(charArray1, charArray2);
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String s = String.valueOf(charArray);
            if (!map.containsKey(s)){
                ArrayList<String> as = new ArrayList<>();
                as.add(str);
                map.put(s,as);
            }else {

                    map.get(s).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
//        String[] s = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        List<List<String>> lists = new Solution().groupAnagrams2(s);
//        System.out.println(Arrays.toString(lists.toArray()));
        int[] inums = {0,1,0,3,12};
        int[] ints = new Solution().moveZeroes(inums);
        System.out.println(Arrays.toString(ints));
    }


        public int[] moveZeroes(int[] nums) {
            int[] inums = new int[nums.length];
            int flag = nums.length -1;
            for(int i = 0;i<nums.length;i++){
                if(nums[i] == 0){
                    inums[flag] = nums[i];
                    flag--;
                }else{
                    int nf = nums.length - flag-1;
                    inums[i-nf] = nums[i];
                }
            }
            return nums = inums;
        }

}