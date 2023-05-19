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
import imigo.api.models.Cliente;
import imigo.api.repositories.ClienteRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/api/cliente")
public class ClienteController {

    Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteRepository repository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;
    @SecurityRequirement(name = "bearer-key")

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){
        Page<Cliente> clientes = (busca == null)?
        
        repository.findAll(pageable):
        repository.findByNomeContaining(busca, pageable);
        return assembler.toModel(clientes.map(Cliente::toEntityModel));
    }





    @PostMapping("/api/cliente")
    public ResponseEntity<Object> create(@RequestBody @Valid Cliente cliente){
        log.info("Cadastrando cliente" + cliente);
        repository.save(cliente);
        return ResponseEntity
        .created(cliente.toEntityModel().getRequiredLink("self").toUri())
        .body(cliente.toEntityModel());
    }


    @GetMapping("{id}")
    public ResponseEntity<Cliente> show(@PathVariable Long id) {
        log.info("Detalhando cliente" + id);
        var cliente = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("cliente não encontrado"));

        return ResponseEntity.ok(cliente);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Cliente> destroy(@PathVariable Long id) {
        log.info("Apagando cliente" + id);
        var cliente = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, cliente não encontrado"));

        repository.delete(cliente);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody @Valid Cliente cliente){
        log.info("Atualizando cliente" + id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, cliente não encontrado"));

        cliente.setId(id);
        repository.save(cliente);

        return ResponseEntity.ok(cliente);
        
    }
}
