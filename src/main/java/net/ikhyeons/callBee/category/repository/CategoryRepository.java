package net.ikhyeons.callBee.category.repository;

import net.ikhyeons.callBee.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {}
