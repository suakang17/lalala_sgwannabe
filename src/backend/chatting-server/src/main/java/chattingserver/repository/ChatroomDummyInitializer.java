package chattingserver.repository;

import chattingserver.domain.chat.ChatMessage;
import chattingserver.domain.room.Music;
import chattingserver.domain.room.Playlist;
import chattingserver.domain.room.Room;
import chattingserver.domain.room.User;
import chattingserver.util.constant.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class ChatroomDummyInitializer implements ApplicationRunner {

    private final RoomRepository roomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User dummyUser1 = User.builder()
                .uid(1L)
                .nickName("유저닉네임1")
                .profileImage("userprofileimage1.url")
                .enteredAt(LocalDateTime.now())
                .build();

        User dummyUser2 = User.builder()
                .uid(2L)
                .nickName("유저닉네임2")
                .profileImage("userprofileimage2.url")
                .enteredAt(LocalDateTime.now())
                .build();

        List<User> users = new ArrayList<>();
        users.add(dummyUser1);
        users.add(dummyUser2);

        Music dummyMusic1 = Music.builder()
                .title("음원1")
                .artist("아티스트1")
                .playtime("12:34")
                .thumbnail("thumbnail-1.url")
                .build();

        Music dummyMusic2 = Music.builder()
                .title("음원2")
                .artist("아티스트2")
                .playtime("01:23")
                .thumbnail("thumbnail-2.url")
                .build();

        List<Music> dummyMusics = new ArrayList<>();
        dummyMusics.add(dummyMusic1);
        dummyMusics.add(dummyMusic2);


        Playlist dummyPlaylist = Playlist.builder()
                .id("qwer1234")
                .name("플레이리스트1")
                .musics(dummyMusics)
                .build();

        // room build
        Room dummyRoom = Room.builder()
                .roomName(dummyPlaylist.getName())
                .playlist(dummyPlaylist)
                .users(users)
                .build();

        roomRepository.save(dummyRoom);
        log.info("방 생성 성공 room={}", dummyRoom);

        ChatMessage message1 = chatMessageRepository.save(ChatMessage.builder()
                .messageType(MessageType.MSG)
                .roomId(dummyRoom.getId())
                .senderId(dummyUser1.getUid())
                .nickName(dummyUser1.getNickName())
                .senderProfileImage(dummyUser1.getProfileImage())
                .content("메시지메시지1")
                .createdAt(LocalDateTime.now())
                .build());

        ChatMessage message2 = chatMessageRepository.save(ChatMessage.builder()
                .messageType(MessageType.MSG)
                .roomId(dummyRoom.getId())
                .senderId(dummyUser2.getUid())
                .nickName(dummyUser2.getNickName())
                .senderProfileImage(dummyUser2.getProfileImage())
                .content("메시지메시지2")
                .createdAt(LocalDateTime.now())
                .build());

        log.info("메시지 생성 성공 message1={}", message1);
        log.info("메시지 생성 성공 message2={}", message2);

        User dummyUser3 = User.builder()
                .uid(3L)
                .nickName("유저닉네임3")
                .profileImage("userprofileimage3.url")
                .enteredAt(LocalDateTime.now())
                .build();

        User dummyUser4 = User.builder()
                .uid(3L)
                .nickName("유저닉네임4")
                .profileImage("userprofileimage24url")
                .enteredAt(LocalDateTime.now())
                .build();

        List<User> users2 = new ArrayList<>();
        users2.add(dummyUser3);
        users2.add(dummyUser4);
        users2.add(dummyUser1);

        Music dummyMusic3 = Music.builder()
                .title("음원1")
                .artist("아티스트1")
                .playtime("12:34")
                .thumbnail("thumbnail-1.url")
                .build();

        List<Music> dummyMusics2 = new ArrayList<>();
        dummyMusics.add(dummyMusic1);
        dummyMusics.add(dummyMusic3);


        Playlist dummyPlaylist2 = Playlist.builder()
                .id("098765432qwerty")
                .name("플레이리스트2")
                .musics(dummyMusics2)
                .build();

        // room build
        Room dummyRoom2 = Room.builder()
                .roomName(dummyPlaylist2.getName())
                .playlist(dummyPlaylist2)
                .users(users2)
                .build();

        roomRepository.save(dummyRoom2);
        log.info("방 생성 성공 room={}", dummyRoom);

        ChatMessage message3 = chatMessageRepository.save(ChatMessage.builder()
                .messageType(MessageType.MSG)
                .roomId(dummyRoom2.getId())
                .senderId(dummyUser1.getUid())
                .nickName(dummyUser1.getNickName())
                .senderProfileImage(dummyUser1.getProfileImage())
                .content("메시지메시지3")
                .createdAt(LocalDateTime.now())
                .build());

        ChatMessage message4 = chatMessageRepository.save(ChatMessage.builder()
                .messageType(MessageType.MSG)
                .roomId(dummyRoom2.getId())
                .senderId(dummyUser4.getUid())
                .nickName(dummyUser4.getNickName())
                .senderProfileImage(dummyUser4.getProfileImage())
                .content("메시지메시지4")
                .createdAt(LocalDateTime.now())
                .build());

        log.info("메시지 생성 성공 message3={}", message3);
        log.info("메시지 생성 성공 message4={}", message4);


    }
}
