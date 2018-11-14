package net.radionica.ejira.util;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

public class SecurityUtil {

    public static String generateToken() {
	return UUID.randomUUID().toString();
    }

    public static final Cache<String, Long> CACHE = Caffeine.newBuilder().maximumSize(10_000)
	    .expireAfterAccess(30, TimeUnit.MINUTES).build();

}
