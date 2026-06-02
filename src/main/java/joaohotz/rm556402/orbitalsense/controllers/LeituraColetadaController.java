package joaohotz.rm556402.orbitalsense.controllers;

import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import joaohotz.rm556402.orbitalsense.dtos.LeituraColetadaDto;
import joaohotz.rm556402.orbitalsense.services.LeituraColetadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}
