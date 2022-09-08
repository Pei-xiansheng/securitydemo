package com.zap;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zap.dao.UserMapper;
import com.zap.entity.Person;
import com.zap.entity.queue.SeqQueue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: Apeng
 * @version: 1.0
 * @date: 2022/5/26 14:09
 */

@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void test(){

        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Person::getUsername,"张三");
        Person person = userMapper.selectOne(queryWrapper);
        System.out.println(person);

    }

    @Test
    public void getPassWord(){
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
    @Test
    public void bubble(){
        int[] arr = new int[]{1,55,20,3,99,56,82,12};


        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        Arrays.stream(arr).forEach(item->{
            System.out.println(item);
        });
    }

    @Test
    public void testHashMap(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("张三",1);
        map.put("李四",5);
        map.put("王五",3);
        sortMap(map);
    }

    private void sortMap(HashMap<String, Integer> map) {
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue()-o2.getValue();
            }
        });
        for (Map.Entry<String,Integer> m:list
             ) {
            System.out.println(m.getKey());
        }
    }

    @Test
    public void testQueue(){
        ArrayList<String> list = new ArrayList<String>() {{
            add("A");
            add("B");
            add("C");
            add("D");
            add("E");
            add("F");
            add("G");
        }};
        SeqQueue<String> queue = new SeqQueue<>(list.size() * 2, String.class);

        for (int i = 0; i < list.size()*2; i++) {
            if(i<list.size()){
                queue.add(list.get(i));
            }else{
                queue.add(list.get(list.size()*2-1-i));
            }
            log.info("队列中的元素--->{}",queue.poll());
        }

    }


}
