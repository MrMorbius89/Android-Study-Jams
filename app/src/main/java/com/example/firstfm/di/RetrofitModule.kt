package com.example.firstfm.di


import com.example.firstfm.albumdetails.model.retrofit.AlbumDetailsServices
import com.example.firstfm.albums.model.retrofit.AlbumsServices
import com.example.firstfm.artistdetails.model.retrofit.ArtistDetailsServices
import com.example.firstfm.artists.model.retrofit.ArtistsServices
import com.example.firstfm.constants.CONSTANTS
import com.example.firstfm.genredetails.model.retrofit.GenreInfoServices
import com.example.firstfm.main.model.retrofit.Genre
import com.example.firstfm.main.model.retrofit.GenreServices
import com.example.firstfm.tracks.model.retrofit.TracksServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder().addConverterFactory(gsonConverterFactory).client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)).build()).baseUrl(CONSTANTS.BaseURL).build()
    }
}

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitServices {

    @Provides
    @ViewModelScoped
    fun provideGenreServices(retrofit: Retrofit):GenreServices = retrofit.create(GenreServices::class.java)

    @Provides
    @ViewModelScoped
    fun provideGenreInfoServices(retrofit: Retrofit):GenreInfoServices = retrofit.create(GenreInfoServices::class.java)

    @Provides
    @ViewModelScoped
    fun provideTracksServices(retrofit: Retrofit):TracksServices = retrofit.create(TracksServices::class.java)

    @Provides
    @ViewModelScoped
    fun provideAlbumsServices(retrofit: Retrofit):AlbumsServices = retrofit.create(AlbumsServices::class.java)

    @Provides
    @ViewModelScoped
    fun provideArtistsServices(retrofit: Retrofit):ArtistsServices = retrofit.create(ArtistsServices::class.java)

    @Provides
    @ViewModelScoped
    fun provideAlbumDetailsServices(retrofit: Retrofit):AlbumDetailsServices = retrofit.create(AlbumDetailsServices::class.java)

    @Provides
    @ViewModelScoped
    fun provideArtistDetailsServices(retrofit: Retrofit):ArtistDetailsServices = retrofit.create(ArtistDetailsServices::class.java)

}