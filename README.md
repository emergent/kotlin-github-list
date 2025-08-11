# Kotlin GitHub List

GitHub APIを使用してユーザーのリポジトリ一覧を取得・表示するKotlin製のCLIツールです。

## 機能

- 指定したGitHubユーザーの公開リポジトリ一覧を取得
- リポジトリの詳細情報を表示（名前、説明、URL、言語、スター数、フォーク数、最終更新日）
- GitHubアクセストークンによる認証対応（プライベートリポジトリも取得可能）
- コードの品質管理（ktlintによるリンター・フォーマッター）

## 前提条件

- Java 21以上
- Git（オプション）

## セットアップ

1. プロジェクトをクローン（またはダウンロード）
```bash
git clone <repository-url>
cd kotlin-github-list
```

2. 実行権限を付与
```bash
chmod +x ./gradlew
```

## 使用方法

### 基本的な使用方法

公開リポジトリのみを取得する場合：

```bash
./gradlew run --args="<ユーザー名>"
```

例：
```bash
./gradlew run --args="octocat"
```

### GitHubトークンを使用した認証

プライベートリポジトリも含めて取得する場合や、APIレート制限を緩和したい場合：

```bash
./gradlew run --args="<ユーザー名> <GitHubトークン>"
```

例：
```bash
./gradlew run --args="octocat ghp_xxxxxxxxxxxxxxxxxxxx"
```

### GitHubトークンの取得方法

1. GitHubにログインし、Settings > Developer settings > Personal access tokens > Tokens (classic) にアクセス
2. "Generate new token (classic)" をクリック
3. 必要なスコープを選択（`repo`スコープを推奨）
4. トークンを生成してコピー

## 開発用コマンド

### ビルド
```bash
./gradlew build
```

### テスト実行
```bash
./gradlew test
```

### コードフォーマット
```bash
./gradlew ktlintFormat
```

### リントチェック
```bash
./gradlew ktlintCheck
```

## 出力例

```
Fetching repositories for user: octocat

Repositories for octocat:
────────────────────────────────────────────────────────────────────────────────
Name: Hello-World
Full Name: octocat/Hello-World
Description: My first repository on GitHub!
URL: https://github.com/octocat/Hello-World
Stars: 3068, Forks: 3450
Last Updated: 2025-08-11T09:52:29Z
────────────────────────────────────────────────────────────────────────────────
Name: Spoon-Knife
Full Name: octocat/Spoon-Knife
Description: This repo is for demonstration purposes only.
URL: https://github.com/octocat/Spoon-Knife
Language: HTML
Stars: 13158, Forks: 152622
Last Updated: 2025-08-11T09:52:27Z
────────────────────────────────────────────────────────────────────────────────

Total: 8 repositories
```

## 技術仕様

- **言語**: Kotlin
- **ビルドツール**: Gradle
- **HTTPクライアント**: OkHttp
- **JSON解析**: Moshi
- **リンター/フォーマッター**: ktlint
- **テストフレームワーク**: JUnit 5

## プロジェクト構成

```
kotlin-github-list/
├── app/
│   ├── src/
│   │   ├── main/kotlin/org/example/
│   │   │   ├── App.kt                # メインアプリケーション
│   │   │   └── GitHubClient.kt       # GitHub API クライアント
│   │   └── test/kotlin/org/example/
│   │       └── AppTest.kt            # ユニットテスト
│   └── build.gradle.kts              # ビルド設定
├── gradle/
├── gradlew                           # Gradle Wrapper
├── gradlew.bat
└── README.md
```

## エラー対応

### API レート制限に達した場合
```
Error fetching repositories: GitHub API request failed: 403 rate limit exceeded
```
→ GitHubトークンを使用して認証してください

### ユーザーが見つからない場合
```
Error fetching repositories: GitHub API request failed: 404 Not Found
```
→ ユーザー名のスペルを確認してください

### ネットワークエラー
```
Error fetching repositories: Failed to connect to api.github.com
```
→ インターネット接続を確認してください

## ライセンス

このプロジェクトはMITライセンスの下で公開されています。

## 貢献

プルリクエストや課題報告を歓迎します。開発に貢献する場合は、以下の点にご注意ください：

1. コードを変更する前に `./gradlew ktlintFormat` でフォーマットしてください
2. `./gradlew ktlintCheck` でリントエラーがないことを確認してください
3. `./gradlew test` でテストが通ることを確認してください