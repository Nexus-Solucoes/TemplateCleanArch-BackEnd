package com.salaoDeBeleza.main;

import com.salaoDeBeleza.application.gateways.UserGateway;
import com.salaoDeBeleza.application.usecases.CreateUserInteractor;
import com.salaoDeBeleza.infrastructure.controllers.User.dto.mapper.UserDTOMapper;
import com.salaoDeBeleza.infrastructure.gateways.UserRepositoryGateway;
import com.salaoDeBeleza.infrastructure.gateways.mappers.UserEntityMapper;
import com.salaoDeBeleza.infrastructure.persistence.User.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public CreateUserInteractor createUserInteractor(UserGateway userGateway) {
        return new CreateUserInteractor(userGateway);
    }

    @Bean
    public UserGateway userGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        return new UserRepositoryGateway(userRepository, userEntityMapper);
    }

    @Bean
    public UserEntityMapper userEntityMapper()  {
        return new UserEntityMapper();
    }

    @Bean
    public UserDTOMapper userDTOMapper()  {
        return new UserDTOMapper();
    }
}
