package org.filos.bussiness;

import org.filos.shared.web.dto.rest.response.UserResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SearchUserService {

    public UserResponse findByUserId(@NonNull String userId) {
        return new UserResponse("1", "firstName", "lastName", "email", "dasd", "d");
    }

    public UserResponse findByEmail(String email) {
        return null;
    }

    public boolean existsByEmailAndPassword(String email, String password) {
        return false;
    }

    public boolean existsByEmail(String email) {
        return false;
    }
}
