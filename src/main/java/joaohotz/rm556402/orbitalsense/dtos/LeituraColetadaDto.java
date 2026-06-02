package joaohotz.rm556402.orbitalsense.dtos;

import jakarta.validation.constraints.*;
import joaohotz.rm556402.orbitalsense.entities.LeituraColetada;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeituraColetadaDto {

    private Long id;

    @NotBlank(message = "O tipo de medição é obrigatório.")
    @Size(max = 60, message = "O tipo de medição deve ter no máximo 60 caracteres.")
    private String tipoMedicao;

    @NotNull(message = "O valor medido é obrigatório.")
    private Double valorMedido;

    @NotBlank(message = "A unidade é obrigatória.")
    @Size(max = 20, message = "A unidade deve ter no máximo 20 caracteres.")
    private String unidade;


    @PastOrPresent(message = "A data da leitura não pode ser futura.")
    private LocalDate dataLeitura;

    @NotNull(message = "A latitude é obrigatória.")
    @DecimalMin(value = "-90.0", message = "a latitude deve estar entre -90 e 90")
    @DecimalMax(value = "90.0", message = "a latitude deve estar entre -90 e 90")
    private Double latitude;

    @NotNull(message = "A longitude é obrigatória.")
    @DecimalMin(value = "-180.0", message = "a longitude deve estar entre -180 e 180")
    @DecimalMax(value = "180.0", message = "a longitude deve estar entre -180 e 180")
    private Double longitude;


    private Long sateliteId;


    public LeituraColetadaDto(LeituraColetada leituraColetada) {
        id = leituraColetada.getId();
        tipoMedicao = leituraColetada.getTipoMedicao();
        valorMedido = leituraColetada.getValorMedido();
        unidade = leituraColetada.getUnidade();
        dataLeitura = leituraColetada.getDataLeitura();
        latitude = leituraColetada.getLatitude();
        longitude = leituraColetada.getLongitude();
        sateliteId = leituraColetada.getSatelite().getId();

    }
}
