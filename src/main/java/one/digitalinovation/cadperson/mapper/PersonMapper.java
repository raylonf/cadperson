package one.digitalinovation.cadperson.mapper;

import one.digitalinovation.cadperson.dto.request.PersonDTO;
import one.digitalinovation.cadperson.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    default Person toModel(PersonDTO personDTO) {
        return null;
    }

    PersonDTO toDTO(Person person);

}
