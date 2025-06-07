package com.salaoDeBeleza.infrastructure.gateways;

import com.salaoDeBeleza.application.gateways.UserGateway;
import com.salaoDeBeleza.domain.entity.UserDomain;
import com.salaoDeBeleza.infrastructure.gateways.mappers.UserEntityMapper;
import com.salaoDeBeleza.infrastructure.persistence.User.UserEntity;
import com.salaoDeBeleza.infrastructure.persistence.User.UserRepository;

public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserDomain createUser(UserDomain user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        return userEntityMapper.toDomainObject(userRepository.save(userEntity));
    }
}
