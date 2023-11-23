package com.falikiali.menurecipeapp.data.remote.dto

import com.falikiali.menurecipeapp.domain.model.Recipe
import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @field:SerializedName("idMeal") val id: String,
    @field:SerializedName("strMeal") val name: String,
    @field:SerializedName("strCategory") val category: String,
    @field:SerializedName("strArea") val area: String,
    @field:SerializedName("strInstructions") val instructions: String,
    @field:SerializedName("strMealThumb") val thumbnail: String,
    @field:SerializedName("strYoutube") val youtube: String,

    @field:SerializedName("strIngredient1") val ingredient1: String,
    @field:SerializedName("strIngredient2") val ingredient2: String,
    @field:SerializedName("strIngredient3") val ingredient3: String,
    @field:SerializedName("strIngredient4") val ingredient4: String,
    @field:SerializedName("strIngredient5") val ingredient5: String,
    @field:SerializedName("strIngredient6") val ingredient6: String,
    @field:SerializedName("strIngredient7") val ingredient7: String,
    @field:SerializedName("strIngredient8") val ingredient8: String,
    @field:SerializedName("strIngredient9") val ingredient9: String,
    @field:SerializedName("strIngredient10") val ingredient10: String,
    @field:SerializedName("strIngredient11") val ingredient11: String,
    @field:SerializedName("strIngredient12") val ingredient12: String,
    @field:SerializedName("strIngredient13") val ingredient13: String,
    @field:SerializedName("strIngredient14") val ingredient14: String,
    @field:SerializedName("strIngredient15") val ingredient15: String,
    @field:SerializedName("strIngredient16") val ingredient16: String,
    @field:SerializedName("strIngredient17") val ingredient17: String,
    @field:SerializedName("strIngredient18") val ingredient18: String,
    @field:SerializedName("strIngredient19") val ingredient19: String,
    @field:SerializedName("strIngredient20") val ingredient20: String,

    @field:SerializedName("strMeasure1") val measure1: String,
    @field:SerializedName("strMeasure2") val measure2: String,
    @field:SerializedName("strMeasure3") val measure3: String,
    @field:SerializedName("strMeasure4") val measure4: String,
    @field:SerializedName("strMeasure5") val measure5: String,
    @field:SerializedName("strMeasure6") val measure6: String,
    @field:SerializedName("strMeasure7") val measure7: String,
    @field:SerializedName("strMeasure8") val measure8: String,
    @field:SerializedName("strMeasure9") val measure9: String,
    @field:SerializedName("strMeasure10") val measure10: String,
    @field:SerializedName("strMeasure11") val measure11: String,
    @field:SerializedName("strMeasure12") val measure12: String,
    @field:SerializedName("strMeasure13") val measure13: String,
    @field:SerializedName("strMeasure14") val measure14: String,
    @field:SerializedName("strMeasure15") val measure15: String,
    @field:SerializedName("strMeasure16") val measure16: String,
    @field:SerializedName("strMeasure17") val measure17: String,
    @field:SerializedName("strMeasure18") val measure18: String,
    @field:SerializedName("strMeasure19") val measure19: String,
    @field:SerializedName("strMeasure20") val measure20: String
) {
    fun toDomain(): Recipe {
        return Recipe(
            id, name, category, area, instructions, thumbnail, youtube, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10, ingredient11, ingredient12, ingredient13, ingredient14, ingredient15, ingredient16, ingredient17, ingredient18, ingredient19, ingredient20, measure1, measure2, measure3, measure4, measure5, measure6, measure7, measure8, measure9, measure10, measure11, measure12, measure13, measure14, measure15, measure16, measure17, measure18, measure19, measure20
        )
    }
}
