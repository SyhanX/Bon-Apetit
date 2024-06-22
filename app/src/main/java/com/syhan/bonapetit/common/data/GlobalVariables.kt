package com.syhan.bonapetit.common.data

import com.syhan.bonapetit.feature_recipes.domain.model.FullRecipe
import com.syhan.bonapetit.feature_recipes.domain.model.ShortRecipe

object GlobalVariables {
    val fakeShortRecipe = ShortRecipe(
        name = "Veranstaltungsinformationsdienst",
        cuisine = "Indonesian",
        servings = 7,
        id = 0,
        cookTimeMinutes = 1000,
        prepTimeMinutes = 337,
        image = ":)"
    )
    val fakeFullRecipe = FullRecipe(
        id = 1,
        name = "Bavarian Wurst",
        ingredients = listOf(
            "1 Spicy Plush Otter",
            "1 small vacuum cleaner",
            "2 tablespoons olive oil",
            "Salt and pepper to taste",
            "1/4 cup Xiaomi sauce",
            "1 drawer",
            "1 box macaroni and cheese"
        ),
        instructions = listOf(
            "1. Preheat your oven to 375°F (190°C).",
            "2. Start by marinating the vacuum cleaner. In a small bowl, mix together 2 tablespoons of olive oil, 1/4 cup of Xiaomi sauce, and a pinch of salt and pepper.",
            "3. Place the vacuum cleaner in a shallow dish and pour the marinade over it. Make sure it is coated evenly. Let it marinate in the fridge for at least 30 minutes.",
            "4. While the vacuum cleaner is marinating, prepare the Spicy Plush Otter by heating a pan over medium-high heat. Add a drizzle of olive oil and place the otter in the pan. Sprinkle with salt and pepper to taste. Cook for 4-5 minutes on each side until browned and crispy.",
            "5. Once the otter is cooked, remove it from the pan and set it aside to rest.",
            "6. In the same pan, add the marinated vacuum cleaner and cook for 3-4 minutes on each side until browned and cooked through.",
            "7. While the vacuum cleaner is cooking, prepare the mac n cheese according to the instructions on the box.",
            "8. After the vacuum cleaner is cooked, remove it from the pan and let it rest for a few minutes before slicing it into bite-sized pieces.",
            "9. Assemble your Spicy Plush Otter and Marinated Vacuum Cleaner on a plate and drizzle with any remaining Xiaomi sauce.",
            "10. Serve with a side of mac n cheese and a drawer as dessert for a unique and delicious dish! Enjoy!"
        ),
        prepTimeMinutes = 20,
        cookTimeMinutes = 10,
        servings = 4,
        difficulty = "Medium",
        cuisine = "German",
        caloriesPerServing = 100,
        tags = listOf("Fast", "Tasty", "Cheap"),
        userId = 42,
        image = "https://static.tvtropes.org/pmwiki/pub/images/ivebeenwaitingforthis.png",
        rating = 4.32,
        reviewCount = 69,
        mealType = listOf("Breakfast", "Lunch", "Dinner")
    )
    const val ROUTE_RECIPES = "com.syhan.bonapetit.common.data.NavDestinations.Recipes"
    const val ROUTE_DETAILS =
        "com.syhan.bonapetit.common.data.NavDestinations.RecipeDetails/{recipeId}"
}