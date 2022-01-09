package com.example.firstfm.di

import android.provider.MediaStore
import androidx.fragment.app.FragmentActivity
import com.example.firstfm.adapter.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class AdapterModule {

    @Provides
    @ActivityScoped
    fun provideGenreListAdapter():GenreListAdapter{
        return GenreListAdapter()
    }

    @Provides
    @ActivityScoped
    fun providerAlbumsListAdapter():AlbumsListAdapter{
        return AlbumsListAdapter()
    }

    @Provides
    @ActivityScoped
    fun provideArtistsListAdapter():ArtistsListAdapter{
        return ArtistsListAdapter()
    }

    @Provides
    @ActivityScoped
    fun provideTracksListAdapter():TracksListAdapter{
        return TracksListAdapter()
    }

    @Provides
    @ActivityScoped
    fun providerTagListAdapter():TagListAdapter{
        return TagListAdapter()
    }

    @Provides
    @ActivityScoped
    fun provideConstrainedAlbumsListAdapter():ConstrainedAlbumsListAdapter{
        return ConstrainedAlbumsListAdapter()
    }

    @Provides
    @ActivityScoped
    fun provideConstrainedTracksListAdapter():ConstrainedTracksListAdapter{
        return ConstrainedTracksListAdapter()
    }
}