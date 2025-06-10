package com.example.weatherapp.di

import com.example.weatherapp.data.remote.WeatherRemoteDataSource
import com.example.weatherapp.data.remote.WeatherRemoteDataSourceImpl
import com.example.weatherapp.data.remote.provideKtorClient
import com.example.weatherapp.domain.WeatherRepository
import com.example.weatherapp.data.repository.WeatherRepositoryImpl
import com.example.weatherapp.domain.usecases.GetWeatherUseCase
import com.example.weatherapp.presentation.features.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { provideKtorClient() }

    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get()) }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    single { GetWeatherUseCase(get()) }

    viewModel { WeatherViewModel(get()) }

}
