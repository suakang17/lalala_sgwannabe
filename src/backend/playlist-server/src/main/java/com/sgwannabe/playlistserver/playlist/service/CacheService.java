package com.sgwannabe.playlistserver.playlist.service;

@Service
@RequiredArgsConstructor
public class CacheService {
    private final RedisTemplate<String, Playlist> redisTemplate;

    public void cachePlaylist(Playlist playlist) {
        String key = KeyGenerator.playlistKeyGenerate(playlist.getId());
        redisTemplate.opsForValue().set(key, playlist, Duration.ofMinutes(10));
    }

    public Optional<PlaylistResponseDto> getCachedPlaylist(String id) {
        String key = KeyGenerator.playlistKeyGenerate(id);
        Playlist cachedPlaylist = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(cachedPlaylist).map(converter::convert);
    }

    public void evictPlaylist(String id) {
        String key = KeyGenerator.playlistKeyGenerate(id);
        redisTemplate.delete(key);
    }
}