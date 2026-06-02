package joaohotz.rm556402.orbitalsense.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
@Table(name = "tb_leitura_coleta")
public class LeituraColetada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "TIPO_MEDICAO", nullable = false, length = 60)
    private String tipoMedicao;

    @Column(name = "VALOR_MEDIDO", nullable = false)
    private Double valorMedido;

    @Column(name = "UNIDADE", nullable = false, length = 20)
    private String unidade;

    @Column(name = "DATA_LEITURA", nullable = false)
    private LocalDate dataLeitura;

    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "SATELITE_ID", nullable = false)
    private Satelite satelite;
}


