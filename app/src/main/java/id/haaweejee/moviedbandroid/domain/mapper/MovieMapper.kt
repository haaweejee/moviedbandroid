package id.haaweejee.moviedbandroid.domain.mapper

import id.haaweejee.moviedbandroid.data.local.entity.LocalMovieEntity
import id.haaweejee.moviedbandroid.domain.entities.MovieEntities

fun MovieEntities.toMovieEntity(): LocalMovieEntity =
    LocalMovieEntity(
        idMovie = this.idMovie,
        titleMovie = this.titleMovie,
        posterMovie = this.posterMovie,
        releaseDateMovie = this.releaseDateMovie,
        ratingMovie = this.ratingMovie,
        isBookmarked = this.isBookmarked,
    )
