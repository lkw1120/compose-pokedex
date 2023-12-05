package com.lkw1120.pokedex.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.lkw1120.pokedex.R

@Composable
fun getTypeColor(type: String): Color {
    return colorResource(
        when(type) {
            "normal" -> R.color.type_normal
            "fire" -> R.color.type_fire
            "water" -> R.color.type_water
            "electric" -> R.color.type_electric
            "grass" -> R.color.type_grass
            "ice" -> R.color.type_ice
            "fighting" -> R.color.type_fighting
            "poison" -> R.color.type_poison
            "ground" -> R.color.type_ground
            "flying" -> R.color.type_flying
            "psychic" -> R.color.type_psychic
            "bug" -> R.color.type_bug
            "rock" -> R.color.type_rock
            "ghost" -> R.color.type_ghost
            "dragon" -> R.color.type_dragon
            "dark" -> R.color.type_dark
            "steel" -> R.color.type_steel
            "fairy" -> R.color.type_fairy
            else -> R.color.type_normal
        }
    )
}

@Composable
fun getStatColor(stat: String): Color {
    return colorResource(
        when(stat) {
            "hp" -> R.color.stat_hp
            "attack" -> R.color.stat_attack
            "defense" -> R.color.stat_defense
            "special-attack" -> R.color.stat_special_attack
            "special-defense" -> R.color.stat_special_defense
            "speed" -> R.color.stat_speed
            else -> R.color.stat_hp
        }
    )
}

@Composable
fun getStatString(stat: String): String {
    return stringResource(
        when(stat) {
            "hp" -> R.string.stat_hp
            "attack" -> R.string.stat_attack
            "defense" -> R.string.stat_defense
            "special-attack" -> R.string.stat_special_attack
            "special-defense" -> R.string.stat_special_defense
            "speed" -> R.string.stat_speed
            else -> R.string.stat_total
        }
    )
}