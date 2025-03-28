package com.example.session;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.session.app.dto.UserSignUpDto;
import com.example.session.app.service.UserService;
import com.example.session.core.model.UserEntity;
import com.example.session.core.usecase.IFindUserUseCase;
import com.example.session.core.usecase.ISaveUserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ISaveUserUseCase saveUserUseCase;

    @Mock
    private IFindUserUseCase findUserUseCase;

    @InjectMocks
    private UserService userService;

    private UserSignUpDto mockUserSignUpDto;
    private UserEntity mockUser;

    @BeforeEach
    void setUp() {
        mockUserSignUpDto = mock(UserSignUpDto.class);
        mockUser = new UserEntity();
    }

    @Test
    void create_ShouldReturnUserEntity() {
        when(mockUserSignUpDto.toUser(passwordEncoder)).thenReturn(mockUser);
        when(saveUserUseCase.execute(mockUser)).thenReturn(mockUser);

        UserEntity result = userService.create(mockUserSignUpDto);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(saveUserUseCase, times(1)).execute(mockUser);
    }

    @Test
    void findUser_ShouldReturnUserEntity() {
        long userId = 1L;
        when(findUserUseCase.findUserById(userId)).thenReturn(mockUser);

        UserEntity result = userService.findUser(userId);

        assertNotNull(result);
        assertEquals(mockUser, result);
        verify(findUserUseCase, times(1)).findUserById(userId);
    }
}

