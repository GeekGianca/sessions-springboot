package com.example.session;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.session.app.service.SessionService;
import com.example.session.core.model.SessionEntity;
import com.example.session.core.usecase.ICreateSessionUseCase;
import com.example.session.core.usecase.IFindAllSessionUseCase;
import com.example.session.core.usecase.ILogoutUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private ICreateSessionUseCase createSessionUseCase;

    @Mock
    private ILogoutUseCase logoutUseCase;

    @Mock
    private IFindAllSessionUseCase findAllSessionUseCase;

    @InjectMocks
    private SessionService sessionService;

    private SessionEntity mockSession;

    @BeforeEach
    void setUp() {
        mockSession = new SessionEntity();
    }

    @Test
    void create_ShouldReturnSessionEntity() throws Throwable {
        when(createSessionUseCase.execute()).thenReturn(mockSession);

        SessionEntity result = sessionService.create();

        assertNotNull(result);
        assertEquals(mockSession, result);
        verify(createSessionUseCase, times(1)).execute();
    }

    @Test
    void logout_ShouldCallLogoutUseCase() throws Throwable {
        HttpServletRequest request = mock(HttpServletRequest.class);

        sessionService.logout(request);

        verify(logoutUseCase, times(1)).execute(request);
    }

    @Test
    void getSessions_ShouldReturnSessionList() {
        List<SessionEntity> sessions = Arrays.asList(new SessionEntity(), new SessionEntity());
        when(findAllSessionUseCase.execute()).thenReturn(sessions);

        List<SessionEntity> result = sessionService.getSessions();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(findAllSessionUseCase, times(1)).execute();
    }
}

