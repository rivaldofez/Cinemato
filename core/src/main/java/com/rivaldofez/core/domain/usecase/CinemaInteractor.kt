package com.rivaldofez.core.domain.usecase

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.datasource.remote.MoviesType
import com.rivaldofez.core.datasource.remote.TvShowsType
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail
import com.rivaldofez.core.domain.repository.ICinemaRepository
import kotlinx.coroutines.flow.Flow

class CinemaInteractor(private val cinemaRepository: ICinemaRepository): CinemaUseCase {
    override fun getPopularMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getMovies(MoviesType.Popular, page)

    override fun getUpComingMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getMovies(MoviesType.UpComing, page)

    override fun getTopRatedMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getMovies(MoviesType.TopRated, page)

    override fun getNowPlayingMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getMovies(MoviesType.NowPlaying, page)

    override fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>> = cinemaRepository.getDetailMovie(id)

    override fun getPopularTvShow(page: String): Flow<Resource<List<TvShow>>> = cinemaRepository.getTvShows(TvShowsType.Popular, page)

    override fun getTopRatedTvShow(page: String): Flow<Resource<List<TvShow>>> = cinemaRepository.getTvShows(TvShowsType.TopRated, page)

    override fun getOnTheAirTvShow(page: String): Flow<Resource<List<TvShow>>> = cinemaRepository.getTvShows(TvShowsType.OnTheAir, page)

    override fun getAiringTodayTvShow(page: String): Flow<Resource<List<TvShow>>> = cinemaRepository.getTvShows(TvShowsType.AiringToday, page)

    override fun getDetailTvShow(id: String): Flow<Resource<TvShowDetail?>> = cinemaRepository.getDetailTvShow(id)

    override fun setFavoriteTvShow(detailTvShow: TvShowDetail, state: Boolean) = cinemaRepository.setFavoriteTvShow(detailTvShow, state)

    override fun setFavoriteMovie(detailMovie: MovieDetail, state: Boolean) = cinemaRepository.setFavoriteMovie(detailMovie, state)

    override fun getFavoriteTvShow(): Flow<List<TvShowDetail>> = cinemaRepository.getFavoriteTvShows()

    override fun getFavoriteMovie(): Flow<List<MovieDetail>> = cinemaRepository.getFavoriteMovies()

    override suspend fun getSearchMovieResult(query: String): List<Movie> = cinemaRepository.getSearchMovieResult(query)

    override suspend fun getSearchTvShowResult(query: String): List<TvShow> = cinemaRepository.getSearchTvShowResult(query)

    override suspend fun getSearchNameFavoriteTvShowResult(query: String): List<TvShowDetail> = cinemaRepository.getSearchNameFavoriteTvShowResult(query)

    override suspend fun getSearchNameFavoriteMovieResult(query: String): List<MovieDetail> = cinemaRepository.getSearchNameFavoriteMovieResult(query)

}