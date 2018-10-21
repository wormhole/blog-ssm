package xyz.stackoverflow.blog.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 缓存管理器
 *
 * @author 凉衫薄
 */
public class ShiroRedisCacheManager implements CacheManager {

    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroRedisCache<K, V>(redisCacheManager.getCache(s), redisTemplate);
    }
}
