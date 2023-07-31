package br.com.network.dto


import com.google.gson.annotations.SerializedName

data class CharacterAllDto(
    @SerializedName("info")
    val info: InfoDto,
    @SerializedName("results")
    val results: List<CharacterDto>
)

