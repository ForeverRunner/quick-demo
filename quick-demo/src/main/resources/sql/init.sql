CREATE TABLE course(
    cid BIGINT(20) PRIMARY KEY,
    cname VARCHAR(50) NOT NULL,
    user_id bigint(20) NOT NULL,
    cstatus varchar(10) NOT NULL
)

CREATE TABLE t_user(
    uid BIGINT(20) PRIMARY KEY,
    uname VARCHAR(50) NOT NULL,
)
