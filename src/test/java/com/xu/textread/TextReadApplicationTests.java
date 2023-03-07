package com.xu.textread;

import com.xu.textread.mapper.FriendsMapper;
import com.xu.textread.model.domain.Text;
import com.xu.textread.model.vo.FriendVo;
import com.xu.textread.model.vo.TextVo;
import com.xu.textread.utils.BeanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class TextReadApplicationTests {

    public static final String HEAD_PASSWORD = "tr.sql";

    public static final String FOOT_PASSWORD = "byXYC";

    public static final String USER_LOGIN_DATA = "userData";

    @Resource
    private FriendsMapper friendsMapper;

    @Test
    void contextLoads() {
//        String newPassword = DigestUtils.md5DigestAsHex((HEAD_PASSWORD + 12345678 + FOOT_PASSWORD).getBytes());
//        System.out.println(newPassword);

        List<FriendVo> friendVos = friendsMapper.selectFriendVoList(1);
        System.out.println(friendVos);
    }

    @Test
    void copy(){
        TextVo textVo = new TextVo();
        textVo.setTextId(0L);
        textVo.setTextAuthorId(0L);
        textVo.setTextTitle("1");
        textVo.setTextAuthorName("1");
        textVo.setTextIntroduce("1");
        textVo.setShowImgUrl("1");

        Text text = BeanUtil.copyProperties(textVo, new Text());
        System.out.println(text);
    }

    @Test
    void a(){
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = new int[]{2,5,6};
        int n = 3;
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        System.arraycopy(nums2,0,nums1,0,n);
        Arrays.sort(nums1);
    }


    public static void main(String[] args) {
        int i = climbStairs(10);
    }

    public static int climbStairs(int n) {
        int[] oth = new int[n + 1];
        return swap(n,oth);
    }

    static int  swap(int n,int[] oth){
        if(oth[n] > 0){

            return oth[n];
        }
        if(n == 1){
            return oth[1] = 1;
        } else if(n == 2){
            return oth[2] = 2;
        }else{
            oth[n] = swap(n - 1,oth) + swap(n - 2,oth);
        }

        return oth[n];
    }

}
