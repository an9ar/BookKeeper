package com.an9ar.bookkeeper.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.realm.Realm
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RealmModule {

    @Singleton
    @Provides
    fun provideRealmInstance(): Realm = Realm.getDefaultInstance()

}