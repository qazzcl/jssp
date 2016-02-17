import math
import time
import datetime
from numpy import random
from threading import Thread, RLock, local
from multiprocessing import Process, Value
import matplotlib.pyplot as plt

s0 = 100.0  # initial value of the stock price
mu = 0.03   # the expected return in a risk-neutral world
rf = 0.03
rq = 0.02
sigma = 0.1  # volatility
dt = 1.0 / (365.0 * 24 * 60 * 60 * 100)

flag = True

time_series = []
local_data = local()


def f1(e):
    return (mu - sigma * sigma * 0.5) * dt + sigma * e * math.sqrt(dt)


def gen_drift():
    global mu
    while 1:
        while flag:
            mu = random.normal() / 10.0
            print 'mu: ' + str(mu)
            time.sleep(20)
        time.sleep(20)


def gen_diffusion():
    global sigma
    while 1:
        while flag:
            sigma = random.normal() / 5.0
            print 'sigma: ' + str(sigma)
            time.sleep(40)
        time.sleep(40)


def gen_price():
    global s0
    while 1:
        while flag:
            e = random.normal()
            s0 *= math.exp(f1(e))
            # print s0
            time.sleep(0.01)
        time.sleep(0.01)


def reload_thread():
    global flag
    while 1:
        t = datetime.datetime.now()
        sleep_time = None
        # if t.hour == 17 and 0 <= t.minute <= 4:
        #     print '##############################################################################'
        #     sleep_time = datetime.datetime(t.year, t.month, t.day, 17, 5, 0, 0) - t
        #     flag = True
        # elif t.hour == 17 and 10 <= t.minute <= 14:
        #     print '##############################################################################'
        #     sleep_time = datetime.datetime(t.year, t.month, t.day, 17, 15, 0, 0) - t
        #     flag = False
        # elif t.hour == 17 and 20 <= t.minute <= 24:
        #     print '##############################################################################'
        #     sleep_time = datetime.datetime(t.year, t.month, t.day, 17, 25, 0, 0) - t
        #     flag = True
        # elif t.hour == 17 and 30 <= t.minute <= 34:
        #     print '##############################################################################'
        #     sleep_time = datetime.datetime(t.year, t.month, t.day, 17, 35, 0, 0) - t
        #     flag = False
        # print flag
        if t.hour == 9 and 20 <= t.minute <= 29:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 9, 30, 0, 0) - t
            temp_flag = True
        elif t.hour == 11 and 20 <= t.minute <= 29:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 11, 30, 0, 0) - t
            temp_flag = False
        elif t.hour == 12 and 50 <= t.minute <= 59:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 13, 0, 0, 0) - t
            temp_flag = True
        elif t.hour == 14 and 50 <= t.minute <= 59:
            print '##############################################################################'
            sleep_time = datetime.datetime(t.year, t.month, t.day, 15, 0, 0, 0) - t
            temp_flag = False
        print flag

        if sleep_time is not None:
            print sleep_time.total_seconds()
            time.sleep(int(sleep_time.total_seconds()))
            flag = temp_flag
        time.sleep(10)
        # time.sleep(600)


def print_s0():
    while 1:
        print datetime.datetime.now()
        print '****************' + str(s0)
        print flag
        print ''
        time_series.append(s0)
        time.sleep(30)


if __name__ == '__main__':
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
