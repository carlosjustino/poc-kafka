package eti.justino.poc.kafka.mapper;

import eti.justino.poc.kafka.model.Message;
import eti.justino.poc.kafka.model.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MessageMapper {

    Message messageDtoToMessage(MessageDto dto);


}
