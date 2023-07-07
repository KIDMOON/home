DROP TABLE IF EXISTS `customs_declaration_card_list_do`;
CREATE TABLE `customs_declaration_card_list_do`
(
    `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
    `g_no`           VARCHAR(255),
    `g_model`        VARCHAR(255),
    `seq_no`         VARCHAR(255),
    `origin_country` VARCHAR(255),
    `g_qty`          VARCHAR(255),
    `g_name`         VARCHAR(255),
    `decl_price`     VARCHAR(255),
    `code_t`         VARCHAR(255),
    `qty1`           VARCHAR(255),
    `code_s`         VARCHAR(255),
    `rmb_price`      VARCHAR(255),
    `qty2`           VARCHAR(255),
    `usd_price`      VARCHAR(255),
    `unit1`          VARCHAR(255),
    `unit2`          VARCHAR(255),
    `g_unit`         VARCHAR(255),
    `mod_num`        VARCHAR(255),
    `trade_curr`     VARCHAR(255),
    `decl_total`     VARCHAR(255),
    `contr_item`     VARCHAR(255),
    `product_Name`   VARCHAR(255),
    `duty_mode`      VARCHAR(255),
    `ybf`            VARCHAR(255),
    `zsl`            VARCHAR(255),
    `tsl`            VARCHAR(255),
    `be_long`        VARCHAR(255),
    `created_date`  DATETIME,
    `entry_id`   VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;



DROP TABLE IF EXISTS `customs_declaration_do`;
CREATE TABLE `customs_declaration_do`
(
    `id`           BIGINT(20) NOT NULL AUTO_INCREMENT,
    `ie_date`      VARCHAR(255),
    `op_mode`      VARCHAR(255),
    `flag_dec`     VARCHAR(255),
    `flag_conta`   VARCHAR(255),
    `ie_port`      VARCHAR(255),
    `date_dec`     VARCHAR(255),
    `usd_prices`   VARCHAR(255),
    `print_num`    INT(255) DEFAULT 0,
    `entry_id`     VARCHAR(255),
    `be_long`      VARCHAR(255),
    `encrypt_str`  VARCHAR(255),
    `created_date` DATETIME,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `customs_declaration_head_do`;
CREATE TABLE `customs_declaration_head_do`
(
    `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
    `traf_mode`      VARCHAR(255),
    `wrap_type`      VARCHAR(255),
    `seq_no`         VARCHAR(255),
    `trade_name`     VARCHAR(255),
    `other_rate`     VARCHAR(255),
    `insur_rate`     VARCHAR(255),
    `net_wt`         VARCHAR(255),
    `entry_id`       VARCHAR(255),
    `bill_no`        VARCHAR(255),
    `trade_mode`     VARCHAR(255),
    `owner_code`     VARCHAR(255),
    `owner_name`     VARCHAR(255),
    `status`         VARCHAR(255),
    `ie_port`        VARCHAR(255),
    `agent_code`     VARCHAR(255),
    `trans_mode`     VARCHAR(255),
    `decl_port`      VARCHAR(255),
    `trade_co`       VARCHAR(255),
    `conta_id`       VARCHAR(255),
    `destinate_port` VARCHAR(255),
    `pack_no`        VARCHAR(255),
    `edi_note`       VARCHAR(255),
    `gross_wt`       VARCHAR(255),
    `rtx_type`       VARCHAR(255),
    `d_date`         VARCHAR(255),
    `a_flag`         VARCHAR(255),
    `fee_rate`       VARCHAR(255),
    `ie_date`        VARCHAR(255),
    `district_code`  VARCHAR(255),
    `mod_num`        VARCHAR(255),
    `agent_name`     VARCHAR(255),
    `pre_entry_id`   VARCHAR(255),
    `trade_country`  VARCHAR(255),
    `manual_no`       VARCHAR(255),
    `contr_no`        VARCHAR(255),
    `traf_name`       VARCHAR(255),
    `voyage_no`       VARCHAR(255),
    `jzxsl`          VARCHAR(255),
    `pay_way`         VARCHAR(255),
    `co_owner`        VARCHAR(255),
    `payment_mark`    VARCHAR(255),
    `cut_mode`        VARCHAR(255),
    `fee_mark`        VARCHAR(255),
    `fee_curr`        VARCHAR(255),
    `license_no`      VARCHAR(255),
    `insur_mark`      VARCHAR(255),
    `insur_curr`      VARCHAR(255),
    `appr_no`         VARCHAR(255),
    `other_mark`      VARCHAR(255),
    `other_curr`      VARCHAR(255),
    `cert_mark`       VARCHAR(255),
    `note_s`          VARCHAR(255),
    `be_long`        VARCHAR(255),
    `created_date`   DATETIME,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`              BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `account`         VARCHAR(255) NOT NULL,
    `password`        VARCHAR(255),
    `contact`         VARCHAR(255),
    `mobile`          VARCHAR(255),
    `email`           VARCHAR(255),
    `company`         VARCHAR(255),
    `credit_code`     VARCHAR(255),
    `customs_code`    VARCHAR(255),
    `company_type`    VARCHAR(255),
    `expiration_date` DATETIME,
    `status`          BIGINT(20),
    `creator`         VARCHAR(255),
    `created_date`    DATETIME,
    `modifier`        VARCHAR(255),
    `modified_date`   DATETIME,
    `port_pwd`        VARCHAR(255),
    `image_id`        BIGINT(20),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `commodity_code`;
CREATE TABLE `commodity_code`
(
    `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
    `code`        VARCHAR(255),
    `tax_rate`    VARCHAR(255),
    `rebate_rate` FLOAT,
    `start_date`  DATETIME,
    `end_date`    DATETIME,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;



DROP TABLE IF EXISTS `exchange_rate_do`;
CREATE TABLE `exchange_rate_do`
(
    `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
    `money_id`      VARCHAR(255),
    `year`          INT(255),
    `month`         INT(255),
    `money_rate`    FLOAT(10),
    `day01`         FLOAT(10),
    `day02`         FLOAT(10),
    `day03`         FLOAT(10),
    `day04`         FLOAT(10),
    `day05`         FLOAT(10),
    `day06`         FLOAT(10),
    `day07`         FLOAT(10),
    `day08`         FLOAT(10),
    `day09`         FLOAT(10),
    `day10`         FLOAT(10),
    `day11`         FLOAT(10),
    `day12`         FLOAT(10),
    `day13`         FLOAT(10),
    `day14`         FLOAT(10),
    `day15`         FLOAT(10),
    `day16`         FLOAT(10),
    `day17`         FLOAT(10),
    `day18`         FLOAT(10),
    `day19`         FLOAT(10),
    `day20`         FLOAT(10),
    `day21`         FLOAT(10),
    `day22`         FLOAT(10),
    `day23`         FLOAT(10),
    `day24`         FLOAT(10),
    `day25`         FLOAT(10),
    `day26`         FLOAT(10),
    `day27`         FLOAT(10),
    `day28`         FLOAT(10),
    `day29`         FLOAT(10),
    `day30`         FLOAT(10),
    `day31`         FLOAT(10),
    `creator`       VARCHAR(255),
    `created_date`  DATETIME,
    `modifier`      VARCHAR(255),
    `be_long`        VARCHAR(255),
    `modified_date` DATETIME,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `trade_way_do`;
CREATE TABLE `trade_way_do`
(
    `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
    `code`       VARCHAR(255),
    `cn`         VARCHAR(255),
    `en`         VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `money_do`;
CREATE TABLE `money_do`
(
    `id`         BIGINT(20) NOT NULL AUTO_INCREMENT,
    `code`       VARCHAR(255),
    `cn`         VARCHAR(255),
    `en`         VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `bulletin_do`;
CREATE TABLE `bulletin_do`
(
    `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
    `title`   VARCHAR(255),
    `creator` VARCHAR(255),
    `content` LONGTEXT,
    `top`     BIT(1)     NOT NULL DEFAULT b'0',
    `created_date`  DATETIME,
    `deleted` BIT(1)     NOT NULL DEFAULT b'0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


INSERT INTO `user`(`id`, `account`, `password`, `contact`, `mobile`, `email`, `company`, `credit_code`, `customs_code`, `company_type`, `expiration_date`, `status`, `creator`, `created_date`, `modifier`, `modified_date`) VALUES (1, 'admin', 'd7bc1b05b9ced282c40015f20780c764', NULL, NULL, NULL, '圣玥科技', NULL, NULL, NULL, NULL, 2, NULL, '2021-06-18 11:54:38', NULL, '2021-06-18 11:54:43');
