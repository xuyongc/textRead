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
    textLikeNumber     bigint                             null comment '喜欢数',
    textFavoriteNumber bigint                             null comment '收藏数量',
    textStatus         int      default 1                 not null comment '状态 0-正常 1-审核中 2-下架',
    createTime         datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime         datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    isDelete           tinyint  default 0                 not null comment '逻辑-1删除 -0存在'
)
    comment '文章表';

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
