package com.andresimiquelli.finalfinance.services;

import com.andresimiquelli.finalfinance.dto.UserDTO;
import com.andresimiquelli.finalfinance.entities.Group;
import com.andresimiquelli.finalfinance.entities.Recurrency;
import com.andresimiquelli.finalfinance.entities.User;
import com.andresimiquelli.finalfinance.entities.Wallet;
import com.andresimiquelli.finalfinance.entities.enums.EntryType;
import com.andresimiquelli.finalfinance.entities.enums.UserLevel;
import com.andresimiquelli.finalfinance.entities.enums.UserStatus;
import com.andresimiquelli.finalfinance.repositories.UserRepository;
import com.andresimiquelli.finalfinance.security.UserSpringSecurity;
import com.andresimiquelli.finalfinance.services.exceptions.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceUnitTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Authentication authenticationMock;

    @Mock
    SecurityContext securityContextMock;

    @InjectMocks
    private UserService userService;

    User activeUser;
    UserDTO activeUserDto;
    UserSpringSecurity userSpringSecurityAdmin;
    Wallet wallet;
    Group group;
    Recurrency creditRecurrency;

    @BeforeEach
    public void setup() {
        group = Group.builder()
                .id(1)
                .name("DEfault group")
                .color("red")
                .build();

        creditRecurrency = Recurrency.builder()
                .id(1)
                .title("Rent")
                .description("Rent of the ap")
                .type(EntryType.CREDIT.getCod())
                .amount(200.0)
                .build();

        wallet = Wallet.builder()
                .id(1)
                .name("Default Wallet")
                .groups(List.of(group))
                .recurrences(List.of(creditRecurrency))
                .build();

        activeUser = User.builder()
                .id(1)
                .name("John Brown")
                .email("john.brown@gmail.com")
                .password("12345")
                .status(UserStatus.ACTIVE.getCod())
                .wallets(List.of(wallet))
                .levels(Set.of(UserLevel.ADMIN.getCod()))
                .build();

        activeUserDto = new UserDTO(activeUser);
        userSpringSecurityAdmin = new UserSpringSecurity(1, "john.brown@gmail.com", "12345", Set.of(UserLevel.ADMIN));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserSholdReturnUser() {
        when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        SecurityContextHolder.setContext(securityContextMock);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userSpringSecurityAdmin);
        when(userRepository.findById(1)).thenReturn(Optional.of(activeUser));

        UserDTO result = userService.findById(1);

        assertEquals(activeUserDto, result);
    }

    @Test
    public void testGetUserSholdThrowsNotFoundException() {
        when(securityContextMock.getAuthentication()).thenReturn(authenticationMock);
        SecurityContextHolder.setContext(securityContextMock);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(userSpringSecurityAdmin);
        when(userRepository.findById(1)).thenReturn(Optional.of(activeUser));

        try {
            UserDTO result = userService.findById(2);
            Assert.fail();
        }catch (ResourceNotFoundException e) {
            assertEquals("Resource not found. Id: 2 Tipo: "+User.class.getName(), e.getMessage());
        }
    }
}
