#! /bin/bash

pushd src

pushd fortran/linux64
rm -f *.o
gfortran -c -fPIC *.FOR
popd

pushd c/linux64
rm -f *.o
g++ -c -fpic -I/usr/lib/jvm/java-8-oracle/include -I/usr/lib/jvm/java-8-oracle/include/linux smerfLib_sMERFLib.cpp
popd
popd

pushd lib/linux64
rm -f *.so
g++ -shared -o libsMERFLib.so ../../src/fortran/linux64/*.o ../../src/c/linux64/*.o -lgfortran

