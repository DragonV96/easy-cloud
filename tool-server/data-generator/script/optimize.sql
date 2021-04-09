-- --------------- 执行计划 查询 sql ---------------

-- 年龄条件查询 sql
EXPLAIN SELECT * FROM users t WHERE t.age = '18'

-- 姓名条件查询 sql
EXPLAIN SELECT * FROM users t WHERE t.real_name = '庞咽'

-- 邮箱条件查询 sql
EXPLAIN SELECT * FROM users t WHERE t.email = 'hagtc2us8@qq.com'

-- 邮箱条件模糊查询 sql
EXPLAIN SELECT * FROM users t WHERE t.email LIKE 'ha%'

-- 分页查询 sql
EXPLAIN SELECT * FROM users t LIMIT 9800000, 20

-- 分页查询 sql 姓名条件查询
EXPLAIN SELECT * FROM users t WHERE t.real_name = '庞咽' LIMIT 1, 20

-- 分页查询 sql 邮箱条件查询
EXPLAIN SELECT * FROM users t WHERE t.age = '18' LIMIT 1, 20

-- 分页查询 sql 邮箱条件模糊查询
EXPLAIN SELECT * FROM users t WHERE t.email LIKE 'ha%' LIMIT 9800000, 20

-- 分页查询 sql 邮箱条件模糊查询
EXPLAIN SELECT * FROM users t WHERE t.email LIKE 'ha%' LIMIT 1, 20

-- 第980w页分页查询 sql
EXPLAIN SELECT * FROM users t LIMIT 9800000, 20

-- 优化第980w页分页查询 sql
EXPLAIN SELECT * FROM users WHERE id >= (SELECT id FROM users ORDER BY id DESC LIMIT 9800000, 1) LIMIT 20

-- 优化第980w页分页查询 sql
EXPLAIN SELECT * FROM users WHERE id >= '9800000' LIMIT 20




-- --------------- 优化 sql ---------------
-- 为用户姓名建索引
ALTER TABLE users ADD INDEX users_real_name(real_name);

-- 为用户姓名建前缀索引
ALTER TABLE users ADD INDEX users_email(email(12));