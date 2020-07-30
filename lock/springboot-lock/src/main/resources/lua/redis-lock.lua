if redis.call("get", KEYS[1]) == ARGV[1] then
    return redis.call("del", KEYS[1])
else
    return 0
end

--为了保证删除操作页面原子性操作,使用lua脚本
