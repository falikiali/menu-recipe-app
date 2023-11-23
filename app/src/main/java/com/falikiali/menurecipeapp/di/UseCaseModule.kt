package com.falikiali.menurecipeapp.di

import com.falikiali.menurecipeapp.domain.usecase.ImplMenuUseCase
import com.falikiali.menurecipeapp.domain.usecase.MenuUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun provideMenuUseCase(implMenuUseCase: ImplMenuUseCase): MenuUseCase

}