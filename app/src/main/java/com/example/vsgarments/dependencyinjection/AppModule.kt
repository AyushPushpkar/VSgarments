package com.example.vsgarments.dependencyinjection

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//dagger hilt module
//to declare dependency
@Module
@InstallIn(SingletonComponent::class) // dependency live as long as application
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()
}