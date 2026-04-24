package com.demo.surefiretest.service;

import org.springframework.stereotype.Service;

/**
 * ResultService - Business Logic for Student Result Checking
 * 
 * This service contains the core logic to determine if a student passes or fails.
 * - If marks >= 40: Student PASSES
 * - If marks < 40: Student FAILS
 * 
 * @Service annotation makes this a Spring Bean (managed by Spring)
 */
@Service
public class ResultService {

    /**
     * Checks if the student passes or fails based on marks
     * 
     * @param marks - Student's marks (0-100)
     * @return "Pass" if marks >= 40, "Fail" if marks < 40
     */
    public String checkResult(int marks) {
        // Business rule: Passing marks is 40
        if (marks >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    /**
     * Gets the passing marks threshold
     * 
     * @return minimum marks required to pass (40)
     */
    public int getPassingMarks() {
        return 40;
    }
}
