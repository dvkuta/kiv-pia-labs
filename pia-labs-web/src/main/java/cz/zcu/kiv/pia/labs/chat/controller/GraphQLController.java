package cz.zcu.kiv.pia.labs.chat.controller;

import cz.zcu.kiv.pia.labs.chat.converter.graphql.RoomToRoomVOConverter;
import cz.zcu.kiv.pia.labs.chat.domain.Room;
import cz.zcu.kiv.pia.labs.chat.graphql.RoomVO;
import cz.zcu.kiv.pia.labs.chat.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class GraphQLController {

    private final RoomService roomService;
    private final ConversionService conversionService;

    public GraphQLController(RoomService roomService, ConversionService conversionService) {
        this.roomService = roomService;
        this.conversionService = conversionService;
    }

    @QueryMapping
    public Flux<RoomVO> listRooms(@Argument String name) {
        return roomService.searchRooms(name)
                .map(room -> conversionService.convert(room, RoomVO.class));
    }
}