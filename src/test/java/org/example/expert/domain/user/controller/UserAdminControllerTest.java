package org.example.expert.domain.user.controller;

import org.example.expert.domain.user.dto.request.UserRoleChangeRequest;
import org.example.expert.domain.user.service.UserAdminService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserAdminController.class)
class UserAdminControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserAdminService userAdminService;

    @Test
    @DisplayName("유저 역할 변환이 성공적으로 되는지 확인")
    void changeUserRole() throws Exception {

        //given
        long userId = 1L;
        UserRoleChangeRequest userRoleChangeRequest = new UserRoleChangeRequest("ADMIN");



    }
}