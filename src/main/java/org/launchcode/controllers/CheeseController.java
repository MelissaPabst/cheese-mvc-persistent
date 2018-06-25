package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.Cheese;
import org.launchcode.models.data.CategoryDao;
import org.launchcode.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("cheese")
public class CheeseController {

    @Autowired
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");

        return "cheese/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {
        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute  @Valid Cheese newCheese,
                                       Errors errors, @RequestParam int categoryId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Cheese");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheese/add";
        }

        //fetch a single Category object, with ID matching the CategoryID value selected
        Category cat = categoryDao.findOne(categoryId);
        //set it
        newCheese.setCategory(cat);

        cheeseDao.save(newCheese);
        return "redirect:";
    }

    @RequestMapping(value = "category/{categoryId}", method = RequestMethod.GET)
    public String category(@PathVariable int categoryId, Model model) {

        Category cat = categoryDao.findOne(categoryId);
        //create list
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("title", "All '" + cat.getName() + "' Cheeses");
        model.addAttribute("cheeses", cheeses);
        return "cheese/index";

    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Remove Cheese");
        return "cheese/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

        for (int cheeseId : cheeseIds) {
            cheeseDao.delete(cheeseId);
        }

        return "redirect:";
    }

    //method to display edit cheese form
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int cheeseId) {

        Cheese cheese = cheeseDao.findOne(cheeseId);
        model.addAttribute("cheese", cheese);
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Edit Cheese " + cheese.getName() + " (ID = " + cheese.getId() + ")");

        return "cheese/edit";

    }

    //method to process edit cheese form
    @RequestMapping(value = "edit/{cheeseId}", method = RequestMethod.POST)
    public String processEditForm(@ModelAttribute @Valid Cheese editedCheese, Errors errors, @RequestParam int cheeseId, @RequestParam int catId, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("Title", "Edit Cheese " + editedCheese.getName() + " (ID = " + cheeseId + ")");
            model.addAttribute("categories", categoryDao.findAll());
            return "cheee/add";
        }

        Cheese cheese = cheeseDao.findOne(cheeseId);
        Category cat = categoryDao.findOne(catId);
        cheese.setCategory(cat);
        cheese.setName(editedCheese.getName());
        cheese.setDescription(editedCheese.getDescription());
        cheeseDao.save(cheese);

        return "redirect:";
    }
}
