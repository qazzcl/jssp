import time
import json
import zlib
import eventlet
from gevent.wsgi import WSGIServer
from eventlet.wsgi import server
from flask import Flask, make_response
from flask_restful import Api, Resource, reqparse, abort
from flask_cors import CORS


from qpid_helper import Sender, Message
'''
sudo pip install gevent
sudo pip install flask-restful
sudo pip install flask-cors

'''
app = Flask(__name__)
api = Api(app)
CORS(app)

broker = '127.0.0.1'

addr_bonds = 'test.bonds'
addr_quotes = 'test.quotes'
addr_deals = 'test.deals'
addr_money_market = 'test.money_market'
addr_primary_market = 'test.primary_market'
addr_cdc_data = 'cdc_data_request'
addr_research_task = 'test.research_task'
addr_investment_pool = 'test.investment_pool'
addr_manager_bond_task = 'test.manager_bond_task'
addr_primary_order = 'test.primary_order'
addr_secondary_order = 'test.secondary_order'
addr_money_order = 'test.money_order'

addr_position = 'account.position'
addr_asset = 'account.asset'

addr_cash = 'account.cash'

class BondList(Resource):
    sender = Sender(broker, addr_bonds)
    sender.init()

    def get(self):
        t = time.clock()
        response = self.sender.sync_send(Message(correlation_id='bond_list'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch bond list failed.")


class Bond(Resource):
    sender = Sender(broker, addr_bonds)
    sender.init()

    def get(self, bond_key_listed_market):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='bond'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch bond info failed.")


class BondKeyList(Resource):
    sender = Sender(broker, addr_bonds)
    sender.init()

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        print args
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='bond_key_list'),
                                         timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch bond key list failed.")


class QuoteList(Resource):
    sender = Sender(broker, addr_quotes)
    sender.init()

    def get(self):
        t = time.clock()
        response = self.sender.sync_send(Message(correlation_id='quote_list'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch quote list failed.")


class DealList(Resource):
    sender = Sender(broker, addr_deals)
    sender.init()

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('bond_key_listed_market')
        args = parser.parse_args()
        bond_key_listed_market = args['bond_key_listed_market']
        print 'bond_key_listed_market:', bond_key_listed_market
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='deals_list'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch deal list failed.")


class MoneyList(Resource):
    sender = Sender(broker, addr_money_market)
    sender.init()

    def get(self):
        t = time.clock()
        response = self.sender.sync_send(Message(correlation_id='mmlist'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch money list failed.")


class PrimaryMarketList(Resource):
    sender = Sender(broker, addr_primary_market)
    sender.init()

    def get(self):
        t = time.clock()
        response = self.sender.sync_send(Message(correlation_id='pmlist'), timeout=3)
        print '[pmlist] get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch primary market list failed.")


class PrimaryMarketRecommend(Resource):
    sender = Sender(broker, addr_primary_market)
    sender.init()

    def get(self, bond_key_listed_market):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='pmrecommend'),
            timeout=5)
        print '[pmrecommend] get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch recommended primary bonds failed.")

class PrimaryMarketBond(Resource):
    sender = Sender(broker, addr_primary_market)
    sender.init()

    def get(self, bond_key_listed_market):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='pmbond'),
            timeout=5)
        print '[pmbond] get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch primary bond failed.")


class CDCDataList(Resource):
    sender = Sender(broker, addr_cdc_data)
    sender.init()

    def get(self):
        t = time.clock()
        response = self.sender.sync_send(Message(correlation_id='cdc_all'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch cdc data list failed.")


class CDCData(Resource):
    sender = Sender(broker, addr_cdc_data)
    sender.init()

    def get(self, bond_key_listed_market):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='cdc_single'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch cdc data failed.")


class ResearchTaskList(Resource):
    sender = Sender(broker, addr_research_task)
    sender.init()

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('task_type')
        parser.add_argument('confirmed')
        args = parser.parse_args()
        task_type = args['task_type']
        confirmed = args['confirmed']
        print 'task_type:', task_type, 'confirmed:', confirmed
        t = time.clock()
        response = self.sender.sync_send(Message(content={
                                            'task_type': task_type,
                                            'confirmed': confirmed,
                                        }, correlation_id='task_list'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch research task list failed.")

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('task_list', type=list, location='json')
        args = parser.parse_args()
        print args
        task_list = args['task_list']
        self.sender.send(Message(content=json.dumps(task_list), correlation_id='task_add'))
        # response = sender.sync_send(Message(content=json.dumps(task_list), correlation_id='task_add'), timeout=3)
        # update_list = json.loads(response.content)
        return '', 202
        # return update_list
        # task_list = json.loads(args['task_list'])
        # print task_list


class ResearchTask(Resource):
    sender = Sender(broker, addr_research_task)
    sender.init()

    def get(self, task_id):
        t = time.clock()
        response = self.sender.sync_send(Message(content={'task_id': task_id}, correlation_id='task'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch research task failed.")

    def put(self, task_id):
        parser = reqparse.RequestParser()
        parser.add_argument('task', type=dict, location='json')
        args = parser.parse_args()
        print args
        task = args['task']
        task['task_id'] = task_id
        task_list = [task]

        self.sender.send(Message(content=json.dumps(task_list), correlation_id='task_update'))
        # response = sender.sync_send(Message(content=json.dumps(task_list), correlation_id='task_add'), timeout=3)
        # update_list = json.loads(response.content)
        return '', 202
        # return update_list
        # task_list = json.loads(args['task_list'])
        # print task_list

    def delete(self, task_id):
        task_list = [task_id]
        self.sender.send(Message(content=json.dumps(task_list), correlation_id='task_delete'))
        # response = sender.sync_send(Message(content=json.dumps(task_list), correlation_id='task_delete'), timeout=3)
        # update_list = json.loads(response.content)
        return '', 202
        # return update_list


class InvestableBondList(Resource):
    sender = Sender(broker, addr_investment_pool)
    sender.init()

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('investable')
        args = parser.parse_args()
        investable = args['investable']
        print 'investable:', investable
        t = time.clock()
        response = self.sender.sync_send(Message(content={'investable': investable}, correlation_id='investment_pool'),
                                         timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch investable bond list failed.")

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('investable_bond_list', type=list, location='json')
        args = parser.parse_args()
        print args
        investable_bond_list = args['investable_bond_list']
        self.sender.send(Message(content=json.dumps(investable_bond_list), correlation_id='investment_pool_add'))
        return '', 202


class InvestableBond(Resource):
    sender = Sender(broker, addr_investment_pool)
    sender.init()

    def get(self, bond_key_listed_market):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='investable_bond'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch investable bond failed.")

    def put(self, bond_key_listed_market):
        parser = reqparse.RequestParser()
        parser.add_argument('investable_bond', type=list, location='json')
        args = parser.parse_args()
        print args
        investable_bond = args['investable_bond']
        investable_bond['bond_key_listed_market'] = bond_key_listed_market
        investable_bond_list = [investable_bond]

        self.sender.send(Message(content=json.dumps(investable_bond_list), correlation_id='investment_pool_update'))
        return '', 202

    def delete(self, bond_key_listed_market):
        investable_bond_list = [bond_key_listed_market]
        self.sender.send(Message(content=json.dumps(investable_bond_list), correlation_id='investment_pool_delete'))
        return '', 202


class ManagerBondTaskList(Resource):
    sender = Sender(broker, addr_manager_bond_task)
    sender.init()

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('task_type')
        args = parser.parse_args()
        task_type = args['task_type']
        print 'task_type:', task_type
        t = time.clock()
        response = self.sender.sync_send(Message(content={'task_type': task_type}, correlation_id='task_list'),
                                         timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch manager bond task list failed.")

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('task_list', type=list, location='json')
        args = parser.parse_args()
        print args
        task_list = args['task_list']
        self.sender.send(Message(content=json.dumps(task_list), correlation_id='manager_task_add'))
        # response = sender.sync_send(Message(content=json.dumps(task_list), correlation_id='task_add'), timeout=3)
        # update_list = json.loads(response.content)
        return '', 202
        # return update_list
        # task_list = json.loads(args['task_list'])
        # print task_list


class ManagerBondTask(Resource):
    sender = Sender(broker, addr_manager_bond_task)
    sender.init()

    def get(self, task_id):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'task_id': task_id}, correlation_id='single_task'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch manager bond task failed.")

    def put(self, task_id):
        parser = reqparse.RequestParser()
        parser.add_argument('task', type=list, location='json')
        args = parser.parse_args()
        print args
        task = args['task']
        task['task_id'] = task_id
        task_list = [task]

        self.sender.send(Message(content=json.dumps(task_list), correlation_id='manager_task_update'))
        return '', 202

    def delete(self, task_id):
        task_list = [task_id]
        self.sender.send(Message(content=json.dumps(task_list), correlation_id='manager_task_delete'))
        return '', 202


class PrimaryOrderList(Resource):
    sender = Sender(broker, addr_primary_order)
    sender.init()

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('bond_key_listed_market')
        args = parser.parse_args()
        bond_key_listed_market = args['bond_key_listed_market']
        print 'bond_key_listed_market:', bond_key_listed_market
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='order_list'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch primary order list failed.")

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('order_list', type=list, location='json')
        args = parser.parse_args()
        print args
        order_list = args['order_list']
        self.sender.send(Message(content=json.dumps(order_list), correlation_id='primary_order_add'))
        # response = sender.sync_send(Message(content=json.dumps(task_list), correlation_id='task_add'), timeout=3)
        # update_list = json.loads(response.content)
        return '', 202
        # return update_list
        # task_list = json.loads(args['task_list'])
        # print task_list


class PrimaryOrder(Resource):
    sender = Sender(broker, addr_primary_order)
    sender.init()

    def get(self, order_id):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'order_id': order_id}, correlation_id='single_order'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch primary order failed.")

    def put(self, order_id):
        parser = reqparse.RequestParser()
        parser.add_argument('order', type=list, location='json')
        args = parser.parse_args()
        print args
        order = args['order']
        order['order_id'] = order_id
        order_list = [order]

        self.sender.send(Message(content=json.dumps(order_list), correlation_id='primary_order_update'))
        return '', 202

    def delete(self, order_id):
        order_list = [order_id]
        self.sender.send(Message(content=json.dumps(order_list), correlation_id='primary_order_delete'))
        return '', 202


class SecondaryOrderList(Resource):
    sender = Sender(broker, addr_secondary_order)
    sender.init()

    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('bond_key_listed_market')
        args = parser.parse_args()
        bond_key_listed_market = args['bond_key_listed_market']
        print 'bond_key_listed_market:', bond_key_listed_market
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'bond_key_listed_market': bond_key_listed_market}, correlation_id='order_list'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch secondary order list failed.")

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('order_list', type=list, location='json')
        args = parser.parse_args()
        print args
        order_list = args['order_list']
        self.sender.send(Message(content=json.dumps(order_list), correlation_id='secondary_order_add'))
        # response = sender.sync_send(Message(content=json.dumps(task_list), correlation_id='task_add'), timeout=3)
        # update_list = json.loads(response.content)
        return '', 202
        # return update_list
        # task_list = json.loads(args['task_list'])
        # print task_list


class SecondaryOrder(Resource):
    sender = Sender(broker, addr_secondary_order)
    sender.init()

    def get(self, order_id):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'order_id': order_id}, correlation_id='single_order'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch secondary order failed.")

    def put(self, order_id):
        parser = reqparse.RequestParser()
        parser.add_argument('order', type=list, location='json')
        args = parser.parse_args()
        print args
        order = args['order']
        order['order_id'] = order_id
        order_list = [order]

        self.sender.send(Message(content=json.dumps(order_list), correlation_id='secondary_order_update'))
        return '', 202

    def delete(self, order_id):
        order_list = [order_id]
        self.sender.send(Message(content=json.dumps(order_list), correlation_id='secondary_order_delete'))
        return '', 202


class MoneyOrderList(Resource):
    sender = Sender(broker, addr_money_order)
    sender.init()

    def get(self):
        t = time.clock()
        response = self.sender.sync_send(Message(correlation_id='order_list'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch money order list failed.")

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('order_list', type=list, location='json')
        args = parser.parse_args()
        print args
        order_list = args['order_list']
        self.sender.send(Message(content=json.dumps(order_list), correlation_id='money_order_add'))
        # response = sender.sync_send(Message(content=json.dumps(task_list), correlation_id='task_add'), timeout=3)
        # update_list = json.loads(response.content)
        return '', 202
        # return update_list
        # task_list = json.loads(args['task_list'])
        # print task_list


class MoneyOrder(Resource):
    sender = Sender(broker, addr_money_order)
    sender.init()

    def get(self, order_id):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'order_id': order_id}, correlation_id='single_order'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch secondary order failed.")

    def put(self, order_id):
        parser = reqparse.RequestParser()
        parser.add_argument('order', type=list, location='json')
        args = parser.parse_args()
        print args
        order = args['order']
        order['order_id'] = order_id
        order_list = [order]

        self.sender.send(Message(content=json.dumps(order_list), correlation_id='money_order_update'))
        return '', 202

    def delete(self, order_id):
        order_list = [order_id]
        self.sender.send(Message(content=json.dumps(order_list), correlation_id='money_order_delete'))
        return '', 202


class AccountPositionList(Resource):
    sender = Sender(broker, addr_position)
    sender.init()

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='query'),
                                         timeout=30)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch account list failed.")


class AccountPositionImport(Resource):
    sender = Sender(broker, addr_position)
    sender.init()

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='import'),
                                         timeout=30)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch account position failed.")


class AccountAssetList(Resource):
    sender = Sender(broker, addr_asset)
    sender.init()

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='query'),
                                         timeout=30)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch account asset failed.")


class AccountAssetImport(Resource):
    sender = Sender(broker, addr_asset)
    sender.init()

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='import'),
                                         timeout=30)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch account asset failed.")


class AccountCashList(Resource):
    sender = Sender(broker, addr_cash)
    sender.init()

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='query'),
                                         timeout=30)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch account cash failed.")


class AccountCashImport(Resource):
    sender = Sender(broker, addr_cash)
    sender.init()

    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='import'),
                                         timeout=30)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="fetch account cash failed.")


api.add_resource(QuoteList, '/quotes')
api.add_resource(BondList, '/bonds')
api.add_resource(Bond, '/bond/<bond_key_listed_market>')
api.add_resource(BondKeyList, '/bondkeys')
api.add_resource(DealList, '/deals')
api.add_resource(MoneyList, '/moneys')
api.add_resource(PrimaryMarketList, '/primary_markets')
api.add_resource(PrimaryMarketBond, '/primary_market/bond/<bond_key_listed_market>')
api.add_resource(PrimaryMarketRecommend, '/primary_market/recommend/<bond_key_listed_market>')
api.add_resource(CDCDataList, '/cdc_datas')
api.add_resource(CDCData, '/cdc_data/<bond_key_listed_market>')
api.add_resource(ResearchTaskList, '/research_tasks')
api.add_resource(ResearchTask, '/research_task/<task_id>')
api.add_resource(InvestableBondList, '/investable_bonds')
api.add_resource(InvestableBond, '/investable_bond/<bond_key_listed_market>')
api.add_resource(ManagerBondTaskList, '/manager_bond_tasks')
api.add_resource(ManagerBondTask, '/manager_bond_task/<task_id>')
api.add_resource(PrimaryOrderList, '/primary_orders')
api.add_resource(PrimaryOrder, '/primary_order/<order_id>')
api.add_resource(SecondaryOrderList, '/secondary_orders')
api.add_resource(SecondaryOrder, '/secondary_order/<order_id>')
api.add_resource(MoneyOrderList, '/money_orders')
api.add_resource(MoneyOrder, '/money_order/<order_id>')
api.add_resource(AccountPositionList, '/account_position')
api.add_resource(AccountAssetList, '/account_asset')
api.add_resource(AccountCashList, '/account_cash')
api.add_resource(AccountPositionImport, '/account_position_import')
api.add_resource(AccountAssetImport, '/account_asset_import')
api.add_resource(AccountCashImport, '/account_cash_import')

if __name__ == '__main__':
    # gevent wsgi
    http_server = WSGIServer(('', 5000), app)
    http_server.serve_forever()

    # eventlet wsgi
    # http_server = server(eventlet.listen(('', 5000), backlog=1000), app)

    # app.run(host='0.0.0.0', threaded=True)
