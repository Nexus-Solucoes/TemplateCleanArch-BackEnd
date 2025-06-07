package com.salaoDeBeleza.infrastructure.controllers.User;

import com.salaoDeBeleza.application.usecases.CreateUserInteractor;
import com.salaoDeBeleza.domain.entity.UserDomain;
import com.salaoDeBeleza.infrastructure.controllers.User.dto.CreateUserRequest;
import com.salaoDeBeleza.infrastructure.controllers.User.dto.CreateUserResponse;
import com.salaoDeBeleza.infrastructure.controllers.User.dto.mapper.UserDTOMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserInteractor createUserInteractor;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUserInteractor createUserInteractor, UserDTOMapper userDTOMapper) {
        this.createUserInteractor = createUserInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest userRequest) {
        UserDomain userObject = userDTOMapper.toUser(userRequest);
        return userDTOMapper.toResponse(createUserInteractor.createUser(userObject));
    }
}
