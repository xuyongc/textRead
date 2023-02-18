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

}
