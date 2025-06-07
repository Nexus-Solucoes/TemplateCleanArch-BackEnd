package com.salaoDeBeleza.application.usecases;

import com.salaoDeBeleza.application.gateways.UserGateway;
import com.salaoDeBeleza.domain.entity.UserDomain;
import org.springframework.stereotype.Service;

@Service
public class CreateUserInteractor {

    private final UserGateway userGateway;
    public CreateUserInteractor(UserGateway userGateway){
        this.userGateway = userGateway;
    }

    public UserDomain createUser(UserDomain user) {
        return userGateway.createUser(user);
    }
}
