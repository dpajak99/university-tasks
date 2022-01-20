#!/bin/bash
for f in images/variants/*.png; do echo "\\includegraphics[width=0.9\\linewidth]{$f}\\\\"; done;