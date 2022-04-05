package com.nosorae.labs.apollo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import com.nosorae.labs.LocationsQuery
import com.nosorae.labs.R
import kotlinx.coroutines.launch

class ApolloTestActivity : AppCompatActivity() {
    val apolloClient = ApolloClient.Builder()
        .serverUrl("https://rickandmortyapi.com/graphql")
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apollo_test)

        lifecycleScope.launch {
            val response = apolloClient.query(LocationsQuery()).execute()
            Log.d("LocationsQuery", "Success ${response.data}")
        }
    }
}