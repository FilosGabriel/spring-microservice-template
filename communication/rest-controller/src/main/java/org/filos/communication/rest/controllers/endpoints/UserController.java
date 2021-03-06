package org.filos.communication.rest.controllers.endpoints;

import org.filos.bussiness.SearchUserService;
import org.filos.shared.web.dto.rest.response.UserResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final SearchUserService searchUserService;

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable String id) {
        return searchUserService.findByUserId(id);
    }

    @GetMapping("/exists")
    public void checkExistence(@RequestParam(name = "email", required = true) String email) {
        searchUserService.existsByEmail(email);
    }
}
