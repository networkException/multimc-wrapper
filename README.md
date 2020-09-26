# multimc-wrapper

a webserver that handles instance events from multimc

# commands

```sh
# launch
curl http://127.0.0.1:20240/launch/?name=$INST_NAME&id=$INST_ID&dir=$INST_DIR&mcDir=$INST_MC_DIR

# exit
curl http://127.0.0.1:20240/exit/?name=$INST_NAME&id=$INST_ID&dir=$INST_DIR&mcDir=$INST_MC_DIR

# change function in /opt/multimc/run.sh
runmmc() {
    wrapper="/path/to/your/multimc-wrapper-0.0.1.jar"
    cd ${INSTDIR}
    trap "pkill -f 'java -jar $wrapper'" INT TERM ERR
    trap "pkill -f 'java -jar $wrapper'" EXIT

    java -jar $wrapper &
    ./MultiMC "$@"
}
```
