package com.salaoDeBeleza.application.gateways;

import com.salaoDeBeleza.domain.entity.UserDomain;

public interface UserGateway {
    UserDomain createUser(UserDomain user);
}
