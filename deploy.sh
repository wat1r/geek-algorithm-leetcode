#!/bin/sh
 
git pull

gitbook  build

git add .  &&  git commit -m "deploy" && git push

#echo "=========git serve======="
#gitbook serve
