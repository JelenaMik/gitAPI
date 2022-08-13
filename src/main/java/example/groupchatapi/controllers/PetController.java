package example.groupchatapi.controllers;
import example.groupchatapi.models.Pet;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
public class PetController {

    private final ArrayList<Pet> pets = new ArrayList<>();

    @GetMapping("/api/pets")
    public ArrayList<Pet> getPets(){
        return pets;
    }

    @GetMapping("/api/pets/{id}")
    public Pet getPet(@PathVariable UUID id){
        for(Pet pet:pets){
            if(pet.getId().equals(id)){
                return pet;
            }
        }
        return null;
    }

    @PostMapping("/api/pets")
    public Pet createPet(@RequestBody() Pet petDataRequest){
        Pet pet = new Pet(
                petDataRequest.getName(),
                petDataRequest.getAge(),
                petDataRequest.getType(),
                petDataRequest.getOwnerName()
        );
        this.pets.add(pet);
        return pet;
    }

    @PutMapping("/api/pets/{id}")
    public ArrayList<Pet> updatePet(@PathVariable UUID id, @RequestBody Pet petDataUpdated) {
        for (Pet pet : pets) {
            if (pet.getId().equals(id)) {
                pet.setName(petDataUpdated.getName());
                pet.setAge(petDataUpdated.getAge());
                pet.setType(petDataUpdated.getType());
                pet.setOwnerName(petDataUpdated.getOwnerName());
            }
        }
        return pets;
    }

    @DeleteMapping("/api/pets/{id}")
    public ArrayList<Pet> deletePet(@PathVariable UUID id){
        for(Pet pet: pets){
            if(pet.getId().equals(id)){
                pets.remove(pet);
            }
        }
        return pets;
    }
}
