package com.cbrothercoder.demo.common.redis;

import com.cbrothercoder.demo.common.test.SpringTestContextLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try {
            System.out.println("success get jedis");
            System.out.println("\njedis.toString:\t\t\t\t\t" + jedis.toString());
//        Jedis jedis = new Jedis("127.0.0.1", 1234);
            String key = "key";
            jedis.del(key);
            jedis.set(key, "1232");
            System.out.println("\ntest key & value:\t\t\t\t" + jedis.get(key));

            //list 测试
            System.out.println("\n==========================================\t\ttest list start\t\t==========================================");
            String listKey = "list";
            jedis.del(listKey);
            jedis.rpush(listKey, "1");
            jedis.rpush(listKey, "2");
            List<String> list = jedis.sort(listKey);
//            list.forEach(System.out::println);
            list.forEach(s -> System.out.println("listValues:" + s));
            System.out.println("==========================================\t\ttest list end\t\t==========================================");

            //Map测试
            System.out.println("\n==========================================\t\ttest map start\t\t==========================================");
            Map<String, String> mapTest = new HashMap<>();
            mapTest.put("a", "1");
            mapTest.put("b", "2");
            String mapKey = "mapKey";
            jedis.hmset(mapKey, mapTest);

            System.out.println(String.format("mapKeys: %s", jedis.hkeys(mapKey)));
            System.out.println(String.format("mapValues: %s", jedis.hvals(mapKey)));
            Map<String, String> stringStringMap = jedis.hgetAll(mapKey);
            stringStringMap.forEach((key1, value) -> System.out.println("key:" + key1 + ",\tvalue:" + value));
            System.out.println("==========================================\t\ttest map end\t\t==========================================");


        } catch (Exception e) {
            System.out.println("==========================================\t\tJedisTest testPool 异常\t\t==========================================");
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }

}
