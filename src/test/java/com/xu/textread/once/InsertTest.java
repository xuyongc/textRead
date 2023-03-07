package com.xu.textread.once;
import java.util.Date;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import com.xu.textread.mapper.UserMapper;
import com.xu.textread.model.domain.User;
import com.xu.textread.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.util.StopWatch;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @Author xyc
 * @CreteDate 2023/2/7 15:26
 **/
@SpringBootTest
public class InsertTest {


    @Resource
    private UserService userService;

    @Resource
    public UserMapper userMapper;
    @Test
    void insertUser(){
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        final int INSERT_NUM = 500000;
        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0;i < 10;i++){
            List<User> userList = new ArrayList<>();
            while (true){
                j++;
                User user = new User();
                user.setNickName("假数据");
                user.setUserAccount("textRead_null");
                user.setAvatarUrl("https://img-home.csdnimg.cn/images/20201124032511.png");
                user.setGender(0);
                user.setUserPassword("12345678");
                user.setPhone("12345678901");
                user.setEmail("123456@qq.com");
                user.setUserStatus(0);

                userList.add(user);
                if (j % 50000 == 1){
                    break;
                }
            }

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                System.out.println("threadName" + Thread.currentThread().getName());
                userService.saveBatch(userList, 10000);
            });

            futureList.add(future);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();

        System.out.println("这是打印出来的" + stopWatch.getTotalTimeMillis());
    }

    @Test
    void insertBatch(){

    }

    @Test
    void insertText(){

    }


    @Test
    void deleteText(){
        long count = userService.count();
//        List<Long> longs = new ArrayList<>();
//        while (count > 2200000) {
//
//            longs.add(count);
//
//            count--;
//            userMapper.deleteOneUser(count);
//        }
//
//        userService.removeBatchByIds(longs,1000);

        int j = 0;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        for (int i = 0;i < 40/0;i++){
            List<Long> longs = new ArrayList<>();
            while (true){
                longs.add(count - j);

                j++;

                if (j%10000  == 0){
                    break;
                }
            }

            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                userService.removeBatchByIds(longs, 10000);
            });

            futureList.add(future);
        }

        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

    }

//    2231276

//    2231170

//    2100341

    @Test
    void selectCount(){
        long count = userService.count();
        System.out.println("这是总数" + count);

        User byId = userService.getById(2231276);
        System.out.println("这是" + count +  "条" + byId);
    }

    @Test
    void i (){
        int [] nums = new int[]{1,1,2};
        HashSet<Integer> n = new HashSet<>();
        for(int i = 0;i < nums.length;i++){
            n.add(nums[i]);
        }
        nums = n.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(nums));
    }

    @Test
    void a(){
        System.out.println(3%10);
    }
    @Test
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> s = new HashSet<>();
        for(int i = 0;i < nums.length;i++){
            if(!s.contains(nums[i])){
                s.add(nums[i]);
            }else{
                return false;
            }
        }

        return true;
    }

    @Test
    void c(){
        int[] al = new int[10];
        int[] ints = Arrays.stream(al).filter(nums -> {

            for (int i = 0; i < 10; i++) {
                if (nums == 1) {
                    return true;
                }
            }
            return false;
        }).toArray();
        ArrayList<Object> objects = new ArrayList<>();
        List<Integer> c = new ArrayList<>();

    }

    @Test
    void b(){
        Optional<User> optionalUser = Optional.of(new User());
        Optional<Long> optionalLong = optionalUser.map(User::getUserId);


    }

}
