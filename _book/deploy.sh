#!/bin/bash
echo "=========git pull======="
git pull

echo "=========gitbook build======="
gitbook  build

echo "=========git push======="
git add .  &&  git commit -m "deploy" && git push

#echo "=========gitbook  serve======="
#gitbook serve
