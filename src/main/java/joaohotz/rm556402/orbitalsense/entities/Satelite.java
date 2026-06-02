package joaohotz.rm556402.orbitalsense.entities;

import jakarta.persistence.*;
import joaohotz.rm556402.orbitalsense.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_satelite")
public class Satelite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Column(name = "AGENCIA", nullable = false, length = 100)
    private String agencia;

    @Column(name = "TIPO", nullable = false, length = 80)
    private String tipo;

    @Column(name = "ALTITUDE_KM", nullable = false)
    private double altitudeKm;

    @Column(name = "DATA_LANCAMENTO", nullable = false)
    private LocalDate dataLancamento;

    @Column(name = "STATUS_SAT", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "satelite")
    private List<LeituraColetada> leituras = new ArrayList<>();

}
