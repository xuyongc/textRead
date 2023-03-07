create DATABASE textRead;

use textRead;
-- auto-generated definition
create table user
(
    userId       bigint auto_increment comment '用户ID'
        primary key,
    nickName     varchar(30)                        null comment '用户昵称',
    userAccount  varchar(256)                       null comment '账户',
    avatarUrl    varchar(1024)                      null comment '头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(256)                       not null comment '密码',
    phone        varchar(256)                       null comment '电话号码',
    email        varchar(256)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0-正常 1-封号',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete     tinyint  default 0                 not null comment '逻辑-1删除 -0存在',
    userRole     int      default 0                 not null comment '0-普通, 1- 作者 2-管理员'
)
    comment '用户表';


insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('韦志泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('陆鹏煊', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('董立诚', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('段伟泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('廖振家', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('万弘文', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('吴健雄', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('贺弘文', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('孙天磊', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('黎博超', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('宋博文', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('苏鑫鹏', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('高昊天', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('徐锦程', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('邵凯瑞', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('严乐驹', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('陈靖琪', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('沈雨泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('程志强', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('张烨霖', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('冯立轩', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('邓胤祥', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('高哲瀚', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('董雨泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('白潇然', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('王擎苍', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('罗志强', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('于智宸', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('黄立果', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('龙建辉', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('夏昊强', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('王伟泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('孟哲瀚', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('廖文', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('何耀杰', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('叶志泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('姚鹏', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('韦昊天', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('熊熠彤', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('程思远', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('戴昊然', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('谭乐驹', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('韩伟泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('薛琪', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('蔡煜祺', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('贾文博', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('夏炫明', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('钱伟泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('叶鹤轩', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('江峻熙', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('于文博', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('钱明杰', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('韩凯瑞', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('廖潇然', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('韩昊然', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('徐煜城', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('于修杰', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('彭金鑫', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('彭瑞霖', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('夏熠彤', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('戴琪', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('郑瑾瑜', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('冯天宇', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('孔懿轩', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('覃鹤轩', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('蒋耀杰', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('孟昊焱', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('蒋涛', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('吕越彬', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('赵志泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('魏靖琪', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('韩烨霖', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('吕鹏煊', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('曾展鹏', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('谢博文', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('段鸿涛', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('金立辉', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('石晋鹏', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('贾驰', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('何伟祺', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('武鹏煊', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('沈鑫磊', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('江鹏煊', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('严明辉', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('徐晓博', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('孙鑫磊', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('唐聪健', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('韩致远', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('刘峻熙', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('江明哲', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('姜健柏', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('陶鹏涛', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('龚胤祥', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('徐振家', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('冯伟泽', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('钟明轩', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('武振家', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('谭梓晨', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('黎鹏飞', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');
insert into `user` (`nickName`, `avatarUrl`, `userPassword`) values ('郝博超', '/upload/20230223_22472577.png', '0c2230e4e1fc10dd27d050a0b95850c4');

-- auto-generated definition

drop table if exists text;

create table text
(
    textId             bigint auto_increment comment '文章Id'
        primary key,
    textTitle          varchar(256)                       null comment '文章标题',
    textAuthorId       bigint                             null comment '作者Id',
    showImgUrl         varchar(256)                       null comment '封面地址',
    userText           longText                           not null comment '文章内容',
    textHtml           longText                           not null comment '文章内容html',
    textIntroduce      varchar(256)                       not null comment '文章介绍html',
    textLikeNumber     bigint   default 0                 null comment '喜欢数',
    textFavoriteNumber bigint   default 0                 null comment '收藏数量',
    textStatus         int      default 1                 not null comment '状态 0-正常 1-审核中 2-下架',
    createTime         datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime         datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete           tinyint  default 0                 not null comment '逻辑-1删除 -0存在'
)
    comment '文章表';


insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么我们需要更多的自由时间', 7, '蔡徐坤', '篮球', '篮球');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('真相：消费者食品中的添加剂', 5, '蔡徐坤', 'rap', 'rap');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('手机革命：智能手机如何改变我们的生活', 10, '练习', '鸡', '唱');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么太阳能是可持续发展的未来', 50, '蔡徐坤', '唱', 'rap');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('睡眠质量对身体健康的影响', 56, '个人练习生', '美', '篮球');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('穿越时空：时光旅行是否有可能？', 73, '你', '太', '练习');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('婚姻中的危机与挑战', 66, '两年半', 'music', 'music');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('真相：消费者食品中的添加剂', 63, '你', '篮球', '个人练习生');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么冥想对身心健康有益？', 19, '时长', 'music', '篮球');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('新时代的工作方式：远程办公', 88, 'music', '唱', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('性别平等的挑战：为什么还有很长的路要走', 43, '个人练习生', '唱', '美');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何在人生的低谷中振作起来', 47, '蔡徐坤', '太', '个人练习生');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何培养你的领导力', 53, '你', 'music', '篮球');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('大数据：什么是它？它是如何影响我们的生活？', 75, '篮球', '鸡', '练习');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('生物科技对人类的影响', 95, '个人练习生', '篮球', '篮球');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('让你的音乐更好听的秘诀', 90, '美', '跳', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('亲密关系中的心理学：爱情的科学', 31, '两年半', '太', '跳');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('亲密关系中的心理学：爱情的科学', 73, '篮球', '唱', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么人类需要梦想', 37, '跳', '唱', '两年半');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么太阳能是可持续发展的未来', 21, '你', '鸡', '太');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('财务自由的路上：如何理智地投资你的钱', 37, '时长', '跳', 'rap');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么我们需要更多的自我反思', 68, 'music', '两年半', '个人练习生');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('现代艺术的探索之路', 47, '蔡徐坤', '个人练习生', 'rap');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何在人生的低谷中振作起来', 41, '跳', '你', 'rap');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么太阳能是可持续发展的未来', 71, '篮球', '太', '太');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('自我意识的力量：如何认识你自己', 97, '唱', '鸡', '跳');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何在恶劣天气中保持积极心态', 51, '太', 'music', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('让你的音乐更好听的秘诀', 92, '美', '篮球', '两年半');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('亲密关系中的心理学：爱情的科学', 92, 'rap', '篮球', 'music');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何成为一个优秀的演讲者', 42, '个人练习生', '太', 'music');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('健康生活的10个简单技巧', 89, '两年半', '篮球', 'rap');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何在团队中建立信任', 51, '你', '蔡徐坤', '太');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('新冠病毒疫苗：安全性和有效性的评估', 65, '唱', '美', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么我们需要更多的自我关爱', 27, 'music', 'rap', '美');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何处理不同文化之间的差异', 75, '蔡徐坤', '鸡', '你');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么我们需要更多的自由时间', 57, '两年半', '时长', '太');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('生物科技对人类的影响', 22, '练习', '你', '唱');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么自恋者总是会赢？', 75, '篮球', '跳', '练习');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('睡眠质量对身体健康的影响', 76, '两年半', '蔡徐坤', '鸡');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何减轻焦虑症状', 24, '练习', '太', '美');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('人类是否能够在地球以外的星球上生存？', 7, 'music', '时长', '美');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么人类需要梦想', 1, '唱', 'music', '蔡徐坤');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('性别平等的挑战：为什么还有很长的路要走', 86, '你', '你', '你');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何进行高效的时间管理', 86, '鸡', 'music', '跳');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('人类是否能够在地球以外的星球上生存？', 28, '蔡徐坤', 'rap', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何建立积极的心态', 67, '鸡', '个人练习生', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('数字化时代的隐私保护', 99, '个人练习生', 'rap', '时长');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('如何在人生的低谷中振作起来', 91, '练习', '太', '你');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('为什么冥想对身心健康有益？', 63, '个人练习生', '美', '美');
insert into `text` (`textTitle`, `textAuthorId`, `userText`, `textHtml`, `textIntroduce`) values ('财务自由的路上：如何理智地投资你的钱', 78, '个人练习生', '唱', '蔡徐坤');

drop table if exists favorites;
-- auto-generated definition
create table favorites
(
    favoritesId bigint auto_increment primary key comment '表ID',
    userId      bigint                             not null comment '用户ID',
    textId      bigint                             not null comment '文章Id',
    createTime  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete    tinyint  default 0                 not null comment '逻辑-1删除 -0存在'
)
    comment '收藏夹';

drop table if exists TextLike;
-- auto-generated definition
create table TextLike
(
    TextLikeId bigint auto_increment primary key comment '表ID',
    userId     bigint                             not null comment '用户ID',
    textId     bigint                             not null comment '文章Id',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete   tinyint  default 0                 not null comment '逻辑-1删除 -0存在'
)
    comment '点赞表';

drop table if exists friends;

create table friends
(
    friendsId  bigint primary key auto_increment comment '表ID',
    userId     bigint                             not null comment '用户ID',
    friendId   bigint                             not null comment '关注id',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete   tinyint  default 0                 not null comment '逻辑-1删除 -0存在'
)
    comment '关注表';

drop table if exists comments;

create table comments
(
    commentsId bigint primary key auto_increment comment '表ID',
    userId     bigint                             not null comment '用户ID',
    content    varchar(150)                       not null null comment '评论内容',
    textId     bigint                             not null comment '文章id',
    createTime datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete   tinyint  default 0                 not null comment '逻辑-1删除 -0存在'
)
    comment '评论表';

