package by.home.Cource.service;

import by.home.Cource.entity.Cource;
import by.home.Cource.entity.User;
import by.home.Cource.entity.dto.CourceDto;
import by.home.Cource.entity.mapper.CourceMapper;
import by.home.Cource.repository.CourceRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CourceService {
    CourceRepository courceRepository;

    CourceMapper courceMapper;

    public CourceDto createCource(CourceDto request){
        Cource courceToSave = courceMapper.toEntity(request);
        courceRepository.save(courceToSave);
        return courceMapper.ToDto(courceToSave);
    }
}
