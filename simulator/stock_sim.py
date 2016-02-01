import math
import time
from numpy import random
from threading import Thread
import matplotlib.pyplot as plt

s0 = 100.0  # initial value of the stock price
mu = 0.03   # the expected return in a risk-neutral world
rf = 0.03
rq = 0.02
sigma = 0.1  # volatility
dt = 1.0 / (365.0 * 24 * 12)

time_series = []


def f1(e):
    return (mu - sigma * sigma * 0.5) * dt + sigma * e * math.sqrt(dt)


def gen_drift():
    global mu
    while(1):
        mu = random.normal() / 10.0
        time.sleep(20)

def gen_diffusion():
    global sigma
    while(1):
        sigma = random.normal() / 5.0
        time.sleep(40)


if __name__ == '__main__':
    print 'start: '
    drift_generator = Thread(target=gen_drift)
    drift_generator.start()

    diffusion_generator = Thread(target=gen_diffusion)
    diffusion_generator.start()

    n = 10000
    while n > 0:
        e = random.normal()
        s0 *= math.exp(f1(e))
        time_series.append(s0)
        print str(s0) + ', mu: ' + str(mu) + ', sigma: ' + str(sigma)
        time.sleep(0.1)
        n -= 1

    plt.plot(time_series)
    plt.show()
