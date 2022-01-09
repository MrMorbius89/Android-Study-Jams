package com.example.firstfm.di

import com.example.firstfm.albumdetails.model.AlbumsDetailsRepository
import com.example.firstfm.albumdetails.model.retrofit.AlbumDetailsServices
import com.example.firstfm.albums.model.AlbumsRepository
import com.example.firstfm.albums.model.retrofit.AlbumsServices
import com.example.firstfm.artistdetails.model.ArtistDetailsRepository
import com.example.firstfm.artistdetails.model.retrofit.ArtistDetailsServices
import com.example.firstfm.artists.model.ArtistsRepository
import com.example.firstfm.artists.model.retrofit.ArtistsServices
import com.example.firstfm.genredetails.model.GenreDetailsRepository
import com.example.firstfm.genredetails.model.retrofit.GenreInfoServices
import com.example.firstfm.main.model.MainRepository
import com.example.firstfm.main.model.retrofit.GenreServices
import com.example.firstfm.tracks.model.TracksRepository
import com.example.firstfm.tracks.model.retrofit.TracksServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(genreServices: GenreServices):MainRepository {
        return MainRepository(genreServices)
    }

    @Provides
    @ViewModelScoped
    fun provideGenreDetailsRepository(genreInfoServices: GenreInfoServices):GenreDetailsRepository {
        return GenreDetailsRepository(genreInfoServices)
    }

    @Provides
    @ViewModelScoped
    fun provideTracksRepository(tracksServices: TracksServices):TracksRepository {
        return TracksRepository(tracksServices)
    }

    @Provides
    @ViewModelScoped
    fun provideAlbumsRepository(albumsServices: AlbumsServices):AlbumsRepository {
        return AlbumsRepository(albumsServices)
    }

    @Provides
    @ViewModelScoped
    fun provideArtistsRepository(artistsServices: ArtistsServices):ArtistsRepository {
        return ArtistsRepository(artistsServices)
    }

    @Provides
    @ViewModelScoped
    fun provideAlbumDetailsRepository(albumDetailsServices: AlbumDetailsServices):AlbumsDetailsRepository {
        return AlbumsDetailsRepository(albumDetailsServices)
    }

    @Provides
    @ViewModelScoped
    fun provideArtistDetailsRepository(artistDetailsServices: ArtistDetailsServices):ArtistDetailsRepository {
        return ArtistDetailsRepository(artistDetailsServices)
    }

}