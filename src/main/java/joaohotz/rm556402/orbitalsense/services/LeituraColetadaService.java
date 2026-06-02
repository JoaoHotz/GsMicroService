package joaohotz.rm556402.orbitalsense.services;

import jakarta.persistence.EntityNotFoundException;
import joaohotz.rm556402.orbitalsense.dtos.LeituraColetadaDto;
import joaohotz.rm556402.orbitalsense.dtos.SateliteDto;
import joaohotz.rm556402.orbitalsense.entities.LeituraColetada;
import joaohotz.rm556402.orbitalsense.entities.Satelite;
import joaohotz.rm556402.orbitalsense.enums.Status;
import joaohotz.rm556402.orbitalsense.exceptions.DatabaseException;
import joaohotz.rm556402.orbitalsense.exceptions.ResourceNotFoundException;
import joaohotz.rm556402.orbitalsense.repositories.LeituraColetadaRepository;
import joaohotz.rm556402.orbitalsense.repositories.SateliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class LeituraColetadaService {
    @Autowired
    public LeituraColetadaRepository leituraColetadaRepository;

    @Autowired
    public SateliteRepository sateliteRepository;

    @Transactional
    public List<LeituraColetadaDto> findAllLeituraColetada() {
        return leituraColetadaRepository.findAll().stream().map(LeituraColetadaDto::new).toList();
    }

    @Transactional
    public LeituraColetadaDto findLeituraColetadaById(Long id) {
    LeituraColetada leituraColetada = leituraColetadaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso nao encontrado ID " + id));
    return new LeituraColetadaDto(leituraColetada);
    }

    @Transactional
    public LeituraColetadaDto saveLeituraColetada(LeituraColetadaDto leituraColetadaDto) {
        try {
            LeituraColetada leituraColetada = new LeituraColetada();
            leituraColetada.setDataLeitura(LocalDate.now());
            copyDtoToLeituraColetada(leituraColetadaDto, leituraColetada);
            leituraColetada = leituraColetadaRepository.save(leituraColetada);
            return new LeituraColetadaDto(leituraColetada);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível salvar a leitura. Satelite inexistente. ID: " + leituraColetadaDto.getSateliteId());
        }
    }

    @Transactional
    public LeituraColetadaDto updateLeituraColetada(Long id, LeituraColetadaDto leituraColetadaDto) {
        try {
            LeituraColetada leituraColetada = leituraColetadaRepository.getReferenceById(id);
            leituraColetada.setDataLeitura(LocalDate.now());
            copyDtoToLeituraColetada(leituraColetadaDto, leituraColetada);
            leituraColetada = leituraColetadaRepository.save(leituraColetada);
            return new LeituraColetadaDto(leituraColetada);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso nao encontrado ID " + id);
        }
    }

    @Transactional
    public void deleteLeituraColetada(Long id) {
        if (!leituraColetadaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso nao encontrado ID " + id);
        }
        leituraColetadaRepository.deleteById(id);
    }


    private void copyDtoToLeituraColetada(LeituraColetadaDto leituraColetadaDto, LeituraColetada leituraColetada) {
        leituraColetada.setTipoMedicao(leituraColetadaDto.getTipoMedicao());
        leituraColetada.setValorMedido(leituraColetadaDto.getValorMedido());
        leituraColetada.setUnidade(leituraColetadaDto.getUnidade());
        leituraColetada.setLatitude(leituraColetadaDto.getLatitude());
        leituraColetada.setLongitude(leituraColetadaDto.getLongitude());

        Satelite satelite = sateliteRepository.getReferenceById(leituraColetadaDto.getSateliteId());
        leituraColetada.setSatelite(satelite);

    }


}
