package org.capsules.validation.collection.constraints.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Martin Janys
 */
@Controller
public class PersonController {

    @RequestMapping(value="/persons", method= RequestMethod.POST)
    public String save(@RequestParam MultiValueMap<String, String> params,
                       @Valid @ModelAttribute Person person,
                       Errors errors,
                       Model model) {
        if (errors.hasErrors()) {
            return "form";
        }
        return "view";
    }
}