package am.aca.classwork.builder;

import am.aca.classwork.dto.ReviewResponseDto;
import am.aca.classwork.entity.book.Review;

public class ReviewResponseBuilder {
    public static ReviewResponseDto buildReviewResponseDto(Review review) {
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setReviewId(review.getId());
        reviewResponseDto.setRate(review.getRate());
        reviewResponseDto.setUsername(review.getUser().getUsername());
        reviewResponseDto.setBookName(review.getBook().getName());
        return reviewResponseDto;
    }
}
