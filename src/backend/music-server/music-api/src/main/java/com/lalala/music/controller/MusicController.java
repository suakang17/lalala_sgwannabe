package com.lalala.music.controller;

import com.lalala.music.service.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MusicController {
    private final MusicService service;
}
