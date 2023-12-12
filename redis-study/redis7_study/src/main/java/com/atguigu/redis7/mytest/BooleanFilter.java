package com.atguigu.redis7.mytest;

import com.atguigu.redis7.mytest.utils.HashFunction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;

/**
 * @program: test
 * @description:
 * @author: dx
 * @create: 2023/7/16 15:43
 */


@Service
public class BooleanFilter {

    private Integer BooleanFilterSize = 10000;
    @Resource
    private RedisTemplate redisTemplate;


    public Boolean add(String o) {
        int djb2Hash = HashFunction.DJB2Hash(o);
        int index1 = djb2Hash % BooleanFilterSize;
        redisTemplate.opsForValue().setBit("白名单", index1, true);

        int fnvHash = HashFunction.FNVHash(o);
        int index2 = fnvHash % BooleanFilterSize;
        redisTemplate.opsForValue().setBit("白名单", index2, true);

        int murmurHash = HashFunction.MurmurHash(o);
        int index3 = murmurHash % BooleanFilterSize;
        redisTemplate.opsForValue().setBit("白名单", index3, true);

        return true;
    }

    public Boolean check(String o) {

        int djb2Hash = HashFunction.DJB2Hash(o);
        int index1 = djb2Hash % BooleanFilterSize;
        Boolean bit1 = redisTemplate.opsForValue().getBit("白名单", index1);

        int fnvHash = HashFunction.FNVHash(o);
        int index2 = fnvHash % BooleanFilterSize;
        Boolean bit2 = redisTemplate.opsForValue().getBit("白名单", index2);
        int murmurHash = HashFunction.MurmurHash(o);
        int index3 = murmurHash % BooleanFilterSize;
        Boolean bit3 = redisTemplate.opsForValue().getBit("白名单", index3);
        assert bit1 != null;
        assert bit2 != null;
        assert bit3 != null;
        return bit1 && bit2 && bit3;
    }


}
