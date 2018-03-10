-- mysql 5.6+
-- 时区一律 UTC+0，慎用now和CURRENT_TIMESTAMP
-- default-time-zone = '+00:00'
-- 字符一律 UTF-8
-- character-set-server=utf8

-- 添加表，按字母顺序排列 --

CREATE TABLE `cc_sequence` (
  `table_name` varchar(100) NOT NULL COMMENT '表名',
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT 'seq值，当前正在使用的',
  PRIMARY KEY (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='全局主键';

CREATE TABLE `cc_logno` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除:true=1,false=0',
  `logno` bigint(20) DEFAULT NULL COMMENT '日志编号等于id',
  `log_type` int(11) DEFAULT NULL COMMENT '日志类型',
  `admin_id` bigint(20) DEFAULT NULL COMMENT '管理员ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `terminal_id` varchar(100) DEFAULT NULL COMMENT '登陆设备标识:ip等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='日志编号';

