package com.rivaldofez.core.datasource.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.local.entity.tvshow.*
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.datasource.remote.response.TvShowListItem
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.model.TvShowDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface CinemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieItemList: List<MovieItemLocalEntity>)

    @RawQuery(observedEntities = [MovieItemLocalEntity::class])
    fun getMovieList(query: SupportSQLiteQuery): Flow<List<MovieItemLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdPopularMovies(idPopularMovies: List<PopularMovieLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdTopRatedMovies(idTopRatedMovies: List<TopRatedMovieLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdNowPlayingMovies(idNowPlayingMovies: List<NowPlayingMovieLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdUpcomingMovies(idUpcomingMovies: List<UpcomingMovieLocalEntity>)




    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowList(tvShowItemList: List<TvShowItemLocalEntity>)

    @RawQuery(observedEntities = [TvShowItemLocalEntity::class])
    fun getTvShowList(query: SupportSQLiteQuery): Flow<List<TvShowItemLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdPopularTvShow(idPopularTvShow: List<PopularTvShowLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdTopRatedTvShow(idTopRatedTvShow: List<TopRatedTvShowLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdOnTheAirTvShow(idOnAirTvShow: List<OnTheAirTvShowLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdAiringTodayTvShow(idAiringTvShow: List<AiringTodayTvShowEntity>)



    @Query("Select * FROM tvshowlist natural join populartvshow")
    fun getPopularTvShow(): Flow<List<TvShowItemLocalEntity>>

    @Query("Select * FROM tvshowlist natural join topratedtvshow")
    fun getTopRatedTvShow(): Flow<List<TvShowItemLocalEntity>>

    @Query("Select * FROM tvshowlist natural join ontheairtvshow")
    fun getOnTheAirShow(): Flow<List<TvShowItemLocalEntity>>

    @Query("Select * FROM tvshowlist natural join airingtodaytvshow")
    fun getAiringTodayTvShow(): Flow<List<TvShowItemLocalEntity>>


    @Query("Select * FROM moviedetail where id= :id")
    fun getDetailMovie(id: Int): Flow<MovieDetailLocalEntity?>

    @Query("Select * FROM tvshowdetail where id= :id")
    fun getDetailTvShow(id: Int): Flow<TvShowDetailLocalEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(detailMovie: MovieDetailLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowDetail(detailTvShow: TvShowDetailLocalEntity)

    @Query("Select * From moviedetail where isFavorite = 1")
    fun getFavoritoMovies(): Flow<List<MovieDetailLocalEntity>>

    @Query("Select * From tvshowdetail where isFavorite = 1")
    fun getFavoritoTvShows(): Flow<List<TvShowDetailLocalEntity>>

    @Update
    fun updateDetailMovie(detailMovie: MovieDetailLocalEntity)

    @Update
    fun updateDetailTvShow(detailTvShow: TvShowDetailLocalEntity)

    @Query("SELECT * FROM movielist WHERE title like :query")
    suspend fun getSearchMovieResult(query: String): List<MovieItemLocalEntity>

    @Query("SELECT * FROM tvshowlist WHERE name like :query")
    suspend fun getSearchTvShowResult(query: String): List<TvShowItemLocalEntity>


    @Query("SELECT * FROM tvshowdetail WHERE isFavorite = 1 and name like :query")
    suspend fun getSearchNameFavoriteTvShowResult(query: String): List<TvShowDetailLocalEntity>

    @Query("SELECT * FROM moviedetail WHERE isFavorite = 1 and title like :query")
    suspend fun getSearchNameFavoriteMovieResult(query: String): List<MovieDetailLocalEntity>

}