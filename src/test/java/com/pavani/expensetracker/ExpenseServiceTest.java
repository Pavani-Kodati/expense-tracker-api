package com.pavani.expensetracker;

import com.pavani.expensetracker.model.Expense;
import com.pavani.expensetracker.repository.ExpenseRepository;
import com.pavani.expensetracker.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense sampleExpense;

    @BeforeEach
    void setUp() {
        sampleExpense = new Expense("Groceries", new BigDecimal("85.50"), "Food", LocalDate.now(), "Weekly shopping");
        sampleExpense.setId(1L);
    }

    @Test
    void getAllExpenses_ShouldReturnAllExpenses() {
        when(expenseRepository.findAll()).thenReturn(Arrays.asList(sampleExpense));
        List<Expense> result = expenseService.getAllExpenses();
        assertEquals(1, result.size());
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void getExpenseById_WhenExists_ShouldReturnExpense() {
        when(expenseRepository.findById(1L)).thenReturn(Optional.of(sampleExpense));
        Optional<Expense> result = expenseService.getExpenseById(1L);
        assertTrue(result.isPresent());
        assertEquals("Groceries", result.get().getTitle());
    }

    @Test
    void getExpenseById_WhenNotExists_ShouldReturnEmpty() {
        when(expenseRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Expense> result = expenseService.getExpenseById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    void createExpense_ShouldSaveAndReturnExpense() {
        when(expenseRepository.save(any(Expense.class))).thenReturn(sampleExpense);
        Expense result = expenseService.createExpense(sampleExpense);
        assertNotNull(result);
        assertEquals("Food", result.getCategory());
        verify(expenseRepository, times(1)).save(sampleExpense);
    }

    @Test
    void deleteExpense_WhenExists_ShouldDelete() {
        when(expenseRepository.existsById(1L)).thenReturn(true);
        doNothing().when(expenseRepository).deleteById(1L);
        assertDoesNotThrow(() -> expenseService.deleteExpense(1L));
        verify(expenseRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteExpense_WhenNotExists_ShouldThrowException() {
        when(expenseRepository.existsById(99L)).thenReturn(false);
        assertThrows(RuntimeException.class, () -> expenseService.deleteExpense(99L));
    }

    @Test
    void getTotalByCategory_WhenExpensesExist_ShouldReturnSum() {
        when(expenseRepository.sumByCategory("Food")).thenReturn(new BigDecimal("104.25"));
        BigDecimal total = expenseService.getTotalByCategory("Food");
        assertEquals(new BigDecimal("104.25"), total);
    }
}
