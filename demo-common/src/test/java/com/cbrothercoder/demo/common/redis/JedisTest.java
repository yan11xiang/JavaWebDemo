package com.cbrothercoder.demo.common.redis;

import com.cbrothercoder.demo.common.test.SpringTestContextLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author 小C
 * @since 2018-03-08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringTestContextLoader.class)
public class JedisTest {

    private JedisPool jedisPool;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Test
    public void testPool() {
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.toString());
//        Jedis jedis = new Jedis("127.0.0.1", 1234);
        System.out.println("success");
        String key = "key";
        jedis.set(key, "1232");
        System.out.println(jedis.get(key));
        System.out.println("end");
    }

}
