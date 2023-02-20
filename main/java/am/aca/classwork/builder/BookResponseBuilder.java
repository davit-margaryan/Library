package am.aca.classwork.builder;

import am.aca.classwork.dto.BookResponseDto;
import am.aca.classwork.entity.book.Book;
import am.aca.classwork.entity.book.Review;

import java.util.List;
import java.util.OptionalDouble;

public final class BookResponseBuilder {

    public static BookResponseDto buildBookResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setBookName(book.getName());
        bookResponseDto.setRating(calculateRate(book));
        bookResponseDto.setAuthorName(book.getAuthor().getName());
        bookResponseDto.setId(book.getId());
        return bookResponseDto;
    }

    private static Double calculateRate(Book book) {
        List<Review> reviews = book.getReviews();
        OptionalDouble average = reviews.stream().mapToDouble(Review::getRate).average();
        return average.orElse(0);
    }
}
