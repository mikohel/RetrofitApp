package com.moviles.retrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.moviles.retrofitapp.remote.PokemonEntry
import com.moviles.retrofitapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("27")

        retrofit.enqueue(object : Callback<PokemonEntry>{
            override fun  onResponse(call: Call<PokemonEntry>,response: Response<PokemonEntry>) {
                val resBody = response.body()
                if (resBody!=null){
                    Log.d("retrofitresponse", "res:${response.body()}")
                    Log.d("retrofitresponse", "name:${resBody.name}")
                    for (stat in resBody.stats){
                        Log.d("retrofitresponse", "stats: ${stat.stat.stat_value}: ${stat.base_stat}")
                    }
                    Log.d("retrofitresponse","type:${resBody.type[0].type.name}")
                    Log.d("retrofitresponse","PokeSprite: ${resBody.sprites.front_default}")
            }

            }
            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.e("retrofitresponse","error: ${t.message}")
            }

        })
    }
}