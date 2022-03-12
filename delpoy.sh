 #!/bin/sh
 
echo "=========git pull======="
git   pull

echo "=========gitbook build=======" 
gitbook  build

echo "=========deploy======="
git add . && git commit -m "deploy" && git push

#echo "=========git serve======="
#gitbook serve
