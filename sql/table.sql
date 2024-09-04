-- auto-generated definition
create table user
(
    id           bigint auto_increment comment '主键id'
        primary key,
    username     varchar(256)                                                                                                  null comment '用户昵称',
    userAccount  varchar(256)                                                                                                  null comment '账户',
    avatarUrl    varchar(1024) default 'https://pic.code-nav.cn/user_avatar/1739833412782415873/thumbnail/u58lH4g1-header.jpg' null comment '用户头像',
    gender       tinyint                                                                                                       null comment '性别',
    userPassword varchar(512)                                                                                                  not null comment '密码',
    phone        varchar(128)                                                                                                  null comment '电话',
    email        varchar(512)                                                                                                  null comment '邮箱',
    userStatus   int           default 0                                                                                       not null comment '用户状态 0 -正常',
    createTime   datetime      default CURRENT_TIMESTAMP                                                                       null comment '创建时间',
    updateTime   datetime      default CURRENT_TIMESTAMP                                                                       null comment '更新时间',
    isDelete     tinyint       default 0                                                                                       not null comment '是否删除（逻辑删除）',
    userRole     int           default 0                                                                                       not null comment '用户权限 0-普通用户 1-管理员',
    planeCode    varchar(512)                                                                                                  null comment '星球编号'
)
    comment '用户';

