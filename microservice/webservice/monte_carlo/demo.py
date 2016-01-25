import math
import numpy as np
from numpy import random

# r = 0.05  # risk-free interest rate
s0 = 10.0  # initial value of the stock price
mu = 0.1  # the expected return in a risk-neutral world
sigma = 0.1  # volatility
dt = 1.0 / 365.0

f0 = math.log(s0)


def f1(e):
    return (mu - sigma * sigma * 0.5) * dt + sigma * e * math.sqrt(dt)


def simulate(n, m):
    l = []
    for i in range(n):
        f = f0
        for j in range(m):
            e = random.normal()
            f += f1(e)

        l.append(f)

    return l


lst = simulate(100, 365)

sum = 0.0
for f in lst:
    sum += f

f = sum / len(lst)

print math.exp(f)
