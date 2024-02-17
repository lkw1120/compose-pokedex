package com.lkw1120.pokedex.core.data.mapper

import com.lkw1120.pokedex.core.common.Constants.POKEMON_URL
import com.lkw1120.pokedex.core.database.entity.DetailEntity
import com.lkw1120.pokedex.core.database.entity.PokeEntity
import com.lkw1120.pokedex.core.database.entity.StatEntity
import com.lkw1120.pokedex.core.database.entity.TypeEntity
import com.lkw1120.pokedex.core.model.PokeDetail
import com.lkw1120.pokedex.core.model.PokeItem
import com.lkw1120.pokedex.core.model.StatInfo
import com.lkw1120.pokedex.core.model.TypeInfo
import com.lkw1120.pokedex.core.network.response.PokeDetailResp
import com.lkw1120.pokedex.core.network.response.ResultResp

fun toPokeDetailFromRemote(from: PokeDetailResp): PokeDetail {
    return PokeDetail(
        id = from.id,
        baseExperience = from.baseExperience,
        weight = from.weight,
        height = from.height,
        name = from.name,
        order = from.order,
        stats = from.statResps.sortedBy {
            it.stat.url.split("/")[6].toInt()
        }.map { stat ->
            StatInfo(
                baseStat = stat.baseStat,
                effort = stat.effort,
                name = stat.stat.name,
                url = stat.stat.url
            )
        },
        types = from.typeResps.sortedBy {
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
            stats = from.stats.map { convertStatInfo(it) },
            types = from.types.map { convertTypeInfo(it) }
        )
    }
}

fun toPokeItemFromRemote(from: ResultResp): PokeItem {
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
        stats = from.stats.map { convertStatEntity(it) },
        types = from.types.map { convertTypeEntity(it) }
    )
}

fun toPokeEntityFromPokeDetail(from: PokeDetail): PokeEntity {
    return PokeEntity(
        id = from.id,
        name = from.name,
        url = String.format(POKEMON_URL,from.id)
    )
}

fun convertTypeEntity(type: TypeInfo): TypeEntity {
    return TypeEntity(
        slot = type.slot,
        name = type.name,
        url = type.url
    )
}

fun convertStatEntity(stat: StatInfo): StatEntity {
    return StatEntity(
        baseStat = stat.baseStat,
        effort = stat.effort,
        name = stat.name,
        url = stat.url
    )
}

fun convertTypeInfo(type: TypeEntity): TypeInfo {
    return TypeInfo(
        slot = type.slot,
        name = type.name,
        url = type.url
    )
}

fun convertStatInfo(stat: StatEntity): StatInfo {
    return StatInfo(
        baseStat = stat.baseStat,
        effort = stat.effort,
        name = stat.name,
        url = stat.url
    )
}
