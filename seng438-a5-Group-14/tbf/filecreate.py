import os

def touch(fname, iter):
    for i in range(iter):
        filename = fname + str(i) + '.txt'
        with open(filename, 'a'):
            os.utime(filename, None)
            
touch('report',10)