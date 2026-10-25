Set WshShell = CreateObject("WScript.Shell")
projectDir = "C:\Users\User\Desktop\Aprendiendo a programar Java y C++"

' Iniciar server.py en segundo plano (0 = ventana invisible)
WshShell.Run "python """ & projectDir & "\server.py""", 0, False
