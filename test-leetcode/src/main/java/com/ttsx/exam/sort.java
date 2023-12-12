package com.ttsx.exam;

import java.util.*;

public class sort {
    public static void main(String[] args) {
        String[] words = {"apper","pear","banana","apple"};
        HashMap<String,Integer> wordCountMap = new HashMap<>();

        for(String word : words){
            if(wordCountMap.containsKey(word)){
                int count = wordCountMap.get(word);
                wordCountMap.put(word,count + 1);
            }else{
                wordCountMap.put(word,1);
            }

            for(String w : wordCountMap.keySet()){
                int count = wordCountMap.get(w);
                System.out.println(w + ":"+count);
            }
        }
    }


}
