package com.atguigu.redis7.mytest.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * @program: test
 * @description:
 * @author: dx
 * @create: 2023/7/16 15:53
 */
public class HashFunction {

    public static int DJB2Hash(String str) {
        int hash = 5381;
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            hash = ((hash << 5) + hash) + (int) b;
        }
        return hash;
    }

    public static int MurmurHash(String key) {
        return Hashing.murmur3_32().hashString(key, StandardCharsets.UTF_8).asInt();
    }

    public static int FNVHash(String str) {
        final int fnv_prime = 0x811C9DC5;
        int hash = 0;
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        for (byte b : bytes) {
            hash *= fnv_prime;
            hash ^= (int) b;
        }
        return hash;
    }
}
