#!/bin/sh
#/apps-friends-service/bin/apps-friends-service db status config.yml
#mkdir dumps
#/apps-friends-service/bin/apps-friends-service db dump config.yml > "dumps/$(date +%Y-%m-%d_%H-%M-%S)"
#/apps-friends-service/bin/apps-friends-service db tag config.yml "$(date +%Y-%m-%d_%H-%M-%S)"
/apps-friends-service/bin/apps-friends-service db migrate config.yml
#/apps-friends-service/bin/apps-friends-service db status config.yml
/apps-friends-service/bin/apps-friends-service server config.yml

