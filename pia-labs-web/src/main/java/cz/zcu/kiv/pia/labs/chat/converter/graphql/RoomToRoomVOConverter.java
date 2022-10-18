package cz.zcu.kiv.pia.labs.chat.converter.graphql;

import cz.zcu.kiv.pia.labs.chat.domain.Room;
import cz.zcu.kiv.pia.labs.chat.graphql.RoomVO;
import org.springframework.core.convert.converter.Converter;

public class RoomToRoomVOConverter implements Converter<Room, RoomVO> {
    @Override
    public RoomVO convert(Room source) {
        return RoomVO.builder()
                .withId(source.getId())
                .withName(source.getName())
                .build();
    }
}