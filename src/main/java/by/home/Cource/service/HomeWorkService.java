package by.home.Cource.service;

import by.home.Cource.entity.HomeWork;
import by.home.Cource.entity.User;
import by.home.Cource.entity.dto.HomeWorkDto;
import by.home.Cource.entity.dto.UserDto;
import by.home.Cource.entity.mapper.HomeWorkMapper;
import by.home.Cource.entity.mapper.UserMapper;
import by.home.Cource.repository.HomeWorkRepository;
import by.home.Cource.repository.UserRepository;
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
