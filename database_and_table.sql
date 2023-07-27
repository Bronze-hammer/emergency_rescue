
CREATE DATABASE `demo` /*!40100 DEFAULT CHARACTER SET utf8 */;


-- demo.`user` definition

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login` varchar(100) DEFAULT NULL COMMENT '用户',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO demo.`user` (login, password, `role`) VALUES('user', 'user', 'ROLE_USER');
INSERT INTO demo.`user` (login, password, `role`) VALUES('admin', 'admin', 'ROLE_ADMIN');
