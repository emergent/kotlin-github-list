package org.example

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Usage: kotlin-github-list <username> [github-token]")
        println("  username: GitHub username to fetch repositories for")
        println("  github-token: Optional personal access token for authenticated requests")
        exitProcess(1)
    }

    val username = args[0]
    val token = if (args.size > 1) args[1] else null

    val client = GitHubClient()

    try {
        println("Fetching repositories for user: $username")
        val repositories = client.getRepositories(username, token)

        if (repositories.isEmpty()) {
            println("No repositories found for user: $username")
            return
        }

        println("\nRepositories for $username:")
        println("${"─".repeat(80)}")

        repositories.forEach { repo ->
            println("Name: ${repo.name}")
            println("Full Name: ${repo.full_name}")
            repo.description?.let { println("Description: $it") }
            println("URL: ${repo.html_url}")
            repo.language?.let { println("Language: $it") }
            println("Stars: ${repo.stargazers_count}, Forks: ${repo.forks_count}")
            println("Last Updated: ${repo.updated_at}")
            println("${"─".repeat(80)}")
        }

        println("\nTotal: ${repositories.size} repositories")
    } catch (e: Exception) {
        println("Error fetching repositories: ${e.message}")
        exitProcess(1)
    }
}
