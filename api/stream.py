# -*- coding: utf-8 -*-
from http.server import BaseHTTPRequestHandler
import urllib.parse
import json

class handler(BaseHTTPRequestHandler):
    def do_OPTIONS(self):
        self.send_response(200)
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods', 'GET, OPTIONS')
        self.send_header('Access-Control-Allow-Headers', 'Range, Content-Type')
        self.end_headers()

    def do_GET(self):
        parsed = urllib.parse.urlparse(self.path)
        qs = urllib.parse.parse_qs(parsed.query)
        vid = qs.get('v', [None])[0]

        if not vid:
            self.send_response(400)
            self.send_header('Content-Type', 'application/json; charset=utf-8')
            self.send_header('Access-Control-Allow-Origin', '*')
            self.end_headers()
            self.wfile.write(json.dumps({'error': "Parametro 'v' requerido"}).encode('utf-8'))
            return

        # Indicar que para streaming nativo sin restricciones de YouTube se debe conectar al motor local
        self.send_response(503)
        self.send_header('Content-Type', 'application/json; charset=utf-8')
        self.send_header('Access-Control-Allow-Origin', '*')
        self.end_headers()
        self.wfile.write(json.dumps({
            'status': 'remote_mode',
            'message': 'Para streaming nativo directo sin restricciones de YouTube, use el motor local http://localhost:3000.',
            'videoId': vid,
            'directUrl': f"https://www.youtube.com/watch?v={vid}"
        }).encode('utf-8'))
