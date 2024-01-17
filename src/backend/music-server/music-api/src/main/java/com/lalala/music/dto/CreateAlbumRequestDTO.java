package com.lalala.music.dto;

import com.lalala.music.entity.AlbumType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CreateAlbumRequestDTO {
    private String title;
    private String coverUrl;
    private AlbumType type;
    private LocalDateTime releasedAt;
    private Long artistId;
}
