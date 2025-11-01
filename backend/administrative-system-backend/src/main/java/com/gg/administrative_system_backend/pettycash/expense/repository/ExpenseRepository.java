package com.gg.administrative_system_backend.pettycash.expense.repository;

import com.gg.administrative_system_backend.pettycash.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

}
