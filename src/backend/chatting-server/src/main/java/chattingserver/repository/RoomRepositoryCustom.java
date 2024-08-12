package chattingserver.repository;

import chattingserver.domain.room.User;
import chattingserver.dto.request.ReadMessageUpdateRequestDto;
import com.mongodb.client.result.UpdateResult;

public interface RoomRepositoryCustom {
    void exitRoom(String roomId, Long uid);
    UpdateResult addUserToRoom(String roomId, User user);
    UpdateResult updateLastReadMsgId(String roomId, Long uid, String messageId);
    void updatePlaylist(String roomId, Playlist playlist);
}
