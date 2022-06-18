package com.example.battleships.seeders;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.battleships.model.Categories;
import com.example.battleships.model.enums.CategoryNames;
import com.example.battleships.repositories.CategoriesRepository;

@Component
public class CategorySeeder implements CommandLineRunner{
    
    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategorySeeder(CategoriesRepository catRep) {
        this.categoriesRepository = catRep;
    }

    @Override
    public void run(String... args) throws Exception{
        if(this.categoriesRepository.count() == 0){
                List<Categories> category = Arrays.stream(CategoryNames.values())
                .map(type -> new Categories(type)).collect(Collectors.toList());

                this.categoriesRepository.saveAll(category);
        }
    }


    

}
