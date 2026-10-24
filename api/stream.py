# -*- coding: utf-8 -*-
from http.server import BaseHTTPRequestHandler
import urllib.parse

# Motor de streaming nativo dedicado de alta velocidad (sin bloqueos de bot)
TUNNEL_STREAM_URL = "https://reveals-assessments-introductory-experiments.trycloudflare.com/api/stream"

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
            self.send_header('Content-Type', 'text/plain; charset=utf-8')
            self.send_header('Access-Control-Allow-Origin', '*')
            self.end_headers()
            self.wfile.write(b"Parametro 'v' requerido")
            return

        # Redirigir al motor de streaming nativo activo de alta velocidad
        target_url = f"{TUNNEL_STREAM_URL}?v={vid}"
        self.send_response(302)
        self.send_header('Location', target_url)
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Headers', 'Range')
        self.send_header('Access-Control-Expose-Headers', 'Content-Range, Content-Length, Accept-Ranges')
        self.send_header('Cache-Control', 'public, max-age=3600')
        self.end_headers()
