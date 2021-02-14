package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void string() {
        redisTemplate.opsForValue().set("num", 123);
        redisTemplate.opsForValue().set("string", "some strings");
        Object s = redisTemplate.opsForValue().get("num");
        Object s2 = redisTemplate.opsForValue().get("string");
        System.out.println(s);
        System.out.println(s2);
    }

    @Test
    public void string2(){
        //设置的是3s失效，3s之内查询有结果，3s之后查询则返回null
        redisTemplate.opsForValue().set("num","123XYZ",5, TimeUnit.SECONDS);
        try{
            final Object num = redisTemplate.opsForValue().get("num");
            System.out.println("====="+num);
            Thread.currentThread().sleep(2000);
            final Object num1 = redisTemplate.opsForValue().get("num");
            System.out.println("两秒后："+num1);
            Thread.currentThread().sleep(3000);
            final Object num2 = redisTemplate.opsForValue().get("num");
            System.out.println("五秒后："+num2);
        }catch(Exception e){
            log.error("redis失效拿不到结果",e);
        }
    }
    @Test
    public void string3(){
        //重写给定key所存储的字符串值，从偏移量offset开始
        redisTemplate.opsForValue().set("key","hello world");
        redisTemplate.opsForValue().set("key","redis", 6);
        System.out.println(redisTemplate.opsForValue().get("key"));
    }

    @Test
    public void string4(){
        //设置键的字符串值并返回其旧值
        redisTemplate.opsForValue().set("getSetTest","test");
        System.out.println(redisTemplate.opsForValue().getAndSet("getSetTest","test2"));
        System.out.println(redisTemplate.opsForValue().get("getSetTest"));
    }

    @Test
    public void test5(){
        redisTemplate.opsForValue().append("k","test");
        System.out.println(redisTemplate.opsForValue().get("k"));
        redisTemplate.opsForValue().append("k","test2");
        System.out.println(redisTemplate.opsForValue().get("k"));
    }

    @Test
    public void string6(){
        redisTemplate.opsForValue().set("key","1");
        System.out.println(redisTemplate.opsForValue().size("key"));
    }

    @Test
    public void hash1(){
        HashMap<String,Object> testMap = new HashMap();
        testMap.put("name","zhonghua");
        testMap.put("sex","male");
        redisTemplate.opsForHash().putAll("Hash",testMap);
        System.out.println(redisTemplate.opsForHash().entries("Hash"));
    }

    @Test
    public void hash2(){
        redisTemplate.opsForHash().put("redisHash","name","mengyang");
        redisTemplate.opsForHash().put("redisHash","sex","male");
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
    }

    @Test
    public void hash3(){
        redisTemplate.opsForHash().put("redisHash","name","mengyang");
        redisTemplate.opsForHash().put("redisHash","sex","male");
        System.out.println(redisTemplate.opsForHash().values("redisHash"));
    }

    @Test
    public void hash4(){
        redisTemplate.opsForHash().put("redisHash","name","mengyang");
        redisTemplate.opsForHash().put("redisHash","sex","male");
        System.out.println(redisTemplate.opsForHash().delete("redisHash","name"));
        System.out.println(redisTemplate.opsForHash().entries("redisHash"));
    }

    @Test
    public void hash5() {
        redisTemplate.opsForHash().put("redisHash","name","mengyang");
        redisTemplate.opsForHash().put("redisHash","sex","male");
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash","name"));
        System.out.println(redisTemplate.opsForHash().hasKey("redisHash","age"));
    }

    @Test
    public void hash6(){
        redisTemplate.opsForHash().put("redisHash","name","mengyang");
        redisTemplate.opsForHash().put("redisHash","sex","male");
        System.out.println(redisTemplate.opsForHash().get("redisHash","name"));
    }

    @Test
    public void hash7(){
        redisTemplate.opsForHash().put("redisHash","name","mengyang");
        redisTemplate.opsForHash().put("redisHash","sex","male");
        System.out.println(redisTemplate.opsForHash().keys("redisHash"));
    }

    @Test
    public void hash8(){
        redisTemplate.opsForHash().put("redisHash","name","mengyang");
        redisTemplate.opsForHash().put("redisHash","sex","male");
        System.out.println(redisTemplate.opsForHash().size("redisHash"));
    }

    @Test
    public void list1(){
        String[] strings = new String[]{"1","2","3"};
        redisTemplate.opsForList().leftPushAll("list",strings);
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
    }

    @Test
    public void list2(){
        String[] strings = new String[]{"1","2","3"};
        redisTemplate.opsForList().leftPushAll("list",strings);
        System.out.println(redisTemplate.opsForList().size("list"));
    }

    @Test
    public void list3(){
        redisTemplate.opsForList().leftPush("list","1");
        System.out.println(redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().leftPush("list","2");
        System.out.println(redisTemplate.opsForList().size("list"));
        redisTemplate.opsForList().leftPush("list","3");
        System.out.println(redisTemplate.opsForList().size("list"));
    }

    @Test
    public void list4(){
        redisTemplate.opsForList().rightPush("listRight","1");
        System.out.println(redisTemplate.opsForList().size("listRight"));
        redisTemplate.opsForList().rightPush("listRight","2");
        System.out.println(redisTemplate.opsForList().size("listRight"));
        redisTemplate.opsForList().rightPush("listRight","3");
        System.out.println(redisTemplate.opsForList().size("listRight"));
    }

    @Test
    public void list5(){
        String[] strings  = new String[]{"1","2","3"};
        redisTemplate.opsForList().rightPushAll("list",strings);
        System.out.println(redisTemplate.opsForList().range("list",0,-1));
    }

    @Test
    public void list6(){
        String[] strings  = new String[]{"1","2","3"};
        redisTemplate.opsForList().rightPushAll("list6",strings);
        System.out.println(redisTemplate.opsForList().range("list6",0,-1));
        redisTemplate.opsForList().set("list6",1,"值");
        System.out.println(redisTemplate.opsForList().range("list6",0,-1));
    }

    @Test
    public void list7(){
        String[] strings  = new String[]{"1","2","3"};
        redisTemplate.opsForList().rightPushAll("list7",strings);
        System.out.println(redisTemplate.opsForList().range("list7",0,-1));
        redisTemplate.opsForList().remove("list7",1,"2");
        System.out.println(redisTemplate.opsForList().range("list7",0,-1));
    }

    @Test
    public void list8(){
        String[] strings  = new String[]{"1","2","3"};
        redisTemplate.opsForList().rightPushAll("list8",strings);
        System.out.println(redisTemplate.opsForList().range("list8",0,-1));
        System.out.println(redisTemplate.opsForList().index("list8",2));
    }

    @Test
    public void list9(){
        String[] strings  = new String[]{"1","2","3"};
        redisTemplate.opsForList().rightPushAll("list9",strings);
        System.out.println(redisTemplate.opsForList().range("list9",0,-1));
        System.out.println(redisTemplate.opsForList().leftPop("list9"));
        System.out.println(redisTemplate.opsForList().range("list9",0,-1));
    }

    @Test
    public void list10(){
        String[] strings  = new String[]{"1","2","3"};
        redisTemplate.opsForList().rightPushAll("list10",strings);
        System.out.println(redisTemplate.opsForList().range("list10",0,-1));
        System.out.println(redisTemplate.opsForList().rightPop("list10"));
        System.out.println(redisTemplate.opsForList().range("list10",0,-1));
    }

    @Test
    public void set1(){
        String[] strs = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("set1",strs));
        System.out.println(redisTemplate.opsForSet().add("set1","1","2","3"));
    }

    @Test
    public void set2(){
        String[] strs = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("set2",strs));
        System.out.println(redisTemplate.opsForSet().remove("set2",strs));
    }

    @Test
    public void set3(){
        String[] strs = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("set3",strs));
        System.out.println(redisTemplate.opsForSet().pop("set3"));
        System.out.println(redisTemplate.opsForSet().members("set3"));
    }

    @Test
    public void set4(){
        String[] strs = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("set4",strs));
        redisTemplate.opsForSet().move("set4","str2","set4to2");
        System.out.println(redisTemplate.opsForSet().members("set4"));
        System.out.println(redisTemplate.opsForSet().members("set4to2"));
    }

    @Test
    public void set5(){
        String[] strs = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("set5",strs));
        System.out.println(redisTemplate.opsForSet().size("set5"));
    }

    @Test
    public void set6(){
        String[] strs = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("set6",strs));
        System.out.println(redisTemplate.opsForSet().members("set6"));
    }

    @Test
    public void set7(){
        String[] strs = new String[]{"str1","str2"};
        System.out.println(redisTemplate.opsForSet().add("set7",strs));
        Cursor<Object> cursor = redisTemplate.opsForSet().scan("set7", ScanOptions.NONE);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

    @Test
    public void zset1(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1", 9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2", 9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
    }

    @Test
    public void zset2(){
        System.out.println(redisTemplate.opsForZSet().add("zset2","zset-1",1.0));
        System.out.println(redisTemplate.opsForZSet().add("zset2","zset-1",1.0));
    }

    @Test
    public void zset3(){
        System.out.println(redisTemplate.opsForZSet().add("zset3","zset-1",3.0));
        System.out.println(redisTemplate.opsForZSet().add("zset3","zset-2",2.0));
        System.out.println(redisTemplate.opsForZSet().range("zset3",0,-1));
        System.out.println(redisTemplate.opsForZSet().remove("zset3","zset-2"));
        System.out.println(redisTemplate.opsForZSet().range("zset3",0,-1));
    }

    @Test
    public void zset4(){
        System.out.println(redisTemplate.opsForZSet().add("zset4","zset-1",2.0));
        System.out.println(redisTemplate.opsForZSet().add("zset4","zset-2",1.0));
        System.out.println(redisTemplate.opsForZSet().range("zset4",0,-1));
        System.out.println(redisTemplate.opsForZSet().rank("zset4","zset-1"));
    }

    @Test
    public void zset5(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2",9.1);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        System.out.println(redisTemplate.opsForZSet().add("zset5",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset5",0,-1));
    }

    @Test
    public void zset6(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1",8.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2",7.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3",5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset6",tuples));
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset6",0,9));
        System.out.println(redisTemplate.opsForZSet().count("zset6",0,6));
    }

    @Test
    public void zset7(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1",8.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2",7.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3",5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset7",tuples));
        System.out.println(redisTemplate.opsForZSet().size("zset7"));
    }

    @Test
    public void zset8(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1",8.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2",7.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3",5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset8",tuples));
        System.out.println(redisTemplate.opsForZSet().score("zset8","zset-3"));
    }

    @Test
    public void zset9(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1",8.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2",7.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3",5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset9",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset9",0,-1));
        System.out.println(redisTemplate.opsForZSet().removeRange("zset9",1,2));
        System.out.println(redisTemplate.opsForZSet().range("zset9",0,-1));
    }

    @Test
    public void zset10(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1",8.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2",7.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3",5.7);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        System.out.println(redisTemplate.opsForZSet().add("zset10",tuples));
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("zset10", ScanOptions.NONE);
        while(cursor.hasNext()){
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue()+":"+item.getScore());
        }
    }
}
