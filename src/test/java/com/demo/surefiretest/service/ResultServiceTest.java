package com.demo.surefiretest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ResultServiceTest - JUnit Test Class for ResultService
 * 
 * This test class demonstrates Maven Surefire Plugin in action.
 * When you run 'mvn test', Surefire automatically discovers and runs these tests.
 * 
 * Test Cases:
 * 1. Test with marks 75 (should return "Pass")
 * 2. Test with marks 20 (should return "Fail")
 * 3. Test with marks 40 (boundary - should return "Pass")
 * 
 * @BeforeEach runs before each test method to set up test data
 * @Test marks methods as test cases
 */
class ResultServiceTest {

    // Instance of ResultService to test
    private ResultService resultService;

    /**
     * Setup method - runs before each test
     * Creates a fresh instance of ResultService for each test
     */
    @BeforeEach
    void setUp() {
        resultService = new ResultService();
    }

    /**
     * Test Case 1: Student with 75 marks should PASS
     * 
     * Expected Result: "Pass"
     * This tests a typical passing scenario
     */
    @Test
    void testCheckResultWithPassingMarks() {
        // Given: Student has 75 marks
        int marks = 75;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Pass"
        assertEquals("Pass", result, "Student with 75 marks should PASS");
        
        // Print to console for presentation visibility
        System.out.println("Test 1 PASSED: Marks = 75, Result = " + result);
    }

    /**
     * Test Case 2: Student with 20 marks should FAIL
     * 
     * Expected Result: "Fail"
     * This tests a typical failing scenario
     */
    @Test
    void testCheckResultWithFailingMarks() {
        // Given: Student has 20 marks
        int marks = 20;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Fail"
        assertEquals("Fail", result, "Student with 20 marks should FAIL");
        
        // Print to console for presentation visibility
        System.out.println("Test 2 PASSED: Marks = 20, Result = " + result);
    }

    /**
     * Test Case 3: Student with 40 marks (boundary) should PASS
     * 
     * Expected Result: "Pass"
     * This tests the boundary condition - minimum passing marks
     */
    @Test
    void testCheckResultWithBoundaryMarks() {
        // Given: Student has exactly 40 marks (minimum passing)
        int marks = 40;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Pass"
        assertEquals("Pass", result, "Student with 40 marks (boundary) should PASS");
        
        // Print to console for presentation visibility
        System.out.println("Test 3 PASSED: Marks = 40 (Boundary), Result = " + result);
    }

    /**
     * Test Case 4: Verify passing marks threshold
     * 
     * Expected Result: 40
     * This tests the getPassingMarks() method
     */
    @Test
    void testGetPassingMarks() {
        // When: We get the passing marks threshold
        int passingMarks = resultService.getPassingMarks();
        
        // Then: It should be 40
        assertEquals(40, passingMarks, "Passing marks should be 40");
        
        // Print to console for presentation visibility
        System.out.println("Test 4 PASSED: Passing Marks Threshold = " + passingMarks);
    }

    /**
     * Test Case 5: Additional test - marks 39 should FAIL
     * 
     * Expected Result: "Fail"
     * This tests just below the boundary
     */
    @Test
    void testCheckResultJustBelowBoundary() {
        // Given: Student has 39 marks (just below passing)
        int marks = 39;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Fail"
        assertEquals("Fail", result, "Student with 39 marks should FAIL");
        
        // Print to console for presentation visibility
        System.out.println("Test 5 PASSED: Marks = 39, Result = " + result);
    }

    /**
     * Test Case 6: Perfect score - 100 marks should PASS
     * 
     * Expected Result: "Pass"
     * This tests the maximum possible marks
     */
    @Test
    void testCheckResultWithPerfectScore() {
        // Given: Student has perfect score (100 marks)
        int marks = 100;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Pass"
        assertEquals("Pass", result, "Student with 100 marks should PASS");
        
        // Print to console for presentation visibility
        System.out.println("Test 6 PASSED: Marks = 100 (Perfect Score), Result = " + result);
    }

    /**
     * Test Case 7: Zero marks should FAIL
     * 
     * Expected Result: "Fail"
     * This tests the minimum possible marks
     */
    @Test
    void testCheckResultWithZeroMarks() {
        // Given: Student has 0 marks
        int marks = 0;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Fail"
        assertEquals("Fail", result, "Student with 0 marks should FAIL");
        
        // Print to console for presentation visibility
        System.out.println("Test 7 PASSED: Marks = 0, Result = " + result);
    }

    /**
     * Test Case 8: Multiple marks test - verify consistency
     * 
     * This test checks multiple passing marks to ensure consistency
     */
    @Test
    void testCheckResultWithMultiplePassingMarks() {
        // Test various passing marks
        int[] passingMarks = {40, 45, 50, 60, 70, 80, 90, 99};
        
        for (int marks : passingMarks) {
            String result = resultService.checkResult(marks);
            assertEquals("Pass", result, 
                "Student with " + marks + " marks should PASS");
        }
        
        // Print to console for presentation visibility
        System.out.println("Test 8 PASSED: Multiple passing marks tested (40-99)");
    }

    /**
     * Test Case 9: Multiple failing marks test - verify consistency
     * 
     * This test checks multiple failing marks to ensure consistency
     */
    @Test
    void testCheckResultWithMultipleFailingMarks() {
        // Test various failing marks
        int[] failingMarks = {0, 5, 10, 15, 20, 25, 30, 35, 39};
        
        for (int marks : failingMarks) {
            String result = resultService.checkResult(marks);
            assertEquals("Fail", result, 
                "Student with " + marks + " marks should FAIL");
        }
        
        // Print to console for presentation visibility
        System.out.println("Test 9 PASSED: Multiple failing marks tested (0-39)");
    }

    /**
     * Test Case 10: Verify passing marks threshold value
     * 
     * This test ensures the passing marks threshold is correctly set to 40
     */
    @Test
    void testPassingMarksThresholdIsValid() {
        // When: We get the passing marks
        int passingMarks = resultService.getPassingMarks();
        
        // Then: It should be exactly 40
        assertEquals(40, passingMarks, "Passing marks threshold must be 40");
        assertTrue(passingMarks > 0, "Passing marks should be positive");
        assertTrue(passingMarks <= 100, "Passing marks should be <= 100");
        
        // Print to console for presentation visibility
        System.out.println("Test 10 PASSED: Passing marks threshold is valid (" + passingMarks + ")");
    }

    /**
     * Test Case 11: Boundary test - one mark above passing (41)
     * 
     * Expected Result: "Pass"
     * Tests just above the boundary
     */
    @Test
    void testCheckResultJustAboveBoundary() {
        // Given: Student has 41 marks (just above passing)
        int marks = 41;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Pass"
        assertEquals("Pass", result, "Student with 41 marks should PASS");
        
        // Print to console for presentation visibility
        System.out.println("Test 11 PASSED: Marks = 41 (Just Above Boundary), Result = " + result);
    }

    /**
     * Test Case 12: Round number test - 50 marks (halfway point)
     * 
     * Expected Result: "Pass"
     * Tests a common round number
     */
    @Test
    void testCheckResultWithHalfwayMarks() {
        // Given: Student has 50 marks (halfway point)
        int marks = 50;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Pass"
        assertEquals("Pass", result, "Student with 50 marks should PASS");
        
        // Print to console for presentation visibility
        System.out.println("Test 12 PASSED: Marks = 50 (Halfway), Result = " + result);
    }

    /**
     * Test Case 13: Low passing marks - 42 marks
     * 
     * Expected Result: "Pass"
     * Tests marks just above the minimum passing
     */
    @Test
    void testCheckResultWithLowPassingMarks() {
        // Given: Student has 42 marks (low passing)
        int marks = 42;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Pass"
        assertEquals("Pass", result, "Student with 42 marks should PASS");
        
        // Print to console for presentation visibility
        System.out.println("Test 13 PASSED: Marks = 42 (Low Passing), Result = " + result);
    }

    /**
     * Test Case 14: High failing marks - 38 marks
     * 
     * Expected Result: "Fail"
     * Tests marks just below the minimum passing
     */
    @Test
    void testCheckResultWithHighFailingMarks() {
        // Given: Student has 38 marks (high failing - close to passing)
        int marks = 38;
        
        // When: We check the result
        String result = resultService.checkResult(marks);
        
        // Then: Result should be "Fail"
        assertEquals("Fail", result, "Student with 38 marks should FAIL");
        
        // Print to console for presentation visibility
        System.out.println("Test 14 PASSED: Marks = 38 (High Failing), Result = " + result);
    }

    /**
     * Test Case 15: Result is never null
     * 
     * This test ensures the method always returns a non-null value
     */
    @Test
    void testCheckResultNeverReturnsNull() {
        // Test with various marks
        int[] testMarks = {0, 20, 39, 40, 41, 75, 100};
        
        for (int marks : testMarks) {
            String result = resultService.checkResult(marks);
            assertNotNull(result, 
                "Result should never be null for marks: " + marks);
        }
        
        // Print to console for presentation visibility
        System.out.println("Test 15 PASSED: Result is never null for any input");
    }
}
