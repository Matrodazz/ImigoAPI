package imigo.api.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import imigo.api.controllers.EnderecoController;
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

public class Endereco {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double numero;

    @NotNull
    private String cep;

    private String ponto_referencia;

    @NotNull
    private String logradouro;

    @NotNull
    private String bairro;

    @NotNull
    private String cidade;

    @NotNull
    private String estado;

    @NotNull
    private String regiao;

    public EntityModel<Endereco> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(EnderecoController.class).show(id)).withSelfRel(),
            linkTo(methodOn(EnderecoController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(EnderecoController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }

}