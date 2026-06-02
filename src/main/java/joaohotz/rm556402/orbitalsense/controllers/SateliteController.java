package joaohotz.rm556402.orbitalsense.controllers;

import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import joaohotz.rm556402.orbitalsense.dtos.SateliteDto;
import joaohotz.rm556402.orbitalsense.repositories.SateliteRepository;
import joaohotz.rm556402.orbitalsense.services.SateliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/satelites")
public class SateliteController {

    @Autowired
    private SateliteService sateliteService;

    @GetMapping
    public ResponseEntity<List<SateliteDto>> getSatelites() {
        List<SateliteDto> sateliteDtos = sateliteService.findAllSatelites();
        return ResponseEntity.ok(sateliteDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SateliteDto> getSateliteById(@PathVariable long id) {
        SateliteDto sateliteDto = sateliteService.findSateliteById(id);
        return ResponseEntity.ok(sateliteDto);
    }

    @PostMapping
    public ResponseEntity<SateliteDto> saveSatelite(@RequestBody @Valid SateliteDto sateliteDto, ServletRequest servletRequest) {
        sateliteDto = sateliteService.saveSatelite(sateliteDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(sateliteDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(sateliteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SateliteDto> updateSatelite(@PathVariable Long id, @Valid @RequestBody SateliteDto sateliteDto){
        sateliteDto = sateliteService.updateSatelite(id, sateliteDto);
        return ResponseEntity.ok(sateliteDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SateliteDto> deleteSatelite(@PathVariable Long id){
        sateliteService.deleteSateliteById(id);
        return ResponseEntity.noContent().build();
    }
}
