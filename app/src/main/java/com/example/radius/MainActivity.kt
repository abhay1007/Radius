package com.example.radius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radius.adapters.FacilityAdapter
import com.example.radius.model.Facility
import com.example.radius.network.ApiService
import io.paperdb.Paper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var facilitiesRecyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Paper.init(this)
        facilitiesRecyclerView = findViewById(R.id.facilitiesRecyclerView)
        facilitiesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Sample data

        val facilitiesLiveData = getFacilities()
        facilitiesLiveData.observe(this, Observer { facilities ->
            // Update the UI with the facilities data
            val adapter = FacilityAdapter(facilities, this)
            facilitiesRecyclerView.adapter = adapter
        })

// If there are stored facilities, update the UI immediately
        val storedFacilities = getStoredFacilities()
        if (storedFacilities.isNotEmpty()) {
            val adapter = FacilityAdapter(storedFacilities, this)
            facilitiesRecyclerView.adapter = adapter
        }

    }

    private fun getFacilities(): LiveData<List<Facility>> {
        val facilitiesLiveData = MutableLiveData<List<Facility>>()

        val storedFacilities = getStoredFacilities()
        val lastUpdateTime = Paper.book().read<Long>("lastUpdateTime", 0)

        // Check if the data is available in PaperDB and if it was updated within the last day
        val shouldFetchFromApi = storedFacilities.isEmpty() || System.currentTimeMillis() - lastUpdateTime!! > TimeUnit.DAYS.toMillis(1)

        if (shouldFetchFromApi) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            apiService.getFacilities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    // Handle the successful response
                    val facilities = response.facilities
                    facilitiesLiveData.value = facilities

                    // Save the response to PaperDB
                    Paper.book().write("facilities", facilities)
                    Paper.book().write("lastUpdateTime", System.currentTimeMillis())
                }, { error ->
                    // Handle the error
                })
        } else {
            // Use the stored facilities
            facilitiesLiveData.value = storedFacilities
        }

        return facilitiesLiveData
    }


    private fun getStoredFacilities(): List<Facility> {
        return Paper.book().read("facilities", emptyList())!!
    }

}
