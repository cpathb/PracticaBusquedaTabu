#!/usr/bin/env python
# -*- coding: utf-8 -*-
import numpy

fd = open("resultados.txt","r")
file=fd.read()

resultados = file.split("\n")
resultados.pop()

for i in range(len(resultados)):
    resultados[i]= float(resultados[i])

print "Media: ", numpy.mean(resultados)
print "Desviación estándar: ", numpy.std(resultados)