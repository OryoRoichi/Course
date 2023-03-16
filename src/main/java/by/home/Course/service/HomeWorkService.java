package by.home.Course.service;

import by.home.Course.entity.HomeWork;
import by.home.Course.entity.dto.HomeWorkDto;
import by.home.Course.entity.mapper.HomeWorkMapper;
import by.home.Course.repository.HomeWorkRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class HomeWorkService {

    HomeWorkRepository homeWorkRepository;
    HomeWorkMapper homeWorkMapper;

    public HomeWorkDto createHomeWork(HomeWorkDto request) {
        HomeWork homeWorkToSave = homeWorkMapper.toEntity(request);
        homeWorkRepository.save(homeWorkToSave);
        return homeWorkMapper.ToDto(homeWorkToSave);
    }
}
