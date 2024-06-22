package com.syhan.bonapetit.feature_recipes.presentation.details

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.data.ErrorType
import com.syhan.bonapetit.common.data.GlobalVariables.fakeFullRecipe
import com.syhan.bonapetit.common.data.NetworkResponse
import com.syhan.bonapetit.common.domain.FiveStarRating
import com.syhan.bonapetit.common.domain.showToastMessage
import com.syhan.bonapetit.common.presentation.ErrorScreen
import com.syhan.bonapetit.common.presentation.LoadingScreen
import com.syhan.bonapetit.common.presentation.components.BoldText
import com.syhan.bonapetit.common.presentation.components.NormalText
import com.syhan.bonapetit.common.presentation.components.SemiboldNormalTextColumn
import com.syhan.bonapetit.common.presentation.components.SemiboldNormalTextRow
import com.syhan.bonapetit.common.presentation.components.SemiboldText
import com.syhan.bonapetit.common.presentation.theme.BonApetitTheme
import com.syhan.bonapetit.feature_recipes.domain.model.FullRecipe
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailsScreen(viewModel: RecipeDetailsViewModel = koinViewModel()) {
    val response = viewModel.networkResponse.collectAsState()
    val recipe = viewModel.recipeState.value.run {
        FullRecipe(
            id = id,
            name = name,
            caloriesPerServing = caloriesPerServing,
            tags = tags,
            image = image,
            cookTimeMinutes = cookTimeMinutes,
            prepTimeMinutes = prepTimeMinutes,
            instructions = instructions,
            rating = rating,
            mealType = mealType,
            servings = servings,
            userId = userId,
            cuisine = cuisine,
            difficulty = difficulty,
            ingredients = ingredients,
            reviewCount = reviewCount
        )
    }

    when (response.value) {
        NetworkResponse.Loading -> {
            LoadingScreen()
        }

        NetworkResponse.UnexpectedError -> {
            ErrorScreen(ErrorType.UnexpectedError)
        }

        NetworkResponse.InternetConnectionError -> {
            ErrorScreen(ErrorType.ConnectionError)
        }

        NetworkResponse.Success -> {
            DetailsContent(recipe)
        }
    }
}

@Composable
private fun DetailsContent(
    recipe: FullRecipe,
    clipboardManager: ClipboardManager = LocalClipboardManager.current
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(Modifier.height(8.dp))
                MainRecipeInfo(
                    name = recipe.name,
                    rating = recipe.rating,
                    reviewCount = recipe.reviewCount,
                    cuisine = recipe.cuisine,
                    tags = recipe.tags,
                    mealType = recipe.mealType
                )
                RecipeImage(name = recipe.name, image = recipe.image)
                OtherRecipeInfoCard(
                    prepTimeMinutes = recipe.prepTimeMinutes,
                    cookTimeMinutes = recipe.cookTimeMinutes,
                    difficulty = recipe.difficulty,
                    servings = recipe.servings,
                    caloriesPerServing = recipe.caloriesPerServing,
                )
                RecipeIngredients(recipe.ingredients, clipboardManager)
                RecipeCookingSteps(instructions = recipe.instructions)
            }
        }
    }
}

@Composable
private fun MainRecipeInfo(
    name: String,
    rating: Double,
    reviewCount: Int,
    cuisine: String,
    tags: List<String>,
    mealType: List<String>
) {
    BoldText(text = name, fontSize = 26.sp)
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        FiveStarRating(rating)
        SemiboldText(text = stringResource(R.string.reviews, reviewCount))
    }
    Row {
        SemiboldText(text = mealType.joinToString(separator = ", ") + "  |  ")
        SemiboldText(text = cuisine + " " + stringResource(R.string.cuisine_lowercase))
    }
    SemiboldNormalTextRow(
        boldText = stringResource(R.string.tags),
        normalText = tags.joinToString(separator = ", ")
    )
}

@Composable
private fun RecipeImage(
    name: String,
    image: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = image,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.true_bavarian_wurst),
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .size(350.dp)
        )
    }
}

@Composable
private fun OtherRecipeInfoCard(
    prepTimeMinutes: Int,
    cookTimeMinutes: Int,
    difficulty: String,
    servings: Int,
    caloriesPerServing: Int,
    modifier: Modifier = Modifier
) {
    OutlinedCard(modifier) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(64.dp),
            modifier = Modifier
                .padding(16.dp, 8.dp)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SemiboldNormalTextColumn(
                    boldText = stringResource(R.string.prep_time),
                    normalText = stringResource(
                        R.string.time_minutes,
                        prepTimeMinutes
                    )
                )
                SemiboldNormalTextColumn(
                    boldText = stringResource(R.string.total_time),
                    normalText = stringResource(
                        R.string.time_minutes,
                        (prepTimeMinutes + cookTimeMinutes)
                    )
                )
                SemiboldNormalTextColumn(
                    boldText = stringResource(R.string.servings),
                    normalText = servings.toString()
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                SemiboldNormalTextColumn(
                    boldText = stringResource(R.string.cook_time),
                    normalText = stringResource(
                        R.string.time_minutes,
                        cookTimeMinutes
                    )
                )
                SemiboldNormalTextColumn(
                    boldText = stringResource(R.string.difficulty),
                    normalText = difficulty
                )
                SemiboldNormalTextColumn(
                    boldText = stringResource(R.string.calories),
                    normalText = stringResource(
                        R.string.calories_per_serving,
                        caloriesPerServing
                    )
                )
            }
        }
    }
}

@Composable
private fun RecipeIngredients(
    ingredients: List<String>,
    clipboardManager: ClipboardManager,
    context: Context = LocalContext.current
) {
    OutlinedCard(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(16.dp, 8.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                BoldText(text = stringResource(R.string.ingredients), fontSize = 20.sp)
                IconButton(onClick = {
                    clipboardManager.setText(
                        AnnotatedString(
                            ingredients.joinToString(
                                prefix = "- ",
                                separator = "\n\n- "
                            )
                        )
                    )
                    showToastMessage(context = context)
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_content_copy),
                        contentDescription = stringResource(R.string.copy_text)
                    )
                }
            }
            NormalText(text = ingredients.joinToString(prefix = "- ", separator = "\n\n- "))
        }
    }
}

@Composable
private fun RecipeCookingSteps(instructions: List<String>) {
    BoldText(text = stringResource(R.string.instructions), fontSize = 20.sp)
    instructions.forEachIndexed { index, step ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SemiboldText(text = stringResource(R.string.step_number, (index + 1)))
            NormalText(text = step)
        }
    }
    Spacer(Modifier.height(16.dp))
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Composable
private fun DetailsPreview() {
    BonApetitTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            DetailsContent(fakeFullRecipe)
        }
    }
}