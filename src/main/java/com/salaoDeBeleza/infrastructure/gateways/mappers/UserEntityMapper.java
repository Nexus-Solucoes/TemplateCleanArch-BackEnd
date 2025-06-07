package com.salaoDeBeleza.infrastructure.gateways.mappers;

import com.salaoDeBeleza.domain.entity.UserDomain;
import com.salaoDeBeleza.infrastructure.persistence.User.UserEntity;

public class UserEntityMapper {
    public UserEntity toEntity(UserDomain userDomainObject) {
        return new UserEntity(userDomainObject.username(), userDomainObject.password(), userDomainObject.email());
    }

    public UserDomain toDomainObject(UserEntity userEntity) {
        return new UserDomain(userEntity.getUsername(), userEntity.getPassword(), userEntity.getEmail());
    }
}
