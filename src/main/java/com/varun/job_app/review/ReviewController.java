package com.varun.job_app.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getReviews(companyId);
        if (reviews == null || reviews.isEmpty())   return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                            @PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId, reviewId);
        if (review == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId,
                                            @RequestBody Review review){
        boolean isReviewAdd = reviewService.addReview(companyId, review);
        if (!isReviewAdd)   return new ResponseEntity<>("company doesn't exists",
                                                        HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Review added successfully",
                                    HttpStatus.CREATED);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if (!isReviewUpdated)   return new ResponseEntity<>("either of company or review is not present",
                                                            HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("review updated successfully",
                                    HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> removeReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean isReviewRemoved = reviewService.removeReview(companyId, reviewId);
        if (!isReviewRemoved)   return new ResponseEntity<>("either of company or review if not present",
                                                            HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("review removed successfully",
                                    HttpStatus.OK);
    }
}
