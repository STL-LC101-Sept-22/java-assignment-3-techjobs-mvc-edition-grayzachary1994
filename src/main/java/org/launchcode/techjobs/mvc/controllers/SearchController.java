package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping("results")
    //requestParam is for pulling the SearchTerm and SearchType from the form in search.html
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        //creates a new job arraylist to store searched jobs
        ArrayList<Job> jobs = new ArrayList<>();
        //if the search box is empty, or says "all," all jobs will be pulled up.
        if (searchTerm.isEmpty() || searchTerm.equals("all")) {
            jobs = JobData.findAll();
            //passes the
            model.addAttribute("title", "All Jobs");
        } else {
            //if search box is not empty, searches for column and value from method made in JobData.
            jobs = (JobData.findByColumnAndValue(searchType, searchTerm));
        }
        model.addAttribute("columns", columnChoices);
        model.addAttribute("jobs", jobs);
        return "search";
    }
}
