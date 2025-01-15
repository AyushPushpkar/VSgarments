package com.example.vsgarments.dependencyinjection

import android.content.Context
import com.example.vsgarments.product.FirebaseProductRepository
import com.example.vsgarments.product.ProductRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.appwrite.Client
import io.appwrite.services.Storage
import javax.inject.Singleton



//dagger hilt module
//to declare dependency
@Module
@InstallIn(SingletonComponent::class) // dependency live as long as application
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestoreDatabase() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAppWriteClient(@ApplicationContext context: Context): Client {
        return Client(context)
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("67838c5c0028311de936")
    }

    @Provides
    @Singleton
    fun provideStorageService(client: Client): Storage {
        return Storage(client)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        db: FirebaseFirestore ,
        appwriteStorage: Storage
    ): ProductRepository {
        return FirebaseProductRepository(db , appwriteStorage )
    }
}