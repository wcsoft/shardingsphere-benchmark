CREATE DATABASE IF NOT EXISTS default_database;
CREATE TABLE IF NOT EXISTS  `default_table` ( `id` bigint(20) NOT NULL AUTO_INCREMENT, `k` int(11) NOT NULL DEFAULT '0', `c` char(120) NOT NULL DEFAULT '', `pad` char(60) NOT NULL DEFAULT '', PRIMARY KEY (`id`), KEY `k_index` (`k`), KEY `c_index` (`c`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;
