def probability(data, cls_val, cls_name="class"):
    return len(filter(lambda x: x[cls_name] == cls_val, data)) * 1.0 / len(data)


# Calculate the Prob(attr|cls)
def PT(data, cls_val, attr_name, attr_val, cls_name="class"):
    cnt1 = 0.0
    cnt2 = 0.0
    for e in data:
        if e[cls_name] == cls_val:
            cnt1 += 1
            if e[attr_name] == attr_val:
                cnt2 += 1

    return cnt2 / cnt1


def NaiveBayes(data, test, cls_y, cls_n):
    p_positive = probability(data, cls_y)
    p_negative = probability(data, cls_n)
    for key, val in test.items():
        print key, val
        p_positive *= PT(data, cls_y, key, val)
        p_negative *= PT(data, cls_n, key, val)
    return {cls_y: p_positive, cls_n: p_negative}


if __name__ == "__main__":
    data = [
        {"outlook": "sunny", "temp": "hot", "humidity": "high", "wind": "weak", "class": "no"},
        {"outlook": "sunny", "temp": "hot", "humidity": "high", "wind": "strong", "class": "no"},
        {"outlook": "overcast", "temp": "hot", "humidity": "high", "wind": "weak", "class": "yes"},
        {"outlook": "rain", "temp": "mild", "humidity": "high", "wind": "weak", "class": "yes"},
        {"outlook": "rain", "temp": "cool", "humidity": "normal", "wind": "weak", "class": "yes"},
        {"outlook": "rain", "temp": "cool", "humidity": "normal", "wind": "strong", "class": "no"},
        {"outlook": "overcast", "temp": "cool", "humidity": "normal", "wind": "strong", "class": "yes"},
        {"outlook": "sunny", "temp": "mild", "humidity": "high", "wind": "weak", "class": "no"},
        {"outlook": "sunny", "temp": "cool", "humidity": "normal", "wind": "weak", "class": "yes"},
        {"outlook": "rain", "temp": "mild", "humidity": "normal", "wind": "weak", "class": "yes"},
        {"outlook": "sunny", "temp": "mild", "humidity": "normal", "wind": "strong", "class": "yes"},
        {"outlook": "overcast", "temp": "mild", "humidity": "high", "wind": "strong", "class": "yes"},
        {"outlook": "overcast", "temp": "hot", "humidity": "normal", "wind": "weak", "class": "yes"},
        {"outlook": "rain", "temp": "mild", "humidity": "high", "wind": "strong", "class": "no"},
    ]

    print NaiveBayes(data, {"outlook": "sunny", "temp": "cool", "humidity": "high", "wind": "strong"}, "yes", "no")
