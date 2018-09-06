package xyz.stackoverflow.blog.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.Set;

public class ShiroRedisCache<K, V> implements Cache<K, V> {

    private String cacheKey;
    private RedisTemplate redisTemplate;

    public ShiroRedisCache(String cacheName,RedisTemplate redisTemplate) {
        this.cacheKey = cacheName;
        this.redisTemplate = redisTemplate;
    }

    private Object getKey(K k) {
        if (k instanceof PrincipalCollection) {
            PrincipalCollection prin = (PrincipalCollection) k;
            String email = prin.getPrimaryPrincipal().toString();
            return email;
        }
        return k;
    }

    @Override
    public V get(K k) throws CacheException {
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = getKey(k);
        return hash.get(key);
    }

    @Override
    public V put(K k, V v) throws CacheException {
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = getKey(k);
        hash.put((K) key, v);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        BoundHashOperations<String, K, V> hash = redisTemplate.boundHashOps(cacheKey);
        Object key = getKey(k);
        V value = hash.get(key);
        hash.delete(key);
        return value;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(cacheKey);
    }

    @Override
    public int size() {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.size().intValue();
    }

    @Override
    public Set<K> keys() {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.keys();
    }

    @Override
    public Collection<V> values() {
        BoundHashOperations<String,K,V> hash = redisTemplate.boundHashOps(cacheKey);
        return hash.values();
    }
}
