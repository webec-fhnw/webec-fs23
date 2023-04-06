package ch.fhnw.webec.wishlist.service;

import ch.fhnw.webec.wishlist.data.CategoryRepository;
import ch.fhnw.webec.wishlist.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.unmodifiableList;

@Service
public class CategoryService {

    private final CategoryRepository repo;
    private final WishlistService wishlistService;

    public CategoryService(CategoryRepository repo, WishlistService wishlistService) {
        this.repo = repo;
        this.wishlistService = wishlistService;
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Optional<Category> findById(int id) {
        return repo.findById(id);
    }

    public Category save(Category category) {
        return repo.save(category);
    }

    public void delete(Category category) {
        wishlistService.findAll().stream()
                .flatMap(l -> l.getEntries().stream())
                .forEach(w -> w.getCategories().remove(category));
        repo.delete(category);
    }
}
