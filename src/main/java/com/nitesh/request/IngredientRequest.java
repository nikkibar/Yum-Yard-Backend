package com.nitesh.request;

import lombok.Data;

@Data
public class IngredientRequest {

    private String ingredientName;
    private Long categoryId;
    private Long restaurantId;
}
