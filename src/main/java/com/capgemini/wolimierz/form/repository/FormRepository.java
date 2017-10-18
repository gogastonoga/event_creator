package com.capgemini.wolimierz.form.repository;

import com.capgemini.wolimierz.form.Form;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<Form, Long> {
}
