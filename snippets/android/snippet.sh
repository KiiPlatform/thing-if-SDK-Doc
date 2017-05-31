#!/bin/bash

SRC=SampleProject/app/src/main/java/test/android/example/com/snippetsample/MainActivity.java

FLAG=0
INDENT=""
while IFS= read LINE;do
  case `echo $LINE` in
    "// CodeTagStart: "*)
        FLAG=1
        INDENT=${LINE%%/*}
        TAG=${LINE#*"// CodeTagStart: "}
        printf "export %s=(" $TAG
        ;;
    "// CodeTagEnd: "*)
        printf ")\n"
        FLAG=0
        ;;
    *) if [ $FLAG -ne 0 ] ; then
         printf " '%s'" "${LINE#$INDENT}"
       fi;;
  esac
done < $SRC > src.data

tr -d '\r' < src.data > src2.data
mo --source=src2.data base.md
