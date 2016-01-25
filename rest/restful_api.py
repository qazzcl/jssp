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

app = Flask(__name__)
api = Api(app)
CORS(app)

broker = '127.0.0.1'

addr_demo = 'demo.service'

sender = Sender(broker, addr_demo)
sender.init()

class Demo1(Resource):

    def get(self, key):
        t = time.clock()
        response = self.sender.sync_send(
            Message(content={'key': key}, correlation_id='demo1'),
            timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="Demo1: get method failed.")


class Demo2(Resource):
    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('type')
        parser.add_argument('status')
        args = parser.parse_args()
        task_type = args['type']
        status = args['status']
        t = time.clock()
        response = self.sender.sync_send(Message(content={
                                            'task_type': task_type,
                                            'status': status,
                                        }, correlation_id='demo2_get'), timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="Demo2: get method failed.")


    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('filter_params', type=dict, location='json')
        args = parser.parse_args()
        print args
        filter_params = args['filter_params']
        t = time.clock()
        response = self.sender.sync_send(Message(content=json.dumps(filter_params), correlation_id='demo2_post'),
                                         timeout=3)
        print 'get cost time: {}'.format(time.clock() - t)
        if response:
            try:
                resp = make_response(zlib.decompress(response.content))
                resp.headers['content-type'] = 'text/plain'
                return resp
            except:
                pass

        abort(404, message="Demo2: post method failed.")


    def put(self, key):
        parser = reqparse.RequestParser()
        parser.add_argument('bonds', type=list, location='json')
        args = parser.parse_args()
        print args
        investable_bond = args['bonds']
        investable_bond['key'] = key
        investable_bond_list = [investable_bond]

        self.sender.send(Message(content=json.dumps(investable_bond_list), correlation_id='investment_pool_update'))
        return '', 202


api.add_resource(Demo1, '/demo1/<key>')
api.add_resource(Demo2, '/demo2')

if __name__ == '__main__':
    # gevent wsgi
    http_server = WSGIServer(('', 5000), app)
    http_server.serve_forever()

    # eventlet wsgi
    # http_server = server(eventlet.listen(('', 5000), backlog=1000), app)

    # app.run(host='0.0.0.0', threaded=True)
