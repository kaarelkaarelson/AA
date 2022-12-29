from magasin import *

def pööratud_poola(tehe):
    arvud = Stack()
    tehtemärgid = Stack()

    for i in range(len(tehe)):
        if tehe[i].isalpha:
            tehtemärgid.push(tehe[i])
        else:
            arvud.push(tehe[i])


if __name__ == "__main__":
    tulemus = pööratud_poola("8-(4+6)*3+1")


