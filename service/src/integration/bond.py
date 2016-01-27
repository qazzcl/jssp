from qpid.messaging import *
import datetime
import pprint

broker = "127.0.0.1:5672"
requestAddress = "demo.service.request"
responseAddress = "demo.service.response;{create:always}"
connection = Connection(broker)

try:
    connection.open()
    session = connection.session()

    sender = session.sender(requestAddress)
    receiver = session.receiver(responseAddress)

    content = {
        "bonds": [
            {
                "tradeDate": "20150403",
                "tenor": "1D",
                "listedMarket": "CIB",  # CIB, SSE, SZE
            },
            {
                "bondKey": "Z0001222010FINCSB0101",
                "tradeDate": "20150408",
                "tenor": "1D",
                "listedMarket": "CIB",  # CIB, SSE, SZE
                "fullPrice": 102.0,
                "rebate": 1.0
            },
            {
                "bondKey": "G0001242013FINPBB11",
                "settlementDate": "20150403",
                "listedMarket": "CIB",  # CIB, SSE, SZE
            },
            {
                "bondKey": "G0001242013FINPBB12",
                "settlementDate": "20140115",
                "listedMarket": "CIB",  # CIB, SSE, SZE
                "yield": 0.042,
                "predictRate": 0.043,  # only avail for FRN
                # "fullPrice": 101.12,
                # "cleanPrice": 103.3683,
                # "spread": 0.003, #float only
                # "yieldToExecution" 0.032 #option only
                # "yieldToCall" 0.032 #call option only
                # "yieldToPut" 0.032 #put option only
                "returnCashflow": False,
            },
            {
                "bondKey": "X0002132012FINNCB01",
                "settlementDate": "20150115",
                # "yield": 0.042,
                "fullPrice": 101.12,
                # "cleanPrice": 103.3683,
                # "spread": 0.003, #float only
                # "yieldToExecution" 0.032 #option only
                # "yieldToCall" 0.032 #call option only
                # "yieldToPut" 0.032 #put option only
                "returnCashflow": False,
            },
            {
                "bondKey": "H0001762015CORLMN01",
                "settlementDate": "20150520",
                "customCouponRate": 0.034,
                "yield": 0.05
            }
        ]
    }

    sentTime = datetime.datetime.now()
    msg = Message(content=content, reply_to=responseAddress, correlation_id="50189", properties={"messageId": str(uuid4())})

    sender.send(msg)
    response = receiver.fetch()

    pp = pprint.PrettyPrinter(depth=6)
    pp.pprint(response.content)

    session.acknowledge()

except MessagingError, err:
    print err

finally:
    connection.close()
