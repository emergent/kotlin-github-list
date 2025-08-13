package org.nitamago.ghlist

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun testGitHubClientCreation() {
        val client = GitHubClient()
        assertNotNull(client, "GitHubClient should be created successfully")
    }

    @Test
    fun testRepositoryDataClass() {
        val repo =
            Repository(
                name = "test-repo",
                full_name = "user/test-repo",
                description = "Test repository",
                html_url = "https://github.com/user/test-repo",
                language = "Kotlin",
                stargazers_count = 10,
                forks_count = 5,
                updated_at = "2024-01-01T00:00:00Z",
            )
        assertNotNull(repo, "Repository object should be created successfully")
    }
}
