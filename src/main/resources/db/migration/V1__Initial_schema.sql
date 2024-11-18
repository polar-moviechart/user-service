create table users (
	id int auto_increment primary key,
	password varchar(100),
	nickname varchar(10) not null,
	external_id bigint not null,
	auth_type varchar(20) not null,
	created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) comment '유저' charset = utf8mb4;