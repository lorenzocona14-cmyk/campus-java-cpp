# -*- coding: utf-8 -*-
"""
Servidor Educativo Local para Academia UDA - Laboratorio II & Programacion II
Provee servidor HTTP local y Proxy de Streaming Nativo de Videos (Bypass a bloqueos de YouTube)
"""
import sys
import os
import http.server
import socketserver
import urllib.request
import urllib.parse
import urllib.error
import json
import socket
import yt_dlp

# Asegurar codificacion UTF-8 en consola de Windows
if sys.platform.startswith('win'):
    try:
        sys.stdout.reconfigure(encoding='utf-8', errors='replace')
        sys.stderr.reconfigure(encoding='utf-8', errors='replace')
    except Exception:
        pass

PORT = 3000
DIRECTORY = os.path.dirname(os.path.abspath(__file__))

# Cache en memoria de URLs directas
stream_cache = {}

def get_local_ip():
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.settimeout(0.5)
        s.connect(('8.8.8.8', 80))
        ip = s.getsockname()[0]
        s.close()
        return ip
    except Exception:
        return "127.0.0.1"

def resolve_direct_stream(video_id, force_refresh=False):
    if not force_refresh and video_id in stream_cache:
        return stream_cache[video_id]

    ydl_opts = {
        'quiet': True,
        'no_warnings': True,
        'extractor_args': {
            'youtube': {
                'player_client': ['android']
            }
        }
    }
    try:
        with yt_dlp.YoutubeDL(ydl_opts) as ydl:
            info = ydl.extract_info(f"https://www.youtube.com/watch?v={video_id}", download=False)
            stream_url = None
            
            # Prioridad 1: Formato 18 (MP4 360p audio+video combinado estándar)
            for f in info.get('formats', []):
                if f.get('format_id') == '18' and f.get('url'):
                    stream_url = f.get('url')
                    break
            
            # Prioridad 2: Cualquier formato MP4 combinado con audio y video
            if not stream_url:
                for f in info.get('formats', []):
                    if f.get('vcodec') != 'none' and f.get('acodec') != 'none' and f.get('url'):
                        stream_url = f.get('url')
                        break
            
            if stream_url:
                stream_cache[video_id] = stream_url
                return stream_url
    except Exception as e:
        print(f"[-] Error al resolver video {video_id}: {e}")
    return None

class AcademicPlatformHandler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=DIRECTORY, **kwargs)

    def do_OPTIONS(self):
        self.send_response(200)
        self.end_headers()

    def do_GET(self):
        parsed = urllib.parse.urlparse(self.path)
        
        # 1. Endpoint de Streaming Nativo
        if parsed.path == '/api/stream':
            qs = urllib.parse.parse_qs(parsed.query)
            vid = qs.get('v', [None])[0]
            if not vid:
                self.send_error(400, "Parametro 'v' (ID de video) requerido")
                return

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

            # Intentar abrir el flujo, reintentando una vez si expiró (403/410)
            resp = None
            try:
                req = urllib.request.Request(real_url, headers=req_headers)
                resp = urllib.request.urlopen(req, timeout=10)
            except urllib.error.HTTPError as he:
                if he.code in [403, 410]:
                    real_url = resolve_direct_stream(vid, force_refresh=True)
                    if real_url:
                        req = urllib.request.Request(real_url, headers=req_headers)
                        try:
                            resp = urllib.request.urlopen(req, timeout=10)
                        except Exception:
                            resp = None
                if not resp:
                    self.send_error(502, f"Error al conectar con el servidor de video: {he}")
                    return
            except Exception as e:
                self.send_error(502, f"Error de conexion al flujo: {e}")
                return

            try:
                self.send_response(resp.status)
                for h, v in resp.headers.items():
                    hl = h.lower()
                    if hl in ['content-type', 'content-length', 'content-range', 'accept-ranges']:
                        self.send_header(h, v)
                self.send_header('Cache-Control', 'public, max-age=3600')
                self.end_headers()

                while True:
                    chunk = resp.read(65536)
                    if not chunk:
                        break
                    self.wfile.write(chunk)
            except (ConnectionResetError, BrokenPipeError):
                # El reproductor salto a otra posicion o pauso la conexion
                pass
            except Exception as e:
                pass
            finally:
                resp.close()
            return

        # 2. Endpoint de Estado del Servidor
        elif parsed.path == '/api/health':
            self.send_response(200)
            self.send_header('Content-Type', 'application/json; charset=utf-8')
            self.end_headers()
            self.wfile.write(json.dumps({
                'status': 'ok',
                'streaming': True,
                'server': 'UDA Educational Streaming Engine',
                'version': '2.1'
            }).encode('utf-8'))
            return

        # 3. Archivos estaticos normales
        super().do_GET()

    def end_headers(self):
        # Cabeceras CORS limpias sin duplicados
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Access-Control-Allow-Methods', 'GET, HEAD, OPTIONS')
        self.send_header('Access-Control-Allow-Headers', 'Range, Content-Type, Accept')
        self.send_header('Access-Control-Expose-Headers', 'Content-Range, Content-Length, Accept-Ranges')
        super().end_headers()

def run_server():
    socketserver.TCPServer.allow_reuse_address = True
    local_ip = get_local_ip()
    with socketserver.ThreadingTCPServer(("", PORT), AcademicPlatformHandler) as httpd:
        print("============================================================")
        print("  [+] SERVIDOR EDUCATIVO UDA ACTIVO")
        print(f"  [+] En esta computadora:  http://localhost:{PORT}")
        print(f"  [+] En celular / tablet:   http://{local_ip}:{PORT}")
        print("  [+] Motor de Streaming Nativo: ACTIVO (Sin bloqueos de YouTube)")
        print("============================================================")
        sys.stdout.flush()
        try:
            httpd.serve_forever()
        except KeyboardInterrupt:
            print("\nServidor cerrado.")

if __name__ == '__main__':
    run_server()
