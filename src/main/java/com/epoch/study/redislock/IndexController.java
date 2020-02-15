package com.epoch.study.redislock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @description: redis分布式锁
 * @author: zjbing
 * @create: 2020-02-12 18:54
 **/
@RestController
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    /**
     * 单体应用保证原子操作
     *
     * @return
     */
    @GetMapping(value = "/jiankucundt")
    public Object jiankucundt() {

        synchronized (this) {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realstock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realstock + "");
                System.out.println("减库存成功，剩余库存\t" + realstock);
            } else {
                System.out.println("减库存失败，库存不足");
            }
        }
        return "end";
    }

    /**
     * 普通版实现多点分布式锁，当A线程的执行时间大于失效时间时会出现问题
     *
     * @return
     */
    @GetMapping(value = "/jiankucun")
    public Object jiankucun() {
        String lockkey = "lockKey";

        String clientId = UUID.randomUUID().toString();
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockkey, clientId, 20, TimeUnit.SECONDS);

        if (!result) {
            return "error";
        }

        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realstock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realstock + "");
                System.out.println("减库存成功，剩余库存\t" + realstock);
            } else {
                System.out.println("减库存失败，库存不足");
            }
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockkey))) {
                stringRedisTemplate.delete(lockkey);
            }
        }
        return "end";
    }

    /**
     * redisson版，大厂实现版本
     *
     * @return
     */
    @GetMapping(value = "/jiankucunByRedissson")
    public Object jiankucunByRedissson() {
        String lockkey = "lockKey";
        RLock lock = redisson.getLock(lockkey);

        try {
            lock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realstock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realstock + "");
                System.out.println("减库存成功，剩余库存\t" + realstock);
            } else {
                System.out.println("减库存失败，库存不足");
            }
        } finally {
            lock.unlock();
        }
        return "end";
    }
}
