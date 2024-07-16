package com.nitesh.Controller;

import com.nitesh.Service.IngredientService;
import com.nitesh.model.IngredientCategory;
import com.nitesh.model.IngredientsItem;
import com.nitesh.request.IngredientCategoryRequest;
import com.nitesh.request.IngredientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
//@CrossOrigin(origins="*")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;;

    @PostMapping("/category")
//    @CrossOrigin(origins="*")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
            @RequestBody IngredientCategoryRequest req) throws Exception {
        IngredientCategory category = ingredientService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PostMapping()
//    @CrossOrigin(origins="*")
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientRequest req) throws Exception {
        IngredientsItem item = ingredientService.createIngredientsItem(req.getIngredientName(), req.getRestaurantId(), req.getCategoryId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
//    @CrossOrigin(origins="*")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
           @PathVariable Long id) throws Exception {
        IngredientsItem item = ingredientService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
//    @CrossOrigin(origins="*")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
            @PathVariable Long id) throws Exception {
        List<IngredientsItem> items = ingredientService.findRestaurantIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
//    @CrossOrigin(origins="*")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
            @PathVariable Long id) throws Exception {
        List<IngredientCategory> categories = ingredientService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
