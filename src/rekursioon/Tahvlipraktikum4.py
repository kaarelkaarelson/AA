
# 1 haru
def f1(hinnad, raha, i = 0): # i - mitmenda hinna juures oleme
    if i == len(hinnad):
        return 0
    if raha < hinnad[i]:
        return 0

    return hinnad[i] + f1(hinnad, raha - hinnad[i], i+1)
# 2 haru
def f2(hinnad, raha, i = 0): # i - mitmenda hinna juures oleme
    if i == len(hinnad):
        return 0
    if hinnad[i] <= raha: # Kui rohkem või täpselt raha
        ostmata = f2(hinnad, raha, i+1) # ei osta
        ostes = hinnad[i] + f2(hinnad, raha-hinnad[i], i+1) # ostame
        return max(ostmata, ostes)
    else:
        return f2(hinnad, raha, i+1) # Kui raha pole, siis vaatame edasi.

# 3 haru - mis siis kui ostame 0, 1, 2
def f3(hinnad, raha, i = 0): # i - mitmenda hinna juures oleme
    if i == len(hinnad):
        return 0

    valikud = [] # Siia salvestame mitu ostes kui palju kulub ostes 1, 2 ,3 eset.
    for mitu in range(0, 3): # iga tsükli samm teeb määrab mitu ostes
        kulu = mitu * hinnad[i]
        if kulu <= raha:
            valikud.append(kulu + f3(hinnad, raha - kulu, i+1))

    return max(valikud)

# n haru
def fn(hinnad, raha, i = 0): # i - mitmenda hinna juures oleme
    if i == len(hinnad):
        return 0

    valikud = [] # Siia salvestame mitu ostes kui palju kulub ostes 1, 2 ,3 eset.
    mitu_võimalik = raha // hinnad[i]
    for mitu in range(0, mitu_võimalik + 1): # iga tsükli samm teeb määrab mitu ostes
        kulu = mitu * hinnad[i]
        if kulu <= raha:
            valikud.append(kulu + fn(hinnad, raha - kulu, i+1))

    return max(valikud)

# summa1 = f1(antud_hinnad, 18)
# print(summa1)
# summa2 = f2(antud_hinnad, 16)
# print(summa2)

if __name__ == "__main__":
    antud_hinnad = [6, 3, 10]
    summaN = fn(antud_hinnad, 15)
    print(summaN)
