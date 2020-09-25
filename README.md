# multimc-wrapper

a webserver that handles instance events from multimc

# commands

```sh
# launch
curl http://127.0.0.1:20240/launch/?name=$INST_NAME&id=$INST_ID&dir=$INST_DIR&mcDir=$INST_MC_DIR

# exit
curl http://127.0.0.1:20240/exit/?name=$INST_NAME&id=$INST_ID&dir=$INST_DIR&mcDir=$INST_MC_DIR
```
