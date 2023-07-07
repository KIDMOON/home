DROP TABLE IF EXISTS `file_do`;
CREATE TABLE `file_do`
(
    `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(255) NOT NULL,
    `hash`         VARCHAR(100) NOT NULL,
    `thumb`        VARCHAR(100),
    `type`         VARCHAR(100) NOT NULL,
    `content_type` VARCHAR(255),
    `size`         BIGINT(20)   NOT NULL DEFAULT 0,
    `snowflake_id` BIGINT(255)  NOT NULL,
    `time`         DATETIME,
    PRIMARY KEY (`id`),
    KEY `file_hash_col_index` (`hash`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `date_calendar_do`;
CREATE TABLE `date_calendar_do`
(
    `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `software_dw_do`;
CREATE TABLE `software_dw_do`
(
    `id`          BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL,
    `sub_name`    VARCHAR(255),
    `version`     VARCHAR(255),
    `icon`        VARCHAR(255),
    `software`    VARCHAR(255),
    `sort`         BIGINT(20),
    `update_time` DATETIME,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;