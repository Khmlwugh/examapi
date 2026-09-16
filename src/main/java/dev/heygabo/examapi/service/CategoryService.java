package dev.heygabo.examapi.service;

import dev.heygabo.examapi.dto.CategoryResponse;
import dev.heygabo.examapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
            .map(c -> new CategoryResponse(c.getId(), c.getName()))
            .toList();
    }
}