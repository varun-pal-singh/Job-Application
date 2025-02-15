package com.varun.job_app.review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Long companyId);

    Review getReview(Long companyId, Long reviewId);

    boolean addReview(Long companyId, Review review);

    boolean updateReview(Long companyId, Long reviewId, Review review);

    boolean removeReview(Long companyId, Long reviewId);
}
