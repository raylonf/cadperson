package one.digitalinovation.cadperson.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

//@Table(indexes = {
//        @Index(name = "idx_persondto_birthdate", columnList = "birthDate")
//})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String lastName;

    @CPF
    @NotEmpty
    private String cpf;

    private String birthDate;

    @NotEmpty
    @Valid
    private List<PhoneDTO> phones;

}
