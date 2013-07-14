package com.gtt.kenshin.cache;

import java.util.List;

/**
 * 缓存服务
 * 
 * @author tiantiangao
 */
public interface CacheService {

	boolean add(CacheKey key, Object value);

	<T> boolean mAdd(CacheKey cacheKey, List<T> objs);

	<T> T get(CacheKey key);

	<T> List<T> mGet(List<CacheKey> keys);

	boolean remove(CacheKey key);

}
