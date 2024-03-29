package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.BookInformation;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    @Query("SELECT b.title FROM Book b WHERE b.ageRestriction = :restriction")
    List<String> findByAgeRestriction(AgeRestriction restriction);

    List<Book> findByEditionTypeAndCopiesLessThan(EditionType type, int copies);

    List<Book> findByPriceLessThanOrPriceGreaterThan(BigDecimal lowerBound, BigDecimal upperBound);

    List<Book> findByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    List<Book> findByTitleContaining(String containing);

    List<Book> findByAuthorLastNameStartingWith(String startsWith);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :length")
    int countBooksWithTitleLongerThan(int length);

    @Query("SELECT b.title AS title, b.editionType AS editionType," +
            " b.ageRestriction AS ageRestriction, b.price AS price FROM Book b" +
            " WHERE b.title = :title")
    BookInformation findSummaryForTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies * :amount WHERE b.releaseDate > :date")
    int addCopiesToBooksAfter(LocalDate date, int amount);

    @Transactional
    int deleteByCopiesLessThan(int amount);
}
