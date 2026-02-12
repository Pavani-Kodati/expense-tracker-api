package com.pavani.expensetracker;

import com.pavani.expensetracker.model.Expense;
import com.pavani.expensetracker.repository.ExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ExpenseRepository expenseRepository;

    public DataLoader(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void run(String... args) {
        expenseRepository.save(new Expense("Groceries", new BigDecimal("85.50"), "Food", LocalDate.now().minusDays(1), "Weekly grocery shopping"));
        expenseRepository.save(new Expense("Netflix", new BigDecimal("15.99"), "Entertainment", LocalDate.now().minusDays(3), "Monthly subscription"));
        expenseRepository.save(new Expense("Gas", new BigDecimal("60.00"), "Transport", LocalDate.now().minusDays(2), "Full tank"));
        expenseRepository.save(new Expense("Electricity Bill", new BigDecimal("120.00"), "Utilities", LocalDate.now().minusDays(5), "Monthly bill"));
        expenseRepository.save(new Expense("Lunch", new BigDecimal("18.75"), "Food", LocalDate.now(), "Work lunch"));
    }
}
