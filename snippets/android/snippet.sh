#!/bin/bash

SRC=SampleProject/app/src/main/java/test/android/example/com/snippetsample/MainActivity.java

FLAG=0
INDENT=""
while IFS= read LINE;do
  STR=${LINE%""}
  case `echo $STR` in
    "// CodeTagStart: "*)
        FLAG=1
        INDENT=${STR%%/*}
        TAG=${STR#*"// CodeTagStart: "}
        ARRAY="("
        ;;
    "// CodeTagEnd: "*)
        ARRAY+=" )"
        FLAG=0
        declare -a -x $TAG="${ARRAY}"
        ;;
    *) if [ $FLAG -ne 0 ] ; then
         ARRAY+=" '${STR#$INDENT}'"
       fi;;
  esac
done < $SRC

. mo
mo base.md
