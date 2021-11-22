package kg.megacom.referalsystem.mappers;

import kg.megacom.referalsystem.model.dto.InvitationDto;
import kg.megacom.referalsystem.model.entity.Invitation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvitationMapper extends BaseMapper<Invitation, InvitationDto> {
    InvitationMapper INSTANCE = Mappers.getMapper(InvitationMapper.class);
}
