### springboot-lock
#### 后台服务器集群下，redis分布式锁
---
- Synchronized锁
- lock锁
- 分布式锁
  - mysql分布式锁
  - redis分布式锁
  - zookeeper分布式锁
```
概要：
1.锁的使用场景
多线程，高并发，结果互斥、不能重复

2.举例业务场景，春运抢票

3.无锁的情况下，多个用户发起抢票
模拟多线程抢票过程
TicketTest
会导致抢到相同的票

3.单台服务器加锁
3.1.Synchronized锁
SyncTest
3.2.lock锁
5.lock锁
Lock lock = new ReentrantLock();//实例化锁对象
lock.lock();//加锁操作
lock.unlock();//减锁操作
lock.trylock();//尝试加锁
LockTest
可以正常使用

4.但是现实往往是后台服务器集群
分布式（有多个后台服务器）：
用户 => 负载均衡服务器 => [后台服务器，后台服务器，后台服务器] => 数据库服务器
即使给后台服务器加锁，数据库仍可能会被不同后台服务器同时访问到

分布式锁设计理念：
用户 => 负载均衡服务器 => [后台服务器，后台服务器，后台服务器] => 第三方锁机制（每次只允许一个线程操作） => 数据库服务器

第三方锁特点：高并发，锁控制，避免死锁，原子性操作

4.1.mysql分布式锁
加锁：MysqlMapper.insert(id:1)，写入数据库成功即加锁成功
解锁：MysqlMapper.delete(id:1)，删除id=1的数据
MysqlLock
MysqlMapper
MysqlLockTest
反复读写数据库，性能较差

4.2.redis分布式锁
加锁：redis.setnx(特定key,随机值)，设定失效时间，写值成功即加锁成功
解锁：删除redis的节点key数据时，要保证获取value/value值比对一致/删除数据，3个操作是原子性，使用lua脚本实现
VMware起redis集群  
RedisConfig,redis.properties redis配置
RedisLock
LuaStream
redis-lock.lua
ThreadPool线程池
RedislLockTest

4.3.zookeeper分布式锁
不用不停询问锁是否用完，锁用完了，主动通知，基于zookeeper
```