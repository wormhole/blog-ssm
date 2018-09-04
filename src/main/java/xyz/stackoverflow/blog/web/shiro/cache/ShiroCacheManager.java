package xyz.stackoverflow.blog.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;

public class ShiroCacheManager implements CacheManager {

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroCache<>(s,redisCacheManager);
    }
}
