package com.demo.surefiretest.controller;

import com.demo.surefiretest.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ResultController - Handles both REST API and Web Pages
 * 
 * This controller exposes:
 * 1. REST API endpoints for programmatic access
 * 2. Web pages with Thymeleaf templates for UI
 */
@Controller
public class ResultController {

    // Inject ResultService using Spring's Dependency Injection
    @Autowired
    private ResultService resultService;

    /**
     * GET endpoint for home page
     * 
     * URL: http://localhost:8080/
     * 
     * @param model - Spring Model to pass data to Thymeleaf template
     * @return home page view name
     */
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "Academic Result Evaluation Platform");
        model.addAttribute("passingMarks", resultService.getPassingMarks());
        return "index";  // Returns templates/index.html
    }

    /**
     * GET endpoint for API documentation page
     * 
     * URL: http://localhost:8080/api-docs
     * 
     * @param model - Spring Model to pass data to Thymeleaf template
     * @return API documentation page view name
     */
    @GetMapping("/api-docs")
    public String apiDocsPage(Model model) {
        model.addAttribute("title", "API Documentation");
        model.addAttribute("passingMarks", resultService.getPassingMarks());
        
        // Pre-execute some example API calls to show live data
        int[] testMarks = {75, 40, 20, 85, 35};
        java.util.List<java.util.Map<String, Object>> examples = new java.util.ArrayList<>();
        
        for (int marks : testMarks) {
            java.util.Map<String, Object> example = new java.util.HashMap<>();
            example.put("marks", marks);
            example.put("result", resultService.checkResult(marks));
            example.put("message", resultService.checkResult(marks).equals("Pass") 
                ? "Congratulations! You passed." 
                : "Sorry! You failed.");
            example.put("url", "/api/result/" + marks);
            examples.add(example);
        }
        
        model.addAttribute("examples", examples);
        return "api-docs";  // Returns templates/api-docs.html
    }

    /**
     * GET endpoint for About Surefire page
     * 
     * URL: http://localhost:8080/about
     * 
     * @param model - Spring Model to pass data to Thymeleaf template
     * @return About page view name
     */
    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("title", "Quality Pipeline & Maven Surefire");
        return "about";  // Returns templates/about.html
    }

    /**
     * GET endpoint to check result and display on web page
     * 
     * URL: http://localhost:8080/check?marks=75
     * 
     * @param marks - Student's marks from query parameter
     * @param model - Spring Model to pass data to Thymeleaf template
     * @return result page view name
     */
    @GetMapping("/check")
    public String checkResultWeb(@RequestParam(required = false) Integer marks, Model model) {
        model.addAttribute("title", "Academic Result Evaluation Platform");
        model.addAttribute("passingMarks", resultService.getPassingMarks());
        
        if (marks != null) {
            // Validate marks range (0-100)
            if (marks < 0 || marks > 100) {
                model.addAttribute("error", "Invalid marks! Please enter marks between 0 and 100.");
                model.addAttribute("marks", marks);
            } else {
                // Call service to check result
                String result = resultService.checkResult(marks);
                
                model.addAttribute("marks", marks);
                model.addAttribute("result", result);
                
                // Add appropriate message based on result
                if (result.equals("Pass")) {
                    model.addAttribute("message", "Congratulations! You passed the exam.");
                } else {
                    model.addAttribute("message", "Sorry! You need to improve your performance.");
                }
            }
        }
        
        return "index";  // Returns templates/index.html
    }

    /**
     * REST API endpoint to check result by marks (returns JSON)
     * 
     * URL: http://localhost:8080/api/result/{marks}
     * 
     * Examples:
     * - GET /api/result/75 Returns {"marks": 75, "result": "Pass", "message": "Congratulations! You passed."}
     * - GET /api/result/20 Returns {"marks": 20, "result": "Fail", "message": "Sorry! You failed."}
     * 
     * @param marks - Student's marks (passed in URL path)
     * @return Map containing marks, result, and message (JSON response)
     */
    @RestController
    public class ResultApiController {
        
        @GetMapping("/api/result/{marks}")
        public Map<String, Object> getResultAPI(@PathVariable int marks) {
            
            // Create response map
            Map<String, Object> response = new HashMap<>();
            
            // Validate marks range (0-100)
            if (marks < 0 || marks > 100) {
                response.put("marks", marks);
                response.put("result", "Error");
                response.put("message", "Invalid marks! Please enter marks between 0 and 100.");
                return response;
            }

            // Call service to check result
            String result = resultService.checkResult(marks);
            
            // Build response
            response.put("marks", marks);
            response.put("result", result);
            
            // Add appropriate message based on result
            if (result.equals("Pass")) {
                response.put("message", "Congratulations! You passed.");
            } else {
                response.put("message", "Sorry! You failed.");
            }

            return response;
        }
    }
}
