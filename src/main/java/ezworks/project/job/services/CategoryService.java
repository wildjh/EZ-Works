package ezworks.project.job.services;

import org.springframework.stereotype.Service;

import ezworks.project.job.entities.Category;
import ezworks.project.job.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // Inyección de dependencias mediante el constructor
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Método para buscar todas las categorías
    public List<Category> buscarTodas() {
        return categoryRepository.findAll();
    }

    // Método para guardar una nueva categoría
    public void guardar(Category category) {
        categoryRepository.save(category);
    }

    // Método para buscar una categoría específica por su ID
    public Category buscarPorId(Integer id) {
        Optional<Category> opcional = categoryRepository.findById(id);
        return opcional.orElse(null); // Retorna nulo si no la encuentra
    }
}