package com.exo2.Exercice2.service;

import com.exo2.Exercice2.dto.EtudiantDto;
import com.exo2.Exercice2.entity.Etudiant;
import com.exo2.Exercice2.entity.Projet;
import com.exo2.Exercice2.mapper.EtudiantMapper;
import com.exo2.Exercice2.repository.EtudiantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;

    public List<EtudiantDto> findAll() {
        return etudiantMapper.toDtos(etudiantRepository.findAll());
    }

    public EtudiantDto findById(Long id) {
        return etudiantMapper.toDto(etudiantRepository.findById(id).orElse(null));
    }

    public EtudiantDto save(EtudiantDto etudiantDto) {
        Etudiant etudiant = etudiantMapper.toEntity(etudiantDto);
        for(Projet projet : etudiant.getProjets())
        {
            projet.getEtudiants().add(etudiant);
        }
        return etudiantMapper.toDto(etudiantRepository.save(etudiant));
    }

    public List<EtudiantDto> findByNom(String nom) {
        return etudiantMapper.toDtos(etudiantRepository.findByNom(nom));
    }
}
