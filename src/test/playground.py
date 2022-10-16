import time

import abi.AbiPy

printimisi = 0

def korruta(a, b):
    if b == 1:
        return a
    return a + korruta(a, b-1)

def sort1(a): # Ei ole stabiilne
    N = len(a)
    for i in range(N-1, 1, -1):
        for j in range(i):
            if a[i][0] < a[j][0]:
                a[i], a[j] = a[j], a[i]

def sort2(a): # Ei ole stabiilne
    N = len(a)
    for i in range(N):
        jmin = i
        for j in range(i+1, N):
            if a[j][0] < a[jmin][0]:
                jmin = j
        a[i], a[jmin] = a[jmin], a[i]

def sort3(a): # Stabiilne
    N = len(a)
    for i in range(N):
        for j in range(N-1):
            if a[j+1][0] < a[j][0]:
                a[j], a[j+1] = a[j+1], a[j]

def sort4(a): # Stabiilne
    N = len(a)
    for i in range(N):
        for j in range(i, 0, -1):
            if a[j][0] < a[j-1][0]:
                a[j-1], a[j] = a[j], a[j-1]
            else:
                break



def tõsta(n, kust, kuhu, ajutine):
    if n == 1:
        global printimisi
        print("Tõsta ketas tornist", kust, "torni", kuhu + ".")
        printimisi += 1
    else:
        tõsta(n-1, kust, ajutine, kuhu)
        tõsta(1, kust, kuhu, ajutine)
        tõsta(n-1, ajutine, kuhu, kust)

def seljakott(a, k):
    if len(a) == 0 or k <= 0:
        return []
    parim = []
    parim_summa = 0
    for i in range(len(a)):
        b = [a[i]] + seljakott(a[:i]+a[i+1:], k-a[i])
        s = sum(b)
        if s > parim_summa and s <= k:
            parim = b
            parim_summa = s
    return parim


def mod2(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return mod2(n-2)

# def f(a, b):
#     if b == 0:
#         return a
#     return 1 + f(a, b-1)

def pal(s):
    if len(s) <= 1:
        return True
    return s[0]==s[-1]

def paaris(n):
    if n == 0:
        return True
    return not paaritu(n-1)

def paaritu(n):
    if n == 0:
        return True
    return not paaris(n-1)

def tag(sõne):
    if len(sõne) == 0:
        return
    tag(sõne[1:])
    print(sõne[0], end=' ')

def tag_a(sõne):
    if len(sõne) == 0:
        return
    print(sõne[0], end=' ')
    tag_b(sõne[1:])

def tag_b(sõne):
    if len(sõne) == 0:
        return
    tag_a(sõne[1:])
    print(sõne[0], end=' ')
def f(n):
    if n < 0:
        return False
    if n == 0:
        return True
    return f(n-1)

def f1(n):
    f1(n//2)
    print(n%2, end='')

def f2(n):
    if n > 0:
        f2(n-1)
        print(n%2, end='')

def f3(n):
    if n > 0:
        print(n%2, end='')
        f3(n//2)

def f4(n):
    if n > 0:
        f4(n//2)
        print(n%2, end='')

def f5(n):
    if n == 0:
        print(0)
    elif n == 1:
        print(1)
    else:
        f5(n//2)

def fibo_rek(n):
    if n < 3:
        return 1
    global printimisi
    printimisi += 2
    return fibo_rek(n-1) + fibo_rek(n-2)

def dna_molekuli_generaator(n, genoomilõik = ""):
    if len(genoomilõik) == n:
        print(genoomilõik)
    else:
        for nukleotiid in ["A", "T", "C", "G"]:
            dna_molekuli_generaator(n, genoomilõik  + nukleotiid)
if __name__ == "__main__":
    # f = open("nimed.txt", "r")
    # for rida in f:
    #     print(rida)
    # a = "Py/thon"
    # print("Parool:" + a)
    # b= a[3:6+1]
    # print(b)
    # fibo_rek(15)
    # print(printimisi)
    # dna_molekuli_generaator(3)
    # print(fibo_rek(11))
    # print(printimisi, "\n")
    # print(f(0.5))
    # print(paaris(3))
    # tag("ALGORITM")
    # print(f(-2, 3))
    # print(korruta(1, -55))
    # # print(f(0.5, 2.25))
    # tag_a("ALGORITM")
    # print(f(1.5))
    #   tag_b("ALGORITM")
    # f1(2)
    # n = 8
    # f2(n)
    # print("\n")
    # f3(n)
    # print("\n")
    # f4(n)
    # print("\n")
    # f5(n)

    # s = "aja"
    # s = s[1:-1]
    # print( s[0]==s[-1])
    # print(s[1:-1])
    # print(pal("SaS"))
    # tõsta(10, "A", "B", "C")
    # print(printimisi)
    # print(paaris(1))
    # tudengid = [1, 2, 3, 4]
    tudengid = [[3, 1], [1, 1], [2, 1], [3, 2], [1, 2], [2, 2],[1, 3], [1, 4]]
    A = {}
    sort2(tudengid)
    print(tudengid)
    # tag("MATEMAATIKA")
    # print(tudengid, "\n", uus)
    # uus = tudengid[:1] + tudengid[1+1:]
    # n = [6, 12, 24]
    # for i in range(len(n)):
    #     j = abi.AbiPy.gen_järjend(n[i])
    #     start_time = time.time_ns()
    #     seljakott(j, len(j))
    #     end_time = time.time_ns()
    #     print("Aega kulus:", end_time-start_time)
    # print(mod2(0))
    # print(korruta(2, 5))
    # print(f(5.5, 7.5))
    # print(pal("SiaS"))