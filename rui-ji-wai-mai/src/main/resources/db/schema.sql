create table if not exists tb_user
(
    id          bigint unsigned auto_increment,
    account     varchar(100)     not null comment '账号',
    password    varchar(100)      not null default '' comment '密码',
    login_type  tinyint unsigned not null comment '登录方式',
    role        tinyint          not null default 0 comment '角色',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '用户表';

create table if not exists tb_user_info
(
    id            bigint unsigned auto_increment,
    user_id       bigint unsigned  not null unique comment '用户ID',
    uid           bigint unsigned  not null unique comment '用户显示ID',
    username      varchar(25)      not null default '' comment '用户名',
    gender        tinyint unsigned not null default 0 comment '性别',
    phone         varchar(20)      not null default '' comment '手机号',
    profile_photo varchar(255)     not null default '' comment '头像',
    create_time   datetime         not null default current_timestamp ,
    update_time   datetime         not null default current_timestamp,
    is_deleted    tinyint unsigned not null default 0,
    primary key (id)
) comment '用户信息表';

create table if not exists tb_merchant
(
    id          bigint unsigned auto_increment,
    name        varchar(25)      not null default '' comment '店铺名',
    introduce   varchar(255)     not null default '' comment '介绍',
    logo_photo  varchar(255)     not null default '' 'Logo',
    province    varchar(10)      not null default '' comment '省',
    city        varchar(10)      not null default '' comment '市',
    county      varchar(10)      not null default '' comment '区',
    address     varchar(50)      not null default '' comment '地址',
    longitude   varchar(20)      not null default '' comment '经度',
    latitude    varchar(20)      not null default '' comment '维度',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '商家表';

create table if not exists tb_merchant_staff
(
    id          bigint unsigned auto_increment,
    merchant_id bigint unsigned  not null unique comment '商家ID',
    user_id     bigint unsigned  not null unique comment '用户ID',
    isAdmin     tinyint          not null default 0 comment '管理员',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '商家和员工关联表';

create table if not exists tb_dishes
(
    id          bigint unsigned auto_increment,
    merchant_id bigint unsigned  not null comment '商铺ID',
    name        varchar(50)      not null default '' comment '名称',
    introduce   varchar(255)     not null default '' comment '描述',
    picture     varchar(255)     not null default '' comment '图片',
    price       int              not null default 0 comment '价格',
    sales       int unsigned     not null default 0 comment '销量',
    state       tinyint          not null default 0 comment '状态',
    label       tinyint          not null default 0 comment '标记',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '菜品表';

create table if not exists tb_dishes_category
(
    id          bigint unsigned auto_increment,
    merchant_id bigint unsigned  not null comment '商铺ID',
    name        varchar(25)      not null default '' comment '类别名称',
    state       tinyint          not null default 0 comment '状态',
    label       tinyint          not null default 0 comment '标记',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '菜品类别表';

create table if not exists tb_dishes_and_category_rel
(
    id          bigint unsigned auto_increment,
    category_id bigint unsigned  not null comment '菜品类别ID',
    dishes      varchar(500)     not null default '' comment '菜品详情',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '菜品和类别关联表';

create table if not exists tb_shipping_address
(
    id          bigint unsigned auto_increment,
    user_id     bigint unsigned  not null comment '用户ID',
    contact     varchar(20)      not null default '' comment '联系人',
    gender      tinyint unsigned not null default 0 comment '性别',
    phone       varchar(20)      not null default '' comment '联系电话',
    label       tinyint          not null default 0 comment '标记',
    province    varchar(10)      not null default '' comment '省',
    city        varchar(10)      not null default '' comment '市',
    county      varchar(10)      not null default '' comment '区',
    address     varchar(50)      not null default '' comment '地址',
    longitude   varchar(20)      not null default '' comment '经度',
    latitude    varchar(20)      not null default '' comment '维度',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '收货地址表';

create table if not exists tb_order
(
    id                   bigint unsigned auto_increment,
    user_id              bigint unsigned  not null comment '用户ID',
    merchant_id          bigint unsigned  not null comment '商铺ID',
    content              varchar(1000)    not null default '' comment '订单详情',
    remark               varchar(255)     not null default '' comment '备注',
    shipping_address_id  bigint unsigned  not null comment '收货地址ID',
    state                tinyint          not null default 0 comment '状态',
    payment_type         tinyint          not null comment '支付方式',
    order_time           datetime         not null comment '下单时间',
    payment_time         datetime comment '支付时间',
    merchant_accept_time datetime comment '接单时间',
    delivery_time        datetime comment '配送时间',
    finish_time          datetime comment '完成时间',
    create_time          datetime         not null default current_timestamp,
    update_time          datetime         not null default current_timestamp,
    is_deleted           tinyint unsigned not null default 0,
    primary key (id)
) comment '订单表';

create table if not exists tb_shopping_cart
(
    id          bigint unsigned auto_increment,
    user_id     bigint unsigned  not null comment '用户ID',
    content     varchar(1000)    not null default '' comment '详情',
    create_time datetime         not null default current_timestamp,
    update_time datetime         not null default current_timestamp,
    is_deleted  tinyint unsigned not null default 0,
    primary key (id)
) comment '购物车表';



#     订单详情: 菜品、口味、数量、原价 订单总价、优惠、订单最终价格

# 登录记录表、自定义菜品口味表、自定义地址标签表