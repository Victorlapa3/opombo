package com.example.opombo.model.seletor;

import com.example.opombo.model.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UsuarioSeletor extends BaseSeletor implements Specification<Usuario> {

    private String nome;
    private String email;
    private LocalDateTime criadoEmInicio;
    private LocalDateTime criadoEmFim;

    @Override
    public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicados = new ArrayList<>();

        if (this.getNome() != null && !this.getNome().trim().isEmpty()) {
            predicados.add(cb.like(root.get("nome"), "%" + this.getNome() + "%"));
        }

        if (this.getEmail() != null && !this.getEmail().trim().isEmpty()) {
            predicados.add(cb.like(root.get("email"), "%" + this.getEmail() + "%"));
        }

        aplicarFiltroPorData(root, cb, predicados, this.getCriadoEmInicio(), this.getCriadoEmFim(), "criadoEm");

        return cb.and(predicados.toArray(new Predicate[0]));
    }
}