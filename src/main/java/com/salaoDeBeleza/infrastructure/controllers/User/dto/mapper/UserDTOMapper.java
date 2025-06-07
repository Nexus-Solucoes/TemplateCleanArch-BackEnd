package com.salaoDeBeleza.infrastructure.controllers.User.dto.mapper;

import com.salaoDeBeleza.domain.entity.UserDomain;
import com.salaoDeBeleza.infrastructure.controllers.User.dto.CreateUserRequest;
import com.salaoDeBeleza.infrastructure.controllers.User.dto.CreateUserResponse;

public class UserDTOMapper {
    public CreateUserResponse toResponse(UserDomain user) {
        return new CreateUserResponse(user.username(), user.email());
    }

    public UserDomain toUser(CreateUserRequest request) {
        return new UserDomain(request.username(), request.password(), request.email());
    }
}
