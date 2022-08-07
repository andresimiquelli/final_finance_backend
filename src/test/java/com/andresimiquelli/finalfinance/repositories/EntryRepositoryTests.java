package com.andresimiquelli.finalfinance.repositories;

import com.andresimiquelli.finalfinance.entities.*;
import com.andresimiquelli.finalfinance.entities.enums.EntryType;
import com.andresimiquelli.finalfinance.entities.enums.PeriodStatus;
import com.andresimiquelli.finalfinance.entities.enums.RecurrencyStatus;
import com.andresimiquelli.finalfinance.entities.enums.UserStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EntryRepositoryTests {

    @Mock
    private EntryRepository entryRepository;

    User user;
    Wallet wallet;
    Group group;
    Recurrency recurrency;
    Period periodOpened;
    Period periodClosed;
    Entry entry;

    @BeforeEach
    public void mockEntyties() {
        user = new User(1, "John Brown", "john@gmail.com", "12345", UserStatus.ACTIVE);
        wallet = new Wallet(1, "Personal Finances", "Personal finances controll", 200.0, user);
        periodOpened = new Period(1, 2022, 8, 200.0, PeriodStatus.OPEN, wallet);
        periodClosed = new Period(1, 2022, 8, 200.0, PeriodStatus.CLOSE, wallet);
        group = new Group(1, "Default", "#000000", wallet);
        recurrency = new Recurrency(1, EntryType.CREDIT, 200.0, "Recurrency test", "Description recurrency test", RecurrencyStatus.ACTIVE, wallet, group);
        entry = new Entry(1, EntryType.CREDIT, 200.0, "Credit test", "Description test", periodClosed, group, recurrency, 1, 1, true);
    }

    @Test
    public void findByPeriodStatusAndRecurrencyIdSholdReturnOne() {
        entryRepository.save(entry);
        List<Entry> result = entryRepository.findByPeriodStatusAndRecurrencyId(PeriodStatus.OPEN.getCod(),recurrency.getId());
        assertEquals(1, result.size());
    }

    @Test
    public void findByPeriodStatusAndRecurrencyIdSholdReturnZero() {
        entryRepository.save(entry);
        List<Entry> result = entryRepository.findByPeriodStatusAndRecurrencyId(PeriodStatus.OPEN.getCod(),recurrency.getId());
        assertEquals(1, result.size());
    }
}
