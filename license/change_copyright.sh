cd ../
mvn license:format
for filePath in $(find . -name "*.java" -not -iwholename "*/target/*"); do
    echo "$filePath"
    YEAR=`git log --reverse "$filePath" | grep Date | grep -o -m 1 -E '20[0-9]{2}'`
    sed -i -e "s@COPYRIGHT_YEAR@${YEAR}@g" "$filePath"
done
read -p "Hit enter: "
