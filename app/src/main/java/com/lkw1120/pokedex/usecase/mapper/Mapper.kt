package com.lkw1120.pokedex.usecase.mapper

import com.lkw1120.pokedex.common.Constants.POKEMON_URL
import com.lkw1120.pokedex.datasource.entity.DetailEntity
import com.lkw1120.pokedex.datasource.entity.PokeEntity
import com.lkw1120.pokedex.datasource.remote.response.PokeDetailResp
import com.lkw1120.pokedex.datasource.remote.response.Result
import com.lkw1120.pokedex.usecase.model.PokeDetail
import com.lkw1120.pokedex.usecase.model.PokeItem
import com.lkw1120.pokedex.usecase.model.StatInfo
import com.lkw1120.pokedex.usecase.model.TypeInfo

fun toPokeDetailFromRemote(from: PokeDetailResp): PokeDetail {
    return PokeDetail(
        id = from.id,
        baseExperience = from.baseExperience,
        weight = from.weight,
        height = from.height,
        name = from.name,
        order = from.order,
        stats = from.stats.sortedBy {
            it.stat.url.split("/")[6].toInt()
        }.map { stat ->
            StatInfo(
                baseStat = stat.baseStat,
                effort = stat.effort,
                name = stat.stat.name,
                url = stat.stat.url
            )
        },
        types = from.types.sortedBy {
            it.slot.toInt()
        }.map { type ->
            TypeInfo(
                slot = type.slot,
                name = type.type.name,
                url = type.type.url
            )

        }
    )
}

fun toPokeDetailFromLocal(from: DetailEntity?): PokeDetail? {
    return from?.let {
        PokeDetail(
            id = from.id,
            baseExperience = from.baseExperience,
            weight = from.weight,
            height = from.height,
            name = from.name,
            order = from.order,
            stats = from.stats,
            types = from.types
        )
    }
}

fun toPokeItemFromRemote(from: Result): PokeItem {
    return PokeItem(
        id = from.url.split("/")[6].toLong(),
        name = from.name,
        url = from.url
    )
}

fun toPokeItemFromLocal(from: PokeEntity): PokeItem {
    return PokeItem(
        id = from.id,
        name = from.name,
        url = from.url
    )
}

fun toDetailEntityFromPokeDetail(from: PokeDetail): DetailEntity {
    return DetailEntity(
        id = from.id,
        baseExperience = from.baseExperience,
        weight = from.weight,
        height = from.height,
        name = from.name,
        order = from.order,
        stats = from.stats,
        types = from.types
    )
}

fun toPokeEntityFromPokeDetail(from: PokeDetail): PokeEntity {
    return PokeEntity(
        id = from.id,
        name = from.name,
        url = String.format(POKEMON_URL,from.id)
    )
}
