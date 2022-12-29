import sys

def loe_fail(failinimi):
    f = open(failinimi, encoding="UTF-8")
    read = f.readlines()
    järjend = []

    for rida in read:
        korrastatud = rida.strip()
        andmed = korrastatud.split(',')
        järjend.append(andmed)
        print(andmed)

    f.close()
    return järjend


def leia_odavaim_takso(järjend, teepikkus):
    odavaim_takso = ''
    odavaim_hind= sys.maxsize

    for i in range(len(järjend)):
        takso_nimi = järjend[i][0]
        istumise_hind = float(järjend[i][1].strip())
        kilomeetri_hind = float(järjend[i][2].strip())
        sõiduhind = arvuta_sõiduhind(istumise_hind, kilomeetri_hind, teepikkus)

        if sõiduhind < odavaim_hind:
            odavaim_hind = sõiduhind
            odavaim_takso = takso_nimi

    return odavaim_takso

def arvuta_sõiduhind(istumise_hind, kilomeetri_hind, teepikkus):
    return istumise_hind + kilomeetri_hind*teepikkus

if __name__ == "__main__":
    while True:
        teepikkus = input("Sisesta tee pikkus: ")
        #if not teepikkus.isdigit():
         #   raise ValueError(f"Sisend pole number, sisestati '{teepikkus}', mille pikkus on {len(teepikkus)}")
        taksode_andmed = loe_fail('taksohinnad.txt')
        hind = leia_odavaim_takso(taksode_andmed, float(teepikkus))
        print(hind)