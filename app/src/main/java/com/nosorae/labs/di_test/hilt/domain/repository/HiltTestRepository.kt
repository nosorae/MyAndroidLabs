package com.nosorae.labs.di_test.hilt.domain.repository

interface HiltTestRepository {
    fun getMovies(keyword: String)
}