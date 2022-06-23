package com.vlsantillan.imagetoresult.di

import com.vlsantillan.data.CalculatorRepositoryImpl
import com.vlsantillan.domain.repository.CalculatorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Bind repositories
 *
 * Created by Vincent Santillan on 24/06/2022.
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindingModule {

    @Binds
    fun calculatorRepository(impl: CalculatorRepositoryImpl): CalculatorRepository
}