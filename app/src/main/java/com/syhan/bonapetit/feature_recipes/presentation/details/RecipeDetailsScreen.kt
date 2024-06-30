package com.syhan.bonapetit.feature_recipes.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.syhan.bonapetit.R
import com.syhan.bonapetit.common.data.GlobalVariables.fakeFullRecipe
import com.syhan.bonapetit.common.domain.FiveStarRating
import com.syhan.bonapetit.common.presentation.ManageInternetConnection
import com.syhan.bonapetit.common.presentation.components.BoldText
import com.syhan.bonapetit.common.presentation.components.NormalText
import com.syhan.bonapetit.common.presentation.components.SemiboldNormalTextColumn
import com.syhan.bonapetit.common.presentation.components.SemiboldText
import com.syhan.bonapetit.common.presentation.theme.BonApetitTheme
import com.syhan.bonapetit.feature_recipes.domain.model.FullRecipe
import com.syhan.bonapetit.feature_recipes.presentation.details.compontents.CardWithSpacedItems
import com.syhan.bonapetit.feature_recipes.presentation.details.compontents.CopyToClipboardButton
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecipeDetailsScreen(
    viewModel: RecipeDetailsViewModel = koinViewModel(),
    onBackPressed: () -> Unit = {}
) {
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

    ManageInternetConnection(response = response.value, retryAction = {
        viewModel.retryConnection(response.value)
    }) {
        DetailsContent(recipe = recipe) { onBackPressed() }
    }
}

@Composable
private fun DetailsContent(
    recipe: FullRecipe,
    onBackPressed: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(
                        text = recipe.name,
                        fontWeight = FontWeight.Bold
                    )
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.FavoriteBorder, contentDescription = null)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.action_back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 8.dp)
        ) {
            item {
                MainInformation(
                    rating = recipe.rating,
                    reviewCount = recipe.reviewCount,
                    cuisine = recipe.cuisine,
                    tags = recipe.tags,
                    mealType = recipe.mealType,
                    name = recipe.name,
                    image = recipe.image
                )
            }
            item {
                OtherInformation(
                    prepTimeMinutes = recipe.prepTimeMinutes,
                    cookTimeMinutes = recipe.cookTimeMinutes,
                    difficulty = recipe.difficulty,
                    servings = recipe.servings,
                    caloriesPerServing = recipe.caloriesPerServing,
                )
            }
            item {
                Ingredients(recipe.ingredients)
            }
            item {
                CookingSteps(recipe.instructions)
                Spacer(Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun MainInformation(
    rating: Double,
    reviewCount: Int,
    cuisine: String,
    tags: List<String>,
    image: String,
    name: String,
    mealType: List<String>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
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
        Row {
            BoldText(text = stringResource(R.string.tags) + ": ")
            NormalText(text = tags.joinToString(separator = ", "))
        }
        AsyncImage(
            model = image,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.true_bavarian_wurst),
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .fillMaxSize()
        )
    }
}

@Composable
private fun OtherInformation(
    prepTimeMinutes: Int,
    cookTimeMinutes: Int,
    difficulty: String,
    servings: Int,
    caloriesPerServing: Int
) {
    CardWithSpacedItems {
        Row(
            horizontalArrangement = Arrangement.spacedBy(64.dp),
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
private fun Ingredients(
    ingredients: List<String>,
) {
    CardWithSpacedItems {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BoldText(text = stringResource(R.string.ingredients), fontSize = 20.sp)
            CopyToClipboardButton(
                string = ingredients.joinToString(
                    prefix = "- ",
                    separator = "\n\n- "
                )
            )
        }
        NormalText(text = ingredients.joinToString(prefix = "- ", separator = "\n\n- "))
    }
}

@Composable
private fun CookingSteps(instructions: List<String>) {
    CardWithSpacedItems {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BoldText(text = stringResource(R.string.instructions), fontSize = 20.sp)
            CopyToClipboardButton(
                string = instructions.joinToString(prefix = "- ", separator = "\n\n- "),
            )
        }
        instructions.forEachIndexed { index, step ->
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SemiboldText(text = stringResource(R.string.step_number, (index + 1)))
                NormalText(text = step)
            }
        }
    }
}

@Preview
@Composable
private fun DetailsPreview() {
    BonApetitTheme(darkTheme = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            DetailsContent(fakeFullRecipe)
        }
    }
}