package com.example.opombo.model.entity;

import com.example.opombo.model.dto.DenunciaDTO;
import com.example.opombo.model.enums.Motivo;
import com.example.opombo.model.enums.Papel;
import com.example.opombo.model.enums.Situacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Data
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @UuidGenerator
    private String id;

    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @NotNull(message = "A denuncia deve possuir um motivo!")
    @Enumerated(EnumType.STRING)
    private Motivo motivo;

    @Enumerated(EnumType.STRING)
    private Situacao situacao = Situacao.PENDENTE; // Se o valor não for informado, o padrão é PENDENTE

    @CreationTimestamp
    private LocalDateTime criadoEm;

    public static DenunciaDTO toDTO(String publicacaoId, int totalDenuncias, int denunciasPendentes, int denunciasRecusadas, int denunciasAceitadas) {
        DenunciaDTO dto = new DenunciaDTO(publicacaoId, totalDenuncias, denunciasPendentes, denunciasRecusadas, denunciasAceitadas);
        return dto;
    }
}