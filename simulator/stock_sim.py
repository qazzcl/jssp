import math
import time
import datetime
from numpy import random
from threading import Thread
import matplotlib.pyplot as plt

s0 = 100.0  # initial value of the stock price
mu = 0.03   # the expected return in a risk-neutral world
rf = 0.03
rq = 0.02
sigma = 0.1  # volatility
dt = 1.0 / (365.0 * 24 * 60 * 60 * 100)

flag = True

time_series = []


def f1(e):
    return (mu - sigma * sigma * 0.5) * dt + sigma * e * math.sqrt(dt)


def gen_drift():
    global mu, flag
    while(flag):
        mu = random.normal() / 10.0
        time.sleep(20)


def gen_diffusion():
    global sigma, flag
    while(flag):
        sigma = random.normal() / 5.0
        time.sleep(40)


def gen_price():
    global s0, flag
    while(flag):
        e = random.normal()
        s0 *= math.exp(f1(e))
        # print s0
        time.sleep(0.01)


def reload_thread():
    global flag
    while 1:
        t = datetime.datetime.now()
        sleep_time = None
        if t.hour == 9 and 20 <= t.minute <= 29:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 9, 30, 0, 0) - t
            flag = True
        elif t.hour == 11 and 20 <= t.minute <= 29:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 11, 30, 0, 0) - t
            flag = False
        elif t.hour == 12 and 50 <= t.minute <= 59:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 13, 0, 0, 0) - t
            flag = True
        elif t.hour == 14 and 50 <= t.minute <= 59:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 15, 0, 0, 0) - t
            flag = False 

        if sleep_time is not None:
            print sleep_time.total_seconds()
            time.sleep(float(sleep_time.total_seconds()))
        time.sleep(600)


def print_s0():
    while 1:
        print datetime.datetime.now()
        print '****************' + str(s0)
        time_series.append(s0)
        time.sleep(300)


if __name__ == '__main__':
    print 'start: '
    reload = Thread(target=reload_thread)
    reload.start()

    drift_generator = Thread(target=gen_drift)
    drift_generator.start()

    diffusion_generator = Thread(target=gen_diffusion)
    diffusion_generator.start()


    price_generator = Thread(target=gen_price)
    price_generator.start()

    print_thread = Thread(target=print_s0)
    print_thread.start()

    # n = 10000
    # while n > 0:
    #     e = random.normal()
    #     s0 *= math.exp(f1(e))
    #     # time_series.append(s0)
    #     print str(s0) + ', mu: ' + str(mu) + ', sigma: ' + str(sigma)
    #     time.sleep(0.1)
    #     n -= 1
    #
    # plt.plot(time_series)
    # plt.show()
