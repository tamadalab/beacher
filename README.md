# beacher

[![Version](https://img.shields.io/badge/Version-v1.1.0-green)](https://github.com/tamadalab/beacher/releases/tag/v1.1.0)
[![License](https://img.shields.io/badge/License-Apache2.0-green)](https://github.com/tamadalab/beacher/blob/main/LICENSE)
[![Docker](https://img.shields.io/badge/Docker-v1.1.0-green?logo=docker)](https://github.com/tamadalab/beacher/pkgs/container/beacher/)


Detecting the build tools in use.

![beacher_logo](https://github.com/tamadalab/beacher/blob/main/images/logo.svg)

## :speaking_head: Description

This tool aims to detect the build tools in use for the project for surveying the share of the build tools.
The build tools build a project along with the rules defined in the build files.
The default names of the build files are fixed for each build tool.
This tool finds the build files from the specified directories, and identifies the build tools in use.

## :runner: Usage

```sh
Usage: java -jar beacher-1.1.0.jar [-hLV] [-@=INPUT] [--append-defs=DEFS_JSON] [-d=DEFS_JSON]
               [-f=FORMAT] [PROJECTs...]
A tool for detecting build tools of the projects
      [PROJECTs...]     The target project directories for beacher.
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
```

### Sample Output

```sh
$ java -jar beacher-1.1.0.jar . ../../AP/Java/HelloWorld/
.
  ./app/build.gradle: Gradle
../../AP/Java/HelloWorld
  ../../AP/Java/HelloWorld/build.xml: Apache Ant
  ../../AP/Java/HelloWorld/Makefile: Make
$ java -jar beacher-1.1.0.jar --format Json . ../../AP/Java/HelloWorld/ | jq .
{
  "base": ".",
  "build-tools": [
    {
      "file-path": "./app/build.gradle",
      "tool-name": "Gradle"
    }
  ]
}
{
  "base": "../../AP/Java/HelloWorld",
  "build-tools": [
    {
      "file-path": "../../AP/Java/HelloWorld/build.xml",
      "tool-name": "Apache Ant"
    },
    {
      "file-path": "../../AP/Java/HelloWorld/Makefile",
      "tool-name": "Make"
    }
  ]
}
```

## :whale: Docker

```sh
docker run --rm -it -v $PWD:/home/beacher ghcr.io/tamadalab/beacher:latest .
```

* Container OS
    * Working directory: `/home/beacher`
    * user: `beacher`


## :hammer_and_wrench: Related Tools

* [Licensee](https://github.com/licensee/licensee)
  * License detector for the projects.
* [linguist](https://github.com/github/linguist)
  * Programming languages detector for the projects.
