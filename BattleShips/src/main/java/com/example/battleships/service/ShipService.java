package com.example.battleships.service;

import com.example.battleships.model.Categories;
import com.example.battleships.model.Ships;
import com.example.battleships.model.Users;
import com.example.battleships.model.dtos.ShipDTO;
import com.example.battleships.model.enums.CategoryNames;
import com.example.battleships.repositories.CategoriesRepository;
import com.example.battleships.repositories.ShipRepository;
import com.example.battleships.repositories.UserRepository;
import com.example.battleships.user.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    private ShipRepository shipRepository;
    private UserRepository userRepository;
    private CategoriesRepository categoriesRepository;
    private CurrentUser currentUser;


    public ShipService(ShipRepository shipRepository, CurrentUser currentUser, UserRepository userRepository, CategoriesRepository categoriesRepository) {
        this.currentUser = currentUser;
        this.shipRepository = shipRepository;
        this.userRepository = userRepository;
        this.categoriesRepository = categoriesRepository;
    }


    public boolean addShip(ShipDTO shipDTO) {

        Optional<Ships> byName = shipRepository.findByName(shipDTO.getName());

        if(byName.isPresent()){
            return false;
        }

        Optional<Users> current = userRepository.findByEmail(currentUser.getEmail());


        CategoryNames type = null;
        switch (shipDTO.getCategory()) {
            case "0" -> type = CategoryNames.BATTLE;
            case "1" -> type = CategoryNames.CARGO;
            case "2" -> type = CategoryNames.PATROL;
        };

        Categories category = this.categoriesRepository.findByName(type);
        Ships theNewShip = new Ships();

        theNewShip.setName(shipDTO.getName());
        theNewShip.setHealth(shipDTO.getHealth());
        theNewShip.setType(category);
        theNewShip.setCreated(shipDTO.getCreated());
        theNewShip.setPower(shipDTO.getPower());
        theNewShip.setUser(current.get());


        this.shipRepository.save(theNewShip);
        return true;
    }
}

