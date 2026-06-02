package joaohotz.rm556402.orbitalsense.services;

import jakarta.persistence.EntityNotFoundException;
import joaohotz.rm556402.orbitalsense.dtos.LeituraColetadaDto;
import joaohotz.rm556402.orbitalsense.dtos.SateliteDto;
import joaohotz.rm556402.orbitalsense.entities.LeituraColetada;
import joaohotz.rm556402.orbitalsense.entities.Satelite;
import joaohotz.rm556402.orbitalsense.enums.Status;
import joaohotz.rm556402.orbitalsense.exceptions.ResourceNotFoundException;
import joaohotz.rm556402.orbitalsense.repositories.LeituraColetadaRepository;
import joaohotz.rm556402.orbitalsense.repositories.SateliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SateliteService {
    @Autowired
    private SateliteRepository sateliteRepository;


    @Transactional
    public SateliteDto saveSatelite(SateliteDto sateliteDto) {
        Satelite satelite = new Satelite();
        satelite.setDataLancamento(LocalDate.now());
        satelite.setStatus(Status.ATIVO);
        copyDtoToSatelite(sateliteDto, satelite);
        satelite = sateliteRepository.save(satelite);
        return new SateliteDto(satelite);
    }

    @Transactional
    public List<SateliteDto> findAllSatelites() {
        return sateliteRepository.findAll().stream().map(SateliteDto::new).toList();

    }

    @Transactional
    public SateliteDto findSateliteById(Long id) {
        Satelite satelite = sateliteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso n~ao encontrado ID " + id));
        return new SateliteDto(satelite);
    }

    @Transactional
    public SateliteDto updateSatelite(Long id, SateliteDto sateliteDto) {
        try {
            Satelite satelite = sateliteRepository.getReferenceById(id);
            satelite.getLeituras().clear();
            satelite.setDataLancamento(LocalDate.now());
            copyDtoToSatelite(sateliteDto, satelite);
            satelite = sateliteRepository.save(satelite);
            return new SateliteDto(satelite);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso nao encontrado ID " + id);
        }
    }

    @Transactional
    public void deleteSateliteById(Long id) {
        if (!sateliteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso nao encontrado ID " + id);
        }
        sateliteRepository.deleteById(id);
    }

    private void copyDtoToSatelite(SateliteDto sateliteDto, Satelite satelite) {
        satelite.setNome(sateliteDto.getNome());
        satelite.setAgencia(sateliteDto.getAgencia());
        satelite.setTipo(sateliteDto.getTipo());
        satelite.setAltitudeKm(sateliteDto.getAltitudeKm());
        satelite.setStatus(sateliteDto.getStatus());

        for (LeituraColetadaDto leituraColetadaDto: sateliteDto.getLeituras()){
            LeituraColetada leituraColetada = new LeituraColetada();
            leituraColetada.setTipoMedicao(leituraColetadaDto.getTipoMedicao());
            leituraColetada.setValorMedido(leituraColetadaDto.getValorMedido());
            leituraColetada.setUnidade(leituraColetadaDto.getUnidade());
            leituraColetada.setDataLeitura(leituraColetadaDto.getDataLeitura());
            leituraColetada.setSatelite(satelite);
            satelite.getLeituras().add(leituraColetada);
        }
    }
}
