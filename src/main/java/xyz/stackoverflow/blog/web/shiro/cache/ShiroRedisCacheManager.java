package xyz.stackoverflow.blog.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class ShiroRedisCacheManager implements CacheManager {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String prefix = "shiro:";

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroRedisCache<K, V>(prefix + s,redisTemplate);
    }
}
