-- Account Table --
CREATE TABLE `Act_Account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userId` int(11) unsigned NOT NULL COMMENT '用户ID',
  `price` decimal(10,2) NOT NULL COMMENT '账目金额',
  `memo` text COMMENT '账目说明',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `lastModified` datetime DEFAULT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- User Table --
CREATE TABLE `Act_User` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码(MD5)',
  `email` varchar(200) NOT NULL COMMENT '邮箱地址',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户状态(1-正常,0-删除)',
  `created` datetime DEFAULT NULL COMMENT '创建日期',
  `lastModified` datetime DEFAULT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
