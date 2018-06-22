package org.launchcode.controllers;


import org.launchcode.models.data.CategoryDao;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

}
