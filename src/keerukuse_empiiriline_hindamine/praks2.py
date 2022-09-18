import random
import time


# https://www.geeksforgeeks.org/python-program-for-quicksort/


def _eralda_indeksitega(algus, lõpp, massiiv):
    x, mitu_väiksemat = massiiv[lõpp], algus
    
    for i in range(algus, lõpp):
        if massiiv[i] == x:
            massiiv[i], massiiv[mitu_väiksemat] = massiiv[mitu_väiksemat], massiiv[i]
            mitu_väiksemat += 1
    
    massiiv[mitu_väiksemat], massiiv[lõpp] = massiiv[lõpp], massiiv[mitu_väiksemat]
    # return mitu_väiksemat


def _quicksort_indeksitega(algus, lõpp, massiiv):
    if len(massiiv) == 1:
        return massiiv
    if algus < lõpp:
        pi = _eralda_indeksitega(algus, lõpp, massiiv)
        _quicksort_indeksitega(algus, pi - 1, massiiv)
        _quicksort_indeksitega(pi + 1, lõpp, massiiv)
    return massiiv


def quicksort_indeksitega(massiiv):
    return _quicksort_indeksitega(0, len(massiiv) - 1, massiiv)


def _eralda_kopeerimisega(massiiv):
    x, mitu_väiksemat = massiiv[len(massiiv) - 1], 0
    
    for i in range(len(massiiv) - 1):
        if massiiv[i] <= x:
            massiiv[i], massiiv[mitu_väiksemat] = massiiv[mitu_väiksemat], massiiv[i]
            mitu_väiksemat += 1
    
    massiiv[mitu_väiksemat], massiiv[len(massiiv) - 1] = massiiv[len(massiiv) - 1], massiiv[mitu_väiksemat]
    return mitu_väiksemat


def quicksort_kopeerimisega(massiiv):
    if len(massiiv) == 1:
        return massiiv
    if len(massiiv) > 1:
        pi = _eralda_kopeerimisega(massiiv)
        massiiv[:pi] = quicksort_kopeerimisega(massiiv[:pi])
        massiiv[(pi + 1):] = quicksort_kopeerimisega(massiiv[(pi + 1):])
    return massiiv


def juhuslik_massiiv(n, a, b):
    return [random.randint(a, b - 1) for _ in range(n)]


def fibo_rek(n):
    if n < 3:
        return 1
    return fibo_rek(n - 1) + fibo_rek(n - 2)


def yl2b():
    for i in range(1, 11):
        n = 10000 * i
        m = juhuslik_massiiv(n, 10, 100)
        start = time.perf_counter()
        quicksort_kopeerimisega(m)
        stopp = time.perf_counter()
        print((stopp - start) * 1000)
    
    for i in range(1, 11):
        n = 10000 * i
        m = juhuslik_massiiv(n, 10, 100)
        start = time.perf_counter()
        quicksort_indeksitega(m)
        stopp = time.perf_counter()
        print((stopp - start) * 1000)


def yl2e():
    for i in range(1, 41):
        start = time.perf_counter()
        fibo_rek(i)
        stopp = time.perf_counter()
        print((stopp - start) * 1000)

yl2b()