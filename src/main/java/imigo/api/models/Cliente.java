package imigo.api.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;



import imigo.api.controllers.ClienteController;
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

public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String senha;

    @NotNull
    private String email;

    private String genero;

    @NotNull
    private String cpf;

    public EntityModel<Cliente> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(ClienteController.class).show(id)).withSelfRel(),
            linkTo(methodOn(ClienteController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(ClienteController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}