package imigo.api.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import imigo.api.exceptions.RestNotFoundException;
import imigo.api.models.Suporte;
import imigo.api.repositories.SuporteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/api/suporte")
public class SuporteController {

    Logger log = LoggerFactory.getLogger(SuporteController.class);

    @Autowired
    SuporteRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable) {
        Page<Suporte> suportes = (busca == null) ?
                repository.findAll(pageable) :
                repository.findByDescricaoContaining(busca, pageable);
        return assembler.toModel(suportes.map(Suporte::toEntityModel));
    }





    @PostMapping("/api/suporte")
    public ResponseEntity<Object> create(@RequestBody @Valid Suporte suporte){
        log.info("Cadastrando suporte" + suporte);
        repository.save(suporte);
        return ResponseEntity
        .created(suporte.toEntityModel().getRequiredLink("self").toUri())
        .body(suporte.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Suporte> show(@PathVariable Long id) {
        log.info("Detalhando suporte" + id);
        var suporte = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("suporte não encontrado"));

        return ResponseEntity.ok(suporte);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Suporte> destroy(@PathVariable Long id) {
        log.info("Apagando suporte" + id);
        var suporte = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, suporte não encontrado"));

        repository.delete(suporte);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Suporte> update(@PathVariable Long id, @RequestBody @Valid Suporte suporte){
        log.info("Atualizando suporte" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, suporte não encontrado"));

        suporte.setId(id);
        repository.save(suporte);

        return ResponseEntity.ok(suporte);
        
    }
}
