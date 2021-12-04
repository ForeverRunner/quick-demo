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

insert into
    `tb_user`(`username`,`password`,`phone`,`email`) values
('fox','$2a$10$9ZhDOBp.sRKat4l14ygu/.LscxrMUcDAfeVOEPiYwbcRkoB09gCmi','158xxx
xxxx','xxxxxxx@gmail.com');