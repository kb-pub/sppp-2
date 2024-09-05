package JDBC.and.Boot.service;

import JDBC.and.Boot.domain.Author;
import JDBC.and.Boot.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    @Override
    public Collection<Author> findAllByNamePart(String namePart) {
        return authorRepository.findAllWithoutBooksByNamePart(namePart);
    }
}
