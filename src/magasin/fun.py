def f(n):
  i = n
  while i > 0:
    g(i,i%2)
    i = i//2

def g(x,y):
  j = y
  while j > 0:
    h(j)
    if j%2 == 0:
      j = j - 2
    else:
      j = j + 1
  print(x)

def h(k):
  print(k * "jah ")

  