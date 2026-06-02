package joaohotz.rm556402.orbitalsense.controllers;

import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import joaohotz.rm556402.orbitalsense.dtos.LeituraColetadaDto;
import joaohotz.rm556402.orbitalsense.dtos.SateliteDto;
import joaohotz.rm556402.orbitalsense.services.LeituraColetadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/leiturasColetadas")
public class LeituraColetadaController {
    @Autowired
    private LeituraColetadaService leituraColetadaService;

    @PostMapping
    public ResponseEntity<LeituraColetadaDto> saveLeituraColetada(@RequestBody @Valid LeituraColetadaDto leituraColetadaDto, ServletRequest servletRequest) {
        leituraColetadaDto = leituraColetadaService.saveLeituraColetada(leituraColetadaDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(leituraColetadaDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(leituraColetadaDto);
    }

    @GetMapping
    public ResponseEntity<List<LeituraColetadaDto>> getLeituraColetadas() {
        List<LeituraColetadaDto> leituraColetadaDtos = leituraColetadaService.findAllLeituraColetada();
        return ResponseEntity.ok(leituraColetadaDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeituraColetadaDto> getLeituraColetadaById(@PathVariable Long id) {
        LeituraColetadaDto leituraColetadaDto = leituraColetadaService.findLeituraColetadaById(id);
        return ResponseEntity.ok(leituraColetadaDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeituraColetadaDto> updateLeituraColetada(@PathVariable Long id, @Valid @RequestBody LeituraColetadaDto leituraColetadaDto) {
        leituraColetadaDto = leituraColetadaService.updateLeituraColetada(id, leituraColetadaDto);
        return ResponseEntity.ok(leituraColetadaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLeituraColetada(@PathVariable Long id) {
        leituraColetadaService.deleteLeituraColetada(id);
        return ResponseEntity.noContent().build();
    }
}
