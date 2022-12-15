# beacher

## プロジェクトのビルドツールを探し出して列挙する


### 使用できる出力形式
Json, Yaml, Xml, Defalut

#### コマンド一覧 (Command List)
  -@=INPUT              Specify the file contains project path list. If INPUT
                          is dash ('-'), read from STDIN.
      --append-defs=DEFS_JSON
                        Specify the additional definitions of the build tools.
  -d, --definition=DEFS_JSON
                        Specify the definition of the build tools.
  -f, --format=FORMAT   Specify the output format [default: Default] [possible
                          values: Default, Json, Xml, Yaml]
  -h, --help            Show this help message and exit.
  -L, --list-defs       Print the build tools' definition list
      --no-ignore       Do not respect ignore files (.ignore, .gitignore, etc.)
  -V, --version         Print version information and exit.

#### 使用例 (Examples of Use)
java -jar app/build/libs/app.jar -f Yaml .    ('.'is project's directory)
java -jar app/build/libs/app.jar -h    (help message)
java -jar app/build/libs/app.jar -L    (print definition list)
