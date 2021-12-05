DROP TABLE tb_user;
CREATE TABLE IF NOT EXISTS `tb_user`
(
    `id`       bigint(20)  NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL DEFAULT 'NULL' COMMENT '用户名',
    `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码，加密存储',
    `phone`    varchar(20) NOT NULL DEFAULT '' COMMENT '注册手机号',
    `email`    varchar(50) NOT NULL DEFAULT '' COMMENT '注册邮箱',
    `created`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated`  datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`) USING BTREE,
    UNIQUE KEY `phone` (`phone`) USING BTREE,
    UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 38
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

insert into `tb_user`(`username`, `password`, `phone`, `email`)
values ('fox', '$2a$10$9ZhDOBp.sRKat4l14ygu/.LscxrMUcDAfeVOEPiYwbcRkoB09gCmi', '158xxx
xxxx', 'xxxxxxx@gmail.com');

DROP TABLE tb_role;
CREATE TABLE `tb_role` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
 `parent_id` bigint(20) DEFAULT NULL COMMENT '父角色',
 `name` varchar(64) NOT NULL COMMENT '角色名称',
 `enname` varchar(64) NOT NULL COMMENT '角色英文名称',
 `description` varchar(200)NOT NULL DEFAULT '' COMMENT '备注',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='角色表';
insert into `tb_role`(`id`,`parent_id`,`name`,`enname`) values (37,0,'超级管理员','fox');

CREATE TABLE `tb_user_role` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`user_id` bigint(20) NOT NULL COMMENT '用户 ID',
`role_id` bigint(20) NOT NULL COMMENT '角色 ID',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='用户角色表';
insert into `tb_user_role`(`id`,`user_id`,`role_id`) values (37,37,37);


CREATE TABLE `tb_permission` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`parent_id` bigint(20) DEFAULT NULL COMMENT '父权限',
`name` varchar(64) NOT NULL COMMENT '权限名称',
`enname` varchar(64) NOT NULL COMMENT '权限英文名称',
 `url` varchar(255) NOT NULL COMMENT '授权路径',
  `description` varchar(200) DEFAULT '' COMMENT '备注',
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='权限表';
insert into `tb_permission`(`id`,`parent_id`,`name`,`enname`,`url`)
values (37,0,'系统管理','System','/'),
       (38,37,'用户管理','SystemUser','/users/'),
       (39,38,'查看用户','SystemUserView',''),
       (40,38,'新增用户','SystemUserInsert',''),
       (41,38,'编辑用户','SystemUserUpdate',''),
       (42,38,'删除用户','SystemUserDelete',''),
       (44,37,'内容管理','SystemContent','/contents/'),
       (45,44,'查看内容','SystemContentView','/contents/view/**'),
       (46,44,'新增内容','SystemContentInsert','/contents/insert/**'),
       (47,44,'编辑内容','SystemContentUpdate','/contents/update/**'),
       (48,44,'删除内容','SystemContentDelete','/contents/delete/**');

CREATE TABLE `tb_role_permission` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) NOT NULL COMMENT '角色 ID',
    `permission_id` bigint(20) NOT NULL COMMENT '权限 ID',
     PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='角色权限表';
insert into `tb_role_permission`(`id`,`role_id`,`permission_id`) values (37,37,37), (38,37,38), (39,37,39), (40,37,40), (41,37,41), (42,37,42), (43,37,44), (44,37,45), (45,37,46), (46,37,47), (47,37,48);