-- query ref
--  - https://dev.mysql.com/doc/refman/8.4/en/alter-table-partition-operations.html
--  - https://hoing.io/archives/24488
DROP TABLE IF EXISTS user_request;

CREATE TABLE user_request
(
    id         BIGINT       NOT NULL,
    created_at DATETIME(6)  NOT NULL,
    params     VARCHAR(255) NULL,
    status     INT          NOT NULL,
    PRIMARY KEY (id, created_at)
)
    PARTITION BY RANGE COLUMNS (created_at) (
        PARTITION p_20241021 VALUES LESS THAN ('2024-10-21'),
        PARTITION p_max VALUES LESS THAN (MAXVALUE)
        );
