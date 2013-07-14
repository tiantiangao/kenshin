package com.gtt.kenshin.cache;

import java.util.List;

/**
 * 缓存服务
 * 
 * @author tiantiangao
 */
public class CacheServiceImpl implements CacheService {

	@Override
	public boolean add(CacheKey key, Object value) {
		return true;
	}

	@Override
	public <T> boolean mAdd(CacheKey cacheKey, List<T> objs) {
		return true;
	}

	@Override
	public <T> T get(CacheKey key) {
		return null;
	}

	@Override
	public <T> List<T> mGet(List<CacheKey> keys) {
		return null;
	}

	@Override
	public boolean remove(CacheKey key) {
		return false;
	}
}
