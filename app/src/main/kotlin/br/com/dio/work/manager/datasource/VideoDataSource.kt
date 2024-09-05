package br.com.dio.work.manager.datasource

import android.content.res.AssetManager
import br.com.dio.work.manager.data.model.Video
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VideoDataSource {
    private val list by lazy { arrayListOf<Video>() }

    fun setupFromFile(assetManager: AssetManager) {
        val videosFromFile = Gson().fromFile<List<Video>>(assetManager, filename = "videos.json")
        list.addAll(videosFromFile)
    }

    private inline fun <reified T> Gson.fromFile(assetManager: AssetManager, filename: String): T {
        return fromJson(assetManager.open(filename).bufferedReader(), object : TypeToken<T>() {}.type)
    }

}