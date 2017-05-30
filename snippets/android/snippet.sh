#!/bin/sh

SRC=SampleProject/app/src/main/java/test/android/example/com/snippetsample/MainActivity.java

putCode() {
  TAG=$1
  FLAG=0
  INDENT=""
  cat $SRC | while IFS= read LINE;do
    case `echo $LINE` in
      "// CodeTagStart: "$TAG*)
          FLAG=1
          INDENT=${LINE%%/*}
          ;;
      "// CodeTagEnd: "$TAG*) break;;
      *) if [ $FLAG -ne 0 ] ; then
           printf "%s\n" "${LINE#$INDENT}"
         fi;;
    esac
  done
}

cat base.md | while IFS= read LINE;do
  case $LINE in
    "// UseCode: "*) putCode ${LINE#"// UseCode: "};;
    *) printf "%s\n" "$LINE";;
  esac
done

