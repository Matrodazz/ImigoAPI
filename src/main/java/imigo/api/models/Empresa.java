package imigo.api.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import imigo.api.controllers.EmpresaController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Empresa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private String cnpj_inscricao;

    public EntityModel<Empresa> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(EmpresaController.class).show(id)).withSelfRel(),
            linkTo(methodOn(EmpresaController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(EmpresaController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }



}