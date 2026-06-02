package joaohotz.rm556402.orbitalsense.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import joaohotz.rm556402.orbitalsense.entities.LeituraColetada;
import joaohotz.rm556402.orbitalsense.entities.Satelite;
import joaohotz.rm556402.orbitalsense.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class  SateliteDto {

    private Long id;

    @NotBlank(message = "O nome do satélite é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "A agência é obrigatória.")
    @Size(max = 100, message = "A agência deve ter no máximo 100 caracteres.")
    private String agencia;

    @NotBlank(message = "O tipo é obrigatório.")
    @Size(max = 80, message = "O tipo deve ter no máximo 80 caracteres.")
    private String tipo;

    @NotNull(message = "A altitude em km é obrigatória.")
    @Positive(message = "A altitude deve ser um valor positivo.")
    private Double altitudeKm;

    @PastOrPresent(message = "A data de lançamento não pode ser futura.")
    private LocalDate dataLancamento;

    @Enumerated(EnumType.STRING)
    private Status status;

    private List<LeituraColetadaDto> leituras = new ArrayList<>();


    public SateliteDto(Satelite satelite) {
        id = satelite.getId();
        nome = satelite.getNome();
        agencia = satelite.getAgencia();
        tipo = satelite.getTipo();
        altitudeKm = satelite.getAltitudeKm();
        dataLancamento = satelite.getDataLancamento();
        status = satelite.getStatus();

        for (LeituraColetada leitura: satelite.getLeituras()){
            LeituraColetadaDto leituraColetadaDto = new LeituraColetadaDto(leitura);
            leituras.add(leituraColetadaDto);
        }
    }
}
