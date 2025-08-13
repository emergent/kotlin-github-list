package org.nitamago.ghlist

import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

@JsonClass(generateAdapter = true)
data class Repository(
    val name: String,
    val full_name: String,
    val description: String?,
    val html_url: String,
    val language: String?,
    val stargazers_count: Int,
    val forks_count: Int,
    val updated_at: String,
)

class GitHubClient {
    private val client = OkHttpClient()
    private val moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    fun getRepositories(
        username: String,
        token: String? = null,
    ): List<Repository> {
        val url = "https://api.github.com/users/$username/repos?sort=updated&per_page=100"

        val requestBuilder = Request.Builder().url(url)

        token?.let {
            requestBuilder.addHeader("Authorization", "token $it")
        }

        requestBuilder.addHeader("Accept", "application/vnd.github.v3+json")

        val request = requestBuilder.build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) {
                throw IOException("GitHub API request failed: ${response.code} ${response.message}")
            }

            val responseBody =
                response.body?.string()
                    ?: throw IOException("Empty response from GitHub API")

            val adapter =
                moshi.adapter<List<Repository>>(
                    Types.newParameterizedType(
                        List::class.java,
                        Repository::class.java,
                    ),
                )

            return adapter.fromJson(responseBody)
                ?: throw IOException("Failed to parse GitHub API response")
        }
    }
}
