def kahanevad(n):
    j = [n]
    kahanevadRek(n, j)

def kahanevadRek(n, j):
    if(n == 0):
        for i in range(len(j)):
            print("(" + str(j[i]) + ")", end="")

    kahanevadRek(n-1, j[:] + [n-1])

    kahanevadRek(n-2, j[:] + [n-2])



if __name__ == "__main__":
    kahanevad(5)