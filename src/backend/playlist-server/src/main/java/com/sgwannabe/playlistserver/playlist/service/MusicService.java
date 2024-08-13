package com.sgwannabe.playlistserver.playlist.service;

@Service
@RequiredArgsConstructor
public class MusicService {
    private final FeignMusicClient musicClient;

    public List<Music> getMusicsFromIds(List<Long> musicIds) {
        BaseResponse<List<MusicDTO>> response = musicClient.getMusicFromIds(new MusicRetrieveRequestDTO(musicIds));
        return response.getData().stream()
                .map(this::convertToMusic)
                .toList();
    }

    public Music createMusic(MusicRequestDto dto) {
        return Music.builder()
                .title(dto.getTitle())
                .artistId(dto.getArtistId())
                .artist(dto.getArtist())
                .albumId(dto.getAlbumId())
                .album(dto.getAlbum())
                .thumbnail(dto.getThumbnail())
                .playtime(dto.getPlaytime())
                .build();
    }

    private Music convertToMusic(MusicDTO dto) {
        return Music.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .artistId(dto.getArtist().getId())
                .artist(dto.getArtist().getName())
                .albumId(dto.getAlbum().getId())
                .album(dto.getAlbum().getTitle())
                .thumbnail(dto.getAlbum().getCoverUrl())
                .playtime(formatPlayTime(dto.getPlayTime()))
                .build();
    }

    private String formatPlayTime(Short playTime) {
        int min = playTime / 60;
        int sec = playTime % 60;
        return String.format("%02d:%02d", min, sec);
    }
}