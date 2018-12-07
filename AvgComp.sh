#!/bin/bash
#echo Enter the command you would like to run
#read COMMAND

echo "Average Computational Time:"

for i in {1..5}
   do
     LD_LIBRARY_PATH="./" /usr/bin/time ./oplrun tarot/Tarot.mod tarot/Tarot.dat 2>&1 | grep -o '[0-9]\+maxresident' | sed s/maxresident//
   done
