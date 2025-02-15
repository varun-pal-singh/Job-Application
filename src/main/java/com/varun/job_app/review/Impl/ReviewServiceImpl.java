package com.varun.job_app.review.Impl;

import com.varun.job_app.company.Company;
import com.varun.job_app.company.CompanyService;
import com.varun.job_app.review.Review;
import com.varun.job_app.review.ReviewRepository;
import com.varun.job_app.review.ReviewService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompany(companyId);
        if (company == null)    return null;
        return company.getReviews().stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean addReview(Long companyId, @NotNull Review review) {
        Company company = companyService.getCompany(companyId);
        if (company == null)    return false;
        review.setCompany(company);
        reviewRepository.save(review);
        return true;
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Company company = companyService.getCompany(companyId);
        if (company == null)    return false;
//        Optional<Review> curReviewOptional = reviewRepository.findById(reviewId);
//        if (curReviewOptional.isEmpty())    return false;
//        Review curReview = curReviewOptional.get();

        Review curReview = company.getReviews().stream()
                                    .filter(review -> review.getId().equals(reviewId))
                                    .findFirst().orElse(null);

        if (curReview == null)  return false;

        if (updatedReview.getTitle() != null)           curReview.setTitle(updatedReview.getTitle());
        if (updatedReview.getDescription() != null)     curReview.setDescription(updatedReview.getDescription());
        if (updatedReview.getRating() != null)          curReview.setRating(updatedReview.getRating());
        if (updatedReview.getCompany() != null)         curReview.setCompany(updatedReview.getCompany());

        reviewRepository.save(curReview);
        return true;
    }

    @Override
    public boolean removeReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompany(companyId);
        if (company == null)    return false;
        Review curReview = company.getReviews().stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst().orElse(null);

        if (curReview == null)  return false;

        company.getReviews().remove(curReview);
        companyService.updateCompany(companyId, company);
        reviewRepository.deleteById(reviewId);
        return true;
    }
}
