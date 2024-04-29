#! /bin/bash


rm *.so
rm *.dylib
rm *.o

cd ../../fortran/linux64/
gfortran -c -fPIC *.FOR

cd ../../src/c/mac64/
g++ -c -fpic -I/Library/Java/JavaVirtualMachines/jdk1.8.0_20.jdk/Contents/Home/include/ -I/Library/Java/JavaVirtualMachines/jdk1.8.0_20.jdk/Contents/Home/include/darwin smerfLib_sMERFLib.cpp


cd ../../../lib/mac64/
rm *.dylib
g++ -shared -o libsMERFLib.dylib  ../../src/fortran/linux64/*.o ../../src/c/mac64/*.o -L/usr/local/gfortran/lib/ -lgfortran


