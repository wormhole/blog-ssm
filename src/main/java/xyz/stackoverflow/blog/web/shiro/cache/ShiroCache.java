package xyz.stackoverflow.blog.web.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.Collection;
import java.util.Set;

public class ShiroCache<K,V> implements Cache<K,V> {

    private RedisCacheManager redisCacheManager;
    private org.springframework.cache.Cache cache;

    public ShiroCache(String cacheName, RedisCacheManager redisCacheManager){
        this.redisCacheManager = redisCacheManager;
        cache = redisCacheManager.getCache(cacheName);
    }

    String getKey(K k){
        String key = null;
        if(k instanceof PrincipalCollection){
            PrincipalCollection principalCollection = (PrincipalCollection)k;
            key = "authorization_"+principalCollection.getPrimaryPrincipal().toString();
        }else{
            key = (String) k;
        }
        return key;
    }

    @Override
    public V get(K k) throws CacheException {
        if(k == null) {
            return null;
        }
        org.springframework.cache.Cache.ValueWrapper valueWrapper = null;
        String key = getKey(k);
        valueWrapper = cache.get(key);
        if(valueWrapper == null) {
            return null;
        }
        return (V) valueWrapper.get();
    }

    @Override
    public V put(K k, V v) throws CacheException {
        String key = getKey(k);
        cache.put(key,v);
        return get(k);
    }

    @Override
    public V remove(K k) throws CacheException {
        V v = get(k);
        String key = getKey(k);
        cache.evict(key);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        cache.clear();
    }

    @Override
    public int size() {
        return redisCacheManager.getCacheNames().size();
    }

    @Override
    public Set<K> keys() {
        return (Set<K>) redisCacheManager.getCacheNames();
    }

    @Override
    public Collection<V> values() {
        return (Collection<V>) cache.get(redisCacheManager.getCacheNames()).get();
    }
}
