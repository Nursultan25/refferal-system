package kg.megacom.referalsystem.mappers;

import kg.megacom.referalsystem.model.dto.SubscriberDto;
import kg.megacom.referalsystem.model.entity.Subscriber;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriberMapper extends BaseMapper<Subscriber, SubscriberDto> {
    SubscriberMapper INSTANCE = Mappers.getMapper(SubscriberMapper.class);
}
