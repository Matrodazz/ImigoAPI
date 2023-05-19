package imigo.api.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;

import imigo.api.controllers.SuporteController;
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

public class Suporte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descricao;

    @NotNull
    private LocalDate data_inicio;

    @NotNull
    private LocalDate data_fim;

    @NotNull
    private int prioridade;

    public EntityModel<Suporte> toEntityModel(){
        return EntityModel.of(
            this, 
            linkTo(methodOn(SuporteController.class).show(id)).withSelfRel(),
            linkTo(methodOn(SuporteController.class).destroy(id)).withRel("delete"),
            linkTo(methodOn(SuporteController.class).index(null, Pageable.unpaged())).withRel("all")
        );

    }


}
