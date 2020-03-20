# World Reset for Nukkit
[![Build](https://img.shields.io/circleci/build/github/wode490390/WorldReset/master)](https://circleci.com/gh/wode490390/WorldReset/tree/master)
[![Release](https://img.shields.io/github/v/release/wode490390/WorldReset)](https://github.com/wode490390/WorldReset/releases)
[![Release date](https://img.shields.io/github/release-date/wode490390/WorldReset)](https://github.com/wode490390/WorldReset/releases)
[![MCBBS](https://img.shields.io/badge/-mcbbs-inactive)](https://www.mcbbs.net/thread-819930-1-1.html "世界重置")
<!--[![Servers](https://img.shields.io/bstats/servers/6811)](https://bstats.org/plugin/bukkit/WorldReset/6811)
[![Players](https://img.shields.io/bstats/players/6811)](https://bstats.org/plugin/bukkit/WorldReset/6811)-->

This plugin is used to automatically reset worlds. You can also use command to reset worlds.

If you found any bugs or have any suggestions, please open an issue on [GitHub Issues](https://github.com/wode490390/WorldReset/issues).

If you love this plugin, please star it on [GitHub](https://github.com/wode490390/WorldReset).

## Download
- [Releases](https://github.com/wode490390/WorldReset/releases)
- [Snapshots](https://circleci.com/gh/wode490390/WorldReset)

## Commands
| Command | Alias | Permission | Description | Default |
| - | - | - | - | - |
| /worldreset \<levelName\> [keepSeed] [keepRules] | `/wr` | worldreset.command | Resets a world | OP |

## Configurations

### config.yml
```yaml
worlds:
  # world name
  example_world1:
    # default is 10080 minutes (7 days)
    period: 10080
    keep-seed: false
    keep-gamerule: true
  example_world2:
    period: 10080
    keep-seed: false
    keep-gamerule: true
```

## Compiling
1. Install [Maven](https://maven.apache.org/).
2. Run `mvn clean package`. The compiled JAR can be found in the `target/` directory.

## Metrics Collection

This plugin uses [bStats](https://github.com/wode490390/bStats-Nukkit) - you can opt out using the global bStats config, see the [official website](https://bstats.org/getting-started) for more details.

<!--[![Metrics](https://bstats.org/signatures/bukkit/WorldReset.svg)](https://bstats.org/plugin/bukkit/WorldReset/6811)-->

###### If I have any grammar and terms error, please correct my wrong :)
