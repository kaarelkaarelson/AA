import random

def gen_järjend(n):
    tulemus = []
    for i in range(n):
        tulemus.append(random.randint(0, 10))

    return tulemus