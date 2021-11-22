package kg.megacom.referalsystem.mappers;

public interface BaseMapper<ENTITY, DTO>{

    ENTITY toEntity(DTO dto);
    DTO toDto(ENTITY entity);

}
