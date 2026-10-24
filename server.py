# -*- coding: utf-8 -*-
"""
Servidor Educativo Local para Academia UDA - Laboratorio II & Programacion II
Provee servidor HTTP local y Proxy de Streaming Nativo de Videos (Bypass a bloqueos de iframe de YouTube)
"""
import sys

# Asegurar codificacion UTF-8 en consola de Windows
if sys.platform.startswith('win'):
    try:
        sys.stdout.reconfigure(encoding='utf-8', errors='replace')
        sys.stderr.reconfigure(encoding='utf-8', errors='replace')
    except Exception:
        pass

import http.server
import socketserver
import urllib.request
import urllib.parse
import os
import json
import yt_dlp

PORT = 3000
DIRECTORY = os.path.dirname(os.path.abspath(__file__))

# Cache en memoria de URLs resueltas para inicio instantaneo de chunks
stream_cache = {}

def resolve_direct_stream(video_id):
    if video_id in stream_cache:
        return stream_cache[video_id]

    ydl_opts = {
        'quiet': True,
        'no_warnings': True,
        'extractor_args': {
            'youtube': {
                'player_client': ['android', 'ios']
            }
        }
    }
    with yt_dlp.YoutubeDL(ydl_opts) as ydl:
        info = ydl.extract_info(f"https://www.youtube.com/watch?v={video_id}", download=False)
        stream_url = None
        
        # Prioridad 1: Formato 18 (MP4 360p audio+video combinado estandar)
        for f in info.get('formats', []):
            if f.get('format_id') == '18' and f.get('url'):
                stream_url = f.get('url')
                break
        
        # Prioridad 2: Cualquier formato combinado con audio y video
        if not stream_url:
            for f in info.get('formats', []):
                if f.get('vcodec') != 'none' and f.get('acodec') != 'none' and f.get('url'):
                    stream_url = f.get('url')
                    break
        
        if stream_url:
            stream_cache[video_id] = stream_url
            return stream_url
        return None

class AcademicPlatformHandler(http.server.SimpleHTTPRequestHandler):
    protocol_version = "HTTP/1.1"

    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=DIRECTORY, **kwargs)

    def do_GET(self):
        parsed = urllib.parse.urlparse(self.path)
        
        # 1. Endpoint de Streaming Nativo
        if parsed.path == '/api/stream':
            qs = urllib.parse.parse_qs(parsed.query)
            vid = qs.get('v', [None])[0]
            if not vid:
                self.send_error(400, "Parametro 'v' (ID de video) requerido")
                return

            try:
                real_url = resolve_direct_stream(vid)
                if not real_url:
                    self.send_error(404, "No se encontro flujo de video compatible")
                    return

                range_header = self.headers.get('Range')
                req_headers = {
                    'User-Agent': 'com.google.android.youtube/19.29.37 (Linux; U; Android 11) gzip'
                }
                if range_header:
                    req_headers['Range'] = range_header

                req = urllib.request.Request(real_url, headers=req_headers)
                with urllib.request.urlopen(req) as resp:
                    self.send_response(resp.status)
                    for h, v in resp.headers.items():
                        hl = h.lower()
                        if hl in ['content-type', 'content-length', 'content-range', 'accept-ranges']:
                            self.send_header(h, v)
                    self.send_header('Access-Control-Allow-Origin', '*')
                    self.send_header('Cache-Control', 'public, max-age=3600')
                    self.end_headers()

                    while True:
                        chunk = resp.read(65536)
                        if not chunk:
                            break
                        self.wfile.write(chunk)
            except Exception as e:
                # Si el cliente cierra el reproductor o salta en la barra de tiempo
                pass
            return

        # 2. Endpoint de Estado del Servidor
        elif parsed.path == '/api/health':
            self.send_response(200)
            self.send_header('Content-Type', 'application/json; charset=utf-8')
            self.send_header('Access-Control-Allow-Origin', '*')
            self.end_headers()
            self.wfile.write(json.dumps({'status': 'ok', 'server': 'UDA Educational Streaming Engine'}).encode('utf-8'))
            return

        # 3. Archivos estaticos normales con cabeceras CORS
        super().do_GET()

    def end_headers(self):
        self.send_header('Access-Control-Allow-Origin', '*')
        super().end_headers()

def run_server():
    socketserver.TCPServer.allow_reuse_address = True
    with socketserver.ThreadingTCPServer(("", PORT), AcademicPlatformHandler) as httpd:
        print("============================================================")
        print("  [+] SERVIDOR EDUCATIVO UDA ACTIVO")
        print(f"  [+] Disponible en: http://localhost:{PORT}")
        print("  [+] Motor de Streaming Nativo: ACTIVO (Sin bloqueos de YouTube)")
        print("============================================================")
        sys.stdout.flush()
        try:
            httpd.serve_forever()
        except KeyboardInterrupt:
            print("\nServidor cerrado.")

if __name__ == '__main__':
    run_server()
