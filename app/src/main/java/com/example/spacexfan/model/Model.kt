package com.example.spacexfan.model
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Rocket(

    @Embedded(prefix = "fairings_")
    @SerializedName("fairings") val fairings : Fairings,

    @Embedded(prefix = "links_")
    @SerializedName("links") val links : Links,  // String yerine Links olması lazım ancak hata veriyor.

    @SerializedName("static_fire_date_utc") val static_fire_date_utc : String,
    @SerializedName("static_fire_date_unix") val static_fire_date_unix : Int,
    @SerializedName("tdb") val tdb : Boolean,
    @SerializedName("net") val net : Boolean,
    @SerializedName("window") val window : Int,
    @SerializedName("rocket") val rocket : String,
    @SerializedName("success") val success : Boolean,
    @SerializedName("details") val details : String,
    @SerializedName("crew") val crew : List<String>,
    @SerializedName("ships") val ships : List<String>,
    @SerializedName("capsules") val capsules : List<String>,
    @SerializedName("payloads") val payloads : List<String>,
    @SerializedName("launchpad") val launchpad : String,
    @SerializedName("auto_update") val auto_update : Boolean,
    @SerializedName("flight_number") val flight_number : Int,
    @SerializedName("name") val name : String,
    @SerializedName("date_utc") val date_utc : String,
    @SerializedName("date_unix") val date_unix : Int,
    @SerializedName("date_local") val date_local : String,
    @SerializedName("date_precision") val date_precision : String,
    @SerializedName("upcoming") val upcoming : Boolean,
    @SerializedName("cores") val cores : List<Cores>,
    @SerializedName("id") val id : String
){

    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}

data class Fairings (
    @SerializedName("reused") val reused: Boolean,
    @SerializedName("recovery_attempt") val recovery_attempt: Boolean,
    @SerializedName("recovered") val recovered: Boolean,
    @SerializedName("ships") val ships: List<String>


    )

data class Cores (

    @SerializedName("core") val core : String,
    @SerializedName("flight") val flight : Int,
    @SerializedName("gridfins") val gridfins : Boolean,
    @SerializedName("legs") val legs : Boolean,
    @SerializedName("reused") val reused : Boolean,
    @SerializedName("landing_attempt") val landing_attempt : Boolean,
    @SerializedName("landing_success") val landing_success : Boolean,
    @SerializedName("landing_type") val landing_type : String,
    @SerializedName("landpad") val landpad : String
)
data class Flickr (
    @SerializedName("small") val small : List<String>,
    @SerializedName("original") val original : List<String>
)
data class Links (
    @Embedded(prefix = "patch_")
    @SerializedName("patch") val patch : Patch,

    @Embedded(prefix = "reddit_")
    @SerializedName("reddit") val reddit : Reddit,

    @Embedded(prefix = "flickr_")
    @SerializedName("flickr") val flickr : Flickr,

    @SerializedName("presskit") val presskit : String,
    @SerializedName("webcast") val webcast : String,
    @SerializedName("youtube_id") val youtube_id : String,
    @SerializedName("article") val article : String,
    @SerializedName("wikipedia") val wikipedia : String
)
data class Patch (

    @SerializedName("small") val small : String,
    @SerializedName("large") val large : String
)

data class Reddit (

    @SerializedName("campaign") val campaign : String,
    @SerializedName("launch") val launch : String,
    @SerializedName("media") val media : String,
    @SerializedName("recovery") val recovery : String
)
